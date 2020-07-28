package com.hmm;

import java.util.Arrays;
import java.util.List;

import com.hmm.WikipediaViterbi.MedicalState;
import com.hmm.WikipediaViterbi.Symptom;

import io.github.adrianulbona.hmm.Model;
import io.github.adrianulbona.hmm.solver.MostProbableStateSequenceFinder;
import io.github.adrianulbona.hmm.solver.MostProbableStateSequenceFinder.Observer;
import io.github.adrianulbona.hmm.solver.MostProbableStateSequenceFinder.OptimalTransition;

public class HMMTester
{

	public static void main(String[] args)
	{

		Model<MedicalState, Symptom> model = WikipediaViterbi.INSTANCE.model;
		List<Symptom> symptoms = Arrays.asList(WikipediaViterbi.Symptom.NORMAL, WikipediaViterbi.Symptom.COLD,
				WikipediaViterbi.Symptom.DIZZY);
		List<MedicalState> evolution = new MostProbableStateSequenceFinder<>(model).basedOn(symptoms);

		MostProbableStateSequenceFinder<MedicalState, Symptom> solver = new MostProbableStateSequenceFinder<>(model);
		solver.addObserver(new Tracer());
		solver.basedOn(symptoms);
	}
	
	private static class Tracer implements Observer<MedicalState, Symptom> {
		@Override
		public void processingObservation(Symptom observation) {
			System.out.println("processing observation: " + observation);
		}

		@Override
		public void foundOptimalTransitions(OptimalTransition<MedicalState> optimalTransition) {
			System.out.printf("%s <- %.5f%n", optimalTransition.getState(), optimalTransition.getProbability());
		}
	}
}
