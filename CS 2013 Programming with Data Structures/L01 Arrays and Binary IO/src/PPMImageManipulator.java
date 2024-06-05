/*
L01 Arrays and Binary I/O
Noelani Mishina Hinh
CS 2013-08 Lab
PPMImageManipulator.java
2/1/2024
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class PPMImageManipulator {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter relative path of a PPM file: ");
		String filepath = input.nextLine().trim();
		
		try {
			PPMImage img = new PPMImage(filepath);

			//Asks for user input to select a filter or copy without altering
			System.out.println("How would you like to manipulate this file?");

			System.out.println("\t[G] Convert to grayscale.");
			System.out.println("\t[S] Convert it sepia.");
			System.out.println("\t[N] Convert it to its negative.");
			System.out.println("\t[M] Convert it to its custom.");
			//EX: System.out.println("\t[CL] Convert it to its cool.");
			System.out.println("\t[C] Copy without altering.");
			
			char operation = input.nextLine().charAt(0);
			operation = Character.toUpperCase(operation);

			//Converts image to selected filter with user input
			switch (operation) {
			case 'G': img.grayscale(); break;
			case 'S': img.sepia(); break;
			case 'N': img.negative(); break;
			case 'C': /* do nothing... */ break;
			case 'M': img.custom(); break;
			//EX: case 'W': img.warm(); break;
			default: 
				System.out.println(operation + " is not a recognized command.");
				System.out.println("Exiting.");
				System.exit(1);
			}
			
			System.out.print("Enter filepath for the manipulated image: ");
			filepath = input.nextLine().trim();
			img.writeFile(filepath);
			
			System.out.println("Thank you for using PPMImageManipulator!");
			
		} catch (FileNotFoundException e) {
			System.out.println(filepath + " does not exist.");
		} catch (IOException e) {
			System.out.println("Could not complete request.");
			e.printStackTrace();
		}	
	}
}
