package it.unicam.cs.pa2021.f1.view;

import it.unicam.cs.pa2021.f1.controller.DefaultMasterController;
import it.unicam.cs.pa2021.f1.model.PilotType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * Controller della vista in JavaFX che gestisce la configurazione delle impostazioni di gioco.
 */
public class GameSettingsController implements PrincipleController {

    private static final Logger LOGGER = Logger.getLogger(JavaFXController.class.getName());
    FileChooser fc = new FileChooser();
    private DefaultMasterController masterController;
    private String path;
    private int numberOfBots = 0;
    private int numberOfPlayer = 0;

    @FXML
    private Label labelBot;

    @FXML
    private Button removeBotId;

    @FXML
    private TextField playerNameInput;

    @FXML
    private ListView<String> lisOfPlayers;

    @Override
    public void controllerSettings(DefaultMasterController masterController) {
        this.masterController = masterController;
        lisOfPlayers.setItems(FXCollections.observableArrayList(new LinkedList<>()));
    }

    @FXML
    public void chooseFile() {
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG File ", "*.png"));
        File file = fc.showOpenDialog(null);
        if (file == null) {
            generateErrorAlert("File selezionato non valido, riselezionare!");
        } else {
            this.path = file.getAbsolutePath();
            LOGGER.info(path);
        }
    }

    @FXML
    public void addBot() {
        numberOfBots++;
        labelBot.setText(String.valueOf(this.numberOfBots));
        removeBotId.setDisable(false);
    }

    @FXML
    public void removeBot() {
        numberOfBots--;
        labelBot.setText(String.valueOf(this.numberOfBots));
        if (numberOfBots == 0) removeBotId.setDisable(true);
    }

    @FXML
    public void addPlayer() {
        setPlayerName();
        numberOfPlayer++;
    }

    private void setPlayerName() {
        this.lisOfPlayers.getItems().add(playerNameInput.getText());
        playerNameInput.clear();
    }

    private void configureBot() {
        for (int i = 1; i <= numberOfBots; i++) {
            masterController.configurePlayer("bot" + i, PilotType.BOT);
        }
    }

    private void doClose(ActionEvent actionEvent) {
        ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
    }

    @FXML
    public void cancel(ActionEvent actionEvent) {
        LOGGER.info("Cancel!");
        doClose(actionEvent);
    }

    @FXML
    public void startGame(ActionEvent actionEvent) {
        if (numberOfPlayer + numberOfBots < 2) {
            generateSuccessAlert("Non ci sono abbastanza giocatori");
            return;
        }
        if (this.path == null) {
            generateSuccessAlert("Il path passato non e' valido");
            return;
        }
        try {
            masterController.newGame(this.path);
            lisOfPlayers.getItems().forEach(p -> masterController.configurePlayer(p, PilotType.PLAYER));
            configureBot();
            doClose(actionEvent);
        } catch (IOException e) {
            generateSuccessAlert("File non trovato.");
            this.path = null;
        }
    }

}
