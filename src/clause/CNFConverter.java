package clause;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parser.FOLParser;
import sentence.ConnectedSentence;
import sentence.NotSentence;
import sentence.Predicate;
import sentence.QuantifiedSentence;
import sentence.Sentence;
import sentence.TermEquality;
import term.Constant;
import term.Function;
import term.Term;
import term.Variable;
import utility.FOLVisitor;
import utility.SubstVisitor;
import utility.SyncategorematicSymbols;
import abstracts.CNF;
import abstracts.Clause;

/**
 * Every sentence of first-order logic can be converted into an equivalent CNF
 * sentence.
 */
public class CNFConverter {

	private FOLParser parser = null; // initialize FOL parser
	private SubstVisitor substVisitor; // determine the behavior of the
										// variables when visited using the
										// input

	// constructor with parser setter
	public CNFConverter(FOLParser parser) {
		this.parser = parser;

		this.substVisitor = new SubstVisitor();
	}

	/**
	 * Returns the specified sentence as a list of clauses, where each clause is
	 * a disjunction of literals.
	 * 
	 * @param aSentence
	 *            a sentence in first order logic (predicate calculus)
	 * 
	 * @return the specified sentence as a list of clauses, where each clause is
	 *         a disjunction of literals.
	 */
	public CNF convertToCNF(Sentence aSentence, boolean trace) {
		// double Implications Out:
		System.out.println("ORIGINAL: " + aSentence.toString());
		Sentence implicationsOut = (Sentence) aSentence.accept(
				new ImplicationsOut(), null);
		if (trace)
			System.out.println("IMPLICATIONS double : "
					+ implicationsOut.toString());

		// single implications
		Sentence implicationsOut2 = (Sentence) implicationsOut.accept(
				new ImplicationsOut2(), null);
		if (trace)
			System.out.println("IMPLICATIONS single : "
					+ implicationsOut2.toString());

		// Negations In:
		Sentence negationsIn = (Sentence) implicationsOut2.accept(
				new NegationsIn(), null);
		if (trace)
			System.out.println("NEGATION: " + negationsIn.toString());

		// Standardize variables:
		// For sentences like:
		// (FORALL x P(x)) V (EXISTS x Q(x)),
		// which use the same variable name twice, change the name of one of the
		// variables.
		Sentence saSyncategorematicSymbols = (Sentence) negationsIn.accept(
				new StandardizeQuantiferVariables(substVisitor),
				new LinkedHashSet<Variable>());
		if (trace)
			System.out.println("STANDARDIZED: " + saSyncategorematicSymbols);

		// Remove explicit quantifiers, by skolemizing existentials
		// and dropping universals:
		// Existentials Out
		// skolemize + drop the there exist
		Sentence andsAndOrs = (Sentence) saSyncategorematicSymbols.accept(
				new RemoveSyncategorematicSymbols(parser),
				new LinkedHashSet<Variable>());
		if (trace)
			System.out.println("SKOLEMIZED there exist: "
					+ andsAndOrs.toString());

		// drop the forall
		Sentence andsAndOrs2 = (Sentence) andsAndOrs.accept(
				new RemoveSyncategorematicSymbols2(parser),
				new LinkedHashSet<Variable>());
		if (trace)
			System.out.println("SKOLEMIZED for all: " + andsAndOrs2.toString());

		// Distribution
		// V over ^:
		Sentence orDistributedOverAnd = (Sentence) andsAndOrs2.accept(
				new DistributeOrOverAnd(), null);
		if (trace)
			System.out.println("DISTRIBUTED: "
					+ orDistributedOverAnd.toString());

		String[] flattened = orDistributedOverAnd.toString().split("AND");

		for (int i = 0; i < flattened.length; i++) {
			if (i == 0) {
				if (trace)
					System.out.println("FLATTEN:\t" + flattened[0]);
			} else {
				if (trace)
					System.out.println("\tAND " + flattened[i]);
			}

		}

		// Operators Out
		return (new CNFConstructor()).construct(orDistributedOverAnd);
	}
}

