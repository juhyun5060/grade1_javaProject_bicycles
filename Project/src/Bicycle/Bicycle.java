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
	public static String[][] getCustomers(){ //배열로 데이터 불러오기
		try {
			Connection con = getConnection(); //connection 불러오기
			PreparedStatement statement = con.prepareStatement("Select normal, twoPeople, mountain, folding FROM bicyle"); //명령어를 준비해라 (불러올 명령어)
			ResultSet results = statement.executeQuery();//결과값 저장
			ArrayList<String[]> list = new ArrayList<String[]>(); //ArryList생성
			while(results.next()) { //첫번째 데이터를 불러오면 그 다음 데이터로 넘어가기 => 다음 데이터가 없을때까지
				list.add(new String[] {
						results.getString("normal"),
						results.getString("twoPeople"),
						results.getString("mountain"),
						results.getString("folding")});
				
			}
			System.out.println("데이터를 정상적으로 불러왔습니다.");
			String[][] arr = new String[list.size()][4]; //name, phone, time, date 4개
			return list.toArray(arr); //배열로 리턴
			
		} catch(Exception e) {
			System.out.println("오류가 발생했습니다 : "+e.getMessage());
			return null;
		}
	}

	
	//
	public static void errorBicycle(int normal, int twoPeople, int mountain, int folding) throws SQLException { //자전거 제한
		Connection con = getConnection();
		PreparedStatement row = con.prepareStatement("SELECT COUNT(*) as cnt FROM bicycle"); //저장되어있는 자전거 몇개 인지 카운트
		ResultSet cntResult = row.executeQuery(); //카운트한 반환값을 cntResult에 저장
		cntResult.next(); //다음 요소 선택
		int totCnt = cntResult.getInt("cnt");
		if ( totCnt >= 10 ){ //db에 저장되어있는 값이 10개 이상이면 
			JOptionPane.showMessageDialog(null, "최대 테이블 저장갯수 10건을 초과하였습니다.");
		}else{
		   createBicycle(normal, twoPeople, mountain, folding); //그렇지 않으면 createCustomer 실행
		}
	}
	
	public static void createBicycle(int normal, int twoPeople, int mountain, int folding) { //데이터 저장
		Connection con = getConnection();
		try {
			PreparedStatement insert = con.prepareStatement(""
															+ "INSERT INTO bicycle"
															+ "(normal, twoPeople, mountain, folding) "
															+ "VALUE "
															+ "('"+10+"','"+10+"','"+10+"','"+10+"')");
			insert.executeUpdate(); //INSERT 구문을 실행한 결과 반환
			System.out.println("데이터가 정상적으로 저장되었습니다.");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
				
	}
	

	
	public static void lend_normalBicycle(int num) { //일반 자전거 대여 -> 자전거를 빌리면 원래 있던 노멀 자전거 개수 -빌린 자전거 개수
		try {
			Connection con = getConnection();
			PreparedStatement createTable = con.prepareStatement("UPDATE bicycle SET "
																+ "normal = normal - " + num);
			
			createTable.execute();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("자전거가 대여되었습니다.");
		}
	}
	
	public static void lend_twoPeopleBicycle(int num) { //일반 자전거 대여 -> 자전거를 빌리면 원래 있던 노멀 자전거 개수 -빌린 자전거 개수
		try {
			Connection con = getConnection();
			PreparedStatement createTable = con.prepareStatement("UPDATE bicycle SET "
																+ "twoPeople = twoPeople - " + num);
			
			createTable.execute();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("자전거가 대여되었습니다.");
		}
	}
	
	public static void lend_mountainBicycle(int num) { //일반 자전거 대여 -> 자전거를 빌리면 원래 있던 노멀 자전거 개수 -빌린 자전거 개수
		try {
			Connection con = getConnection();
			PreparedStatement createTable = con.prepareStatement("UPDATE bicycle SET "
																+ "mountain = mountain - " + num);
			
			createTable.execute();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("자전거가 대여되었습니다.");
		}
	}
	
	public static void lend_foldingBicycle(int num) { //일반 자전거 대여 -> 자전거를 빌리면 원래 있던 노멀 자전거 개수 -빌린 자전거 개수
		try {
			Connection con = getConnection();
			PreparedStatement createTable = con.prepareStatement("UPDATE bicycle SET "
																+ "folding = folding - " + num);
			
			createTable.execute();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("자전거가 대여되었습니다.");
		}
	}
	
	//자전거 반납 2 => bike null 문제 해결
	public static void return_bicycle(String phone) {
		try {
			Connection con = getConnection();
			PreparedStatement updateTable = con.prepareStatement("UPDATE customer c, bicycle b SET b.normal = b.normal + "
																+ "c.num WHERE phone = " + phone);// bike를 모르는데...
			updateTable.execute();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("자전거가 반납되었습니다.");
		}
	}
	
	public static void return_normalBicycle(String phone) { //일반 자전거 반납 -> 자전거를 빌리면 원래 있던 노멀 자전거 개수 +빌려간 자전거 개수
		try {
			Connection con = getConnection();
			PreparedStatement updateTable = con.prepareStatement("UPDATE customer c, bicycle b SET b.normal = b.normal + "
																+ "c.num WHERE phone = " + phone);// bike를 모르는데...
			updateTable.execute();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("자전거가 반납되었습니다.");
		}
	}
	
	public static void return_twoPeopleBicycle(String phone) { //투피플 자전거 반남 -> 자전거를 빌리면 원래 있던 투피플 자전거 개수 +빌려간 자전거 개수
		try {
			Connection con = getConnection();
			PreparedStatement updateTable = con.prepareStatement("UPDATE customer c, bicycle b SET b.twoPeople = b.twoPeople + "
																+ "c.num WHERE phone = " + phone);
			
			updateTable.execute();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("자전거가 반납되었습니다.");
		}
	}
	
	public static void return_mountainBicycle(String phone) { //산악 자전거 반남 -> 자전거를 빌리면 원래 있던 산악 자전거 개수 +빌려간 자전거 개수
		try {
			Connection con = getConnection();
			PreparedStatement updateTable = con.prepareStatement("UPDATE customer c, bicycle b SET b.mountain = b.mountain + "
											 					+ "c.num WHERE phone = " + phone);
			
			updateTable.execute();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("자전거가 반납되었습니다.");
		}
	}
	
	public static void return_foldingBicycle(String phone) { //폴딩 자전거 반납 -> 자전거를 빌리면 원래 있던 폴딩 자전거 개수 +빌려간 자전거 개수
		try {
			Connection con = getConnection();
			PreparedStatement updateTable = con.prepareStatement("UPDATE customer c, bicycle b SET b.folding = b.folding + "
																+ "c.num WHERE phone = " + phone);
			
			updateTable.execute();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("자전거가 반납되었습니다.");
		}
	}
	
	public static void createTable_bicycle() { //테이블 생성
		try {
			Connection con = getConnection();
			PreparedStatement createTable = con.prepareStatement("CREATE TABLE IF NOT EXISTS " //테이블이 없을 경우
																+ "bicycle(id int(11) NOT NULL," //데이터 형식 NULL값이 될수 없고 무조건 값이 들어가야 한다, 자동으로 순번 설정
																+ "normal varChar(10),"
																+ "twoPeople varChar(10),"
																+ "mountain varChar(10),"
																+ "folding varChar(10))");
			createTable.execute();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("테이블이 생성되었습니다.");
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
