import java.io.OutputStream;

public class Audio extends Medium {
	private String interpret;
	private int dauer;
	
	public Audio(String titel, int jahr, String interpret, int dauer) {
		super(titel, jahr);
		this.interpret = interpret;
		this.dauer = dauer;
	}
	
	public void druckeDaten(OutputStream stream) {
		stream = System.out.printf("ID: %d %s von %s aus %d Spieldauer: %s sek.", getID(), getTitle(), interpret, getJahr(), dauer);
		//System.out.println("ID: " + getID() + " " + getTitle() + " von " + interpret + " aus " + getJahr() + " Spieldauer: " + dauer + "sek.");
	}
}