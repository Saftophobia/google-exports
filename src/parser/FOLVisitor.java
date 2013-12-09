package parser;

import sentence.ConnectedSentence;
import sentence.NotSentence;
import sentence.Predicate;
import sentence.QuantifiedSentence;
import sentence.TermEquality;
import term.Constant;
import term.Function;
import term.Variable;



/**
 * @author Ravi Mohan
 * 
 */
public interface FOLVisitor {
	public Object visitPredicate(Predicate p, Object arg);

	public Object visitTermEquality(TermEquality equality, Object arg);

	public Object visitVariable(Variable variable, Object arg);

	public Object visitConstant(Constant constant, Object arg);

	public Object visitFunction(Function function, Object arg);

	public Object visitNotSentence(NotSentence sentence, Object arg);

	public Object visitConnectedSentence(ConnectedSentence sentence, Object arg);

	public Object visitQuantifiedSentence(QuantifiedSentence sentence,
			Object arg);
}