// implications class
class ImplicationsOut implements FOLVisitor {
	public ImplicationsOut() {

	}

	// predicate parser for implicator
	public Object visitPredicate(Predicate p, Object arg) {
		return p;
	}

	// term parser for implicator
	public Object visitTermEquality(TermEquality equality, Object arg) {
		return equality;
	}

	// variable parser for implicator
	public Object visitVariable(Variable variable, Object arg) {
		return variable;
	}

	// constant parser for implicator
	public Object visitConstant(Constant constant, Object arg) {
		return constant;
	}

	// function parser for implicator
	public Object visitFunction(Function function, Object arg) {
		return function;
	}

	// return negated sentence for implicator
	public Object visitNotSentence(NotSentence notSentence, Object arg) {
		Sentence negated = notSentence.getNegated();

		return new NotSentence((Sentence) negated.accept(this, arg));
	}

	// conntectedSentences parser, while eliminating the 2 implications
	public Object visitConnectedSentence(ConnectedSentence sentence, Object arg) {
		Sentence alpha = (Sentence) sentence.getFirst().accept(this, arg);
		Sentence beta = (Sentence) sentence.getSecond().accept(this, arg);

		// Eliminate <=>, bi-conditional elimination,
		// replace (alpha <=> beta) with (~alpha V beta) ^ (alpha V ~beta).
		if (SyncategorematicSymbols.isBICOND(sentence.getConnector())) {
			// Sentence first = new
			// ConnectedSentence(SyncategorematicSymbols.OR,
			// new NotSentence(alpha), beta);

			Sentence first = new ConnectedSentence(
					SyncategorematicSymbols.IMPLIES, alpha, beta);

			// Sentence second = new
			// ConnectedSentence(SyncategorematicSymbols.OR,
			// alpha, new NotSentence(beta));
			Sentence second = new ConnectedSentence(
					SyncategorematicSymbols.IMPLIES, beta, alpha);

			return new ConnectedSentence(SyncategorematicSymbols.AND, first,
					second);
		}

		return new ConnectedSentence(sentence.getConnector(), alpha, beta);
	}

	// quantified sentence parser
	public Object visitQuantifiedSentence(QuantifiedSentence sentence,
			Object arg) {

		return new QuantifiedSentence(sentence.getQuantifier(),
				sentence.getVariables(), (Sentence) sentence.getQuantified()
						.accept(this, arg));
	}
}

class ImplicationsOut2 implements FOLVisitor {
	public ImplicationsOut2() {

	}

	// predicate parser for implicator
	public Object visitPredicate(Predicate p, Object arg) {
		return p;
	}

	// term parser for implicator
	public Object visitTermEquality(TermEquality equality, Object arg) {
		return equality;
	}

	// variable parser for implicator
	public Object visitVariable(Variable variable, Object arg) {
		return variable;
	}

	// constant parser for implicator
	public Object visitConstant(Constant constant, Object arg) {
		return constant;
	}

	// function parser for implicator
	public Object visitFunction(Function function, Object arg) {
		return function;
	}

	// return negated sentence for implicator
	public Object visitNotSentence(NotSentence notSentence, Object arg) {
		Sentence negated = notSentence.getNegated();

		return new NotSentence((Sentence) negated.accept(this, arg));
	}

	// conntectedSentences parser, while eliminating the 2 implications
	public Object visitConnectedSentence(ConnectedSentence sentence, Object arg) {
		Sentence alpha = (Sentence) sentence.getFirst().accept(this, arg);
		Sentence beta = (Sentence) sentence.getSecond().accept(this, arg);

		// Eliminate =>, implication elimination,
		// replacing (alpha => beta) with (~alpha V beta)
		if (SyncategorematicSymbols.isIMPLIES(sentence.getConnector())) {
			return new ConnectedSentence(SyncategorematicSymbols.OR,
					new NotSentence(alpha), beta);
		}

		return new ConnectedSentence(sentence.getConnector(), alpha, beta);
	}

