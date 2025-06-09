/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabsensi.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import sistemabsensi.database.connection.DBConnection;

/**
 *
 * @author rss
 */
public class DatabaseAbsensi {
	
	private DBConnection database;
	private Statement statement;

	public DatabaseAbsensi() {
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
	
	public boolean isDataDetailAbsenAda(int idDetailAbsen) {
		try {
			ResultSet result = statement.executeQuery(String.format(
				"SELECT 1 FROM tdetailabsen WHERE id_detailabsen = %d", idDetailAbsen));
			return result.next();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		return false;
	}

	public boolean isDataJabatanAda(int idJabatan) {
		try {
			ResultSet result = statement.executeQuery(String.format(
				"SELECT 1 FROM tjabatan WHERE id_jabatan = %d", idJabatan));
			return result.next();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		return false;
	}

	public boolean isDataJabatanAda(String jabatan) {
		try {
			ResultSet result = statement.executeQuery(String.format(
				"SELECT 1 FROM tjabatan WHERE nama_jabatan = '%s'", jabatan));
			return result.next();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		return false;
	}

	public boolean isDataKaryawanAda(String idKaryawan) {
		try {
			ResultSet result = statement.executeQuery(String.format(
				"SELECT 1 FROM tkaryawan WHERE id_karyawan = '%s'", idKaryawan));
			return result.next();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return false;
	}

	public boolean isDataRecordAbsenKaryawanAda(int idKaryawan, Date tanggalSekarang) {
		try {
			ResultSet result = statement.executeQuery(String.format(
				"SELECT 1 FROM trecordabsen WHERE id_karyawan = %d and tanggalrecord = %s", idKaryawan, tanggalSekarang));
			return result.next();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return false;
	}

	public boolean isDataProdiAda(int idProdi) {
		try {
			ResultSet result = statement.executeQuery(String.format(
				"SELECT 1 FROM tprodi WHERE id_prodi = %d", idProdi));
			return result.next();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return false;
	}

	public boolean isDataProdiAda(String namaProdi) {
		try {
			ResultSet result = statement.executeQuery(String.format(
				"SELECT 1 FROM tprodi WHERE nama_prodi = '%s'", namaProdi));
			return result.next();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return false;
	}

	public boolean isDataRecordAbsenAda(int idRecordAbsen) {
		try {
			ResultSet result = statement.executeQuery(String.format(
				"SELECT 1 FROM trecordabsen WHERE id_recordabsen = %d", idRecordAbsen));
			return result.next();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return false;
	}

	public boolean isDataShiftAda(int idShift) {
		try {
			ResultSet result = statement.executeQuery(String.format(
				"SELECT 1 FROM tshift WHERE id_shift = %d", idShift));
			return result.next();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return false;
	}

}
