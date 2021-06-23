package it.unicam.cs.pa2021.f1.view;

import it.unicam.cs.pa2021.f1.controller.DefaultMasterController;
import it.unicam.cs.pa2021.f1.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Controller principale della vista in JavaFX.
 */
public class JavaFXController implements PrincipleController {

    private static final Logger LOGGER = Logger.getLogger(JavaFXController.class.getName());
    private final Image track;
    private final Image grid;
    private final Image nearPosition;
    private List<DefaultPosition> positionList;
    private DefaultMasterController masterController;
    @FXML
    private Button startRaceButton;

    @FXML
    private Canvas canvas;

    public JavaFXController() {
        track = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/asfalto.jpg")));
        grid = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/grid.jpg")));
        nearPosition = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/nearPosition.png")));
    }


    @FXML
    public void handleNewMatch() {
        newScene("/start.fxml");
        setCanvas();
        setStartButton();
    }

    private void setStartButton() {
        startRaceButton.setDisable(false);
        startRaceButton.setVisible(true);
    }

    private void newScene(String filePath) {
        try {
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(filePath));
            Parent root = loader.load();
            PrincipleController controller = loader.getController();
            controller.controllerSettings(this.masterController);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void controllerSettings(DefaultMasterController masterController) {
        this.masterController = masterController;
    }

    private void setCanvas() {
        DefaultRacingPlan racingPlan = masterController.getRacingPlanFileReader().getRacingPlan();
        racingPlan.getAllPositions().forEach(p -> {
            if (p.getStatus().equals(StatusPosition.IN)) {
                canvas.getGraphicsContext2D().drawImage(track, p.getX() * 32, p.getY() * 32, 32, 32);
            }
            if (p.getStatus().equals(StatusPosition.FINISH) || p.getStatus().equals(StatusPosition.GRID)) {
                canvas.getGraphicsContext2D().drawImage(grid, p.getX() * 32, p.getY() * 32, 32, 32);
            }
        });
        setCarCanvas();
    }

    @FXML
    public void startRace() {
        startRaceButton.setDisable(true);
        this.gameLoop();
    }

    private void gameLoop() {
        while (!masterController.isFinish(masterController.getRacingPlanFileReader().getRacingPlan())) {
            try {
                DefaultPilot pilot = masterController.getReferee().pilotTurn();
                if (pilot.getType().equals(PilotType.PLAYER)) {
                    nearPositions(pilot);
                    return;
                } else {
                    masterController.setRacingVehicleMovemenet(pilot, null);
                    setCanvas();
                }
            } catch (IllegalArgumentException e) {
                LOGGER.warning(e.getMessage());
            }
        }
        finish();
    }

    private void nearPositions(DefaultPilot pilot) {
        this.positionList = masterController.getGameEngine().allNearPosition(pilot.getRacingVehicle());
        positionList.forEach(p -> canvas.getGraphicsContext2D().drawImage(nearPosition, p.getX() * 32, p.getY() * 32, 32, 32));
    }

    private void setCarCanvas() {
        masterController.getRacingPlanFileReader().getRacingPlan().getAllVehicles().forEach(c -> canvas.getGraphicsContext2D().drawImage(c.getSkin(), c.getPosition().getX() * 32, c.getPosition().getY() * 32, 32, 32));
    }

    @FXML
    public void mouseClicked(MouseEvent e) {
        if (masterController.getReferee().getIsFinish()) {
            return;
        }
        double x = e.getX();
        double y = e.getY();
        Optional<DefaultPosition> position = this.positionList.stream().filter(p -> {
            if (p.getX() * 32 < x && x < (p.getX() + 1) * 32) {
                return p.getY() * 32 < y && y < (p.getY() + 1) * 32;
            }
            return false;
        }).findFirst();
        if (position.isPresent()) {
            masterController.setRacingVehicleMovemenet(masterController.getReferee().pilotTurn(), position.get());
            setCanvas();
            this.gameLoop();
        } else {
            generateErrorAlert("Posizione selezionata non valida.");
        }
    }

    private void finish() {
        generateSuccessAlert("Partita terminata!");
    }
}
