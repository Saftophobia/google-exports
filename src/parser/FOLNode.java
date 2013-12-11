package parser;

import java.util.List;

// This interface is used to present any part of FOL Sentence or Term
public interface FOLNode {
	
	// get the symbol name of the sentence or term
	// Term:
	// function returns the function name
	// constant returns constant value
	// variable returns variable name
	// Sentences:
	// depending on the sentence type equality => =, 
	// negation = >not etc...
	String getSymbolicName();

	// a boolean method that says if the term or the sentence 
	//include inside more terms or sentences
	boolean isCompound();
	
	// this method return the arguments of a compound term or sentence
	// which are something of type FOLnode.
	List<? extends FOLNode> getArgs();
	
	// this method is the core method that define the behavior of the FOLNode
	// in case of unification the substVisitor which is a subclass of FOLVisitor
	// was used to do the substitution part and the decompose part 
	// of the unification algorithm
	// in case of the clause form, it handles each step in the algorithm, such as removing implications, quantifiers ..etc
	// as each step had its own visitor, as such, the response differs from one algorithm step to another
	// the function is designed to be generic so that it can be used in any purpose problem
	// it takes and outputs Object data type which is the most generic data type.
	Object accept(FOLVisitor v,Object arg);
	
	// a method to clone the FOLNode
	FOLNode copy();
}
