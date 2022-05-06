package hw7;
import hw4.*;
import hw5.MarvelParser;
import hw6.*;
import hw7.*;
import java.util.Scanner;


import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
 

public class CampusPaths{
	public static void main(Object object) {
		// TODO Auto-generated method stub
		//System.out.println("for once");
		Controller controller = new Controller();
		Scanner inp = new Scanner(System.in);
		boolean bre = true;
		while(bre) {
			String i = inp.nextLine();
			if( i.equals("r") || i.equals( "b") ||  i.equals("m") || i.equals("q")) {
				if(i.equals( "q") ) {
					break;
				}
				controller.input(i, inp);			
			}
			else {
				System.out.println("Unknown option");
			}
		}
		
	}
	
}