package vxmlModel;
public class Script extends DataHolder {

	String charSet;
	String maxAge;
	String src;
	String srcExpr;
	String maxStale;
	String fetchHint;
	String fetchTimeOut;
	String ScriptContent;

	public Script(String charSet, String maxAge, String src, String srcExpr,
			String maxStale, String fetchHint, String fetchTimeOut,String ScriptContent) {
		super();
		this.charSet = charSet;
		this.maxAge = maxAge;
		this.src = src;
		this.srcExpr = srcExpr;
		this.maxStale = maxStale;
		this.fetchHint = fetchHint;
		this.fetchTimeOut = fetchTimeOut;
		this.ScriptContent = ScriptContent;
	}

	public String getCharSet() {
		return charSet;
	}

	public String getMaxAge() {
		return maxAge;
	}

	public String getSrc() {
		return src;
	}

	public String getSrcExpr() {
		return srcExpr;
	}

	public String getMaxStale() {
		return maxStale;
	}

	public String getFetchHint() {
		return fetchHint;
	}

	public String getFetchTimeOut() {
		return fetchTimeOut;
	}

}
