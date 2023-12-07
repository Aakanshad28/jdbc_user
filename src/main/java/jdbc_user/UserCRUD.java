package jdbc_user;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class UserCRUD {
	public Connection  getConnection () throws IOException, ClassNotFoundException, SQLException {
		FileReader reader=new FileReader("dbconfig.properties");
		Properties properties=new Properties();
		properties.load(reader);
		
		//1. 
		Class.forName(properties.getProperty("className"));
		//2. 
		Connection connection=DriverManager.getConnection(properties.getProperty("url"),
				properties.getProperty("user"), properties.getProperty("password"));
		return connection;
	}
	public void signUpUser(User user) throws Exception {
		Connection connection=getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO USER(id,name,phone,email,password) VALUES (?,?,?,?,?)");
		
		preparedStatement.setInt(1 ,user.getId());
		preparedStatement.setString(2 , user.getName());
		preparedStatement.setLong(3 , user.getPhone());
		preparedStatement.setString(4 ,user.getEmail());
		preparedStatement.setString(5 ,user.getPassword());
		 int result=preparedStatement.executeUpdate();
		 if (result!=0) 
		 {
			System.out.println("Data Inserted");
		 } 
		 else 
		 {
            System.out.println("Data not Inserted");
		 }
		 
		 connection.close();
		
	}
	public boolean userLogin(String email,String password) throws ClassNotFoundException, IOException, SQLException {
		Connection connection=getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement("SELECT PASSWORD FROM USER WHERE EMAIL=?");
		
		preparedStatement.setString(1, email);
		
		ResultSet resultSet=preparedStatement.executeQuery();
		String dbPassword=null;
		while (resultSet.next()) {
			 dbPassword=resultSet.getString("password");
		}
		connection.close();
		
		if (password.equals(dbPassword)) {
			//System.out.println("Login Successful");
			return true;
		} else {
           // System.out.println("Login Failed");
			return false;
		}
	}
	public void showPassword(String email) throws ClassNotFoundException, IOException, SQLException {
		Connection connection=getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM USER WHERE EMAIL=?");
		
		preparedStatement.setString(1, email);
		ResultSet resultSet=preparedStatement.executeQuery();
		while (resultSet.next()) {
			System.out.println("Saved Password are :");
			System.out.println("Facebook Password is :"+resultSet.getString("facebook"));
			System.out.println("Twitter Password is :"+resultSet.getString("twitter"));
			System.out.println("Instagram Password is :"+resultSet.getString("instagram"));
			System.out.println("Whatsapp Password is :"+resultSet.getString("whatsapp"));
			System.out.println("Snapchat Password is :"+resultSet.getString("snapchat"));
			
			System.out.println("Enter your choice\n1.Update Password\n2.LogOut");
			Scanner scanner=new Scanner(System.in);
			int choice=scanner.nextInt();
			switch (choice) {
			case 1:{
				System.out.println("Enter the pasword you want to update:");
			}
				
				break;

			default:
				break;
			}
		}
		connection.close();
		
	}
	public void updatePassword(User user) throws ClassNotFoundException, IOException, SQLException {
		Connection connection=getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement("UPDATE USER SET FACEBOOK=? WHATSAPP=? SNAPCHAT=? TWITTER=? INSTAGRAM=? WHERE EMAIL=?");
		preparedStatement.setString(1,user.getFacebook());
		preparedStatement.setString(2, user.getTwitter());
		preparedStatement.setString(3, user.getInstagram());
		preparedStatement.setString(4, user.getWhatsapp());
		preparedStatement.setString(5, user.getSnapchat());
		
		
		}
	
}

