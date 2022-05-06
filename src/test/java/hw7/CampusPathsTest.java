package hw7;
import hw4.*;
import hw7.MapParser;
import java.io.*;
import java.util.*;

import static org.junit.Assert.*;
import org.junit.Test;

public class CampusPathsTest { // Rename to the name of your "main" class

	/**
	 * @param file1 
	 * @param file2
	 * @return true if file1 and file2 have the same content, false otherwise
	 * @throws IOException
	 */	
	/* compares two text files, line by line */
	private static boolean compare(String file1, String file2) throws IOException {
		BufferedReader is1 = new BufferedReader(new FileReader(file1)); // Decorator design pattern!
		BufferedReader is2 = new BufferedReader(new FileReader(file2));
		String line1, line2;
		boolean result = true;
		while ((line1=is1.readLine()) != null) {
			line2 = is2.readLine();
			if (line2 == null) {
				System.out.println(file1+" longer than "+file2);
				result = false;
				break;
			}
			if (!line1.equals(line2)) {
				System.out.println("Lines: "+line1+" and "+line2+" differ.");
				result = false;
				break;
			}
		}
		if (result && is2.readLine() != null) {
			System.out.println(file1+" shorter than "+file2);
			result = false;
		}
		is1.close();
		is2.close();
		return result;
	}
	
	private void runTest(String filename) throws IOException {
		InputStream in = System.in;
		PrintStream out = System.out;
		String inFilename = "data/"+filename+".test"; // Input filename: [filename].test here  
		String expectedFilename = "data/"+filename+".expected"; // Expected result filename: [filename].expected
		String outFilename = "data/"+filename+".out"; // Output filename: [filename].out
		BufferedInputStream is = new BufferedInputStream(new FileInputStream(inFilename));
		System.setIn(is); // redirects standard input to a file, [filename].test 
		PrintStream os = new PrintStream(new FileOutputStream(outFilename));
		System.setOut(os); // redirects standard output to a file, [filename].out 
		CampusPaths.main(null); // Call to YOUR main. May have to rename.
		System.setIn(in); // restores standard input
		System.setOut(out); // restores standard output
		assertTrue(compare(expectedFilename, outFilename)); 
		// TODO: You can implement more informative file comparison, if you would like.
		
	}
	
	@Test
	public void testListBuildings() throws IOException {
		runTest("test1");
	}
	@Test 
	public void test2() throws IOException {
		Map<String, Building> IDbuildInfo = new HashMap<String, Building>(); 
		Map<String, Building> bNameInfo = new HashMap<String, Building>();
		Map<String, List<String>> connections = new HashMap<String, List<String>> ();
		String file2 = "data/RPI_map_data_Nodes.csv";
		String file = "data/RPI_map_data_Edges.csv";
		MapParser.readData(file, IDbuildInfo, bNameInfo, connections);
		MapParser.readData(file2, IDbuildInfo, bNameInfo, connections);

	}
	@Test 
	public void test3() throws IOException{
		Model c = new Model();
		c.createNewGraph("data/RPI_map_data_Nodes.csv", "data/RPI_map_data_Edges.csv");
		System.out.print(c.shortestPath("Blitman Residence Commons", "Rensselaer Union"));
	}
	@Test 
	public void test4() throws IOException{
		Model c = new Model();
		c.createNewGraph("data/RPI_map_data_Nodes.csv", "data/RPI_map_data_Edges.csv");
		System.out.print(c.shortestPath("Radio Club", "LINAC Facility"));
	}
	@Test 
	public void test5() throws IOException{
		Model c = new Model();
		c.createNewGraph("data/RPI_map_data_Nodes.csv", "data/RPI_map_data_Edges.csv");
		System.out.print(c.shortestPath("60", "cbis"));
	}
	@Test 
	public void test6() throws IOException{
		Model m = new Model();
		View v = new View();
		Controller co = new Controller(m,v);
		
		co.setModel(m);
	}	
	@Test
	public void testOtherCommands() throws IOException {
		runTest("test2");
	}
	
}
