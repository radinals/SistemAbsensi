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
	private String namaProdi;
	private String jabatan;
	private Shift shift;
	private RecordAbsen recordAbsen;

	public Karyawan() {
		this(null, null, null, null, null, null);
	}

	public Karyawan(String idKaryawan, String namaKaryawan, String namaProdi, String jabatan, Shift shift, RecordAbsen recordAbsen) {
		this.idKaryawan = idKaryawan;
		this.namaKaryawan = namaKaryawan;
		this.namaProdi = namaProdi;
		this.jabatan = jabatan;
		this.shift = shift;
		this.recordAbsen = recordAbsen;
	}

	public void setIdKaryawan(String idKaryawan) {
		this.idKaryawan = idKaryawan;
	}

	public void setNamaKaryawan(String namaKaryawan) {
		this.namaKaryawan = namaKaryawan;
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
}
