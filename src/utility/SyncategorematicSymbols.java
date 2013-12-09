package utility;


public class SyncategorematicSymbols {
	public static final String AND = "AND";

	public static final String OR = "OR";

	public static final String NOT = "NOT";

	public static final String IMPLIES = "=>";

	public static final String BICOND = "<=>";
	
	public static final String FORALL = "FORALL";
	
	public static final String EXISTS = "EXISTS";

	public static boolean isAND(String connector) {
		return AND.equals(connector);
	}

	public static boolean isOR(String connector) {
		return OR.equals(connector);
	}

	public static boolean isNOT(String connector) {
		return NOT.equals(connector);
	}

	public static boolean isIMPLIES(String connector) {
		return IMPLIES.equals(connector);
	}

	public static boolean isBICOND(String connector) {
		return BICOND.equals(connector);
	}
	
	public static boolean isFORALL(String quantifier) {
		return FORALL.equals(quantifier);
	}

	public static boolean isEXISTS(String quantifier) {
		return EXISTS.equals(quantifier);
	}
}