package Engine.Utils;

public class Logger {
	private static boolean debugMode = true;

	public static void log(String tag, String message) {
		if (debugMode) {
			System.out.println("[" + tag + "] " + message);
		}
	}

	public static void setDebugMode(boolean enabled) {
		debugMode = enabled;
	}
}
