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
import javax.swing.table.DefaultTableModel;
import sistemabsensi.admin.ui.Pesan;
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

	public boolean isDataJabatanAda(final Jabatan jabatan) {
		try {
			ResultSet result = statement.executeQuery(String.format(
				"SELECT 1 FROM tjabatan WHERE id_jabatan = %d AND nama_jabatan = '%s'", jabatan.idJabatan, jabatan.namaJabatan));
			return result.next();
		} catch (SQLException e) {
			exitError("Gagal Melakukan Query", e);
		}

		return false;
	}

	public boolean isDataJabatanAda(String jabatan) {
		try {
			ResultSet result = statement.executeQuery(String.format(
				"SELECT 1 FROM tjabatan WHERE id_jabatan = '%s'", jabatan));
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

	public boolean isDataProdiAda(String namaProdi) {
		try {
			ResultSet result = statement.executeQuery(String.format(
				"SELECT 1 FROM tprodi WHERE nama_prodi = '%s'", namaProdi));
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

		LinkedList<Jabatan> data = new LinkedList<>();

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);

			ResultSet result = query.executeQuery();

			while (result.next()) {
				data.add(new Jabatan(result.getInt("id_jabatan"), result.getString("nama_jabatan")));
			}

			return data;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public LinkedList<Karyawan> getDaftarDataKaryawan() throws SQLException {
		final String sql = "SELECT * FROM tkaryawan";

		LinkedList<Karyawan> data = new LinkedList<>();

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);

			ResultSet result = query.executeQuery();

			while (result.next()) {
				Karyawan karyawan = new Karyawan();
				karyawan.idKaryawan = result.getString("id_karyawan");
				karyawan.namaKaryawan = result.getString("nama_karyawan");
				karyawan.idJabatan = result.getInt("id_jabatan");
				karyawan.idProdi = result.getInt("id_prodi");
				karyawan.idShift = result.getInt("id_shift");
				karyawan.alamat = result.getString("alamat");
				karyawan.noTelepon = result.getString("no_telp");
				karyawan.email = result.getString("email");
				data.add(karyawan);
			}

			return data;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public LinkedList<Prodi> getDaftarDataProdi() throws SQLException {
		final String sql = "SELECT * FROM tprodi";

		LinkedList<Prodi> data = new LinkedList<>();

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);

			ResultSet result = query.executeQuery();

			while (result.next()) {
				data.add(new Prodi(result.getInt("id_prodi"), result.getString("nama_prodi")));
			}

			return data;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public LinkedList<Shift> getDaftarDataShift() throws SQLException {
		final String sql = "SELECT * FROM tshift";

		LinkedList<Shift> data = new LinkedList<>();

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);

			ResultSet result = query.executeQuery();

			while (result.next()) {
				Shift shift = new Shift();

				shift.idShift = result.getInt("id_shift");
				shift.shift_start = result.getTime("shift_start");
				shift.shift_end = result.getTime("shift_end");
				shift.deskripsi = result.getString("deskripsi");

				data.add(shift);
			}

			return data;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	//----------------------------------------------------------------------

	public Object[] getDataKaryawan(String idKaryawan) throws SQLException {
		final String sql
			= "SELECT "
			+ "a.id_karyawan, a.nama_karyawan, a.id_prodi, b.nama_prodi, "
			+ "a.id_jabatan, c.nama_jabatan, "
			+ "a.id_shift, e.deskripsi, "
			+ "e.shift_start, e.shift_end, "
			+ "a.email, a.no_telp, a.alamat "
			+ "FROM tkaryawan a "
			+ "LEFT JOIN tprodi b ON a.id_prodi = b.id_prodi "
			+ "LEFT JOIN tjabatan c ON a.id_jabatan = c.id_jabatan "
			+ "LEFT JOIN tshift e ON a.id_shift = e.id_shift "
			+ "WHERE a.id_karyawan = ?";

		try (PreparedStatement query = this.getConnection().prepareStatement(sql)) {
			query.setString(1, idKaryawan);
			ResultSet result = query.executeQuery();

			if (result.next()) {

				Karyawan karyawan = new Karyawan();
				karyawan.alamat = result.getString("alamat");
				karyawan.namaKaryawan = result.getString("nama_karyawan");
				karyawan.email = result.getString("email");
				karyawan.noTelepon = result.getString("no_telp");
				karyawan.idKaryawan = result.getString("id_karyawan");
				karyawan.idJabatan = null;
				karyawan.idProdi = null;
				karyawan.idShift = null;

				Prodi prodi = null;
				int idProdi = result.getInt("id_prodi");
				if (!result.wasNull() && result.getString("nama_prodi") != null) {
					karyawan.idProdi = idProdi;
					prodi = new Prodi(idProdi, result.getString("nama_prodi"));
				}

				Jabatan jabatan = null;
				int idJabatan = result.getInt("id_jabatan");
				if (!result.wasNull() && result.getString("nama_jabatan") != null) {
					karyawan.idJabatan = idJabatan;
					jabatan = new Jabatan(idJabatan, result.getString("nama_jabatan"));
				}

				Shift shift = null;
				int idShift = result.getInt("id_shift");
				if (!result.wasNull() && result.getTime("shift_start") != null) {
					shift = new Shift();
					karyawan.idShift = idShift;
					shift.idShift = idShift;
					shift.shift_start = result.getTime("shift_start");
					shift.shift_end = result.getTime("shift_end");
					shift.deskripsi = result.getString("deskripsi");

				}

				return new Object[]{karyawan, prodi, jabatan, shift};
			}

			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public Jabatan getDataJabatan(String namaJabatan) throws SQLException {
		final String sql
			= "SELECT * FROM tjabatan where nama_jabatan = ?";

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);

			query.setString(1, namaJabatan);

			ResultSet result = query.executeQuery();

			if (result.next()) {
				return new Jabatan(result.getInt("id_jabatan"), result.getString("nama_jabatan"));
			}

			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public Prodi getDataProdi(String namaProdi) throws SQLException {
		final String sql
			= "SELECT * FROM tprodi where nama_prodi = ?";

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);

			query.setString(1, namaProdi);

			ResultSet result = query.executeQuery();

			if (result.next()) {
				return new Prodi(result.getInt("id_prodi"), result.getString("nama_prodi"));
			}

			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	//----------------------------------------------------------------------
	public DefaultTableModel getModelTabel_Karyawan() throws SQLException {
		final String sql
			= "SELECT "
			+ "a.id_karyawan, a.nama_karyawan, a.id_prodi, b.nama_prodi, "
			+ "a.id_jabatan, c.nama_jabatan, "
			+ "a.id_shift, e.shift_start, e.shift_end, e.deskripsi,"
			+ "a.email, a.no_telp, a.alamat "
			+ "FROM tkaryawan a "
			+ "LEFT JOIN tprodi b ON a.id_prodi = b.id_prodi "
			+ "LEFT JOIN tjabatan c ON a.id_jabatan = c.id_jabatan "
			+ "LEFT JOIN tshift e ON a.id_shift = e.id_shift";

		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("ID");
		model.addColumn("NAMA");
		model.addColumn("PRODI");
		model.addColumn("SHIFT");
		model.addColumn("JABATAN");
		model.addColumn("ALAMAT");
		model.addColumn("NO TELP");
		model.addColumn("EMAIL");

		try (PreparedStatement query = this.getConnection().prepareStatement(sql)) {
			ResultSet result = query.executeQuery();

			while (result.next()) {
				Prodi prodi = null;
				int idProdi = result.getInt("id_prodi");
				if (!result.wasNull() && result.getString("nama_prodi") != null) {
					prodi = new Prodi(idProdi, result.getString("nama_prodi"));
				}

				Jabatan jabatan = null;
				int idJabatan = result.getInt("id_jabatan");
				if (!result.wasNull() && result.getString("nama_jabatan") != null) {
					jabatan = new Jabatan(idJabatan, result.getString("nama_jabatan"));
				}

				Shift shift = null;
				int idShift = result.getInt("id_shift");
				if (!result.wasNull() && result.getTime("shift_start") != null) {
					shift = new Shift();
					shift.idShift = idShift;
					shift.shift_start = result.getTime("shift_start");
					shift.shift_end = result.getTime("shift_end");
					shift.deskripsi = result.getString("deskripsi");

				}

				Object[] row = {
					result.getString("id_karyawan"),
					result.getString("nama_karyawan"),
					prodi,
					shift,
					jabatan,
					result.getString("alamat"),
					result.getString("no_telp"),
					result.getString("email")
				};

				model.addRow(row);
			}

			return model;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public DefaultTableModel getModelTabel_Jabatan() throws SQLException {
		final String sql = "SELECT * FROM tjabatan;";
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("JABATAN");
		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);

			ResultSet result = query.executeQuery();
			while (result.next()) {
				Object[] row = {
					result.getString("id_jabatan"),
					result.getString("nama_jabatan")
				};
				model.addRow(row);
			}

			return model;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public DefaultTableModel getModelTabel_Prodi() throws SQLException {
		final String sql = "SELECT * FROM tprodi;";
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("PRODI");
		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);

			ResultSet result = query.executeQuery();
			while (result.next()) {
				Object[] row = {
					result.getString("id_prodi"),
					result.getString("nama_prodi")
				};
				model.addRow(row);
			}

			return model;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	//----------------------------------------------------------------------
	public void simpanDataKaryawan(final Karyawan karyawan) throws SQLException {
		if (this.isDataKaryawanAda(karyawan.idKaryawan)) {
			if (Pesan.tampilkanKonfirmasi("Konfirmasi Update Data", "Apakah anda yakin ingin mengupdate data?")) {
				updateDataKaryawan(karyawan);
			}
		} else {
			if (Pesan.tampilkanKonfirmasi("Konfirmasi Insert Data", "Apakah anda yakin ingin menambah data baru?")) {
				insertDataKaryawan(karyawan);
			}
		}
	}

	public void insertDataKaryawan(final Karyawan karyawan) throws SQLException {
		final String sql = "INSERT INTO tkaryawan values(?,?,?,?,?,?,?,?);";

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);

			query.setString(1, karyawan.idKaryawan);
			query.setString(2, karyawan.namaKaryawan);
			query.setInt(3, karyawan.idProdi);
			query.setInt(4, karyawan.idShift);
			query.setInt(5, karyawan.idJabatan);
			query.setString(6, karyawan.alamat);
			query.setString(7, karyawan.noTelepon);
			query.setString(8, karyawan.email);

			int affected = query.executeUpdate();

			if (affected <= 0) {
				throw new SQLException("Gagal menambahkan data karyawan baru");
			}

			System.out.println("MENAMBAHKAN DATA KARYAWAN BARU: " + affected);

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void updateDataKaryawan(final Karyawan karyawan) throws SQLException {
		final String sql = "UPDATE tkaryawan SET nama_karyawan = ?,"
			+ " id_prodi = ?, id_shift = ?, id_jabatan = ?,"
			+ " alamat = ?, no_telp = ?, email = ? WHERE id_karyawan = ?";

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);

			query.setString(1, karyawan.namaKaryawan);
			query.setInt(2, karyawan.idProdi);
			query.setInt(3, karyawan.idShift);
			query.setInt(4, karyawan.idJabatan);
			query.setString(5, karyawan.alamat);
			query.setString(6, karyawan.noTelepon);
			query.setString(7, karyawan.email);
			query.setString(8, karyawan.idKaryawan);

			int affected = query.executeUpdate();

			if (affected <= 0) {
				throw new SQLException("Gagal mengupdate data karyawan");
			}

			System.out.println("MENGUPDATE DATA KARYAWAN: " + affected);

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void simpanDataJabatan(final Jabatan jabatan) throws SQLException {
		if (jabatan.idJabatan != null) {
			if (Pesan.tampilkanKonfirmasi("Konfirmasi Update Data", "Apakah anda yakin ingin mengupdate data?")) {
				updateDataJabatan(jabatan);
			}
		} else {
			if (Pesan.tampilkanKonfirmasi("Konfirmasi Insert Data", "Apakah anda yakin ingin menambah data baru?")) {
				insertDataJabatan(jabatan.namaJabatan);
			}
		}
	}

	public void updateDataJabatan(final Jabatan jabatan) throws SQLException {
		final String sql = "UPDATE tjabatan SET nama_jabatan = ? WHERE id_jabatan = ?";
		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);
			query.setString(1, jabatan.namaJabatan);
			query.setInt(2, jabatan.idJabatan);
			int affected = query.executeUpdate();

			if (affected <= 0) {
				throw new SQLException("Gagal mengupdate data jabatan");
			}

			System.out.println("MENGUPDATE DATA JABATAN: " + affected);

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void insertDataJabatan(String namaJabatan) throws SQLException {
		final String sql = "INSERT INTO tjabatan (nama_jabatan) VALUES (?)";
		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);
			query.setString(1, namaJabatan);
			int affected = query.executeUpdate();

			if (affected <= 0) {
				throw new SQLException("Gagal menginsert data jabatan");
			}

			System.out.println("MENGINSERT DATA JABATAN: " + affected);

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void simpanDataProdi(final Prodi prodi) throws SQLException {
		if (prodi.id != null) {
			if (Pesan.tampilkanKonfirmasi("Konfirmasi Update Data", "Apakah anda yakin ingin mengupdate data?")) {
				updateDataProdi(prodi);
			}
		} else {
			if (Pesan.tampilkanKonfirmasi("Konfirmasi Insert Data", "Apakah anda yakin ingin menambah data baru?")) {
				insertDataProdi(prodi.namaProdi);
			}
		}
	}

	public void updateDataProdi(final Prodi prodi) throws SQLException {
		final String sql = "UPDATE tprodi SET nama_prodi = ? WHERE id_prodi = ?";
		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);
			query.setString(1, prodi.namaProdi);
			query.setInt(2, prodi.id);
			int affected = query.executeUpdate();

			if (affected <= 0) {
				throw new SQLException("Gagal mengupdate data PRODI");
			}

			System.out.println("MENGUPDATE DATA PRODI: " + affected);

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void insertDataProdi(String namaProdi) throws SQLException {
		final String sql = "INSERT INTO tprodi (nama_prodi) VALUES (?)";
		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);
			query.setString(1, namaProdi);
			int affected = query.executeUpdate();

			if (affected <= 0) {
				throw new SQLException("Gagal menginsert data PRODI");
			}

			System.out.println("MENGINSERT DATA PRODI: " + affected);

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void updateDataRecordAbsen(final RecordAbsen record) {

	}

	public void updateDataShift(final Shift shift) {

	}

	//----------------------------------------------------------------------
	public void deleteDataKaryawan(String idKaryawan) throws SQLException {
		final String sql = "DELETE FROM tkaryawan WHERE id_karyawan = ?";

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);
			query.setString(1, idKaryawan);

			int affected = query.executeUpdate();

			if (affected <= 0) {
				throw new SQLException("Gagal menghapus data karyawan dengan ID: " + idKaryawan);
			}

			System.out.println("MENGHAPUS DATA KARYAWAN: " + affected + " baris terhapus");

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deleteDataJabatan(int idJabatan) throws SQLException {
		final String sql = "DELETE FROM tjabatan WHERE id_jabatan = ?";

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);
			query.setInt(1, idJabatan);
			int affected = query.executeUpdate();

			if (affected <= 0) {
				throw new SQLException("Gagal menghapus data JABATAN dengan ID: " + idJabatan);
			}

			System.out.println("MENGHAPUS DATA JABATAN: " + affected + " baris terhapus");
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deleteDataProdi(int idProdi) throws SQLException {
		final String sql = "DELETE FROM tprodi WHERE id_prodi = ?";

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);
			query.setInt(1, idProdi);
			int affected = query.executeUpdate();

			if (affected <= 0) {
				throw new SQLException("Gagal menghapus data PRODI dengan ID: " + idProdi);
			}

			System.out.println("MENGHAPUS DATA PRODI: " + affected + " baris terhapus");
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deleteDataRecordAbsen(int idRecord) {

	}

	public void deleteDataCatatanRecord(int idDetailRecord) {

	}

	public void deleteDataShift(int idShift) {

	}

}