	// quantified sentence parser
	public Object visitQuantifiedSentence(QuantifiedSentence sentence,
			Object arg) {

		return new QuantifiedSentence(sentence.getQuantifier(),
				sentence.getVariables(), (Sentence) sentence.getQuantified()
						.accept(this, arg));
	}
}

// negations class
class NegationsIn implements FOLVisitor {
	public NegationsIn() {
		// empty constructor
	}

	// predicate parser for negations
	public Object visitPredicate(Predicate p, Object arg) {
		return p;
	}

	// term parser for negations
	public Object visitTermEquality(TermEquality equality, Object arg) {
		return equality;
	}

	// variable parser for negations
	public Object visitVariable(Variable variable, Object arg) {
		return variable;
	}

	// Constant parser for negations
	public Object visitConstant(Constant constant, Object arg) {
		return constant;
	}

	// function parser for negations
	public Object visitFunction(Function function, Object arg) {
		return function;
	}

	// process negations
	public Object visitNotSentence(NotSentence notSentence, Object arg) {
		Sentence negated = notSentence.getNegated();
		// CNF requires NOT (~) to appear only in literals, so we 'move ~
		// inwards' by repeated application of the following equivalences:
		// ~(~alpha) equivalent to alpha (double negation elimination)
		if (negated instanceof NotSentence) {
			return ((NotSentence) negated).getNegated().accept(this, arg);
		}

		if (negated instanceof ConnectedSentence) {
			// temp variable
			ConnectedSentence negConnected = (ConnectedSentence) negated;
			// get the two connections
			Sentence alpha = negConnected.getFirst();
			Sentence beta = negConnected.getSecond();
			// ~(alpha ^ beta) equivalent to (~alpha V ~beta) as in De Morgan's
			// Rules
			if (SyncategorematicSymbols.isAND(negConnected.getConnector())) {
				// I need to ensure the ~ are moved in deeper
				Sentence notAlpha = (Sentence) (new NotSentence(alpha)).accept(
						this, arg);
				Sentence notBeta = (Sentence) (new NotSentence(beta)).accept(
						this, arg);
				return new ConnectedSentence(SyncategorematicSymbols.OR,
						notAlpha, notBeta);
			}

			// ~(alpha V beta) equivalent to (~alpha ^ ~beta) (De Morgan)
			if (SyncategorematicSymbols.isOR(negConnected.getConnector())) {
				// I need to ensure the ~s are moved in deeper
				Sentence notAlpha = (Sentence) (new NotSentence(alpha)).accept(
						this, arg);
				Sentence notBeta = (Sentence) (new NotSentence(beta)).accept(
						this, arg);
				return new ConnectedSentence(SyncategorematicSymbols.AND,
						notAlpha, notBeta);
			}
		}

		// rules for negated quantifiers:
		if (negated instanceof QuantifiedSentence) {
			QuantifiedSentence negQuantified = (QuantifiedSentence) negated;
			// I need to ensure the ~ is moved in deeper as above
			Sentence notP = (Sentence) (new NotSentence(
					negQuantified.getQuantified())).accept(this, arg);

			// ~FORALL x p becomes EXISTS x ~p
			if (SyncategorematicSymbols.isFORALL(negQuantified.getQuantifier())) {
				return new QuantifiedSentence(SyncategorematicSymbols.EXISTS,
						negQuantified.getVariables(), notP);
			}

			// ~EXISTS x p becomes FORALL x ~p
			if (SyncategorematicSymbols.isEXISTS(negQuantified.getQuantifier())) {
				return new QuantifiedSentence(SyncategorematicSymbols.FORALL,
						negQuantified.getVariables(), notP);
			}
		}

		return new NotSentence((Sentence) negated.accept(this, arg));
	}

