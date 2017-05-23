import java.io.OutputStream;

public class Bild extends Medium {
	private String ort;
	
	public Bild(String titel, int jahr, String ort) {
		super(titel, jahr);
		this.ort = ort;
	}
	
	public void druckeDaten(OutputStream Stream) {
		System.out.println("ID: " + getID() + " " + getTitle() + " aufgenommen im Jahr " + getJahr() + " in " + ort);
	}
}
