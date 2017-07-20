package gui;
import datenhaltung.Medienverwaltung;

import fachlogik.Audio;
import fachlogik.Bild;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AudioErfassungView extends ModalStage {

	private Medienverwaltung medienverwaltung;

	public AudioErfassungView(Stage stage, Bild bild, Medienverwaltung medienverwaltung) {
		super(stage);
		this.medienverwaltung = medienverwaltung;
	}

	public void loadView() {
		VBox vbox = new VBox();
		GridPane gridpane = new GridPane();
		HBox hbox = new HBox();

		TextField titleField = new TextField();
		TextField jahrField = new TextField();
		TextField interpretField = new TextField();
		TextField dauerField = new TextField();
		Label titleLabel = new Label("Titel:");
		Label jahrLabel = new Label("Jahr:");
		Label interpretLabel = new Label("Interpret:");
		Label dauerLabel = new Label("Dauer:");
		titleField.setMinWidth(300.0);

		gridpane.add(titleField, 1, 0);
		gridpane.add(titleLabel, 0, 0);
		gridpane.add(jahrField, 1, 1);
		gridpane.add(jahrLabel, 0, 1);
		gridpane.add(interpretField, 1, 2);
		gridpane.add(interpretLabel, 0, 2);
		gridpane.add(dauerField, 1, 3);
		gridpane.add(dauerLabel, 0, 3);

		GridPane.setMargin(titleField, new Insets(10.0, 20.0, 5.0, 5.0));
		GridPane.setMargin(jahrField, new Insets(5.0, 20.0, 5.0, 5.0));
		GridPane.setMargin(interpretField, new Insets(5.0, 20.0, 5.0, 5.0));
		GridPane.setMargin(dauerField, new Insets(5.0, 20.0, 5.0, 5.0));
		GridPane.setMargin(interpretLabel, new Insets(0, 0, 0, 5.0)); 

		GridPane.setHalignment(titleLabel, HPos.RIGHT);
		GridPane.setHalignment(jahrLabel, HPos.RIGHT);
		GridPane.setHalignment(interpretLabel, HPos.RIGHT);
		GridPane.setHalignment(dauerLabel, HPos.RIGHT);

		Button neuButton = new Button("Neu");
		neuButton.setOnAction(EventHandler -> {
			String titleText = titleField.getText();
			String jahrText = jahrField.getText();
			String interpretText = interpretField.getText();
			String dauerText = dauerField.getText();
			int jahr, dauer;
			
			if (titleText.isEmpty() || jahrText.isEmpty() || interpretText.isEmpty() || dauerText.isEmpty()) {
				MessageView.create(this, "Leeres Feld", "Es darf kein leeres Feld vorhanden sein!").showView();
				return;
			}
			
			try {
				jahr = Integer.parseInt(jahrText);
			} catch (NumberFormatException e) {
				MessageView.create(this, "Leeres Feld", "Bitte geben Sie ein gültiges Jahr ein").showView();
				return;
			}
			
			try {
				dauer = Integer.parseInt(dauerText);
			} catch (NumberFormatException e) {
				MessageView.create(this, "Leeres Feld", "Bitte geben Sie eine gültige Dauer ein").showView();
				return;
			}
			
			medienverwaltung.aufnehmen(new Audio(titleText, jahr, interpretText, dauer));
			MessageView.create(this, "Medienverwaltung", "Medium erfolgreich hinzugefügt!").showView();
			this.close();
		});
		
		Button abbrechenButton = new Button("Abbrechen");
		abbrechenButton.setOnAction(EventHandler -> {
			this.close();
		});

		hbox.getChildren().addAll(neuButton, abbrechenButton);
		HBox.setMargin(neuButton, new Insets(5.0, 10.0, 5.0, 0));
		hbox.setPadding(new Insets(15.0, 0, 5.0, 0));
		hbox.setAlignment(Pos.CENTER);

		vbox.getChildren().addAll(gridpane, hbox);
		this.setScene(new Scene(vbox));
		this.setTitle("Audioerfassung");
		this.show();
	}

}
