package clause;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import proofs.ProofStepRenaming;
import sentence.AtomicSentence;
import sentence.Sentence;
import term.Term;
import term.Variable;
import utility.SubstVisitor;
import abstracts.Chain;
import abstracts.Clause;
import abstracts.Literal;


public class StandardizeApart {
	private VariableCollector variableCollector = null; // it gets all variables
														// in the correct order
														// given the sentence
	private SubstVisitor substVisitor = null;

	// constructor with instance variables initializers
	public StandardizeApart() {
		variableCollector = new VariableCollector();
		substVisitor = new SubstVisitor();
	}

	// constructor with instance variables setters
	public StandardizeApart(VariableCollector variableCollector,
			SubstVisitor substVisitor) {
		this.variableCollector = variableCollector;
		this.substVisitor = substVisitor;
	}

	// renaming the variables to avoid clashes
	public StandardizeApartResult standardizeApart(Sentence sentence,
			StandardizeApartIndexical standardizeApartIndexical) {
		Set<Variable> toRename = variableCollector
				.collectAllVariables(sentence); // temp set
		Map<Variable, Term> renameSubstitution = new HashMap<Variable, Term>(); // temp
																				// rename
																				// subst.
																				// hashmap
		Map<Variable, Term> reverseSubstitution = new HashMap<Variable, Term>();// temp
																				// reverse
																				// subst.
																				// hashmap

		// forevery variable we have to rename
		for (Variable var : toRename) {
			Variable v = null; // temp variable
			do {
				v = new Variable(standardizeApartIndexical.getPrefix()
						+ standardizeApartIndexical.getNextIndex()); // temp is
																		// the
																		// prefix
																		// of
																		// the
																		// standardizesentence
																		// + the
																		// next
																		// index(prefix)
				// Ensure the new variable name is not already
				// accidentally used in the sentence
			} while (toRename.contains(v));
			// feed the rename and reverse hashmaps ( to know the original and
			// the edited values)
			renameSubstitution.put(var, v);
			reverseSubstitution.put(v, var);
		}
		// create new sentence with the renamed substitutions
		Sentence standardized = substVisitor
				.subst(renameSubstitution, sentence);
		// return the standardizeapartresult
		return new StandardizeApartResult(sentence, standardized,
				renameSubstitution, reverseSubstitution);
	}

	// renaming the variables in clause to avoid clashes
	public Clause standardizeApart(Clause clause,
			StandardizeApartIndexical standardizeApartIndexical) {
		// temp set of variables to be renamed
		Set<Variable> toRename = variableCollector.collectAllVariables(clause);
		// hashmap of the renameSubstitution
		Map<Variable, Term> renameSubstitution = new HashMap<Variable, Term>();
		// forevery variable to be renamed
		for (Variable var : toRename) {
			Variable v = null;// temp var
			do {
				// set the temp var to the indexical prefix + the next
				// index(prefix)
				v = new Variable(standardizeApartIndexical.getPrefix()
						+ standardizeApartIndexical.getNextIndex());
				// Ensure the new variable name is not already
				// accidentally used in the sentence
			} while (toRename.contains(v));
			// feed the renamesub hashmap with the new/edited variable
			renameSubstitution.put(var, v);
		}
		// if there are renameSubstitution to be made
		if (renameSubstitution.size() > 0) {
			List<Literal> literals = new ArrayList<Literal>(); // temp literals
																// list

			for (Literal l : clause.getLiterals()) {
				// for every literal, it transform that literal to an atomic
				// sentence first then
				// call its accept function to see how it will behave with the
				// given inputs
				// and return the change occurred based on the substitution
				literals.add(substVisitor.subst(renameSubstitution, l));
			}
			Clause renamed = new Clause(literals); // create new clause with the
													// literals listed
			// set the proof step for printing/tracing
		//	renamed.setProofStep(new ProofStepRenaming(renamed, clause
		//			.getProofStep()));
			// return the clause
			return renamed;
		}

		return clause;
	}

	// renaming the variables in chains to avoid clashes
	public Chain standardizeApart(Chain chain,
			StandardizeApartIndexical standardizeApartIndexical) {
		// temp set of variables to be renamed

		Set<Variable> toRename = variableCollector.collectAllVariables(chain);
		// hashmap of the renameSubstitution

		Map<Variable, Term> renameSubstitution = new HashMap<Variable, Term>();
		// forevery variable to be renamed

		for (Variable var : toRename) {
			Variable v = null;// temp var
			do {
				// set the temp var to the indexical prefix + the next
				// index(prefix)

				v = new Variable(standardizeApartIndexical.getPrefix()
						+ standardizeApartIndexical.getNextIndex());
				// Ensure the new variable name is not already
				// accidentally used in the sentence
			} while (toRename.contains(v));
			// feed the renamesub hashmap with the new/edited variable

			renameSubstitution.put(var, v);
		}

		// if there are renameSubstitution to be made
		if (renameSubstitution.size() > 0) {
			List<Literal> lits = new ArrayList<Literal>();

			for (Literal l : chain.getLiterals()) {
				// for every literal, it transform that literal to an atomic
				// sentence first then
				// call its accept function to see how it will behave with the
				// given inputs
				// and return the change occurred based on the substitution
				AtomicSentence atom = (AtomicSentence) substVisitor.subst(
						renameSubstitution, l.getAtomicSentence());
				lits.add(l.newInstance(atom));
			}

			Chain renamed = new Chain(lits);// create new clause with the
											// literals listed
			// set proof step for tracing/print
	//		renamed.setProofStep(new ProofStepRenaming(renamed, chain
		//			.getProofStep()));
			// return the chain
			return renamed;
		}
		// return the chain
		return chain;
	}

	public Map<Variable, Term> standardizeApart(List<Literal> l1Literals,
			List<Literal> l2Literals,
			StandardizeApartIndexical standardizeApartIndexical) {
		// temp set of variables to be renamed
		Set<Variable> toRename = new HashSet<Variable>();
		// feed the temp set with all variables in literal p1
		for (Literal pl : l1Literals) {
			toRename.addAll(variableCollector.collectAllVariables(pl
					.getAtomicSentence()));
		}
		// feed the temp set with all variables in literal p2
		for (Literal nl : l2Literals) {
			toRename.addAll(variableCollector.collectAllVariables(nl
					.getAtomicSentence()));
		}
		// initialize a new hashmap
		Map<Variable, Term> renameSubstitution = new HashMap<Variable, Term>();
		// forevery variable to be renamed, create a new variable with its
		// prefix and nextindex and compare it to the list of variables
		for (Variable var : toRename) {
			Variable v = null;
			do {
				v = new Variable(standardizeApartIndexical.getPrefix()
						+ standardizeApartIndexical.getNextIndex());
				// Ensure the new variable name is not already
				// accidentally used in the sentence
			} while (toRename.contains(v));
			// add the variable if its doesn't exist
			renameSubstitution.put(var, v);
		}
		//initialize +/-ve literals lists
		List<Literal> posLits = new ArrayList<Literal>();
		List<Literal> negLits = new ArrayList<Literal>();
		//classify literals into the +/-ve lists
		for (Literal pl : l1Literals) {
			posLits.add(substVisitor.subst(renameSubstitution, pl));
		}
		for (Literal nl : l2Literals) {
			negLits.add(substVisitor.subst(renameSubstitution, nl));
		}
		//sort them out in the l1 and l2 literals 
		l1Literals.clear();
		l1Literals.addAll(posLits);
		l2Literals.clear();
		l2Literals.addAll(negLits);
		//return the final renamedsubst. hashmap
		return renameSubstitution;
	}
}
