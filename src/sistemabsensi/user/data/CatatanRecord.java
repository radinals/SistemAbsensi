/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabsensi.user.data;

/**
 *
 * @author rss
 */
public class CatatanRecord {
	private final int idRecord;
	private DBAbsensi db;
	
	public CatatanRecord(DBAbsensi db, int idRecord) {
		this.idRecord = idRecord;
		this.db = db;
	}
	
	public void tambahkanCatatan(String catatan) {
		this.db.buatDetailRecord(idRecord, catatan);
	}
}
