import java.io.OutputStream;
import java.io.PrintStream;

public class Bild extends Medium {
	private String ort;
	
	public Bild(String titel, int jahr, String ort) {
		super(titel, jahr);
		this.ort = ort;
	}
	
	public void druckeDaten(OutputStream stream) {
		//System.out.println("ID: " + getID() + " " + getTitle() + " aufgenommen im Jahr " + getJahr() + " in " + ort);
		PrintStream ausgabe = new PrintStream(stream);
		ausgabe.printf("ID: %d  Titel: %s aufgenommen im Jahr %d in %s\n", getID(), getTitle(), getJahr(), ort);
		ausgabe.flush();
	}
}
