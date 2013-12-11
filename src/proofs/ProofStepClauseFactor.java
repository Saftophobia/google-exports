package proofs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import term.Term;
import term.Variable;
import abstracts.Clause;
import abstracts.Literal;

public class ProofStepClauseFactor extends AbstractProofStep {
	// list of predecessors for tracing
	private List<ProofStep> predecessors = new ArrayList<ProofStep>();
	// clause that represents the factor
	private Clause factor = null;
	// clause for every proof step
	private Clause factorOf = null;
	// literals lx and ly to be compared
	private Literal lx = null;
	private Literal ly = null;
	// original and renamed subst hashmaps
	private Map<Variable, Term> subst = new LinkedHashMap<Variable, Term>();
	private Map<Variable, Term> renameSubst = new LinkedHashMap<Variable, Term>();

	// constructor with IV setters
	public ProofStepClauseFactor(Clause factor, Clause factorOf, Literal lx,
			Literal ly, Map<Variable, Term> subst,
			Map<Variable, Term> renameSubst) {
		this.factor = factor;
		this.factorOf = factorOf;
		this.lx = lx;
		this.ly = ly;
		this.subst.putAll(subst);
		this.renameSubst.putAll(renameSubst);
		this.predecessors.add(factorOf.getProofStep());
	}

	//
	// START-ProofStep
	public List<ProofStep> getPredecessorSteps() {
		//return the predecessors for tracing
		return Collections.unmodifiableList(predecessors);
	}

	public String getProof() {
		//return the proof string
		return factor.toString();
	}

	public String getJustification() {
		//return the proof justification for the proof step 
		return "Factor of " + factorOf.getProofStep().getStepNumber() + "  ["
				+ lx + ", " + ly + "], subst=" + subst + ", renaming="
				+ renameSubst;
	}
	// END-ProofStep
	//
}
