package gui;

import datenhaltung.Medienverwaltung;
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

public class BildErfassungView extends ModalStage {
	
	private Medienverwaltung medienverwaltung;
	
	public BildErfassungView(Stage stage, Bild bild, Medienverwaltung medienverwaltung) {
		super(stage);
		this.medienverwaltung = medienverwaltung;
	}
	
	public void loadView() {
		VBox vbox = new VBox();
		GridPane gridpane = new GridPane();
		HBox hbox = new HBox();
		
		TextField titleField = new TextField();
		TextField ortField = new TextField();
		TextField aufnahmejahrField = new TextField();	
		Label titleLabel = new Label("Titel:");
		Label ortLabel = new Label("Ort:");
		Label aufnahmeJahrLabel = new Label("Aufnahmejahr:");
		titleField.setMinWidth(300.0);
			
		gridpane.add(titleField, 1, 0);
		gridpane.add(titleLabel, 0, 0);
		gridpane.add(ortField, 1, 1);
		gridpane.add(ortLabel, 0, 1);
		gridpane.add(aufnahmejahrField, 1, 2);
		gridpane.add(aufnahmeJahrLabel, 0, 2);
			
		GridPane.setMargin(titleField, new Insets(10.0, 20.0, 5.0, 5.0));
		GridPane.setMargin(ortField, new Insets(5.0, 20.0, 5.0, 5.0));
		GridPane.setMargin(aufnahmejahrField, new Insets(5.0, 20.0, 25.0, 5.0));
		GridPane.setMargin(aufnahmeJahrLabel, new Insets(0, 0, 25.0, 5.0)); // Um einen kleinen Abstand links vom Label zu bekommen

		GridPane.setHalignment(titleLabel, HPos.RIGHT); // Damit der Text am TextField ist
		GridPane.setHalignment(ortLabel, HPos.RIGHT);
		GridPane.setHalignment(aufnahmeJahrLabel, HPos.RIGHT);			
			
		Button neuButton = new Button("Neu");			
		neuButton.setOnAction(EventHandler -> {
			String titleText = titleField.getText();
			String ortText = ortField.getText();
			String aufnahmejahrText = aufnahmejahrField.getText();
			int aufnahmejahr;

			if (titleText.isEmpty() || ortText.isEmpty() || aufnahmejahrText.isEmpty()) {
				MessageView.create(this, "Leeres Feld", "Es darf kein leeres Feld vorhanden sein!").showView();
				return;
			}
				
			try {
				aufnahmejahr = Integer.parseInt(aufnahmejahrText);
			} catch (NumberFormatException e) {
				MessageView.create(this, "Leeres Feld", "Bitte geben Sie ein gültiges Jahr ein").showView();
				return;
			}
			medienverwaltung.aufnehmen(new Bild(titleText, aufnahmejahr, ortText));
			MessageView.create(this, "Medienverwaltung", "Medium erfolgreich hinzugefügt!").showView();
			this.close();
		});
		
		Button abbrechenButton = new Button("Abbrechen");
		abbrechenButton.setOnAction(ActionEvent -> {
			this.close();
		});
			
		hbox.getChildren().addAll(neuButton, abbrechenButton);
		HBox.setMargin(neuButton, new Insets(5.0, 10.0, 5.0, 0));
		hbox.setPadding(new Insets(0, 0, 5.0, 0));
		hbox.setAlignment(Pos.CENTER);
			
		vbox.getChildren().addAll(gridpane, hbox);
		this.setScene(new Scene(vbox));
		this.setTitle("Bilderfassung");
		this.show();
	}

}
