package edu.jsp.carsdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jsp.entity.Users;

public class Userdao {

	public void userRegister(Users u) throws SQLException {
		try (Connection con = ValidateToEmail_pwd.javadbConnection()) {
			String query = "insert into usertb values(?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, u.getU_id());
			pstmt.setString(2, u.getU_name());
			pstmt.setString(3, u.getU_email());
			pstmt.setLong(4, u.getU_mobile());
			pstmt.setString(5, u.getU_address());
			pstmt.setString(6, u.getU_pass());

			int rs = pstmt.executeUpdate();
			if (rs > 0) {
				System.out.println("User details is added");
				System.out.println("please login your details");
			} else {
				System.out.println("User details not added");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public boolean loginUserPage(String u_email1, String u_pass1) throws SQLException {
		try (Connection con = ValidateToEmail_pwd.javadbConnection()) {
			String query = "select u_email, u_pass FROM usertb where u_email = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, u_email1);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String Email = rs.getString("u_email");
				String Pwd = rs.getString("u_pass");

				if (Pwd.equals(u_pass1)) {
					System.out.println("Login successful " + Email);
					return Pwd.equals(u_pass1);
				} else {
					System.out.println("Incorrect Emailid & password. Try again.");
				}
			} else {
				System.out.println("User not found. Please verify your registation .");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public String emailcheck(String email) throws SQLException {
		try (Connection con = ValidateToEmail_pwd.javadbConnection()) {
			String query = "select u_email from usertb where u_email = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getString("u_email");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;

	}

	public void forgetPassword(Users u) throws SQLException {
		try (Connection con = ValidateToEmail_pwd.javadbConnection()) {
			String query = "update usertb SET u_pass=? where u_email=?";
			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, u.getU_pass());
			ps.setString(2, u.getU_email());

			int rs = ps.executeUpdate();
			if (rs > 0) {
				System.out.println("new password updated");
			} else {
				System.out.println("password is not update");
			}
		} catch (Exception e) {
			// TODO: handle
			e.printStackTrace();
		}
	}

	public void forgetEmailPassword(Users u) throws SQLException {
		try (Connection con = ValidateToEmail_pwd.javadbConnection()) {
			String query = "update usertb SET u_email=?, u_pass=? where u_name=?";
			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, u.getU_email());
			ps.setString(2, u.getU_pass());
			ps.setString(3, u.getU_name());

			int rs = ps.executeUpdate();
			if (rs > 0) {
				System.out.println("new email and password updated");
			} else {
				System.out.println("password is not update");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void closeConnection() throws SQLException {
		try (Connection con = ValidateToEmail_pwd.javadbConnection()) {
			if (con == null)
				System.out.println("Connection is Null");
			else if (con.isClosed())
				System.out.println("Connection is ALready Closed");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
