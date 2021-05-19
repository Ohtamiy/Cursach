package gui;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import additional.Sound;
import classes.Counter;
import classes.Creator;
import classes.LabelsToGo;
import classes.Queue;
import service.Armor;
import service.Cashbox;
import service.Potion;
import service.Weapon;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class MainGui extends JFrame {

	public JPanel contentPane;
	
	public JTextField text_fieldQueueCashbox;
	public JTextField text_fieldServed;
	public JTextField text_fieldLost;
	public JTextField text_fieldqueueArmor;
	public JTextField text_fieldqueueWeapon;
	public JTextField text_fieldqueuePotion;
	public JTextField text_fieldServedPotion;
	public JTextField text_fieldServedWeapon;
	public JTextField text_fieldServedArmor;
	
	public boolean end = true;
	
	public JSlider sliderArrival;
	public JSlider sliderPotionChoice;
	public JSlider sliderWeaponChoice;
	public JSlider sliderArmorChoice;
	public JSlider sliderVolume;
	
	public JButton startBtn;
	public JButton endBtn;
		
	public JLabel labelRoute;
	public JLabel labelCashbox;
	public JLabel labelPotion;
	public JLabel labelWeapon;
	public JLabel labelArmor;
	public JLabel labelEnter;
	public JLabel labelExit;
	
	public Counter cqueueArmor;
	public Counter cqueueWeapon;
	public Counter cqueuePotion;
	public Counter cQueueCashbox;
	
	public Counter cArmorServed;
	public Counter cWeaponServed;
	public Counter cPotionServed;
	public Counter cCashboxServed;
	
	public Counter cLost;
	
	public Queue queueArmor;
	public Queue queueWeapon;
	public Queue queuePotion;
	public Queue queueCashbox;
	
	public LabelsToGo lLabelEnter;
	public LabelsToGo lLabelExit;
	public LabelsToGo lLabelRoute;
	
	public Thread threadCreator;
	public Thread threadPotion;
	public Thread threadWeapon;
	public Thread threadArmor;
	public Thread threadCashbox;
	public Thread threadMusic;
	
	public Sound soundtrack;
	
	public int maxQueueSize;
	
	public Image img;
	
	private JLabel lblNewLabel_2_1_2_1_1_3;
	private JLabel lblNewLabel_2_1_2_1_1;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_1_2_1_2;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_1_2_1;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_1_2;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainGui frame = new MainGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public MainGui() throws IOException {
		maxQueueSize = 3;
		img = ImageIO.read(MainGui.class.getResource("/pictures/background.png"));
		setFont(new Font("Segoe Print", Font.PLAIN, 14));
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainGui.class.getResource("/pictures/viking.png")));
		setTitle("Supermarket");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 789, 735);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.BLACK);
		menuBar.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		setJMenuBar(menuBar);
		
		JMenu menuSettings = new JMenu("Settings");
		menuSettings.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		menuBar.add(menuSettings);
		
		JMenuItem menuAbout = new JMenuItem("About");
		menuAbout.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		menuSettings.add(menuAbout);
		contentPane = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				Image scaleImg = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
				g2d.drawImage(scaleImg, 0, 0, this);
			}
		};
		this.setResizable(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		labelCashbox = new JLabel("");
		labelCashbox.setBounds(143, 162, 89, 89);
		contentPane.add(labelCashbox);
		Image imageCashbox = ImageIO.read(MainGui.class.getResource("/pictures/cashbox.png"))
				.getScaledInstance(labelCashbox.getWidth(), labelCashbox.getHeight(), 
						Image.SCALE_SMOOTH);
		labelCashbox.setIcon(new ImageIcon(imageCashbox));
		
		text_fieldQueueCashbox = new JTextField();
		text_fieldQueueCashbox.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		text_fieldQueueCashbox.setHorizontalAlignment(SwingConstants.CENTER);
		text_fieldQueueCashbox.setText("0");
		text_fieldQueueCashbox.setEditable(false);
		text_fieldQueueCashbox.setToolTipText("");
		text_fieldQueueCashbox.setBounds(242, 231, 48, 20);
		contentPane.add(text_fieldQueueCashbox);
		text_fieldQueueCashbox.setColumns(10);
		
		text_fieldServed = new JTextField();
		text_fieldServed.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		text_fieldServed.setHorizontalAlignment(SwingConstants.CENTER);
		text_fieldServed.setText("0");
		text_fieldServed.setEditable(false);
		text_fieldServed.setBounds(72, 179, 48, 20);
		contentPane.add(text_fieldServed);
		text_fieldServed.setColumns(10);
		
		text_fieldLost = new JTextField();
		text_fieldLost.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		text_fieldLost.setEditable(false);
		text_fieldLost.setHorizontalAlignment(SwingConstants.CENTER);
		text_fieldLost.setText("0");
		text_fieldLost.setColumns(10);
		text_fieldLost.setBounds(72, 231, 48, 20);
		contentPane.add(text_fieldLost);
		
		startBtn = new JButton("Start");
		startBtn.setFont(new Font("Segoe Print", Font.BOLD, 16));
		startBtn.setBounds(143, 65, 89, 23);
		contentPane.add(startBtn);
		
		endBtn = new JButton("End");
		endBtn.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		endBtn.setBounds(143, 103, 89, 23);
		endBtn.setEnabled(false);
		contentPane.add(endBtn);
		
		sliderArrival = new JSlider();
		sliderArrival.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		sliderArrival.setMajorTickSpacing(1);
		sliderArrival.setPaintLabels(true);
		sliderArrival.setValue(3);
		sliderArrival.setMinorTickSpacing(1);
		sliderArrival.setPaintTicks(true);
		sliderArrival.setMinimum(1);
		sliderArrival.setMaximum(7);
		sliderArrival.setToolTipText("");
		sliderArrival.setBounds(268, 88, 200, 52);
		contentPane.add(sliderArrival);
		
		JLabel lblNewLabel_2_1 = new JLabel("Arrival speed");
		lblNewLabel_2_1.setFont(new Font("Segoe Print", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(311, 54, 106, 23);
		contentPane.add(lblNewLabel_2_1);
		
		labelExit = new JLabel("");
		labelExit.setBounds(23, 280, 33, 36);
		contentPane.add(labelExit);
		
		labelEnter = new JLabel("");
		labelEnter.setBounds(23, 513, 48, 14);
		contentPane.add(labelEnter);
		
		labelPotion = new JLabel();
		labelPotion.setBounds(504, 162, 99, 89);
		contentPane.add(labelPotion);
		Image imagePotionShelf = ImageIO.read(MainGui.class.getResource("/pictures/fullShelf_potion.png"))
				.getScaledInstance(labelPotion.getWidth(), labelPotion.getHeight(), 
						Image.SCALE_SMOOTH);
		labelPotion.setIcon(new ImageIcon(imagePotionShelf));
		
		labelWeapon = new JLabel("");
		labelWeapon.setBounds(607, 337, 116, 66);
		contentPane.add(labelWeapon);
		Image imageWeaponShelf = ImageIO.read(MainGui.class.getResource("/pictures/fullShelf_weapon.png"))
				.getScaledInstance(labelWeapon.getWidth(), labelWeapon.getHeight(), 
						Image.SCALE_SMOOTH);
		labelWeapon.setIcon(new ImageIcon(imageWeaponShelf));
		
		labelArmor = new JLabel();
		labelArmor.setBounds(400, 466, 116, 71);
		contentPane.add(labelArmor);
		Image imageArmorShelf = ImageIO.read(MainGui.class.getResource("/pictures/fullShelf_protection.png"))
				.getScaledInstance(labelArmor.getWidth(), labelArmor.getHeight(), 
						Image.SCALE_SMOOTH);
		labelArmor.setIcon(new ImageIcon(imageArmorShelf));
		
		sliderPotionChoice = new JSlider();
		sliderPotionChoice.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		sliderPotionChoice.setValue(3);
		sliderPotionChoice.setToolTipText("");
		sliderPotionChoice.setPaintTicks(true);
		sliderPotionChoice.setPaintLabels(true);
		sliderPotionChoice.setMinorTickSpacing(1);
		sliderPotionChoice.setMinimum(1);
		sliderPotionChoice.setMaximum(7);
		sliderPotionChoice.setMajorTickSpacing(1);
		sliderPotionChoice.setBounds(497, 271, 127, 45);
		contentPane.add(sliderPotionChoice);
		
		sliderWeaponChoice = new JSlider();
		sliderWeaponChoice.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		sliderWeaponChoice.setValue(3);
		sliderWeaponChoice.setToolTipText("");
		sliderWeaponChoice.setPaintTicks(true);
		sliderWeaponChoice.setPaintLabels(true);
		sliderWeaponChoice.setMinorTickSpacing(1);
		sliderWeaponChoice.setMinimum(1);
		sliderWeaponChoice.setMaximum(5);
		sliderWeaponChoice.setMajorTickSpacing(1);
		sliderWeaponChoice.setBounds(607, 433, 116, 45);
		contentPane.add(sliderWeaponChoice);
		
		sliderArmorChoice = new JSlider();
		sliderArmorChoice.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		sliderArmorChoice.setForeground(Color.BLACK);
		sliderArmorChoice.setValue(3);
		sliderArmorChoice.setToolTipText("");
		sliderArmorChoice.setPaintTicks(true);
		sliderArmorChoice.setPaintLabels(true);
		sliderArmorChoice.setMinorTickSpacing(1);
		sliderArmorChoice.setMinimum(1);
		sliderArmorChoice.setMaximum(4);
		sliderArmorChoice.setMajorTickSpacing(1);
		sliderArmorChoice.setBounds(400, 564, 116, 45);
		contentPane.add(sliderArmorChoice);
		
		text_fieldqueueArmor = new JTextField();
		text_fieldqueueArmor.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		text_fieldqueueArmor.setText("0");
		text_fieldqueueArmor.setHorizontalAlignment(SwingConstants.CENTER);
		text_fieldqueueArmor.setEditable(false);
		text_fieldqueueArmor.setBounds(347, 494, 40, 20);
		contentPane.add(text_fieldqueueArmor);
		text_fieldqueueArmor.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Queue");
		lblNewLabel_2.setFont(new Font("Segoe Print", Font.BOLD, 14));
		lblNewLabel_2.setBounds(343, 468, 48, 24);
		contentPane.add(lblNewLabel_2);
		
		text_fieldqueueWeapon = new JTextField();
		text_fieldqueueWeapon.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		text_fieldqueueWeapon.setHorizontalAlignment(SwingConstants.CENTER);
		text_fieldqueueWeapon.setText("0");
		text_fieldqueueWeapon.setEditable(false);
		text_fieldqueueWeapon.setColumns(10);
		text_fieldqueueWeapon.setBounds(555, 362, 40, 20);
		contentPane.add(text_fieldqueueWeapon);
		
		text_fieldqueuePotion = new JTextField();
		text_fieldqueuePotion.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		text_fieldqueuePotion.setHorizontalAlignment(SwingConstants.CENTER);
		text_fieldqueuePotion.setText("0");
		text_fieldqueuePotion.setEditable(false);
		text_fieldqueuePotion.setColumns(10);
		text_fieldqueuePotion.setBounds(443, 191, 47, 20);
		contentPane.add(text_fieldqueuePotion);
		
		text_fieldServedPotion = new JTextField();
		text_fieldServedPotion.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		text_fieldServedPotion.setHorizontalAlignment(SwingConstants.CENTER);
		text_fieldServedPotion.setText("0");
		text_fieldServedPotion.setEditable(false);
		text_fieldServedPotion.setColumns(10);
		text_fieldServedPotion.setBounds(444, 248, 48, 20);
		contentPane.add(text_fieldServedPotion);
		
		text_fieldServedWeapon = new JTextField();
		text_fieldServedWeapon.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		text_fieldServedWeapon.setHorizontalAlignment(SwingConstants.CENTER);
		text_fieldServedWeapon.setText("0");
		text_fieldServedWeapon.setEditable(false);
		text_fieldServedWeapon.setColumns(10);
		text_fieldServedWeapon.setBounds(555, 412, 40, 20);
		contentPane.add(text_fieldServedWeapon);
		
		text_fieldServedArmor = new JTextField();
		text_fieldServedArmor.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		text_fieldServedArmor.setHorizontalAlignment(SwingConstants.CENTER);
		text_fieldServedArmor.setText("0");
		text_fieldServedArmor.setEditable(false);
		text_fieldServedArmor.setColumns(10);
		text_fieldServedArmor.setBounds(347, 544, 40, 20);
		contentPane.add(text_fieldServedArmor);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Served");
		lblNewLabel_1_2_1_1.setFont(new Font("Segoe Print", Font.BOLD, 14));
		lblNewLabel_1_2_1_1.setBounds(342, 523, 57, 14);
		contentPane.add(lblNewLabel_1_2_1_1);
		
		sliderVolume = new JSlider();
		sliderVolume.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		sliderVolume.setValue(3);
		sliderVolume.setToolTipText("");
		sliderVolume.setPaintTicks(true);
		sliderVolume.setPaintLabels(true);
		sliderVolume.setMinorTickSpacing(1);
		sliderVolume.setMinimum(1);
		sliderVolume.setMaximum(6);
		sliderVolume.setMajorTickSpacing(1);
		sliderVolume.setBounds(504, 88, 200, 52);
		contentPane.add(sliderVolume);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Volume");
		lblNewLabel_2_1_1.setFont(new Font("Segoe Print", Font.BOLD, 16));
		lblNewLabel_2_1_1.setBounds(555, 54, 90, 23);
		contentPane.add(lblNewLabel_2_1_1);
		
		labelRoute = new JLabel();
		labelRoute.setBounds(216, 369, 69, 52);
		contentPane.add(labelRoute);
		Image imageRoute = ImageIO.read(MainGui.class.getResource("/pictures/pointer.png"))
				.getScaledInstance(labelRoute.getWidth(), labelRoute.getHeight(), 
						Image.SCALE_SMOOTH);
		labelRoute.setIcon(new ImageIcon(imageRoute));
		
		JLabel lblNewLabel_2_1_2_1_1_2 = new JLabel("Speed of choice");
		lblNewLabel_2_1_2_1_1_2.setFont(new Font("Segoe Print", Font.BOLD, 16));
		lblNewLabel_2_1_2_1_1_2.setBounds(504, 256, 122, 14);
		contentPane.add(lblNewLabel_2_1_2_1_1_2);
		
		lblNewLabel_2_1_2_1_1_3 = new JLabel("Speed of choice");
		lblNewLabel_2_1_2_1_1_3.setFont(new Font("Segoe Print", Font.BOLD, 16));
		lblNewLabel_2_1_2_1_1_3.setBounds(607, 414, 122, 14);
		contentPane.add(lblNewLabel_2_1_2_1_1_3);
		
		lblNewLabel_2_1_2_1_1 = new JLabel("Speed of choice");
		lblNewLabel_2_1_2_1_1.setFont(new Font("Segoe Print", Font.BOLD, 16));
		lblNewLabel_2_1_2_1_1.setBounds(400, 547, 122, 14);
		contentPane.add(lblNewLabel_2_1_2_1_1);
		
		lblNewLabel_3 = new JLabel("Queue");
		lblNewLabel_3.setFont(new Font("Segoe Print", Font.BOLD, 14));
		lblNewLabel_3.setBounds(551, 339, 48, 24);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_1_2_1_2 = new JLabel("Served");
		lblNewLabel_1_2_1_2.setFont(new Font("Segoe Print", Font.BOLD, 14));
		lblNewLabel_1_2_1_2.setBounds(549, 391, 57, 14);
		contentPane.add(lblNewLabel_1_2_1_2);
		
		lblNewLabel_4 = new JLabel("Queue");
		lblNewLabel_4.setFont(new Font("Segoe Print", Font.BOLD, 14));
		lblNewLabel_4.setBounds(446, 163, 48, 24);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_1_2_1 = new JLabel("Served");
		lblNewLabel_1_2_1.setFont(new Font("Segoe Print", Font.BOLD, 14));
		lblNewLabel_1_2_1.setBounds(444, 226, 57, 14);
		contentPane.add(lblNewLabel_1_2_1);
		
		lblNewLabel_5 = new JLabel("Queue");
		lblNewLabel_5.setFont(new Font("Segoe Print", Font.BOLD, 14));
		lblNewLabel_5.setBounds(242, 202, 48, 24);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_1_2 = new JLabel("Served");
		lblNewLabel_1_2.setFont(new Font("Segoe Print", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(72, 158, 57, 14);
		contentPane.add(lblNewLabel_1_2);
		
		lblNewLabel_1 = new JLabel("Lost");
		lblNewLabel_1.setFont(new Font("Segoe Print", Font.BOLD, 14));
		lblNewLabel_1.setBounds(82, 210, 49, 14);
		contentPane.add(lblNewLabel_1);
		
		menuAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				About about = new About();
				about.setVisible(true);
			}
		});
		
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startend();
				
				threadMusic = new Thread() {
					@Override
					public void run() {
						soundtrack = new Sound("/pictures/soundtrack.wav",sliderVolume);
						soundtrack.playMusic();
						soundtrack.loopMusic();

						new Thread(() ->{
							while(true) {
								soundtrack.setVolume();
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}).start();
					}
				};
				
				cqueueArmor = new Counter(text_fieldqueueArmor);
				cqueueWeapon = new Counter(text_fieldqueueWeapon);
				cqueuePotion = new Counter(text_fieldqueuePotion);
				cQueueCashbox = new Counter(text_fieldQueueCashbox);
				
				cArmorServed = new Counter(text_fieldServedArmor);
				cWeaponServed = new Counter(text_fieldServedWeapon);
				cPotionServed = new Counter(text_fieldServedPotion);
				cCashboxServed = new Counter(text_fieldServed);
				
				cLost = new Counter(text_fieldLost);
				
				queueArmor = new Queue(maxQueueSize,cqueueArmor);
				queueWeapon= new Queue(maxQueueSize,cqueueWeapon);
				queuePotion = new Queue(maxQueueSize,cqueuePotion);
				queueCashbox = new Queue(maxQueueSize,cQueueCashbox);
				
				lLabelEnter = new LabelsToGo(labelEnter);
				lLabelExit = new LabelsToGo(labelExit);
				lLabelRoute = new LabelsToGo(labelRoute);
				
				Creator creator = new Creator(MainGui.this, sliderArrival, labelEnter);
				Armor armor = new Armor(MainGui.this, labelArmor, queueArmor, sliderArmorChoice, 
						maxQueueSize, 3, cqueueArmor, cArmorServed);
				Weapon weapon = new Weapon(MainGui.this, labelWeapon, queueWeapon, sliderWeaponChoice, 
						maxQueueSize, 3, cqueueWeapon, cWeaponServed);
				Potion potion = new Potion(MainGui.this, labelPotion, queuePotion, sliderPotionChoice, 
						maxQueueSize, 3, cqueuePotion, cPotionServed);
				Cashbox cashbox = new Cashbox(MainGui.this, labelCashbox, queueCashbox, sliderArrival,
								maxQueueSize, 3, cQueueCashbox, cCashboxServed);
				
				threadCashbox = new Thread(cashbox);
				threadPotion = new Thread(potion);
				threadWeapon = new Thread(weapon);
				threadArmor = new Thread(armor);
				threadCreator = new Thread(creator);

				threadCashbox.start();
				threadPotion.start();
				threadWeapon.start();
				threadArmor.start();
				threadCreator.start();
				threadMusic.start();
			}
		});
		
		endBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startend();			
				soundtrack.stopMusic();			
				try {
					threadCreator.join();
					threadPotion.join();
					threadWeapon.join();
					threadArmor.join();
					threadCashbox.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void startend() {
		end = !end;
		startBtn.setEnabled(end);
		endBtn.setEnabled(!end);
	}
}