	// connectedsentences parser, just returns the notsentence parser of the two
	// parts
	public Object visitConnectedSentence(ConnectedSentence sentence, Object arg) {
		return new ConnectedSentence(sentence.getConnector(),
				(Sentence) sentence.getFirst().accept(this, arg),
				(Sentence) sentence.getSecond().accept(this, arg));
	}

	// quantifiedsentence parser, returns the quantified sentence with the new
	// set of variables, the quantifier string and sentence
	public Object visitQuantifiedSentence(QuantifiedSentence sentence,
			Object arg) {

		return new QuantifiedSentence(sentence.getQuantifier(),
				sentence.getVariables(), (Sentence) sentence.getQuantified()
						.accept(this, arg));
	}
}

// standardizeclass
class StandardizeQuantiferVariables implements FOLVisitor {
	// Just use a localized indexical here.
	private StandardizeApartIndexical quantifiedIndexical = new StandardizeApartIndexical() {
		private int index = 0;

		// getprefix
		public String getPrefix() {
			return "q";
		}

		// getnextindex of the indexical
		public int getNextIndex() {
			return index++;
		}
	};
	// the visitor(reader)
	private SubstVisitor substVisitor = null;

	// constructor with visitor setter
	public StandardizeQuantiferVariables(SubstVisitor substVisitor) {
		this.substVisitor = substVisitor;
	}

	// predicate reader for standardize
	public Object visitPredicate(Predicate p, Object arg) {
		return p;
	}

	// term reader for standardize
	public Object visitTermEquality(TermEquality equality, Object arg) {
		return equality;
	}

	// variable reader for standardize
	public Object visitVariable(Variable variable, Object arg) {
		return variable;
	}

	// constant reader for standardize
	public Object visitConstant(Constant constant, Object arg) {
		return constant;
	}

	// function reader for standardize
	public Object visitFunction(Function function, Object arg) {
		return function;
	}

	// notsentence reader for standardize
	public Object visitNotSentence(NotSentence sentence, Object arg) {
		return new NotSentence((Sentence) sentence.getNegated().accept(this,
				arg));
	}

	// connectedsentence reader, connects the two parts of the sentence with the
	// connector
	public Object visitConnectedSentence(ConnectedSentence sentence, Object arg) {
		return new ConnectedSentence(sentence.getConnector(),
				(Sentence) sentence.getFirst().accept(this, arg),
				(Sentence) sentence.getSecond().accept(this, arg));
	}

	@SuppressWarnings("unchecked")
	public Object visitQuantifiedSentence(QuantifiedSentence sentence,
			Object arg) {
		// progress tracker
		Set<Variable> seenSoFar = (Set<Variable>) arg;

		// Keep track of what I have to subst locally and
		// what my renamed variables will be.
		Map<Variable, Term> localSubst = new LinkedHashMap<Variable, Term>();
		List<Variable> replVariables = new ArrayList<Variable>();
		for (Variable v : sentence.getVariables()) {
			// If local variable has be renamed already
			// then I need to come up with own name
			if (seenSoFar.contains(v)) {
				Variable sV = new Variable(quantifiedIndexical.getPrefix()
						+ quantifiedIndexical.getNextIndex());
				localSubst.put(v, sV);
				// Replacement variables should contain new name for variable
				replVariables.add(sV);
			} else {
				// Not already replaced, this name is good
				replVariables.add(v);
			}
		}

		// Apply the local subst reader
		Sentence subst = substVisitor.subst(localSubst,
				sentence.getQuantified());

		// Ensure all my existing and replaced variable
		// names are tracked
		seenSoFar.addAll(replVariables);
		// build the sentence
		Sentence sQuantified = (Sentence) subst.accept(this, arg);
		// return the new sentence with the old one,new variables and quantifier
		return new QuantifiedSentence(sentence.getQuantifier(), replVariables,
				sQuantified);
	}
}

