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
	Date d = new Date(); //오늘 날짜 생성
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String now = date.format(d); //date에서 설정한 형식화된 출력
	static Bicycle bicycle = new Bicycle();
	
	public static String[][] getCustomers(){ //배열로 데이터 불러오기
		try {
			Connection con = getConnection(); //connection 불러오기
			PreparedStatement statement = con.prepareStatement("Select name, phone, time, date FROM customer"); //명령어를 준비해라 (불러올 명령어)
			ResultSet results = statement.executeQuery();//결과값 저장
			ArrayList<String[]> list = new ArrayList<String[]>(); //ArryList생성
			while(results.next()) { //첫번째 데이터를 불러오면 그 다음 데이터로 넘어가기 => 다음 데이터가 없을때까지
				list.add(new String[] {
						results.getString("name"),
						results.getString("phone"),
						results.getString("time"),
						results.getString("date")});
				
			}
			System.out.println("데이터를 정상적으로 불러왔습니다.");
			String[][] arr = new String[list.size()][4]; //name, phone, time, date 4개
			return list.toArray(arr); //배열로 리턴
			
		} catch(Exception e) {
			System.out.println("오류가 발생했습니다 : "+e.getMessage());
			return null;
		}
	}
	
	public static void errorCustomer(String name, String phone, int time, String date, String bike, int num) throws SQLException { //사람 제한
		Connection con = getConnection();
		PreparedStatement row = con.prepareStatement("SELECT COUNT(*) as cnt FROM customer"); //저장되어있는 사람 몇명인지 카운트
		ResultSet cntResult = row.executeQuery(); //카운트한 반환값을 cntResult에 저장
		cntResult.next(); //다음 요소 선택
		int totCnt = cntResult.getInt("cnt");
		if ( totCnt >= 10 ){ //db에 저장되어있는 값이 10개 이상이면 
			JOptionPane.showMessageDialog(null, "최대 테이블 저장갯수 10건을 초과하였습니다.");
		}else{
		   createCustomer(name, phone, time, date, bike, num); //그렇지 않으면 createCustomer 실행
		}
	}
	
	public static void createCustomer(String name, String phone, int time, String date, String bike, int num) { //데이터 저장
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
			insert.executeUpdate(); //INSERT 구문을 실행한 결과 반환
			System.out.println("데이터가 정상적으로 저장되었습니다.");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
				
	}
	
	public static void createTable_customer() { //테이블 생성
		try {
			Connection con = getConnection();
			PreparedStatement createTable = con.prepareStatement("CREATE TABLE IF NOT EXISTS " //테이블이 없을 경우
																+ "customer(id int(11) NOT NULL," //데이터 형식 NULL값이 될수 없고 무조건 값이 들어가야 한다, 자동으로 순번 설정
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
			System.out.println("테이블이 생성되었습니다.");
		}
	}
	
	public static void deleteCustomer(String phone, String bike) { //자전거 반납 => bike는 전번에서 알 수 었ㅇ어 그러니까 applet로 써야지
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
			System.out.println("자전거가 반납되었습니다.");
		}
	}

	public static void lateTime(int lateTime, String phone) { //시간 연장
		try {
			Connection con = getConnection();
			PreparedStatement change = con.prepareStatement("UPDATE customer SET time = time + " + lateTime +" " 
															+"WHERE phone = " + phone); //고객의 폰넘버에 맞는 폰넘버 시간에 시간 추가
			change.execute();
			System.out.println("시간이 연장되었습니다.");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static Connection getConnection() { //db연동
		try {
			String dbURL = "jdbc:mysql://localhost:3306/customer?serverTimezone=UTC";
			String dbID = "root";
			String dbPassword  = "sofiajuhyun5060";
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("정상적으로 연결되었습니다.");
			return DriverManager.getConnection(dbURL, dbID, dbPassword);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}

