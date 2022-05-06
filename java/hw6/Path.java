package hw6;
import hw4.*;
import java.util.*;
public class Path implements Comparable<Path> {
	private List<Edge<String, Double>> edgesInPath;
	private double cost;
	private String destination;
	
	//constructor
	/**
	 * @modifies: this
	 * @effects: cost var = 0, initializes edgesInPath
	 */
	public Path() {
		edgesInPath = new ArrayList<Edge<String,Double>>();
		cost = 0;
		
	}
	//getter
	/**
	 * 
	 * @return: destination
	 */
	public String getDest() {
		destination = edgesInPath.get(edgesInPath.size()-1).getDestination();
		return destination;
	}
	/**
	 * 
	 * @return: edgesInPath
	 */
	public List<Edge<String,Double>> getEdges(){
		return edgesInPath;
		
	}
	//methods for adding edges
	/**
	 * @param: Edge<String, Double> e
	 * @modifies: this
	 * @effects: increases cost of path and adds new edge to the path
	 * 
	 */
	public void addEdgetoPath(Edge<String, Double> e) {
		edgesInPath.add(e);
		cost+=e.getWeight();
	}
	//get cost
	/**
	 * 
	 * @return: cost
	 */
	public double getCost() {
		return cost;
	}
	//get the start node of the path
	/**
	 * 
	 * @return: first node in edgesInPath
	 */
	public String firstNode() {
		return edgesInPath.get(0).getSource();
	}
	//get the last node of the path
	/**
	 * 
	 * @return: last node in edgesInPath
	 */
	public String lastNode() {
		return edgesInPath.get(edgesInPath.size()-1).getDestination();
	}
	//get the total cost
	/**
	 * 
	 * @return: total cost of the path
	 */
	public double totalCost() {
		double total = 0.0;
		for(Edge<String, Double> e1:edgesInPath) {
			total+=e1.getWeight();
		}
		return total;
	}
	//set cost
	/**
	 * 
	 * @param cost1
	 * @effects: cost is cost1
	 */
	public void setCost(double cost1) {
		cost = cost1;
	}
	//remove edge from path list
	/**
	 * 
	 * @param ind
	 * @effects: removes edges in path
	 */
	public void remove(int ind) {
		edgesInPath.remove(ind);
	}
	//copy method
	/**
	 * 
	 * @param p
	 * @modifies: cost to be p.cost
	 * @effects: adds edges in path
	 */
	public void copyPath(Path p) {
		this.edgesInPath.addAll(p.edgesInPath);
		this.cost = p.cost;
	}
	
	@Override
	/**
	 * @param: Path o
	 * @return: integer return value
	 */
	public int compareTo(Path o) {
		Path p = (Path) o;
		if(this.cost >= p.cost) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
	
	
	
	
}
