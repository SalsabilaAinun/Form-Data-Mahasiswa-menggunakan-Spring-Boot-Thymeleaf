package com.tutorial.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.tutorial.demo.model.Mahasiswa;

public interface MahasiswaService {
	List<Mahasiswa> getAllMahasiswa();
	void saveMahasiswa(Mahasiswa mahasiswa);
	Mahasiswa getMahasiswaById(long id);
	void deleteMahasiswaById(long id);
	Page<Mahasiswa> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
