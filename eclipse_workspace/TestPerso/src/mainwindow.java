import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JSplitPane;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.WindowStateListener;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class mainwindow extends JFrame {

	private final JButton buttonYes = new JButton("yes");
	private final JTextArea Explications = new JTextArea();
	private final JScrollPane scrollPane = new JScrollPane();
	private final Component verticalStrut = Box.createVerticalStrut(20);
	private final JPanel Simpelpanel = new JPanel();
	
	public mainwindow() {
		addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent e) {
				Explications.setText("window state changed!");
			}
		});
		setFixedSize(300, 300);
		setTitle("Hello");
		setBackground(Color.BLUE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		getContentPane().setLayout(new GridLayout(0, 2, 5, 0));
		
		getContentPane().add(Simpelpanel);
		Simpelpanel.setLayout(null);
		
		JLabel label = new JLabel("New label");
		label.setBounds(0, 0, 46, 14);
		Simpelpanel.add(label);
		
		getContentPane().add(buttonYes);
		
		getContentPane().add(scrollPane);
		
		Explications.setText("...");
		scrollPane.setViewportView(Explications);
		
		scrollPane.setColumnHeaderView(verticalStrut);
		
		buttonYes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Explications.setText("Hello!");
			}
		});
		
		pack();
		setVisible(true);
	}

	private void setFixedSize(int i, int j) {
		
	}
}
