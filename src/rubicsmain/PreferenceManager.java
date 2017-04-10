//package rubicsmain;
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.prefs.Preferences;
//
///*
// * Regelt den Zugriff auf anwendungsspezifische Werte
// */
//public class PreferenceManager {
//	private static PreferenceManager instance = null;
//	private final String CLOCKWISE_ROTATION_ANGLE = "CLOCKWISE_ROTATION_ANGLE";
//	private final String COUNTERCLOCKWISE_ROTATION_ANGLE = "COUNTERCLOCKWISE_ROTATION_ANGLE";
//	private Preferences prefs;
//	
//	private PreferenceManager() {
//		this.prefs = Preferences.systemRoot();
//	}
//	
//	public static PreferenceManager getInstance() {
//		if (instance == null) {
//			instance = new PreferenceManager();
//		}
//		return instance;
//	}
//	
//	private void write(String fileName, int value) {
//		 FileOutputStream fileStream = null;
//	     try {
//			fileStream = new FileOutputStream(new File(fileName));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	    DataOutputStream dataStream = new DataOutputStream(fileStream);
//        try {
//           dataStream.writeChars(String.valueOf(value));
//           fileStream.flush();
//        } catch (IOException e) {
//        	e.printStackTrace();
//        }
//	
//	    try {
//	        fileStream.close();
//	    } catch (IOException e) {
//	    	e.printStackTrace();
//	    }
//	}
//	
//	private void read(String fileName) {
//		FileInputStream fileStream = null;
//	     try {
//			fileStream = new FileInputStream(new File(fileName));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	    DataInputStream dataStream = new DataInputStream(fileStream);
//	    try {
//	    	int content;
//	    	while ()
//	    }
//	}
//	
//	public void setClockwiseRotationAngle(int angle) {
//		this.prefs.put(CLOCKWISE_ROTATION_ANGLE, String.valueOf(angle));
//	}
//	
//	public void setCounterclockwiseRotationAngle(int angle) {
//		this.prefs.put(COUNTERCLOCKWISE_ROTATION_ANGLE, String.valueOf(angle));
//	}
//	
//	public int getClockwiseRotationAngle() {
//		String defaultValue = "106";
//		String propertyValue = prefs.get(CLOCKWISE_ROTATION_ANGLE, defaultValue);
//		return Integer.parseInt(propertyValue);
//	}
//	
//	public int getCounterclockwiseRotationAngle() {
//		String defaultValue = "-106";
//		String propertyValue = prefs.get(COUNTERCLOCKWISE_ROTATION_ANGLE, defaultValue);
//		return Integer.parseInt(propertyValue);
//	}
//}
