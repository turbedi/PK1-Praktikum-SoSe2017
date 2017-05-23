
public class Main {

	public static void main(String[] args) {
		Audio audio = new Audio("It Means Nothing", 2015, "Stereophonics", 229);
		Bild bild = new Bild("Gebäude FB Informatik", 2014, "Dortmund");
		
		Medienverwaltung medienverwaltung = new Medienverwaltung();
		medienverwaltung.aufnehmen(audio);
		medienverwaltung.aufnehmen(bild);
		//medienverwaltung.zeigeMedien();
//		System.out.println("----------------------------------");
//		medienverwaltung.sucheNeuesMedium();
//		System.out.println("----------------------------------");
//		medienverwaltung.zeigeMedien();
		Menu menu = new Menu(medienverwaltung);
		menu.menuStart();
		
	}

}
