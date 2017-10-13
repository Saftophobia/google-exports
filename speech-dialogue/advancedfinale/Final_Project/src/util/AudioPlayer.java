package util;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class AudioPlayer {

	private final int BUFFER_SIZE = 128000;
	private File soundFile;
	private AudioInputStream audioStream;
	private AudioFormat audioFormat;
	private SourceDataLine sourceLine;

	/**
	 * @param filename
	 *            the name of the file that is going to be played sequentially
	 */
	private void playSoundSequentially(String filename) {

		String strFilename = filename;

		try {
			soundFile = new File(strFilename);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		try {
			audioStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		audioFormat = audioStream.getFormat();

		DataLine.Info info = new DataLine.Info(SourceDataLine.class,
				audioFormat);
		try {
			sourceLine = (SourceDataLine) AudioSystem.getLine(info);
			sourceLine.open(audioFormat);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		sourceLine.start();

		int nBytesRead = 0;
		byte[] abData = new byte[BUFFER_SIZE];
		while (nBytesRead != -1) {
			try {
				nBytesRead = audioStream.read(abData, 0, abData.length);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (nBytesRead >= 0) {
				@SuppressWarnings("unused")
				int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
			}
		}

		sourceLine.drain();
		sourceLine.close();
	}

	
	/**
	 * @param filename
	 *            the name of the file that is going to be played Parallel
	 */
	private void playSoundParallel(final String filename) {

		Thread playSound = new Thread() {
			public void run() {
				String strFilename = filename;

				try {
					soundFile = new File(strFilename);
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}

				try {
					audioStream = AudioSystem.getAudioInputStream(soundFile);
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}

				audioFormat = audioStream.getFormat();

				DataLine.Info info = new DataLine.Info(SourceDataLine.class,
						audioFormat);
				try {
					sourceLine = (SourceDataLine) AudioSystem.getLine(info);
					sourceLine.open(audioFormat);
				} catch (LineUnavailableException e) {
					e.printStackTrace();
					System.exit(1);
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}

				sourceLine.start();

				int nBytesRead = 0;
				byte[] abData = new byte[BUFFER_SIZE];
				while (nBytesRead != -1) {
					try {
						nBytesRead = audioStream.read(abData, 0, abData.length);
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (nBytesRead >= 0) {
						@SuppressWarnings("unused")
						int nBytesWritten = sourceLine.write(abData, 0,
								nBytesRead);
					}
				}

				
				sourceLine.close();
			}
		};
		playSound.start();
	}

	public void playSound(String fileName, int type) {
		if (sourceLine!=null && sourceLine.isRunning()) {
			sourceLine.stop();
			sourceLine.flush();
		}
		if(type !=3){
		if (type == 0) {
			playSoundSequentially(fileName);
		} else {
			playSoundParallel(fileName);
		}
		}
	}

	public static void main(String[] args) {
		System.out.println("start");
		AudioPlayer player = new AudioPlayer();
		player.playSound("sounds/ALARM.WAV", 1);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		player.playSound("sounds/ALARM.WAV", 0);
		System.out.println("end");
	}
}