import javax.swing.JOptionPane;

public class Menu {
	
	private Medienverwaltung medienverwaltung;
	
	public Menu(Medienverwaltung medienverwaltung) {
		this.medienverwaltung = medienverwaltung;
	}
	
	public void menuStart() {
        
        int eingabe, jahr, dauer;
        String titel, ort, interpret, jahrString, dauerString;
		
		String eingabeDialog = JOptionPane.showInputDialog(null,
				"Medienverwaltung\n\n1. Audio aufnehmen\n2. Bild aufnehmen\n3. Zeige alle Medien\n4. Zeige neues Medium\n5. Berechne durchschnittliches Erscheinungsjahr\n6. Beenden\n");
		
		// eingabeDialog bleibt null wenn man auf Abbrechen klickt
		if (eingabeDialog == null)
			return;
		
		try {
			eingabe = Integer.parseInt(eingabeDialog);
		} catch (NumberFormatException e) { // Die Eingabe ist keine Zahl
			JOptionPane.showMessageDialog(null, "Ungültige Eingabe");
			menuStart();
		}
		
		switch (eingabe) {
			case 1: // Füge ein Audio-Objekt zu der Liste hinzu
				if (!inputTitelJahr()) // Input für Titel und Jahr
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
			case 2: // Füge ein Bild-Objekt zu der Liste hinzu
				if (!inputTitelJahr()) // Input für Titel und Jahr
					break;
				
				ort = JOptionPane.showInputDialog(null, "Aufnahmeort");
				Bild bild = new Bild(titel, jahr, ort);
				medienverwaltung.aufnehmen(bild);
				break;
			case 3: // Zeige alle Medien in der Liste
				medienverwaltung.zeigeMedien();
				break;
			case 4: // Suche nach dem neuesten Medium
				medienverwaltung.sucheNeuesMedium();
				break;
			case 5: // Berechne durchschnittliches Erscheinungsjahr
				JOptionPane.showMessageDialog(null, "Das durchschnittliche Erscheinungsjahr ist"
						+ medienverwaltung.berechneErscheinungsjahr());
			case 6: // Beende Programm
				break;
			default: // Zahl, die nicht zwischen 1 und 6 ist
				menuStart();
				break;
		
		}
	}
	
    // Damit der Code in Audio- und Bild-Objekterugung doppelt verwendet wird (Input-Code für Titel und Jahr ist gleich)
    // Rückgabetyp boolean, damit man die switch-Abfrage von menuStart() unterbrechen kann
    // true = erfolgreich, false = Exception und Abbruch
	public boolean inputTitelJahr() {
        String titel = JOptionPane.showInputDialog(null, "Titel");					
        String jahrString = JOptionPane.showInputDialog(null, "Jahr");
        int jahr;
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
