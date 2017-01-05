package mak.entropycollection.stringmodels;

import java.util.Map.Entry;

import mak.entropycollection.models.Pair;

public abstract class EntropyString {

	public static double LOG_BASE = 2.0;

	  private EntropyString() {}
	  
	  public strictfp static double calculateConditionalEntropy(String[] dataVector, String[] conditionVector)
	  {
		  JointProbabilityStateString state = new JointProbabilityStateString(dataVector,conditionVector);

	    double jointValue, condValue;
	    double condEntropy = 0.0;

	    for (Entry<Pair<String,String>,Double> e : state.jointProbMap.entrySet())
	    {
	      jointValue = e.getValue();
	      condValue = state.secondProbMap.get(e.getKey().b);
	      if ((jointValue > 0) && (condValue > 0))
	      {
	        condEntropy -= jointValue * Math.log(jointValue / condValue);
	      }
	    }
	    
	    condEntropy /= Math.log(LOG_BASE);

	    return condEntropy/state.jointProbMap.size();
	  }//calculateConditionalEntropy(double [],double [])

}
