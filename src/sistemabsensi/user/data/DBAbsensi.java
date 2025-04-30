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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import sistemabsensi.database.connection.DBConnection;

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

	//------------------------------------------------------------------------------------------------------------//
	// Buatkan detail record baru untuk sebuah record                                                             //
	//------------------------------------------------------------------------------------------------------------//
	
	public void buatDetailRecord(int idRecord, String catatan) {

		final String sql = 
			"INSERT INTO tdetailAbsen("
				+ "id_recordabsen,"
				+ " catatan_absen,"
				+ " time_created,"
				+ " time_modified)"
			+ " VALUES (?,?,?,?)";

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);

			final Timestamp timestampSekarang = Timestamp.valueOf(LocalDateTime.now());

			query.setInt(1, idRecord);
			query.setString(2, catatan);
			query.setTimestamp(3, timestampSekarang);
			query.setTimestamp(4, timestampSekarang);

		} catch (SQLException e) {
			exitError("Gagal Membuat Detail Record", e);
		}
	}

	//-----------------------------------------------------------------------------------------------------------------//
	// Lakukan UPDATE pada sebuah data tabel record abse menggunakan data yang disimpan pada sebuah RecordAbsen        //
	//-----------------------------------------------------------------------------------------------------------------//
	
	public void updateRecordAbsenKaryawan(final RecordAbsen recordAbsen) throws SQLException {
		final String sql = 
			"UPDATE trecordabsen"
			+ " SET "
				+ "jamAbsenMasuk = ?,"
				+ " jamAbsenPulang = ?,"
				+ " jamAbsenMulaiIstirahat = ?,"
				+ " jamAbsenKembaliIstirahat = ?,"
				+ " time_modified = ?"
			+ " WHERE"
				+ " id_recordabsen = ?;";
		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);

			final Timestamp timestampSekarang = Timestamp.valueOf(LocalDateTime.now());

			query.setTime(1, recordAbsen.getWaktuMasuk());
			query.setTime(2, recordAbsen.getWaktuPulang());
			query.setTime(3, recordAbsen.getWaktuIstirahat());
			query.setTime(4, recordAbsen.getWaktuSelesaiIstirahat());
			query.setTimestamp(5, timestampSekarang);
			query.setInt(6, recordAbsen.getIdRecord());

			int affected = query.executeUpdate();

			System.out.println("UPDATE RECORD ABSEN: " + affected);

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

	}

	//--------------------------------------------------------------------------------------------------------------------//
	// Kembalikan Sebuah Linked List / Daftar semua data record yang dimiliki seorang karyawan                            //
	//--------------------------------------------------------------------------------------------------------------------//
	
	public LinkedList<RecordAbsen> getDaftarRecordAbsenKaryawan(String idKaryawan) throws SQLException {
		final String sql = "SELECT * FROM trecordabsen where id_karyawan = ?;";

		try {
			LinkedList<RecordAbsen> daftarTanggal = new LinkedList<>();
			PreparedStatement query = this.getConnection().prepareStatement(sql);

			query.setString(1, idKaryawan);

			ResultSet result = query.executeQuery();

			while (result.next()) {
				RecordAbsen record = new RecordAbsen();
				record.setIdKaryawan(result.getString("id_karyawan"));
				record.setIdRecord(result.getInt("id_recordabsen"));
				record.setWaktuMasuk(result.getTime("jamAbsenMasuk"));
				record.setWaktuPulang(result.getTime("jamAbsenPulang"));
				record.setWaktuIstirahat(result.getTime("jamAbsenMulaiIstirahat"));
				record.setWaktuSelesaiIstirahat(result.getTime("jamAbsenKembaliIstirahat"));
				record.setTglRecord(result.getDate("tanggalrecord"));
				daftarTanggal.add(record);
			}

			return daftarTanggal;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	//-----------------------------------------------------------------------------------------------------//
	// Dapatkan sebuah data record absen karyawan pada suatu tanggal                                       //
	// (hanya akan ada 1 record untuk tiap tanggal)                                                        //
	//-----------------------------------------------------------------------------------------------------//

	public RecordAbsen getRecordAbsenKaryawan(String idKaryawan, Date tanggalRecord) throws SQLException {
		final String sql = "SELECT * FROM trecordabsen where id_karyawan = ? and tanggalrecord = ?;";

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);

			query.setString(1, idKaryawan);
			query.setDate(2, tanggalRecord);

			ResultSet result = query.executeQuery();

			if (!result.next()) {
				throw new SQLException("DATA TIDAK DITEMUKAN");
			}
			
			RecordAbsen record = new RecordAbsen();
			record.setIdKaryawan(result.getString("id_karyawan"));
			record.setIdRecord(result.getInt("id_recordabsen"));
			record.setWaktuMasuk(result.getTime("jamAbsenMasuk"));
			record.setWaktuPulang(result.getTime("jamAbsenPulang"));
			record.setWaktuIstirahat(result.getTime("jamAbsenMulaiIstirahat"));
			record.setWaktuSelesaiIstirahat(result.getTime("jamAbsenKembaliIstirahat"));
			record.setTglRecord(result.getDate("tanggalrecord"));

			return record;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	//-------------------------------------------------------------------------------------------------------------------------------//
	// Buatkan Record Absen untuk seorang karyawan                                                                                   // 
	//-------------------------------------------------------------------------------------------------------------------------------//
	
	private void createRecordAbsenBaruKaryawan(String idKaryawan) throws SQLException {
		final String sql = 
			"INSERT INTO "
			+ "trecordabsen("
				+ "id_karyawan,"
				+ " tanggalrecord,"
				+ " time_created,"
				+ " time_modified)"
			+ " values "
			+ "	(?,?,?,?);";

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);

			final Timestamp timestampSekarang = Timestamp.valueOf(LocalDateTime.now());
			final Date tanggalSekarang = Date.valueOf(LocalDate.now());

			query.setString(1, idKaryawan);
			query.setDate(2, tanggalSekarang);
			query.setTimestamp(3, timestampSekarang);
			query.setTimestamp(4, timestampSekarang);

			int affected = query.executeUpdate();

			System.out.println("MENAMBAHKAN RECORD ABSEN BARU: " + affected);

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	
	//--------------------------------------------------------------------------------------------------------------------------------//
	// Dapakan record seorang karyawan, jika tidak ada, buatkan yang baru                                                             //
	// (HANYA BOLEH ADA SATU RECORD ABSEN PER HARI NYA)                                                                               //
	//--------------------------------------------------------------------------------------------------------------------------------//
	public RecordAbsen getDataRecordAbsenTerkini(String idKaryawan) throws SQLException {
		final String sql = 
			"SELECT * FROM trecordabsen"
			+ " WHERE "
				+ "id_karyawan = ? AND tanggalrecord = ?;";

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);

			query.setString(1, idKaryawan);
			query.setDate(2, Date.valueOf(LocalDate.now()));

			ResultSet result = query.executeQuery();

			if (result.next()) {
				RecordAbsen record = new RecordAbsen();
				record.setIdKaryawan(result.getString("id_karyawan"));
				record.setIdRecord(result.getInt("id_recordabsen"));
				record.setWaktuMasuk(result.getTime("jamAbsenMasuk"));
				record.setWaktuPulang(result.getTime("jamAbsenPulang"));
				record.setWaktuIstirahat(result.getTime("jamAbsenMulaiIstirahat"));
				record.setWaktuSelesaiIstirahat(result.getTime("jamAbsenKembaliIstirahat"));
				record.setTglRecord(result.getDate("tanggalrecord"));
				return record;
			} else {
				createRecordAbsenBaruKaryawan(idKaryawan);
				getDataRecordAbsenTerkini(idKaryawan);
			}

			return null;

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
		final String sql = 
			"SELECT "
				+ "a.id_karyawan, a.nama_karyawan, a.password, b.nama_prodi,"
				+ " c.nama_jabatan, d.role, e.jamMasukKerja, e.jamPulangKerja,"
				+ " e.jamIstirahat, e.jamSelesaiIstirahat"
			+ " FROM"
				+ " tkaryawan a, tprodi b, tjabatan c, trole d, tshift e"
			+ " WHERE"
				+ " a.id_karyawan = ? "
				+ "AND a.id_prodi = b.id_prodi "
				+ "AND a.id_jabatan = c.id_jabatan "
				+ "AND a.id_role = d.id_role "
				+ "AND a.id_shift = e.id_shift;";

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);

			query.setString(1, idKaryawan);

			ResultSet result = query.executeQuery();

			if (result.next()) {
				Karyawan karyawan = new Karyawan();

				karyawan.setIdKaryawan(result.getString("id_karyawan"));
				karyawan.setNamaKaryawan(result.getString("nama_karyawan"));
				karyawan.setPassword(result.getString("password"));
				karyawan.setNamaProdi(result.getString("nama_prodi"));
				karyawan.setRole(result.getString("role"));
				karyawan.setJabatan(result.getString("nama_jabatan"));

				karyawan.setShift(new Shift(
					result.getTime("jamMasukKerja"),
					result.getTime("jamPulangKerja"),
					result.getTime("jamIstirahat"),
					result.getTime("jamSelesaiIstirahat")
				));
				
				karyawan.setRecordAbsen(
					getDataRecordAbsenTerkini(karyawan.getIdKaryawan())
				);

				return karyawan;
			}

			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

}
