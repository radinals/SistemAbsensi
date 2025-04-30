/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabsensi.user.ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import sistemabsensi.user.data.DBAbsensi;
import sistemabsensi.user.data.Karyawan;

/**
 *
 * @author rss
 */
// Aplikasi disusun dengan menggunakan satu jframe yang
// memiliki jpanel yang banyak.
// Hierarki Tampilan,
// JFrameAbsensi (JFrame)
//   |---- panelUtama (JPanel) <-- Menggunakan CardLayout
//             |--- JPanelLogin (JPanel)
//             |--- JPanelAbsen (JPanel)
public class JFrameAbsensi extends JFrame {

	private JPanel panelUtama; // Panel utama dari frame ini
	private CardLayout layoutPanelUtama;
	private DBAbsensi dbAbsensi;
	private Karyawan karyawan;

	private JPanelLogin panelLogin;
	private JPanelAbsen panelAbsen;

	public JFrameAbsensi() {
		this.dbAbsensi = new DBAbsensi();
		initFrame();
		initJPanelUtama();
		bukaPanelLogin();
	}

	private void initFrame() {
		this.setMinimumSize(new Dimension(800, 600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout());

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(karyawan != null) {
					dbAbsensi.tandaiKaryawanTerLogout(karyawan.getIdKaryawan());
				}
			}
		});

	}

	private void initJPanelUtama() {

		// Agar dapat menyimpan banayk JPanel dalam satu
		// Jpanel, digunakan CardLayout. Agar JPanel yang
		// terkandung seperti tumpukan kartu.
		this.layoutPanelUtama = new CardLayout();
		this.panelUtama = new JPanel(this.layoutPanelUtama);

		this.panelLogin = new JPanelLogin(this);
		this.panelAbsen = new JPanelAbsen(this);

		this.panelUtama.add("LOGIN", panelLogin);
		this.panelUtama.add("ABSEN", panelAbsen);

		this.add(this.panelUtama);
	}

	public Karyawan getKaryawan() {
		return karyawan;
	}

	public void setKaryawan(Karyawan karyawan) {
		this.karyawan = karyawan;
	}

	public DBAbsensi getDB() {
		return this.dbAbsensi;
	}

	public void bukaPanelAbsen() {
		this.layoutPanelUtama.show(panelUtama, "ABSEN");
		this.panelAbsen.dapatkanData();
	}

	public void bukaPanelLogin() {
		this.layoutPanelUtama.show(panelUtama, "LOGIN");
		this.panelLogin.resetInputBox();
	}

}
