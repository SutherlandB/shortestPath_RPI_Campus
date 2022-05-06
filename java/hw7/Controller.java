package hw7;
import hw7.Model;

import java.util.Scanner;

import hw7.Controller;

public class Controller {
	//private member variables
	private Model mod;
	private View view;
	//constructor
	/**
	 * @effects: default constructor
	 */
	public Controller() {
		mod = new Model();
		mod.createNewGraph("data/RPI_map_data_Nodes.csv", "data/RPI_map_data_Edges.csv");
		view = new View(mod, this);
		
	}
	/**
	 * 
	 * @param mo
	 * @param co
	 * @modifies mod and view 
	 * @effects construct controller object with pre-existing model and view 
	 */
	public Controller(Model mo, View co) {
		mod = mo;
		this.view =co;
	}
	/**
	 * 
	 * @param in
	 * @param inS
	 * @effects response for the client after pressing a certain menu option
	 */
	//inputs
	public void input(String in, Scanner inS) {
		if(in.equals("b")) {
			view.showBuildings(mod.getBuildings());
			
		} 
		else if(in.equals("r")) {
			String arg1 = "";
			String arg2 = ""; 
			view.promptUser(arg1, arg2, inS);
			arg1 = inS.nextLine();
			arg2 = inS.nextLine();
			view.showPath(mod.shortestPath(arg1, arg2));
		}
		else if(in.equals("m")) {
			view.listMenu();
		} 
		else {
			System.out.println("Unknown option\n");
		}
		
	}
	/**
	 * 
	 * @param m
	 * @modifies mod
	 */
	public void setModel(Model m) {
		// TODO Auto-generated method stub
		mod = m;
	}
}
