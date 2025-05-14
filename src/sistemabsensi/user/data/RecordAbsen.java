/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabsensi.user.data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author rss
 */
public class RecordAbsen {
	public enum TipeAbsen {
		ABSEN_MASUK,
		ABSEN_PULANG,
		ABSEN_ISTIRAHAT,
		ABSEN_KEMBALI_ISTIRAHAT;
	};
	
	public int id_recordabsen;
	public Timestamp waktu_absen;
	public String id_karyawan;
	public String catatan_absen;
	public TipeAbsen tipe_absen;
	
	public RecordAbsen() {
		this(-1, null, null, null, null);
	}
	
	public RecordAbsen(int id_recordabsen, Timestamp waktu_absen, String id_karyawan, String catatan_absen, TipeAbsen tipe_absen) {
		this.id_recordabsen = id_recordabsen;
		this.id_karyawan = id_karyawan;
		this.catatan_absen = catatan_absen;
		this.waktu_absen = waktu_absen;
		this.tipe_absen = tipe_absen;
	}
	
	public static RecordAbsen buatRecordAbsen(TipeAbsen tipe, String id_karyawan, String catatan) {
		RecordAbsen record = new RecordAbsen();
		
		LocalDateTime waktuTerkini = LocalDateTime.now();
		
		record.waktu_absen = Timestamp.valueOf(waktuTerkini);
		record.id_karyawan = id_karyawan;
		record.tipe_absen = tipe;
		record.catatan_absen = catatan;
		
		return record;
	}
}
