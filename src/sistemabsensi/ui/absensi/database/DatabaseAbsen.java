/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabsensi.ui.absensi.database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.LinkedList;
import sistemabsensi.database.DatabaseAbsensi;
import sistemabsensi.database.RecordAbsen;
import sistemabsensi.database.Shift;
import sistemabsensi.database.StatusAbsen;
import sistemabsensi.database.TipeAbsen;

/**
 *
 * @author rss
 */
public class DatabaseAbsen extends DatabaseAbsensi {
	
	public void tambahkanRecordAbsen(String id_karyawan, TipeAbsen tipe_absen, Timestamp waktuAbsen, StatusAbsen status_absen, String catatan) {
		final String sql = "INSERT INTO trecordabsen(waktu_absen, id_karyawan,status_absen,catatan_absen,tipe_absen)"
			+ " values (?,?,?,?,?);";

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);

			RecordAbsen record = new RecordAbsen();
				
			record.catatan_absen = catatan;
			record.id_karyawan = id_karyawan;
			record.status_absen = status_absen;
			record.waktu_absen = waktuAbsen;
			record.tipe_absen = tipe_absen;
			
			query.setTimestamp(1, record.waktu_absen);
			query.setString(2, record.id_karyawan);
			query.setString(3, record.status_absen.toString());
			query.setString(4, record.catatan_absen);
			query.setString(5, record.tipe_absen.toString());

			int affected = query.executeUpdate();
			
			System.out.println("MENGINSERT " + affected + " recordabsen.");

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	
	public boolean adaDataRecordAbsenHariIni(String idKaryawan, TipeAbsen tipe) {
		final String sql = "SELECT 1 FROM trecordabsen"
			+ " WHERE id_karyawan = ? AND DATE(waktu_absen) = ? AND tipe_absen = ?;";

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);
			
			Date tanggalSekarang = Date.valueOf(LocalDate.now()); // dapatkan tanggal terkini

			query.setString(1, idKaryawan);
			query.setDate(2, tanggalSekarang);
			query.setString(3, tipe.toString());

			ResultSet result = query.executeQuery();

			return result.next();

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		return false;
	}

	//--------------------------------------------------------------------------------------------------------------------//
	// Kembalikan Sebuah Linked List / Daftar semua data record yang dimiliki seorang karyawan                            //
	//--------------------------------------------------------------------------------------------------------------------//
	public LinkedList<RecordAbsen> getDaftarRecordAbsenKaryawanHariIni(String idKaryawan)  {
		final String sql = "SELECT * FROM trecordabsen WHERE id_karyawan = ? AND DATE(waktu_absen) = ?;";

		try {
			LinkedList<RecordAbsen> daftarRecord = new LinkedList<>();
			PreparedStatement query = this.getConnection().prepareStatement(sql);
			
			Date tanggalSekarang = Date.valueOf(LocalDate.now());

			query.setString(1, idKaryawan);
			query.setDate(2, tanggalSekarang);

			ResultSet result = query.executeQuery();

			while (result.next()) {
				RecordAbsen record = new RecordAbsen();
				record.id_recordabsen = result.getInt("id_recordabsen");
				record.id_karyawan = result.getString("id_karyawan");
				record.waktu_absen = result.getTimestamp("waktu_absen");
				
				switch(result.getString("status_absen")) {
					case "TELAT":
						record.status_absen = StatusAbsen.TELAT;
						break;
					case "TEPAT_WAKTU":
						record.status_absen = StatusAbsen.TEPAT_WAKTU;
						break;
					case "TERLALU_DINI":
						record.status_absen = StatusAbsen.TERLALU_DINI;
						break;
					case "INVALID":
						record.status_absen = StatusAbsen.INVALID;
						break;
				}
				
				switch(result.getString("tipe_absen")) {
					case "ABSEN_MASUK":
						record.tipe_absen = TipeAbsen.ABSEN_MASUK;
						break;
					case "ABSEN_PULANG":
						record.tipe_absen = TipeAbsen.ABSEN_PULANG;
						break;
					case "ABSEN_ISTIRAHAT":
						record.tipe_absen = TipeAbsen.ABSEN_ISTIRAHAT;
						break;
					case "ABSEN_KEMBALI_ISTIRAHAT":
						record.tipe_absen = TipeAbsen.ABSEN_KEMBALI_ISTIRAHAT;
						break;
					case "INVALID":
						record.tipe_absen = TipeAbsen.INVALID;
						break;
					
				}
				
				record.catatan_absen = result.getString("catatan_absen");
				daftarRecord.add(record);
			}

			return daftarRecord;

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		return null;
	}

	//-------------------------------------------------------------------------------------------------------------------//
	// Query SELECT join-relasi ke tabel-tabel data karyawan (prodi, shift, jabatan) dan juga membuat/mendapatkan        //
	// record absen untuk karyawan tersebut.                                                                             //
	//-------------------------------------------------------------------------------------------------------------------//
	public Karyawan getDataKaryawan(String idKaryawan) {
		final String sql
			= "SELECT "
			+ "a.id_karyawan, a.nama_karyawan,"
			+ " c.nama_jabatan, e.id_shift, e.deskripsi, e.shift_start, e.shift_end"
			+ " FROM"
			+ " tkaryawan a, tprodi b, tjabatan c, tshift e"
			+ " WHERE"
			+ " a.id_karyawan = ? "
			+ "AND a.id_prodi = b.id_prodi "
			+ "AND a.id_jabatan = c.id_jabatan "
			+ "AND a.id_shift = e.id_shift;";

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);

			query.setString(1, idKaryawan);

			ResultSet result = query.executeQuery();

			if (result.next()) {
				Karyawan karyawan = new Karyawan();

				karyawan.setIdKaryawan(result.getString("id_karyawan"));
				karyawan.setNamaKaryawan(result.getString("nama_karyawan"));
				karyawan.setShift(new Shift(
					result.getInt("id_shift"),
					result.getTime("shift_start"),
					result.getTime("shift_end"),
					result.getString("deskripsi")
				));

				return karyawan;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		return null;
	}
}
