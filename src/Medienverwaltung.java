import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Medienverwaltung {
	private List<Medium> medienListe;
	
	public Medienverwaltung() {
		medienListe = new LinkedList<>();
	}

	public void aufnehmen(Medium medium) {
		medienListe.add(medium);
	}
	
	public void zeigeMedien() {
		Collections.sort(medienListe);
		
		Iterator<Medium> it = medienListe.iterator();
		while (it.hasNext())
			it.next().druckeDaten();
	}
	
	public void sucheNeuesMedium() {
		Medium n = medienListe.get(0);
		for (Medium m: medienListe) {
			if (m.getJahr() > n.getJahr()) {
				n = m;
			}
		}
		n.druckeDaten();
	}
	
	public double berechneErscheinungsjahr() {
		if (medienListe.isEmpty())
			return 0;
		double jahre=0;
		int counter=0;
		for (Medium m: medienListe) {
			jahre += m.getJahr();
			counter++;
		}
		return jahre/counter;
		
	}
	
	public static void copy(File from, File to) throws IOException { // Von Vorlesung 5 kopiert, aber mit Buffer
//		BufferedInputStream bufferInput = null;
//		BufferedOutputStream bufferOutput = null;
//		try {
//			InputStream input = new FileInputStream(from);
//			OutputStream output = new FileOutputStream(to);
//			bufferInput = new BufferedInputStream(input);
//			bufferOutput = new BufferedOutputStream(output);
//		
//			int c;
//			while ((c = bufferInput.read()) != -1) {
//				bufferOutput.write(c);
//			}
//
//		} catch (IOException e) {
//			System.out.println("Fehler");
//			e.printStackTrace();
//		} finally {
//			try {
//				bufferInput.close();
//				bufferOutput.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
		
		// mit RandomAccessFile
		
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

}
