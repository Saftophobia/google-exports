package proofs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * rename proof step
 * 
 */
public class ProofStepRenaming extends AbstractProofStep {
	private List<ProofStep> predecessors = new ArrayList<ProofStep>(); //list of predecessors for tracing
	private Object proof = ""; //initialize proof step

	//constructor with I.V setters
	public ProofStepRenaming(Object proof, ProofStep predecessor) {
		this.proof = proof;
		this.predecessors.add(predecessor);
	}

	//
	// START-ProofStep
	@Override
	public List<ProofStep> getPredecessorSteps() {
		//return predecessors list
		return Collections.unmodifiableList(predecessors);
	}

	@Override
	public String getProof() {
		//print the proof
		return proof.toString();
	}

	@Override
	public String getJustification() {
		//printing the proof step
		return "Renaming of " + predecessors.get(0).getStepNumber();
	}
	// END-ProofStep
	//
}
