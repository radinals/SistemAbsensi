/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabsensi.user.data;

/**
 *
 * @author rss
 */


//------------------------------------------------------------------------------------------------------//
// Class Karyawan menyimpan seluruh set data yang dimiliki atau berhubungan langsung seorang karyawan.  //
//------------------------------------------------------------------------------------------------------//

public class Karyawan {

	private String idKaryawan;
	private String namaKaryawan;
	private String password;
	private String namaProdi;
	private String jabatan;
	private Shift shift;
	private String role;
	private RecordAbsen recordAbsen;

	public Karyawan() {
		this(null, null, null, null, null, null, null, null);
	}

	public Karyawan(String idKaryawan, String namaKaryawan, String password, String namaProdi, String jabatan, Shift shift, String role, RecordAbsen recordAbsen) {
		this.idKaryawan = idKaryawan;
		this.namaKaryawan = namaKaryawan;
		this.password = password;
		this.namaProdi = namaProdi;
		this.jabatan = jabatan;
		this.shift = shift;
		this.role = role;
		this.recordAbsen = recordAbsen;
	}

	public void setIdKaryawan(String idKaryawan) {
		this.idKaryawan = idKaryawan;
	}

	public void setNamaKaryawan(String namaKaryawan) {
		this.namaKaryawan = namaKaryawan;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNamaProdi(String namaProdi) {
		this.namaProdi = namaProdi;
	}

	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
	}

	public void setRecordAbsen(RecordAbsen recordAbsen) {
		this.recordAbsen = recordAbsen;
	}

	public String getNamaKaryawan() {
		return namaKaryawan;
	}

	public String getRole() {
		return role;
	}

	public String getPassword() {
		return password;
	}

	public String getNamaProdi() {
		return namaProdi;
	}

	public String getJabatan() {
		return jabatan;
	}

	public Shift getShift() {
		return shift;
	}

	public String getIdKaryawan() {
		return idKaryawan;
	}

	public RecordAbsen getRecordAbsen() {
		return recordAbsen;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
