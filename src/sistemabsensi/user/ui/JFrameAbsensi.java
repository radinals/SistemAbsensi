/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabsensi.user.ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import sistemabsensi.user.data.DBAbsensi;
import sistemabsensi.user.data.Karyawan;

/**
 *
 * @author rss
 */
public class JFrameAbsensi extends JFrame {
	private JPanel panelUtama;
	private CardLayout layoutPanelUtama;
	private DBAbsensi dbAbsensi;
	private Karyawan karyawan;
	
	private JPanelLogin panelLogin;
	private JPanelAbsen panelAbsen;

	public JFrameAbsensi() {
		this.dbAbsensi = new DBAbsensi();
		initFrame();
	}
	
	private void initFrame() {
		this.setMinimumSize(new Dimension(800,600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout());
		
		this.layoutPanelUtama = new CardLayout();
		this.panelUtama = new JPanel(this.layoutPanelUtama);
		
		panelLogin = new JPanelLogin(this);
		panelAbsen = new JPanelAbsen(this);
		
		this.panelUtama.add("LOGIN", panelLogin);
		this.panelUtama.add("ABSEN", panelAbsen);

		
		this.add(this.panelUtama);
		
		bukaPanelLogin();
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
