package example.chat;


public class Log  {

	static synchronized public void log(String string) {
		System.out.println(string);
	}

	static public void log(String place, Exception e) {
		log(place+": "+e.getMessage());
		e.printStackTrace();
	}

}
