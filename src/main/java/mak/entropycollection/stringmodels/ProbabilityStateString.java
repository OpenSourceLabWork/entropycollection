package mak.entropycollection.stringmodels;

import java.util.HashMap;
import java.util.Map.Entry;

public class ProbabilityStateString {

	public final HashMap<String, Double> probMap;
//	public final int maxState;


	  public ProbabilityStateString(String[] dataVector)
	  {
	    probMap = new HashMap<String,Double>();
//	    int vectorLength = dataVector.length;
	    double doubleLength = dataVector.length;

	    //round input to integers
//	    String[] normalisedVector = new String[vectorLength];
//	    maxState = normaliseArray(dataVector,normalisedVector);
	   
	    HashMap<String,Integer> countMap = new HashMap<String,Integer>();

	    for (String value : dataVector)
	    {
	    	String tmpKey = value;
	        Integer tmpValue = countMap.remove(tmpKey);
	        if (tmpValue == null)
	        {
	            countMap.put(tmpKey,1);
	        }
	        else
	        {
	            countMap.put(tmpKey,tmpValue + 1);
	        }
	    }

	    for (Entry<String,Integer> e : countMap.entrySet())
	    {
	        probMap.put(e.getKey(),e.getValue() / doubleLength);
	    }
	  }// constructor(double[])

/*	  
	public static int normaliseArray(double[] inputVector, int[] outputVector) {
		int minVal = 0;
		int maxVal = 0;
		int currentValue;
		int vectorLength = inputVector.length;

		if (vectorLength > 0) {
			minVal = (int) Math.floor(inputVector[0]);
			maxVal = (int) Math.floor(inputVector[0]);

			for (int i = 0; i < vectorLength; i++) {
				currentValue = (int) Math.floor(inputVector[i]);
				outputVector[i] = currentValue;

				if (currentValue < minVal) {
					minVal = currentValue;
				}

				if (currentValue > maxVal) {
					maxVal = currentValue;
				}
			} 

			for (int i = 0; i < vectorLength; i++) {
				outputVector[i] = outputVector[i] - minVal;
			}

			maxVal = (maxVal - minVal) + 1;
		}

		return maxVal;
	}// normaliseArray(double[],double[])
	*/

/*	
	public static int mergeArrays(double[] firstVector, double[] secondVector, double[] outputVector) {
		int[] firstNormalisedVector;
		int[] secondNormalisedVector;
		int firstNumStates;
		int secondNumStates;
		int[] stateMap;
		int stateCount;
		int curIndex;
		int vectorLength = firstVector.length;

		firstNormalisedVector = new int[vectorLength];
		secondNormalisedVector = new int[vectorLength];

		firstNumStates = normaliseArray(firstVector, firstNormalisedVector);
		secondNumStates = normaliseArray(secondVector, secondNormalisedVector);

		stateMap = new int[firstNumStates * secondNumStates];
		stateCount = 1;
		for (int i = 0; i < vectorLength; i++) {
			curIndex = firstNormalisedVector[i] + (secondNormalisedVector[i] * firstNumStates);
			if (stateMap[curIndex] == 0) {
				stateMap[curIndex] = stateCount;
				stateCount++;
			}
			outputVector[i] = stateMap[curIndex];
		}

		return stateCount;
	}// mergeArrays(double[],double[],double[])
*/
	
	public static void printIntVector(int[] vector) {
		for (int i = 0; i < vector.length; i++) {
			if (vector[i] > 0) {
				System.out.println("Val at i=" + i + ", is " + vector[i]);
			}
		}
	}

	public static void printDoubleVector(String[] vector) {
		for (int i = 0; i < vector.length; i++) {
//			if (vector[i] > 0) {
				System.out.println("Val at i=" + i + ", is " + vector[i]);
//			}
		} 
	}
}
