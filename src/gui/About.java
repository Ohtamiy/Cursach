package gui;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;

import javax.swing.JTextArea;
import java.awt.Toolkit;

public class About extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					About frame = new About();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public About() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(About.class.getResource("/pictures/logo.png")));
		setFont(new Font("Segoe Print", Font.PLAIN, 14));
		setTitle("Info about creators");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 802, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//JLabel lblNewLabel = new JLabel("The project of multithread supermarket was created by students of group PI-191. Special thanks to:");
		JLabel lblNewLabel = new JLabel("Проєкт мультипоточного Супермаркету було створено студентами групи ПІ-191. Особлива подяка:");
		lblNewLabel.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 0, 776, 26);
		contentPane.add(lblNewLabel);
		
		JLabel labelArtemiy = new JLabel("");
		labelArtemiy.setBounds(10, 30, 221, 269);
		contentPane.add(labelArtemiy);
		
		JLabel labelSvetlana = new JLabel("");
		labelSvetlana.setBounds(264, 30, 209, 269);
		contentPane.add(labelSvetlana);
		
		JLabel labelRodya = new JLabel("");
		labelRodya.setBounds(503, 30, 273, 269);
		contentPane.add(labelRodya);
		
		Image imageArtemiy, imageSvetlana, imageRodya;
		try {
			imageArtemiy = ImageIO.read(MainGui.class.getResource("/pictures/Artemiy.jpg"))
					.getScaledInstance(labelArtemiy.getWidth(), labelArtemiy.getHeight(), 
							Image.SCALE_SMOOTH);
			imageSvetlana = ImageIO.read(MainGui.class.getResource("/pictures/Svetlana.jpg"))
					.getScaledInstance(labelSvetlana.getWidth(), labelSvetlana.getHeight(), 
							Image.SCALE_SMOOTH);
			imageRodya = ImageIO.read(MainGui.class.getResource("/pictures/Rodya.jpg"))
					.getScaledInstance(labelRodya.getWidth(), labelRodya.getHeight(), 
							Image.SCALE_SMOOTH);
			labelArtemiy.setIcon(new ImageIcon(imageArtemiy));
			labelSvetlana.setIcon(new ImageIcon(imageSvetlana));
			labelRodya.setIcon(new ImageIcon(imageRodya));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JTextArea textArtemiy = new JTextArea("1) Программіст - Красенко Артем Олександрович, \"Зроблю курсовий проєкт за великі гроші\"\n"
											+ "2) Дизайнер - Коноваленко Світлана Олександрівна, \"Іконка головного вікна - склянка\"\n"
											+ "3) Паперовий майстер - Потапенко Родіон Володимирович, \"Запрезентую будь-якого інвестора\"\n");
		textArtemiy.setFont(new Font("Segoe Print", Font.PLAIN, 13));
		textArtemiy.setBounds(10, 310, 766, 76);
		contentPane.add(textArtemiy);
	}
}
