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
	public Time shift_start;
	public Time shift_end;
	public String deskripsi;

	public Shift() {
		this(null, null, null, null);
	}

	public Shift(Integer idShift, Time shift_start, Time shift_end, String deskripsi) {
		this.idShift = idShift;
		this.shift_end = shift_end;
		this.shift_start = shift_start;
		this.deskripsi = deskripsi;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Shift) {
			return this.idShift == ((Shift) obj).idShift;
		} else {
			return super.equals(obj);
		}
	}

	public String jadwal() {
		return String.format("%s - %s", this.shift_start, this.shift_end);
	}

	@Override
	public String toString() {
		return String.format("(%s) %s [%s]", this.idShift.toString(), this.deskripsi, jadwal());
	}
}
