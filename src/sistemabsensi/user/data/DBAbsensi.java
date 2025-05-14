/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabsensi.user.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import sistemabsensi.database.connection.DBConnection;
import sistemabsensi.user.data.RecordAbsen.TipeAbsen;

/**
 *
 * @author rss
 */
public class DBAbsensi {

	private DBConnection database;
	private Statement statement;

	public DBAbsensi() {
		this.database = new DBConnection("jdbc:mysql://localhost/dbabsensi", "root", "");

		try {
			this.statement = this.database.createStatement();
		} catch (Exception ex) {
			System.err.println("Gagal membuat statement");
			System.exit(-1);
		}
	}

	public Connection getConnection() {
		return this.database.getConnection();
	}

	private void exitError(String msg, Exception e) {
		System.err.println(msg + ": " + e);
		System.exit(-1);
	}

	//---------------------------------------------------------------------------------------------------------//
	// Method-Method untuk mengecek jika sebuah data sudah ada atau tidak                                      //
	//---------------------------------------------------------------------------------------------------------//
	public boolean isDataDetailAbsenAda(int idDetailAbsen) {
		try {
			ResultSet result = statement.executeQuery(String.format(
				"SELECT 1 FROM tdetailabsen WHERE id_detailabsen = %d", idDetailAbsen));
			return result.next();
		} catch (SQLException e) {
			exitError("Gagal Melakukan Query", e);
		}

		return false;
	}

	public boolean isDataJabatanAda(int idJabatan) {
		try {
			ResultSet result = statement.executeQuery(String.format(
				"SELECT 1 FROM tjabatan WHERE id_jabatan = %d", idJabatan));
			return result.next();
		} catch (SQLException e) {
			exitError("Gagal Melakukan Query", e);
		}

		return false;
	}

	public boolean isDataKaryawanAda(String idKaryawan) {
		try {
			ResultSet result = statement.executeQuery(String.format(
				"SELECT 1 FROM tkaryawan WHERE id_karyawan = '%s'", idKaryawan));
			return result.next();
		} catch (SQLException e) {
			exitError("Gagal Melakukan Query", e);
		}
		return false;
	}

	public boolean isDataRecordAbsenKaryawanAda(int idKaryawan, Date tanggalSekarang) {
		try {
			ResultSet result = statement.executeQuery(String.format(
				"SELECT 1 FROM trecordabsen WHERE id_karyawan = %d and tanggalrecord = %s", idKaryawan, tanggalSekarang));
			return result.next();
		} catch (SQLException e) {
			exitError("Gagal Melakukan Query", e);
		}
		return false;
	}

	public boolean isDataProdiAda(int idProdi) {
		try {
			ResultSet result = statement.executeQuery(String.format(
				"SELECT 1 FROM tprodi WHERE id_prodi = %d", idProdi));
			return result.next();
		} catch (SQLException e) {
			exitError("Gagal Melakukan Query", e);
		}
		return false;
	}

	public boolean isDataRecordAbsenAda(int idRecordAbsen) {
		try {
			ResultSet result = statement.executeQuery(String.format(
				"SELECT 1 FROM trecordabsen WHERE id_recordabsen = %d", idRecordAbsen));
			return result.next();
		} catch (SQLException e) {
			exitError("Gagal Melakukan Query", e);
		}
		return false;
	}

	public boolean isDataRoleAda(int idRole) {
		try {
			ResultSet result = statement.executeQuery(String.format(
				"SELECT 1 FROM trole WHERE id_role = %d", idRole));
			return result.next();
		} catch (SQLException e) {
			exitError("Gagal Melakukan Query", e);
		}
		return false;
	}

	public boolean isDataShiftAda(int idShift) {
		try {
			ResultSet result = statement.executeQuery(String.format(
				"SELECT 1 FROM tshift WHERE id_shift = %d", idShift));
			return result.next();
		} catch (SQLException e) {
			exitError("Gagal Melakukan Query", e);
		}
		return false;
	}
	
	public void tambahkanRecordAbsen(String id_karyawan, TipeAbsen tipe_absen, String catatan) throws SQLException {
		final String sql = "INSERT INTO trecordabsen values (default,?,?,?,?);";

		try {
			LinkedList<RecordAbsen> daftarTanggal = new LinkedList<>();
			PreparedStatement query = this.getConnection().prepareStatement(sql);
			
			Date tanggalSekarang = Date.valueOf(LocalDate.now());

			RecordAbsen record = RecordAbsen.buatRecordAbsen(tipe_absen, id_karyawan, catatan);
			
			query.setTimestamp(1, record.waktu_absen);
			query.setString(2, record.id_karyawan);
			query.setString(3, record.catatan_absen);
			query.setString(4, record.tipe_absen.toString());

			int affected = query.executeUpdate();
			
			System.out.println("MENGINSERT " + affected + " recordabsen.");

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public boolean adaDataRecordAbsenHariIni(String idKaryawan, TipeAbsen tipe) throws SQLException {
		final String sql = "SELECT * FROM trecordabsen WHERE id_karyawan = ? AND DATE(waktu_absen) = ? AND tipe_absen = ?;";

		try {
			LinkedList<RecordAbsen> daftarTanggal = new LinkedList<>();
			PreparedStatement query = this.getConnection().prepareStatement(sql);
			
			Date tanggalSekarang = Date.valueOf(LocalDate.now());

			query.setString(1, idKaryawan);
			query.setDate(2, tanggalSekarang);
			query.setString(3, tipe.toString());

			ResultSet result = query.executeQuery();

			return result.next();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	//--------------------------------------------------------------------------------------------------------------------//
	// Kembalikan Sebuah Linked List / Daftar semua data record yang dimiliki seorang karyawan                            //
	//--------------------------------------------------------------------------------------------------------------------//
	public LinkedList<RecordAbsen> getDaftarRecordAbsenKaryawanHariIni(String idKaryawan) throws SQLException {
		final String sql = "SELECT * FROM trecordabsen WHERE id_karyawan = ? AND DATE(waktu_absen) = ?;";

		try {
			LinkedList<RecordAbsen> daftarTanggal = new LinkedList<>();
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
					
				}
				
				record.catatan_absen = result.getString("catatan_absen");
				daftarTanggal.add(record);
			}

			return daftarTanggal;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	//-------------------------------------------------------------------------------------------------------------------//
	// Query SELECT join-relasi ke tabel-tabel data karyawan (prodi, shift, jabatan) dan juga membuat/mendapatkan        //
	// record absen untuk karyawan tersebut.                                                                             //
	//-------------------------------------------------------------------------------------------------------------------//
	public Karyawan getDataKaryawan(String idKaryawan) throws SQLException {
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

			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
