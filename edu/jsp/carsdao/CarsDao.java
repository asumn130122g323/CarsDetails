package edu.jsp.carsdao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

import com.jsp.entity.Cars;

public class CarsDao {
	static Scanner sc = new Scanner(System.in);

	public void carDetails(Cars c) throws SQLException {
		try (Connection con = ValidateToEmail_pwd.javadbConnection()) {
			String query = "insert into cars values(?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, c.getC_id());
			pstmt.setString(2, c.getC_name());
			pstmt.setString(3, c.getC_color());
			pstmt.setString(4, c.getC_model());
			pstmt.setString(5, c.getC_type());
			pstmt.setString(6, c.getC_no());
			pstmt.setDouble(7, c.getC_price());
			pstmt.setString(8, c.getdescription());

			int rs = pstmt.executeUpdate();
			if (rs > 0) {
				System.out.println("car details is added");

			} else {
				System.out.println("car details not added");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateCar(Cars c) throws SQLException {
		try (Connection con = ValidateToEmail_pwd.javadbConnection()) {
			String query = "update usertb SET c_id=?,c_name=?,c_color=?,c_model=?,c_type=?,c_no=?,c_price=?,c_description where c_id=?";
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setInt(1, c.getC_id());
			pstmt.setString(2, c.getC_name());
			pstmt.setString(3, c.getC_color());
			pstmt.setString(4, c.getC_model());
			pstmt.setString(5, c.getC_type());
			pstmt.setString(6, c.getC_no());
			pstmt.setDouble(7, c.getC_price());
			pstmt.setString(8, c.getdescription());
			int rs = pstmt.executeUpdate();

			if (rs > 0) {
				System.out.println("new password updated");
			} else {
				System.out.println("password is not update");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void viewAllCars() throws SQLException {
	    try (Connection con = ValidateToEmail_pwd.javadbConnection()) {
	        String query = "SELECT * FROM cars";
	        PreparedStatement s1 = con.prepareStatement(query);
	        ResultSet rs = s1.executeQuery();

	        while (rs.next()) {
	            System.out.println("Car ID: " + rs.getInt("c_id"));
	            System.out.println("Car Name: " + rs.getString("c_name"));
	            System.out.println("Car Color: " + rs.getString("c_color"));
	            System.out.println("Car Model: " + rs.getString("c_model"));
	            System.out.println("Car Type: " + rs.getString("c_type"));
	            System.out.println("Car No: " + rs.getString("c_no"));
	            System.out.println("Car Price: " + rs.getDouble("c_price"));
	            System.out.println("Car Description: " + rs.getString("description"));
	            System.out.println("-----------------------------------------------");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	public void fetchCarByName(String c_name) throws SQLException {
		try (Connection con = ValidateToEmail_pwd.javadbConnection()) {
			String query = "select * from cars where c_name=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, c_name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				System.out.println("car id:" + rs.getInt("c_id"));
				System.out.println(" car name:" + rs.getString("c_name"));
				System.out.println("car color:" + rs.getString("c_color"));
				System.out.println("car model" + rs.getString("c_model"));
				System.out.println("car type" + rs.getString("c_type"));
				System.out.println("car no" + rs.getString("c_no"));
				System.out.println("car prce:" + rs.getDouble("c_price"));
				System.out.println("car Description:" + rs.getString("description"));
				System.out.println("-----------------------------------------------");
			}
		} catch (Exception e) {
			// 
            e.printStackTrace();
		}
	}

	public void deleteCar(Cars c) throws SQLException {
		try (Connection con = ValidateToEmail_pwd.javadbConnection()) {
			String query = "delete from cars where c_id=? ";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, c.getC_id());
			int rows = pstmt.executeUpdate();
			System.out.println(rows > 0 ? rows + " rows Deleted" : " No rows Deleted");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//public void countCarByColor(String c_color) throws SQLException{
//	try (Connection con = ValidateToEmail_pwd.javadbConnection()) {
//	String query="select * from cars where c_color=?";
//	  PreparedStatement ps=con.prepareStatement(query);
//	  ps.setString(1,c_color);
//	  ResultSet rs=ps.executeQuery();
//	  int count=0;
//	  while (rs.next()) {
//                          		  
//   System.out.println("car id:" +rs.getInt("c_id"));
//   System.out.println(" car name:" +rs.getString("c_name") );
//   System.out.println("car color:"+rs.getString("c_color") );
//   System.out.println("car model"+rs.getString("c_model"));
//   System.out.println("car type"+ rs.getString("c_type"));
//   System.out.println("car no"+ rs.getString("c_no"));
//   System.out.println("car price:" +rs.getDouble("c_price"));
//	 System.out.println("car Description:" + rs.getString("description"));
//   System.out.println("-----------------------------------------------");
//    count++;
//    }System.out.println("Number of cars present in this color: " + count);
//    } catch (Exception e) {
// 
//     e.printStackTrace();
//    }
//
//}

	public void countCarByColor(String color) throws SQLException {
		try (Connection con = ValidateToEmail_pwd.javadbConnection()) {
			CallableStatement cs = con.prepareCall("CALL car_count(?,?)");

			cs.setString(1, color);
			cs.registerOutParameter(2,Types.INTEGER);
			cs.executeUpdate();

			int count = cs.getInt(2);
			System.out.println("Number of " + color + " cars: " + count);
		} catch (Exception e) {
			// 
               e.printStackTrace();
		}

	}
	
//	CREATE TABLE wishlist (
//			  w_id SERIAL PRIMARY KEY,
//			  c_id INT REFERENCES cars(c_id)
//			);
	
	public void addToWishlist(int whilid,int carid) throws SQLException {
	    try (Connection con = ValidateToEmail_pwd.javadbConnection()) {
	        String query = "insert into wishlist values (?,?)";
	        PreparedStatement pstmt = con.prepareStatement(query);
	        pstmt.setInt(1,whilid);
	        pstmt.setInt(2,carid);
	        int rows = pstmt.executeUpdate();
	        if (rows>0) {
	            System.out.println("Car added to wishlist");
	        } else {
	            System.out.println(" car not added to wishlist.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	public void viewWishlist() throws SQLException {
	   
	    try (Connection con = ValidateToEmail_pwd.javadbConnection()) {
	    	String query ="SELECT c.c_id, c.c_name, c.c_color, c.c_model, c.c_type, c.c_no, c.c_price, c.description from wishlist w JOIN cars c ON w.c_id = c.c_id";
	    	PreparedStatement pstmt = con.prepareStatement(query);
	        ResultSet rs = pstmt.executeQuery();
	        System.out.println("----- Wishlist -----");
	        while (rs.next()) {
	        	System.out.println("car id:" + rs.getInt("c_id"));
				System.out.println(" car name:" + rs.getString("c_name"));
				System.out.println("car color:" + rs.getString("c_color"));
				System.out.println("car model" + rs.getString("c_model"));
				System.out.println("car type" + rs.getString("c_type"));
				System.out.println("car no" + rs.getString("c_no"));
				System.out.println("car prce:" + rs.getDouble("c_price"));
				System.out.println("car Description:" + rs.getString("description"));
				System.out.println("-----------------------------------------------");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	public void closeConnection() throws SQLException {
		try (Connection con = ValidateToEmail_pwd.javadbConnection()) {
			if (con == null)
				System.out.println("Connection is null");
			else if (con.isClosed())
				System.out.println("Connection is ALready closed");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