// syncategorematics remover
class RemoveSyncategorematicSymbols implements FOLVisitor {
	// parser and reader
	private FOLParser parser = null;
	private SubstVisitor substVisitor = null;

	// constructor
	public RemoveSyncategorematicSymbols(FOLParser parser) {
		this.parser = parser;

		substVisitor = new SubstVisitor();
	}

	// predicate reader for Syncategorematics remover
	public Object visitPredicate(Predicate p, Object arg) {
		return p;
	}

	// term reader for Syncategorematics remover
	public Object visitTermEquality(TermEquality equality, Object arg) {
		return equality;
	}

	// variable reader for Syncategorematics remover
	public Object visitVariable(Variable variable, Object arg) {
		return variable;
	}

	// constant reader for Syncategorematics remover
	public Object visitConstant(Constant constant, Object arg) {
		return constant;
	}

	// function reader for Syncategorematics remover
	public Object visitFunction(Function function, Object arg) {
		return function;
	}

	// not sentence reader for Syncategorematics remover, return negated
	// sentence
	public Object visitNotSentence(NotSentence sentence, Object arg) {
		return new NotSentence((Sentence) sentence.getNegated().accept(this,
				arg));
	}

	// connectedsentence reader for Syncategorematics remover, connects two
	// parts of the sentence with the connector
	public Object visitConnectedSentence(ConnectedSentence sentence, Object arg) {
		return new ConnectedSentence(sentence.getConnector(),
				(Sentence) sentence.getFirst().accept(this, arg),
				(Sentence) sentence.getSecond().accept(this, arg));
	}

	// Qsentence reader for Syncategorematics remover
	@SuppressWarnings("unchecked")
	public Object visitQuantifiedSentence(QuantifiedSentence sentence,
			Object arg) {
		// get Q sentence
		Sentence quantified = sentence.getQuantified();
		Set<Variable> universalScope = (Set<Variable>) arg;

		// Skolemize: Skolemization is the process of removing existential
		// quantifiers by elimination. This is done by introducing Skolem
		// functions. The general rule is that the arguments of the Skolem
		// function are all the universally quantified variables in whose
		// scope the existential quantifier appears.
		if (SyncategorematicSymbols.isEXISTS(sentence.getQuantifier())) {
			Map<Variable, Term> skolemSubst = new LinkedHashMap<Variable, Term>();
			for (Variable eVar : sentence.getVariables()) {
				if (universalScope.size() > 0) {
					// Replace with a Skolem Function
					String skolemFunctionName = parser.getFOLDomain()
							.addSkolemFunction();
					skolemSubst.put(eVar, new Function(skolemFunctionName,
							new ArrayList<Term>(universalScope)));
				} else {
					// Replace with a Skolem Constant
					String skolemConstantName = parser.getFOLDomain()
							.addSkolemConstant();
					skolemSubst.put(eVar, new Constant(skolemConstantName));
				}
			}
			// return the skolemized sentence
			Sentence skolemized = substVisitor.subst(skolemSubst, quantified);
			return skolemized.accept(this, arg);
		}

		// System.out.println("TESTER 3 : " + sentence );
		// Drop universal quantifiers.
		if (SyncategorematicSymbols.isFORALL(sentence.getQuantifier())) {
			// Add to the universal scope so that
			// existential skolemization may be done correctly
			universalScope.addAll(sentence.getVariables());

			Sentence droppedUniversal = (Sentence) quantified.accept(this, arg);

			// Ensure my scope is removed before moving back up
			// the call stack when returning
			universalScope.removeAll(sentence.getVariables());
			return new QuantifiedSentence(SyncategorematicSymbols.FORALL,
					sentence.getVariables(), droppedUniversal);
		}

		// Should not reach here as have already
		// handled the two quantifiers.
		throw new IllegalStateException("Unhandled Quantifier:"
				+ sentence.getQuantifier());
	}
}

