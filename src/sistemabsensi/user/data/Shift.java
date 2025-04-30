/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabsensi.user.data;

import java.sql.Time;

/**
 *
 * @author rss
 */
public class Shift {
	private final Time waktuMasuk;
	private final Time waktuPulang;
	private final Time waktuIstirahat;
	private final Time waktuSelesaiIstirahat;

	public Shift(Time waktuMasuk, Time waktuPulang, Time waktuIstirahat, Time waktuSelesaiIstirahat) {
		this.waktuMasuk = waktuMasuk;
		this.waktuPulang = waktuPulang;
		this.waktuIstirahat = waktuIstirahat;
		this.waktuSelesaiIstirahat = waktuSelesaiIstirahat;
	}
	
	// getter
	// (Tidak memerlukan setter karena user tidak
	//  memiliki kemampuan untuk merubah shift)

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
