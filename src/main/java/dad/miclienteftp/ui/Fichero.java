package dad.miclienteftp.ui;

import java.io.File;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.FTPFile;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Fichero {
	
	@SuppressWarnings("serial")
	public static final Map<Integer, String> FILE_TYPE = new HashMap<Integer, String>() {{
		put(FTPFile.DIRECTORY_TYPE, "Directorio");
		put(FTPFile.FILE_TYPE, "Fichero");
		put(FTPFile.SYMBOLIC_LINK_TYPE, "Enlace");
	}};
	
	private ObjectProperty<FTPFile> fichero = new SimpleObjectProperty<>();
	
	private StringProperty nombre= new SimpleStringProperty();
	
	private LongProperty tamano = new SimpleLongProperty();
	
	private StringProperty tipo= new SimpleStringProperty();
	
	
	public Fichero(FTPFile file) {
		setNombre(file.getName());
		setTamano(file.getSize());
		setTipo(FILE_TYPE.get(file.getType()));
	}

	public ObjectProperty<FTPFile> ficheroProperty() {
		return this.fichero;
	}
	

	public FTPFile getFichero() {
		return this.ficheroProperty().get();
	}
	

	public void setFichero(final FTPFile fichero) {
		this.ficheroProperty().set(fichero);
	}
	

	public StringProperty nombreProperty() {
		return this.nombre;
	}
	

	public String getNombre() {
		return this.nombreProperty().get();
	}
	

	public void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}
	

	public LongProperty tamanoProperty() {
		return this.tamano;
	}
	

	public long getTamano() {
		return this.tamanoProperty().get();
	}
	

	public void setTamano(final long tamano) {
		this.tamanoProperty().set(tamano);
	}


	public StringProperty tipoProperty() {
		return this.tipo;
	}
	

	public String getTipo() {
		return this.tipoProperty().get();
	}
	

	public void setTipo(final String tipo) {
		this.tipoProperty().set(tipo);
	}

}
