package hw4;
import java.util.*;
import hw4.Edge;
//import hw4.Graph.Edge;


class GraphWrapper<T,V>{
	//adjacency list representation
	private TreeMap<T, List<Edge>> adjList;
	private List<Edge<T,V>> edges;
	private String listNode;
	private String listKids;
	private List<String> lmao;
	public TreeMap<T, List<Edge>> getGraph(){
		return adjList;
	}
	public String getListNodes(){
		return listNode;
	}
	public String getListKids() {
		return listKids;
	}
	
	public GraphWrapper(){
		adjList = new TreeMap<T, List<Edge>>();
		edges = new ArrayList<>();
	}

	public void addNode(T nodeData) {
		if (adjList.containsKey(nodeData)) {
			return;
		}
		else{
			List<Edge> o = new ArrayList<>();
			adjList.put(nodeData, o);
		}
		//what happens with duplicate nodes? dont add them
	
	}
	
	public void addEdge(T parentNode, T childNode, V edgeLabel) {
		//Graph p = new Graph();
		/*
		Edge<T,V> e = new Edge<T,V>(parentNode, childNode,edgeLabel);
		for(int i = 0; i < edges.size(); i++) {
			if(e.equals(edges.get(i))){
				return;
			}
		}
		edges.add(e);
		adjList.get(parentNode).add(e);
		//what happens with duplicate edges? dont add them
		 */
		Edge<T,V> e = new Edge<T,V>(parentNode, childNode,edgeLabel);
		edges.add(e);
		adjList.get(parentNode).add(e);
		
	}
	/*
	public Iterator<String> listNodes(){
		Iterator<String> i = adjList.keySet().iterator();
		listNode = "";
		while(i.hasNext()) {
			listNode+=(i.next() + "\n");
		}
		return i;
	}
	*/
	public Iterator<T> listNodes(){
		Iterator<T> i =adjList.keySet().iterator();
		return i;
	}
	
	
	public Iterator<String> listChildren(String parentNode){
		listKids = "";
		lmao = new ArrayList<>();
		List<Edge<T,V>> children = new ArrayList<>();
		TreeMap<T, List<V>> e = new TreeMap<T, List<V>>();
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
				List<V> f = new ArrayList<V>();
				f.add( children.get(i).getWeight());
				e.put(children.get(i).getDestination(), f);
			}
		}
		Set<T> kSet = e.keySet();
		
		//sort the weights for each child node 
		for(T key1:kSet) {
			//sort again i guess
			//Collections.sort(e.get(key1));
			for(int k = 0; k < e.get(key1).size();k++) {
				String add = "";
				listKids+=key1 + "(" + e.get(key1).get(k) + ")\n";
				add+= key1 + "(" + e.get(key1).get(k) + ")"; 
				lmao.add(add);
			}
		}
		
		Iterator<String> r = lmao.iterator();
		
		return r;
	}
	
}






