package com.masai.ui;

import java.util.Scanner;

public class AppMain {
	
	static void diaplayMenu() {
		System.out.println("1. Add car");
		System.out.println("2. view All cars");
		System.out.println("3. Update car");
		System.out.println("4. Delete car ");
		System.out.println("5. view single car details");
		System.out.println("6. Generate Report and Analytics ");
		System.out.println("7. ");
		System.out.println("0. Logout");
	}
	
	static void adminMenu(Scanner sc) {
		int choice  = 0;
		do {
			diaplayMenu();
			System.out.print("Enter selection : ");
			choice = sc.nextInt();
			switch(choice) {
			case 1: 
				AdminUI.addCar(sc);
				break;
			case 2:
//				viewAllCars(sc);
				break;
			case 3:
				AdminUI.updateCar(sc);
				break;
			case 4:
//				deleteCar(sc);
				break;
			case 5:
//				viewSingleCar(sc);
				break;
			case 6:
//				generateReport(sc);
				break;
			case 7:
				break;
			case 0:
				System.out.println("admin loged out successfully..");
			default:
				System.out.println("Invalid selection...");
			}
			
			
		}while(choice != 0);
	}
	
	static void adminLogin(Scanner sc) {
		System.out.print("Enter User-Name : ");
		String userName = sc.next();
		System.out.print("Enter Password : ");
		String password = sc.next();
		
		if(userName.equals("admin") && password.equals("admin")) {
			adminMenu(sc);
			System.out.println("login successfully");
		}
		else {
			System.out.println("Invalid userName or password...");
		}
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		do {
			System.out.println("1. Admin login");
			System.out.println("2. Customer Login");
			System.out.println("3. Customer Registration");
			System.out.println("0. Exit from App..");
			System.out.print("Enter your selection : ");
			
			choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				adminLogin(sc);
				break;
			case 2:
//				customerLogin();
				break;
			case 3:
//				customerRegistration();
				break;
			case 0:
				System.out.println("Exited from app successfully, \n Thanks for using our services...");
			default:
				System.out.println("Invalid Selection...");
			}
		}
		while(choice != 0);
	}
}
