package mak.entropycollection.stringmodels;

import java.util.Map.Entry;

import mak.entropycollection.models.Pair;
import mak.entropycollection.models.ProbabilityState;

public abstract class EntropyString {

	public static double LOG_BASE = 2.0;

	private EntropyString() {
	}

	public strictfp static double calculateConditionalEntropy(String[] dataVector, String[] conditionVector) {
		JointProbabilityStateString state = new JointProbabilityStateString(dataVector, conditionVector);

		double jointValue, condValue;
		double condEntropy = 0.0;

		for (Entry<Pair<String, String>, Double> e : state.jointProbMap.entrySet()) {
			jointValue = e.getValue();
			condValue = state.secondProbMap.get(e.getKey().b);
			if ((jointValue > 0) && (condValue > 0)) {
				condEntropy -= jointValue * Math.log(jointValue / condValue);
			}
		}

		condEntropy /= Math.log(LOG_BASE);

		return condEntropy / state.jointProbMap.size();
	}// calculateConditionalEntropy(double [],double [])

	public static double calculateEntropyString(String[] dataVector) {
		ProbabilityStateString state = new ProbabilityStateString(dataVector);

		double entropy = 0.0;
		for (Double prob : state.probMap.values()) {
			if (prob > 0) {
				entropy -= prob * Math.log(prob);
			}
		}

		entropy /= Math.log(LOG_BASE);

		return entropy / state.probMap.size();
	}// calculateEntropy(double [])

	public static double getMaximum(double[] vector) {
		double max = 0;
		for (double val : vector) {
			if (val > max)
				max = val;
		}
		return max;
	}

	public static double getMinimum(double[] vector) {
		double min = 1;
		for (double val : vector) {
			if (val != 0) {
				if (val < min)
					min = val;
			}
		}
		return min;
	}
}
