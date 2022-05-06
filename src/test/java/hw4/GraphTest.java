package hw4;

import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.Rule;
import org.junit.rules.Timeout;

public final class GraphTest {
	@Rule public Timeout globalTimeout = Timeout.seconds(10);
	public void Graph1(Graph<String,String> G) {
		//Graph<String, String> G = new Graph<String,String>();
		  G.addNode("b");
		  G.addNode("a");
		  G.addNode("c");
		  G.addNode("d");
		  G.addEdge("a", "b", "1");
		  G.addEdge("b", "a", "2");
		  G.addEdge("b", "a", "1");
		  G.addEdge("a", "a", "1");
		  
		  System.out.println(G.listChildren("b"));
		  System.out.println(G.getListKids());
	  }
	  public void Graph2() {
		  Graph<String, String> G = new Graph<String,String>();
		  G.addNode("a");
		  G.addNode("b");
		  G.addNode("c");
		  G.addEdge("a","b","1");
		  G.addEdge("a","c","1");
		  //test duplicate
		  G.addEdge("a","c","1");
		  G.addEdge("b","c","1");
		  G.addEdge("c","b","1");
	  }
	  public void Graph3() {
		  Graph<String, String> G = new Graph<String,String>();

		  G.addNode("a");
		  G.addNode("b");
		  G.addNode("c");
		  G.addEdge("a","b","4");
		  G.addEdge("c","b","2");
		  G.addEdge("b","c","5");
		  G.addEdge("a","c","7");
		  G.addEdge("a","c","4");
		  G.listChildren("a");
		  System.out.println(G.getListKids());
	  }
	  @Test
	  public void addNodesinGraph() {
		  Graph<String, String> G = new Graph<String,String>();
		  G.addNode("A");
		  G.listNodes();
		  assertEquals("A\n",G.getListNodes());
	  }
	  @Test
	  public void addEdgesinGraph() {
		  Graph<String, String> G = new Graph<String,String>();
		  G.addNode("A");
		  G.addNode("B");
		  G.addEdge("A", "B", "1");
		  G.listChildren("A");
		  assertEquals("B(1)\n", G.getListKids());
	  }
	  @Test
	  public void possiblePaths() {
		  Graph<String, String> G = new Graph<String,String>();
		  Graph1(G);		
		  assertEquals(true, G.possiblePath("a", "b"));		  
	  }
	  @Test
	  public void possiblePathfromChildtoParent() {
		  Graph<String, String> G = new Graph<String,String>();
		  Graph1(G);
		  //go from child to parent node
		  assertEquals(false, G.possiblePath("c", "a"));
	  }
	  @Test
	  public void possiblePathtoItself() {
		  Graph<String, String> G = new Graph<String,String>();

		  Graph1(G);
		  assertEquals(true, G.possiblePath("a", "a"));
	  }
	  @Test 
	  public void UndirectedGraph() {
		  Graph r = new Graph();
		  //Graph1(r);
		  //assertEquals(true, r.isGraphUndirected());
	  }
	  @Test 
	  public void clearGraph() {
		  Graph<String, String> G = new Graph<String,String>();
		  Graph1(G);
		  G.clear();
		  G.listNodes();
		  assertTrue(G.getListNodes().isEmpty());
	  }
	  @Test
	  public void getGraph() {
		  Graph e = new Graph();
		  //Graph3(e);
		  e.listNodes();
		  e.getGraph();
		  //assertFalse(e.getListNodes().isEmpty());
	  }
	
}
