/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabsensi.admin.data;

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
}
