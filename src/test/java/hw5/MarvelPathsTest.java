package hw5;
import hw4.*;
import hw5.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;
import java.util.*;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;

public final class MarvelPathsTest {
	@Test
	public void TestMoney(){
		assertEquals("money", "money");
	}
	@Test
	public void TestRead() {
		String file = "data/marvel.csv";
		MarvelPaths mp = new MarvelPaths(); 
		mp.createNewGraph(file);
		mp.getGraph().listNodes();
		//System.out.println(mp.getGraph().getListNodes());
		//mp.getGraph().listChildren("FROST, CARMILLA");
		//System.out.println(mp.getGraph().getListKids());
		
		
	}
	@Test
	public void TestPath1() {
		String file = "data/marvel.csv";
		MarvelPaths mp = new MarvelPaths(); 
		mp.createNewGraph(file);
		System.out.println(mp.findPath("BATMAN", "BATMAN"));
	}
	@Test 
	public void TestPath2() {
		String file = "data/marvel.csv";
		MarvelPaths mp = new MarvelPaths(); 
		mp.createNewGraph(file);
		System.out.println(mp.findPath("FROST, CARMILLA", "G"));
	}
	@Test
	public void TestPath3() {
		String file = "data/marvel.csv";
		MarvelPaths mp = new MarvelPaths(); 
		mp.createNewGraph(file);
		System.out.println(mp.findPath("BATMAN", "Goofman"));
	}
	@Test
	public void TestPath4() {
		String file = "data/marvel.csv";
		MarvelPaths mp = new MarvelPaths(); 
		mp.createNewGraph(file);
		System.out.println(mp.findPath("", ""));
	}
	@Test
	public void TestPath5() {
		String file = "data/marvel.csv";
		MarvelPaths mp = new MarvelPaths(); 
		mp.createNewGraph(file);
		System.out.println(mp.findPath("PETERS, SHANA TOC", "SEERESS"));
	}
}