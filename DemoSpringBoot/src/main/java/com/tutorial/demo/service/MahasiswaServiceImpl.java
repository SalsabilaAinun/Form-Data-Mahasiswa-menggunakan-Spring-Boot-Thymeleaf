package com.tutorial.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tutorial.demo.model.Mahasiswa;
import com.tutorial.demo.repository.MahasiswaRepository;

@Service
public class MahasiswaServiceImpl implements MahasiswaService {
	
	@Autowired
	private MahasiswaRepository mahasiswaRepository;
	
	@Override
	public List<Mahasiswa> getAllMahasiswa() {
		return mahasiswaRepository.findAll();
	}
	
	@Override
	public void saveMahasiswa(Mahasiswa mahasiswa) {
		this.mahasiswaRepository.save(mahasiswa);
	}
	
	@Override
	public Mahasiswa getMahasiswaById(long id) {
		Optional<Mahasiswa> optional = mahasiswaRepository.findById(id);
		Mahasiswa mahasiswa = null;
		if(optional.isPresent()) {
			mahasiswa = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return mahasiswa;
	}
	
	@Override
	public void deleteMahasiswaById(long id) {
		this.mahasiswaRepository.deleteById(id);
	}
	
	@Override
	public Page<Mahasiswa> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.mahasiswaRepository.findAll(pageable);
	}
}
