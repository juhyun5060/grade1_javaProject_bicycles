package Bicycle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

public class Customer {
	Date d = new Date(); //���� ��¥ ����
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String now = date.format(d); //date���� ������ ����ȭ�� ���
	static Bicycle bicycle = new Bicycle();
	
	public static String[][] getCustomers(){ //�迭�� ������ �ҷ�����
		try {
			Connection con = getConnection(); //connection �ҷ�����
			PreparedStatement statement = con.prepareStatement("Select name, phone, time, date FROM customer"); //��ɾ �غ��ض� (�ҷ��� ��ɾ�)
			ResultSet results = statement.executeQuery();//����� ����
			ArrayList<String[]> list = new ArrayList<String[]>(); //ArryList����
			while(results.next()) { //ù��° �����͸� �ҷ����� �� ���� �����ͷ� �Ѿ�� => ���� �����Ͱ� ����������
				list.add(new String[] {
						results.getString("name"),
						results.getString("phone"),
						results.getString("time"),
						results.getString("date")});
				
			}
			System.out.println("�����͸� ���������� �ҷ��Խ��ϴ�.");
			String[][] arr = new String[list.size()][4]; //name, phone, time, date 4��
			return list.toArray(arr); //�迭�� ����
			
		} catch(Exception e) {
			System.out.println("������ �߻��߽��ϴ� : "+e.getMessage());
			return null;
		}
	}
	
	public static void errorCustomer(String name, String phone, int time, String date, String bike, int num) throws SQLException { //��� ����
		Connection con = getConnection();
		PreparedStatement row = con.prepareStatement("SELECT COUNT(*) as cnt FROM customer"); //����Ǿ��ִ� ��� ������� ī��Ʈ
		ResultSet cntResult = row.executeQuery(); //ī��Ʈ�� ��ȯ���� cntResult�� ����
		cntResult.next(); //���� ��� ����
		int totCnt = cntResult.getInt("cnt");
		if ( totCnt >= 10 ){ //db�� ����Ǿ��ִ� ���� 10�� �̻��̸� 
			JOptionPane.showMessageDialog(null, "�ִ� ���̺� ���尹�� 10���� �ʰ��Ͽ����ϴ�.");
		}else{
		   createCustomer(name, phone, time, date, bike, num); //�׷��� ������ createCustomer ����
		}
	}
	
	public static void createCustomer(String name, String phone, int time, String date, String bike, int num) { //������ ����
		Connection con = getConnection();
		try {
			PreparedStatement insert = con.prepareStatement(""
															+ "INSERT INTO customer"
															+ "(name, phone, time, date, bike, num) "
															+ "VALUE "
															+ "('"+name+"','"+phone+"','"+time+"','"+date+"','"+bike+"','"+num+"')");
			switch(bike) {
				case "normal" : bicycle.lend_normalBicycle(num); break;
				case "twoPeople" : bicycle.lend_twoPeopleBicycle(num); break;
				case "mountain" : bicycle.lend_mountainBicycle(num); break;
				case "folding" : bicycle.lend_foldingBicycle(num); break;
				default : 
			}
			insert.executeUpdate(); //INSERT ������ ������ ��� ��ȯ
			System.out.println("�����Ͱ� ���������� ����Ǿ����ϴ�.");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
				
	}
	
	public static void createTable_customer() { //���̺� ����
		try {
			Connection con = getConnection();
			PreparedStatement createTable = con.prepareStatement("CREATE TABLE IF NOT EXISTS " //���̺��� ���� ���
																+ "customer(id int(11) NOT NULL," //������ ���� NULL���� �ɼ� ���� ������ ���� ���� �Ѵ�, �ڵ����� ���� ����
																+ "name varChar(20),"
																+ "phone varChar(11),"
																+ "time varChar(20),"
																+ "date varChar(255),"
																+ "bike varChar(10),"
																+ "num varChar(10))");
			createTable.execute();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("���̺��� �����Ǿ����ϴ�.");
		}
	}
	
	public static void deleteCustomer(String phone, String bike) { //������ �ݳ� => bike�� �������� �� �� ������ �׷��ϱ� applet�� �����
		Connection con = getConnection();
		try {
			PreparedStatement deleteTable = con.prepareStatement("DELETE FROM customer "
																+ "WHERE phone = " + phone);
			switch(bike) {
				case "normal" : bicycle.return_normalBicycle(phone); break;
				case "twoPeople" : bicycle.return_twoPeopleBicycle(phone); break;
				case "mountain" : bicycle.return_mountainBicycle(phone); break;
				case "folding" : bicycle.return_foldingBicycle(phone); break;
				default : 
			}
			deleteTable.executeUpdate();
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			System.out.println("�����Ű� �ݳ��Ǿ����ϴ�.");
		}
	}

	public static void lateTime(int lateTime, String phone) { //�ð� ����
		try {
			Connection con = getConnection();
			PreparedStatement change = con.prepareStatement("UPDATE customer SET time = time + " + lateTime +" " 
															+"WHERE phone = " + phone); //���� ���ѹ��� �´� ���ѹ� �ð��� �ð� �߰�
			change.execute();
			System.out.println("�ð��� ����Ǿ����ϴ�.");
		} catch(Exception e) {
			System.out.println(e.getMessage());
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

