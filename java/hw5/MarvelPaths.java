package hw5;
import hw4.Edge;
import hw4.*;
//import hw4.Graph.Edge;
import hw5.MarvelParser;
import java.io.IOException;
import java.util.*;

public class MarvelPaths{
	/**
	 * Specification field:
	 * 				Graph used to represent nodes and edges  
	 * 
	 * Abstraction function: 
	 *                     adjList = adjacency list
	 *                     edge list = edges
	 *                     
	 * Representation Invariant: 
	 *                     adjList != null
	 *                     edge source node must be in adjList
	 *                     edge destination node must be in adjList 
	 *                     edges   != null
	 *                     
	 *                     
	 */
	private Graph<String, String> g;
	
	/*
	 * @effects: create new instance of graph with empty node and edges list
	 */
	public MarvelPaths(){
		g = new Graph<String,String>();	
		
	}
	/**
	 * @return: graph 
	 */
	public Graph<String, String> getGraph() {
		return g;
	}
	
	/**
	 * @param filename
	 * @modifies graph g 
	 * @effects create edges and nodes in the private member variable graph g
	 */
	public void createNewGraph(String filename) {
		Map<String, Set<String>> charsInBooks = new HashMap<String, Set<String>>();
		Set<String> characters = new HashSet<String>();
		try {
		MarvelParser.readData(filename, charsInBooks, characters);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		//repeated edges have to be included
		
		Iterator<String> itr1 = charsInBooks.keySet().iterator();
		while(itr1.hasNext()) {
			String key = itr1.next();
			//value set has book character set
			Set<String> value = charsInBooks.get(key);
			//loop through that set's stuff
			Iterator<String> itr2 = value.iterator(); 
			Iterator<String> copy = value.iterator();
			
			//the second iterator to match with each of the characters that are being looped through
			
			for(int j = 0; j < value.size(); j++) {
				String src = itr2.next();
				g.addNode(src);
				
				Iterator<String> itr3 = value.iterator();
				//loop through same set and make edges with characters of the same book
				for(int k = 0; k < value.size();k++) {
					String dest = itr3.next();
					//no reflexive edges 
					if(src == dest) {
						continue;
					}
					//add edges to the graph
					else {
						g.addNode(dest);
						g.addEdge(src, dest, key);
					}
				}
			}
		}
		//sort final product
		
	}
	/**
	 * @requires graph be non-null
	 * @param node1, node2
	 * @return string of the path from node1 to node2 in the lexicographically shortest path.
	 */
	public String findPath(String node1, String node2) {
		
		if(!(g.getGraph().containsKey(node1)) && !(g.getGraph().containsKey(node2)) && !(node1.equals(node2))) {
			return "unknown character " + node1 + "\n" + "unknown character " + node2 + "\n";
		}
		else if(!(g.getGraph().containsKey(node1))) {
			return "unknown character " + node1 + "\n";
		}
		else if(!(g.getGraph().containsKey(node2))) {
			return "unknown character " + node2 + "\n";
		}
		else if(g.getGraph().containsKey(node1) && node1.equals(node2)) {
			return "path from " + node1 + " to " + node2 + ":\n";
		}
		String save = node1;
//		g.listChildren("PETERS, SHANA TOC");
//		System.out.println("Children: ");
//		System.out.println(g.getListKids());
		List<String> lst = new ArrayList<>(); //queued nodes
		lst.add(node1);
		String finalPath = "";
		Map<String, List<String>> paths = new HashMap<String,List<String>>();
		List<String> pList = new ArrayList<>();
		paths.put(node1, pList);
		while(lst.size() != 0) {
			node1 = lst.remove(0);
			if(node1.equals(node2)) {
				//string with paths
				finalPath+= "path from " + save + " to " + node2 + ":\n";
				for(int i = 0; i < paths.get(node1).size(); i++) {
					g.listChildren(paths.get(node1).get(i));					
					if(i == paths.get(node1).size()-1) {
						String firstOcc = g.mapEdges().get(node2).get(0);
						finalPath+= paths.get(node1).get(i) + " to " + node1 + " via " + firstOcc + "\n";
					}
					else {
						String firstOcc = g.mapEdges().get(paths.get(node1).get(i+1)).get(0);
						finalPath += paths.get(node1).get(i) + " to " + paths.get(node1).get(i+1) + " via " + firstOcc + "\n";
					}
				}
				return finalPath;
			}
			String next; 
			Collections.sort(g.getGraph().get(node1),(e1,e2)->e1.getDestination().equals(e2.getDestination()) ? e1.getWeight().compareTo(e2.getWeight()): e1.getDestination().compareTo(e2.getDestination()));
			Iterator<Edge<String, String>> scan = g.getGraph().get(node1).iterator();
			while(scan.hasNext()) {
				next = scan.next().getDestination();

				if(paths.containsKey(next)) {
					continue;
				}
				List<String> empt = new ArrayList<>(paths.get(node1));
				paths.put(next, empt);
				paths.get(next).add(node1);
				lst.add(next);
			}
		}
		return "path from " + save +" to " + node2 + ":\nno path found\n";
	}
}