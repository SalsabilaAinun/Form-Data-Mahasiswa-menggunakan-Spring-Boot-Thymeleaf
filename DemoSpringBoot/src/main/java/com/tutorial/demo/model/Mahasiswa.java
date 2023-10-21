package com.tutorial.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mahasiswa")
public class Mahasiswa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nama")
	private String nama;
	
	@Column(name = "nim")
	private String nim;
	
	@Column(name = "alamat")
	private String alamat;
	
	@Column(name = "tgllahir")
	private String tgllahir;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getNim() {
		return nim;
	}
	public void setNim(String nim) {
		this.nim = nim;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public String getTgllahir() {
		return tgllahir;
	}
	public void setTgllahir(String tgllahir) {
		this.tgllahir = tgllahir;
	}

}
