package edu.jsp.carsdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ValidateToEmail_pwd {

	static Scanner sc = new Scanner(System.in);

	public static boolean validateEmail(String email) {

		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		return email.matches(emailRegex);
	}

	public static boolean validatePassword(String password) {

		String pwdRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
		return password.matches(pwdRegex);
	}

	public static boolean otp() throws InterruptedException {

		int s = (int) (Math.random() * 1000000);
		System.out.println("OTP Generating....");
		Thread.sleep(2000);
		System.out.println("Your OTP: " + s);
		System.out.print("Enter Your OTP: ");
		int otp = sc.nextInt();
		if (otp == s) {
			System.out.println("OTP Verified Successfully");
			return true;
		} else {
			System.out.println("Incorrect OTP!");
			return false;
		}
	}

	public static Connection javadbConnection() {
		String url = "jdbc:postgresql://localhost:5432/jai";
		String user = "postgres";
		String pass = "root";
		Connection con = null;
		{
			try {
				Class.forName("org.postgresql.Driver");
				con = DriverManager.getConnection(url, user, pass);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return con;

	}

}