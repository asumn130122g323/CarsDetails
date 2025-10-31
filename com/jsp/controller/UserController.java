package com.jsp.controller;

import java.util.Scanner;
import org.postgresql.util.PSQLException;
import com.jsp.entity.Users;
import edu.jsp.carsdao.Userdao;
import edu.jsp.carsdao.ValidateToEmail_pwd;

public class UserController {
	static Userdao udao = new Userdao();
	static boolean loginSuccess;
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws Exception, PSQLException {
		boolean exit = false;
		while (!exit) {
			System.out.println("***** login/Register Page *****");
			System.out.println("Enter 1 to registeruser page:");
			System.out.println("Enter 2 to loginPage:");
			System.out.println("Enter 3 to forgot password");
			System.out.println("Enter 4 to forgot both email & password");
			System.out.println("Enter 5 to logout");
			System.out.print("Enter your choice: ");
			int choice = sc.nextInt();
			sc.nextLine();

			Users u = new Users();

			switch (choice) {
			case 1: {
				System.out.println(" User register page ");
				System.out.print("Enter the userid: ");
				int u_id = sc.nextInt();
				sc.nextLine();

				System.out.print("Enter the u_name: ");
				String u_name = sc.nextLine();

				System.out.print("Enter the u_emailid: ");
				String u_email = sc.nextLine();

				if (!ValidateToEmail_pwd.validateEmail(u_email)) {
					System.out.println("Invalid email format! Please try again.");
					break;
				}

				System.out.print("Enter the u_mobile: ");
				long u_mobile = sc.nextLong();
				sc.nextLine();

				System.out.print("Enter the u_address: ");
				String u_address = sc.nextLine();

				System.out.print("Enter the u_password : ");
				String u_pass = sc.nextLine();

				if (!ValidateToEmail_pwd.validatePassword(u_pass)) {
					System.out.println(
							"Invalid password! must contain at least:\n- 8 characters\n- 1 uppercase\n- 1 lowercase\n- 1 digit\n- 1 special character");
					break;
				}

				u.setU_id(u_id);
				u.setU_name(u_name);
				u.setU_email(u_email);
				u.setU_mobile(u_mobile);
				u.setU_address(u_address);
				u.setU_pass(u_pass);

				udao.userRegister(u);
				System.out.println("user register successfully!");
			}
				break;

			case 2: {
				System.out.println("User login page");
				System.out.print("Enter the u_emailid: ");
				String u_email1 = sc.nextLine();

				if (!ValidateToEmail_pwd.validateEmail(u_email1)) {
					System.out.println("Invalid email format! Please enter a valid email.");
					break;
				}

				System.out.print("Enter the u_password : ");
				String u_pass1 = sc.nextLine();

				if (u_pass1.isEmpty()) {
					System.out.println("Password cannot be empty!");
					break;
				}

				System.out.println("=====================================");
				loginSuccess = udao.loginUserPage(u_email1, u_pass1);
				if (loginSuccess) {
					System.out.println("pls wait");
					try {

						for (int i = 0; i < 5; i++) {
							Thread.sleep(100);
							System.out.print(".");
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					CarsController.carDetails();
				}
			}
				break;

			case 3: {
				System.out.println("===========Change the password===========");
				System.out.print("Enter emailid: ");
				String email = sc.nextLine();

				if (!ValidateToEmail_pwd.validateEmail(email)) {
					System.out.println("Invalid email format! Please enter a valid email.");
					break;
				}

				String em = udao.emailcheck(email);
				if (em == null) {
					System.out.println("Email not found in our records!");
					break;
				}

				boolean otpVerified = ValidateToEmail_pwd.otp();
				if (!otpVerified) {
					System.out.println("Invalid OTP! Password reset cancelled.");
					sc.nextLine();
					break;
				}

				System.out.print("Enter new password: ");
				u.setU_pass(sc.nextLine());

				if (!ValidateToEmail_pwd.validatePassword(u.getU_pass())) {
					System.out.println(
							"Invalid password! must contain at least:\n- 8 characters\n- 1 uppercase\n- 1 lowercase\n- 1 digit\n- 1 special character");
					break;
				}
				u.setU_email(email);
				udao.forgetPassword(u);

			}
				break;

			case 4: {
				System.out.println("===========Change the password===========");
				System.out.print("Enter user name: ");
				u.setU_name(sc.nextLine());

				System.out.print("Enter new emailid: ");
				u.setU_email(sc.nextLine());

				if (!ValidateToEmail_pwd.validateEmail(u.getU_email())) {
					System.out.println("Invalid email format! Please enter a valid email.");
					break;
				}

				System.out.print("Enter new password: ");
				u.setU_pass(sc.nextLine());
				if (!ValidateToEmail_pwd.validatePassword(u.getU_pass())) {
					System.out.println(
							"Invalid password! must contain at least:\n- 8 characters\n- 1 uppercase\n- 1 lowercase\n- 1 digit\n- 1 special character");
					break;
				}

				udao.forgetEmailPassword(u);
			}
				break;

			case 5: {
				udao.closeConnection();
				System.out.println("Logging out");
				try {

					for (int i = 0; i < 5; i++) {
						Thread.sleep(500);
						System.out.print(".");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("logout successfully");
				System.out.println("Thanks for using our app. Come again! Bye.");
				exit = true;

			}
				break;
			}

		}

	}
}
