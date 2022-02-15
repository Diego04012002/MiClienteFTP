package dad.miclienteftp.ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class MainController implements Initializable {

	private StringProperty nombre = new SimpleStringProperty();
	private IntegerProperty tam = new SimpleIntegerProperty();
	private StringProperty filetype = new SimpleStringProperty();
	private ObjectProperty<File> fichero = new SimpleObjectProperty<>();

	// model
	private StringProperty labelX = new SimpleStringProperty();

	private StringProperty directorio = new SimpleStringProperty();

	private ObjectProperty<Fichero> seleccinado = new SimpleObjectProperty<>();

	private ListProperty<Fichero> tablaList = new SimpleListProperty<>(FXCollections.observableArrayList());

	private static LoginController controller;
	
	private ObjectProperty<FTPClient> ftpC= new SimpleObjectProperty<FTPClient>();

	@FXML
	private MenuItem conectarItem;

	@FXML
	private Button descargarButton;

	@FXML
	private MenuItem desconectarItem;

	@FXML
	private Label directorioLabel;

	@FXML
	private TableColumn<Fichero, String> nombreColumn;

	@FXML
	private TableView<Fichero> tablaElementos;

	@FXML
	private TableColumn<Fichero, Number> tamanoColumn;

	@FXML
	private TableColumn<Fichero, String> tipoColumn;

	@FXML
	private GridPane view;

	public MainController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("view.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// deshabilito el boton desconectar si no esta conectado a un servidor FTP
		labelX.bind(directorioLabel.textProperty());
		desconectarItem.disableProperty().bind(labelX.isEmpty());

		// deshabilito el boton conectar cuando esta conectado a un servidor FTP
		labelX.bind(directorioLabel.textProperty());
		conectarItem.disableProperty().bind(labelX.isNotEmpty());

		seleccinado.bind(tablaElementos.getSelectionModel().selectedItemProperty());

		tablaElementos.itemsProperty().bind(tablaList);
		
		ftpC.bind(controller.ftpProperty());

		tamanoColumn.setCellValueFactory(v -> v.getValue().tamanoProperty());
		nombreColumn.setCellValueFactory(v -> v.getValue().nombreProperty());
		tipoColumn.setCellValueFactory(v -> v.getValue().tipoProperty());

	}

	public GridPane getView() {
		return view;
	}

	@FXML
	void onConectarAction(ActionEvent event) throws IOException {
		controller = new LoginController();
		controller.showOnStage();
		directorioLabel.setText(controller.getFtp().printWorkingDirectory());
	}

	void listar() throws IOException {
		FTPFile[] ficheros;
		try {
			ficheros=controller.getFtp().listFiles();
			Arrays.stream(ficheros).forEach(ftpfile -> {
				tablaList.add(new Fichero(ftpfile));
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onDescargarAction(ActionEvent event) throws IOException {
		
	}

	@FXML
	void onDesconectarAction(ActionEvent event) throws IOException {
		directorioLabel.setText("");
	}

	@FXML
	void onMouseArchivoClicked(MouseEvent event) {

	}

}
