/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabsensi.admin.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	public LinkedList<Jabatan> getDaftarDataJabatan() throws SQLException {
		final String sql = "SELECT * FROM tjabatan";

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);
			
			ResultSet result = query.executeQuery();

			while(result.next()) {
				// create jabatan, add to list.
			}

			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public LinkedList<Karyawan> getDaftarDataKaryawan() throws SQLException {
		final String sql = "SELECT * FROM tkaryawan";

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);
			
			ResultSet result = query.executeQuery();

			while(result.next()) {
				// create karyawan, add to list.
			}

			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public LinkedList<Prodi> getDaftarDataProdi() throws SQLException {
		final String sql = "SELECT * FROM tprodi";

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);
			
			ResultSet result = query.executeQuery();

			while(result.next()) {
				// create prodi, add to list.
			}

			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}	
	}
	
	public LinkedList<RecordAbsen> getDaftarDataRecordAbsen() throws SQLException {
		final String sql = "SELECT * FROM tprodi ";

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);
			
			ResultSet result = query.executeQuery();

			while(result.next()) {
				// create recordabsen, add to list.
			}

			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public LinkedList<RecordAbsen> getDaftarDataRecordAbsen(String idKaryawan) throws SQLException {
		final String sql = "SELECT * FROM tprodi WHERE id_karyawan = ?";

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);
			
			query.setString(1, idKaryawan);
			
			ResultSet result = query.executeQuery();

			while(result.next()) {
				// create recordabsen, add to list.
			}

			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public LinkedList<CatatanRecord> getDaftarDataCatatanRecord(int idRecordAbsen) throws SQLException {
		final String sql = "SELECT * FROM tdetailrecord WHERE id_recordabsen = ?";

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);
			
			query.setInt(1, idRecordAbsen);
			
			ResultSet result = query.executeQuery();

			while(result.next()) {
				// create detailrecordabsen, add to list.
			}

			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public LinkedList<Shift> getDaftarDataShift() throws SQLException {
		final String sql = "SELECT * FROM tshift";

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);
			
			ResultSet result = query.executeQuery();

			while(result.next()) {
				// create shift, add to list.
			}

			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void updateDataKaryawan(final Karyawan karyawan) {
		
	}
	
	public void updateDataJabatan(final Jabatan jabatan) {
		
	}
	
	public void updateDataProdi(final Prodi prodi) {
		
	}
	
	public void updateDataRecordAbsen(final RecordAbsen record) {
		
	}
	
	public void updateDataCatatanRecord(final CatatanRecord catatan) {
		
	}
	
	public void updateDataShift(final Shift shift) {
		
	}
	
	public void deleteDataKaryawan(String idKaryawan) {
		
	}
	
	public void deleteDataJabatan(int idJabatan) {
		
	}
	
	public void deleteDataProdi(int idProdi) {
		
	}
	
	public void deleteDataRecordAbsen(int idRecord) {
		
	}
	
	public void deleteDataCatatanRecord(int idDetailRecord) {
		
	}
	
	public void deleteDataShift(int idShift) {
		
	}
	

}
