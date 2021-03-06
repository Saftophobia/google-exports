package unifier;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import parser.FOLNode;
import term.Function;
import term.Term;
import term.Variable;
import utility.SubstVisitor;




/**
 * Artificial Intelligence A Modern Approach (3rd Edition): Figure 9.1, page
 * 328.<br>
 * <br>
 * 
 * <pre>
 * function UNIFY(x, y, theta) returns a substitution to make x and y identical
 *   inputs: x, a variable, constant, list, or compound
 *           y, a variable, constant, list, or compound
 *           theta, the substitution built up so far (optional, defaults to empty)
 *           
 *   if theta = failure then return failure
 *   else if x = y the return theta
 *   else if VARIABLE?(x) then return UNIVY-VAR(x, y, theta)
 *   else if VARIABLE?(y) then return UNIFY-VAR(y, x, theta)
 *   else if COMPOUND?(x) and COMPOUND?(y) then
 *       return UNIFY(x.ARGS, y.ARGS, UNIFY(x.OP, y.OP, theta))
 *   else if LIST?(x) and LIST?(y) then
 *       return UNIFY(x.REST, y.REST, UNIFY(x.FIRST, y.FIRST, theta))
 *   else return failure
 *   
 * ---------------------------------------------------------------------------------------------------
 * 
 * function UNIFY-VAR(var, x, theta) returns a substitution
 *            
 *   if {var/val} E theta then return UNIFY(val, x, theta)
 *   else if {x/val} E theta then return UNIFY(var, val, theta)
 *   else if OCCUR-CHECK?(var, x) then return failure
 *   else return add {var/x} to theta
 * </pre>
 * 
 * Figure 9.1 The unification algorithm. The algorithm works by comparing the
 * structures of the inputs, elements by element. The substitution theta that is
 * the argument to UNIFY is built up along the way and is used to make sure that
 * later comparisons are consistent with bindings that were established earlier.
 * In a compound expression, such as F(A, B), the OP field picks out the
 * function symbol F and the ARGS field picks out the argument list (A, B).
 * 
 * 
 */
public class Unifier {

	// it has an instance of the substVisitor to
	// determine the behavior of the variables when visited with substitutions
	SubstVisitor substVisitor;
	boolean tracer;
	
	public Unifier(boolean tracer) {
		// creating a new instance of the substvisitor 
		this.tracer = tracer;
		substVisitor = new  SubstVisitor();
	}

	/**
	 * Returns a Map<Variable, Term> representing the substitution (i.e. a set
	 * of variable/term pairs) or null which is used to indicate a failure to
	 * unify.
	 * 
	 * @param x
	 *            a variable, constant, list, or compound
	 * @param y
	 *            a variable, constant, list, or compound
	 * 
	 * @return a Map<Variable, Term> representing the substitution (i.e. a set
	 *         of variable/term pairs) or null which is used to indicate a
	 *         failure to unify.
	 */
	public Map<Variable, Term> unify(FOLNode x, FOLNode y) {
		return unify(x, y, new LinkedHashMap<Variable, Term>());
	}

	/**
	 * Returns a Map<Variable, Term> representing the substitution (i.e. a set
	 * of variable/term pairs) or null which is used to indicate a failure to
	 * unify.
	 * 
	 * @param x
	 *            a variable, constant, list, or compound
	 * @param y
	 *            a variable, constant, list, or compound
	 * @param theta
	 *            the substitution built up so far
	 * 
	 * @return a Map<Variable, Term> representing the substitution (i.e. a set
	 *         of variable/term pairs) or null which is used to indicate a
	 *         failure to unify.
	 */
	public Map<Variable, Term> unify(FOLNode x, FOLNode y,
			Map<Variable, Term> theta) {
		if(tracer)
		System.out.println("Unifying  for : "+x+" and "+y+" with "+theta);
		// if theta = failure then return failure
		if (theta == null) {
			return null;
		} else if (x.equals(y)) {
			// else if x = y then return theta
			return theta;
		} else if (x instanceof Variable) {
			// else if VARIABLE?(x) then return UNIVY-VAR(x, y, theta)
			return unifyVar((Variable) x, y, theta);
		} else if (y instanceof Variable) {
			// else if VARIABLE?(y) then return UNIFY-VAR(y, x, theta)
			return unifyVar((Variable) y, x, theta);
		} else if (isCompound(x) && isCompound(y)) {
			// else if COMPOUND?(x) and COMPOUND?(y) then
			// return UNIFY(x.ARGS, y.ARGS, UNIFY(x.OP, y.OP, theta))
			return unify(args(x), args(y), unifyOps(op(x), op(y), theta));
		} else {
			// else return failure
			return null;
		}
	}

