package abstracts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;


/**
 * 
 * A Chain is a sequence of literals (while a clause is a set) - order is
 * important for a chain.
 * 
 */
public class Chain {
	//empty chain 
	private static List<Literal> _emptyLiteralsList = Collections
			.unmodifiableList(new ArrayList<Literal>());
	//list of literals initialized
	private List<Literal> literals = new ArrayList<Literal>();
	//initializing proofstep
	//private ProofStep proofStep = null;
	
	//empty constructor for empty chain
	public Chain() {
		
	}
	//constructor with initial list of literals
	public Chain(List<Literal> literals) {
		this.literals.addAll(literals);
	}
	//constructor with initial "set" of literals
	public Chain(Set<Literal> literals) {
		this.literals.addAll(literals);
	}

	

	//check if the chain is empty
	public boolean isEmpty() {
		return literals.size() == 0;
	}

	//add literal to the chain(list) of literals
	public void addLiteral(Literal literal) {
		literals.add(literal);
	}

	//get the first index in the chain
	public Literal getHead() {
		if (0 == literals.size()) {
			return null;
		}
		return literals.get(0);
	}

	//return the last index in the chain, return "emptyliterallist" if its empty
	public List<Literal> getTail() {
		if (0 == literals.size()) {
			return _emptyLiteralsList;
		}
		return Collections
				.unmodifiableList(literals.subList(1, literals.size()));
	}
	//get list of literals size
	public int getNumberLiterals() {
		return literals.size();
	}
	//get the list of literals
	public List<Literal> getLiterals() {
		return Collections.unmodifiableList(literals);
	}

	/**
	 * A contrapositive of a chain is a permutation in which a different literal
	 * is placed at the front. The contrapositives of a chain are logically
	 * equivalent to the original chain.
	 */
	public List<Chain> getContrapositives() {
		//initialize the chain of contrapositives.
		List<Chain> contrapositives = new ArrayList<Chain>();
		//temp list of literals.
		List<Literal> lits = new ArrayList<Literal>();

		for (int i = 1; i < literals.size(); i++) { //forevery literal
			lits.clear();  //clear lits
			lits.add(literals.get(i)); //add the literal at position i.
			lits.addAll(literals.subList(0, i)); //add all literals from 0 to i.
			lits.addAll(literals.subList(i + 1, literals.size())); //add all literals from i+1 to the end of the list.
			Chain cont = new Chain(lits); // create the chain with the current changes.
		//	cont.setProofStep(new ProofStepChainContrapositive(cont, this)); //set the proof step for printing 
			contrapositives.add(cont); // add the chain to the contrapositives.
		}
		//return the amended list.
		return contrapositives;
	}

	//return the list of literals in a string. ( every literal)
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<");

		for (int i = 0; i < literals.size(); i++) {
			if (i > 0) {
				sb.append(",");
			}
			sb.append(literals.get(i).toString());
		}

		sb.append(">");

		return sb.toString();
	}
}