class RemoveSyncategorematicSymbols2 implements FOLVisitor {

	// constructor
	public RemoveSyncategorematicSymbols2(FOLParser parser) {

	}

	// predicate reader for Syncategorematics remover
	public Object visitPredicate(Predicate p, Object arg) {
		return p;
	}

	// term reader for Syncategorematics remover
	public Object visitTermEquality(TermEquality equality, Object arg) {
		return equality;
	}

	// variable reader for Syncategorematics remover
	public Object visitVariable(Variable variable, Object arg) {
		return variable;
	}

	// constant reader for Syncategorematics remover
	public Object visitConstant(Constant constant, Object arg) {
		return constant;
	}

	// function reader for Syncategorematics remover
	public Object visitFunction(Function function, Object arg) {
		return function;
	}

	// not sentence reader for Syncategorematics remover, return negated
	// sentence
	public Object visitNotSentence(NotSentence sentence, Object arg) {
		return new NotSentence((Sentence) sentence.getNegated().accept(this,
				arg));
	}

	// connectedsentence reader for Syncategorematics remover, connects two
	// parts of the sentence with the connector
	public Object visitConnectedSentence(ConnectedSentence sentence, Object arg) {
		return new ConnectedSentence(sentence.getConnector(),
				(Sentence) sentence.getFirst().accept(this, arg),
				(Sentence) sentence.getSecond().accept(this, arg));
	}

	// Qsentence reader for Syncategorematics remover
	@SuppressWarnings("unchecked")
	public Object visitQuantifiedSentence(QuantifiedSentence sentence,
			Object arg) {
		// get Q sentence
		Sentence quantified = sentence.getQuantified();
		Set<Variable> universalScope = (Set<Variable>) arg;

		// Drop universal quantifiers.
		if (SyncategorematicSymbols.isFORALL(sentence.getQuantifier())) {
			// Add to the universal scope so that
			// existential skolemization may be done correctly
			universalScope.addAll(sentence.getVariables());

			Sentence droppedUniversal = (Sentence) quantified.accept(this, arg);

			// Enusre my scope is removed before moving back up
			// the call stack when returning
			universalScope.removeAll(sentence.getVariables());

			return droppedUniversal;
		}

		// Should not reach here as have already
		// handled the two quantifiers.
		throw new IllegalStateException("Unhandled Quantifier:"
				+ sentence.getQuantifier());
	}
}

// V to ^
class DistributeOrOverAnd implements FOLVisitor {
	// constructor
	public DistributeOrOverAnd() {

	}

	// predicate reader for V to ^
	public Object visitPredicate(Predicate p, Object arg) {
		return p;
	}

	// term reader for V to ^
	public Object visitTermEquality(TermEquality equality, Object arg) {
		return equality;
	}

	// variable reader for V to ^
	public Object visitVariable(Variable variable, Object arg) {
		return variable;
	}

	// constant reader for V to ^
	public Object visitConstant(Constant constant, Object arg) {
		return constant;
	}

	// function reader for V to ^
	public Object visitFunction(Function function, Object arg) {
		return function;
	}

	// notsentence negator
	public Object visitNotSentence(NotSentence sentence, Object arg) {
		return new NotSentence((Sentence) sentence.getNegated().accept(this,
				arg));
	}

