/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package it.unicam.cs.pa2021.f1;

import it.unicam.cs.pa2021.f1.view.ConsoleView;
import it.unicam.cs.pa2021.f1.view.JavaFXView;
import it.unicam.cs.pa2021.f1.view.View;

import java.io.IOException;

public class App  {

    private final View view;

    public App (View view) {
        this.view = view;
    }

    private static App createApp(String[] args){
        return new App(new JavaFXView());
    }

    private void startApp () throws IOException {
        view.open();
    }

    public static void main(String[] args) throws IOException {
       createApp(args).startApp();
    }

}
