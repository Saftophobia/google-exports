package vxmlModel;

public class Goto extends DataHolder {

	
	String expr;
	String exprItem;
	String fetchAudio;
	String fetchHint;
	String fetchTimeOut;
	String maxAge;
	String maxStale;
	String next;
	String nestItemString;
	
	
	public Goto(String expr, String exprItem, String fetchAudio,
			String fetchHint, String fetchTimeOut, String maxAge,
			String maxStale, String next, String nestItemString) {
		super();
		this.expr = expr;
		this.exprItem = exprItem;
		this.fetchAudio = fetchAudio;
		this.fetchHint = fetchHint;
		this.fetchTimeOut = fetchTimeOut;
		this.maxAge = maxAge;
		this.maxStale = maxStale;
		this.next = next;
		this.nestItemString = nestItemString;
	}


	public String getExpr() {
		return expr;
	}


	public String getExprItem() {
		return exprItem;
	}


	public String getFetchAudio() {
		return fetchAudio;
	}


	public String getFetchHint() {
		return fetchHint;
	}


	public String getFetchTimeOut() {
		return fetchTimeOut;
	}


	public String getMaxAge() {
		return maxAge;
	}


	public String getMaxStale() {
		return maxStale;
	}


	public String getNext() {
		return next;
	}


	public String getNestItemString() {
		return nestItemString;
	}
	
	
}
