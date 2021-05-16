package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGui extends JFrame {

	private JPanel contentPane;
	private JTextField text_fieldQueueCashbox;
	private JTextField text_fieldServed;
	private JTextField text_fieldLost;
	private JTextField text_fieldQueueBread;
	private JTextField text_fieldQueueMilk;
	private JTextField text_fieldQueueMeat;
	private JTextField text_fieldServedMeat;
	private JTextField text_fieldServedMilk;
	private JTextField text_fieldServedBread;
	public boolean end = false;

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
	 */
	public MainGui() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainGui.class.getResource("/pictures/\u0430\u0442\u0431.png")));
		setTitle("Supermarket");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 755, 592);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuSettings = new JMenu("Settings");
		menuBar.add(menuSettings);
		
		JMenuItem menuAbout = new JMenuItem("About");
		menuAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				About about = new About();
				about.setVisible(true);
			}
		});
		menuSettings.add(menuAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelCashbox = new JLabel("Cashbox----------------------------------");
		labelCashbox.setBounds(96, 109, 188, 89);
		contentPane.add(labelCashbox);
		
		text_fieldQueueCashbox = new JTextField();
		text_fieldQueueCashbox.setToolTipText("");
		text_fieldQueueCashbox.setBounds(10, 109, 76, 20);
		contentPane.add(text_fieldQueueCashbox);
		text_fieldQueueCashbox.setColumns(10);
		
		text_fieldServed = new JTextField();
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
		text_fieldLost.setColumns(10);
		text_fieldLost.setBounds(10, 217, 76, 20);
		contentPane.add(text_fieldLost);
		
		JLabel lblNewLabel_1_1 = new JLabel("\u0412\u0442\u0440\u0430\u0447\u0435\u043D\u043E");
		lblNewLabel_1_1.setBounds(20, 203, 63, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JButton startBtn = new JButton("Start");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startend();
				
			}
		});
		startBtn.setBounds(126, 11, 89, 23);
		contentPane.add(startBtn);
		
		JButton endBtn = new JButton("End");
		endBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startend();
			}
		});
		endBtn.setBounds(126, 45, 89, 23);
		contentPane.add(endBtn);
		
		JSlider sliderArrival = new JSlider();
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
		
		JLabel labelExit = new JLabel("\u0412\u0438\u0445\u0456\u0434--------------------");
		labelExit.setBounds(10, 280, 116, 89);
		contentPane.add(labelExit);
		
		JLabel labelEnter = new JLabel("\u0412\u0445\u0456\u0434---------------------");
		labelEnter.setBounds(10, 406, 116, 89);
		contentPane.add(labelEnter);
		
		JLabel labelMeat = new JLabel("Meat-----------------------");
		labelMeat.setBounds(512, 127, 116, 71);
		contentPane.add(labelMeat);
		
		JLabel labelMilk = new JLabel("\u041C\u043E\u043B\u043E\u043A\u043E-------------------");
		labelMilk.setBounds(613, 280, 116, 71);
		contentPane.add(labelMilk);
		
		JLabel labelBread = new JLabel("\u0425\u043B\u0456\u0431-----------------------");
		labelBread.setBounds(410, 378, 116, 71);
		contentPane.add(labelBread);
		
		JSlider sliderMeatChoice = new JSlider();
		sliderMeatChoice.setValue(3);
		sliderMeatChoice.setToolTipText("");
		sliderMeatChoice.setPaintTicks(true);
		sliderMeatChoice.setPaintLabels(true);
		sliderMeatChoice.setMinorTickSpacing(1);
		sliderMeatChoice.setMinimum(1);
		sliderMeatChoice.setMaximum(7);
		sliderMeatChoice.setMajorTickSpacing(1);
		sliderMeatChoice.setBounds(512, 224, 116, 45);
		contentPane.add(sliderMeatChoice);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("\u0428\u0432\u0438\u0434\u043A\u0456\u0441\u0442\u044C \u0432\u0438\u0431\u043E\u0440\u0443");
		lblNewLabel_2_1_2.setBounds(522, 203, 100, 14);
		contentPane.add(lblNewLabel_2_1_2);
		
		JSlider sliderMilkChoice = new JSlider();
		sliderMilkChoice.setValue(3);
		sliderMilkChoice.setToolTipText("");
		sliderMilkChoice.setPaintTicks(true);
		sliderMilkChoice.setPaintLabels(true);
		sliderMilkChoice.setMinorTickSpacing(1);
		sliderMilkChoice.setMinimum(1);
		sliderMilkChoice.setMaximum(5);
		sliderMilkChoice.setMajorTickSpacing(1);
		sliderMilkChoice.setBounds(613, 377, 116, 45);
		contentPane.add(sliderMilkChoice);
		
		JLabel lblNewLabel_2_1_2_1 = new JLabel("\u0428\u0432\u0438\u0434\u043A\u0456\u0441\u0442\u044C \u0432\u0438\u0431\u043E\u0440\u0443");
		lblNewLabel_2_1_2_1.setBounds(623, 362, 100, 14);
		contentPane.add(lblNewLabel_2_1_2_1);
		
		JSlider sliderBreadChoice = new JSlider();
		sliderBreadChoice.setValue(3);
		sliderBreadChoice.setToolTipText("");
		sliderBreadChoice.setPaintTicks(true);
		sliderBreadChoice.setPaintLabels(true);
		sliderBreadChoice.setMinorTickSpacing(1);
		sliderBreadChoice.setMinimum(1);
		sliderBreadChoice.setMaximum(4);
		sliderBreadChoice.setMajorTickSpacing(1);
		sliderBreadChoice.setBounds(410, 475, 116, 45);
		contentPane.add(sliderBreadChoice);
		
		JLabel lblNewLabel_2_1_2_1_1 = new JLabel("\u0428\u0432\u0438\u0434\u043A\u0456\u0441\u0442\u044C \u0432\u0438\u0431\u043E\u0440\u0443");
		lblNewLabel_2_1_2_1_1.setBounds(420, 460, 100, 14);
		contentPane.add(lblNewLabel_2_1_2_1_1);
		
		text_fieldQueueBread = new JTextField();
		text_fieldQueueBread.setBounds(343, 440, 57, 20);
		contentPane.add(text_fieldQueueBread);
		text_fieldQueueBread.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u0427\u0435\u0440\u0433\u0430");
		lblNewLabel_2.setBounds(354, 423, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		text_fieldQueueMilk = new JTextField();
		text_fieldQueueMilk.setColumns(10);
		text_fieldQueueMilk.setBounds(546, 331, 57, 20);
		contentPane.add(text_fieldQueueMilk);
		
		JLabel lblNewLabel_2_2 = new JLabel("\u0427\u0435\u0440\u0433\u0430");
		lblNewLabel_2_2.setBounds(557, 317, 46, 14);
		contentPane.add(lblNewLabel_2_2);
		
		text_fieldQueueMeat = new JTextField();
		text_fieldQueueMeat.setColumns(10);
		text_fieldQueueMeat.setBounds(440, 178, 57, 20);
		contentPane.add(text_fieldQueueMeat);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("\u0427\u0435\u0440\u0433\u0430");
		lblNewLabel_2_2_1.setBounds(456, 165, 46, 14);
		contentPane.add(lblNewLabel_2_2_1);
		
		text_fieldServedMeat = new JTextField();
		text_fieldServedMeat.setColumns(10);
		text_fieldServedMeat.setBounds(440, 234, 57, 20);
		contentPane.add(text_fieldServedMeat);
		
		JLabel lblNewLabel_1_2 = new JLabel("\u041E\u0431\u0441\u043B\u0443\u0436\u0435\u043D\u043E");
		lblNewLabel_1_2.setBounds(440, 220, 63, 14);
		contentPane.add(lblNewLabel_1_2);
		
		text_fieldServedMilk = new JTextField();
		text_fieldServedMilk.setColumns(10);
		text_fieldServedMilk.setBounds(546, 381, 57, 20);
		contentPane.add(text_fieldServedMilk);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("\u041E\u0431\u0441\u043B\u0443\u0436\u0435\u043D\u043E");
		lblNewLabel_1_2_1.setBounds(546, 362, 63, 14);
		contentPane.add(lblNewLabel_1_2_1);
		
		text_fieldServedBread = new JTextField();
		text_fieldServedBread.setColumns(10);
		text_fieldServedBread.setBounds(343, 489, 57, 20);
		contentPane.add(text_fieldServedBread);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("\u041E\u0431\u0441\u043B\u0443\u0436\u0435\u043D\u043E");
		lblNewLabel_1_2_1_1.setBounds(343, 475, 63, 14);
		contentPane.add(lblNewLabel_1_2_1_1);
	}
	
	public void startend() {
		end = !end;
	}

	// програвання музики
	public boolean isPlaying() {
		
		
		return false;
	}
}
