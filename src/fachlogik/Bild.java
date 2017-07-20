package fachlogik;
import java.io.OutputStream;

import java.io.PrintStream;

public class Bild extends Medium {

	private static final long serialVersionUID = 1L;
	private String ort;
	
	public Bild(String titel, int jahr, String ort) {
		super(titel, jahr);
		this.ort = ort;
	}
	
	public void druckeDaten(OutputStream stream) {
		PrintStream ausgabe = new PrintStream(stream);
		ausgabe.printf("ID: %d Titel: %s aufgenommen im Jahr %d in %s\n", getID(), getTitle(), getJahr(), ort);
		ausgabe.flush();
	}
	
	public void setOrt(String ort) {
		this.ort = ort;
	}
	
	public String getOrt() {
		return ort;
	}
	
	public String toString() {
		return "ID: " +  getID() + " Titel: " + getTitle() + " aufgenommen im Jahr" +  getJahr() + " in " + getOrt() + "\n";
	}
	
}
