/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import trabalhografos.TelaPrincipalController;

/**
 *
 * @author Luish
 */
public class Vertice extends JFXButton
{

    private int id;

    public Vertice(String text)
    {
        super(text);
        this.id = Integer.parseInt(text);
        iniciaInfos();
        initEventos();
    }

    public void initEventos()
    {
        this.setOnMouseDragged(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                setLayoutX(event.getSceneX() - getPrefWidth() / 2 - 15);
                setLayoutY(event.getSceneY() - getPrefHeight() / 2 - 80);
            }
        });
        this.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                if (event.getButton().equals(MouseButton.SECONDARY))
                {
                    System.out.println("Hum");
                    int i;
                    for (i = 0; i < TelaPrincipalController.vertices.size(); i++)
                    {
                        if (TelaPrincipalController.vertices.get(i).id == id)
                        {
                            TelaPrincipalController.vertices.remove(i);
                            TelaPrincipalController.painelAcessivel.getChildren().remove(i);
                            i = TelaPrincipalController.vertices.size();
                        }
                    }
                }
            }
        });
    }

    @Override
    public boolean equals(Object obj)
    {
        boolean f = false;
        if (obj instanceof JFXButton)
        {
            JFXButton aux = (JFXButton) obj;
            f = getText().equals(aux.getText());
        }
        return f;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 13 * hash + this.id;
        return hash;
    }

    private void iniciaInfos()
    {
        setFocusTraversable(false);
        setStyle("-fx-padding: 10;\n" +
                "-fx-background-color: gray;\n" +
                "-fx-border-radius: 30 30 30 30;"
                + "");
    }

}
