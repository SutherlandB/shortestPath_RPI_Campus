package hw4;
import java.util.*;
//import hw4.Edge;

/*
 * Overview: 
 * Graph ADT utilizes edge class and TreeMaps to represent a full graph. This graph ADT uses adjacency lists to show 
 * neighboring nodes.
 */

/*
 class Edge{
	/*
	 * abstraction function:
	 *                      Edge e represents the source and destination of an edge with weight label.
	 * representation invariant: 
	 * 						edge source and destination have to be in the adjacency list.
	 
	private String weight;
	private String source;
	private String destination;
	/**
	 *	@param: source, destination, edgeLabel to be added to edge list
	 *	@effects: creates an edge object with the respective member variables.
	 *  
	 
	Edge(String src, String dest, String wei){
		this.weight = wei;
		this.source = src;
		this.destination = dest;
	}
	/*
	 * @returns: source member variable
	 
	public String getSource() {
		return source;
	}
	/*
	 * @returns: weight member variable
	 
	public String getWeight() {
		return weight;
	}
	/*
	 * @returns: destination member variable
	 
	public String getDestination() {
		return destination;
	}
	/*
	 * @returns: true if two edges are equal
	 
	@Override 
	public boolean equals(Object o) {
		
		Edge e = (Edge) o;
		if(e.getSource() == this.getSource() && e.getDestination() == this.getDestination() && e.getWeight() == this.getWeight()) {
			return true;
		}
		return false;
	}
}*/



public class Graph<T extends Comparable <? super T>,V extends Comparable <? super V>>{
	//adjacency list representation
	/*
	 * Specification field:
	 * Nodes represented adjList TreeMap
	 * Edges are represented as a List<Edge> 
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
		
		private TreeMap<T, List<Edge<T,V>>> adjList;
		private List<Edge<T,V>> edges;
		private String listNode;
		private String listKids;
		private TreeMap<T, List<V>> e;

		private Map<Edge<T,V>, Integer> godMap;
		

		public static final boolean debug = true;
		/*
		 * @returns: adjList treeMap representation of nodes
		 */
		public TreeMap<T, List<Edge<T,V>>> getGraph(){
			return adjList;
		}
		/*
		 * @returns: get list of nodes
		 */
		public String getListNodes(){
			return listNode;
		}
		/*
		 * @returns: get list of children of parent node
		 */
		public String getListKids() {
			return listKids;
		}
		


		public List<Edge<T,V>> getEdges() {
			// TODO Auto-generated method stub
			return edges;
		}
		
		public Map<T, List<V>> mapEdges() {
			// TODO Auto-generated method stub
			return e;
		}
		/*
		 * @effects: create new instance of graph with empty node and edges list
		 */
		
		public Graph(){
			adjList = new TreeMap<T, List<Edge<T,V>>>();
			edges = new ArrayList<>();
			godMap = new HashMap<Edge<T,V>, Integer> ();
		}
		/**
		 *	@param: nodeData string to be added to adjList
		 *	@modifies: this graph
		 *	@effects: adds to adjList
		 *	
		 */
		public void addNode(T nodeData) {
			if (adjList.containsKey(nodeData)) {
				return;
			}
			else{
				List<Edge<T,V>> o = new ArrayList<>();
				adjList.put(nodeData, o);
				//godMap.put(nodeData, o);
			}
			//what happens with duplicate nodes? dont add them
			//checkRep();
		}
		/**
		 *	@param: parentNode, childNode, edgeLabel to be added to edge list
		 *	@modifies: connection of nodes
		 *	@effects: adds the new edge to the edge list
		 *	@throws: RuntimeException from checkRep if 
		 *
		 */
		public Edge<T,V> addEdge(T parentNode, T childNode, V edgeLabel) {
			Edge<T,V> e = new Edge<T,V>(parentNode, childNode,edgeLabel);
			
//			for(int i = 0; i < edges.size(); i++) {
//				if(e.equals(edges.get(i))){
			/*
			 * boolean found = false; for(Edge<T, V> e1:adjList.get(parentNode)) {
			 * if(e1.getDestination().equals(e.getDestination())) { found = true;
			 * e1.addDupe(); return e1; } } if(!found) { adjList.get(parentNode).add(e); }
			 */
				/*
				for(int i = 0; i < adjList.get(e.getSource()).size(); i++) {
					if(e.equals(adjList.get(e.getSource()).get(i))) {
						adjList.get(parentNode).get(i).addDupe();
						
						return adjList.get(e.getSource()).get(i);
					}
				}
			}
			else {
				//List<Edge<T,V>> empt = new ArrayList<>();
				//empt.add(e);
				//godMap.get(e.getSource()).add(e);
				
			}
//			for(int i = 0; i < edges.size(); i++) {
//				if(e.equals(edges.get(i))){					
//					return;
//				}
//			}*/
			edges.add(e);
			adjList.get(parentNode).add(e);
			return e;
			
			//Collections.sort(adjList.get(parentNode),(e1,e2)->e1.getDestination().equals(e2.getDestination()) ? e1.getWeight().compareTo(e2.getWeight()): e1.getDestination().compareTo(e2.getDestination()));
			//checkRep();
			//what happens with duplicate edges? dont add them
		}
		
