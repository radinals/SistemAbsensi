/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabsensi.admin.data;

import java.sql.Timestamp;

/**
 *
 * @author rss
 */
public class RecordAbsen {
	public enum TipeAbsen {
		ABSEN_MASUK,
		ABSEN_PULANG,
		ABSEN_ISTIRAHAT,
		ABSEN_KEMBALI_ISTIRAHAT,
		INVALID
	};
	
	public enum StatusAbsen {
		TELAT,
		TEPAT_WAKTU,
		TERLALU_DINI,
		INVALID
	}
	
	public Integer id_recordabsen;
	public Timestamp waktu_absen;
	public String id_karyawan;
	public String catatan_absen;
	public TipeAbsen tipe_absen;
	public StatusAbsen status_absen;
	
	public RecordAbsen() {
		this(null, null, null, null, null, null);
	}
	
	public RecordAbsen(Integer id_recordabsen, Timestamp waktu_absen, String id_karyawan,StatusAbsen status_absen, String catatan_absen, TipeAbsen tipe_absen) {
		this.id_recordabsen = id_recordabsen;
		this.id_karyawan = id_karyawan;
		this.status_absen = status_absen;
		this.catatan_absen = catatan_absen;
		this.waktu_absen = waktu_absen;
		this.tipe_absen = tipe_absen;
	}

}
