import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class mainwindow extends JFrame {
	private JTable table_route;

	public mainwindow() {
		
		setSize(800, 500);
		setTitle("Hello");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		table_route = new JTable();
		
		table_route.setTableHeader(null);
		
		table_route.getColumnModel().getColumn(0).setPreferredWidth(50);
		table_route.getColumnModel().getColumn(0).setMinWidth(20);
		table_route.getColumnModel().getColumn(1).setPreferredWidth(50);
		table_route.getColumnModel().getColumn(1).setMinWidth(20);
		table_route.getColumnModel().getColumn(2).setPreferredWidth(50);
		table_route.getColumnModel().getColumn(2).setMinWidth(20);
		table_route.getColumnModel().getColumn(3).setPreferredWidth(50);
		table_route.getColumnModel().getColumn(3).setMinWidth(20);
		table_route.getColumnModel().getColumn(4).setPreferredWidth(50);
		table_route.getColumnModel().getColumn(4).setMinWidth(20);
		table_route.setBorder(new LineBorder(Color.BLUE, 2, true));
		table_route.setBounds(25, 25, 300, 200);
		getContentPane().add(table_route);
		
		setVisible(true);
	}
}