		/**
		 *	@param: parentNode, childNode, edgeLabel to be added to edge list
		 *	@modifies: connection of nodes
		 *	@effects: adds the new edge to the edge list
		 *	@throws: RuntimeException from checkRep if 
		 *
		 */
		public Edge<T,V> addEdge2(T parentNode, T childNode, V edgeLabel) {
			Edge<T,V> e = new Edge<T,V>(parentNode, childNode,edgeLabel);
			
//			for(int i = 0; i < edges.size(); i++) {
//				if(e.equals(edges.get(i))){
			boolean found = false;
			for(Edge<T, V> e1:adjList.get(parentNode)) {
				if(e1.getDestination().equals(e.getDestination())) {
					found = true;
					e1.addDupe();
					return e1;
				}
			}
			if(!found) {
				adjList.get(parentNode).add(e);
			}
				/*
				for(int i = 0; i < adjList.get(e.getSource()).size(); i++) {
					if(e.equals(adjList.get(e.getSource()).get(i))) {
						adjList.get(parentNode).get(i).addDupe();
						
						return adjList.get(e.getSource()).get(i);
					}
				}
			}
			else {
				//List<Edge<T,V>> empt = new ArrayList<>();
				//empt.add(e);
				//godMap.get(e.getSource()).add(e);
				
			}
//			for(int i = 0; i < edges.size(); i++) {
//				if(e.equals(edges.get(i))){					
//					return;
//				}
//			}*/
			edges.add(e);
			
			return e;
			
			//Collections.sort(adjList.get(parentNode),(e1,e2)->e1.getDestination().equals(e2.getDestination()) ? e1.getWeight().compareTo(e2.getWeight()): e1.getDestination().compareTo(e2.getDestination()));
			//checkRep();
			//what happens with duplicate edges? dont add them
		}
		/*
		 * @returns: Iterator<String> of end of keys in adjList
		 */
		public Iterator<T> listNodes(){
			Iterator<T> i = adjList.keySet().iterator();
			listNode = "";
			while(i.hasNext()) {
				listNode+=(i.next() + "\n");
			}
			return i;
		}
		
		/*
		 * @returns: Iterator<String> of children of a certain node
		 */
		public Iterator<T> listChildren(T parentNode){
			//sort before calling the method for generic
			Collections.sort(adjList.get(parentNode),(e1,e2)->e1.getDestination().equals(e2.getDestination()) ? e1.getWeight().compareTo(e2.getWeight()): e1.getDestination().compareTo(e2.getDestination()));
			listKids = "";
			List<Edge<T,V>> children = new ArrayList<>();
			e = new TreeMap<T, List<V>>();
			//loop through edge set and match the edge child nodes with parentNodes to make child set
			
			//loop through edge set and match the edge source nodes with parentNodes to make child set
			for(int i = 0; i < edges.size(); i++) {
				if(edges.get(i).getSource().equals(parentNode)) {
					children.add(edges.get(i));
				}
			} 
			//populate the TreeMap with edges
			for(int i = 0; i < children.size(); i++) {
				if(e.containsKey(children.get(i).getDestination())) {
					e.get(children.get(i).getDestination()).add(children.get(i).getWeight());
					Collections.sort(e.get(children.get(i).getDestination()));
					
				} 
				else {
					List<V> f = new ArrayList<V>();
					f.add(children.get(i).getWeight());
					e.put(children.get(i).getDestination(), f);
				}
			}
			Set<T> kSet = e.keySet();
			
			Iterator<T> r = kSet.iterator();
			//sort the weights for each child node 
			for(T key1:kSet) {
				//sort again i guess
				Collections.sort(e.get(key1));
				for(int k = 0; k < e.get(key1).size();k++) {
					listKids+=key1 + "(" + e.get(key1).get(k).toString() + ")\n";
				}
			}
			return r;
		}
		public void makeE(T parentNode){
			//sort before calling the method for generic
			//Collections.sort(adjList.get(parentNode),(e1,e2)->e1.getDestination().equals(e2.getDestination()) ? e1.getWeight().compareTo(e2.getWeight()): e1.getDestination().compareTo(e2.getDestination()));
			listKids = "";
			List<Edge<T,V>> children = new ArrayList<>();
			e = new TreeMap<T, List<V>>();
			//loop through edge set and match the edge child nodes with parentNodes to make child set
			
			//loop through edge set and match the edge source nodes with parentNodes to make child set
			for(int i = 0; i < edges.size(); i++) {
				
				if(edges.get(i).getSource().equals(parentNode)) {
					children.add(edges.get(i));
				}
			}
			//populate the TreeMap with edges
			for(int i = 0; i < children.size(); i++) {
				if(e.containsKey(children.get(i).getDestination())) {
					e.get(children.get(i).getDestination()).add(children.get(i).getWeight());
					//Collections.sort(e.get(children.get(i).getDestination()));
				} 
				else {
					List<V> f = new ArrayList<>();
					f.add(children.get(i).getWeight());
					e.put(children.get(i).getDestination(), f);
				}
			}
		}
		public Iterator<T> listChildren2(T parentNode){
			Set<T> kSet = e.keySet();
			
			Iterator<T> r = kSet.iterator();
			//sort the weights for each child node 
			for(T key1:kSet) {
				//sort again i guess
				//Collections.sort(e.get(key1));
				for(int k = 0; k < e.get(key1).size();k++) {
					listKids+=key1 + "(" + e.get(key1).get(k).toString() + ")\n";
				}
			}
			return r;
		}
		/*
		 * @returns: whether or not a source can reach the desired destination.
		 */
		public boolean possiblePath(T src, T dest){
			
			List<T> lst = new ArrayList<>(); //queued nodes
			//visited array for BFS traversal
			Map<T, Integer> visited = new HashMap<T, Integer>();
			Iterator<T> lmao = adjList.keySet().iterator();
			while(lmao.hasNext()) {
				visited.put(lmao.next(), 0);
			}
			visited.put(src, 0);
			lst.add(src);
			while(lst.size() != 0) {
				src = lst.remove(0);
				T next; 
				Iterator<Edge<T,V>> scan = adjList.get(src).iterator();
				while(scan.hasNext()) {
					next = scan.next().getDestination();
					
					if(next.equals(dest)) {
						return true;
					}
					else {
						if(visited.get(next)==0){
							visited.put(next,1);
							lst.add(next);
						}
					}
				}
			}
			return false;
		}
		
