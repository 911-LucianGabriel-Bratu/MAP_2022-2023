package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader programListLoader = new FXMLLoader();
        programListLoader.setLocation(Main.class.getResource("ToyProgramSelectorController.fxml"));
        Parent programListRoot = programListLoader.load();
        Scene programListScene = new Scene(programListRoot, 500, 550);
        ToyProgramSelectorController programSelectorController = programListLoader.getController();
        stage.setTitle("Select a program");
        stage.setScene(programListScene);
        stage.show();

        FXMLLoader programExecutionLoader = new FXMLLoader();
        programExecutionLoader.setLocation(Main.class.getResource("ToyProgramExecutionController.fxml"));
        Parent programExecutionRoot = programExecutionLoader.load();
        Scene programExecutionScene = new Scene(programExecutionRoot, 700, 500);
        ToyProgramExecutionController programExecutionController = programExecutionLoader.getController();
        programSelectorController.setToyProgramExecutionController(programExecutionController);
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Interpreter");
        secondaryStage.setScene(programExecutionScene);
        secondaryStage.show();

    }

    public static void main(String[] args){
        launch(args);
    }
}
