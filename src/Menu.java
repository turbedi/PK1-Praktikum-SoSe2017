import javax.swing.JOptionPane;

public class Menu {
	
	private Medienverwaltung medienverwaltung;
	
	public Menu(Medienverwaltung medienverwaltung) {
		this.medienverwaltung = medienverwaltung;
	}
	
	private int eingabe, jahr, dauer;
	private String titel, ort, interpret, jahrString, dauerString;
	
	public void menuStart() {
		
		String eingabeDialog = JOptionPane.showInputDialog(null,
				"Medienverwaltung\n\n1. Audio aufnehmen\n2. Bild aufnehmen\n3. Zeige alle Medien\n4. Zeige neues Medium\n5. Berechne durchschnittliches Erscheinungsjahr\n6. Beenden\n");
		
		// eingabeDialog bleibt null wenn man auf Abbrechen klickt
		if (eingabeDialog == null)
			return;
		
		try {
			eingabe = Integer.parseInt(eingabeDialog);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Ungültige Eingabe");
			menuStart();
		}
		
		switch (eingabe) {
			case 1:
				if (!inputTitelJahr())
					break;
				
				interpret = JOptionPane.showInputDialog(null, "Interpret");
				dauerString = JOptionPane.showInputDialog(null, "Länge");
				try {
					dauer = Integer.parseInt(dauerString);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Ungültige Dauer");
					menuStart();
					break;
				}
				Audio audio = new Audio(titel, jahr, interpret, dauer);
				medienverwaltung.aufnehmen(audio);
				break;
			case 2:
				if (!inputTitelJahr())
					break;
				
				ort = JOptionPane.showInputDialog(null, "Aufnahmeort");
				Bild bild = new Bild(titel, jahr, ort);
				medienverwaltung.aufnehmen(bild);
				break;
			case 3:
				medienverwaltung.zeigeMedien();
				break;
			case 4:
				medienverwaltung.sucheNeuesMedium();
				break;
			case 5:
				JOptionPane.showMessageDialog(null, "Das durchschnittliche Erscheinungsjahr ist"
						+ medienverwaltung.berechneErscheinungsjahr());
			case 6:
				break;
			default:
				menuStart();
				break;
		
		}
	}
		
	public boolean inputTitelJahr() {
		titel = JOptionPane.showInputDialog(null, "Titel");					
		jahrString = JOptionPane.showInputDialog(null, "Jahr");
		try {
			jahr = Integer.parseInt(jahrString);
			return true;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Ungültiges Jahr");
			menuStart();
			return false;
		}
	}
}