	/**
	 * Returns a Map<Variable, Term> representing the substitution (i.e. a set
	 * of variable/term pairs) or null which is used to indicate a failure to
	 * unify.
	 * 
	 * @param x
	 *            a variable, constant, list, or compound
	 * @param y
	 *            a variable, constant, list, or compound
	 * @param theta
	 *            the substitution built up so far
	 * 
	 * @return a Map<Variable, Term> representing the substitution (i.e. a set
	 *         of variable/term pairs) or null which is used to indicate a
	 *         failure to unify.
	 */
	// else if LIST?(x) and LIST?(y) then
	// return UNIFY(x.REST, y.REST, UNIFY(x.FIRST, y.FIRST, theta))
	public Map<Variable, Term> unify(List<? extends FOLNode> x,
			List<? extends FOLNode> y, Map<Variable, Term> theta) {
		// substitutions are = null
		if (theta == null) {
			return null;
		// if the size of the x list != to size of the  list
		} else if (x.size() != y.size()) {
			return null;
		// if the size of both is =0 
		} else if (x.size() == 0 && y.size() == 0) {
			return theta;
		// if the size of both is = 1
		} else if (x.size() == 1 && y.size() == 1) {
			return unify(x.get(0), y.get(0), theta);
		} else {
			// just unify on the tail of the list and unify on the 
			//head of the list getting a new list of substitutions
			return unify(x.subList(1, x.size()), y.subList(1, y.size()),
					unify(x.get(0), y.get(0), theta));
		}
	}

	//
	// PROTECTED METHODS
	//


	protected boolean occurCheck(Map<Variable, Term> theta, Variable var,
			FOLNode x) {
		// if var equal x)
		if (var.equals(x)) {
			return true;
			// if x is in theta keys
		} else if (theta.containsKey(x)) {
			// check if var occurs in the substitution of x
			return occurCheck(theta, var, theta.get(x));
			// if x is function
		} else if (x instanceof Function) {
			// check on every argument of the function if var occur
			Function fx = (Function) x;
			for (Term fxt : fx.getArgs()) {
				if (occurCheck(theta, var, fxt)) {
					return true;
				}
			}
		}
		return false;
	}

	//
	// PRIVATE METHODS
	//

	/**
	 * <code>
	 * function UNIFY-VAR(var, x, theta) returns a substitution
	 *   inputs: var, a variable
	 *       x, any expression
	 *       theta, the substitution built up so far
	 * </code>
	 */
	private Map<Variable, Term> unifyVar(Variable var, FOLNode x,
			Map<Variable, Term> theta) {
		// an instance of term
		if (!Term.class.isInstance(x)) {
			return null;
		} else if (theta.keySet().contains(var)) {
			// if {var/val} E theta then return UNIFY(val, x, theta)
			return unify(theta.get(var), x, theta);
		} else if (theta.keySet().contains(x)) {
			// else if {x/val} E theta then return UNIFY(var, val, theta)
			return unify(var, theta.get(x), theta);
		} else if (occurCheck(theta, var, x)) {
			// else if OCCUR-CHECK?(var, x) then return failure
			return null;
		} else {
			// else return add {var/x} to theta
			cascadeSubstitution(theta, var, (Term) x);
			return theta;
		}
	}

	// unify the symbolic name of a FOLNode
	private Map<Variable, Term> unifyOps(String x, String y,
			Map<Variable, Term> theta) {
		// if theta is not null
		if (theta == null) {
			return null;
			// then if x = y return theta
		} else if (x.equals(y)) {
			return theta;
		} else {// else return null
			return null;
		}
	}

	// extracting the args of a FOLNode (Look at the interface of FOLNode and the terms and sentences)
	private List<? extends FOLNode> args(FOLNode x) {
		return x.getArgs();
	}

	// extracting the symbolic name of the FOLNode to be unified in unifyOps (Look at the interface of FOLNode and the terms and sentences)
	private String op(FOLNode x) {
		return x.getSymbolicName();
	}

	// check if a FOLNode compound or not (Look at the interface of FOLNode and the terms and sentences)
	private boolean isCompound(FOLNode x) {
		return x.isCompound();
	}

	// this method is responsible update the theta and add the new substitution
	private Map<Variable, Term> cascadeSubstitution(Map<Variable, Term> theta,
			Variable var, Term x) {
		if(tracer)
		System.out.println("Checking in theta for : "+ var +" and "+ x);
		// add a substitution (a unification for a variable)
		theta.put(var, x);
		String trace =  theta+"";
		if(tracer)
		System.out.println("Added in theta: "+trace);
		// loop on all variables to update the substitution of the variable
		for (Variable v : theta.keySet()) {
			theta.put(v, substVisitor.subst(theta, theta.get(v)));
		}
		if(!trace.equals(theta.toString())){
			trace = theta +"";
			if(tracer)
			System.out.println("Updated in theta: "+trace);
		}
		// Ensure Function Terms are correctly updates by passing over them
		// and apply the substitution on the functions too.
		for (Variable v : theta.keySet()) {
			Term t = theta.get(v);
			if (t instanceof Function) {
				theta.put(v, substVisitor.subst(theta, t));
			}
		}
		if(!trace.equals(theta.toString())){
			trace =  theta +"";
			if(tracer)
			System.out.println("Updated in theta: "+trace);
		}
		return theta;
	}
	
	

	


}