package term;

import java.util.List;

import parser.FOLNode;


public interface Term extends FOLNode {
	List<Term> getArgs();

	Term copy();
}
