package mak.entropycollection;

import java.util.List;

import mak.entropycollection.models.Entropy;

public class Main {

	public static void main(String[] args) {

		int[][] dataset = DataLoader.readcsvfile("desiredattributes.csv");
		List<double[]> vectors = DataLoader.transpose(dataset);
		String[] attributes = DataLoader.attributes;

		/**	Finding Simple Entropy of a single attribute**/
		System.out.println("Shanan Entropy.....");
		int index = 0;
		for (double[] vector : vectors) {
			double entropy = Entropy.calculateEntropy(vector);
			System.out.print(attributes[index] + " = " + entropy);
			System.out.println("\n");
			index++;
		}System.out.println("\n\n");

		/**	Finding Conditional Entropy of two attributes**/
		System.out.println("Conditional Entropy.....");
		int j = 0;
		for (double[] vector : vectors) {
			int k = 0;
			for (double[] vector2 : vectors) {
				if (j != k){
					double entropy = Entropy.calculateConditionalEntropy(vector, vector2);
					System.out.print(attributes[j]+" | "+attributes[k] + " = " + entropy);
					System.out.println("\n");
				}
				k++;
			}
			j++;
		}System.out.println("\n\n");
		
		/**	Finding Joint Entropy of two attributes**/
		System.out.println("Joint Entropy.....");
		int index1 = 0;
		for (double[] vector : vectors) {
			int index2 = 0;
			for (double[] vector2 : vectors) {
				if (index1 != index2){
					double entropy = Entropy.calculateJointEntropy(vector, vector2);
					System.out.print(attributes[index1]+" | "+attributes[index2] + " = " + entropy);
					System.out.println("\n");
				}
				index2++;
			}
			index1++;
		}
	}

}
