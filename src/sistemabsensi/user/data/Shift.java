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
	public int id_shift;
	public Time start;
	public Time end;
	public String deskripsi;
	
	public Shift() {
		this(-1, null, null, null);
	}
	
	public Shift(int id_shift, Time start, Time end, String deskripsi) {
		this.id_shift = id_shift;
		this.start = start;
		this.end = end;
		this.deskripsi = deskripsi;
	}
	
}
