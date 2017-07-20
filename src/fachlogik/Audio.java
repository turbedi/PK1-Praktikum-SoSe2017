package fachlogik;
import java.io.OutputStream;
import java.io.PrintStream;

public class Audio extends Medium {

	private static final long serialVersionUID = 1L;
	private String interpret;
	private int dauer;
	
	public Audio(String titel, int jahr, String interpret, int dauer) {
		super(titel, jahr);
		this.interpret = interpret;
		this.dauer = dauer;
	}
	
	public String getInterpret() {
		return interpret;
	}
	
	public void setInterpret(String interpret) {
		this.interpret = interpret;
	}
	
	public int getDauer() {
		return dauer;
	}
	
	public void setDauer(int dauer) {
		this.dauer = dauer;
	}
	
	public String toString() {
		return "ID: " +  getID() + getTitle() + " von " + getInterpret() + "aus " + getJahr() + " Spieldauer: "+ getDauer() + " sek.\n";
	}
	
	public void druckeDaten(OutputStream stream) {
		PrintStream ausgabe = new PrintStream(stream);
		//System.out.println("ID: " + getID() + " " + getTitle() + " von " + interpret + " aus " + getJahr() + " Spieldauer: " + dauer + "sek.");
		ausgabe.printf("ID: %d %s von %s aus %d Spieldauer: %s sek.\n", getID(), getTitle(), getDauer(), getJahr(), getDauer());
		ausgabe.flush();
	}
}