		/*
		 * @returns: whether or not a graph is undirected.
		 */
		public boolean isGraphUndirected() {
			 if(adjList.size() > 0) {
				 Iterator<T> i = adjList.keySet().iterator();
				 T save = i.next();
				 for(int x = 0; x < adjList.get(save).size(); x++) {
					 for(int y = 0; y < adjList.get(adjList.get(save).get(x)).size(); y++) {
						 if(adjList.get(adjList.get(save).get(x)).contains(save)) {
							 return true;
						 }
					 }
				 }
			 }
			 return false;
		}
		/**
		 *	@modifies: the graph
		 *	@effects: clears edge and adjacency lists
		 */
		public void clear() {
			adjList.clear();
			edges.clear();
			//checkRep();
		}
		
		/*
		 * @throws: RuntimeException for violating RI 
		 */
		private void checkRep() throws RuntimeException {
	        if(debug) {
	        	if(adjList == null) {
	        		throw new RuntimeException("adjList is null");
	        	}
	        	else if (edges == null) {
	        		throw new  RuntimeException("Edges is null");
	        	}
	        }
	        	for (Edge i: edges) {
	    			if (!(adjList.keySet().contains(i.getSource()))) {
	    				throw new RuntimeException("source not found");
	    			}
	    			if (!(adjList.keySet().contains(i.getDestination()))) {
	    				throw new RuntimeException("destination not found");
	    			}
	        }
	    }
		/*
		public class Edge {
			/*
			 * abstraction function:
			 *                      Edge e represents the source and destination of an edge with weight label.
			 * representation invariant: 
			 * 						edge source and destination have to be in the adjacency list.
			 
			private String weight;
			private String source;
			private String destination;
			/**
			 *	@param: source, destination, edgeLabel to be added to edge list
			 *	@effects: creates an edge object with the respective member variables.
			 *  
			 
			Edge(String src, String dest, String wei){
				this.weight = wei;
				this.source = src;
				this.destination = dest;
			}
			/*
			 * @returns: source member variable
			 
			public String getSource() {
				return source;
			}
			/*
			 * @returns: weight member variable
			 
			public String getWeight() {
				return weight;
			}
			/*
			 * @returns: destination member variable
			 
			public String getDestination() {
				return destination;
			}
			/*
			 * @returns: true if two edges are equal
			 
			@Override 
			public boolean equals(Object o) {
				
				Edge e = (Edge) o;
				if(e.getSource().equals( this.getSource()) && e.getDestination().equals( this.getDestination()) && e.getWeight().equals(this.getWeight())) {
					return true;
				}
				return false;
			}
		}*/
		
		public Class<? extends Object> whatType(T o) {
			return o.getClass();
		}
		
}
