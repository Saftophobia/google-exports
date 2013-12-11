package utility;


// this class is having the symbols and how they are represented
public class SyncategorematicSymbols {
	// AND
	public static final String AND = "AND";
	// OR
	public static final String OR = "OR";
	// NOT
	public static final String NOT = "NOT";
	// =>
	public static final String IMPLIES = "=>";
	// <=>
	public static final String BICOND = "<=>";
	// FORALL
	public static final String FORALL = "FORALL";
	// EXISTS
	public static final String EXISTS = "EXISTS";

	// this methods check if the connector is an AND
	public static boolean isAND(String connector) {
		return AND.equals(connector);
	}
	// this methods check if the connector is an OR
	public static boolean isOR(String connector) {
		return OR.equals(connector);
	}
	// this methods check if the connector is an NOT
	public static boolean isNOT(String connector) {
		return NOT.equals(connector);
	}
	// this methods check if the connector is an =>
	public static boolean isIMPLIES(String connector) {
		return IMPLIES.equals(connector);
	}
	// this methods check if the connector is an <=>
	public static boolean isBICOND(String connector) {
		return BICOND.equals(connector);
	}
	// this methods check if the quantifier is an FORALL
	public static boolean isFORALL(String quantifier) {
		return FORALL.equals(quantifier);
	}
	// this methods check if the quantifier is an EXISTS
	public static boolean isEXISTS(String quantifier) {
		return EXISTS.equals(quantifier);
	}
}