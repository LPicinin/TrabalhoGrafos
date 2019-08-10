/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhografos;

import Classes.Aresta;
import Classes.Vertice;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author Aluno
 */
public class TelaPrincipalController implements Initializable
{
    public static List<Vertice> vertices;
    public static List<Aresta> arestas;
    @FXML
    private TableView<ObservableList> tbMA;
    @FXML
    private TableView<ObservableList> tbMI;
    @FXML
    private TableView<ObservableList> tbLista;
    @FXML
    private Pane painel;
    public static Pane painelAcessivel;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        arestas = new ArrayList<Aresta>();
        vertices = new ArrayList<Vertice>();
        painelAcessivel = painel;
    }

    @FXML
    private void evtClkMouse(MouseEvent event)
    {
        Vertice jb = new Vertice(getNextNumberVertice().toString());
        jb.setLayoutX(event.getSceneX() - jb.getPrefWidth() / 2 - 15);
        jb.setLayoutY(event.getSceneY() - jb.getPrefHeight() / 2 - 80);
        
        vertices.add(jb);
        painel.getChildren().add(jb);
    }
    private Integer getNextNumberVertice()
    {
        return vertices.size()+1;
    }
}
