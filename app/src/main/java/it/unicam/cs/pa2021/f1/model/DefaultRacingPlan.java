package it.unicam.cs.pa2021.f1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementazione di default del piano gara.
 */
public class DefaultRacingPlan implements RacingPlan<DefaultRacingVehicle, DefaultPosition> {

    private final int height;
    private final int width;
    private final List<DefaultPosition> allPositions;
    private final List<DefaultRacingVehicle> allVehicles;
    private List<DefaultPosition> grid;

    /**
     * Costruttore di un piano di gara.
     *
     * @param height       l'altezza del piano di gara.
     * @param width        la larghezza del piano di gara.
     * @param allPositions tutte le posizioni del piano di gara.
     */
    public DefaultRacingPlan(int height, int width, List<DefaultPosition> allPositions) {
        this.height = height;
        this.width = width;
        this.allPositions = allPositions;
        this.allVehicles = new ArrayList<>();
        this.createGrid();
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public List<DefaultPosition> getAllPositions() {
        return this.allPositions;
    }

    /**
     * Restituisce il veicolo con quelle coordinate.
     *
     * @param x la coordinata x.
     * @param y la coordinata y.
     * @return la posizione con quelle coordinate, se presente.
     */
    public Optional<DefaultPosition> getPosition(int x, int y) {
        return allPositions.parallelStream().filter(p -> p.getX() == x && p.getY() == y).findFirst();
    }

    @Override
    public List<DefaultRacingVehicle> getAllVehicles() {
        return this.allVehicles;
    }

    @Override
    public Optional<DefaultRacingVehicle> isBusy(DefaultPosition position) {
        return allVehicles.parallelStream().filter(rc -> rc.getPosition().equals(position)).findFirst();
    }

    @Override
    public List<DefaultPosition> getGrid() {
        return this.grid;
    }

    /**
     * Crea la griglia di partenza del tracciato.
     */
    private void createGrid() {
        this.grid = this.allPositions.stream()
                .filter(p -> p.getStatus().equals(StatusPosition.GRID))
                .collect(Collectors.toList());
    }

    /**
     * Restituisce le posizioni della pista ossia il tracciato.
     *
     * @return le posizioni della pista ossia il tracciato.
     */
    public List<DefaultPosition> trackPositions() {
        return this.allPositions.parallelStream()
                .filter(p -> p.getStatus().equals(StatusPosition.IN))
                .collect(Collectors.toList());
    }

    public Optional<DefaultPosition> getTrackPostion(int x, int y) {
        return this.trackPositions().parallelStream().filter(p -> p.getX() == x && p.getY() == y).findFirst();
    }

    @Override
    public boolean addVehicleToGrid(DefaultRacingVehicle racingVehicle, int gridPosition) throws NullPointerException, IllegalArgumentException {
        if (racingVehicle == null)
            throw new NullPointerException("Il veicolo non puo' essere nullo");
        if (grid.size() < gridPosition || gridPosition <= 0)
            throw new IllegalArgumentException("La griglia non ha la posizione di partenza indicata");
        try {
            if (this.isBusy(this.grid.get(gridPosition - 1)).isPresent())
                throw new IllegalArgumentException("La posizione " + gridPosition + " e' gia' occupata");
            racingVehicle.setPosition(this.grid.get(gridPosition - 1));
            allVehicles.add(racingVehicle);
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * Stampa sulla console il piano di gara.
     */
    public void printRacingPlanConsole() {
        int y = this.height - 1;
        int x = 0;
        while (y >= 0) {
            while (x < width) {
                Optional<DefaultPosition> position = getPosition(x, y);
                position.ifPresent(this::drawRacingPlanPositions);
                x++;
            }
            System.out.print("\n");
            x = 0;
            y--;
        }
    }

    /**
     * Disegna le posizioni del piano di gara che verranno visualizzate sulla console.
     *
     * @param position la posizione che verra' disegnata.
     */
    private void drawRacingPlanPositions(DefaultPosition position) {
        Optional<DefaultRacingVehicle> racingVehicle = isBusy(position);
        if (racingVehicle.isPresent()) {
            System.out.print(racingVehicle.get().getId() + "  ");
            return;
        }
        switch (position.getStatus()) {
            case IN:
                System.out.print("-  ");
                break;
            case GRID:
                System.out.print("G  ");
                break;
            default:
                System.out.print("O  ");
        }
    }
}
