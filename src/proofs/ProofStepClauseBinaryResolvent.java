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



/**
 *  iteratively applying the resolution rule in a suitable way allows for telling whether a 
 *  propositional formula is satisfiable and for proving that a
 *   first-order formula is unsatisfiable
 * 
 */
public class ProofStepClauseBinaryResolvent extends AbstractProofStep {
	private List<ProofStep> predecessors = new ArrayList<ProofStep>(); //list of predecessors for tracing
	private Clause resolvent = null; //to be proved
	private Literal posLiteral = null; //positive literal placeholder
	private Literal negLiteral = null; //negative literall placeholder
	private Clause parent1, parent2 = null; //parents of the resolvent
	private Map<Variable, Term> subst = new LinkedHashMap<Variable, Term>(); //hashmaps of original sub
	private Map<Variable, Term> renameSubst = new LinkedHashMap<Variable, Term>(); //hashmaps of the rename sub. made

	//constructor with Instance variables setters
	public ProofStepClauseBinaryResolvent(Clause resolvent, Literal pl,
			Literal nl, Clause parent1, Clause parent2,
			Map<Variable, Term> subst, Map<Variable, Term> renameSubst) {
		this.resolvent = resolvent;
		this.posLiteral = pl;
		this.negLiteral = nl;
		this.parent1 = parent1;
		this.parent2 = parent2;
		this.subst.putAll(subst);
		this.renameSubst.putAll(renameSubst);
		//add the parents to the predecessors 
		this.predecessors.add(parent1.getProofStep()); 
		this.predecessors.add(parent2.getProofStep());
	}

	//
	// START-ProofStep
	public List<ProofStep> getPredecessorSteps() {
		//return the list of predecessors 
		return Collections.unmodifiableList(predecessors);
	}

	public String getProof() {
		//print the proving resolvent
		return resolvent.toString();
	}

	public String getJustification() {
		//proof the step
		//1) get the step number of each parents
		int lowStep = parent1.getProofStep().getStepNumber();
		int highStep = parent2.getProofStep().getStepNumber();
		//if predecessors parents are crossed over, swap
		if (lowStep > highStep) {
			lowStep = highStep;
			highStep = parent1.getProofStep().getStepNumber();
		}
		//return the justification in string format
		return "Resolution: " + lowStep + ", " + highStep + "  [" + posLiteral
				+ ", " + negLiteral + "], subst=" + subst + ", renaming="
				+ renameSubst;
	}
	// END-ProofStep
	//
}
