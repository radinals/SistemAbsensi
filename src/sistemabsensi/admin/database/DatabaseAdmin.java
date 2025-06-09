/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabsensi.admin.database;

import sistemabsensi.database.Shift;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import sistemabsensi.admin.ui.DialogPesan;
import sistemabsensi.database.DatabaseAbsensi;
import sistemabsensi.database.RecordAbsen;
import sistemabsensi.database.StatusAbsen;
import sistemabsensi.database.TipeAbsen;

/**
 *
 * @author rss
 */
public class DatabaseAdmin extends DatabaseAbsensi{

	public LinkedList<Jabatan> getDaftarDataJabatan() {
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
			System.exit(-1);
		}

		return null;
	}

	public LinkedList<Karyawan> getDaftarDataKaryawan() {
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
			System.exit(-1);
		}

		return null;
	}

	public LinkedList<Prodi> getDaftarDataProdi() {
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
			System.exit(-1);
		}

		return null;
	}

	public LinkedList<Shift> getDaftarDataShift() {
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
			System.exit(-1);
		}

		return null;
	}
	//----------------------------------------------------------------------

	public String getNamaKaryawan(String idKaryawan) {
		final String sql = "SELECT nama_karyawan from tkaryawan WHERE id_karyawan = ?";

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);

			query.setString(1, idKaryawan);

			ResultSet result = query.executeQuery();

			if (result.next()) {
				return result.getString("nama_karyawan");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		return null;
	}

	public Object[] getDataKaryawan(String idKaryawan) {
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

			if (!result.next()) {
				return null;
			}

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

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return null;
	}

	public Jabatan getDataJabatan(String namaJabatan) {
		final String sql
			= "SELECT * FROM tjabatan where nama_jabatan = ?";

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);

			query.setString(1, namaJabatan);

			ResultSet result = query.executeQuery();

			if (result.next()) {
				return new Jabatan(result.getInt("id_jabatan"), result.getString("nama_jabatan"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		return null;
	}

	public Prodi getDataProdi(String namaProdi){
		final String sql
			= "SELECT * FROM tprodi where nama_prodi = ?";

		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);

			query.setString(1, namaProdi);

			ResultSet result = query.executeQuery();

			if (result.next()) {
				return new Prodi(result.getInt("id_prodi"), result.getString("nama_prodi"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		return null;
	}

	//----------------------------------------------------------------------
	public DefaultTableModel getModelTabel_Karyawan() {
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
			System.exit(-1);
		}
		return null;
	}

	private String buatkanWherePencarian(String pencarian, List<KolomPencarian> kolomTarget) {
		String queryWhere = " WHERE ";

		int i = 1; // untuk menentukan kapan keyword 'OR' ditambahkan.

		for (KolomPencarian kolom : kolomTarget) {
			switch (kolom) {
				case ID:
					queryWhere += " a.id_karyawan like '" + pencarian + "'";
					break;
				case NAMA:
					queryWhere += " a.nama_karyawan like '" + pencarian + "'";
					break;
				case JABATAN:
					queryWhere += " c.nama_jabatan like '" + pencarian + "'";
					break;
				case PRODI:
					queryWhere += " b.nama_prodi like '" + pencarian + "'";
					break;
				case SHIFT:
					queryWhere += " e.deskripsi like '" + pencarian + "'";
					break;
				case EMAIL:
					queryWhere += " a.email like '" + pencarian + "'";
					break;
				case NO_TELP:
					queryWhere += " a.no_telp like '" + pencarian + "'";
					break;
				case ALAMAT:
					queryWhere += " a.alamat like '" + pencarian + "'";
					break;
				default:
					throw new AssertionError(kolom.name());
			}

			if (kolomTarget.size() <= 1) {
				break;
			}

			if (i < kolomTarget.size()) {
				queryWhere += " OR "; // tambahkan keyword "OR" setelah setiap 'like'.
				i++;
			}
		}

		return queryWhere;
	}

	public DefaultTableModel getModelTabel_PencarianKaryawan(String pencarian, List<KolomPencarian> kolomTarget) {
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

		sql += buatkanWherePencarian(pencarian, kolomTarget);

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

				model.addRow(
					new Object[]{
						result.getString("id_karyawan"),
						result.getString("nama_karyawan"),
						prodi,
						shift,
						jabatan,
						result.getString("alamat"),
						result.getString("no_telp"),
						result.getString("email")
					});
			}

			return model;

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		return null;
	}

	public StatusLogin loginAdminValid(String id, String password) {
		final String sql = "SELECT a.id_karyawan, b.nama_karyawan, a.password"
			+ " FROM tadmin a, tkaryawan b where a.id_karyawan = b.id_karyawan"
			+ " AND a.id_karyawan = ?;";
		try {
			PreparedStatement query = this.getConnection().prepareStatement(sql);
			query.setString(1, id);

			ResultSet result = query.executeQuery();
			if (result.next()) {
				String passwordAdmin = result.getString("password");

				if (passwordAdmin.equals(password)) {
					return StatusLogin.LOGIN_VALID;
				} else {
					return StatusLogin.PASSWORD_SALAH;
				}
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
			if (result.next()) {
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

	public DefaultTableModel getModelTabel_Jabatan() {
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
			System.exit(-1);
		}

		return null;
	}

	public DefaultTableModel getModelTabel_Prodi() {
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
			System.exit(-1);
		}

		return null;
	}

	public LinkedList<Date> getDaftarTanggalRecordAbsen() throws SQLException {
		LinkedList<Date> tanggalList = new LinkedList<>();
		String sql = "SELECT DISTINCT DATE(waktu_absen) AS tanggal FROM trecordabsen ORDER BY tanggal DESC";

		try {
			PreparedStatement stmt = this.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				tanggalList.add(rs.getDate("tanggal"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tanggalList;
	}

	public DefaultTableModel getModelTabel_RecordAbsen() {
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

		try {
			PreparedStatement stmt = this.getConnection().prepareStatement(sql);
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
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		return model;
	}

	//----------------------------------------------------------------------
	public void simpanDataKaryawan(final Karyawan karyawan) {
		if (this.isDataKaryawanAda(karyawan.idKaryawan)) {
			if (DialogPesan.tampilKonfirmasi("Konfirmasi Update Data", "Apakah anda yakin ingin mengupdate data?")) {
				updateDataKaryawan(karyawan);
			}
		} else {
			if (DialogPesan.tampilKonfirmasi("Konfirmasi Insert Data", "Apakah anda yakin ingin menambah data baru?")) {
				insertDataKaryawan(karyawan);
			}
		}
	}

	public void insertDataKaryawan(final Karyawan karyawan) {
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
			System.exit(-1);
		}
	}

	public void updateDataKaryawan(final Karyawan karyawan) {
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
			System.exit(-1);
		}
	}

	public void simpanDataJabatan(final Jabatan jabatan) {

		if (jabatan.idJabatan != null) {
			if (DialogPesan.tampilKonfirmasi("Konfirmasi Update Data",
				"Apakah anda yakin ingin mengupdate data?")) {
				updateDataJabatan(jabatan);
			}
		} else {
			if (DialogPesan.tampilKonfirmasi("Konfirmasi Insert Data",
				"Apakah anda yakin ingin menambah data baru?")) {
				insertDataJabatan(jabatan.namaJabatan);
			}
		}

	}

	public void updateDataJabatan(final Jabatan jabatan) {
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
			System.exit(-1);
		}
	}

	public void insertDataJabatan(String namaJabatan) {
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
			System.exit(-1);
		}
	}

	public void simpanDataProdi(final Prodi prodi) {
		if (prodi.id != null) {
			if (DialogPesan.tampilKonfirmasi("Konfirmasi Update Data", "Apakah anda yakin ingin mengupdate data?")) {
				updateDataProdi(prodi);
			}
		} else {
			if (DialogPesan.tampilKonfirmasi("Konfirmasi Insert Data", "Apakah anda yakin ingin menambah data baru?")) {
				insertDataProdi(prodi.namaProdi);
			}
		}
	}

	public void updateDataProdi(final Prodi prodi) {
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
			System.exit(-1);
		}
	}

	public void insertDataProdi(String namaProdi) {
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
			System.exit(-1);
		}
	}

	public void simpanDataRecordAbsen(RecordAbsen record) {
		if (record.id_recordabsen != null) {
			if (DialogPesan.tampilKonfirmasi("Konfirmasi Update Data",
				"Apakah anda yakin ingin mengupdate data?")) {
				updateDataRecordAbsen(record);
			}
		} else {
			if (DialogPesan.tampilKonfirmasi("Konfirmasi Insert Data",
				"Apakah anda yakin ingin menambah data baru?")) {
				insertDataRecordAbsen(record);
			}
		}
	}

	public void insertDataRecordAbsen(RecordAbsen record) {
		String sql = "INSERT INTO trecordabsen (id_recordabsen, waktu_absen, id_karyawan, status_absen, catatan_absen, tipe_absen) "
			+ "VALUES (default, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = this.getConnection().prepareStatement(sql)) {
			stmt.setTimestamp(1, record.waktu_absen);
			stmt.setString(2, record.id_karyawan);
			stmt.setString(3, record.status_absen.name());
			stmt.setString(4, record.catatan_absen);
			stmt.setString(5, record.tipe_absen.name());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	// --------------------- READ ---------------------
	public RecordAbsen getRecordAbsenById(int id) {
		String sql = "SELECT * FROM trecordabsen WHERE id_recordabsen = ?";
		try {
			PreparedStatement stmt = this.getConnection().prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new RecordAbsen(
					rs.getInt("id_recordabsen"),
					rs.getTimestamp("waktu_absen"),
					rs.getString("id_karyawan"),
					StatusAbsen.valueOf(rs.getString("status_absen")),
					rs.getString("catatan_absen"),
					TipeAbsen.valueOf(rs.getString("tipe_absen"))
				);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		return null;
	}

	// --------------------- UPDATE ---------------------
	public void updateDataRecordAbsen(final RecordAbsen record) {
		String sql = "UPDATE trecordabsen SET waktu_absen = ?, id_karyawan = ?, status_absen = ?, catatan_absen = ?, tipe_absen = ? "
			+ "WHERE id_recordabsen = ?";
		try (PreparedStatement stmt = this.getConnection().prepareStatement(sql)) {
			stmt.setTimestamp(1, record.waktu_absen);
			stmt.setString(2, record.id_karyawan);
			stmt.setString(3, record.status_absen.name());
			stmt.setString(4, record.catatan_absen);
			stmt.setString(5, record.tipe_absen.name());
			stmt.setInt(6, record.id_recordabsen);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	// --------------------- DELETE ---------------------
	public void deleteDataRecordAbsen(int idRecord) {
		String sql = "DELETE FROM trecordabsen WHERE id_recordabsen = ?";
		try (PreparedStatement stmt = this.getConnection().prepareStatement(sql)) {
			stmt.setInt(1, idRecord);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public void simpanDataShift(Shift shift) {
		if (shift.idShift != null) {
			if (DialogPesan.tampilKonfirmasi("Konfirmasi Update Data", "Apakah anda yakin ingin mengupdate data?")) {
				updateDataShift(shift);
			}
		} else {
			if (DialogPesan.tampilKonfirmasi("Konfirmasi Insert Data", "Apakah anda yakin ingin menambah data baru?")) {
				insertDataShift(shift);
			}
		}
	}

	public DefaultTableModel getModelDataTabel_Shift() {
		String sql = "SELECT * FROM tshift";

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("SHIFT MULAI");
		model.addColumn("SHIFT SELESAI");
		model.addColumn("DESKRIPSI");

		try {
			PreparedStatement stmt = this.getConnection().prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Object[] baris = {
					rs.getInt("id_shift"),
					rs.getTime("shift_start"),
					rs.getTime("shift_end"),
					rs.getString("deskripsi")
				};
				model.addRow(baris);
			}
			
			return model;

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		return null;
	}

	public Shift getDataShift(int id) {
		String sql = "SELECT * FROM tshift WHERE id_shift = ?";
		try {
			PreparedStatement stmt = this.getConnection().prepareStatement(sql);
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
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return null;
	}

	public void updateDataShift(final Shift shift) {
		String sql = "UPDATE tshift SET shift_start = ?, shift_end = ?, deskripsi = ? where id_shift = ?";
		try {
			PreparedStatement stmt = this.getConnection().prepareStatement(sql);
			stmt.setTime(1, shift.shift_start);
			stmt.setTime(2, shift.shift_end);
			stmt.setString(3, shift.deskripsi);
			stmt.setInt(4, shift.idShift);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public void insertDataShift(final Shift shift) {
		String sql = "INSERT INTO tshift(shift_start, shift_end, deskripsi) values (?,?,?)";
		try {
			PreparedStatement stmt = this.getConnection().prepareStatement(sql);
			stmt.setTime(1, shift.shift_start);
			stmt.setTime(2, shift.shift_end);
			stmt.setString(3, shift.deskripsi);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public void deleteDataShift(int idShift) {
		String sql = "DELETE FROM tshift WHERE id_shift = ?";
		try {
			PreparedStatement stmt = this.getConnection().prepareStatement(sql);
			stmt.setInt(1, idShift);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	//----------------------------------------------------------------------
	public void deleteDataKaryawan(String idKaryawan) {
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
			System.exit(-1);
		}
	}

	public void deleteDataJabatan(int idJabatan) {
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
			System.exit(-1);
		}
	}

	public void deleteDataProdi(int idProdi) {
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
			System.exit(-1);
		}
	}

}
