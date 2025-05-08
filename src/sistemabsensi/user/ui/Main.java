/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabsensi.user.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author rss
 */
public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800,600);
		JPanel panel = new JPanelAbsen();
		panel.setVisible(true);
		frame.add(panel);
		frame.setVisible(true);
	}
}
