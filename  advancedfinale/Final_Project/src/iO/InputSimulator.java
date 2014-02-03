package iO;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import util.SphinxListener;

public class InputSimulator {
	Scanner s = new Scanner(new InputStreamReader(System.in));
	ArrayList<SphinxListener> listeners = new ArrayList<SphinxListener>();

	public InputSimulator() {

	}
	
	public void AddListener(SphinxListener s)
	{
		listeners.add(s);
	}
	
	public void OpenMic()
	{
		String result = s.nextLine();
		for(SphinxListener l : listeners)
		{
			l.hear(result);
		}
	}
}
