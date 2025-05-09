/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabsensi.admin.ui;

import javax.swing.JOptionPane;

/**
 *
 * @author rss
 */
public class Pesan {

	public static boolean tampilkanKonfirmasi(String judul, String pesan) {
		int result = JOptionPane.showConfirmDialog(
			null,
			pesan,
			judul,
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE
		);
		return result == JOptionPane.YES_OPTION;
	}

	public static void tampilkanPeringatan(String pesan) {
		JOptionPane.showMessageDialog(null, pesan);
	}
}
