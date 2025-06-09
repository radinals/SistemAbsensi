/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabsensi.database.admin;

/**
 *
 * @author rss
 */
public class Jabatan {

	public Integer idJabatan;
	public String namaJabatan;

	public Jabatan() {
		this(null, null);
	}

	public Jabatan(Integer idJabatan, String namaJabatan) {
		this.idJabatan = idJabatan;
		this.namaJabatan = namaJabatan;
	}

	@Override
	public String toString() {
		return namaJabatan;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Jabatan) {
			return this.idJabatan == ((Jabatan) obj).idJabatan;
		} else {
			return super.equals(obj);
		}
	}

}
