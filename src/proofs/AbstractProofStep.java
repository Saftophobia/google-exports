package proofs;

import java.util.List;


public abstract class AbstractProofStep implements ProofStep {
	private int step = 0;

	public AbstractProofStep() {

	}

	//
	// START-ProofStep
	//get step number
	public int getStepNumber() {
		return step;
	}
	//set step number of the class
	public void setStepNumber(int step) {
		this.step = step;
	}
	//get Predecessor steps for tracing.
	public abstract List<ProofStep> getPredecessorSteps();
	//print step
	public abstract String getProof();
	//prints more details regarding the proof step
	public abstract String getJustification();

	// END-ProofStep
	//
}
