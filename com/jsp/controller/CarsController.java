package com.jsp.controller;

import com.jsp.entity.*;

import java.util.Scanner;

import org.postgresql.util.PSQLException;

import edu.jsp.carsdao.CarsDao;

public class CarsController {

	static CarsDao cdao = new CarsDao();
	static Scanner sc = new Scanner(System.in);

	public static void carDetails() throws Exception, PSQLException {

		Cars c = new Cars();

		if (UserController.loginSuccess) {
			System.out.println("Login successful!");
			boolean carMenu = true;

			while (carMenu) {
				System.out.println("=====Car Menu=====");
				System.out.println("1. Register Car");
				System.out.println("2. View All Cars");
				System.out.println("3. Update All Cars");
				System.out.println("4. fetch details Car");
				System.out.println("5.count of car by color");
				System.out.println("6.delete the car by id");
				System.out.println("7.Add to Wishlist");
				System.out.println("8.View Wishlist");
				System.out.println("9. Exit");
				System.out.print("Enter your choice: ");
				int choice1 = sc.nextInt();
				sc.nextLine();

				switch (choice1) {
				case 1: {
					System.out.println("--------register the car--------- ");
					System.out.print("Enter the c_id: ");
					int c_id = sc.nextInt();
					sc.nextLine();

					System.out.print("Enter the c_name: ");
					String c_name = sc.nextLine();

					System.out.print("Enter the c_color: ");
					String c_color = sc.nextLine();

					System.out.print("Enter the c_model: ");
					String c_model = sc.nextLine();

					System.out.print("Enter the c_type: ");
					String c_type = sc.nextLine();

					System.out.print("Enter the c_no: ");
					String c_no = sc.nextLine();

					System.out.print("Enter the c_price : ");
					double c_price = sc.nextDouble();
					
					System.out.print("Enter the description : ");
					String description= sc.nextLine();

					c.setC_id(c_id);
					c.setC_name(c_name);
					c.setC_color(c_color);
					c.setC_model(c_model);
					c.setC_type(c_type);
					c.setC_no(c_no);
					c.setC_price(c_price);
					c.setdescription(description);

					cdao.carDetails(c);
					System.out.println("car registed successfully!");

				}
					break;

				case 2: {
					System.out.println("----- View All Cars -----");
					cdao.viewAllCars();

				}
					break;

				case 3: {
					System.out.print("update the car records: ");
					System.out.print("Enter c_id: ");
					c.setC_id(sc.nextInt());

					System.out.print("Enter new car name: ");
					c.setC_name(sc.nextLine());

					System.out.print("Enter new car color: ");
					c.setC_color(sc.nextLine());

					System.out.print("Enter new car model: ");
					c.setC_model(sc.nextLine());

					System.out.print("Enter new car type: ");
					c.setC_type(sc.nextLine());

					System.out.print("Enter new car no: ");
					c.setC_no(sc.nextLine());

					System.out.print("Enter new car price: ");
					c.setC_price(sc.nextDouble());
					
					System.out.print("Enter the description : ");
                    c.setdescription(sc.nextLine());
					
					cdao.updateCar(c);

				}
					break;

				case 4: {
					System.out.println("========fetchCarByName=========");
					System.out.print("Enter the car name to fetch details: ");
					String c_name = sc.nextLine();
					cdao.fetchCarByName(c_name);
				}
					break;

				case 5: {
					System.out.println("========Count Car by Color=========");
					System.out.print("Enter the car color: ");
					String color = sc.nextLine();
					cdao.countCarByColor(color);
				}
					break;

				case 6: {
					System.out.println("Enter the c_id to delete car details: ");
					int cid = sc.nextInt();
					c.setC_id(cid);
					cdao.deleteCar(c);
				}
					break;
					
				case 7: {
				    System.out.println("=========Add to Wishlist========");
				    System.out.print("Enter whilist id to add: ");
				    int whilid = sc.nextInt();  
				    System.out.print("Enter Car id to add: ");
				    int carid = sc.nextInt();  
				    cdao.addToWishlist(whilid,carid);
				}
				break;
				
				 case 8:{
			            System.out.println("----- View Wishlist -----");
			            cdao.viewWishlist();
				 }break;
				
				case 9: {
					System.out.println("Exiting");
					try {

						for (int i = 0; i < 5; i++) {
							Thread.sleep(100);
							System.out.print(".");
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					System.out.println("\nThank you for using our app!");
					carMenu = false;

				}
					break;

				}
			}
		}
	}
}
