package abstracts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Conjunctive Normal Form (CNF) : a conjunction of clauses, where each clause
 * is a disjunction of literals.
 */
public class CNF {
	//conjuctionofclauses initializer 
	private List<Clause> conjunctionOfClauses = new ArrayList<Clause>();

	//constructor with conjuctionofclauses setter
	public CNF(List<Clause> conjunctionOfClauses) {
		this.conjunctionOfClauses.addAll(conjunctionOfClauses);
	}
	//return size of the conjuctionofclauses
	public int getNumberOfClauses() {
		return conjunctionOfClauses.size();
	}
	// conjuctionofclauses getter
	public List<Clause> getConjunctionOfClauses() {
		return Collections.unmodifiableList(conjunctionOfClauses);
	}

	//prints every cluase in the conjuctionofclauses
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < conjunctionOfClauses.size(); i++) {
			if (i > 0) {
				sb.append(",");
			}
			sb.append(conjunctionOfClauses.get(i).toString());
		}

		return sb.toString();
	}
}
