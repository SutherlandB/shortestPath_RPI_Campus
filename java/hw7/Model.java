package hw7;
import hw4.*;
import hw5.MarvelParser;
import hw6.*;

import java.io.IOException;
import java.util.*;
public class Model {
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
	//private member var
	private Graph<String, Double> G;
	private Map<String, Building> IDbuildInfo = new HashMap<String, Building>(); 
	private Map<String, Building> bNameInfo = new HashMap<String, Building>();
	private Map<String, List<String>> connections = new HashMap<String, List<String>> ();
	
	//constructor
	/*
	 * @effects: create new instance of graph with empty node and edges list
	 */
	public Model(){
		G = new Graph<String,Double>();	
	}
	//getter
	public Map<String, Building> getBuildings(){
		return bNameInfo;
		
	}
	public Map<String, Building> getIDs(){
		return IDbuildInfo;
	}
	
	/**
	 * @return: graph 
	 */
	public Graph<String, Double> getGraph() {
		return G;
	}
	/**
	 * @param filename
	 * @modifies graph g 
	 * @effects create edges and nodes in the private member variable graph g
	 */
	public void createNewGraph(String filename, String filename2) {
		IDbuildInfo = new HashMap<String, Building>(); 
		bNameInfo = new HashMap<String, Building>();
		connections = new HashMap<String, List<String>> ();
		//create the graph now...
		if(filename.equals("data/RPI_map_data_Nodes.csv")) {
			try {
					MapParser.readData(filename, IDbuildInfo, bNameInfo, connections);
			}
				catch(IOException e) {
					e.printStackTrace();
				}
		}
		if (filename2.equals("data/RPI_map_data_Edges.csv")) {
			try {
				MapParser.readData(filename2, IDbuildInfo, bNameInfo, connections);
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			//System.out.println(connections);
			Iterator<String> itr1 = connections.keySet().iterator();
			//System.out.println("size: " + connections.size());
			while(itr1.hasNext()) {
				String key = itr1.next().toString();
				G.addNode(key);
				for(int i = 0; i < connections.get(key).size(); i++) {
					//making edges for every node with distance 
					double finWeight = Math.sqrt((IDbuildInfo.get(connections.get(key).get(i)).getCoord2()-IDbuildInfo.get(key).getCoord2())*(IDbuildInfo.get(connections.get(key).get(i)).getCoord2()-IDbuildInfo.get(key).getCoord2())+((IDbuildInfo.get(connections.get(key).get(i)).getCoord1()-IDbuildInfo.get(key).getCoord1()))*((IDbuildInfo.get(connections.get(key).get(i)).getCoord1()-IDbuildInfo.get(key).getCoord1())));
					G.addEdge2(key,connections.get(key).get(i).toString(), finWeight);
				}
			}
			//add remaining nodes
			Iterator<String> itr2 = IDbuildInfo.keySet().iterator();
			while(itr2.hasNext()) {
				String key = itr2.next();
				if(!(G.getGraph().containsKey(key))) {
					G.addNode(key);
				}
			}
		}
		
	}
	/**
	 * 
	 * @param r
	 * @return final string with building names and intersections
	 */
	public String replaceID(String r) {
		
		String[] lst = r.split("\n");
		String str = "";
		ArrayList<String> flst = new ArrayList<String>(Arrays.asList(lst));
		
		for(int i = 0; i < flst.size();i++) {
			String y = flst.get(i);
			String[] innerlst = y.split(" ");
			ArrayList<String> words = new ArrayList<String>(Arrays.asList(innerlst));
			for(int j = 0; j < words.size(); j++) {
				String currWor = words.get(j).replace(":", ""); 
				//System.out.println("this is the length " + currWor.length());
				//System.out.println(IDbuildInfo.get(currWor));
				if(IDbuildInfo.containsKey(currWor)) {
					if(IDbuildInfo.get(currWor).buildName().length()!= 0) {
						currWor = IDbuildInfo.get(currWor).buildName();
					}
				}
				if(j == words.size()-1 && i == 0) {
					str+=currWor + ":";
				}
				else {
					str+=currWor + " ";
				}
			}
			str+= "\n";
		}
		
		/*
		ArrayList<String> y = new ArrayList<String>();
		ArrayList<ArrayList<String>> nlst = new ArrayList<ArrayList<String>>(Arrays.asList(lst));
		for(int i =0; i < nlst.size(); i++) {
			String wow = nlst.get(i).replace(':', );
			System.out.println(wow);	
			System.out.println(wow.getClass());
			System.out.println("this is the length " + wow.length());
			if(IDbuildInfo.containsKey(wow)) {
				if(IDbuildInfo.get(wow).buildName().length()!= 0) {
					System.out.println(wow);
					System.out.println("this is the length2 " + wow.length());
					nlst.set(i,IDbuildInfo.get(wow).buildName());
				}
			}
			str+=nlst.get(i) + " ";
		}*/
		return str;
		
	}
	/**
	 * 
	 * @param node1
	 * @param node2
	 * @return shortest path string between the two parameters
	 */
	public String shortestPath(String node1, String node2) {
		MarvelPaths2 mp = new MarvelPaths2(G);
		int y2 = 0;
		int y1 = 0;
		int x2 = 0;
		int x1 = 0;
		if(node1.matches("-?(0|[1-9]\\d*)") && node2.matches("-?(0|[1-9]\\d*)")) {
			if(IDbuildInfo.get(node1)== null && IDbuildInfo.get(node2)== null) {
				if(node1.equals(node2)) {
					return "Unknown building: [" + node1 + "]" ;
				}
				return "Unknown building: [" + node1 + "]\nUnknown building: [" + node2 + "]\n" ;
			}

		else if(IDbuildInfo.get(node1).buildName().isEmpty() && IDbuildInfo.get(node2).buildName().isEmpty()) {
			if(node1.equals(node2)) {
				return "Unknown building: [" + node1 + "]" ;
			}
				return "Unknown building: [" + node1 + "]\nUnknown building: [" + node2 + "]\n" ;
			}
			else if(IDbuildInfo.get(node1).buildName().isEmpty()) {
				return "Unknown building: [" + node1 + "]\n";
			}
			else if(IDbuildInfo.get(node2).buildName().isEmpty()) {
				return "Unknown building: [" + node2 +"]\n";
			}				
			String save = replaceID(mp.findPath(node1, node2));
			String finalStr = "";
			String secDest = "";
			String[] lst = save.split("\n");
			String str = "";
			ArrayList<String> flst = new ArrayList<String>(Arrays.asList(lst));
			for(int i = 0; i < flst.size();i++) {
				if(i == 0) {
					finalStr+=flst.get(i) + "\n";
					continue;
				} else if(i == flst.size()-1) {
					String[] lastLine = flst.get(i).split(" ");
					ArrayList<String> l = new ArrayList<String>(Arrays.asList(lastLine));
					if(l.get(l.size()-1).contains(".")) {
						finalStr+="Total distance: " + l.get(l.size()-1) + " pixel units.\n";
					}else {
						finalStr+= "There is no path from " + IDbuildInfo.get(node1).buildName() + " to " + IDbuildInfo.get(node2).buildName() + ".\n";
					}
				}
				else if(i > 0 && i < flst.size()-1){
					//System.out.println("this rn" + i);
					String[] lst2 = flst.get(i).split(" to ");
					ArrayList<String> inner = new ArrayList<String>(Arrays.asList(lst2));
					//System.out.println(inner);
					if(bNameInfo.containsKey(inner.get(0))) {
						x1 = bNameInfo.get(inner.get(0)).getCoord1();
						y1 = bNameInfo.get(inner.get(0)).getCoord2();
						//System.out.println("x1:" + x1 + " and " + y1);
					}
					else if(IDbuildInfo.containsKey(inner.get(0))) {
						x1 = IDbuildInfo.get(inner.get(0)).getCoord1();
						y1 = IDbuildInfo.get(inner.get(0)).getCoord2();
						//System.out.println("x1:" + x1 + " and " + y1);
					}
					String[] otherin = inner.get(1).split(" with ");
					ArrayList<String> otherin2 = new ArrayList<String>(Arrays.asList(otherin));
					secDest = otherin2.get(0);
					if(bNameInfo.containsKey(secDest)) {
						secDest = bNameInfo.get(secDest).buildName();
					}else {
						secDest = "Intersection " + secDest;
					}
					if(bNameInfo.containsKey(otherin2.get(0))) {
						x2 = bNameInfo.get(otherin2.get(0)).getCoord1();
						y2 = bNameInfo.get(otherin2.get(0)).getCoord2();
						//System.out.println("x2:" + x2 + " and " + y2);
					}
					else if(IDbuildInfo.containsKey(otherin2.get(0))) {
						x2 = IDbuildInfo.get(otherin2.get(0)).getCoord1();
						y2 = IDbuildInfo.get(otherin2.get(0)).getCoord2();
						//System.out.println("x2:" + x2 + " and " + y2);
					}
				}
				
				double dir = Math.atan2((y2-y1),(x2-x1))* (180/Math.PI);
				dir = dir+90;
				if (dir < 0) {

				    dir = 360 + dir;
				}

				else {
				    dir = dir;
				}
				/*
				 * if((y2-y1) > 0 && (x2-x1) > 0) { dir = 90-dir; } else if((y2-y1) < 0 &&
				 * (x2-x1) < 0) { dir = 270-dir; } else if((y2-y1) > 0 && (x2-x1) < 0){ dir =
				 * 270-dir;
				 * 
				 * } else if((y2-y1) <0 && (x2-x1) > 0) { dir = 90-dir; }
				 */
				//System.out.println("my dir " + dir);
				String direction = "";
				if(dir >=0 && dir < 22.5 || dir > 337.5 ) {
					direction = "North";
				}
				else if(dir >= 22.5 && dir < 67.5) {
					direction = "NorthEast";
				}
				else if(dir >= 67.5 && dir < 112.5) {
					direction = "East";
				}
				else if(dir >= 112.5 && dir < 157.5) {
					direction = "SouthEast";
				}
				else if(dir >= 157.5 && dir < 202.5) {
					direction = "South";
				}
				else if(dir >= 202.5 && dir < 247.5) {
					direction = "SouthWest";
				}
				else if(dir >= 247.5 && dir < 292.5 ) {
					direction = "West";
				}
				else if(dir >= 292.5 && dir < 337.5) {
					direction = "NorthWest";
				}
				if(i < flst.size()-1)
				finalStr+="\tWalk " + direction + " to (" + secDest + ")\n";
				//System.out.println("this is a direction " + direction);
			}

			return finalStr;
		}
		if(node1.matches("-?(0|[1-9]\\d*)")) {
			if(IDbuildInfo.get(node1) == null && bNameInfo.get(node2) == null) {
				return "Unknown building: [" + node1 + "]\nUnknown building: [" + node2 + "]\n" ;
			}
			else if(IDbuildInfo.get(node1) == null ) {
				return "Unknown building: [" + node1 + "]";
			}
			else if(IDbuildInfo.get(node1).buildName().isEmpty() && bNameInfo.get(node2) == null) {
				return "Unknown building: [" + node1 + "]\nUnknown building: [" + node2 + "]\n" ;
			}
			else if(IDbuildInfo.get(node1).buildName().isEmpty()) {
				return "Unknown building: [" + node1 + "]\n";
			}
			else if(bNameInfo.get(node2)==null) {
				return "Unknown building: [" + node2 +"]\n";
			}
			if(bNameInfo.containsKey(node2)) {
				String save = replaceID(mp.findPath(node1, Integer.toString(bNameInfo.get(node2).getID())));
				
				String finalStr = "";
				String secDest = "";
				String[] lst = save.split("\n");
				String str = "";
				ArrayList<String> flst = new ArrayList<String>(Arrays.asList(lst));
				//System.out.println(flst);
				for(int i = 0; i < flst.size();i++) {
					if(i == 0) {
						finalStr+=flst.get(i) + "\n";
						continue;
					} else if(i == flst.size()-1) {
						String[] lastLine = flst.get(i).split(" ");
						ArrayList<String> l = new ArrayList<String>(Arrays.asList(lastLine));
						if(l.get(l.size()-1).contains(".")) {
							finalStr+="Total distance: " + l.get(l.size()-1) + " pixel units.\n";
						}else {
							finalStr+= "There is no path from " + IDbuildInfo.get(node1).buildName() + " to " + node2 + ".\n";
						}
					}
					else {
						
						String[] lst2 = flst.get(i).split(" to ");
						ArrayList<String> inner = new ArrayList<String>(Arrays.asList(lst2));
						//System.out.println(inner);
						if(bNameInfo.containsKey(inner.get(0))) {
							x1 = bNameInfo.get(inner.get(0)).getCoord1();
							y1 = bNameInfo.get(inner.get(0)).getCoord2();
							//System.out.println("x1:" + x1 + " and " + y1);
						}
						else if(IDbuildInfo.containsKey(inner.get(0))) {
							x1 = IDbuildInfo.get(inner.get(0)).getCoord1();
							y1 = IDbuildInfo.get(inner.get(0)).getCoord2();
							//System.out.println("x1:" + x1 + " and " + y1);
						}
						String[] otherin = inner.get(1).split(" with ");
						ArrayList<String> otherin2 = new ArrayList<String>(Arrays.asList(otherin));
						secDest = otherin2.get(0);
						if(bNameInfo.containsKey(secDest)) {
							secDest = bNameInfo.get(secDest).buildName();
						}else {
							secDest = "Intersection " + secDest;
						}
						if(bNameInfo.containsKey(otherin2.get(0))) {
							x2 = bNameInfo.get(otherin2.get(0)).getCoord1();
							y2 = bNameInfo.get(otherin2.get(0)).getCoord2();
							//System.out.println("x2:" + x2 + " and " + y2;
						}
						else if(IDbuildInfo.containsKey(otherin2.get(0))) {
							x2 = IDbuildInfo.get(otherin2.get(0)).getCoord1();
							y2 = IDbuildInfo.get(otherin2.get(0)).getCoord2();
							//System.out.println("x2:" + x2 + " and " + y2);
						}
					}
					
					double dir = Math.atan2((y2-y1),(x2-x1))* (180/Math.PI);
					dir = dir+90;
					if (dir < 0) {

					    dir = 360 + dir;
					}
					else {
					    dir = dir;
					}
					/*
					 * if((y2-y1) > 0 && (x2-x1) > 0) { dir = 90-dir; } else if((y2-y1) < 0 &&
					 * (x2-x1) < 0) { dir = 270-dir; } else if((y2-y1) > 0 && (x2-x1) < 0){ dir =
					 * 270-dir;
					 * 
					 * } else if((y2-y1) <0 && (x2-x1) > 0) { dir = 90-dir; }
					 */
				
					String direction = "";
					if(dir >=0 && dir < 22.5 || dir > 337.5 ) {
						direction = "North";
					}
					else if(dir >= 22.5 && dir < 67.5) {
						direction = "NorthEast";
					}
					else if(dir >= 67.5 && dir < 112.5) {
						direction = "East";
					}
					else if(dir >= 112.5 && dir < 157.5) {
						direction = "SouthEast";
					}
					else if(dir >= 157.5 && dir < 202.5) {
						direction = "South";
					}
					else if(dir >= 202.5 && dir < 247.5) {
						direction = "SouthWest";
					}
					else if(dir >= 247.5 && dir < 292.5 ) {
						direction = "West";
					}
					else if(dir >= 292.5 && dir < 337.5) {
						direction = "NorthWest";
					}
					if(i < flst.size()-1)
					finalStr+="\tWalk " + direction + " to (" + secDest + ")\n";
					
				}
				return finalStr;
			}
		}
		if(node2.matches("-?(0|[1-9]\\d*)")) {
			if(bNameInfo.get(node1)== null && IDbuildInfo.get(node2)== null) {
				return "Unknown building: [" + node1 + "]\nUnknown building: [" + node2 + "]\n" ;
			}
			else if(IDbuildInfo.get(node2) == null) {
				return "Unknown building: [" + node2 + "]" ;
			}
			else if(IDbuildInfo.get(node2).buildName().isEmpty() && bNameInfo.get(node1) == null ) {
				return "Unknown building: [" + node1 + "]\nUnknown building: [" + node2 + "]\n" ;
			}
			else if(IDbuildInfo.get(node2).buildName().isEmpty()) {
				return "Unknown building: [" + node2 + "]\n";
			}
			else if(bNameInfo.get(node1)== null	) {
				return "Unknown building: [" + node1 +"]\n";
			}
			if(bNameInfo.containsKey(node1)) {
				//System.out.println(G.getGraph().get(""));
				String save = replaceID(mp.findPath(Integer.toString(bNameInfo.get(node1).getID()), node2));
				String finalStr = "";
				String secDest = "";
				String[] lst = save.split("\n");
				
				String str = "";
				ArrayList<String> flst = new ArrayList<String>(Arrays.asList(lst));
				for(int i = 0; i < flst.size();i++) {
					if(i == 0) {
						finalStr+=flst.get(i) + "\n";
						continue;
					} else if(i == flst.size()-1) {
						String[] lastLine = flst.get(i).split(" ");
						ArrayList<String> l = new ArrayList<String>(Arrays.asList(lastLine));
						if(l.get(l.size()-1).contains(".")) {
							finalStr+="Total distance: " + l.get(l.size()-1) + " pixel units.\n";
						}else {
							finalStr+= "There is no path from " + node1 + " to " + IDbuildInfo.get(node2).buildName() + ".\n";
						}
						
						continue;
					}
					else {
						
						String[] lst2 = flst.get(i).split(" to ");
						ArrayList<String> inner = new ArrayList<String>(Arrays.asList(lst2));
						//System.out.println(inner);
						if(bNameInfo.containsKey(inner.get(0))) {
							x1 = bNameInfo.get(inner.get(0)).getCoord1();
							y1 = bNameInfo.get(inner.get(0)).getCoord2();
							//System.out.println("x1:" + x1 + " and " + y1);
						}
						else if(IDbuildInfo.containsKey(inner.get(0))) {
							x1 = IDbuildInfo.get(inner.get(0)).getCoord1();
							y1 = IDbuildInfo.get(inner.get(0)).getCoord2();
							//System.out.println("x1:" + x1 + " and " + y1);
						}
						String[] otherin = inner.get(1).split(" with ");
						ArrayList<String> otherin2 = new ArrayList<String>(Arrays.asList(otherin));
						secDest = otherin2.get(0);
						
						if(bNameInfo.containsKey(secDest)) {
							secDest = bNameInfo.get(secDest).buildName();
						}else {
							secDest = "Intersection " + secDest;
						}
						if(bNameInfo.containsKey(otherin2.get(0))) {
							x2 = bNameInfo.get(otherin2.get(0)).getCoord1();
							y2 = bNameInfo.get(otherin2.get(0)).getCoord2();
							//System.out.println("x2:" + x2 + " and " + y2);
						}
						else if(IDbuildInfo.containsKey(otherin2.get(0))) {
							x2 = IDbuildInfo.get(otherin2.get(0)).getCoord1();
							y2 = IDbuildInfo.get(otherin2.get(0)).getCoord2();
							//System.out.println("x2:" + x2 + " and " + y2);
						}
					}
					
					double dir = Math.atan2((y2-y1),(x2-x1))* (180/Math.PI);
					dir = dir+90;
					if (dir < 0) {

					    dir = 360 + dir;
					}

					else {
					    dir = dir;
					}
					/*
					 * if((y2-y1) > 0 && (x2-x1) > 0) { dir = 90-dir; } else if((y2-y1) < 0 &&
					 * (x2-x1) < 0) { dir = 270-dir; } else if((y2-y1) > 0 && (x2-x1) < 0){ dir =
					 * 270-dir;
					 * 
					 * } else if((y2-y1) <0 && (x2-x1) > 0) { dir = 90-dir; }
					 */
					//System.out.println("my dir " + dir);
					String direction = "";
					if(dir >=0 && dir < 22.5 || dir > 337.5 ) {
						direction = "North";
					}
					else if(dir >= 22.5 && dir < 67.5) {
						direction = "NorthEast";
					}
					else if(dir >= 67.5 && dir < 112.5) {
						direction = "East";
					}
					else if(dir >= 112.5 && dir < 157.5) {
						direction = "SouthEast";
					}
					else if(dir >= 157.5 && dir < 202.5) {
						direction = "South";
					}
					else if(dir >= 202.5 && dir < 247.5) {
						direction = "SouthWest";
					}
					else if(dir >= 247.5 && dir < 292.5 ) {
						direction = "West";
					}
					else if(dir >= 292.5 && dir < 337.5) {
						direction = "NorthWest";
					}
					if(i < flst.size()-1)
					finalStr+="\tWalk " + direction + " to (" + secDest + ")\n";
					//System.out.println("this is a direction " + direction);
				}

				return finalStr;
			}
		}
		if(!(node1.matches("-?(0|[1-9]\\d*)")) && !(node2.matches("-?(0|[1-9]\\d*)"))) {
			if(bNameInfo.get(node2)== null && bNameInfo.get(node1) == null) {
				return "Unknown building: [" + node1 + "]\nUnknown building: [" + node2 + "]\n" ;
			}
			else if(bNameInfo.get(node1) == null) {
				return "Unknown building: [" + node1 +"]\n";
			}
			else if(bNameInfo.get(node2)== null) {
				return "Unknown building: [" + node2 + "]\n";
			}
			
			
			String save = replaceID(mp.findPath(Integer.toString(bNameInfo.get(node1).getID()), Integer.toString(bNameInfo.get(node2).getID())));
			String finalStr = "";

			String secDest = "";
			String[] lst = save.split("\n");
			String str = "";
			ArrayList<String> flst = new ArrayList<String>(Arrays.asList(lst));
			//System.out.println(flst);
			for(int i = 0; i < flst.size();i++) {
				if(i == 0) {
					finalStr+=flst.get(i) + "\n";
					continue;
				} else if(i == flst.size()-1) {
					String[] lastLine = flst.get(i).split(" ");
					ArrayList<String> l = new ArrayList<String>(Arrays.asList(lastLine));
					if(l.get(l.size()-1).contains(".")) {
						finalStr+="Total distance: " + l.get(l.size()-1) + " pixel units.\n";
					}else {
						finalStr = "There is no path from " + node1 + " to " + node2 + ".\n";
					}
					continue;
				}
				else {
					String[] lst2 = flst.get(i).split(" to ");
					ArrayList<String> inner = new ArrayList<String>(Arrays.asList(lst2));
					//System.out.println(inner);
					if(bNameInfo.containsKey(inner.get(0))) {
						x1 = bNameInfo.get(inner.get(0)).getCoord1();
						y1 = bNameInfo.get(inner.get(0)).getCoord2();
						//System.out.println("x1:" + x1 + " and " + y1);
					}
					else if(IDbuildInfo.containsKey(inner.get(0))) {
						x1 = IDbuildInfo.get(inner.get(0)).getCoord1();
						y1 = IDbuildInfo.get(inner.get(0)).getCoord2();
						//System.out.println("x1:" + x1 + " and " + y1);
					}
					String[] otherin = inner.get(1).split(" with ");
					ArrayList<String> otherin2 = new ArrayList<String>(Arrays.asList(otherin));
					secDest = otherin2.get(0);
					if(bNameInfo.containsKey(secDest)) {
						secDest = bNameInfo.get(secDest).buildName();
					}else {
						secDest = "Intersection " + secDest;
					}
					if(bNameInfo.containsKey(otherin2.get(0))) {
						x2 = bNameInfo.get(otherin2.get(0)).getCoord1();
						y2 = bNameInfo.get(otherin2.get(0)).getCoord2();
						//System.out.println("x2:" + x2 + " and " + y2);
					}
					else if(IDbuildInfo.containsKey(otherin2.get(0))) {
						x2 = IDbuildInfo.get(otherin2.get(0)).getCoord1();
						y2 = IDbuildInfo.get(otherin2.get(0)).getCoord2();
						//System.out.println("x2:" + x2 + " and " + y2);
					}
				}
				
				double dir = Math.atan2((y2-y1),(x2-x1))* (180/Math.PI);
				dir = dir+90;
				if (dir < 0) {

				    dir = 360 + dir;
				}

				else {
				    dir = dir;
				}
				/*
				 * if((y2-y1) > 0 && (x2-x1) > 0) { dir = 90-dir; } else if((y2-y1) < 0 &&
				 * (x2-x1) < 0) { dir = 270-dir; } else if((y2-y1) > 0 && (x2-x1) < 0){ dir =
				 * 270-dir;
				 * 
				 * } else if((y2-y1) <0 && (x2-x1) > 0) { dir = 90-dir; }
				 */
				//System.out.println("my dir " + dir);
				String direction = "";
				if(dir >=0 && dir < 22.5 || dir > 337.5 ) {
					direction = "North";
				}
				else if(dir >= 22.5 && dir < 67.5) {
					direction = "NorthEast";
				}
				else if(dir >= 67.5 && dir < 112.5) {
					direction = "East";
				}
				else if(dir >= 112.5 && dir < 157.5) {
					direction = "SouthEast";
				}
				else if(dir >= 157.5 && dir < 202.5) {
					direction = "South";
				}
				else if(dir >= 202.5 && dir < 247.5) {
					direction = "SouthWest";
				}
				else if(dir >= 247.5 && dir < 292.5 ) {
					direction = "West";
				}
				else if(dir >= 292.5 && dir < 337.5) {
					direction = "NorthWest";
				}
				if(i < flst.size()-1)
				finalStr+="\tWalk " + direction + " to (" + secDest + ")\n";
				//System.out.println("this is a direction " + direction);
			}
			
			return finalStr;
		}
		
		return "no path";
		//if there's a building name for an id number, then take the building name
	}
}
