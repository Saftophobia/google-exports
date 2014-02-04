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

	public void AddListener(SphinxListener s) {
		listeners.add(s);
	}

	public String OpenMic() {
		s = new Scanner(new InputStreamReader(System.in));

		return s.nextLine();
	}
	public String OpenKeyboard()
	{
		s = new Scanner(new InputStreamReader(System.in));

		return s.nextLine();
	}

}
