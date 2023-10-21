package com.tutorial.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tutorial.demo.model.Mahasiswa;
import com.tutorial.demo.service.MahasiswaService;

@Controller
public class MahasiswaController {
	
	@Autowired
	private MahasiswaService mahasiswaService;
	// display list of mahasiswa
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "Nama", "asc", model);
		//model.addAttribute("listMahasiswa", mahasiswaService.getAllMahasiswa());
		//return "index";
	}
	
	@GetMapping("/showNewMahasiswaForm")
	public String showNewMahasiswaForm(Model model) {
		//create model attribute to bind form data
		Mahasiswa mahasiswa = new Mahasiswa();
		model.addAttribute("mahasiswa", mahasiswa);
		return "new_mahasiswa";
	}
	
	@PostMapping("/saveMahasiswa")
	public String saveMahasiswa(@ModelAttribute("mahasiswa") Mahasiswa mahasiswa) {
		//save mahasiswa to database
		mahasiswaService.saveMahasiswa(mahasiswa);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable (value="id") long id, Model model) {
		
		//get mahasiswa from the service
		Mahasiswa mahasiswa = mahasiswaService.getMahasiswaById(id);
		
		//set mahasiswa asa a model attribute to pre-populate the form
		model.addAttribute("mahasiswa", mahasiswa);
		return "update_mahasiswa";
	}
	
	@GetMapping("/deleteMahasiswa/{id}")
	public String deleteMahasiswa(@PathVariable (value = "id") long id) {
		
		//call delete mahasiswa method
		this.mahasiswaService.deleteMahasiswaById(id);
		return "redirect:/";
	}
		
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Mahasiswa> page = mahasiswaService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Mahasiswa> listMahasiswa = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listMahasiswa", listMahasiswa);
		return "index";
	}
}
