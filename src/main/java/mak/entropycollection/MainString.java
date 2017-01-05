package mak.entropycollection;

import java.util.List;

import mak.entropycollection.stringmodels.EntropyString;

public class MainString {

	public static void main(String[] args) {
		String[][] dataset = StringDataLoader.readcsvfile("MarketSegmentation.csv");
		List<String[]> vectors = StringDataLoader.transpose(dataset);
		String[] attributes = StringDataLoader.attributes;
		
		/**	Finding Conditional Entropy of two attributes**/
		System.out.println("Conditional Entropy.....");
		int j = 0;
		for (String[] vector : vectors) {
			int k = 0;
			for (String[] vector2 : vectors) {
				if (j != k){
					double entropy = EntropyString.calculateConditionalEntropy(vector, vector2);
					System.out.print(attributes[j]+" | "+attributes[k] + " = " + entropy);
					System.out.println("\n");
				}
				k++;
			}
			j++;
		}System.out.println("\n\n");

	}

}
