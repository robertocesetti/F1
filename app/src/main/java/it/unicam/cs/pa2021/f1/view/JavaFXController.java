package it.unicam.cs.pa2021.f1.view;

import it.unicam.cs.pa2021.f1.controller.DefaultMasterController;
import it.unicam.cs.pa2021.f1.model.PilotType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JavaFXController implements PrincipleController {

    private final List<String> playerName = new ArrayList<>();
    FileChooser fc = new FileChooser();
    private JavaFXView jfx;
    private DefaultMasterController masterController;
    private String path;
    private int numberOfBots = 0;
    private int numberOfPlayer = 0;
    @FXML
    private Label labelBot;

    @FXML
    private Label labelPlayer;

    @FXML
    private Button removeBotId;

    @FXML
    private Button removePlayerId;

    @FXML
    private TextField playerNameInput;

    public void setJfx(JavaFXView jfx) {
        this.jfx = jfx;
    }

    @Override
    public void setMasterController(DefaultMasterController masterController) {
        this.masterController = masterController;
        System.out.println(this.masterController + " " + masterController);
    }

    private void newScene(String filePath) {
        try {
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(filePath));
            Parent root = loader.load();
            PrincipleController controller = loader.getController();
            controller.setMasterController(this.masterController);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleNewMatch() {
        newScene("/start.fxml");
    }

    @FXML
    public void chooseFile() throws IOException {
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG File ", "*.png"));
        File file = fc.showOpenDialog(null);
        this.path = file.getAbsolutePath();
        System.out.println(path);
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
        newScene("/player.fxml");
        numberOfPlayer++;
        labelPlayer.setText(String.valueOf(this.numberOfPlayer));
        removePlayerId.setDisable(false);
    }

    @FXML
    public void removePlayer() {
        this.playerName.remove(playerName.size() - 1);
        numberOfPlayer--;
        labelPlayer.setText(String.valueOf(this.numberOfPlayer));
        if (numberOfPlayer == 0) removePlayerId.setDisable(true);
    }

    @FXML
    public void setPlayerName(ActionEvent actionEvent) {
        this.playerName.add(playerNameInput.getText());
        doClose(actionEvent);
    }

    @FXML
    public void startGame(ActionEvent actionEvent) {
        if (numberOfPlayer + numberOfBots < 2) {
            generatAlert("Non ci sono abbastanza giocatori");
            return;
        }
        if (this.path == null) {
            generatAlert("Il path passato non e' valido");
            return;
        }
        try {
            System.out.println(masterController);
            masterController.newGame(this.path);
            playerName.forEach(p -> masterController.configurePlayer(p, PilotType.PLAYER));
            configureBot();
            doClose(actionEvent);
            newScene("/game.fxml");
        } catch (IOException e) {
            generatAlert("File non trovato.");
            this.path = null;
        }
    }


    private void configureBot() {
        for (int i = 1; i <= numberOfBots; i++) {
            masterController.configurePlayer("bot" + i, PilotType.BOT);
        }
    }

    private void generatAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Attenzione!!!!");
        alert.setHeaderText("Sono stati riscontrati dei problemi:");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void doClose(ActionEvent actionEvent) {
        ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
    }

    @FXML
    public void cancel(ActionEvent actionEvent) {
        System.out.println("Cancel!");
        doClose(actionEvent);
    }

}
