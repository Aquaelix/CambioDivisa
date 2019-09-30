package dad.javafx.cambiodivisaapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisaApp extends Application {

	private TextField origenText, destinoText;
	private ComboBox<String> origenCombo, destinoCombo;
	private Button cambiarButton;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		origenText = new TextField();
		origenText.setPromptText("Divisa 1");
		origenText.setMaxWidth(80);
		
		destinoText = new TextField();
		destinoText.setPromptText("Divisa 2");
		destinoText.setMaxWidth(80);
		destinoText.setEditable(false);
		
		origenCombo = new ComboBox<String>();
		origenCombo.getItems().addAll("Euro", "Yen", "Dolar", "Libra");
		origenCombo.setPromptText("Divisa 1");
		
		destinoCombo = new ComboBox<String>();
		destinoCombo.getItems().addAll("Euro", "Yen", "Dolar", "Libra");
		destinoCombo.setPromptText("Divisa 1");
		
		cambiarButton = new Button("Cambiar");
		cambiarButton.setDefaultButton(true);
		cambiarButton.setOnAction(e -> onLoginButtonAction(e));
		
		HBox origenBox = new HBox(5, origenText, origenCombo);
		origenBox.setAlignment(Pos.CENTER);
		
		HBox destinoBox = new HBox(5, destinoText, destinoCombo);
		destinoBox.setAlignment(Pos.CENTER);
		
		HBox cambioBox = new HBox(5, cambiarButton);
		cambioBox.setAlignment(Pos.CENTER);
		
		VBox root = new VBox(5, origenBox, destinoBox, cambioBox);
		root.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(root, 320, 200);
		
		primaryStage.setTitle("Cambio de divisa");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	private void onLoginButtonAction(ActionEvent e) {
		
		Divisa euro = new Divisa("Euro", 1.0);
		Divisa libra = new Divisa("Libra", 0.8873);
		Divisa dolar = new Divisa("Dolar", 1.2007);
		Divisa yen = new Divisa("Yen", 133.59);
		Divisa origen = null, destino = null; 
		Double cantidad = 0.0;
		try {
			cantidad = Double.valueOf(origenText.getText());
		}catch (NumberFormatException h) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText("Intento de cambio de divisa");
			alert.setContentText("Error al intentar cambiar de divisa, verifique los datos introducidos.");
			
			alert.showAndWait();
		}
		
		try {
			switch (origenCombo.getSelectionModel().getSelectedItem()) {
				case "Euro":
					origen = euro;
					break;
				case "Libra":
					origen = libra;
					break;
				case "Dolar":
					origen = dolar;
					break;
				case "Yen":
					origen = yen;
					break;
				}
		}catch (NullPointerException h) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText("Intento de cambio de divisa");
			alert.setContentText("Error al intentar cambiar de divisa, verifique los datos introducidos.");
			
			alert.showAndWait();
		}
		
		try {
			switch (destinoCombo.getSelectionModel().getSelectedItem()) {
				case "Euro":
					destino = euro;
					break;
				case "Libra":
					destino = libra;
					break;
				case "Dolar":
					destino = dolar;
					break;
				case "Yen":
					destino = yen;
					break;
				}
		}catch (NumberFormatException h) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText("Intento de cambio de divisa");
			alert.setContentText("Error al intentar cambiar de divisa, verifique los datos introducidos.");
			
			alert.showAndWait();
		}
		
		destinoText.setText(destino.fromEuro(origen.toEuro(cantidad)).toString()); 

	}

	public static void main(String[] args) {
		launch(args);
	}

}
