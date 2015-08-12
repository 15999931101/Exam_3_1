package com.hand.Exam_3_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ex1 {	
	
	public static Connection getConnection(){
		Connection conn =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");//用来注册mysql的jdbc程序
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila","root","lin051477515");//连接数据库

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;		
	}
	

	public static void select(int id){       
	

		Connection conn = getConnection();
		
		String sql = "select city_id ,city from city where country_id = "+id;
		String sql1 = "select country from country where country_id = "+id;
		
		try {

			PreparedStatement ps = conn.prepareCall(sql);	
			PreparedStatement ps1 = conn.prepareCall(sql1);
	
			ResultSet rs1 =ps1.executeQuery(sql1);
			ResultSet rs =ps.executeQuery(sql);//执行sql语句
			while (rs1.next()) {
				System.out.println("country的名字："+rs1.getString("country"));					

			}
			
			System.out.println("城市的id"+"  |  "+"城市的名字");
			while (rs.next()) {
				System.out.println(rs.getInt("city_id")+"\t    "+rs.getString("city"));						

			}
			conn.close();			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		
		System.out.println("请输入Country_id号：");
		
		Scanner sc =new Scanner(System.in);
		int id =sc.nextInt();
		select(id);
		
		
	}

}

