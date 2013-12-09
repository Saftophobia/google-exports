package proofs;

import java.util.List;


public interface ProofStep {
	int getStepNumber();

	void setStepNumber(int step);

	List<ProofStep> getPredecessorSteps();

	String getProof();

	String getJustification();
}
