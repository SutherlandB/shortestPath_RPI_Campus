package hw6;
import hw4.*;
import hw5.MarvelPaths;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;
import java.util.*;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;


public final class MarvelPaths2Test {
	@Test
	public void Test1() {
		Graph<String, Integer> g = new Graph<String, Integer>();
		g.addNode("a");
		g.addNode("b");
		g.addNode("c");
		g.addEdge("a", "b", 2);
		g.addEdge("a", "b", 1);
		
		
	}
	@Test 
	public void Test2() {
		String file = "data/marvel.csv";
		MarvelPaths2 mp = new MarvelPaths2(); 
		mp.createNewGraph(file);
		mp.getGraph().listChildren("FROST, CARMILLA");
		mp.getGraph().listNodes();
		/*
		System.out.println(mp.getGraph().getListNodes());
		System.out.println(mp.getGraph().getListKids());
		*/
	}
	@Test
	public void Test3() {
		String file = "data/marvel.csv";
		MarvelPaths2 mp = new MarvelPaths2(); 
		mp.createNewGraph(file);
		//mp.getGraph().listChildren("FROST, CARMILLA");
		//System.out.println(mp.getGraph().getListKids());
		//List<Edge<String, Double>> eq = mp.findPath("FROST, CARMILLA", "M'Shulla").getEdges();
		//List<Edge<String, Double>> eq = mp.findPath("PETERS, SHANA TOC", "SEERESS").getEdges();
		System.out.println(mp.findPath("PETERS, SHANA TOC", "SEERESS"));
		/*
		eq.remove(0);
		Iterator<Edge<String, Double>> y = eq.iterator();
		
		while(y.hasNext()) {
			Edge<String, Double> l = y.next();
			System.out.println(l.getSource());
			System.out.println(l.getDestination());
		}*/
		
	}
	@Test
	public void Test4() {
		String file = "data/marvel.csv";
		MarvelPaths2 mp = new MarvelPaths2(); 
		mp.createNewGraph(file);
		System.out.println(mp.findPath("GOOM", "HOFFMAN"));
		
	}
	@Test
	public void Test5() {
		String file = "data/marvel.csv";
		MarvelPaths2 mp = new MarvelPaths2(); 
		mp.createNewGraph(file);
		System.out.println(mp.findPath("BATMAN", "CAPTAIN AMERICA"));
	}
	@Test
	public void Test6() {
		String file = "data/marvel.csv";
		MarvelPaths2 mp = new MarvelPaths2(); 
		mp.createNewGraph(file);
		System.out.println(mp.findPath("BATMAN", "GREEN LANTERN"));
	}
	@Test
	public void Test7() {
		String file = "data/marvel.csv";
		MarvelPaths2 mp = new MarvelPaths2(); 
		mp.createNewGraph(file);
		System.out.println(mp.findPath("SEERESS", "SEERESS"));
	}
	@Test
	public void Test8() {
		String file = "data/marvel.csv";
		MarvelPaths2 mp = new MarvelPaths2();
		mp.createNewGraph(file);
		System.out.println(mp.findPath("BATMAN", "BATMAN"));
	}
	@Test 
	public void Test9() {
		String file = "data/marvel.csv";
		MarvelPaths2 mp = new MarvelPaths2();
		mp.createNewGraph(file);
		System.out.println(mp.findPath("SEERESS", "BATMAN"));
	}
	/*
	 * @Test public void Test10() { String file = "data/bruh.csv"; MarvelPaths2 mp =
	 * new MarvelPaths2(); mp.createNewGraph(file);
	 * System.out.println(mp.findPath("SUPERHERO-A", "SUPERHERO-D")); }
	 */
}