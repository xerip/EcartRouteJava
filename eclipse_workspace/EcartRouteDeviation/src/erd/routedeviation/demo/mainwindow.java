package erd.routedeviation.demo;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

@SuppressWarnings({ "serial", "unused" })
public class mainwindow extends JFrame {
	public JTable table;
	public JTextField txtAnswer;
	
	public mainwindow()
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(2, 2, 0, 0));
		
		String[] columns = new String[] {"Name", "Lat", "Lon"};
		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		getContentPane().add(table);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(1, 2, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("The next waypoint is ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_1);
		
		txtAnswer = new JTextField();
		txtAnswer.setText("answer");
		panel_2.add(txtAnswer);
		txtAnswer.setColumns(10);
		
		setVisible(true);
	}
}
