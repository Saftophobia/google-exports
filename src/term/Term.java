package term;

import java.util.List;

import parser.FOLNode;


// this class present a generic term
public interface Term extends FOLNode {
	
	// it forces its implementer to have a get arguments method
	List<Term> getArgs();
	// it forces its implementer to have a copy method
	Term copy();
}
