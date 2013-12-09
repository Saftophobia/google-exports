package term;

import java.util.List;

import parser.FOLNode;

/**
 * @author Ravi Mohan
 * @author Ciaran O'Reilly
 */
public interface Term extends FOLNode {
	List<Term> getArgs();

	Term copy();
}
