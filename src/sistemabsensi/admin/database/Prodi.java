/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabsensi.admin.database;

/**
 *
 * @author rss
 */
public class Prodi {

	public Integer id;
	public String namaProdi;

	public Prodi() {
		this(null, null);
	}

	public Prodi(Integer id, String namaProdi) {
		this.id = id;
		this.namaProdi = namaProdi;
	}

	@Override
	public String toString() {
		return namaProdi;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Prodi) {
			return this.id == ((Prodi) obj).id;
		} else {
			return super.equals(obj);
		}
	}

}
