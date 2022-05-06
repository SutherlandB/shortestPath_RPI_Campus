package hw7;
import java.util.*;
public class Building{
	private String buildingName;
	private int ID;
	private int coord1;
	private int coord2;
	
	//default constructor
	/**
	 * 
	 * @param building
	 * @param id
	 * @param coord1
	 * @param coord2
	 * @effects constructs a building object
	 */
	//constructor
	public Building(String building, int id, int coord1, int coord2) {
		this.buildingName = building;
		ID = id;
		this.coord1 = coord1;
		this.coord2 = coord2;
	}
	//getters
	/**
	 * 
	 * @return building's nama
	 */
	public String buildName() {
		return buildingName;
	}
	/**
	 * @return ID of the building
	 */
	public int getID() {
		return ID;
	}
	/**
	 * 
	 * @return 2nd coordinate of the building
	 */
	public int getCoord2() {
		return coord2;
	}
	/**
	 * 
	 * @return 1st coordinate of the building
	 */
	public int getCoord1() {
		return coord1;
	}
	//equals method

}