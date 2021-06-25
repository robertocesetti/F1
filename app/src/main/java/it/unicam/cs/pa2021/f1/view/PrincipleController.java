package it.unicam.cs.pa2021.f1.view;

import it.unicam.cs.pa2021.f1.controller.DefaultMasterController;
import javafx.scene.control.Alert;

/**
 * Interfaccia che definisce il controller principale della vista con JavaFX.
 */
public interface PrincipleController {

    /**
     * Imposta il controllore principale del controller MVC.
     *
     * @param masterController il controller principale del controller MVC.
     */
    void controllerSettings(DefaultMasterController masterController);

    /**
     * Genera l'alert di errore.
     *
     * @param msg il messaggio stampato.
     */
    default void generateErrorAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Attention!!!!");
        alert.setHeaderText("Problems have been found:");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    /**
     * Genera l'alert di successo.
     *
     * @param msg il messaggio stampato.
     */
    default void generateSuccessAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bye-Bye!!");
        alert.setHeaderText(msg);
        alert.showAndWait();
    }
}
