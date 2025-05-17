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
import java.util.List;
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

	private String buatkanKlausaWherePencarian(String pencarian, List<KolomPencarian> kolomTarget) {
		String sqlWhere = " WHERE ";

		int i = 1;

		for (KolomPencarian kolom : kolomTarget) {
			switch (kolom) {
				case ID:
					sqlWhere += " a.id_karyawan like '" + pencarian + "'";
					break;
				case NAMA:
					sqlWhere += " a.nama_karyawan like '" + pencarian + "'";
					break;
				case JABATAN:
					sqlWhere += " c.nama_jabatan like '" + pencarian + "'";
					break;
				case PRODI:
					sqlWhere += " b.nama_prodi like '" + pencarian + "'";
					break;
				case SHIFT:
					sqlWhere += " e.deskripsi like '" + pencarian + "'";
					break;
				case EMAIL:
					sqlWhere += " a.email like '" + pencarian + "'";
					break;
				case NO_TELP:
					sqlWhere += " a.no_telp like '" + pencarian + "'";
					break;
				case ALAMAT:
					sqlWhere += " a.alamat like '" + pencarian + "'";
					break;
				default:
					throw new AssertionError(kolom.name());
			}

			if (kolomTarget.size() <= 1) {
				continue;
			}

			if (i < kolomTarget.size()) {
				sqlWhere += " AND ";
			}
			i++;
		}

		return sqlWhere;
	}

	public DefaultTableModel getModelTabel_PencarianKaryawan(String pencarian, List<KolomPencarian> kolomTarget) throws SQLException {
		if (kolomTarget.isEmpty()) {
			return null;
		}

		String sql
			= "SELECT "
			+ "a.id_karyawan, a.nama_karyawan, a.id_prodi, b.nama_prodi, "
			+ "a.id_jabatan, c.nama_jabatan, "
			+ "a.id_shift, e.shift_start, e.shift_end, e.deskripsi,"
			+ "a.email, a.no_telp, a.alamat "
			+ "FROM tkaryawan a "
			+ "LEFT JOIN tprodi b ON a.id_prodi = b.id_prodi "
			+ "LEFT JOIN tjabatan c ON a.id_jabatan = c.id_jabatan "
			+ "LEFT JOIN tshift e ON a.id_shift = e.id_shift";

		sql += buatkanKlausaWherePencarian(pencarian, kolomTarget);

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
			System.err.println("QUERY: " + sql);
			e.printStackTrace();
			throw e;
		}
	}
	
	
	
	public StatusLogin loginAdminValid(String id, String password) {
		final String sql = "SELECT a.id_karyawan, b.nama_karyawan, a.password FROM tadmin a, tkaryawan b where a.id_karyawan = b.id_karyawan AND a.id_karyawan = ?;";
		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);
			query.setString(1, id);

			ResultSet result = query.executeQuery();
			if (result.next()) {
				String passwordAdmin = result.getString("password");
				
				if (passwordAdmin.equals(password))
					return StatusLogin.LOGIN_VALID;
				else
					return StatusLogin.PASSWORD_SALAH;
			} else {
				return StatusLogin.ID_TIDAK_TERDAFTAR;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		return null;
	}
	
	public boolean isKaryawanAdmin(String idKaryawan) {
		final String sql = "SELECT 1 FROM tadmin where id_karyawan = ?;";
		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);
			query.setString(1, idKaryawan);

			ResultSet result = query.executeQuery();
			return result.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return false;
	}
	
	public String getPasswordKaryawanAdmin(String idKaryawan) {
		final String sql = "SELECT password FROM tadmin where id_karyawan = ?;";
		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);
			query.setString(1, idKaryawan);

			ResultSet result = query.executeQuery();
			if(result.next()) {
				return result.getString("password");
			}
			
			return null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return null;
	}
	
	public void tambahKaryawanAdmin(String idKaryawan, String password) {
		final String sql = "INSERT INTO tadmin values(?,?);";

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);

			query.setString(1, idKaryawan);
			query.setString(2, password);

			int affected = query.executeUpdate();

			if (affected <= 0) {
				throw new SQLException("Gagal menambahkan data karyawan baru");
			}

			System.out.println("MENAMBAHKAN ADMIN BARU: " + affected);

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public void hapusKaryawanAdmin(String idKaryawan) {
		final String sql = "DELETE FROM tadmin WHERE id_karyawan = ?;";

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);

			query.setString(1, idKaryawan);

			int affected = query.executeUpdate();

			if (affected <= 0) {
				throw new SQLException("Gagal Menghapus data karyawan baru");
			}

			System.out.println("MENGHAPUS ADMIN: " + affected);

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
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
	
	public LinkedList<Date> getDaftarTanggalRecordAbsen() throws SQLException {
	LinkedList<Date> tanggalList = new LinkedList<>();
	String sql = "SELECT DISTINCT DATE(waktu_absen) AS tanggal FROM trecordabsen ORDER BY tanggal DESC";

	try (PreparedStatement stmt = this.getConnection().prepareStatement(sql);
		 ResultSet rs = stmt.executeQuery()) {
		while (rs.next()) {
			tanggalList.add(rs.getDate("tanggal"));
		}
	}
	return tanggalList;
}

	public DefaultTableModel getModelTabel_RecordAbsen() throws SQLException {
		String sql
			= "SELECT "
			+ "b.id_karyawan, b.nama_karyawan, c.id_shift, c.deskripsi, c.shift_start, c.shift_end,"
			+ "a.id_recordabsen, a.waktu_absen, a.status_absen, a.catatan_absen,a.tipe_absen "
			+ "FROM trecordabsen a "
			+ "LEFT JOIN tkaryawan b ON a.id_karyawan = b.id_karyawan "
			+ "LEFT JOIN tshift c ON b.id_shift = c.id_shift";

		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("ID RECORD");
		model.addColumn("ID KARYAWAN");
		model.addColumn("NAMA KARYAWAN");
		model.addColumn("WAKTU ABSEN");
		model.addColumn("TIPE ABSEN");
		model.addColumn("CATATAN ABSEN");

		try (PreparedStatement stmt = this.database.getConnection().prepareStatement(sql)) {
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					RecordAbsen record = new RecordAbsen();

					Object[] baris = {
						rs.getInt("id_recordabsen"),
						rs.getString("id_karyawan"),
						rs.getString("nama_karyawan"),
						rs.getString("status_absen"),
						rs.getTimestamp("waktu_absen"),
						rs.getString("tipe_absen"),
						rs.getString("catatan_absen")
					};

					model.addRow(baris);
				}

			}
		}

		return model;
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
	
	public void simpanDataRecordAbsen(RecordAbsen record) throws SQLException {
		if (record.id_recordabsen != null) {
			if (Pesan.tampilkanKonfirmasi("Konfirmasi Update Data", "Apakah anda yakin ingin mengupdate data?")) {
				updateDataRecordAbsen(record);
			}
		} else {
			if (Pesan.tampilkanKonfirmasi("Konfirmasi Insert Data", "Apakah anda yakin ingin menambah data baru?")) {
				insertDataRecordAbsen(record);
			}
		}
	}

	public void insertDataRecordAbsen(RecordAbsen record) throws SQLException {
		String sql = "INSERT INTO trecordabsen (id_recordabsen, waktu_absen, id_karyawan, status_absen, catatan_absen, tipe_absen) "
			+ "VALUES (default, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = this.database.getConnection().prepareStatement(sql)) {
			stmt.setTimestamp(1, record.waktu_absen);
			stmt.setString(2, record.id_karyawan);
			stmt.setString(3, record.status_absen.name());
			stmt.setString(4, record.catatan_absen);
			stmt.setString(5, record.tipe_absen.name());
			stmt.executeUpdate();
		}
	}

	// --------------------- READ ---------------------
	public RecordAbsen getRecordAbsenById(int id) throws SQLException {
		String sql = "SELECT * FROM trecordabsen WHERE id_recordabsen = ?";
		try (PreparedStatement stmt = this.database.getConnection().prepareStatement(sql)) {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new RecordAbsen(
						rs.getInt("id_recordabsen"),
						rs.getTimestamp("waktu_absen"),
						rs.getString("id_karyawan"),
						RecordAbsen.StatusAbsen.valueOf(rs.getString("status_absen")),
						rs.getString("catatan_absen"),
						RecordAbsen.TipeAbsen.valueOf(rs.getString("tipe_absen"))
					);
				}
			}
		}
		return null;
	}

	// --------------------- UPDATE ---------------------
	public void updateDataRecordAbsen(final RecordAbsen record) throws SQLException {
		String sql = "UPDATE trecordabsen SET waktu_absen = ?, id_karyawan = ?, status_absen = ?, catatan_absen = ?, tipe_absen = ? "
			+ "WHERE id_recordabsen = ?";
		try (PreparedStatement stmt = this.database.getConnection().prepareStatement(sql)) {
			stmt.setTimestamp(1, record.waktu_absen);
			stmt.setString(2, record.id_karyawan);
			stmt.setString(3, record.status_absen.name());
			stmt.setString(4, record.catatan_absen);
			stmt.setString(5, record.tipe_absen.name());
			stmt.setInt(6, record.id_recordabsen);
			stmt.executeUpdate();
		}
	}

	// --------------------- DELETE ---------------------
	public void deleteDataRecordAbsen(int idRecord) throws SQLException, SQLException, SQLException, SQLException {
		String sql = "DELETE FROM trecordabsen WHERE id_recordabsen = ?";
		try (PreparedStatement stmt = this.database.getConnection().prepareStatement(sql)) {
			stmt.setInt(1, idRecord);
			stmt.executeUpdate();
		}
	}
	
	public void simpanDataShift(Shift shift) throws SQLException {
		if (shift.idShift != null) {
			if (Pesan.tampilkanKonfirmasi("Konfirmasi Update Data", "Apakah anda yakin ingin mengupdate data?")) {
				updateDataShift(shift);
			}
		} else {
			if (Pesan.tampilkanKonfirmasi("Konfirmasi Insert Data", "Apakah anda yakin ingin menambah data baru?")) {
				insertDataShift(shift);
			}
		}
	}
	
	public DefaultTableModel getModelDataTabel_Shift() throws SQLException {
		String sql = "SELECT * FROM tshift";
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("SHIFT MULAI");
		model.addColumn("SHIFT SELESAI");
		model.addColumn("DESKRIPSI");
		try (PreparedStatement stmt = this.database.getConnection().prepareStatement(sql)) {
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Object[] baris =  {
						rs.getInt("id_shift"),
						rs.getTime("shift_start"),
						rs.getTime("shift_end"),
						rs.getString("deskripsi")
					};
					
					model.addRow(baris);
				}
			}
		}
		return model;
	}
	
	public Shift getDataShift(int id) throws SQLException {
		String sql = "SELECT * FROM tshift WHERE id_shift = ?";
		try (PreparedStatement stmt = this.database.getConnection().prepareStatement(sql)) {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new Shift(
						rs.getInt("id_shift"),
						rs.getTime("shift_start"),
						rs.getTime("shift_end"),
						rs.getString("deskripsi")
					);
				}
			}
		}
		return null;
	}

	public void updateDataShift(final Shift shift) throws SQLException {
		String sql = "UPDATE tshift SET shift_start = ?, shift_end = ?, deskripsi = ? where id_shift = ?";
		try (PreparedStatement stmt = this.database.getConnection().prepareStatement(sql)) {
			stmt.setTime(1, shift.shift_start);
			stmt.setTime(2, shift.shift_end);
			stmt.setString(3, shift.deskripsi);
			stmt.setInt(4, shift.idShift);
			stmt.executeUpdate();
		}
	}
	
	public void insertDataShift(final Shift shift) throws SQLException {
		String sql = "INSERT INTO tshift(shift_start, shift_end, deskripsi) values (?,?,?";
		try (PreparedStatement stmt = this.database.getConnection().prepareStatement(sql)) {
			stmt.setTime(1, shift.shift_start);
			stmt.setTime(2, shift.shift_end);
			stmt.setString(3, shift.deskripsi);
			stmt.executeUpdate();
		}
	}
	
	public void deleteDataShift(int idShift) throws SQLException {
		String sql = "DELETE FROM tshift WHERE id_shift = ?";
		try (PreparedStatement stmt = this.database.getConnection().prepareStatement(sql)) {
			stmt.setInt(1, idShift);
			stmt.executeUpdate();
		}
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

}
