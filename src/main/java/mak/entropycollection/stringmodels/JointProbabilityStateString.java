package mak.entropycollection.stringmodels;

import java.util.HashMap;
import java.util.Map.Entry;

import mak.entropycollection.models.Pair;

public class JointProbabilityStateString {

	public final HashMap<Pair<String,String>,Double> jointProbMap;
	  public final HashMap<String,Double> firstProbMap;
	  public final HashMap<String,Double> secondProbMap;

//	  public final int jointMaxVal;
//	  public final int firstMaxVal;
//	  public final int secondMaxVal;
	  
	  public JointProbabilityStateString(String[] firstVector, String[] secondVector)
	  {
	    jointProbMap = new HashMap<Pair<String,String>,Double>();
	    firstProbMap = new HashMap<String,Double>();
	    secondProbMap = new HashMap<String,Double>();

	    String firstVal, secondVal;
	    Pair<String,String> jointVal;
	    String tmpKey;
		// tmpKey is used to reduce the amount of autoboxing, and is probably premature optimisation
	    Integer count;

	    int vectorLength = firstVector.length;
	    double doubleLength = firstVector.length;

	    //round input to integers
//	    int[] firstNormalisedVector = new int[vectorLength];
//	    int[] secondNormalisedVector = new int[vectorLength];
//	    firstMaxVal = ProbabilityState.normaliseArray(firstVector,firstNormalisedVector);
//	    secondMaxVal = ProbabilityState.normaliseArray(secondVector,secondNormalisedVector);	   
//	    jointMaxVal = firstMaxVal * secondMaxVal;

	    HashMap<Pair<String,String>,Integer> jointCountMap = new HashMap<Pair<String,String>,Integer>();
	    HashMap<String,Integer> firstCountMap = new HashMap<String,Integer>();
	    HashMap<String,Integer> secondCountMap = new HashMap<String,Integer>();

	    for (int i = 0; i < vectorLength; i++)
	    {
	        firstVal = firstVector[i];
	        secondVal = secondVector[i];
	        jointVal = new Pair<String,String>(firstVal,secondVal);

	        count = jointCountMap.get(jointVal);
	        if (count == null)
	        {
	            jointCountMap.put(jointVal,1);
	        }
	        else
	        {
	            jointCountMap.put(jointVal,count + 1);
	        }

	        tmpKey = firstVal;
	        count = firstCountMap.remove(tmpKey);
	        if (count == null)
	        {
	            firstCountMap.put(tmpKey,1);
	        }
	        else
	        {
	            firstCountMap.put(tmpKey,count + 1);
	        }

	        tmpKey = secondVal;
	        count = secondCountMap.remove(tmpKey);
	        if (count == null)
	        {
	            secondCountMap.put(tmpKey,1);
	        }
	        else
	        {
	            secondCountMap.put(tmpKey,count + 1);
	        }
	    }

	    for (Entry<Pair<String,String>,Integer> e : jointCountMap.entrySet())
	    {
	        jointProbMap.put(e.getKey(),e.getValue() / doubleLength);
	    }

	    for (Entry<String,Integer> e : firstCountMap.entrySet())
	    {
	        firstProbMap.put(e.getKey(),e.getValue() / doubleLength);
	    }

	    for (Entry<String,Integer> e : secondCountMap.entrySet())
	    {
	        secondProbMap.put(e.getKey(),e.getValue() / doubleLength);
	    }
	  }//constructor(double[],double[])
	}//class JointProbabilityState