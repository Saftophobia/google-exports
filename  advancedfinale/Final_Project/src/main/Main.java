package main;

import java.io.File;

import textToSpeech.TextToSpeech;
import vxmlEngine.Engine;
import vxmlModel.VxmlParser;

public class Main {
	public static void main(String[] args) {

		VxmlParser parser = new VxmlParser(new File("src/test2.vxml"));
		Engine e = new Engine(parser);
		TextToSpeech tts = new TextToSpeech();
		e.AddfreeTTsListener(tts);
		e.eval();
		
	}
}
