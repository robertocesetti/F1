package it.unicam.cs.pa2021.f1.view;

import it.unicam.cs.pa2021.f1.controller.DefaultMasterController;
import it.unicam.cs.pa2021.f1.controller.MasterController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Implementazione per una vista con JavaFX.
 */
public class JavaFXView extends Application implements View {

    DefaultMasterController controller = new DefaultMasterController();

    @Override
    public void open() {
        Application.launch(this.getClass());
    }

    @Override
    public void close() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/F1.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("F1 - Pencil Racing");
        primaryStage.setScene(new Scene(root));
        JavaFXController controller = loader.getController();
        controller.setMasterController(this.controller);
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        controller = new DefaultMasterController();
        super.init();
    }

}
