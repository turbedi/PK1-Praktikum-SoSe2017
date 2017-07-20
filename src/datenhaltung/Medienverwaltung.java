package datenhaltung;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import fachlogik.Medium;

public class Medienverwaltung implements Serializable, IDao {
	
	private static final long serialVersionUID = 1L;
	private List<Medium> medienListe;
	
	public Medienverwaltung() {
		medienListe = new LinkedList<>();
	}

	public void aufnehmen(Medium medium) {
		medienListe.add(medium);
	}
	
	public void zeigeMedien(OutputStream stream) {
		Collections.sort(medienListe);
		
		for (Medium m: medienListe) {
			m.druckeDaten(stream);
		}
	}
	
	public Medium sucheNeuesMedium() {
		Medium n = medienListe.get(0);
		for (Medium m: medienListe) {
			if (m.getJahr() > n.getJahr()) {
				n = m;
			}
		}
		return n;
	}
	
	public double berechneErscheinungsjahr() {
		if (medienListe.isEmpty())
			return 0;
		double jahre = 0;
		int counter = 0;
		for (Medium m: medienListe) {
			jahre += m.getJahr();
			counter++;
		}
		return jahre/counter;
	}
	
	public static void copy(File from, File to) throws IOException {
		
		try (RandomAccessFile input = new RandomAccessFile(from, "rw");
			 RandomAccessFile output = new RandomAccessFile(to, "rw");) {
			
			output.seek(0);
			for (int i = 0; i < from.length(); i++)
				output.write(input.read());
			
		} catch (IOException e) {
			System.err.println(e.toString());
		}
	}
	
	public static void cat(File quelle) throws IOException {
		String line = null;
		try (RandomAccessFile input = new RandomAccessFile(quelle, "r");) { 
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			System.err.println(e.toString());
		}
	}
	
	public Iterator<Medium> iterator() {
		return medienListe.iterator();
	}

	@Override
	public void speichern(List<Medium> liste) throws PersistenzException {
		File serFile = new File("medienliste.ser");
		
		try(FileOutputStream fos= new FileOutputStream(serFile);	ObjectOutputStream oos= new ObjectOutputStream(fos);) {
			oos.writeObject(liste);
			System.out.println("Serialisierung erfolgreich");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Serialisierung fehlgeschlagen");
		}	
	}
	
	public List<Medium> getMedienliste() {
		return medienListe;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Medium> laden() throws PersistenzException {
		File file = new File("medienliste.ser");
		try (FileInputStream fis = new FileInputStream(file);	ObjectInputStream ois = new ObjectInputStream(fis);) {
			
			List<Medium> neueListe = (List<Medium>) ois.readObject(); // Lese Object aus der .ser Datei
			System.out.println("Deserialisierung erfolgreich");
			return neueListe;
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Deserialisierung fehlgeschlagen");
			return null;
		}
	}
	
	public void medienlisteInDatei(String dateiname) {
		File listenDatei = new File(dateiname);
		BufferedOutputStream bufferOutput = null;
		try {
			OutputStream output = new FileOutputStream(listenDatei);
			bufferOutput = new BufferedOutputStream(output);
			zeigeMedien(bufferOutput);
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
}
