import java.io.OutputStream;
import java.io.PrintStream;

public class Audio extends Medium {
	private String interpret;
	private int dauer;
	
	public Audio(String titel, int jahr, String interpret, int dauer) {
		super(titel, jahr);
		this.interpret = interpret;
		this.dauer = dauer;
	}
	
	public void druckeDaten(OutputStream stream) {
		PrintStream ausgabe = new PrintStream(stream);
		//System.out.println("ID: " + getID() + " " + getTitle() + " von " + interpret + " aus " + getJahr() + " Spieldauer: " + dauer + "sek.");
		ausgabe.printf("ID: %d %s von %s aus %d Spieldauer: %s sek.\n", getID(), getTitle(), interpret, getJahr(), dauer);
		ausgabe.flush();
	}
}