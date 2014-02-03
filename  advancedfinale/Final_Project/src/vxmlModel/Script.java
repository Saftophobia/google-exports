package vxmlModel;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import util.StateVariables;

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

	public String readFile(String pathname) throws IOException {

		File file = new File(pathname);
		StringBuilder fileContents = new StringBuilder((int) file.length());
		Scanner scanner = new Scanner(file);
		String lineSeparator = System.getProperty("line.separator");

		try {
			while (scanner.hasNextLine()) {
				fileContents.append(scanner.nextLine() + lineSeparator);
			}
			return fileContents.toString();
		} finally {
			scanner.close();
		}
	}
	
	@Override
	public Object eval(StateVariables o) {
		//ScriptEngine engine = (ScriptEngine) o;
		if(src != null){
			//load file TODO
			try{
			o.engine.eval(this.readFile(src));
			}catch(Exception wtv)
			{
				
			}
		}else{
		try {
			System.out.println(ScriptContent);
			o.engine.eval(ScriptContent);
		} catch (ScriptException e) {
			e.printStackTrace();
			//System.exit(0);
		}
		}
		return null;
	}

}
