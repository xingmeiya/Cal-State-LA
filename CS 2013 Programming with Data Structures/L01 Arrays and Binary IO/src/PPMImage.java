/*
L01 Arrays and Binary I/O
Noelani Mishina Hinh
CS 2013-08 Lab
PPMImage.java
2/1/2024
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PPMImage {
	public static final String MAGIC_NUMBER = "P6";
	private String magicNumber;
	private int width;
	private int height;
	private int maxColorValue;
	private char[] raster;
	
	public PPMImage(String imageFileName) throws IOException {
		if(!imageFileName.endsWith(".ppm")) {
			throw new IOException("Input filename must end with .ppm; " 
					+ "got " + imageFileName);
		}
		readImage(imageFileName);
	}

	private void readImage(String imageFileName) throws FileNotFoundException,
	                                                    IOException {
		try {
			FileInputStream fis = 
					new FileInputStream(new File(imageFileName));		
		
			// read magic number
			magicNumber = "" 
			              + (char) fis.read()
			              + (char) fis.read();
			
			if (!magicNumber.equals(MAGIC_NUMBER)) {
				throw new IOException("expected " + MAGIC_NUMBER + ", got"
						+ magicNumber + " as magic number");
			}
			
			// omit line feed after magic number
			fis.skip(1);
			
			width = readNum(fis);
			height = readNum(fis);	
			maxColorValue = readNum(fis);
			
			raster = new char[width*height*3];
			for(int i = 0; i < raster.length; i++) {
				raster[i] = (char)fis.read();
			}
			
			fis.close();

		} catch(FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			
		} 
	}

	public void writeFile(String outputImageFileName) throws FileNotFoundException,
			                                                 IOException {

	    // TO DO: Should also accept
		if(!outputImageFileName.endsWith(".ppm")) {
			throw new IOException("Output filename must end with .ppm; " 
					+ "got " + outputImageFileName);
		}
		
		try { 
			FileOutputStream fos = 
					new FileOutputStream(outputImageFileName);
			
			// write magic number
			fos.write(magicNumber.charAt(0)); // write 'P'
			fos.write(magicNumber.charAt(1)); // write '6'
			
			// write newline
			fos.write('\n');
			
			// write width, height, maxColorValue
			writeNum(fos, width);
			fos.write(' ');
			writeNum(fos, height);
			fos.write('\n');
			writeNum(fos, maxColorValue);
			fos.write('\n');
			
			// write raster data
			for(int i = 0; i < raster.length; i++) {
				fos.write(Math.min(raster[i], maxColorValue));
			}
			
			fos.flush();
			fos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}	
	}
	
	private void writeNum(FileOutputStream fos, int num) throws IOException {
		String digits = Integer.toString(num);

		for(int i = 0; i < digits.length(); i++) {
			fos.write(digits.charAt(i));
		}
	}
	
	private int readNum(FileInputStream fis) throws IOException {
		StringBuilder digits = new StringBuilder();
		//String s = "";
		char d;

		do {
			d = (char)fis.read();
			digits.append(d);
			//s += d;
		} while(!Character.isWhitespace((int)d));

		return Integer.parseInt(digits.toString().trim());
	}

	//Creates grayscale image
	//Casts double into a char value, assigns to raster [i] according to formula (same for following raster indexes)
	public void grayscale() {
		for (int i = 0; i < raster.length; i += 3) {
			//Each pixel has 3 pieces of data, for R, G, and B
			raster [i]     = (char) ((raster [i] * .299) + (raster [i + 1] * .587) + (raster [i + 2] * .114));
			raster [i + 1] = (char) ((raster [i] * .299) + (raster [i + 1] * .587) + (raster [i + 2] * .114));
			raster [i + 2] = (char) ((raster [i] * .299) + (raster [i + 1] * .587) + (raster [i + 2] * .114));
			/* Given information for grayscale
			R' = (R * .299) + (G * .587) + (B * .114)
			G' = (R * .299) + (G * .587) + (B * .114)
			B' = (R * .299) + (G * .587) + (B * .114)
	 		*/
		}
	}

	//Creates sepia image
	//Casts double into a char value, assigns to raster [i] according to formula (same for following raster indexes)
	public void sepia() {
		for (int i = 0; i < raster.length; i += 3) {
			raster[i]     = (char) ((raster[i] * .393) + (raster[i + 1] * .769) + (raster[i + 2] * .189));
			raster[i + 1] = (char) ((raster[i] * .349) + (raster[i + 1] * .686) + (raster[i + 2] * .168));
			raster[i + 2] = (char) ((raster[i] * .272) + (raster[i + 1] * .534) + (raster[i + 2] * .131));
			/* Given information for sepia
			R' = (R * .393) + (G *.769) + (B * .189);
			G' = (R * .349) + (G *.686) + (B * .168);
			B' = (R * .272) + (G *.534) + (B * .131);
	 		*/
		}
	}

	//Creates negative image
	//Casts double into a char value, assigns to raster [i] according to formula (same for following raster indexes)
	public void negative() {
		for (int i = 0; i < raster.length; i += 3) {
			raster[i] = (char) ((255 - raster[i]));
			raster[i + 1] = (char) ((255 - raster[i + 1]));
			raster[i + 2] = (char) ((255 - raster[i + 2]));
		/* Given information for negative
		R' = 255 - R;
		G' = 255 - G;
		B' = 255 - B;
		*/
		}
	}

	//Creates custom image
	//Casts double into a char value, assigns to raster [i] according to custom formula (same for following raster indexes)
	public void custom() {
		for (int i = 0; i < raster.length; i += 3) {
			raster [i]     = (char) ((raster [i] * .2) + (raster [i + 1] * .1) + (raster [i + 2] * .3));
			raster [i + 1] = (char) ((raster [i] * .2) + (raster [i + 1] * .1) + (raster [i + 2] * .3));
			raster [i + 2] = (char) ((raster [i] * .2) + (raster [i + 1] * .1) + (raster [i + 2] * .3));
		}
	}
}

//NOTES
//char r, g, b
//r = raster [i];
//g = raster [i + 1];
//b = raster [i + 2];

//EX:
//public void cool() {
//		for (int i = 0; i < raster.length; i += 3) {
//			raster[]

//Do one extra filter for lab