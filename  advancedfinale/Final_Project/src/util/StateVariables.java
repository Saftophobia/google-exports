package util;

import iO.InputSimulator;

import java.util.ArrayList;
import java.util.HashMap;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import vxmlModel.Form;
import vxmlModel.Grammar;
import vxmlModel.Prompt;
import vxmlModel.Vxml;

public class StateVariables {
	public ScriptEngineManager manager = new ScriptEngineManager();
	public ScriptEngine engine = manager.getEngineByName("javascript");
	public HashMap<String, String> VariableHashMap = new HashMap<String, String>();
	public AudioPlayer audioPlayer = new AudioPlayer();
	public Prompt LastPrompt;
	public ArrayList<FreeTTSListener> Listerners = new ArrayList<FreeTTSListener>();
	public InputSimulator inputSim = new InputSimulator();
	public Vxml root;
	public Form LastForm;
	
	public StateVariables() {
	/*	try {
			this.engine.eval("  function price(size,toppings,crust,thickness) {    var res = 10;    if ('big' == size) res += 5;    if ('cheese' == toppings) res += 1;    if ('mushrooms' == toppings) res += 2;    if ('regular' == size) res += 2;    if ('tuna' == toppings) res += 7;    if ('small' == size) res += 1;    if ('stuffed' == crust) res += 12;   if ('normal' == crust) res += 10;    if ('pan' == thickness) res += 2;   if ('thick' == thickness) res += 5;   return res;  }");
			System.out.println("MINAMINAMINAMINA");
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	
	}
}
