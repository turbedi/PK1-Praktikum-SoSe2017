package gui;

import fachlogik.Audio;
import fachlogik.Bild;
import datenhaltung.Medienverwaltung;
import datenhaltung.PersistenzException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.VBox;

public class HauptfensterView extends Application {

	private Medienverwaltung medienverwaltung;
	
	@Override
	public void start(Stage stage) throws Exception {	
		
		medienverwaltung = new Medienverwaltung();
		Audio audio = new Audio("It Means Nothing", 2015, "Stereophonics", 229);
		Bild bild = new Bild("Gebäude FB Informatik", 2014, "Dortmund");
		medienverwaltung.aufnehmen(audio);
		medienverwaltung.aufnehmen(bild);
		
		VBox root = new VBox();
		MenuBar menuBar = createMenuBar(stage);
		root.getChildren().add(menuBar);
		
		stage.setScene(new Scene(root, 350.0, 150.0));
		stage.setTitle("Medienverwaltung");
		stage.show();		
	}
	
	private MenuBar createMenuBar(Stage stage) {
		final Menu dateiMenu = new Menu("Datei");
		final Menu mediumMenu = new Menu("Medium");
		final Menu anzeigeMenu = new Menu("Anzeige");
		
		MenuItem ladenItem = new MenuItem("Laden");
		ladenItem.setOnAction(ActionEvent -> {
			try {
				medienverwaltung.laden();
			} catch (PersistenzException e) {
				e.printStackTrace();
			}
		});
		
		MenuItem speichernItem = new MenuItem("Speichern");
		speichernItem.setOnAction(ActionEvent -> {
			try {
				medienverwaltung.speichern(medienverwaltung.getMedienliste());
			} catch (PersistenzException e) {
				e.printStackTrace();
			}
		});
		
		MenuItem listeInDateiItem = new MenuItem("Medienliste in Datei");
		listeInDateiItem.setOnAction(ActionEvent -> {
			InputView inputView = InputView.create(stage, "Dateiname", "Bitte geben sie den gewünschten Dateinamen ein: ", "");
			String dateiname = inputView.showView();
			if (dateiname == null)
				return;
			
			if (dateiname.isEmpty()) {
				MessageView.create(inputView, "Warnung", "Leere Dateinamen sind nicht erlaubt!").showView();
				return;
			}	
			medienverwaltung.medienlisteInDatei(dateiname + ".txt");
			System.out.println("Medienliste erfolgreich gespeichert!");
			
		});
		
		MenuItem beendenItem = new MenuItem("Beenden");
		beendenItem.setOnAction(ActionEvent -> {
			stage.close();
		});
		
		
		dateiMenu.getItems().addAll(ladenItem, speichernItem, new SeparatorMenuItem(), listeInDateiItem, new SeparatorMenuItem(), beendenItem);
		
		MenuItem audioNeuItem = new MenuItem("Audio neu");
		audioNeuItem.setOnAction(ActionEvent -> {
			new AudioErfassungView(stage, null, medienverwaltung).loadView();
		});
		
		MenuItem bildNeuItem = new MenuItem("Bild neu");
		bildNeuItem.setOnAction(ActionEvent -> {
			new BildErfassungView(stage, null, medienverwaltung).loadView();
		});
		
		mediumMenu.getItems().addAll(audioNeuItem, bildNeuItem);
		
		MenuItem erscheinungsjahrItem = new MenuItem("Erscheinungjahr");
		erscheinungsjahrItem.setOnAction(ActionEvent -> {
			MessageView.create(stage, "Durchschnittliches Erscheinungsjahr", "Das durchschnittliche Erscheinungsjahr ist "+ medienverwaltung.berechneErscheinungsjahr()).showView();
		});
		
		MenuItem neuestesMediumItem = new MenuItem("Neuestes Medium");
		neuestesMediumItem.setOnAction(ActionEvent -> {
			MessageView.create(stage, "Neuestes Medium", "Das neueste Medium ist: "+ medienverwaltung.sucheNeuesMedium().toString()).showView();
		});
		
		anzeigeMenu.getItems().addAll(erscheinungsjahrItem, neuestesMediumItem);
		
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(dateiMenu, mediumMenu, anzeigeMenu);
		return menuBar;
	}

}
