package it.unicam.cs.pa2021.f1.controller;

import it.unicam.cs.pa2021.f1.model.DefaultPosition;
import it.unicam.cs.pa2021.f1.model.DefaultRacingPlan;
import it.unicam.cs.pa2021.f1.model.StatusPosition;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static java.awt.Color.*;

/**
 * Implementazione della creazione di un piano di gara ottenuto dalla lettura di un file.
 */
public class RacingPlanReader implements FileReader<DefaultRacingPlan> {

    private final List<DefaultPosition> racingPlanPositions = new LinkedList<>();
    private final int black = BLACK.getRGB();
    private final int white = WHITE.getRGB();
    private final int red = RED.getRGB();
    private DefaultRacingPlan racingPlan;

    /**
     * Legge l'immagine dal file.
     *
     * @throws IOException "if an error occurs during reading or when not able to create required ImageInputStream".
     */
    private void read(String filePath) throws IOException {
        BufferedImage image = ImageIO.read(new File(filePath));
        racingPlanPositions(image.getHeight(), image.getWidth());
        racingPlan = new DefaultRacingPlan(image.getHeight(), image.getWidth(), this.setStatusPositionByImage(image));
    }

    /**
     * Crea le posizioni del piano di gara.
     *
     * @param height altezza del piano di gara.
     * @param width larghezza del piano di gara.
     */
    private void racingPlanPositions(Integer height, Integer width) {
        if (height > 100 || width > 100)
            throw new IllegalArgumentException("L'immagine passata ha dimensioni troppo elevate (max. 100 x 100)");
        int y = height - 1;
        int x = 0;
        while (y >= 0) {
            while (x < width) {
                racingPlanPositions.add(new DefaultPosition(y, x));
                x++;
            }
            x = 0;
            y--;
        }
    }

    /**
     * Assegna uno stato alle posizioni del tracciato in base a cio' che e' stato
     * letto dal file.
     *
     * @param image il file immagine passato.
     * @return la lista di posizioni con lo stato assegnato.
     */
    private List<DefaultPosition> setStatusPositionByImage(BufferedImage image) {
        return racingPlanPositions.stream().peek(p -> {
            int rgb = image.getRGB(p.getX(), image.getHeight() - (p.getY() + 1));
            if (rgb == red) {
                p.setStatus(StatusPosition.GRID);
            } else if (rgb == white) {
                p.setStatus(StatusPosition.OUT);
            } else if (rgb == black) {
                p.setStatus(StatusPosition.IN);
            } else {
                throw new IllegalArgumentException("Colore non accettabile: " + new Color(rgb));
            }
        }).collect(Collectors.toList());
    }

    @Override
    public DefaultRacingPlan getRacingPlan() throws IllegalArgumentException {
        if(racingPlan == null) throw new IllegalArgumentException("Non e' stato impostato un circuito");
        return racingPlan;
    }

    @Override
    public void setRacingPlan(String filePath) throws IOException  {
        read(filePath);
    }
}
