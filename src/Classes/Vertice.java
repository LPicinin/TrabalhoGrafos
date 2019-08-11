/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import com.jfoenix.controls.JFXButton;
import java.util.List;
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
                if (event.getButton().equals(MouseButton.PRIMARY))
                {
                    System.out.println("Drag Detectado ID:" + Integer.toString(id));
                    setLayoutX(event.getSceneX() - getPrefWidth() / 2 - 15);
                    setLayoutY(event.getSceneY() - getPrefHeight() / 2 - 80);

                    TelaPrincipalController.recalcularArestas();
                }

            }
        });
        this.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                System.out.println("Click Detectado ID:" + Integer.toString(id));
                if (event.getButton().equals(MouseButton.MIDDLE))
                {
                    if (TelaPrincipalController.vaux == null)
                    {
                        TelaPrincipalController.vaux = buscaId(id);
                    } else
                    {
                        Aresta ar = new Aresta("1", TelaPrincipalController.vaux, buscaId(id));
                        TelaPrincipalController.arestas.add(ar);
                        TelaPrincipalController.painelAcessivel.getChildren().add(ar);
                        TelaPrincipalController.painelAcessivel.getChildren().add(ar.getRotulo());
                        TelaPrincipalController.vaux = null;
                    }
                }
                if (event.getButton().equals(MouseButton.SECONDARY))
                {
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

    private Vertice buscaId(int id)
    {
        List<Vertice> vts = TelaPrincipalController.vertices;
        int i;
        for (i = 0; i < vts.size() && vts.get(i).getID() != id; i++)
        {
        }
        return vts.get(i);
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
        setStyle("-fx-padding: 10;\n"
                + "-fx-background-color: gray;\n"
                + "-fx-border-radius: 30 30 30 30;"
                + "");
    }

    public int getID()
    {
        return id;
    }

    public void setID(int id)
    {
        this.id = id;
    }

}
