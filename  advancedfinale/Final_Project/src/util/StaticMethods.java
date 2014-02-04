package util;

public class StaticMethods {

	public static String Normalize(String s)
	{
		
		if(s.startsWith("\'") && s.endsWith("\'")&& s.length() > 2){
			return Normalize(s.substring(1,s.length()-1));
		}
		if(s.endsWith(" ")&& s.length() > 2){
			return Normalize(s.substring(0,s.length()-1));
		}
		if(s.startsWith(" ")&& s.length() > 2){
			return Normalize(s.substring(1,s.length()));
		}
		return s;
	
	}
}
