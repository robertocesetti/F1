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
public class FileReader implements RacingPlanReader<Integer> {

    private final List<DefaultPosition> trackPositions = new LinkedList<>();

    private DefaultRacingPlan racingPlan;

    private final int black = BLACK.getRGB();

    private final int white = WHITE.getRGB();

    private final int red = RED.getRGB();

    /**
     * Costruttore del lettore del file passato.
     */
    public FileReader() {
        try {
            // Salvataggio dell'immagine dal file
            BufferedImage image = ImageIO.read(new File("D:\\Workspace Eclipse\\F1\\app\\src\\main\\resources\\RacingPlan.png"));
            RacingPlanPositions(image.getHeight(), image.getWidth());
            racingPlan = new DefaultRacingPlan(image.getHeight(),image.getWidth(), this.setStatusPositionByImage(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void RacingPlanPositions(Integer height, Integer width) {
        if(height > 100 || width > 100) throw new IllegalArgumentException("L'immagine passata ha dimensioni troppo elevate (max. 100 x 100)");
        int y = height - 1;
        int x = 0;
        while (y >= 0) {
            while (x < width) {
                trackPositions.add(new DefaultPosition(y, x));
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
    public List<DefaultPosition> setStatusPositionByImage (BufferedImage image){
        return trackPositions.stream().peek(p -> {
            int rgb = image.getRGB(p.getX(), image.getHeight() - (p.getY() + 1));
            if(rgb == red){
                p.setStatus(StatusPosition.GRID);
            } else if(rgb == white) {
                p.setStatus(StatusPosition.OUT);
            } else if(rgb == black){
                p.setStatus(StatusPosition.IN);
            } else {
                throw new IllegalArgumentException("Colore non accettabile: " + new Color(rgb));
            }
        }).collect(Collectors.toList());
    }

    /**
     * Restituisce il piano di gara creato.
     *
     * @return il piano di gara creato.
     */
    public DefaultRacingPlan getRacingPlan() {
        return racingPlan;
    }

}
