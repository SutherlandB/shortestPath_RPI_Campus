package hw4;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;




public final class GraphWrapperTest {
  @Rule public Timeout globalTimeout = Timeout.seconds(10);
  public void Graph1(GraphWrapper<String, String> G) {
	  //G = new GraphWrapper<String,String>();
	  G.addNode("b");
	  G.addNode("a");
	  G.addNode("c");
	  G.addNode("d");
	  G.addEdge("a", "b", "1");
	  G.addEdge("b", "a", "1");
	  G.addEdge("a", "a", "1");
	  G.addEdge("b", "a", "4");
	  System.out.println(G.listChildren("b"));
	  System.out.println(G.getListKids());
	  
  }/*
  public void Graph2(GraphWrapper G) {
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
  public void Graph3(GraphWrapper G) {
	  G.addNode("a");
	  G.addNode("b");
	  G.addNode("c");
	  G.addEdge("a","b","4");
	  G.addEdge("c","b","2");
	  G.addEdge("b","c","5");
	  G.addEdge("a","c","7");
	  G.addEdge("a","c","4");
  }
  public void Graph4(GraphWrapper G) {
	  G.addNode("a");
	  G.addNode("b");
	  G.addNode("c");
	  G.addEdge("a","a","label1");
	  G.addEdge("a","a","3");
	  G.addEdge("a","a","2");
	  G.addEdge("b","b","label3");
	  G.addEdge("c","c","1");
	  G.addEdge("b","b","12");
	  G.addEdge("c", "c", "label2");
  }
  public void Graph5(GraphWrapper G) {
	  G.addNode("A");
	  G.addNode("B");
	  G.addNode("C");
	  G.addEdge("A", "C", "label1");
	  G.addEdge("A", "B", "label1");
	  G.addEdge("A", "B", "label2");
	  G.addEdge("A", "B", "label3");
	  G.addEdge("B", "C", "label2");
	  G.addEdge("B", "B", "label3");
	  G.addEdge("C", "A", "label1");
	  G.addEdge("C", "C", "label2");
	  G.addEdge("C", "B", "label3");
	  G.addEdge("A", "A", "label1");
	  
  }*/
  @Test
  public void ListNodeTest() {
	  GraphWrapper<String, String> G = new GraphWrapper<String, String>();
	  Graph1(G);
	  List<String> str1 = Arrays.asList("a", "b", "c", "d");
	  Iterator<String> gra = G.listNodes();
	  Iterator<String> strI = str1.iterator();
	  
	  while(gra.hasNext())
	  {
		  assertEquals(strI.next(), gra.next());
	  }
	  
  }
  @Test
  public void ListChildrenTest() {
	  GraphWrapper<String,String> G = new GraphWrapper<String, String>();
	  Graph1(G);
	  G.listChildren("a");
	  assertEquals("a(1)\nb(1)\n",  G.getListKids());
	  //GraphWrapper H = new GraphWrapper();
	  /*Graph2(H);
	  H.listChildren("b");
	  assertEquals("c(1)\n", H.getListKids());
	  H.listChildren("a");
	  assertEquals("b(1)\nc(1)\n", H.getListKids());
	  GraphWrapper I = new GraphWrapper();
	  Graph3(I);
	  I.listChildren("a");
	  assertEquals("b(4)\nc(4)\nc(7)\n", I.getListKids());*/
	}
//  @Test
//  public void ListChildrenTestTwo() {
//	  GraphWrapper D = new GraphWrapper();
//	  Graph4(D);
//	  List<String> str1 = Arrays.asList("a(2)", "a(3)");
//	  Iterator<String> strItr = str1.iterator();
//	  Iterator<String> problem = D.listChildren("c");
//	  while(strItr.hasNext()) {
//		  assertEquals(strItr.next(), problem.next());
//	  }
//  }
  @Test
  public void reflexiveEdges() {
	  GraphWrapper<String, String> r = new GraphWrapper<String, String>();
	  Graph1(r);
	  List<String> str1 = Arrays.asList("a(1)", "a(4)");
	  Iterator<String> l = r.listChildren("b");
	  Iterator<String> strI = str1.iterator();
	  while(strI.hasNext())
	  {
		  assertEquals(strI.next(), l.next());
	  }
  }
  



}
