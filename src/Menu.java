import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JOptionPane;

public class Menu {
	
	private Medienverwaltung medienverwaltung;
	
	private int eingabe, dauer, jahr;
	private String titel, ort, interpret, jahrString, dauerString, dateiname;
	private boolean erfolg = false;
	
	public Menu(Medienverwaltung medienverwaltung) {
		this.medienverwaltung = medienverwaltung;
	}
	
	public void menuStart() {
		
		String eingabeDialog = JOptionPane.showInputDialog(null,
				"Medienverwaltung\n\n1. Audio aufnehmen\n2. Bild aufnehmen\n3. Zeige alle Medien\n4. Medienliste in Datei schreiben\n5. Zeige neues Medium\n6. Berechne durchschnittliches Erscheinungsjahr\n7. Beenden\n");
		
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
				if (!inputTitelJahr()) { // Input für Titel und Jahr
					menuStart();
					break;
				}
				
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
				menuStart();
				break;
			case 2: // Füge ein Bild-Objekt zu der Liste hinzu
				if (!inputTitelJahr()) { // Input für Titel und Jahr
					menuStart();
					break;
				}
				
				ort = JOptionPane.showInputDialog(null, "Aufnahmeort");
				Bild bild = new Bild(titel, jahr, ort);
				medienverwaltung.aufnehmen(bild);
				menuStart();
				break;
			case 3: // Zeige alle Medien in der Liste
				medienverwaltung.zeigeMedien(System.out);
				menuStart();
				break;
			case 4: // Schreibe die Medienliste in eine Datei
				while (true) {
					try {
						medienListeInDatei();
						break;
					} catch (EmptyFilenameException e) {
		            	int jaNein = JOptionPane.showConfirmDialog(null, "Dateiname ist leer! Neuen Dateinamen wählen?",
            			"Hinweis", JOptionPane.YES_NO_OPTION);
		            	if (jaNein != JOptionPane.YES_OPTION) 
		            		break;
					}
				}

				menuStart();
				break;
			case 5: //Suche das neueste Medium
				medienverwaltung.sucheNeuesMedium();				
				menuStart();
				break;
			case 6: // Berechne durchschnittliches Erscheinungsjahr
				JOptionPane.showMessageDialog(null, "Das durchschnittliche Erscheinungsjahr ist "
						+ medienverwaltung.berechneErscheinungsjahr());
				menuStart();
				break;
			case 7: // Beende Programm
				return;
			default: // Zahl, die nicht zwischen 1 und 6 ist
				menuStart();
				break;
		
		}
	}
	
	public void medienListeInDatei() throws EmptyFilenameException {

        
            dateiname = JOptionPane.showInputDialog(null, "Dateiname");
            if (dateiname == null) // Abbruch
            	return;
        
            if (dateiname.isEmpty()) {
            	throw new EmptyFilenameException();
//            	int jaNein = JOptionPane.showConfirmDialog(null, "Dateiname ist leer! Neuen Dateinamen wählen?",
//            			"Hinweis", JOptionPane.YES_NO_OPTION);
//            	if (jaNein != JOptionPane.YES_OPTION) 
//            		break;
            }
        
       
//        if (dateiname.isEmpty()) {
//        	//throw new EmptyFilenameException();
//        	int jaNein = JOptionPane.showConfirmDialog(null, "Dateiname ist leer! Neuen Dateinamen wählen?",
//        			"Hinweis", JOptionPane.YES_NO_OPTION);
//        	if (jaNein == JOptionPane.YES_OPTION) 
//        		medienListeInDatei();
//        	return;
//        }
        
        File listenDatei = new File(dateiname);
		BufferedOutputStream bufferOutput = null;
		try {
			OutputStream output = new FileOutputStream(listenDatei);
			bufferOutput = new BufferedOutputStream(output);
	        medienverwaltung.zeigeMedien(bufferOutput);
	        erfolg = true;
		} catch (IOException e) {
			System.out.println("Fehler");
			e.printStackTrace();
		} finally {
			try {
				bufferOutput.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
    // Damit der Code in Audio- und Bild-Objekterugung doppelt verwendet wird (Input-Code für Titel und Jahr ist gleich)
    // Rückgabetyp boolean, damit man die switch-Abfrage von menuStart() unterbrechen kann
    // true = erfolgreich, false = Exception und Abbruch
	public boolean inputTitelJahr() {
        titel = JOptionPane.showInputDialog(null, "Titel");					
        jahrString = JOptionPane.showInputDialog(null, "Jahr");
		try {
			jahr = Integer.parseInt(jahrString);
			return true;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Ungültiges Jahr");
			return false;
		}
	}
}
