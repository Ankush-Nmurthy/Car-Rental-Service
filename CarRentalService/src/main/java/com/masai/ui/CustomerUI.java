package com.masai.ui;

import java.util.Scanner;

public class CustomerUI {
	static void customerRegistration(Scanner sc) {
		System.out.print("Enter your name : ");
		String name = sc.next();
		System.out.print("Enter your user-name : ");
		String username = sc.next();
		System.out.print("Enter your password : ");
		String password = sc.next();
		System.out.print("Enter your Location : ");
		String location = sc.next();
		System.out.print("Enter your Age : ");
		int Age = sc.nextInt();
	}
}
