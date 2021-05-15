package clasess;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import java.awt.Toolkit;

// главное окно

public class MainGui {

	private JFrame frmSupermarket;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainGui window = new MainGui();
					window.frmSupermarket.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSupermarket = new JFrame();
		frmSupermarket.setIconImage(Toolkit.getDefaultToolkit().getImage("G:\\Projects\\Cursach\\\u0430\u0442\u0431.png"));
		frmSupermarket.setTitle("Supermarket");
		frmSupermarket.setBounds(100, 100, 450, 300);
		frmSupermarket.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSupermarket.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		frmSupermarket.setJMenuBar(menuBar);
		
		JMenu menuSettings = new JMenu("Settings");
		menuBar.add(menuSettings);
		
		JMenuItem menuSetDefault = new JMenuItem("Set default settings");
		menuSettings.add(menuSetDefault);
		
		JMenuItem menuAbout = new JMenuItem("About");
		menuSettings.add(menuAbout);
	}

}
