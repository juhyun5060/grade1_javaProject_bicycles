package Bicycle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Main {
	
	private JFrame frame;
	private JTextField nametextfield;
	private JTextField numtextfield;
	private JTextField textfield;

	int time; //�뿩 �ð�
	int lateTime; //���� �ð�
	String bike; //�뿩 ������ ����
	int num; //�뿩 ����

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		//table ����
		Customer.createTable_customer();
		Bicycle.createTable_bicycle();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Customer customer = new Customer();
		Bicycle bicycle = new Bicycle();
		frame = new JFrame();
		frame.setSize(800, 560);
		frame.setResizable(true);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().setLayout(null);
		
		//ó�� �ð� ���� â
		JPanel timechoicepage = new JPanel();
		timechoicepage.setBounds(0, 0, 786, 528);
		frame.getContentPane().add(timechoicepage);
		timechoicepage.setLayout(null);
		
		JComboBox comboTime = new JComboBox();
		comboTime.setFont(new Font("�޸յձ�������", Font.PLAIN, 15));
		comboTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboTime.getSelectedIndex() == 0){
					time = 1;
				} else if(comboTime.getSelectedIndex() == 1) {
					time = 2;
				} else if(comboTime.getSelectedIndex() == 2) {
					time = 3;
				} else if(comboTime.getSelectedIndex() == 3) {
					time = 4;
				}
			}
		});
		
		JLabel lblNewLabel_6 = new JLabel("\uB300\uC5EC\uD558\uAE30");
		lblNewLabel_6.setFont(new Font("�޸յձ�������", Font.PLAIN, 35));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(286, 36, 222, 66);
		timechoicepage.add(lblNewLabel_6);
		comboTime.setModel(new DefaultComboBoxModel(new String[] {"1\uC2DC\uAC04", "2\uC2DC\uAC04", "3\uC2DC\uAC04", "4\uC2DC\uAC04"}));
		comboTime.setBounds(448, 205, 89, 32);
		timechoicepage.add(comboTime);
		
		JComboBox comboCount = new JComboBox();
		comboCount.setFont(new Font("�޸յձ�������", Font.PLAIN, 15));
		comboCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboCount.getSelectedIndex() == 0){
					num = 1;
				} else if(comboCount.getSelectedIndex() == 1) {
					num = 2;
				} else if(comboCount.getSelectedIndex() == 2) {
					num = 3;
				} else if(comboCount.getSelectedIndex() == 3) {
					num = 4;
				}
			}
		});
		comboCount.setModel(new DefaultComboBoxModel(new String[] {"1\uAC1C", "2\uAC1C", "3\uAC1C", "4\uAC1C"}));
		comboCount.setBounds(448, 319, 89, 32);
		timechoicepage.add(comboCount);
		
		JLabel label = new JLabel("���� ����");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("�޸յձ�������", Font.PLAIN, 30));
		label.setBounds(227, 307, 169, 61);
		timechoicepage.add(label);
		
		JLabel label_1 = new JLabel("\uC2DC\uAC04 \uC120\uD0DD");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("�޸յձ�������", Font.PLAIN, 30));
		label_1.setBounds(227, 191, 169, 61);
		timechoicepage.add(label_1);
		
		/*JButton button = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		button.setBounds(51, 455, 131, 32);
		timechoicepage.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				timechoicepage.setVisible(false);
				thirdpage.setVisible(true);
			}
		});
		button.setFont(new Font("�޸յձ�������", Font.PLAIN, 13));*/
		
		//ó�� �ð��� �����ϴ� â���� ǥ�õǴ� �ʱ�ȭ������ ��ư
		JButton btngofirstchoicetime = new JButton("\uB300\uC5EC\uD558\uAE30");
		btngofirstchoicetime.setFont(new Font("�޸յձ�������", Font.PLAIN, 13));
		btngofirstchoicetime.setBounds(590, 455, 131, 32);
		timechoicepage.add(btngofirstchoicetime);
		
		JLabel backgroundtimechoice = new JLabel("");
		backgroundtimechoice.setIcon(new ImageIcon("background.JPG"));
		backgroundtimechoice.setBounds(0, 0, 786, 528);
		timechoicepage.add(backgroundtimechoice);
		
		//������ �뿩�� �Ϸ�Ǿ����ϴٸ� ǥ�����ִ� â 
		JPanel lendokpage = new JPanel();
		lendokpage.setBounds(0, 0, 786, 528);
		frame.getContentPane().add(lendokpage);
		lendokpage.setLayout(null);
		
		//������ �뿩�� �Ϸ�Ǿ����ϴ� ��� ������ ǥ�õǴ� �ؽ�Ʈ 
		JLabel lendok = new JLabel("\uC790\uC804\uAC70 \uB300\uC5EC\uAC00 \uC644\uB8CC\uB418\uC5C8\uC2B5\uB2C8\uB2E4!");
		lendok.setBounds(112, 87, 583, 148);
		lendok.setFont(new Font("�޸յձ�������", Font.PLAIN, 40));
		lendokpage.add(lendok);
		
		//������ �뿩 �������� ���α׷� ���� ��ư 
		JButton btngofirstlendok = new JButton("\uB2EB\uAE30");
		btngofirstlendok.setBackground(new Color(255, 255, 224));
		btngofirstlendok.setBounds(590, 261, 109, 32);
		btngofirstlendok.setFont(new Font("�޸յձ�������", Font.PLAIN, 15));
		lendokpage.add(btngofirstlendok);
		
		JLabel backgroundlendok = new JLabel("New label");
		backgroundlendok.setIcon(new ImageIcon("background.JPG"));
		backgroundlendok.setBounds(0, 0, 785, 528);
		lendokpage.add(backgroundlendok);
		lendokpage.setVisible(false);
		
		//2��° ���������� ���� �ð� Ȯ�� ��ư�� ������ �� �ߴ� â 
		JPanel fivepage = new JPanel();
		fivepage.setBounds(0, 0, 786, 528);
		frame.getContentPane().add(fivepage);
		fivepage.setLayout(null);
		frame.setBounds(100, 100, 800, 565);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Login");
		
		// db ���̺�
		String[][] dt = customer.getCustomers();
		String[] header = new String[] { "Name", "Phone", "Time", "Date" };
		JTable table = new JTable(dt, header);
		table.setRowHeight(30);
		table.setFont(new Font("�޸յձ�������", Font.PLAIN, 13));
		table.setLocation(93, 148);
		table.setSize(612, 300);
		table.getColumnModel().getColumn(0).setPreferredWidth(5); //name
		table.getColumnModel().getColumn(1).setPreferredWidth(10); //phone
		table.getColumnModel().getColumn(2).setPreferredWidth(5); //time
		table.getColumnModel().getColumn(3).setPreferredWidth(50); //date

		JLabel lblNewLabel_5 = new JLabel("Name");
		lblNewLabel_5.setFont(new Font("�޸յձ�������", Font.PLAIN, 15));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(95, 106, 148, 40);
		fivepage.add(lblNewLabel_5);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone.setFont(new Font("�޸յձ�������", Font.PLAIN, 15));
		lblPhone.setBounds(230, 107, 148, 40);
		fivepage.add(lblPhone);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("�޸յձ�������", Font.PLAIN, 15));
		lblTime.setBounds(371, 106, 148, 40);
		fivepage.add(lblTime);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setFont(new Font("�޸յձ�������", Font.PLAIN, 15));
		lblDate.setBounds(533, 106, 148, 40);
		fivepage.add(lblDate);
		table.setPreferredScrollableViewportSize(new Dimension(612, 354));
		fivepage.add(table);
		
		//'���� ���� �ð�'�� ǥ�����ִ� �ؽ�Ʈ 
		JLabel checktime = new JLabel("\uD604\uC7AC \uB0A8\uC740 \uC2DC\uAC04");
		checktime.setHorizontalAlignment(SwingConstants.CENTER);
		checktime.setFont(new Font("�޸յձ�������", Font.PLAIN, 35));
		checktime.setBounds(217, 12, 335, 74);
		fivepage.add(checktime);
		
		//���� ���� �ð��� ǥ�����ִ� â���� ǥ�õǴ� �ʱ�ȭ������ ��ư
		JButton btngofirsttime = new JButton("\uBA54\uB274\uD654\uBA74\uC73C\uB85C ");
		btngofirsttime.setBackground(new Color(255, 255, 224));
		btngofirsttime.setFont(new Font("�޸յձ�������", Font.PLAIN, 13));
		btngofirsttime.setBounds(590, 455, 131, 32);
		fivepage.add(btngofirsttime);
		
		JLabel backgroundfive = new JLabel("New label");
		backgroundfive.setIcon(new ImageIcon("background.JPG"));
		backgroundfive.setBounds(0, 0, 786, 528);
		fivepage.add(backgroundfive);
		fivepage.setVisible(false);	
	
		//�ð��� ����Ǿ����ϴٸ� ǥ�����ִ� â 
		JPanel fourstwolatepage = new JPanel();
		fourstwolatepage.setBounds(0, 0, 786, 528);
		frame.getContentPane().add(fourstwolatepage);
		fourstwolatepage.setLayout(null);
		
		//�ð��� ����Ǿ����ϴ� ��� ������ ǥ�õǴ� �ؽ�Ʈ 
		JLabel latetimeok = new JLabel("\uC2DC\uAC04\uC774 \uC5F0\uC7A5\uB418\uC5C8\uC2B5\uB2C8\uB2E4");
		latetimeok.setBounds(154, 139, 501, 94);
		latetimeok.setFont(new Font("�޸յձ�������", Font.PLAIN, 40));
		latetimeok.setHorizontalAlignment(SwingConstants.CENTER);
		fourstwolatepage.add(latetimeok);
		
		//�ð� ���� �������� ���α׷� ���� ��ư 
		JButton btnlatetimebye = new JButton("\uB2EB\uAE30");
		btnlatetimebye.setBackground(new Color(255, 255, 224));
		btnlatetimebye.setBounds(590, 455, 112, 34);
		btnlatetimebye.setFont(new Font("�޸յձ�������", Font.PLAIN, 12));
		fourstwolatepage.add(btnlatetimebye);
		
		JLabel backgroundfourstwo = new JLabel("New label");
		backgroundfourstwo.setIcon(new ImageIcon("background.JPG"));
		backgroundfourstwo.setBounds(0, 0, 786, 528);
		fourstwolatepage.add(backgroundfourstwo);
		
		//�ð� �����ϴ� â 
		JPanel fourstwopage = new JPanel();
		fourstwopage.setBounds(0, 0, 786, 538);
		frame.getContentPane().add(fourstwopage);
		fourstwopage.setLayout(null);
		
		//'������ �ð� ����'�� ǥ�����ִ� �ؽ�Ʈ 
		JLabel latetimechoice = new JLabel("\uC5F0\uC7A5\uD560 \uC2DC\uAC04 \uC120\uD0DD");
		latetimechoice.setFont(new Font("�޸յձ�������", Font.PLAIN, 35));
		latetimechoice.setBounds(239, 73, 298, 60);
		fourstwopage.add(latetimechoice);
		
		//1�ð� ���� ��ư 
		JButton btnlateone = new JButton("1\uC2DC\uAC04 \uC5F0\uC7A5");
		btnlateone.setBackground(new Color(255, 255, 224));
		btnlateone.setFont(new Font("�޸յձ�������", Font.PLAIN, 18));
		btnlateone.setBounds(275, 177, 205, 31);
		fourstwopage.add(btnlateone);
		
		//2�ð� ���� ��ư 
		JButton btnlatetwo = new JButton("2\uC2DC\uAC04 \uC5F0\uC7A5");
		btnlatetwo.setBackground(new Color(255, 255, 224));
		btnlatetwo.setFont(new Font("�޸յձ�������", Font.PLAIN, 18));
		btnlatetwo.setBounds(275, 235, 205, 31);
		fourstwopage.add(btnlatetwo);
		
		//3�ð� ���� ��ư 
		JButton btnlatethird = new JButton("3\uC2DC\uAC04 \uC5F0\uC7A5");
		btnlatethird.setBackground(new Color(255, 255, 224));
		btnlatethird.setFont(new Font("�޸յձ�������", Font.PLAIN, 18));
		btnlatethird.setBounds(275, 295, 205, 31);
		fourstwopage.add(btnlatethird);
		
		//4�ð� ���� ��ư 
		JButton btnlatefour = new JButton("4\uC2DC\uAC04 \uC5F0\uC7A5");
		btnlatefour.setBackground(new Color(255, 255, 224));
		btnlatefour.setFont(new Font("�޸յձ�������", Font.PLAIN, 18));
		btnlatefour.setBounds(275, 358, 205, 31);
		fourstwopage.add(btnlatefour);
		
		//�ð��� �����ϴ� â���� ǥ�õǴ� �ʱ�ȭ������ ��ư
		JButton btngofirstlatetime = new JButton("\uBA54\uB274\uD654\uBA74\uC73C\uB85C ");
		btngofirstlatetime.setBackground(new Color(255, 255, 224));
		btngofirstlatetime.setFont(new Font("�޸յձ�������", Font.PLAIN, 13));
		btngofirstlatetime.setBounds(590, 455, 131, 32);
		fourstwopage.add(btngofirstlatetime);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("background.JPG"));
		lblNewLabel.setBounds(0, 0, 786, 528);
		fourstwopage.add(lblNewLabel);
		fourstwopage.setVisible(false);
		
		//������ �ݳ��� ������ �ݳ� ��ư�� ������ �� �ݳ� �Ϸ� �޽����� ����ִ� â 
		JPanel foursonepage = new JPanel();
		foursonepage.setBounds(0, 0, 786, 528);
		frame.getContentPane().add(foursonepage);
		foursonepage.setLayout(null);
		
		//'�ݳ��� �Ϸ�Ǿ����ϴ�'�� ǥ�����ִ� �ؽ�Ʈ 
		JLabel reok = new JLabel("\uBC18\uB0A9\uC774 \uC644\uB8CC\uB418\uC5C8\uC2B5\uB2C8\uB2E4");
		reok.setHorizontalAlignment(SwingConstants.CENTER);
		reok.setBounds(150, 75, 495, 77);
		reok.setFont(new Font("�޸յձ�������", Font.PLAIN, 35));
		foursonepage.add(reok);
		
		//'�̿����ּż� �����մϴ�'�� ǥ�����ִ� �ؽ�Ʈ 
		JLabel useok = new JLabel("\uC774\uC6A9\uD574\uC8FC\uC154\uC11C \uAC10\uC0AC\uD569\uB2C8\uB2E4 ");
		useok.setHorizontalAlignment(SwingConstants.CENTER);
		useok.setBounds(185, 149, 442, 77);
		useok.setFont(new Font("�޸յձ�������", Font.PLAIN, 35));
		foursonepage.add(useok);
		
		//�ݳ��� �Ϸ�Ǿ��ٴ� �ȳ� �޽����� ����� â���� ǥ�õǴ� �ʱ�ȭ������ ��ư
		JButton btngofirstreok = new JButton("\uBA54\uC778\uD654\uBA74\uC73C\uB85C ");
		btngofirstreok.setBackground(new Color(255, 255, 224));
		btngofirstreok.setBounds(590, 261, 131, 32);
		btngofirstreok.setFont(new Font("�޸յձ�������", Font.PLAIN, 13));
		foursonepage.add(btngofirstreok);
		
		JLabel backgoundfoursone = new JLabel("New label");
		backgoundfoursone.setIcon(new ImageIcon("background.JPG"));
		backgoundfoursone.setBounds(0, 0, 786, 528);
		foursonepage.add(backgoundfoursone);
		foursonepage.setVisible(false);
		foursonepage.setVisible(false);
		
		//2��° ���������� ������ �ݳ��� ������ �� �ݳ� or �ð������� �����ϴ� â 
		JPanel fourspage = new JPanel();
		fourspage.setBounds(0, 0, 786, 528);
		frame.getContentPane().add(fourspage);
		fourspage.setLayout(null);
		
		//�ݳ� ���� ��ư 
		JButton btnrebycicle = new JButton("\uBC18\uB0A9");
		btnrebycicle.setBackground(new Color(255, 255, 224));
		btnrebycicle.setFont(new Font("�޸յձ�������", Font.PLAIN, 25));
		btnrebycicle.setBounds(149, 81, 178, 110);
		fourspage.add(btnrebycicle);
		
		//�ð� ���� ���� ��ư 
		JButton btnlatetime = new JButton("\uC2DC\uAC04\uC5F0\uC7A5");
		btnlatetime.setBackground(new Color(255, 255, 224));
		btnlatetime.setFont(new Font("�޸յձ�������", Font.PLAIN, 25));
		btnlatetime.setBounds(448, 81, 178, 110);
		fourspage.add(btnlatetime);
		
		//�ݳ��� �ð����� �� �����ϴ� â���� ǥ�õǴ� �ʱ�ȭ������ ��ư
		JButton btngofirstreorlate = new JButton("\uBA54\uC778\uD654\uBA74\uC73C\uB85C ");
		btngofirstreorlate.setBackground(new Color(255, 255, 224));
		btngofirstreorlate.setFont(new Font("�޸յձ�������", Font.PLAIN, 13));
		btngofirstreorlate.setBounds(590, 261, 131, 32);
		fourspage.add(btngofirstreorlate);
		
		JLabel backgroundfours = new JLabel("New label");
		backgroundfours.setIcon(new ImageIcon("background.JPG"));
		backgroundfours.setBounds(0, 0, 791, 528);
		fourspage.add(backgroundfours);
		
		//���̽� �������� �󼼼��� â 
		JPanel thirdfourpage = new JPanel();
		thirdfourpage.setBounds(0, 0, 786, 528);
		frame.getContentPane().add(thirdfourpage);
		thirdfourpage.setLayout(null);
		
		//���̽� �������� �̸��� ǥ�����ִ� �ؽ�Ʈ 
		JLabel foldingname = new JLabel("\uC774\uB984 : \uC288\uD37C \uD3F4\uB529 \uC0AC\uC774\uD074");
		foldingname.setBounds(281, 295, 269, 58);
		foldingname.setFont(new Font("�޸յձ�������", Font.PLAIN, 20));
		thirdfourpage.add(foldingname);
		
		//���̽� �������� Ư¡�� ǥ�����ִ� �ؽ�Ʈ 
		JLabel foldingcharacteristic = new JLabel("\uD2B9\uC9D5 : \uC811\uC774\uC2DD \uC790\uC804\uAC70\uB85C \uD734\uB300\uAC00 \uAC04\uD3B8\uD568");
		foldingcharacteristic.setBounds(232, 363, 369, 44);
		foldingcharacteristic.setFont(new Font("�޸յձ�������", Font.PLAIN, 20));
		thirdfourpage.add(foldingcharacteristic);
		
		//���̽� �������� �뿩�ϱ� ��ư 
		JButton btnlendfolding = new JButton("\uB300\uC5EC\uD558\uAE30");
		btnlendfolding.setBackground(new Color(255, 255, 224));
		btnlendfolding.setBounds(342, 431, 120, 38);
		btnlendfolding.setFont(new Font("�޸յձ�������", Font.PLAIN, 18));
		thirdfourpage.add(btnlendfolding);
		
		//���� �����Ÿ� �󼼺��� â���� ǥ�õǴ� �ʱ�ȭ������ ��ư
		JButton btngofirstfoldingbycicle = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		btngofirstfoldingbycicle.setBackground(new Color(255, 255, 224));
		btngofirstfoldingbycicle.setBounds(590, 455, 131, 32);
		btngofirstfoldingbycicle.setFont(new Font("�޸յձ�������", Font.PLAIN, 13));
		thirdfourpage.add(btngofirstfoldingbycicle);
		
		JLabel foldigimg = new JLabel("");
		foldigimg.setBounds(216, 53, 334, 225);
		foldigimg.setIcon(new ImageIcon("okfoldingbycicle.png"));
		thirdfourpage.add(foldigimg);
		
		JLabel backgroundthirdfour = new JLabel("New label");
		backgroundthirdfour.setIcon(new ImageIcon("background2.JPG"));
		backgroundthirdfour.setBounds(0, 0, 785, 528);
		thirdfourpage.add(backgroundthirdfour);
		
		//��ǿ� �������� �󼼼��� â
		JPanel thirdthirdpage = new JPanel();
		thirdthirdpage.setBounds(0, 0, 786, 538);
		frame.getContentPane().add(thirdthirdpage);
		thirdthirdpage.setLayout(null);
		
		//��ǿ� �������� �̸��� ǥ�����ִ� �ؽ�Ʈ 
		JLabel mountainname = new JLabel("\uC774\uB984 : \uC288\uD37C \uB9C8\uC6B4\uD2F4 \uC0AC\uC774\uD074");
		mountainname.setFont(new Font("�޸յձ�������", Font.PLAIN, 20));
		mountainname.setBounds(267, 302, 269, 58);
		thirdthirdpage.add(mountainname);
		
		//��ǿ� �������� Ư¡�� ǥ�����ִ� �ؽ�Ʈ 
		JLabel mountaincharacterictic = new JLabel("\uD2B9\uC9D5 : \uC0B0\uC545\uC6A9 \uC790\uC804\uAC70\uB85C \uC790\uC804\uAC70\uB85C \uC0B0\uC744 \uD0C8 \uC218 \uC788\uC74C");
		mountaincharacterictic.setFont(new Font("�޸յձ�������", Font.PLAIN, 20));
		mountaincharacterictic.setBounds(167, 370, 593, 44);
		thirdthirdpage.add(mountaincharacterictic);
		
		//��ǿ� �������� �뿩�ϱ� ��ư 
		JButton btnlendmountain = new JButton("\uB300\uC5EC\uD558\uAE30");
		btnlendmountain.setBackground(new Color(255, 255, 224));
		btnlendmountain.setFont(new Font("�޸յձ�������", Font.PLAIN, 18));
		btnlendmountain.setBounds(330, 436, 120, 38);
		thirdthirdpage.add(btnlendmountain);
		
		//��ǿ� ������ �󼼺��� â���� ǥ�õǴ� �ʱ�ȭ������ ��ư
		JButton btngofirstmountainbycicle = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		btngofirstmountainbycicle.setBackground(new Color(255, 255, 224));
		btngofirstmountainbycicle.setFont(new Font("�޸յձ�������", Font.PLAIN, 13));
		btngofirstmountainbycicle.setBounds(590, 455, 131, 32);
		thirdthirdpage.add(btngofirstmountainbycicle);
		
		JLabel mountainimg = new JLabel("");
		mountainimg.setIcon(new ImageIcon("okmountainbycicle.png"));
		mountainimg.setBounds(213, 72, 334, 224);
		thirdthirdpage.add(mountainimg);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("background2.JPG"));
		lblNewLabel_2.setBounds(0, 0, 786, 528);
		thirdthirdpage.add(lblNewLabel_2);
		
		//2�ο� �������� �󼼼��� â
		JPanel thirdtwopage = new JPanel();
		thirdtwopage.setBounds(0, 10, 786, 528);
		frame.getContentPane().add(thirdtwopage);
		thirdtwopage.setLayout(null);
		
		//2�ο� �������� �̸��� ǥ�����ִ� �ؽ�Ʈ
		JLabel twopeoplename = new JLabel("\uC774\uB984 : \uC288\uD37C \uD22C\uD53C\uD50C \uC0AC\uC774\uD074");
		twopeoplename.setFont(new Font("�޸յձ�������", Font.PLAIN, 20));
		twopeoplename.setBounds(270, 288, 269, 58);
		thirdtwopage.add(twopeoplename);
		
		//2�ο� �������� Ư¡�� ǥ�����ִ� �ؽ�Ʈ 
		JLabel twopeoplecharacteristic = new JLabel("\uD2B9\uC9D5 : 2\uC778\uC6A9 \uC790\uC804\uAC70\uB85C \uC5F0\uC778\uC774\uB098 \uAC00\uC871, \uCE5C\uAD6C\uB4E4\uB07C\uB9AC \uD0C8 \uC218 \uC788\uC74C");
		twopeoplecharacteristic.setFont(new Font("�޸յձ�������", Font.PLAIN, 20));
		twopeoplecharacteristic.setBounds(114, 366, 593, 44);
		thirdtwopage.add(twopeoplecharacteristic);
		
		//2�ο� �������� �뿩�ϱ� ��ư 
		JButton btnlendtowpeople = new JButton("\uB300\uC5EC\uD558\uAE30");
		btnlendtowpeople.setBackground(new Color(255, 255, 224));
		btnlendtowpeople.setFont(new Font("�޸յձ�������", Font.PLAIN, 18));
		btnlendtowpeople.setBounds(347, 431, 120, 38);
		thirdtwopage.add(btnlendtowpeople);
		
		//2�ο� ������ �󼼺��� â���� ǥ�õǴ� �ʱ�ȭ������ ��ư
		JButton btngofirsttwobycicle = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		btngofirsttwobycicle.setBackground(new Color(255, 255, 224));
		btngofirsttwobycicle.setFont(new Font("�޸յձ�������", Font.PLAIN, 13));
		btngofirsttwobycicle.setBounds(590, 455, 131, 32);
		thirdtwopage.add(btngofirsttwobycicle);
		
		JLabel twopeopleimg = new JLabel("");
		twopeopleimg.setIcon(new ImageIcon("oktwopeoplebycicle.png"));
		twopeopleimg.setBounds(232, 56, 334, 225);
		thirdtwopage.add(twopeopleimg);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon("background2.JPG"));
		lblNewLabel_3.setBounds(0, 0, 789, 518);
		thirdtwopage.add(lblNewLabel_3);
		
		//�Ϲ� �������� �󼼼��� â
		JPanel thirdonepage = new JPanel();
		thirdonepage.setBounds(0, 0, 786, 528);
		frame.getContentPane().add(thirdonepage);
		thirdonepage.setLayout(null);
		
		//�Ϲ� �������� �̸��� ǥ�����ִ� �ؽ�Ʈ 
		JLabel normalname = new JLabel("\uC774\uB984 : \uC288\uD37C \uB178\uBA40 \uC0AC\uC774\uD074");
		normalname.setBounds(306, 283, 242, 58);
		normalname.setFont(new Font("�޸յձ�������", Font.PLAIN, 20));
		thirdonepage.add(normalname);
		
		//�Ϲ� �������� Ư¡�� ǥ�����ִ� �ؽ�Ʈ 
		JLabel normalcharacteristic = new JLabel("\uD2B9\uC9D5 : \uC77C\uBC18 \uC790\uC804\uAC70\uB85C \uD3B8\uD788 \uC0AC\uC6A9\uD560 \uC218 \uC788\uC74C");
		normalcharacteristic.setBounds(227, 351, 420, 44);
		normalcharacteristic.setFont(new Font("�޸յձ�������", Font.PLAIN, 20));
		thirdonepage.add(normalcharacteristic);
		
		//�Ϲ� �������� �뿩�ϱ� ��ư
		JButton btnlendnormal = new JButton("\uB300\uC5EC\uD558\uAE30");
		btnlendnormal.setBackground(new Color(255, 255, 224));
		btnlendnormal.setBounds(357, 421, 120, 38);
		btnlendnormal.setFont(new Font("�޸յձ�������", Font.PLAIN, 18));
		thirdonepage.add(btnlendnormal);
		
		//�Ϲ� ������ �󼼺��� â���� ǥ�õǴ� �ʱ�ȭ������ ��ư
		JButton btngofirstnormal = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		btngofirstnormal.setBackground(new Color(255, 255, 224));
		btngofirstnormal.setFont(new Font("�޸յձ�������", Font.PLAIN, 13));
		btngofirstnormal.setBounds(590, 455, 131, 32);
		thirdonepage.add(btngofirstnormal);
		
		JLabel normalimg = new JLabel("");
		normalimg.setIcon(new ImageIcon("oknormalbycicle.png"));
		normalimg.setBounds(240, 54, 334, 225);
		thirdonepage.add(normalimg);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon("background2.JPG"));
		lblNewLabel_4.setBounds(0, 0, 786, 528);
		thirdonepage.add(lblNewLabel_4);
		thirdonepage.setVisible(false);			
		
		//������ �뿩�ϱ⸦ ������ �Ѿ�� ȭ�� â 
		JPanel thirdpage = new JPanel();
		thirdpage.setBounds(0, 0, 786, 528);
		frame.getContentPane().add(thirdpage);
		thirdpage.setLayout(null);
		
		//'������ ����'�� ǥ�����ִ� �ؽ�Ʈ 
		JLabel choicebycicle = new JLabel("\uC790\uC804\uAC70 \uC120\uD0DD");
		choicebycicle.setFont(new Font("�޸յձ�������", Font.PLAIN, 40));
		choicebycicle.setBounds(279, 52, 220, 54);
		thirdpage.add(choicebycicle);
		
		//'���� ��� ����Ŭ'�� ǥ�����ִ� �ؽ�Ʈ 
		JLabel normal = new JLabel("\uC288\uD37C \uB178\uBA40 \uC0AC\uC774\uD074");
		normal.setHorizontalAlignment(SwingConstants.CENTER);
		normal.setFont(new Font("�޸յձ�������", Font.PLAIN, 25));
		normal.setBounds(94, 131, 207, 124);
		thirdpage.add(normal);
		
		//�Ϲ� �������� �󼼺��� ��ư 
		JButton btnnormal = new JButton("\uC0C1\uC138\uBCF4\uAE30");
		btnnormal.setBackground(new Color(255, 255, 224));
		btnnormal.setFont(new Font("�޸յձ�������", Font.PLAIN, 12));
		btnnormal.setBounds(153, 230, 91, 31);
		thirdpage.add(btnnormal);
		btnnormal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bike = "normal";
			}
		});
		
		//'���� ������ ����Ŭ'�� ǥ�����ִ� �ؽ�Ʈ 
		JLabel twopeople = new JLabel("\uC288\uD37C \uD22C\uD53C\uD50C \uC0AC\uC774\uD074");
		twopeople.setHorizontalAlignment(SwingConstants.CENTER);
		twopeople.setFont(new Font("�޸յձ�������", Font.PLAIN, 25));
		twopeople.setBounds(437, 132, 239, 123);
		thirdpage.add(twopeople);
		
		//2�ο� �������� �󼼺��� ��ư 
		JButton btntwopeople = new JButton("\uC0C1\uC138\uBCF4\uAE30");
		btntwopeople.setBackground(new Color(255, 255, 224));
		btntwopeople.setFont(new Font("�޸յձ�������", Font.PLAIN, 12));
		btntwopeople.setBounds(509, 230, 91, 31);
		thirdpage.add(btntwopeople);
		btntwopeople.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bike = "twoPeople";
			}
		});
		
		//'���� ����ƾ ����Ŭ'�� ǥ�����ִ� �ؽ�Ʈ 
		JLabel mountain = new JLabel("\uC288\uD37C \uB9C8\uC6B4\uD2F4 \uC0AC\uC774\uD074");
		mountain.setHorizontalAlignment(SwingConstants.CENTER);
		mountain.setFont(new Font("�޸յձ�������", Font.PLAIN, 25));
		mountain.setBounds(86, 286, 233, 106);
		thirdpage.add(mountain);
		
		//��ǿ� ������ �󼼺��� ��ư  
		JButton btnmountain = new JButton("\uC0C1\uC138\uBCF4\uAE30");
		btnmountain.setBackground(new Color(255, 255, 224));
		btnmountain.setFont(new Font("�޸յձ�������", Font.PLAIN, 12));
		btnmountain.setBounds(153, 379, 91, 31);
		thirdpage.add(btnmountain);
		btnmountain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bike = "mountain";
			}
		});
		
		//'���� ���� ����Ŭ'�� ǥ�����ִ� �ؽ�Ʈ 
		JLabel folding = new JLabel("\uC288\uD37C \uD3F4\uB529 \uC0AC\uC774\uD074");
		folding.setHorizontalAlignment(SwingConstants.CENTER);
		folding.setFont(new Font("�޸յձ�������", Font.PLAIN, 25));
		folding.setBounds(447, 278, 207, 123);
		thirdpage.add(folding);
		
		//���̽� �������� �󼼺��� ��ư 
		JButton btnfolding = new JButton("\uC0C1\uC138\uBCF4\uAE30");
		btnfolding.setBackground(new Color(255, 255, 224));
		btnfolding.setFont(new Font("�޸յձ�������", Font.PLAIN, 12));
		btnfolding.setBounds(509, 379, 91, 31);
		thirdpage.add(btnfolding);
		btnfolding.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bike = "folding";
			}
		});
		
		//������ �޴� â���� ǥ�õǴ� �ʱ�ȭ������ ��ư
		JButton btngofirstchoicebycicle = new JButton("\uBA54\uC778\uD654\uBA74\uC73C\uB85C ");
		btngofirstchoicebycicle.setBackground(new Color(255, 255, 224));
		btngofirstchoicebycicle.setHorizontalAlignment(SwingConstants.RIGHT);
		btngofirstchoicebycicle.setFont(new Font("�޸յձ�������", Font.PLAIN, 13));
		btngofirstchoicebycicle.setBounds(590, 455, 131, 32);
		thirdpage.add(btngofirstchoicebycicle);
		
		JLabel backgroundthird = new JLabel("");
		backgroundthird.setIcon(new ImageIcon("background.JPG"));// lblNewLabel
		backgroundthird.setBounds(0, 0, 786, 527);
		thirdpage.add(backgroundthird);
		
		//2��° ������ â 
		JPanel seconedpage = new JPanel();
		seconedpage.setBounds(0, 0, 786, 528);
		frame.getContentPane().add(seconedpage);
		seconedpage.setLayout(null);
		
		//2��° �������� ������ �뿩 ��ư 
		JButton btn1 = new JButton("\uC790\uC804\uAC70 \uB300\uC5EC");
		btn1.setBackground(new Color(255, 255, 224));
		btn1.setFont(new Font("�޸յձ�������", Font.PLAIN, 17));
		btn1.setBounds(157, 209, 173, 74);
		seconedpage.add(btn1);
		
		//2��° �������� ������ �ݳ� ��ư 
		JButton btn2 = new JButton("\uC790\uC804\uAC70 \uBC18\uB0A9");
		btn2.setBackground(new Color(255, 255, 224));
		btn2.setFont(new Font("�޸յձ�������", Font.PLAIN, 17));
		btn2.setBounds(430, 209, 173, 74);
		seconedpage.add(btn2);
		
		//2��° �������� ���� �ð� Ȯ���ϱ� ��ư 
		JButton btn3 = new JButton("\uBC18\uB0A9 \uC2DC\uAC04 \uD655\uC778");
		btn3.setBackground(new Color(255, 255, 224));
		btn3.setFont(new Font("�޸յձ�������", Font.PLAIN, 17));
		btn3.setBounds(287, 326, 187, 74);
		seconedpage.add(btn3);
		
		//choice�� ǥ�����ִ� �ؽ�Ʈ 
		JLabel choice = new JLabel("Choice");
		choice.setHorizontalAlignment(SwingConstants.CENTER);
		choice.setFont(new Font("�޸յձ�������", Font.PLAIN, 35));
		choice.setBounds(235, 77, 295, 74);
		seconedpage.add(choice);
		
		//2��° �������� ��� ��ư 
		JButton btncancle2 = new JButton("\uC885\uB8CC ");
		btncancle2.setBackground(new Color(255, 255, 224));
		btncancle2.setFont(new Font("�޸յձ�������", Font.PLAIN, 15));
		btncancle2.setBounds(649, 460, 91, 27);
		seconedpage.add(btncancle2);
		
		JLabel backgroundseconed = new JLabel("");
		backgroundseconed.setIcon(new ImageIcon("background.JPG"));
		backgroundseconed.setBounds(0, 0, 786, 518);
		seconedpage.add(backgroundseconed);
		
		// ó���� ǥ�õ��� �ʴ� â
		seconedpage.setVisible(false);
		seconedpage.setVisible(false);
		
		//1��° ������ â 
		JPanel firstpage = new JPanel();
		firstpage.setBounds(0, 0, 786, 528);
		frame.getContentPane().add(firstpage);
		firstpage.setLayout(null);
		
		//LogIn�� ǥ�����ִ� �ؽ�Ʈ 
		JLabel login = new JLabel("LogIn");
		login.setHorizontalAlignment(SwingConstants.CENTER);
		login.setBounds(314, 118, 146, 49);
		login.setFont(new Font("�޸յձ�������", Font.PLAIN, 40));
		firstpage.add(login);
		
		//�̸��� �Է¹޴� ĭ 
		nametextfield = new JTextField();
		nametextfield.setBounds(297, 210, 163, 26);
		firstpage.add(nametextfield);
		nametextfield.setColumns(10);
		
		//�̸� �Է� ĭ �տ� '�̸�'�� ǥ�����ִ� �ؽ�Ʈ 
		JLabel username = new JLabel("\uC774\uB984");
		username.setHorizontalAlignment(SwingConstants.CENTER);
		username.setBounds(229, 212, 50, 21);
		username.setFont(new Font("�޸յձ�������", Font.PLAIN, 18));
		firstpage.add(username);
		
		//��ȣ �Է� ĭ �տ� '��ȭ��ȣ'�� ǥ�����ִ� �ؽ�Ʈ 
		JLabel phonenum = new JLabel("\uC804\uD654\uBC88\uD638");
		phonenum.setHorizontalAlignment(SwingConstants.CENTER);
		phonenum.setBounds(212, 265, 69, 31);
		phonenum.setFont(new Font("�޸յձ�������", Font.PLAIN, 15));
		firstpage.add(phonenum);
		
		//��ȭ��ȣ�� �Է¹޴� ĭ 
		numtextfield = new JTextField();
		numtextfield.setBounds(297, 267, 163, 26);
		firstpage.add(numtextfield);
		numtextfield.setColumns(10);
		
		//1��° �������� ��� ��ư 
		JButton btncancle = new JButton("\uC885\uB8CC ");
		btncancle.setBackground(new Color(255, 255, 224));
		btncancle.setBounds(492, 275, 91, 23);
		btncancle.setFont(new Font("�޸յձ�������", Font.PLAIN, 12));
		firstpage.add(btncancle);
		
		//1��° �������� �α��� ��ư 
		JButton btnlogin = new JButton("\uB85C\uADF8\uC778");
		btnlogin.setBackground(new Color(255, 255, 224));
		btnlogin.setFont(new Font("�޸յձ�������", Font.PLAIN, 12));
		btnlogin.setBounds(492, 202, 91, 61);
		firstpage.add(btnlogin);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("background.JPG"));
		lblNewLabel_1.setBounds(0, 0, 794, 528);
		firstpage.add(lblNewLabel_1);
		
				// ������ ����&�뿩 �ð� ���� �� �뿩�ϱ� ��ư ������ �߻��ϴ� �׼�
				btngofirstchoicetime.addActionListener(new ActionListener() {
		
					@Override
					public void actionPerformed(ActionEvent e) {
						timechoicepage.setVisible(false);
						lendokpage.setVisible(true);
						try {
							customer.errorCustomer(nametextfield.getText(), numtextfield.getText(), time, customer.now, bike, num); //�����ִ��������� ��ģ���� ������
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
					}
				});
				timechoicepage.setVisible(false);
		
		//ù ��° ȭ�鿡�� ��� ��ư�� ������ ���α׷��� ����Ǵ� �׼�
		btncancle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		// �α��� ��ư�� ������ ���� �޽����� �߰��ϴ� â
		btnlogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if ((nametextfield.getText().length() == 0) || (numtextfield.getText().length() == 0)) {
					JOptionPane.showMessageDialog(null, ("�̸��� ��ȭ��ȣ�� �Է����ּ��� "));
					firstpage.setVisible(true);
					seconedpage.setVisible(false);
				} else {
					seconedpage.setVisible(true);
					firstpage.setVisible(false);
				}
			}
		});

		// ���� ���� �ð��� Ȯ���ϴ� ���������� �ʱ�ȭ������ ��ư�� ������ 2��° ȭ������ ���ư��� �׼�
		btngofirsttime.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				seconedpage.setVisible(true);
				fivepage.setVisible(false);
			}
		});

		// ���� �ð� ���� ���������� �ʱ�ȭ������ ��ư�� ������ 2��° ȭ������ ���ư��� �׼�
		btngofirstlatetime.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				seconedpage.setVisible(true);
				fourstwopage.setVisible(false);
			}
		});

		// �ð� ���� ���������� 1�ð� �����ϱ� ��ư�� ������ ����Ǿ����ϴ� ��� �޽����� �ߴ� â���� �Ѿ���� �׼�
		btnlateone.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fourstwolatepage.setVisible(true);
				fourstwopage.setVisible(false);
				lateTime = 1;
				customer.lateTime(lateTime, numtextfield.getText());
			}
		});

		// �ð� ���� ���������� 2�ð� �����ϱ� ��ư�� ������ ����Ǿ����ϴ� ��� �޽����� �ߴ� â���� �Ѿ���� �׼�
		btnlatetwo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fourstwolatepage.setVisible(true);
				fourstwopage.setVisible(false);
				lateTime = 2;
				customer.lateTime(lateTime, numtextfield.getText());
			}
		});

		// �ð� ���� ���������� 3�ð� �����ϱ� ��ư�� ������ ����Ǿ����ϴ� ��� �޽����� �ߴ� â���� �Ѿ���� �׼�
		btnlatethird.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fourstwolatepage.setVisible(true);
				fourstwopage.setVisible(false);
				lateTime = 3;
				customer.lateTime(lateTime, numtextfield.getText());
			}
		});

		// �ð� ���� ���������� 4�ð� �����ϱ� ��ư�� ������ ����Ǿ����ϴ� ��� �޽����� �ߴ� â���� �Ѿ���� �׼�
		btnlatefour.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fourstwolatepage.setVisible(true);
				fourstwopage.setVisible(false);
				lateTime = 4;
				customer.lateTime(lateTime, numtextfield.getText()); 
			}
		});

		// �ݳ��� �Ϸ�Ǿ��ٴ� �ȳ� �޽����� ����� ���������� �ʱ�ȭ������ ��ư�� ������ 2��° ȭ������ ���ư��� �׼�
		btngofirstreok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				seconedpage.setVisible(true);
				foursonepage.setVisible(false);
			}
		});

		// �ݳ� ��ư�� ������ �ݳ��� �Ϸ�Ǿ��ٴ� �޽����� ������� �������� �Ѿ�� �׼�(������ �ݳ� �׼�)
		btnrebycicle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				foursonepage.setVisible(true);
				fourspage.setVisible(false);
				customer.deleteCustomer(numtextfield.getText(), bike);
			}
		});

		// �ð� ���� ��ư�� ������ �ð� ���� �������� �Ѿ�� �׼�
		btnlatetime.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fourstwopage.setVisible(true);
				fourspage.setVisible(false);
			}
		});

		// �ݳ��� �ð������� �����ϴ� ���������� �ʱ�ȭ������ ��ư�� ������ 2��° ȭ������ ���ư��� �׼�
		btngofirstreorlate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				seconedpage.setVisible(true);
				fourspage.setVisible(false);
			}
		});

		// ���� ���� ����Ŭ �󼼺��⸦ ���� �� �뿩�ϱ⸦ ������ �ð� ���� �������� �Ѿ�� �׼�
		btnlendfolding.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timechoicepage.setVisible(true);
				thirdfourpage.setVisible(false);
			}
		});

		// ���� ���� ����Ŭ �󼼼��� ���������� �ʱ�ȭ������ ��ư�� ������ 2��° ȭ������ ���ư��� �׼�
		btngofirstfoldingbycicle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thirdpage.setVisible(true);
				thirdfourpage.setVisible(false);
			}
		});

		// ���� ����ƾ ����Ŭ �󼼺��⸦ ���� �� �뿩�ϱ⸦ ������ �ð� ���� �������� �Ѿ�� �׼�
		btnlendmountain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timechoicepage.setVisible(true);
				thirdthirdpage.setVisible(false);
			}
		});

		// ���� ����ƾ ����Ŭ �󼼼��� ���������� �ʱ�ȭ������ ��ư�� ������ 2��° ȭ������ ���ư��� �׼�
		btngofirstmountainbycicle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thirdpage.setVisible(true);
				thirdthirdpage.setVisible(false);
			}
		});

		// ���� ������ ����Ŭ �󼼺��⸦ ���� �� �뿩�ϱ⸦ ������ �ð� ���� �������� �Ѿ�� �׼�
		btnlendtowpeople.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timechoicepage.setVisible(true);
				thirdtwopage.setVisible(false);
			}
		});

		// ���� ������ ����Ŭ �󼼼��� ���������� �ʱ�ȭ������ ��ư�� ������ 2��° ȭ������ ���ư��� �׼�
		btngofirsttwobycicle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thirdpage.setVisible(true);
				thirdtwopage.setVisible(false);
			}
		});

		// ���� ��� ����Ŭ �󼼺��⸦ ���� �� �뿩�ϱ⸦ ������ �ð� ���� �������� �Ѿ�� �׼�
		btnlendnormal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timechoicepage.setVisible(true);
				thirdonepage.setVisible(false);
			}
		});

		// ���� ��� ����Ŭ �󼼼��� ���������� �ʱ�ȭ������ ��ư�� ������ 2��° ȭ������ ���ư��� �׼�
		btngofirstnormal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thirdpage.setVisible(true);
				thirdonepage.setVisible(false);
			}
		});

		// ���� ��� ����Ŭ �󼼺��� ��ư�� ������ �̸��� Ư¡�� ������ �������� �Ѿ�� �׼�
		btnnormal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thirdonepage.setVisible(true);
				thirdpage.setVisible(false);
			}
		});

		// ���� ������ ����Ŭ �󼼺��� ��ư�� ������ �̸��� Ư¡�� ������ �������� �Ѿ�� �׼�
		btntwopeople.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thirdtwopage.setVisible(true);
				thirdpage.setVisible(false);
			}
		});

		// ���� ����ƾ ����Ŭ �󼼺��� ��ư�� ������ �̸��� Ư¡�� ������ �������� �Ѿ�� �׼�
		btnmountain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thirdthirdpage.setVisible(true);
				thirdpage.setVisible(false);
			}
		});

		// ���� ���� ����Ŭ �󼼺��� ��ư�� ������ �̸��� Ư¡�� ������ �������� �Ѿ�� �׼�
		btnfolding.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thirdfourpage.setVisible(true);
				thirdpage.setVisible(false);
			}
		});

		// ������ ���� ���������� �ʱ�ȭ������ ��ư�� ������ 2��° ȭ������ ���ư��� �׼�
		btngofirstchoicebycicle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				seconedpage.setVisible(true);
				thirdpage.setVisible(false);
			}
		});

		// �� ��° ȭ�鿡�� ��� ��ư�� ������ ���α׷��� ����Ǵ� �׼�
		btncancle2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// ������ �뿩 ��ư�� ������ ������ �뿩 �������� �Ѿ�� �׼�
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thirdpage.setVisible(true);
				seconedpage.setVisible(false);
			}
		});

		
		
		// ������ �ݳ� ��ư�� ������ ������ �ݳ� �������� �Ѿ�� �׼�
		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fourspage.setVisible(true);
				seconedpage.setVisible(false);
				foursonepage.setVisible(false);

			}
		});

		// ���� �ð� Ȯ�� ��ư�� ������ ���� �ð��� Ȯ�ν����ִ� �������� �Ѿ�� �׼�
		btn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fivepage.setVisible(true);
				seconedpage.setVisible(false);
				fourspage.setVisible(false);
			}
		});
		
		//������ �뿩�� �Ϸ�Ǿ��ٴ� �޽����� ����ִ� ���������� �ݱ� ��ư�� ������ ���α׷��� �����ϴ� �׼� 
		btngofirstlendok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//�ð��� ����Ǿ��ٴ� ������ �ߴ� â���� �ݱ��ư�� ������ ���α׷��� ����Ǵ� �׼� 
		btnlatetimebye.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//ó�� ����ȭ�鿡�� ǥ�õ��� �ʴ� â 
		seconedpage.setVisible(false);
		thirdpage.setVisible(false);
		fourspage.setVisible(false);
		fivepage.setVisible(false);
		foursonepage.setVisible(false);
		fourstwopage.setVisible(false);
		fourstwolatepage.setVisible(false);
		thirdonepage.setVisible(false);
		thirdonepage.setVisible(false);
		thirdtwopage.setVisible(false);
		thirdthirdpage.setVisible(false);
		thirdfourpage.setVisible(false);
		lendokpage.setVisible(false);
										
	}
}
