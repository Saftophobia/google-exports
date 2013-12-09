package parser;

import java.util.HashSet;
import java.util.Set;


// This class is used for representing the domain of the FOL so that the parsing process
// can be done easily.
public class FOLDomain {
	// it consists of 3 sets (constants, function and predicates)
	//anything else is just a variable. the use of Set here was chosen 
	//to remove the duplicates symbols in each type of sets
	private Set<String> constants, functions, predicates;

	// This constructor of the class just initialize the 3 sets and taking 0 params
	public FOLDomain() {
		// initialize the constants set as HashSet
		this.constants = new HashSet<String>();
		// initialize the functions set as HashSet
		this.functions = new HashSet<String>();
		// initialize the predicates set as HashSet
		this.predicates = new HashSet<String>();
	}

	// This constructor is taking the 3 sets to assign them to the 3 variables (this one is used for coping)
	public FOLDomain(Set<String> constants, Set<String> functions,
			Set<String> predicates) {
		// assign the constants to the constants variable as HashSet
		this.constants = new HashSet<String>(constants);
		// assign the functions to the constants functions as HashSet
		this.functions = new HashSet<String>(functions);
		// assign the predicates to the constants predicates as HashSet
		this.predicates = new HashSet<String>(predicates);
	}
	
	// This constructor is used to copy a domain
	// it takes a domain then call the constructor above with the 3 sets to copy.
	public FOLDomain(FOLDomain toCopy) {
		// calling the constructor with 3 params
		this(toCopy.getConstants(), toCopy.getFunctions(), toCopy
				.getPredicates());
	}

	// this method is used to get the set of constants
	public Set<String> getConstants() {
		// return constants
		return constants;
	}
	
	// this method is used to get the set of functions
	public Set<String> getFunctions() {
		// return functions
		return functions;
	}
	
	// this method is used to get the set of predicates
	public Set<String> getPredicates() {
		// return predicates
		return predicates;
	}

	// this method is used to add a new constant to the set of constants
	public void addConstant(String constant) {
		constants.add(constant);
	}

	// this method is used to add a new function to the set of functions
	public void addFunction(String function) {
		functions.add(function);
	}

	// this method is used to add a new predicate to the set of predicates
	public void addPredicate(String predicate) {
		predicates.add(predicate);
	}


}