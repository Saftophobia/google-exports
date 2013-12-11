package proofs;

import java.util.List;


public interface ProofStep {
	
	int getStepNumber();//stepnumber getter

	void setStepNumber(int step);//stepnumber setter

	List<ProofStep> getPredecessorSteps(); //get predecessor steps for tracing

	String getProof(); //print the proof(step)

	String getJustification();//prints more details regarding the proof step
}
