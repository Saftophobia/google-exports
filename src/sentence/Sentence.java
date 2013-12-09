package sentence;

import parser.FOLNode;

/**
 * @author Ravi Mohan
 * @author Ciaran O'Reilly
 */
public interface Sentence extends FOLNode {
	Sentence copy();
}
