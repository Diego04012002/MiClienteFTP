package dad.miclienteftp.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class LoginController implements Initializable {

	// model
	private StringProperty usuario = new SimpleStringProperty();
	private StringProperty contra = new SimpleStringProperty();
	private StringProperty server = new SimpleStringProperty();
	private IntegerProperty puerto = new SimpleIntegerProperty();
	private StringProperty directorio = new SimpleStringProperty();
	private ObjectProperty<FTPClient> ftp = new SimpleObjectProperty<FTPClient>();

	// pruebas
	private static FTPClient cliente;

	private Stage stage;

	@FXML
	private Button cancelarButton;

	@FXML
	private Button conectatButton;

	@FXML
	private TextField contraText;

	@FXML
	private TextField puertoText;

	@FXML
	private TextField serverText;

	@FXML
	private TextField usuarioText;

	@FXML
	private GridPane view;

	public LoginController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ConexionView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		usuario.bind(usuarioText.textProperty());
		contra.bind(contraText.textProperty());
		server.bind(serverText.textProperty());
		puertoText.textProperty().bindBidirectional(puerto, new NumberStringConverter());
	}

	public GridPane getView() {
		return view;
	}

	@FXML
	void onCancelarButton(ActionEvent event) {
		stage.close();
	}

	@FXML
	void onConectarButton(ActionEvent event) throws IOException {

		try {
			cliente = new FTPClient();

			cliente.connect(server.get(), puerto.get());

			cliente.login(usuario.get(), contra.get());
			
			ftp.set(cliente);

			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Conexion");
			alerta.setContentText("Conexion establecida con exito: " + server.get());
			alerta.setHeaderText("Exito");
			alerta.showAndWait();

		} catch (Exception e) {
			Alert alertaE = new Alert(AlertType.ERROR);
			alertaE.setTitle("Error");
			alertaE.setContentText("No se pudo conectar: " + server.get());
			alertaE.setHeaderText("Error");
			alertaE.showAndWait();
		}

		stage.close();
	}

	public void showOnStage() {
		stage = new Stage();
		stage.setTitle("Editar propiedad");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(App.getPrimary());
		stage.setScene(new Scene(view, 400, 200));
		stage.showAndWait();
	}

	public ObjectProperty<FTPClient> ftpProperty() {
		return this.ftp;
	}

	public FTPClient getFtp() {
		return this.ftpProperty().get();
	}

	public void setFtp(final FTPClient ftp) {
		this.ftpProperty().set(ftp);
	}

}
