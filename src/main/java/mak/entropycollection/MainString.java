package mak.entropycollection;

import java.util.ArrayList;
import java.util.List;

import mak.entropycollection.stringmodels.EntropyString;

public class MainString {

	public static void main(String[] args) {
		String[][] dataset = StringDataLoader.readcsvfile("datasetscale.csv");
		List<String[]> vectors = StringDataLoader.transpose(dataset);
		String[] attributes = StringDataLoader.attributes;
		
		/**	Shanaon Entropy*/
		int in=0;
		for(String[] vector : vectors){
			double entropy = EntropyString.calculateEntropyString(vector);
			System.out.print(attributes[in]+" = " + entropy);
			System.out.println("\n");
			in++;
		}
		System.out.println("\n\n");
		
		/**	Finding Conditional Entropy of two attributes**/
		System.out.println("Conditional Entropy.....");
		System.out.print("\t\t");
		System.out.println(attributes.length);
		
		
		for(String val : attributes){
			System.out.print(val+",");
		}
		List<double[]> d_vectors  = getEntropyTable(vectors);
		System.out.println();
		int j = 0;
		for (double[] vector : d_vectors) {
			System.out.print(attributes[j]);
			for(int i=0; i < vector.length; i++){
				System.out.print("\t"+vector[i]);
			}
			j++;
			System.out.println("\n");
		}

	}
	public static List<double[]> getEntropyTable(List<String[]> vectors){
		List<double[]> r_vectors = new ArrayList<double[]>();
		int j = 0;
//		System.out.println("Vector size : "+vectors.size());
		for (String[] vector : vectors) {
//			System.out.println("Vector size : "+vector.length);
			double[] temp = new double[(vector.length)+2];
//			System.out.println("Vector size : "+temp.length);
			int k = 0;
			for (String[] vector2 : vectors) {
				if (j != k){
//					System.out.println(k+" ");
					double entropy = EntropyString.calculateConditionalEntropy(vector, vector2);
					temp[k] = round((entropy),3);
//					System.out.println(" : "+entropy);
				}else{
//					System.out.println(k+" ");
					temp[k] = 0.000000;
				}
				k++;
			}
			temp[(vector.length)] = EntropyString.getMinimum(temp);
			temp[(vector.length)+1] = EntropyString.getMaximum(temp);
			r_vectors.add(temp);
			j++;
		}
		
		return r_vectors;
	}
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}

}
