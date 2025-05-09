/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabsensi.admin.data;

/**
 *
 * @author rss
 */
public class DetailRecord {

	public Integer idDetailRecord;
	public Integer idRecordAbsen;
	public String catatanAbsen;
	public String kdKategori;

	public DetailRecord() {
		this(null, null, null, null);
	}

	public DetailRecord(Integer idDetailRecord, Integer idRecordAbsen, String catatanAbsen, String kdKategori) {
		this.idDetailRecord = idDetailRecord;
		this.idRecordAbsen = idRecordAbsen;
		this.catatanAbsen = catatanAbsen;
		this.kdKategori = kdKategori;
	}
}
