package it.unicam.cs.pa2021.f1.controller;

import it.unicam.cs.pa2021.f1.model.DefaultPosition;
import it.unicam.cs.pa2021.f1.model.DefaultRacingPlan;
import it.unicam.cs.pa2021.f1.model.DefaultRacingVehicle;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementazione di default del motore di gioco.
 */
public class DefaultGameEngine implements GameEngine<DefaultRacingVehicle, DefaultPosition, DefaultRacingPlan> {

    private final DefaultRacingPlan racingPlan;

    /**
     * Costruttore del motore di gioco.
     *
     * @param racingPlan il piano gara sul quale agisce il motore di gioco.
     */
    public DefaultGameEngine(DefaultRacingPlan racingPlan) {
        this.racingPlan = racingPlan;
    }

    @Override
    public DefaultRacingPlan getRacingPlan() {
        return this.racingPlan;
    }

    @Override
    public void moveRacingVehicle(DefaultRacingVehicle racingVehicle, DefaultPosition nextPosition) throws NullPointerException, IllegalArgumentException {
        if (racingVehicle == null || nextPosition == null)
            throw new NullPointerException("Il veicolo o la posizione inserita non e' valido");
        List<DefaultPosition> nearPositions = allNearPosition(racingVehicle);
        if (!nearPositions.contains(nextPosition))
            throw new IllegalArgumentException("La posizione " + nextPosition + " non e' una posizione accessibile");
        racingVehicle.setPosition(nextPosition);
    }

    /**
     * Si assicura che il veicolo azzeri la propria accelerazione
     * se le posizioni raggiungibili da quest'ultimo non fanno parte della pista.
     *
     * @param racingVehicle il veicolo.
     * @return le lista di posizioni raggiungibili corrette.
     */
    public List<DefaultPosition> allNearPosition(DefaultRacingVehicle racingVehicle) {
        List<DefaultPosition> positions = filteredNearPositions(racingVehicle);
        if (positions.isEmpty()) {
            racingVehicle.updateAcceleration(0, 0);
            positions = filteredNearPositions(racingVehicle);
        }
        return getCorrectPositions(positions);
    }

    /**
     * Restituisce solo le posizioni raggiungibili corrette.
     *
     * @param positions la lista di posizioni da controllare.
     * @return le lista di posizioni raggiungibili corrette.
     */
    private List<DefaultPosition> getCorrectPositions(List<DefaultPosition> positions) {
        List<DefaultPosition> correctPositions;
        correctPositions = positions.stream().filter(p -> p.getY() >= 0)
                .collect(Collectors.toList());
        if (correctPositions.isEmpty()) correctPositions = racingPlan.getFinishLine();
        return correctPositions;
    }

    /**
     * Restituisce le posizioni della pista raggiungibili da un veicolo evitando che questo possa tornare indietro.
     *
     * @param racingVehicle il veicolo per il quale vogliamo sapere le posizioni vicine che non siano dietro rispetto alla sua posizione.
     * @return le posizioni della pista raggiungibili che non sono dietro rispetto alla posizione del veicolo.
     */
    private List<DefaultPosition> filteredNearPositions(DefaultRacingVehicle racingVehicle) {
        return getNearPositions(racingVehicle).parallelStream()
                .filter(p -> p.getY() <= racingVehicle.getPosition().getY()).collect(Collectors.toList());
    }

    /**
     * Le posizioni raggiungibili dal veicolo in base alla sua accelerazione.
     *
     * @param racingVehicle il veicolo.
     * @return l'insieme delle posizioni raggiungibili.
     */
    private List<DefaultPosition> getNearPositions(DefaultRacingVehicle racingVehicle) {
        int traslateX = racingVehicle.getPosition().getX() + racingVehicle.getAcceleration().getX();
        int traslateY = racingVehicle.getPosition().getY() - racingVehicle.getAcceleration().getY();
        return Stream.of(this.center(traslateX, traslateY),
                this.above(traslateX, traslateY),
                this.aboveLeft(traslateX, traslateY),
                this.aboveRight(traslateX, traslateY),
                this.below(traslateX, traslateY),
                this.belowLeft(traslateX, traslateY),
                this.belowRight(traslateX, traslateY),
                this.left(traslateX, traslateY),
                this.right(traslateX, traslateY)).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
    }


     // Metodi che restituiscono le singole posizioni vicine alla posizione data.


    public Optional<DefaultPosition> center(int x, int y) {
        return near(x, y, 0, 0);
    }

    public Optional<DefaultPosition> above(int x, int y) {
        return near(x, y, 0, -1);
    }

    public Optional<DefaultPosition> aboveLeft(int x, int y) {
        return near(x, y, -1, -1);
    }

    public Optional<DefaultPosition> aboveRight(int x, int y) {
        return near(x, y, +1, -1);
    }

    public Optional<DefaultPosition> right(int x, int y) {
        return near(x, y, +1, 0);
    }

    public Optional<DefaultPosition> left(int x, int y) {
        return near(x, y, -1, 0);
    }

    public Optional<DefaultPosition> below(int x, int y) {
        return near(x, y, 0, +1);
    }

    public Optional<DefaultPosition> belowLeft(int x, int y) {
        return near(x, y, -1, +1);
    }

    public Optional<DefaultPosition> belowRight(int x, int y) {
        return near(x, y, +1, +1);
    }

    /**
     * Metodo che ricava una posizione generica nel tracciato in base ai dati passati.
     *
     * @param x    coordinata x dell'accelerazione.
     * @param y    coordinata y dell'accelerazione.
     * @param dirX spostamento sull'asse delle x.
     * @param dirY spostamento sull'asse delle y.
     * @return L'eventuale posizione vicina, derivata dai dati inseriti.
     */
    private Optional<DefaultPosition> near(int x, int y, int dirX, int dirY) {
        int newX = x + dirX;
        int newY = y + dirY;
        return racingPlan.getTrackPostion(newX, newY);
    }
}
