package hw6;
import java.io.IOException;
import java.util.*;
import hw4.*;
import hw5.MarvelParser;
public class MarvelPaths2{
	//member variables
	private Graph<String,String> initG;
	private Graph<String, Double> G;
	private TreeMap<String, TreeMap<String, Set<String>>> bp;
	//constructor
	public MarvelPaths2(){
		initG = new Graph<String, String>();
		G = new Graph<String, Double>();
		
		
	}
	public MarvelPaths2(Graph<String, Double> t) {
		G = t;
		bp = new TreeMap<String, TreeMap<String, Set<String>>>();
	}
	public Graph<String, Double> getGraph() {
		return G;
	}
	/**
	 * @param filename
	 * @modifies graph g 
	 * @effects create edges and nodes in the private member variable graph g
	 */
	public void createNewGraph(String filename) {
		G.getGraph().clear();
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
				
				//TreeMap<String,Set<String>> l = new TreeMap<String, Set<String>>();
				//bp.put(src, l);
				G.addNode(src);
					
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
						/*
						initG.addNode(dest);
						initG.addEdge(src, dest, key); */
						G.addNode(dest);
						Edge<String, Double> Temp = G.addEdge2(src,dest,1.0);
						Temp.setWeight(1.0/Temp.getDupe());
						/*
						int oc = 0;
						for(int h = 0; h < initG.getGraph().get(src).size();h++) {
							if(initG.getGraph().get(src).get(h).getDestination().equals(dest)) {
								oc+=1;
							}
						}
						if(oc > 1) {
							for(int h = 0; h < G.getGraph().get(src).size(); h++) {
								if(G.getGraph().get(src).get(h).getDestination().equals(dest)) {
									G.getGraph().get(src).get(h).setWeight(1.0/oc);
								}
							}
						} else if(oc == 1) {
							G.addEdge(src, dest, 1.0);
						}*/
						
					}
				}
				
				
			}
		}
		//sort final product
		
	}
	/**
	 * 
	 * @param CHAR1
	 * @param CHAR2
	 * @return shortest path string between the two parameters
	 */
	public String findPath(String CHAR1, String CHAR2) {
		String start = CHAR1;
		String dest = CHAR2;
		if(!(G.getGraph().containsKey(start)) && !(G.getGraph().containsKey(dest)) && !(start.equals(dest))) {
			//System.out.println( "unknown character " + start + "\n" + "unknown character " + dest + "\n");
			return  "unknown character " + start + "\n" + "unknown character " + dest + "\n";
		}
		else if(!(G.getGraph().containsKey(start))) {
			//System.out.println( "unknown character " + start + "\n");
			//this is an object not a string
			
			///////////////Reuben notes
			return "unknown character " + start + "\n";
		}
		else if(!(G.getGraph().containsKey(dest))) {
			return "unknown character " + dest + "\n";
		}
		else if(G.getGraph().containsKey(start) && start.equals(dest)) {
			//System.out.println( "path from " + start + " to " + dest + ":");
			//System.out.println( "total cost: 0.000\n");
			return "Path from " + start + " to " + dest + ":\n" + "total cost: 0.000\n";
			
		}
		PriorityQueue<Path> active = new PriorityQueue<Path>();
		List<String> finished = new ArrayList<String>();
		//initialize a map with running paths for each node
		Map<String, Path> minPaths = new HashMap<String, Path>();
		Iterator<String> graphKeys = G.getGraph().keySet().iterator();
		while(graphKeys.hasNext()) {
			String key = graphKeys.next();
			Path p = new Path();
			p.setCost(Double.MAX_VALUE);
			minPaths.put(key, p);
			
		}
		//add path to itself
		Edge<String, Double> e = new Edge<String, Double>(start,start,0.0);
		minPaths.get(start).addEdgetoPath(e);
		minPaths.get(start).setCost(0);
		active.add(minPaths.get(start));
		//while loop to go through graph paths 
		while(!(active.isEmpty())) {
			//remove minimum path from active
			Path min = active.poll();
			min.firstNode();
			min.lastNode();
			String minDest = min.getDest();
			
			if (minDest.equals(dest)){
				min.remove(0);
				Iterator<Edge<String, Double>> y = min.getEdges().iterator();
				String finalY = "";
				finalY+= "Path from " + start + " to " + dest + ":\n";
				while(y.hasNext()) {
					Edge<String, Double> l = y.next();
					finalY+=String.format(l.getSource() + " to " + l.getDestination() + " with weight %.3f\n",l.getWeight());
				}	
				finalY+=String.format("total cost: %.3f\n",min.totalCost());
					
				return finalY;
			}
			if(finished.contains(minDest)) {
				continue;
			}
			for(Edge<String,Double> e1:G.getGraph().get(minDest)) {
				if(!(finished.contains(e1.getDestination()))){
					Path minCop = new Path();
					minCop.copyPath(min);
				    minCop.addEdgetoPath(e1);
				    if(minPaths.get(e1.getDestination()).getCost() > minCop.getCost()) {
				    	minPaths.put(e1.getDestination(), minCop);
				    }
				    //add new path to active 
				    active.add(minCop);
				}
			}
			finished.add(minDest);
		}
		//System.out.println("path from " + start + " to " + dest + ":");
		//System.out.println("no path found\n");
		//Path r = new Path();
		return "path from " + start + " to " + dest + ":\n" + "no path found\n";
	
	}
}
