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

	int time; //대여 시간
	int lateTime; //연장 시간
	String bike; //대여 자전거 종류
	int num; //대여 개수

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		//table 생성
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
		
		//처음 시간 선택 창
		JPanel timechoicepage = new JPanel();
		timechoicepage.setBounds(0, 0, 786, 528);
		frame.getContentPane().add(timechoicepage);
		timechoicepage.setLayout(null);
		
		JComboBox comboTime = new JComboBox();
		comboTime.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));
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
		lblNewLabel_6.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 35));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(286, 36, 222, 66);
		timechoicepage.add(lblNewLabel_6);
		comboTime.setModel(new DefaultComboBoxModel(new String[] {"1\uC2DC\uAC04", "2\uC2DC\uAC04", "3\uC2DC\uAC04", "4\uC2DC\uAC04"}));
		comboTime.setBounds(448, 205, 89, 32);
		timechoicepage.add(comboTime);
		
		JComboBox comboCount = new JComboBox();
		comboCount.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));
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
		
		JLabel label = new JLabel("개수 선택");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 30));
		label.setBounds(227, 307, 169, 61);
		timechoicepage.add(label);
		
		JLabel label_1 = new JLabel("\uC2DC\uAC04 \uC120\uD0DD");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 30));
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
		button.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));*/
		
		//처음 시간을 선택하는 창에서 표시되는 초기화면으로 버튼
		JButton btngofirstchoicetime = new JButton("\uB300\uC5EC\uD558\uAE30");
		btngofirstchoicetime.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
		btngofirstchoicetime.setBounds(590, 455, 131, 32);
		timechoicepage.add(btngofirstchoicetime);
		
		JLabel backgroundtimechoice = new JLabel("");
		backgroundtimechoice.setIcon(new ImageIcon("background.JPG"));
		backgroundtimechoice.setBounds(0, 0, 786, 528);
		timechoicepage.add(backgroundtimechoice);
		
		//자전거 대여가 완료되었습니다를 표시해주는 창 
		JPanel lendokpage = new JPanel();
		lendokpage.setBounds(0, 0, 786, 528);
		frame.getContentPane().add(lendokpage);
		lendokpage.setLayout(null);
		
		//자전거 대여가 완료되었습니다 라는 문구가 표시되는 텍스트 
		JLabel lendok = new JLabel("\uC790\uC804\uAC70 \uB300\uC5EC\uAC00 \uC644\uB8CC\uB418\uC5C8\uC2B5\uB2C8\uB2E4!");
		lendok.setBounds(112, 87, 583, 148);
		lendok.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 40));
		lendokpage.add(lendok);
		
		//자전거 대여 페이지의 프로그램 종료 버튼 
		JButton btngofirstlendok = new JButton("\uB2EB\uAE30");
		btngofirstlendok.setBackground(new Color(255, 255, 224));
		btngofirstlendok.setBounds(590, 261, 109, 32);
		btngofirstlendok.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));
		lendokpage.add(btngofirstlendok);
		
		JLabel backgroundlendok = new JLabel("New label");
		backgroundlendok.setIcon(new ImageIcon("background.JPG"));
		backgroundlendok.setBounds(0, 0, 785, 528);
		lendokpage.add(backgroundlendok);
		lendokpage.setVisible(false);
		
		//2번째 페이지에서 남은 시간 확인 버튼을 눌렀을 때 뜨는 창 
		JPanel fivepage = new JPanel();
		fivepage.setBounds(0, 0, 786, 528);
		frame.getContentPane().add(fivepage);
		fivepage.setLayout(null);
		frame.setBounds(100, 100, 800, 565);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Login");
		
		// db 테이블
		String[][] dt = customer.getCustomers();
		String[] header = new String[] { "Name", "Phone", "Time", "Date" };
		JTable table = new JTable(dt, header);
		table.setRowHeight(30);
		table.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
		table.setLocation(93, 148);
		table.setSize(612, 300);
		table.getColumnModel().getColumn(0).setPreferredWidth(5); //name
		table.getColumnModel().getColumn(1).setPreferredWidth(10); //phone
		table.getColumnModel().getColumn(2).setPreferredWidth(5); //time
		table.getColumnModel().getColumn(3).setPreferredWidth(50); //date

		JLabel lblNewLabel_5 = new JLabel("Name");
		lblNewLabel_5.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(95, 106, 148, 40);
		fivepage.add(lblNewLabel_5);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));
		lblPhone.setBounds(230, 107, 148, 40);
		fivepage.add(lblPhone);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));
		lblTime.setBounds(371, 106, 148, 40);
		fivepage.add(lblTime);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));
		lblDate.setBounds(533, 106, 148, 40);
		fivepage.add(lblDate);
		table.setPreferredScrollableViewportSize(new Dimension(612, 354));
		fivepage.add(table);
		
		//'현재 남은 시간'을 표시해주는 텍스트 
		JLabel checktime = new JLabel("\uD604\uC7AC \uB0A8\uC740 \uC2DC\uAC04");
		checktime.setHorizontalAlignment(SwingConstants.CENTER);
		checktime.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 35));
		checktime.setBounds(217, 12, 335, 74);
		fivepage.add(checktime);
		
		//현재 남은 시간을 표현해주는 창에서 표시되는 초기화면으로 버튼
		JButton btngofirsttime = new JButton("\uBA54\uB274\uD654\uBA74\uC73C\uB85C ");
		btngofirsttime.setBackground(new Color(255, 255, 224));
		btngofirsttime.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
		btngofirsttime.setBounds(590, 455, 131, 32);
		fivepage.add(btngofirsttime);
		
		JLabel backgroundfive = new JLabel("New label");
		backgroundfive.setIcon(new ImageIcon("background.JPG"));
		backgroundfive.setBounds(0, 0, 786, 528);
		fivepage.add(backgroundfive);
		fivepage.setVisible(false);	
	
		//시간이 연장되었습니다를 표시해주는 창 
		JPanel fourstwolatepage = new JPanel();
		fourstwolatepage.setBounds(0, 0, 786, 528);
		frame.getContentPane().add(fourstwolatepage);
		fourstwolatepage.setLayout(null);
		
		//시간이 연장되었습니다 라는 문구가 표시되는 텍스트 
		JLabel latetimeok = new JLabel("\uC2DC\uAC04\uC774 \uC5F0\uC7A5\uB418\uC5C8\uC2B5\uB2C8\uB2E4");
		latetimeok.setBounds(154, 139, 501, 94);
		latetimeok.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 40));
		latetimeok.setHorizontalAlignment(SwingConstants.CENTER);
		fourstwolatepage.add(latetimeok);
		
		//시간 연장 페이지의 프로그램 종료 버튼 
		JButton btnlatetimebye = new JButton("\uB2EB\uAE30");
		btnlatetimebye.setBackground(new Color(255, 255, 224));
		btnlatetimebye.setBounds(590, 455, 112, 34);
		btnlatetimebye.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		fourstwolatepage.add(btnlatetimebye);
		
		JLabel backgroundfourstwo = new JLabel("New label");
		backgroundfourstwo.setIcon(new ImageIcon("background.JPG"));
		backgroundfourstwo.setBounds(0, 0, 786, 528);
		fourstwolatepage.add(backgroundfourstwo);
		
		//시간 연장하는 창 
		JPanel fourstwopage = new JPanel();
		fourstwopage.setBounds(0, 0, 786, 538);
		frame.getContentPane().add(fourstwopage);
		fourstwopage.setLayout(null);
		
		//'연장할 시간 선택'을 표시해주는 텍스트 
		JLabel latetimechoice = new JLabel("\uC5F0\uC7A5\uD560 \uC2DC\uAC04 \uC120\uD0DD");
		latetimechoice.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 35));
		latetimechoice.setBounds(239, 73, 298, 60);
		fourstwopage.add(latetimechoice);
		
		//1시간 연장 버튼 
		JButton btnlateone = new JButton("1\uC2DC\uAC04 \uC5F0\uC7A5");
		btnlateone.setBackground(new Color(255, 255, 224));
		btnlateone.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 18));
		btnlateone.setBounds(275, 177, 205, 31);
		fourstwopage.add(btnlateone);
		
		//2시간 연장 버튼 
		JButton btnlatetwo = new JButton("2\uC2DC\uAC04 \uC5F0\uC7A5");
		btnlatetwo.setBackground(new Color(255, 255, 224));
		btnlatetwo.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 18));
		btnlatetwo.setBounds(275, 235, 205, 31);
		fourstwopage.add(btnlatetwo);
		
		//3시간 연장 버튼 
		JButton btnlatethird = new JButton("3\uC2DC\uAC04 \uC5F0\uC7A5");
		btnlatethird.setBackground(new Color(255, 255, 224));
		btnlatethird.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 18));
		btnlatethird.setBounds(275, 295, 205, 31);
		fourstwopage.add(btnlatethird);
		
		//4시간 연장 버튼 
		JButton btnlatefour = new JButton("4\uC2DC\uAC04 \uC5F0\uC7A5");
		btnlatefour.setBackground(new Color(255, 255, 224));
		btnlatefour.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 18));
		btnlatefour.setBounds(275, 358, 205, 31);
		fourstwopage.add(btnlatefour);
		
		//시간을 연장하는 창에서 표시되는 초기화면으로 버튼
		JButton btngofirstlatetime = new JButton("\uBA54\uB274\uD654\uBA74\uC73C\uB85C ");
		btngofirstlatetime.setBackground(new Color(255, 255, 224));
		btngofirstlatetime.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
		btngofirstlatetime.setBounds(590, 455, 131, 32);
		fourstwopage.add(btngofirstlatetime);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("background.JPG"));
		lblNewLabel.setBounds(0, 0, 786, 528);
		fourstwopage.add(lblNewLabel);
		fourstwopage.setVisible(false);
		
		//자전거 반납을 누르고 반납 버튼을 눌렀을 때 반납 완료 메시지를 띄워주는 창 
		JPanel foursonepage = new JPanel();
		foursonepage.setBounds(0, 0, 786, 528);
		frame.getContentPane().add(foursonepage);
		foursonepage.setLayout(null);
		
		//'반납이 완료되었습니다'를 표시해주는 텍스트 
		JLabel reok = new JLabel("\uBC18\uB0A9\uC774 \uC644\uB8CC\uB418\uC5C8\uC2B5\uB2C8\uB2E4");
		reok.setHorizontalAlignment(SwingConstants.CENTER);
		reok.setBounds(150, 75, 495, 77);
		reok.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 35));
		foursonepage.add(reok);
		
		//'이용해주셔서 감사합니다'를 표시해주는 텍스트 
		JLabel useok = new JLabel("\uC774\uC6A9\uD574\uC8FC\uC154\uC11C \uAC10\uC0AC\uD569\uB2C8\uB2E4 ");
		useok.setHorizontalAlignment(SwingConstants.CENTER);
		useok.setBounds(185, 149, 442, 77);
		useok.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 35));
		foursonepage.add(useok);
		
		//반납이 완료되었다는 안내 메시지가 띄워진 창에서 표시되는 초기화면으로 버튼
		JButton btngofirstreok = new JButton("\uBA54\uC778\uD654\uBA74\uC73C\uB85C ");
		btngofirstreok.setBackground(new Color(255, 255, 224));
		btngofirstreok.setBounds(590, 261, 131, 32);
		btngofirstreok.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
		foursonepage.add(btngofirstreok);
		
		JLabel backgoundfoursone = new JLabel("New label");
		backgoundfoursone.setIcon(new ImageIcon("background.JPG"));
		backgoundfoursone.setBounds(0, 0, 786, 528);
		foursonepage.add(backgoundfoursone);
		foursonepage.setVisible(false);
		foursonepage.setVisible(false);
		
		//2번째 페이지에서 자전거 반납을 눌렀을 때 반납 or 시간연장을 선택하는 창 
		JPanel fourspage = new JPanel();
		fourspage.setBounds(0, 0, 786, 528);
		frame.getContentPane().add(fourspage);
		fourspage.setLayout(null);
		
		//반납 선택 버튼 
		JButton btnrebycicle = new JButton("\uBC18\uB0A9");
		btnrebycicle.setBackground(new Color(255, 255, 224));
		btnrebycicle.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 25));
		btnrebycicle.setBounds(149, 81, 178, 110);
		fourspage.add(btnrebycicle);
		
		//시간 연장 선택 버튼 
		JButton btnlatetime = new JButton("\uC2DC\uAC04\uC5F0\uC7A5");
		btnlatetime.setBackground(new Color(255, 255, 224));
		btnlatetime.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 25));
		btnlatetime.setBounds(448, 81, 178, 110);
		fourspage.add(btnlatetime);
		
		//반납과 시간연장 중 선택하는 창에서 표시되는 초기화면으로 버튼
		JButton btngofirstreorlate = new JButton("\uBA54\uC778\uD654\uBA74\uC73C\uB85C ");
		btngofirstreorlate.setBackground(new Color(255, 255, 224));
		btngofirstreorlate.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
		btngofirstreorlate.setBounds(590, 261, 131, 32);
		fourspage.add(btngofirstreorlate);
		
		JLabel backgroundfours = new JLabel("New label");
		backgroundfours.setIcon(new ImageIcon("background.JPG"));
		backgroundfours.setBounds(0, 0, 791, 528);
		fourspage.add(backgroundfours);
		
		//접이식 자전거의 상세설명 창 
		JPanel thirdfourpage = new JPanel();
		thirdfourpage.setBounds(0, 0, 786, 528);
		frame.getContentPane().add(thirdfourpage);
		thirdfourpage.setLayout(null);
		
		//접이식 자전거의 이름을 표시해주는 텍스트 
		JLabel foldingname = new JLabel("\uC774\uB984 : \uC288\uD37C \uD3F4\uB529 \uC0AC\uC774\uD074");
		foldingname.setBounds(281, 295, 269, 58);
		foldingname.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 20));
		thirdfourpage.add(foldingname);
		
		//접이식 자전거의 특징을 표시해주는 텍스트 
		JLabel foldingcharacteristic = new JLabel("\uD2B9\uC9D5 : \uC811\uC774\uC2DD \uC790\uC804\uAC70\uB85C \uD734\uB300\uAC00 \uAC04\uD3B8\uD568");
		foldingcharacteristic.setBounds(232, 363, 369, 44);
		foldingcharacteristic.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 20));
		thirdfourpage.add(foldingcharacteristic);
		
		//접이식 자전거의 대여하기 버튼 
		JButton btnlendfolding = new JButton("\uB300\uC5EC\uD558\uAE30");
		btnlendfolding.setBackground(new Color(255, 255, 224));
		btnlendfolding.setBounds(342, 431, 120, 38);
		btnlendfolding.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 18));
		thirdfourpage.add(btnlendfolding);
		
		//폴딩 자전거를 상세보기 창에서 표시되는 초기화면으로 버튼
		JButton btngofirstfoldingbycicle = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		btngofirstfoldingbycicle.setBackground(new Color(255, 255, 224));
		btngofirstfoldingbycicle.setBounds(590, 455, 131, 32);
		btngofirstfoldingbycicle.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
		thirdfourpage.add(btngofirstfoldingbycicle);
		
		JLabel foldigimg = new JLabel("");
		foldigimg.setBounds(216, 53, 334, 225);
		foldigimg.setIcon(new ImageIcon("okfoldingbycicle.png"));
		thirdfourpage.add(foldigimg);
		
		JLabel backgroundthirdfour = new JLabel("New label");
		backgroundthirdfour.setIcon(new ImageIcon("background2.JPG"));
		backgroundthirdfour.setBounds(0, 0, 785, 528);
		thirdfourpage.add(backgroundthirdfour);
		
		//산악용 자전거의 상세설명 창
		JPanel thirdthirdpage = new JPanel();
		thirdthirdpage.setBounds(0, 0, 786, 538);
		frame.getContentPane().add(thirdthirdpage);
		thirdthirdpage.setLayout(null);
		
		//산악용 자전거의 이름을 표시해주는 텍스트 
		JLabel mountainname = new JLabel("\uC774\uB984 : \uC288\uD37C \uB9C8\uC6B4\uD2F4 \uC0AC\uC774\uD074");
		mountainname.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 20));
		mountainname.setBounds(267, 302, 269, 58);
		thirdthirdpage.add(mountainname);
		
		//산악용 자전거의 특징을 표시해주는 텍스트 
		JLabel mountaincharacterictic = new JLabel("\uD2B9\uC9D5 : \uC0B0\uC545\uC6A9 \uC790\uC804\uAC70\uB85C \uC790\uC804\uAC70\uB85C \uC0B0\uC744 \uD0C8 \uC218 \uC788\uC74C");
		mountaincharacterictic.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 20));
		mountaincharacterictic.setBounds(167, 370, 593, 44);
		thirdthirdpage.add(mountaincharacterictic);
		
		//산악용 자전거의 대여하기 버튼 
		JButton btnlendmountain = new JButton("\uB300\uC5EC\uD558\uAE30");
		btnlendmountain.setBackground(new Color(255, 255, 224));
		btnlendmountain.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 18));
		btnlendmountain.setBounds(330, 436, 120, 38);
		thirdthirdpage.add(btnlendmountain);
		
		//산악용 자전거 상세보기 창에서 표시되는 초기화면으로 버튼
		JButton btngofirstmountainbycicle = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		btngofirstmountainbycicle.setBackground(new Color(255, 255, 224));
		btngofirstmountainbycicle.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
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
		
		//2인용 자전거의 상세설명 창
		JPanel thirdtwopage = new JPanel();
		thirdtwopage.setBounds(0, 10, 786, 528);
		frame.getContentPane().add(thirdtwopage);
		thirdtwopage.setLayout(null);
		
		//2인용 자전거의 이름을 표시해주는 텍스트
		JLabel twopeoplename = new JLabel("\uC774\uB984 : \uC288\uD37C \uD22C\uD53C\uD50C \uC0AC\uC774\uD074");
		twopeoplename.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 20));
		twopeoplename.setBounds(270, 288, 269, 58);
		thirdtwopage.add(twopeoplename);
		
		//2인용 자전거의 특징을 표시해주는 텍스트 
		JLabel twopeoplecharacteristic = new JLabel("\uD2B9\uC9D5 : 2\uC778\uC6A9 \uC790\uC804\uAC70\uB85C \uC5F0\uC778\uC774\uB098 \uAC00\uC871, \uCE5C\uAD6C\uB4E4\uB07C\uB9AC \uD0C8 \uC218 \uC788\uC74C");
		twopeoplecharacteristic.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 20));
		twopeoplecharacteristic.setBounds(114, 366, 593, 44);
		thirdtwopage.add(twopeoplecharacteristic);
		
		//2인용 자전거의 대여하기 버튼 
		JButton btnlendtowpeople = new JButton("\uB300\uC5EC\uD558\uAE30");
		btnlendtowpeople.setBackground(new Color(255, 255, 224));
		btnlendtowpeople.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 18));
		btnlendtowpeople.setBounds(347, 431, 120, 38);
		thirdtwopage.add(btnlendtowpeople);
		
		//2인용 자전거 상세보기 창에서 표시되는 초기화면으로 버튼
		JButton btngofirsttwobycicle = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		btngofirsttwobycicle.setBackground(new Color(255, 255, 224));
		btngofirsttwobycicle.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
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
		
		//일반 자전거의 상세설명 창
		JPanel thirdonepage = new JPanel();
		thirdonepage.setBounds(0, 0, 786, 528);
		frame.getContentPane().add(thirdonepage);
		thirdonepage.setLayout(null);
		
		//일반 자전거의 이름을 표시해주는 텍스트 
		JLabel normalname = new JLabel("\uC774\uB984 : \uC288\uD37C \uB178\uBA40 \uC0AC\uC774\uD074");
		normalname.setBounds(306, 283, 242, 58);
		normalname.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 20));
		thirdonepage.add(normalname);
		
		//일반 자전거의 특징을 표시해주는 텍스트 
		JLabel normalcharacteristic = new JLabel("\uD2B9\uC9D5 : \uC77C\uBC18 \uC790\uC804\uAC70\uB85C \uD3B8\uD788 \uC0AC\uC6A9\uD560 \uC218 \uC788\uC74C");
		normalcharacteristic.setBounds(227, 351, 420, 44);
		normalcharacteristic.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 20));
		thirdonepage.add(normalcharacteristic);
		
		//일반 자전거의 대여하기 버튼
		JButton btnlendnormal = new JButton("\uB300\uC5EC\uD558\uAE30");
		btnlendnormal.setBackground(new Color(255, 255, 224));
		btnlendnormal.setBounds(357, 421, 120, 38);
		btnlendnormal.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 18));
		thirdonepage.add(btnlendnormal);
		
		//일반 자전거 상세보기 창에서 표시되는 초기화면으로 버튼
		JButton btngofirstnormal = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		btngofirstnormal.setBackground(new Color(255, 255, 224));
		btngofirstnormal.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
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
		
		//자전거 대여하기를 누르면 넘어가는 화면 창 
		JPanel thirdpage = new JPanel();
		thirdpage.setBounds(0, 0, 786, 528);
		frame.getContentPane().add(thirdpage);
		thirdpage.setLayout(null);
		
		//'자전거 선택'을 표시해주는 텍스트 
		JLabel choicebycicle = new JLabel("\uC790\uC804\uAC70 \uC120\uD0DD");
		choicebycicle.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 40));
		choicebycicle.setBounds(279, 52, 220, 54);
		thirdpage.add(choicebycicle);
		
		//'슈퍼 노멀 사이클'을 표시해주는 텍스트 
		JLabel normal = new JLabel("\uC288\uD37C \uB178\uBA40 \uC0AC\uC774\uD074");
		normal.setHorizontalAlignment(SwingConstants.CENTER);
		normal.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 25));
		normal.setBounds(94, 131, 207, 124);
		thirdpage.add(normal);
		
		//일반 자전거의 상세보기 버튼 
		JButton btnnormal = new JButton("\uC0C1\uC138\uBCF4\uAE30");
		btnnormal.setBackground(new Color(255, 255, 224));
		btnnormal.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		btnnormal.setBounds(153, 230, 91, 31);
		thirdpage.add(btnnormal);
		btnnormal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bike = "normal";
			}
		});
		
		//'슈퍼 투피플 사이클'을 표시해주는 텍스트 
		JLabel twopeople = new JLabel("\uC288\uD37C \uD22C\uD53C\uD50C \uC0AC\uC774\uD074");
		twopeople.setHorizontalAlignment(SwingConstants.CENTER);
		twopeople.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 25));
		twopeople.setBounds(437, 132, 239, 123);
		thirdpage.add(twopeople);
		
		//2인용 자전거의 상세보기 버튼 
		JButton btntwopeople = new JButton("\uC0C1\uC138\uBCF4\uAE30");
		btntwopeople.setBackground(new Color(255, 255, 224));
		btntwopeople.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		btntwopeople.setBounds(509, 230, 91, 31);
		thirdpage.add(btntwopeople);
		btntwopeople.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bike = "twoPeople";
			}
		});
		
		//'슈퍼 마운틴 사이클'을 표시해주는 텍스트 
		JLabel mountain = new JLabel("\uC288\uD37C \uB9C8\uC6B4\uD2F4 \uC0AC\uC774\uD074");
		mountain.setHorizontalAlignment(SwingConstants.CENTER);
		mountain.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 25));
		mountain.setBounds(86, 286, 233, 106);
		thirdpage.add(mountain);
		
		//산악용 자전거 상세보기 버튼  
		JButton btnmountain = new JButton("\uC0C1\uC138\uBCF4\uAE30");
		btnmountain.setBackground(new Color(255, 255, 224));
		btnmountain.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		btnmountain.setBounds(153, 379, 91, 31);
		thirdpage.add(btnmountain);
		btnmountain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bike = "mountain";
			}
		});
		
		//'슈퍼 폴딩 사이클'을 표시해주는 텍스트 
		JLabel folding = new JLabel("\uC288\uD37C \uD3F4\uB529 \uC0AC\uC774\uD074");
		folding.setHorizontalAlignment(SwingConstants.CENTER);
		folding.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 25));
		folding.setBounds(447, 278, 207, 123);
		thirdpage.add(folding);
		
		//접이식 자전거의 상세보기 버튼 
		JButton btnfolding = new JButton("\uC0C1\uC138\uBCF4\uAE30");
		btnfolding.setBackground(new Color(255, 255, 224));
		btnfolding.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		btnfolding.setBounds(509, 379, 91, 31);
		thirdpage.add(btnfolding);
		btnfolding.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bike = "folding";
			}
		});
		
		//자전거 메뉴 창에서 표시되는 초기화면으로 버튼
		JButton btngofirstchoicebycicle = new JButton("\uBA54\uC778\uD654\uBA74\uC73C\uB85C ");
		btngofirstchoicebycicle.setBackground(new Color(255, 255, 224));
		btngofirstchoicebycicle.setHorizontalAlignment(SwingConstants.RIGHT);
		btngofirstchoicebycicle.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
		btngofirstchoicebycicle.setBounds(590, 455, 131, 32);
		thirdpage.add(btngofirstchoicebycicle);
		
		JLabel backgroundthird = new JLabel("");
		backgroundthird.setIcon(new ImageIcon("background.JPG"));// lblNewLabel
		backgroundthird.setBounds(0, 0, 786, 527);
		thirdpage.add(backgroundthird);
		
		//2번째 페이지 창 
		JPanel seconedpage = new JPanel();
		seconedpage.setBounds(0, 0, 786, 528);
		frame.getContentPane().add(seconedpage);
		seconedpage.setLayout(null);
		
		//2번째 페이지의 자전거 대여 버튼 
		JButton btn1 = new JButton("\uC790\uC804\uAC70 \uB300\uC5EC");
		btn1.setBackground(new Color(255, 255, 224));
		btn1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
		btn1.setBounds(157, 209, 173, 74);
		seconedpage.add(btn1);
		
		//2번째 페이지의 자전거 반납 버튼 
		JButton btn2 = new JButton("\uC790\uC804\uAC70 \uBC18\uB0A9");
		btn2.setBackground(new Color(255, 255, 224));
		btn2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
		btn2.setBounds(430, 209, 173, 74);
		seconedpage.add(btn2);
		
		//2번째 페이지의 남은 시간 확인하기 버튼 
		JButton btn3 = new JButton("\uBC18\uB0A9 \uC2DC\uAC04 \uD655\uC778");
		btn3.setBackground(new Color(255, 255, 224));
		btn3.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
		btn3.setBounds(287, 326, 187, 74);
		seconedpage.add(btn3);
		
		//choice를 표시해주는 텍스트 
		JLabel choice = new JLabel("Choice");
		choice.setHorizontalAlignment(SwingConstants.CENTER);
		choice.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 35));
		choice.setBounds(235, 77, 295, 74);
		seconedpage.add(choice);
		
		//2번째 페이지의 취소 버튼 
		JButton btncancle2 = new JButton("\uC885\uB8CC ");
		btncancle2.setBackground(new Color(255, 255, 224));
		btncancle2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));
		btncancle2.setBounds(649, 460, 91, 27);
		seconedpage.add(btncancle2);
		
		JLabel backgroundseconed = new JLabel("");
		backgroundseconed.setIcon(new ImageIcon("background.JPG"));
		backgroundseconed.setBounds(0, 0, 786, 518);
		seconedpage.add(backgroundseconed);
		
		// 처음에 표시되지 않는 창
		seconedpage.setVisible(false);
		seconedpage.setVisible(false);
		
		//1번째 페이지 창 
		JPanel firstpage = new JPanel();
		firstpage.setBounds(0, 0, 786, 528);
		frame.getContentPane().add(firstpage);
		firstpage.setLayout(null);
		
		//LogIn을 표시해주는 텍스트 
		JLabel login = new JLabel("LogIn");
		login.setHorizontalAlignment(SwingConstants.CENTER);
		login.setBounds(314, 118, 146, 49);
		login.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 40));
		firstpage.add(login);
		
		//이름을 입력받는 칸 
		nametextfield = new JTextField();
		nametextfield.setBounds(297, 210, 163, 26);
		firstpage.add(nametextfield);
		nametextfield.setColumns(10);
		
		//이름 입력 칸 앞에 '이름'을 표시해주는 텍스트 
		JLabel username = new JLabel("\uC774\uB984");
		username.setHorizontalAlignment(SwingConstants.CENTER);
		username.setBounds(229, 212, 50, 21);
		username.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 18));
		firstpage.add(username);
		
		//번호 입력 칸 앞에 '전화번호'를 표시해주는 텍스트 
		JLabel phonenum = new JLabel("\uC804\uD654\uBC88\uD638");
		phonenum.setHorizontalAlignment(SwingConstants.CENTER);
		phonenum.setBounds(212, 265, 69, 31);
		phonenum.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));
		firstpage.add(phonenum);
		
		//전화번호를 입력받는 칸 
		numtextfield = new JTextField();
		numtextfield.setBounds(297, 267, 163, 26);
		firstpage.add(numtextfield);
		numtextfield.setColumns(10);
		
		//1번째 페이지의 취소 버튼 
		JButton btncancle = new JButton("\uC885\uB8CC ");
		btncancle.setBackground(new Color(255, 255, 224));
		btncancle.setBounds(492, 275, 91, 23);
		btncancle.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		firstpage.add(btncancle);
		
		//1번째 페이지의 로그인 버튼 
		JButton btnlogin = new JButton("\uB85C\uADF8\uC778");
		btnlogin.setBackground(new Color(255, 255, 224));
		btnlogin.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		btnlogin.setBounds(492, 202, 91, 61);
		firstpage.add(btnlogin);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("background.JPG"));
		lblNewLabel_1.setBounds(0, 0, 794, 528);
		firstpage.add(lblNewLabel_1);
		
				// 자전거 개수&대여 시간 선택 후 대여하기 버튼 누르면 발생하는 액션
				btngofirstchoicetime.addActionListener(new ActionListener() {
		
					@Override
					public void actionPerformed(ActionEvent e) {
						timechoicepage.setVisible(false);
						lendokpage.setVisible(true);
						try {
							customer.errorCustomer(nametextfield.getText(), numtextfield.getText(), time, customer.now, bike, num); //에러있는지없는지 거친다음 빌리기
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
					}
				});
				timechoicepage.setVisible(false);
		
		//첫 번째 화면에서 취소 버튼을 누르면 프로그램이 종료되는 액션
		btncancle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		// 로그인 버튼을 누르면 오류 메시지가 뜨게하는 창
		btnlogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if ((nametextfield.getText().length() == 0) || (numtextfield.getText().length() == 0)) {
					JOptionPane.showMessageDialog(null, ("이름과 전화번호를 입력해주세요 "));
					firstpage.setVisible(true);
					seconedpage.setVisible(false);
				} else {
					seconedpage.setVisible(true);
					firstpage.setVisible(false);
				}
			}
		});

		// 현재 남은 시간을 확인하는 페이지에서 초기화면으로 버튼을 누르면 2번째 화면으로 돌아가는 액션
		btngofirsttime.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				seconedpage.setVisible(true);
				fivepage.setVisible(false);
			}
		});

		// 연장 시간 선택 페이지에서 초기화면으로 버튼을 누르면 2번째 화면으로 돌아가는 액션
		btngofirstlatetime.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				seconedpage.setVisible(true);
				fourstwopage.setVisible(false);
			}
		});

		// 시간 연장 페이지에서 1시간 연장하기 버튼을 누르면 연장되었습니다 라는 메시지가 뜨는 창으로 넘어가가는 액션
		btnlateone.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fourstwolatepage.setVisible(true);
				fourstwopage.setVisible(false);
				lateTime = 1;
				customer.lateTime(lateTime, numtextfield.getText());
			}
		});

		// 시간 연장 페이지에서 2시간 연장하기 버튼을 누르면 연장되었습니다 라는 메시지가 뜨는 창으로 넘어가가는 액션
		btnlatetwo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fourstwolatepage.setVisible(true);
				fourstwopage.setVisible(false);
				lateTime = 2;
				customer.lateTime(lateTime, numtextfield.getText());
			}
		});

		// 시간 연장 페이지에서 3시간 연장하기 버튼을 누르면 연장되었습니다 라는 메시지가 뜨는 창으로 넘어가가는 액션
		btnlatethird.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fourstwolatepage.setVisible(true);
				fourstwopage.setVisible(false);
				lateTime = 3;
				customer.lateTime(lateTime, numtextfield.getText());
			}
		});

		// 시간 연장 페이지에서 4시간 연장하기 버튼을 누르면 연장되었습니다 라는 메시지가 뜨는 창으로 넘어가가는 액션
		btnlatefour.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fourstwolatepage.setVisible(true);
				fourstwopage.setVisible(false);
				lateTime = 4;
				customer.lateTime(lateTime, numtextfield.getText()); 
			}
		});

		// 반납이 완료되었다는 안내 메시지가 띄워진 페이지에서 초기화면으로 버튼을 누르면 2번째 화면으로 돌아가는 액션
		btngofirstreok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				seconedpage.setVisible(true);
				foursonepage.setVisible(false);
			}
		});

		// 반납 버튼을 누르면 반납이 완료되었다는 메시지가 띄워지는 페이지로 넘어가는 액션(자전거 반납 액션)
		btnrebycicle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				foursonepage.setVisible(true);
				fourspage.setVisible(false);
				customer.deleteCustomer(numtextfield.getText(), bike);
			}
		});

		// 시간 연장 버튼을 누르면 시간 연장 페이지로 넘어가는 액션
		btnlatetime.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fourstwopage.setVisible(true);
				fourspage.setVisible(false);
			}
		});

		// 반납과 시간연장을 선택하는 페이지에서 초기화면으로 버튼을 누르면 2번째 화면으로 돌아가는 액션
		btngofirstreorlate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				seconedpage.setVisible(true);
				fourspage.setVisible(false);
			}
		});

		// 슈퍼 폴딩 사이클 상세보기를 보던 중 대여하기를 누르면 시간 선택 페이지로 넘어가는 액션
		btnlendfolding.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timechoicepage.setVisible(true);
				thirdfourpage.setVisible(false);
			}
		});

		// 슈퍼 폴딩 사이클 상세설명 페이지에서 초기화면으로 버튼을 누르면 2번째 화면으로 돌아가는 액션
		btngofirstfoldingbycicle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thirdpage.setVisible(true);
				thirdfourpage.setVisible(false);
			}
		});

		// 슈퍼 마운틴 사이클 상세보기를 보던 중 대여하기를 누르면 시간 선택 페이지로 넘어가는 액션
		btnlendmountain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timechoicepage.setVisible(true);
				thirdthirdpage.setVisible(false);
			}
		});

		// 슈퍼 마운틴 사이클 상세설명 페이지에서 초기화면으로 버튼을 누르면 2번째 화면으로 돌아가는 액션
		btngofirstmountainbycicle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thirdpage.setVisible(true);
				thirdthirdpage.setVisible(false);
			}
		});

		// 슈퍼 투피플 사이클 상세보기를 보던 중 대여하기를 누르면 시간 선택 페이지로 넘어가는 액션
		btnlendtowpeople.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timechoicepage.setVisible(true);
				thirdtwopage.setVisible(false);
			}
		});

		// 슈퍼 투피플 사이클 상세설명 페이지에서 초기화면으로 버튼을 누르면 2번째 화면으로 돌아가는 액션
		btngofirsttwobycicle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thirdpage.setVisible(true);
				thirdtwopage.setVisible(false);
			}
		});

		// 슈퍼 노멀 사이클 상세보기를 보던 중 대여하기를 누르면 시간 선택 페이지로 넘어가는 액션
		btnlendnormal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timechoicepage.setVisible(true);
				thirdonepage.setVisible(false);
			}
		});

		// 슈퍼 노멀 사이클 상세설명 페이지에서 초기화면으로 버튼을 누르면 2번째 화면으로 돌아가는 액션
		btngofirstnormal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thirdpage.setVisible(true);
				thirdonepage.setVisible(false);
			}
		});

		// 슈퍼 노멀 사이클 상세보기 버튼을 누르면 이름과 특징이 나오는 페이지로 넘어가는 액션
		btnnormal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thirdonepage.setVisible(true);
				thirdpage.setVisible(false);
			}
		});

		// 슈퍼 투피플 사이클 상세보기 버튼을 누르면 이름과 특징이 나오는 페이지로 넘어가는 액션
		btntwopeople.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thirdtwopage.setVisible(true);
				thirdpage.setVisible(false);
			}
		});

		// 슈퍼 마운틴 사이클 상세보기 버튼을 누르면 이름과 특징이 나오는 페이지로 넘어가는 액션
		btnmountain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thirdthirdpage.setVisible(true);
				thirdpage.setVisible(false);
			}
		});

		// 슈퍼 폴딩 사이클 상세보기 버튼을 누르면 이름과 특징이 나오는 페이지로 넘어가는 액션
		btnfolding.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thirdfourpage.setVisible(true);
				thirdpage.setVisible(false);
			}
		});

		// 자전거 선택 페이지에서 초기화면으로 버튼을 누르면 2번째 화면으로 돌아가는 액션
		btngofirstchoicebycicle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				seconedpage.setVisible(true);
				thirdpage.setVisible(false);
			}
		});

		// 두 번째 화면에서 취소 버튼을 누르면 프로그램이 종료되는 액션
		btncancle2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// 자전거 대여 버튼을 누르면 자전거 대여 페이지로 넘어가는 액션
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thirdpage.setVisible(true);
				seconedpage.setVisible(false);
			}
		});

		
		
		// 자전거 반납 버튼을 누르면 자전거 반납 페이지로 넘어가는 액션
		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fourspage.setVisible(true);
				seconedpage.setVisible(false);
				foursonepage.setVisible(false);

			}
		});

		// 남은 시간 확인 버튼을 누르면 남은 시간을 확인시켜주는 페이지로 넘어가는 액션
		btn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fivepage.setVisible(true);
				seconedpage.setVisible(false);
				fourspage.setVisible(false);
			}
		});
		
		//자전거 대여가 완료되었다는 메시지를 띄워주는 페이지에서 닫기 버튼을 누르면 프로그램을 종료하는 액션 
		btngofirstlendok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//시간이 연장되었다는 문구가 뜨는 창에서 닫기버튼을 누르면 프로그램이 종료되는 액션 
		btnlatetimebye.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//처음 실행화면에서 표시되지 않는 창 
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
