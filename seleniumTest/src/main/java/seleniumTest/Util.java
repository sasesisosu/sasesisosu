package seleniumTest;

public class Util {

	public static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
