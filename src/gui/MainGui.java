package gui;

import java.awt.EventQueue;
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
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class MainGui extends JFrame {

	private JPanel contentPane;
	
	public JTextField text_fieldQueueCashbox;
	public JTextField text_fieldServed;
	public JTextField text_fieldLost;
	public JTextField text_fieldqueueArmor;
	public JTextField text_fieldqueueWeapon;
	public JTextField text_fieldqueuePotion;
	public JTextField text_fieldServedMeat;
	public JTextField text_fieldServedMilk;
	public JTextField text_fieldServedBread;
	
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
	//public LabelsToGo llabelArmor;
	
	public Thread threadCreator;
	public Thread threadPotion;
	public Thread threadWeapon;
	public Thread threadArmor;
	public Thread threadCashbox;
	public Thread threadMusic;
	
	public Sound soundtrack;
	
	public int maxQueueSize = 3;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainGui.class.getResource("/pictures/\u0430\u0442\u0431.png")));
		setTitle("Supermarket");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 755, 698);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuSettings = new JMenu("Settings");
		menuBar.add(menuSettings);
		
		JMenuItem menuAbout = new JMenuItem("About");
		menuSettings.add(menuAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		labelCashbox = new JLabel("Cashbox----------------------------------");
		labelCashbox.setBounds(96, 109, 188, 89);
		contentPane.add(labelCashbox);
		
		text_fieldQueueCashbox = new JTextField();
		text_fieldQueueCashbox.setHorizontalAlignment(SwingConstants.CENTER);
		text_fieldQueueCashbox.setText("0");
		text_fieldQueueCashbox.setEditable(false);
		text_fieldQueueCashbox.setToolTipText("");
		text_fieldQueueCashbox.setBounds(10, 109, 76, 20);
		contentPane.add(text_fieldQueueCashbox);
		text_fieldQueueCashbox.setColumns(10);
		
		text_fieldServed = new JTextField();
		text_fieldServed.setHorizontalAlignment(SwingConstants.CENTER);
		text_fieldServed.setText("0");
		text_fieldServed.setEditable(false);
		text_fieldServed.setBounds(10, 162, 76, 20);
		contentPane.add(text_fieldServed);
		text_fieldServed.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u041D\u0430 \u043A\u0430\u0441\u0456");
		lblNewLabel.setBounds(30, 93, 40, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u041E\u0431\u0441\u043B\u0443\u0436\u0435\u043D\u043E");
		lblNewLabel_1.setBounds(20, 150, 63, 14);
		contentPane.add(lblNewLabel_1);
		
		text_fieldLost = new JTextField();
		text_fieldLost.setEditable(false);
		text_fieldLost.setHorizontalAlignment(SwingConstants.CENTER);
		text_fieldLost.setText("0");
		text_fieldLost.setColumns(10);
		text_fieldLost.setBounds(10, 217, 76, 20);
		contentPane.add(text_fieldLost);
		
		JLabel lblNewLabel_1_1 = new JLabel("\u0412\u0442\u0440\u0430\u0447\u0435\u043D\u043E");
		lblNewLabel_1_1.setBounds(20, 203, 63, 14);
		contentPane.add(lblNewLabel_1_1);
		
		startBtn = new JButton("Start");
		startBtn.setBounds(126, 11, 89, 23);
		contentPane.add(startBtn);
		
		endBtn = new JButton("End");
		endBtn.setBounds(126, 45, 89, 23);
		endBtn.setEnabled(false);
		contentPane.add(endBtn);
		
		sliderArrival = new JSlider();
		sliderArrival.setMajorTickSpacing(1);
		sliderArrival.setPaintLabels(true);
		sliderArrival.setValue(3);
		sliderArrival.setMinorTickSpacing(1);
		sliderArrival.setPaintTicks(true);
		sliderArrival.setMinimum(1);
		sliderArrival.setMaximum(7);
		sliderArrival.setToolTipText("");
		sliderArrival.setBounds(249, 45, 200, 45);
		contentPane.add(sliderArrival);
		
		JLabel lblNewLabel_2_1 = new JLabel("\u0428\u0432\u0438\u0434\u043A\u0456\u0441\u0442\u044C \u043F\u0440\u0438\u0431\u0443\u0442\u0442\u044F");
		lblNewLabel_2_1.setBounds(290, 20, 116, 14);
		contentPane.add(lblNewLabel_2_1);
		
		labelExit = new JLabel("\u0412\u0438\u0445\u0456\u0434--------------------");
		labelExit.setBounds(10, 280, 116, 89);
		contentPane.add(labelExit);
		
		labelEnter = new JLabel("\u0412\u0445\u0456\u0434---------------------");
		labelEnter.setBounds(10, 521, 116, 89);
		contentPane.add(labelEnter);
		
		labelPotion = new JLabel();
		labelPotion.setBounds(504, 137, 91, 89);
		contentPane.add(labelPotion);
		Image imagePotionShelf = ImageIO.read(MainGui.class.getResource("/pictures/fullShelf_potion.png"))
				.getScaledInstance(labelPotion.getWidth(), labelPotion.getHeight(), 
						Image.SCALE_SMOOTH);
		labelPotion.setIcon(new ImageIcon(imagePotionShelf));
		
		labelWeapon = new JLabel("");
		labelWeapon.setBounds(607, 318, 116, 66);
		contentPane.add(labelWeapon);
		Image imageWeaponShelf = ImageIO.read(MainGui.class.getResource("/pictures/fullShelf_weapon.png"))
				.getScaledInstance(labelWeapon.getWidth(), labelWeapon.getHeight(), 
						Image.SCALE_SMOOTH);
		labelWeapon.setIcon(new ImageIcon(imageWeaponShelf));
		
		labelArmor = new JLabel();
		labelArmor.setBounds(479, 485, 116, 71);
		contentPane.add(labelArmor);
		Image imageArmorShelf = ImageIO.read(MainGui.class.getResource("/pictures/fullShelf_protection.png"))
				.getScaledInstance(labelArmor.getWidth(), labelArmor.getHeight(), 
						Image.SCALE_SMOOTH);
		labelArmor.setIcon(new ImageIcon(imageArmorShelf));
		
		sliderPotionChoice = new JSlider();
		sliderPotionChoice.setValue(3);
		sliderPotionChoice.setToolTipText("");
		sliderPotionChoice.setPaintTicks(true);
		sliderPotionChoice.setPaintLabels(true);
		sliderPotionChoice.setMinorTickSpacing(1);
		sliderPotionChoice.setMinimum(1);
		sliderPotionChoice.setMaximum(7);
		sliderPotionChoice.setMajorTickSpacing(1);
		sliderPotionChoice.setBounds(497, 254, 116, 45);
		contentPane.add(sliderPotionChoice);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("\u0428\u0432\u0438\u0434\u043A\u0456\u0441\u0442\u044C \u0432\u0438\u0431\u043E\u0440\u0443");
		lblNewLabel_2_1_2.setBounds(513, 238, 100, 14);
		contentPane.add(lblNewLabel_2_1_2);
		
		sliderWeaponChoice = new JSlider();
		sliderWeaponChoice.setValue(3);
		sliderWeaponChoice.setToolTipText("");
		sliderWeaponChoice.setPaintTicks(true);
		sliderWeaponChoice.setPaintLabels(true);
		sliderWeaponChoice.setMinorTickSpacing(1);
		sliderWeaponChoice.setMinimum(1);
		sliderWeaponChoice.setMaximum(5);
		sliderWeaponChoice.setMajorTickSpacing(1);
		sliderWeaponChoice.setBounds(607, 414, 116, 45);
		contentPane.add(sliderWeaponChoice);
		
		JLabel lblNewLabel_2_1_2_1 = new JLabel("\u0428\u0432\u0438\u0434\u043A\u0456\u0441\u0442\u044C \u0432\u0438\u0431\u043E\u0440\u0443");
		lblNewLabel_2_1_2_1.setBounds(623, 395, 100, 14);
		contentPane.add(lblNewLabel_2_1_2_1);
		
		sliderArmorChoice = new JSlider();
		sliderArmorChoice.setValue(3);
		sliderArmorChoice.setToolTipText("");
		sliderArmorChoice.setPaintTicks(true);
		sliderArmorChoice.setPaintLabels(true);
		sliderArmorChoice.setMinorTickSpacing(1);
		sliderArmorChoice.setMinimum(1);
		sliderArmorChoice.setMaximum(4);
		sliderArmorChoice.setMajorTickSpacing(1);
		sliderArmorChoice.setBounds(479, 581, 116, 45);
		contentPane.add(sliderArmorChoice);
		
		JLabel lblNewLabel_2_1_2_1_1 = new JLabel("\u0428\u0432\u0438\u0434\u043A\u0456\u0441\u0442\u044C \u0432\u0438\u0431\u043E\u0440\u0443");
		lblNewLabel_2_1_2_1_1.setBounds(495, 567, 100, 14);
		contentPane.add(lblNewLabel_2_1_2_1_1);
		
		text_fieldqueueArmor = new JTextField();
		text_fieldqueueArmor.setText("0");
		text_fieldqueueArmor.setHorizontalAlignment(SwingConstants.CENTER);
		text_fieldqueueArmor.setEditable(false);
		text_fieldqueueArmor.setBounds(412, 536, 57, 20);
		contentPane.add(text_fieldqueueArmor);
		text_fieldqueueArmor.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u0427\u0435\u0440\u0433\u0430");
		lblNewLabel_2.setBounds(424, 521, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		text_fieldqueueWeapon = new JTextField();
		text_fieldqueueWeapon.setHorizontalAlignment(SwingConstants.CENTER);
		text_fieldqueueWeapon.setText("0");
		text_fieldqueueWeapon.setEditable(false);
		text_fieldqueueWeapon.setColumns(10);
		text_fieldqueueWeapon.setBounds(546, 364, 57, 20);
		contentPane.add(text_fieldqueueWeapon);
		
		JLabel lblNewLabel_2_2 = new JLabel("\u0427\u0435\u0440\u0433\u0430");
		lblNewLabel_2_2.setBounds(550, 344, 46, 14);
		contentPane.add(lblNewLabel_2_2);
		
		text_fieldqueuePotion = new JTextField();
		text_fieldqueuePotion.setHorizontalAlignment(SwingConstants.CENTER);
		text_fieldqueuePotion.setText("0");
		text_fieldqueuePotion.setEditable(false);
		text_fieldqueuePotion.setColumns(10);
		text_fieldqueuePotion.setBounds(440, 178, 57, 20);
		contentPane.add(text_fieldqueuePotion);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("\u0427\u0435\u0440\u0433\u0430");
		lblNewLabel_2_2_1.setBounds(456, 165, 46, 14);
		contentPane.add(lblNewLabel_2_2_1);
		
		text_fieldServedMeat = new JTextField();
		text_fieldServedMeat.setHorizontalAlignment(SwingConstants.CENTER);
		text_fieldServedMeat.setText("0");
		text_fieldServedMeat.setEditable(false);
		text_fieldServedMeat.setColumns(10);
		text_fieldServedMeat.setBounds(440, 235, 57, 20);
		contentPane.add(text_fieldServedMeat);
		
		JLabel lblNewLabel_1_2 = new JLabel("\u041E\u0431\u0441\u043B\u0443\u0436\u0435\u043D\u043E");
		lblNewLabel_1_2.setBounds(439, 220, 63, 14);
		contentPane.add(lblNewLabel_1_2);
		
		text_fieldServedMilk = new JTextField();
		text_fieldServedMilk.setHorizontalAlignment(SwingConstants.CENTER);
		text_fieldServedMilk.setText("0");
		text_fieldServedMilk.setEditable(false);
		text_fieldServedMilk.setColumns(10);
		text_fieldServedMilk.setBounds(546, 411, 57, 20);
		contentPane.add(text_fieldServedMilk);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("\u041E\u0431\u0441\u043B\u0443\u0436\u0435\u043D\u043E");
		lblNewLabel_1_2_1.setBounds(550, 389, 63, 14);
		contentPane.add(lblNewLabel_1_2_1);
		
		text_fieldServedBread = new JTextField();
		text_fieldServedBread.setHorizontalAlignment(SwingConstants.CENTER);
		text_fieldServedBread.setText("0");
		text_fieldServedBread.setEditable(false);
		text_fieldServedBread.setColumns(10);
		text_fieldServedBread.setBounds(412, 581, 57, 20);
		contentPane.add(text_fieldServedBread);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("\u041E\u0431\u0441\u043B\u0443\u0436\u0435\u043D\u043E");
		lblNewLabel_1_2_1_1.setBounds(412, 567, 63, 14);
		contentPane.add(lblNewLabel_1_2_1_1);
		
		sliderVolume = new JSlider();
		sliderVolume.setValue(3);
		sliderVolume.setToolTipText("");
		sliderVolume.setPaintTicks(true);
		sliderVolume.setPaintLabels(true);
		sliderVolume.setMinorTickSpacing(1);
		sliderVolume.setMinimum(1);
		sliderVolume.setMaximum(6);
		sliderVolume.setMajorTickSpacing(1);
		sliderVolume.setBounds(499, 45, 200, 45);
		contentPane.add(sliderVolume);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("\u0413\u0443\u0447\u043D\u0456\u0441\u0442\u044C");
		lblNewLabel_2_1_1.setBounds(579, 20, 57, 14);
		contentPane.add(lblNewLabel_2_1_1);
		
		labelRoute = new JLabel("Route----------------");
		labelRoute.setBounds(222, 364, 100, 71);
		contentPane.add(labelRoute);
		
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
						try {
							soundtrack = new Sound("/pictures/soundtrack.wav",sliderVolume);
							//soundtrack = new Sound("/pictures/newATB.wav",sliderVolume);
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

							threadCreator.join();
							threadPotion.join();
							threadWeapon.join();
							threadArmor.join();
							threadCashbox.join();
							
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				};
				
				cqueueArmor = new Counter(text_fieldqueueArmor);
				cqueueWeapon = new Counter(text_fieldqueueWeapon);
				cqueuePotion = new Counter(text_fieldqueuePotion);
				cQueueCashbox = new Counter(text_fieldQueueCashbox);
				
				cArmorServed = new Counter(text_fieldServedBread);
				cWeaponServed = new Counter(text_fieldServedMilk);
				cPotionServed = new Counter(text_fieldServedMeat);
				cCashboxServed = new Counter(text_fieldServed);
				
				cLost = new Counter(text_fieldLost);
				
				queueArmor = new Queue(text_fieldqueueArmor,maxQueueSize);
				queueWeapon= new Queue(text_fieldqueueWeapon,maxQueueSize);
				queuePotion = new Queue(text_fieldqueuePotion,maxQueueSize);
				queueCashbox = new Queue(text_fieldQueueCashbox,maxQueueSize);
				
				lLabelEnter = new LabelsToGo(labelEnter);
				lLabelExit = new LabelsToGo(labelExit);
				lLabelRoute = new LabelsToGo(labelRoute);
				
				Creator creator = new Creator(MainGui.this, sliderArrival, labelEnter);
				Armor armor = new Armor(MainGui.this, labelArmor, queueArmor, sliderArmorChoice, 
										3, 3, cqueueArmor, cArmorServed);
				Weapon weapon = new Weapon(MainGui.this, labelWeapon, queueWeapon, sliderWeaponChoice, 
										3, 3, cqueueWeapon, cWeaponServed);
				Potion potion = new Potion(MainGui.this, labelPotion, queuePotion, sliderPotionChoice, 
									3, 3, cqueuePotion, cPotionServed);
				Cashbox cashbox = new Cashbox(MainGui.this, labelCashbox, queueCashbox, 
											3, 3, cQueueCashbox, cCashboxServed, cLost);
				
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

	// програвання музики
	public boolean isPlaying() {

		return false;
	}
}
