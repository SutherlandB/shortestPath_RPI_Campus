package hw7;
import java.util.*;
import java.io.*;
import hw7.Building;

public class MapParser {

		/**
		 * @param: filename     The path to a "CSV" file that contains the
		 *                      "character","book" pairs
		 * @param: charsInBooks The Map that stores parsed <book,
		 *                      Set-of-characters-in-book> pairs; usually an empty Map.
		 * @param: chars        The Set that stores parsed characters; usually an empty
		 *                      Set.
		 * @requires: filename != null && charsInBooks != null && chars != null
		 * @modifies: charsInBooks, chars
		 * @effects: adds parsed <book, Set-of-characters-in-book> pairs to Map
		 *           charsInBooks; adds parsed characters to Set chars.
		 * @throws: IOException if file cannot be read of file not a CSV file following
		 *                      the proper format.
		 * @returns: None
		 */
		public static <T,V> void readData(String filename, Map<String, Building> IDbuildInfo, Map<String, Building> bNameInfo, Map<String, List<String>> connections)
				throws IOException {

			try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
				reader.mark(1);
				String line = null;
				String checkFile = reader.readLine();
				String[] check = checkFile.split(",");
				ArrayList<String> ls = new ArrayList<String>(Arrays.asList(check));
				reader.reset();
				if(ls.size() > 4) {
						throw new IOException("File " + filename + " not a proper CSV file.");
				}
				if(ls.size() > 2) {
					while ((line = reader.readLine()) != null) {
						String[] values = line.split(",");
						ArrayList<String> list = new ArrayList<String>(Arrays.asList(values));
						String building = list.get(0);
						int id = -1;
						id = Integer.valueOf(list.get(1));
						int coord1 = Integer.valueOf(list.get(2));
						int coord2 = Integer.valueOf(list.get(3));
						//make building object and put it into two maps: one with keys of building names and the other is keys of 
						//corresponding id numbers
						Building b = new Building(building, id, coord1, coord2);
						if(building.length() != 0) {
							bNameInfo.put(building, b);
						}
						if(id != -1) {
							IDbuildInfo.put(Integer.toString(id), b);
						}
						
						
					}
				}
				else {
					while ((line = reader.readLine()) != null) {
						String[] values = line.split(",");
						ArrayList<String> list = new ArrayList<String>(Arrays.asList(values));
						String id1 = list.get(0);
						String id2 = list.get(1);
						//make building object and put it into two maps: one with keys of building names and the other is keys of 
						//corresponding id numbers
						
						if(connections.containsKey(id1)) {
							connections.get(id1).add(id2);
						}
						else {
							List<String> n = new ArrayList<String>();
							n.add(id2);
							connections.put(id1, n);
						}
						if(connections.containsKey(id2)) {
							connections.get(id2).add(id1);
						}
						else {
							List<String> n = new ArrayList<String>();
							n.add(id1);
							connections.put(id2, n);
						}
					}
					//System.out.println(connections);
				}
				
				
			}
		}
	}
