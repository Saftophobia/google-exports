package vxmlModel;

public class Elseif extends DataHolder{
	
	String cond;
	
	public Elseif(String cond){
		this.cond  = cond;
	}

	public String getCond(){
		return cond;
	}
	
}
