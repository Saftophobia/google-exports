package parser;

import java.util.List;

// This interface is used present any part of FOL Sentence or term
public interface FOLNode {
	String getSymbolicName();

	boolean isCompound();
	
	List<? extends FOLNode> getArgs();

	Object accept(FOLVisitor v,Object arg);

	FOLNode copy();
}
