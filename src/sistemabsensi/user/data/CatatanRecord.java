/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabsensi.user.data;

/**
 *
 * @author rss
 */

//-----------------------------------------------------------------------------//
// Class ini untuk membuat data baru pada tdetailabsen, catatan absensi        //
// hanya diperlukan untuk membuat catatan, user tidak dapat mengedit           //
//-----------------------------------------------------------------------------//
public class CatatanRecord {
	private final int idRecord;
	private DBAbsensi db;
	
	public CatatanRecord(DBAbsensi db, int idRecord) {
		this.idRecord = idRecord;
		this.db = db;
	}
	
	public void tambahkanCatatan(String catatan, KategoriCatatanAbsen kategori) {
		this.db.buatDetailRecord(idRecord, catatan, kategori);
	}
}
