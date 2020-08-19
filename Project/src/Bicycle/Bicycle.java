package Bicycle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Bicycle {	
	public static String[][] getCustomers(){ //�迭�� ������ �ҷ�����
		try {
			Connection con = getConnection(); //connection �ҷ�����
			PreparedStatement statement = con.prepareStatement("Select normal, twoPeople, mountain, folding FROM bicyle"); //��ɾ �غ��ض� (�ҷ��� ��ɾ�)
			ResultSet results = statement.executeQuery();//����� ����
			ArrayList<String[]> list = new ArrayList<String[]>(); //ArryList����
			while(results.next()) { //ù��° �����͸� �ҷ����� �� ���� �����ͷ� �Ѿ�� => ���� �����Ͱ� ����������
				list.add(new String[] {
						results.getString("normal"),
						results.getString("twoPeople"),
						results.getString("mountain"),
						results.getString("folding")});
				
			}
			System.out.println("�����͸� ���������� �ҷ��Խ��ϴ�.");
			String[][] arr = new String[list.size()][4]; //name, phone, time, date 4��
			return list.toArray(arr); //�迭�� ����
			
		} catch(Exception e) {
			System.out.println("������ �߻��߽��ϴ� : "+e.getMessage());
			return null;
		}
	}

	
	//
	public static void errorBicycle(int normal, int twoPeople, int mountain, int folding) throws SQLException { //������ ����
		Connection con = getConnection();
		PreparedStatement row = con.prepareStatement("SELECT COUNT(*) as cnt FROM bicycle"); //����Ǿ��ִ� ������ � ���� ī��Ʈ
		ResultSet cntResult = row.executeQuery(); //ī��Ʈ�� ��ȯ���� cntResult�� ����
		cntResult.next(); //���� ��� ����
		int totCnt = cntResult.getInt("cnt");
		if ( totCnt >= 10 ){ //db�� ����Ǿ��ִ� ���� 10�� �̻��̸� 
			JOptionPane.showMessageDialog(null, "�ִ� ���̺� ���尹�� 10���� �ʰ��Ͽ����ϴ�.");
		}else{
		   createBicycle(normal, twoPeople, mountain, folding); //�׷��� ������ createCustomer ����
		}
	}
	
	public static void createBicycle(int normal, int twoPeople, int mountain, int folding) { //������ ����
		Connection con = getConnection();
		try {
			PreparedStatement insert = con.prepareStatement(""
															+ "INSERT INTO bicycle"
															+ "(normal, twoPeople, mountain, folding) "
															+ "VALUE "
															+ "('"+10+"','"+10+"','"+10+"','"+10+"')");
			insert.executeUpdate(); //INSERT ������ ������ ��� ��ȯ
			System.out.println("�����Ͱ� ���������� ����Ǿ����ϴ�.");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
				
	}
	

	
	public static void lend_normalBicycle(int num) { //�Ϲ� ������ �뿩 -> �����Ÿ� ������ ���� �ִ� ��� ������ ���� -���� ������ ����
		try {
			Connection con = getConnection();
			PreparedStatement createTable = con.prepareStatement("UPDATE bicycle SET "
																+ "normal = normal - " + num);
			
			createTable.execute();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("�����Ű� �뿩�Ǿ����ϴ�.");
		}
	}
	
	public static void lend_twoPeopleBicycle(int num) { //�Ϲ� ������ �뿩 -> �����Ÿ� ������ ���� �ִ� ��� ������ ���� -���� ������ ����
		try {
			Connection con = getConnection();
			PreparedStatement createTable = con.prepareStatement("UPDATE bicycle SET "
																+ "twoPeople = twoPeople - " + num);
			
			createTable.execute();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("�����Ű� �뿩�Ǿ����ϴ�.");
		}
	}
	
	public static void lend_mountainBicycle(int num) { //�Ϲ� ������ �뿩 -> �����Ÿ� ������ ���� �ִ� ��� ������ ���� -���� ������ ����
		try {
			Connection con = getConnection();
			PreparedStatement createTable = con.prepareStatement("UPDATE bicycle SET "
																+ "mountain = mountain - " + num);
			
			createTable.execute();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("�����Ű� �뿩�Ǿ����ϴ�.");
		}
	}
	
	public static void lend_foldingBicycle(int num) { //�Ϲ� ������ �뿩 -> �����Ÿ� ������ ���� �ִ� ��� ������ ���� -���� ������ ����
		try {
			Connection con = getConnection();
			PreparedStatement createTable = con.prepareStatement("UPDATE bicycle SET "
																+ "folding = folding - " + num);
			
			createTable.execute();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("�����Ű� �뿩�Ǿ����ϴ�.");
		}
	}
	
	//������ �ݳ� 2 => bike null ���� �ذ�
	public static void return_bicycle(String phone) {
		try {
			Connection con = getConnection();
			PreparedStatement updateTable = con.prepareStatement("UPDATE customer c, bicycle b SET b.normal = b.normal + "
																+ "c.num WHERE phone = " + phone);// bike�� �𸣴µ�...
			updateTable.execute();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("�����Ű� �ݳ��Ǿ����ϴ�.");
		}
	}
	
	public static void return_normalBicycle(String phone) { //�Ϲ� ������ �ݳ� -> �����Ÿ� ������ ���� �ִ� ��� ������ ���� +������ ������ ����
		try {
			Connection con = getConnection();
			PreparedStatement updateTable = con.prepareStatement("UPDATE customer c, bicycle b SET b.normal = b.normal + "
																+ "c.num WHERE phone = " + phone);// bike�� �𸣴µ�...
			updateTable.execute();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("�����Ű� �ݳ��Ǿ����ϴ�.");
		}
	}
	
	public static void return_twoPeopleBicycle(String phone) { //������ ������ �ݳ� -> �����Ÿ� ������ ���� �ִ� ������ ������ ���� +������ ������ ����
		try {
			Connection con = getConnection();
			PreparedStatement updateTable = con.prepareStatement("UPDATE customer c, bicycle b SET b.twoPeople = b.twoPeople + "
																+ "c.num WHERE phone = " + phone);
			
			updateTable.execute();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("�����Ű� �ݳ��Ǿ����ϴ�.");
		}
	}
	
	public static void return_mountainBicycle(String phone) { //��� ������ �ݳ� -> �����Ÿ� ������ ���� �ִ� ��� ������ ���� +������ ������ ����
		try {
			Connection con = getConnection();
			PreparedStatement updateTable = con.prepareStatement("UPDATE customer c, bicycle b SET b.mountain = b.mountain + "
											 					+ "c.num WHERE phone = " + phone);
			
			updateTable.execute();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("�����Ű� �ݳ��Ǿ����ϴ�.");
		}
	}
	
	public static void return_foldingBicycle(String phone) { //���� ������ �ݳ� -> �����Ÿ� ������ ���� �ִ� ���� ������ ���� +������ ������ ����
		try {
			Connection con = getConnection();
			PreparedStatement updateTable = con.prepareStatement("UPDATE customer c, bicycle b SET b.folding = b.folding + "
																+ "c.num WHERE phone = " + phone);
			
			updateTable.execute();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("�����Ű� �ݳ��Ǿ����ϴ�.");
		}
	}
	
	public static void createTable_bicycle() { //���̺� ����
		try {
			Connection con = getConnection();
			PreparedStatement createTable = con.prepareStatement("CREATE TABLE IF NOT EXISTS " //���̺��� ���� ���
																+ "bicycle(id int(11) NOT NULL," //������ ���� NULL���� �ɼ� ���� ������ ���� ���� �Ѵ�, �ڵ����� ���� ����
																+ "normal varChar(10),"
																+ "twoPeople varChar(10),"
																+ "mountain varChar(10),"
																+ "folding varChar(10))");
			createTable.execute();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("���̺��� �����Ǿ����ϴ�.");
		}
	}
	
	public static Connection getConnection() { //db����
		try {
			String dbURL = "jdbc:mysql://localhost:3306/customer?serverTimezone=UTC";
			String dbID = "root";
			String dbPassword  = "sofiajuhyun5060";
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("���������� ����Ǿ����ϴ�.");
			return DriverManager.getConnection(dbURL, dbID, dbPassword);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