	// connecting two parts of the sentence with the connector in between. (
	// while editing V to ^
	public Object visitConnectedSentence(ConnectedSentence sentence, Object arg) {
		// Distribute V over ^:
		System.out.println("SENTENCE: " + sentence);
		
		// This will cause flattening out of nested ^s and Vs
		Sentence alpha = (Sentence) sentence.getFirst().accept(this, arg);
		Sentence beta = (Sentence) sentence.getSecond().accept(this, arg);

		
		// (alpha V (beta ^ gamma)) equivalent to
		// ((alpha V beta) ^ (alpha V gamma))
		if (SyncategorematicSymbols.isOR(sentence.getConnector())
				&& ConnectedSentence.class.isInstance(beta)) {
			ConnectedSentence betaAndGamma = (ConnectedSentence) beta;
			if (SyncategorematicSymbols.isAND(betaAndGamma.getConnector())) { // if
																				// example
																				// occurs
				beta = betaAndGamma.getFirst(); // beta is the first sentence
				Sentence gamma = betaAndGamma.getSecond(); // gamma is the 2nd
				// connect beta and gamma using the connector
				return new ConnectedSentence(SyncategorematicSymbols.AND,
						(Sentence) (new ConnectedSentence(
								SyncategorematicSymbols.OR, alpha, beta))
								.accept(this, arg),
						(Sentence) (new ConnectedSentence(
								SyncategorematicSymbols.OR, alpha, gamma))
								.accept(this, arg));
			}
		}

		// ((alpha ^ gamma) V beta) equivalent to
		// ((alpha V beta) ^ (gamma V beta))
		if (SyncategorematicSymbols.isOR(sentence.getConnector())
				&& ConnectedSentence.class.isInstance(alpha)) {
			ConnectedSentence alphaAndGamma = (ConnectedSentence) alpha; // if
																			// example
																			// occurs,
																			// initialize
																			// the
																			// connected
																			// sentence
			if (SyncategorematicSymbols.isAND(alphaAndGamma.getConnector())) {
				alpha = alphaAndGamma.getFirst(); // get first statement
				Sentence gamma = alphaAndGamma.getSecond(); // get 2nd statement
				// return the new CS with the new connector
				return new ConnectedSentence(SyncategorematicSymbols.AND,
						(Sentence) (new ConnectedSentence(
								SyncategorematicSymbols.OR, alpha, beta))
								.accept(this, arg),
						(Sentence) (new ConnectedSentence(
								SyncategorematicSymbols.OR, gamma, beta))
								.accept(this, arg));
			}
		}
		
		
		
//		// (alpha ^ (beta V gamma))
//				if (SyncategorematicSymbols.isAND(sentence.getConnector())
//						&& ConnectedSentence.class.isInstance(beta)) {
//					ConnectedSentence betaAndGamma = (ConnectedSentence) beta;
//					if (SyncategorematicSymbols.isOR(betaAndGamma.getConnector())) { // if
//																						// example
//																						// occurs
//						beta = betaAndGamma.getFirst(); // beta is the first sentence
//						Sentence gamma = betaAndGamma.getSecond(); // gamma is the 2nd
//						// connect beta and gamma using the connector
//						return new ConnectedSentence(SyncategorematicSymbols.OR,
//								(Sentence) (new ConnectedSentence(
//										SyncategorematicSymbols.AND, alpha, beta))
//										.accept(this, arg),
//								(Sentence) (new ConnectedSentence(
//										SyncategorematicSymbols.AND, alpha, gamma))
//										.accept(this, arg));
//					}
//				}
//
//				// ((alpha V beta) ^ (gamma V beta))
//				if (SyncategorematicSymbols.isAND(sentence.getConnector())
//						&& ConnectedSentence.class.isInstance(alpha)) {
//					ConnectedSentence alphaAndGamma = (ConnectedSentence) alpha; // if
//																					// example
//																					// occurs,
//																					// initialize
//																					// the
//																					// connected
//																					// sentence
//					if (SyncategorematicSymbols.isOR(alphaAndGamma.getConnector())) {
//						alpha = alphaAndGamma.getFirst(); // get first statement
//						Sentence gamma = alphaAndGamma.getSecond(); // get 2nd statement
//						// return the new CS with the new connector
//						return new ConnectedSentence(SyncategorematicSymbols.OR,
//								(Sentence) (new ConnectedSentence(
//										SyncategorematicSymbols.AND, alpha, beta))
//										.accept(this, arg),
//								(Sentence) (new ConnectedSentence(
//										SyncategorematicSymbols.AND, gamma, beta))
//										.accept(this, arg));
//					}
//				}
//
//				
				
		// return the connectedsentence if non of the special cases found
		return new ConnectedSentence(sentence.getConnector(), alpha, beta);
	}

