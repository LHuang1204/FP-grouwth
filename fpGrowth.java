package fp_growth;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Arrays;

public class fpGrowth {
	
	public static void main(String[] args) {
		double minsup = 0.4;
		double minconf = 0.6;
		String path = "";  
		int countLine = 0;
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		BufferedReader reader;
		
		// read file and save it to array list by line
		try {
			reader = new BufferedReader(new FileReader(path));
			String line = reader.readLine();
			while (line != null) {
				ArrayList<String> item = new ArrayList<String>(Arrays.asList(line.split(",")));
				list.add(item);
				System.out.println(item);
				line = reader.readLine();
				countLine++;
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("File not found");
		}
		
		FPTree tree = new FPTree(minsup, minconf, countLine);
		
		// print frequent pattern
		for(String pattern: tree.growth(list, null))
			System.out.println(pattern);
	}
}
