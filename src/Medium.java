import java.io.OutputStream;
import java.time.LocalDate;

public abstract class Medium implements Comparable<Medium> {
	private static int idIndex;
	private final int id;
	private String title;
	private int jahr;
	
	public Medium(String title, int jahr) {
		this.id = idIndex++;	
		this.title = title;
		this.jahr = jahr;
	}
	
	public int alter() {
		return LocalDate.now().getYear() - jahr;
	}
	
	public int getID() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getJahr() {
		return jahr;
	}
	
	public int compareTo(Medium x) {
		return Integer.compare(getJahr(), x.getJahr());
	}
	

	public abstract void druckeDaten(OutputStream stream);
}
