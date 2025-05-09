/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabsensi.admin.data;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author rss
 */
public class RecordAbsen {

	public Integer idRecord;
	public String idKaryawan;
	public Time jamAbsenMasuk;
	public Time jamAbsenIstirahat;
	public Time jamAbsenKembaliIstirahat;
	public Time jamAbsenPulang;
	public Date tanggalRecord;

	public RecordAbsen() {
		this(null, null, null, null, null, null, null);
	}

	public RecordAbsen(
		Integer idRecord, String idKaryawan,
		Time jamAbsenMasuk, Time jamAbsenIstirahat,
		Time jamAbsenKembaliIstirahat, Time jamAbsenPulang,
		Date tanggalRecord
	) {
		this.idRecord = idRecord;
		this.idKaryawan = idKaryawan;
		this.jamAbsenMasuk = jamAbsenMasuk;
		this.jamAbsenIstirahat = jamAbsenIstirahat;
		this.jamAbsenKembaliIstirahat = jamAbsenKembaliIstirahat;
		this.jamAbsenPulang = jamAbsenPulang;
		this.tanggalRecord = tanggalRecord;
	}

}
