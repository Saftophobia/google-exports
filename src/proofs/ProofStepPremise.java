package proofs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * proof by a claim
 * 
 */
public class ProofStepPremise extends AbstractProofStep {
	//
	private static final List<ProofStep> _noPredecessors = new ArrayList<ProofStep>(); //no predecessors
	//
	private Object proof = "";

	//constructor w/ proof setter
	public ProofStepPremise(Object proof) {
		this.proof = proof;
	}

	//
	// START-ProofStep
	@Override
	//return the list of predecessors for tracing/printing purposes
	public List<ProofStep> getPredecessorSteps() {
		return Collections.unmodifiableList(_noPredecessors);
	}

	@Override
	public String getProof() {
		return proof.toString(); //return the proof step
	}

	@Override
	public String getJustification() {
		return "Premise"; //return premise, as in ... a claim
	}
	// END-ProofStep
	//
}
