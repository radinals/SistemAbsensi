/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabsensi.user;

import sistemabsensi.user.ui.JFrameAbsensi;

/**
 *
 * @author rss
 */
public class SistemAbsenUser {
	private JFrameAbsensi jframe;
	
	public SistemAbsenUser() {
		this.jframe = new JFrameAbsensi();
		this.jframe.setVisible(true);
	}
	
	public static void main(String[] args) {
		new SistemAbsenUser();
	}
		
	
}