	public Object visitQuantifiedSentence(QuantifiedSentence sentence,
			Object arg) {
		// This should not be called as should have already
		// removed all of the quantifiers earlier in the program.
		throw new IllegalStateException(
				"All quantified sentences should have already been removed.");
	}
}

// the CNFconstructor using cnf.java
class CNFConstructor implements FOLVisitor {
	// constructor
	public CNFConstructor() {

	}

	// construct using the latest changes ( the V distributed over ^ )
	public CNF construct(Sentence orDistributedOverAnd) {
		// clauses intilizer
		ArgData ad = new ArgData();

		orDistributedOverAnd.accept(this, ad);
		// return the cnf with clauses
		return new CNF(ad.clauses);
	}

	// predicate reader for cnfconstructor
	public Object visitPredicate(Predicate p, Object arg) {
		ArgData ad = (ArgData) arg;
		// add clauses according to +/-ve
		if (ad.negated) {
			ad.clauses.get(ad.clauses.size() - 1).addNegativeLiteral(p);
		} else {
			ad.clauses.get(ad.clauses.size() - 1).addPositiveLiteral(p);
		}
		return p;
	}

	// term reader for cnfconstructor
	public Object visitTermEquality(TermEquality equality, Object arg) {
		ArgData ad = (ArgData) arg;
		// add clauses according to +/-ve
		if (ad.negated) {
			ad.clauses.get(ad.clauses.size() - 1).addNegativeLiteral(equality);
		} else {
			ad.clauses.get(ad.clauses.size() - 1).addPositiveLiteral(equality);
		}
		return equality;
	}

	// variable reader for cnfconstructor
	public Object visitVariable(Variable variable, Object arg) {
		// This should not be called
		throw new IllegalStateException("visitVariable() should not be called.");
	}

	// constant reader for cnfconstructor
	public Object visitConstant(Constant constant, Object arg) {
		// This should not be called
		throw new IllegalStateException("visitConstant() should not be called.");
	}

	// function reader for cnfconstructor
	public Object visitFunction(Function function, Object arg) {
		// This should not be called as its already done
		throw new IllegalStateException("visitFunction() should not be called.");
	}

	// negated sentence reader for cnfconstructor
	public Object visitNotSentence(NotSentence sentence, Object arg) {
		ArgData ad = (ArgData) arg;
		// Indicate that the enclosed predicate is negated
		ad.negated = true;
		sentence.getNegated().accept(this, arg);
		ad.negated = false;

		return sentence;
	}

	// CS reader for cnfconstructor
	// binds two parts of the sentence with the connector
	public Object visitConnectedSentence(ConnectedSentence sentence, Object arg) {
		ArgData ad = (ArgData) arg;
		Sentence first = sentence.getFirst();
		Sentence second = sentence.getSecond();

		first.accept(this, arg);
		// if the CS has an AND
		if (SyncategorematicSymbols.isAND(sentence.getConnector())) {
			ad.clauses.add(new Clause());
		}
		second.accept(this, arg);

		return sentence;
	}

	// done earlier
	public Object visitQuantifiedSentence(QuantifiedSentence sentence,
			Object arg) {
		// This should not be called as should have already
		// removed all of the quantifiers earlier in the program.
		throw new IllegalStateException(
				"All quantified sentences should have already been removed.");
	}

	// clauses initializer placeholder
	class ArgData {
		public List<Clause> clauses = new ArrayList<Clause>();
		public boolean negated = false;

		public ArgData() {
			clauses.add(new Clause());
		}
	}
}