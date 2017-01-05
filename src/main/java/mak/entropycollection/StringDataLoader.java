package mak.entropycollection;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StringDataLoader {

	public static String[] attributes;

	public static String[][] readcsvfile(String csvFile) {

		String[][] matrix = null;
		BufferedReader br = null;
		String line = "";

		try {
			br = new BufferedReader(new FileReader(csvFile));
			int cols = 0;
			int rows = 0;
			List<String[]> list = new ArrayList<String[]>();
			
			while ((line = br.readLine()) != null) {
				String[] doc = line.split(",");
				list.add(doc);
				cols = doc.length;
			}
			attributes = list.get(0);
			list.remove(0);
			matrix = new String[list.size()][cols];
			for(String[] doc : list ){
				for(int col = 0; col < doc.length; col++){
					matrix[rows][col] = doc[col].trim();
				}
			rows++;	
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return matrix;
	  }
	
	public static List<String[]> transpose(String[][] arr){
		List<String[]> res = new ArrayList<String[]>();
		int C = arr[0].length;
		int R = arr.length;
		for (int c = 0 ; c != C ; c++) {
			String[] row = new String[R];
	        for (int r = 0 ; r < R ; r++) {
	            row[r] = arr[r][c];
	        }
	        res.add(row);
	    }
		return res;
	}
}
