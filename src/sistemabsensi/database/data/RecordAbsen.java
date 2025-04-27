/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabsensi.database.data;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

/**
 *
 * @author rss
 */
public class RecordAbsen {
	private int idRecord;
	private String idKaryawan;
	private Time waktuMasuk;
	private Time waktuPulang;
	private Time waktuIstirahat;
	private Time waktuSelesaiIstirahat;
	private Date tglRecord;

	public RecordAbsen(int idRecord, int idKaryawan, Time waktuMasuk, Time waktuPulang, Time waktuIstirahat, Time waktuSelesaiIstirahat, Date tglRecord) {
		this.waktuMasuk = waktuMasuk;
		this.waktuPulang = waktuPulang;
		this.waktuIstirahat = waktuIstirahat;
		this.waktuSelesaiIstirahat = waktuSelesaiIstirahat;
		this.tglRecord = tglRecord;
	}

	public RecordAbsen() {
		this(-1, -1, null, null, null, null, null);
	}
	
	private Time getWaktuTerkini() {
		return Time.valueOf(LocalTime.now());
	}
	
	public void catatWaktuMasuk() {
		this.waktuMasuk = getWaktuTerkini();
	}
	
	public void catatWaktuPulang() {
		this.waktuPulang = getWaktuTerkini();
	}
	
	public void catatWaktuIstirahat() {
		this.waktuIstirahat = getWaktuTerkini();
	}
	
	public void catatWaktuSelesaiIstirahat() {
		this.waktuSelesaiIstirahat = getWaktuTerkini();
	}

	public void setWaktuMasuk(Time waktuMasuk) {
		this.waktuMasuk = waktuMasuk;
	}

	public void setWaktuPulang(Time waktuPulang) {
		this.waktuPulang = waktuPulang;
	}

	public void setWaktuIstirahat(Time waktuIstirahat) {
		this.waktuIstirahat = waktuIstirahat;
	}

	public void setWaktuSelesaiIstirahat(Time waktuSelesaiIstirahat) {
		this.waktuSelesaiIstirahat = waktuSelesaiIstirahat;
	}

	public int getIdRecord() {
		return idRecord;
	}

	public void setIdRecord(int idRecord) {
		this.idRecord = idRecord;
	}

	public String getIdKaryawan() {
		return idKaryawan;
	}

	public void setIdKaryawan(String idKaryawan) {
		this.idKaryawan = idKaryawan;
	}
	
	public Date getTglRecord() {
		return tglRecord;
	}

	public void setTglRecord(Date tglRecord) {
		this.tglRecord = tglRecord;
	}

	public Time getWaktuMasuk() {
		return waktuMasuk;
	}

	public Time getWaktuPulang() {
		return waktuPulang;
	}

	public Time getWaktuIstirahat() {
		return waktuIstirahat;
	}

	public Time getWaktuSelesaiIstirahat() {
		return waktuSelesaiIstirahat;
	}
	
	
}
