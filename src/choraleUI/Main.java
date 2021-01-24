package choraleUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Interface.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Tilelli n wegdud");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
