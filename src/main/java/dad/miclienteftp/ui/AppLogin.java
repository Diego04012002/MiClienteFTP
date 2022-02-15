package dad.miclienteftp.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppLogin extends Application {
	
	LoginController controller;
	private static Stage primary;

	@Override
	public void start(Stage primaryStage) throws Exception {
		AppLogin.primary=new Stage();
		controller= new LoginController();
		
		Scene escena= new Scene(controller.getView());
		
		primaryStage.setTitle("Iniciar Conexion");
		primaryStage.setScene(escena);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public static Stage getPrimary() {
		return primary;
	}

}
