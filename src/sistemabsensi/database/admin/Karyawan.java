/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabsensi.database.admin;

/**
 *
 * @author rss
 */
public class Karyawan {

	public String idKaryawan;
	public String namaKaryawan;
	public Integer idProdi;
	public Integer idShift;
	public Integer idJabatan;
	public String alamat;
	public String noTelepon;
	public String email;

	public Karyawan() {
		this(null, null, null, null, null, null, null, null);
	}

	public Karyawan(
		String idKaryawan, String namaKaryawan, Integer idProdi,
		Integer idShift, Integer idJabatan, String alamat, String noTelepon, String email
	) {
		this.idKaryawan = idKaryawan;
		this.namaKaryawan = namaKaryawan;
		this.idProdi = idProdi;
		this.idJabatan = idJabatan;
		this.idShift = idShift;
		this.alamat = alamat;
		this.noTelepon = noTelepon;
		this.email = email;
	}

	public String getIdKaryawan() {
		return idKaryawan;
	}

	public void setIdKaryawan(String idKaryawan) {
		this.idKaryawan = idKaryawan;
	}

	public String getNamaKaryawan() {
		return namaKaryawan;
	}

	public void setNamaKaryawan(String namaKaryawan) {
		this.namaKaryawan = namaKaryawan;
	}

	public Integer getIdProdi() {
		return idProdi;
	}

	public void setIdProdi(Integer idProdi) {
		this.idProdi = idProdi;
	}

	public Integer getIdShift() {
		return idShift;
	}

	public void setIdShift(Integer idShift) {
		this.idShift = idShift;
	}

	public Integer getIdJabatan() {
		return idJabatan;
	}

	public void setIdJabatan(Integer idJabatan) {
		this.idJabatan = idJabatan;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getNoTelepon() {
		return noTelepon;
	}

	public void setNoTelepon(String noTelepon) {
		this.noTelepon = noTelepon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Override
	public String toString() {
		return this.idKaryawan + ":" + this.namaKaryawan;
	}
}
