package dad.miclienteftp.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	
	MainController controller;
	private static Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		App.stage=new Stage();
		controller=new MainController();
		
		Scene escena= new Scene(controller.getView());
		
		primaryStage.setTitle("Mi cliente FTP");
		primaryStage.setScene(escena);
		primaryStage.show();
		
	}
	
	public static Stage getPrimary() {
		return stage;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
