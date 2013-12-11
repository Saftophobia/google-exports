package proofs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import abstracts.Chain;

/*
 * In logic, the contrapositive of a conditional statement is formed
 * by negating both terms and reversing the direction of inference
 */
public class ProofStepChainContrapositive extends AbstractProofStep {
	private List<ProofStep> predecessors = new ArrayList<ProofStep>(); // list
																		// of
																		// predecessor
																		// steps
																		// for
																		// tracing
	private Chain contrapositive = null; // contrapositive proofs
	private Chain contrapositiveOf = null; // contrapositive proof of every
											// step number

	// constructor with setters
	public ProofStepChainContrapositive(Chain contrapositive,
			Chain contrapositiveOf) {
		this.contrapositive = contrapositive;
		this.contrapositiveOf = contrapositiveOf;
		this.predecessors.add(contrapositiveOf.getProofStep());
	}

	//
	// START-ProofStep
	@Override
	public List<ProofStep> getPredecessorSteps() {
		// get predecessor steps for tracing
		return Collections.unmodifiableList(predecessors);
	}

	@Override
	public String getProof() {
		// print the contrapositive list
		return contrapositive.toString();
	}

	@Override
	public String getJustification() { //print the contrapositive of a specific step number
		return "Contrapositive: "
				+ contrapositiveOf.getProofStep().getStepNumber();
	}
	// END-ProofStep
	//
}
