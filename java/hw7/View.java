package hw7;
import hw7.Model;
import java.util.*;
import java.util.Map;

import hw7.Controller;

public class View {
	//member variables
	private Model m;
	private Controller c;
	/**
	 * @effects: default constructor
	 */
	public View() {
		m = new Model();
		c = new Controller();
	}
	/**
	 * 
	 * @param mo
	 * @param co
	 * @modifies mo and cat 
	 * @effects construct view object with pre-existing model and controller 
	 */
	public View(Model mo, Controller cat) {
		this.m = mo;
		this.c = cat;
		
		
	}
	/**
	 * 
	 * @param buildings
	 * @effects prints to outputStream
	 */
	public String showBuildings(Map<String, Building> buildings) {
		// TODO Auto-generated method stub
		List<String> key = new ArrayList<String>();
		key.addAll(buildings.keySet());
		Collections.sort(key);
		Iterator<String> builds = key.iterator();
		String fin = "";
		while(builds.hasNext()) {
			String name = builds.next();
			System.out.println(name + "," + m.getBuildings().get(name).getID());
		}
		
		return fin;
		
	}
	/**
	 * 
	 * @param arg1
	 * @param arg2
	 * @param in
	 * @effects prints to the user a prompt
	 */
	public void promptUser(String arg1, String arg2, Scanner in) {
		// TODO Auto-generated method stub
		System.out.print("First building id/name, followed by Enter: Second building id/name, followed by Enter: ");
		//System.out.println("Second building id/name, followed by Enter: ");
		//arg1 = in.nextLine();
		//arg2 = in.nextLine();	
	}
	/**
	 * @effects prints to the user what menu options are left
	 */
	public void listMenu() {
		System.out.println("b lists all buildings\nr prints directions for the shortest route between any two buildings\nq quits the program\nm prints a menu of all commands");
	}
	/**
	 * 
	 * @param shortestPath
	 * @effects shows the string path for two existing nodes 
	 */
	public void showPath(String shortestPath) {
		System.out.print(shortestPath);
		
	}
	
}
