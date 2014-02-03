package vxmlModel;

import util.AudioPlayer;
import util.StateVariables;

public class Audio extends DataHolder{
	
	String expr;
	String maxAge;
	String src;
	String maxStale;
	String fetchHint;
	String fetchTimeOut;
	

	public Audio(String expr, String maxAge, String src,
			String maxStale, String fetchHint, String fetchTimeOut) {
		super();
		this.expr = expr;
		this.maxAge = maxAge;
		this.src = src;
		this.maxStale = maxStale;
		this.fetchHint = fetchHint;
		this.fetchTimeOut = fetchTimeOut;
	}
	
	
	public String getExpr() {
		return expr;
	}
	public String getMaxAge() {
		return maxAge;
	}
	public String getSrc() {
		return src;
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


	@Override
	public Object eval(StateVariables o) {
		o.audioPlayer.playSound(src, 0);
		return null;
	}
	
	
	
	
}
