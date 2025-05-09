/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabsensi.admin.data;

import java.sql.Time;

/**
 *
 * @author rss
 */
public class Shift {

	public Integer idShift;
	public Time jamMasukKerja;
	public Time jamPulangKerja;
	public Time jamIstirahat;
	public Time jamSelesaiIstirahat;

	public Shift() {
		this(null, null, null, null, null);
	}

	public Shift(Integer idShift, Time jamMasukKerja, Time jamPulangKerja, Time JamIstirahat, Time jamSelesaiIstirahat) {
		this.idShift = idShift;
		this.jamMasukKerja = jamMasukKerja;
		this.jamPulangKerja = jamPulangKerja;
		this.jamIstirahat = jamIstirahat;
		this.jamSelesaiIstirahat = jamSelesaiIstirahat;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Shift) {
			return this.idShift == ((Shift) obj).idShift;
		} else {
			return super.equals(obj);
		}
	}

	public String jadwalKerja() {
		return String.format("%s - %s", this.jamMasukKerja, this.jamPulangKerja);
	}

	public String jadwalIstirahat() {
		return String.format("%s - %s", this.jamIstirahat, this.jamSelesaiIstirahat);
	}

	@Override
	public String toString() {
		return this.idShift.toString();
	}
}
