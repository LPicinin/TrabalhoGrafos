/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhografos;

import Classes.Aresta;
import Classes.Recursao;
import Classes.Vertice;
import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author Aluno
 */
public class TelaPrincipalController implements Initializable
{
    private static boolean grafo;//Digrafo ou grafo
    //para o controle de ligações entre 2 vertices
    public static Vertice vaux;

    //para o controle do grafo
    public static List<Vertice> vertices;
    public static List<Aresta> arestas;
    public static List<Recursao> recursidade;
    @FXML
    private TableView<ObservableList> tbMA;
    @FXML
    private TableView<ObservableList> tbMI;
    @FXML
    private TableView<ObservableList> tbLista;
    @FXML
    private Pane painel;
    public static Pane painelAcessivel;
    @FXML
    private JFXRadioButton rbGrafo;
    @FXML
    private ToggleGroup menuOP;
    @FXML
    private JFXRadioButton rbDrigrafo;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        arestas = new ArrayList<Aresta>();
        vertices = new ArrayList<Vertice>();
        recursidade = new ArrayList<Recursao>();
        painelAcessivel = painel;
        grafo = true;
    }

    @FXML
    private void evtClkMouse(MouseEvent event)
    {
        if (event.getButton().equals(MouseButton.PRIMARY))
        {
            Vertice jb = new Vertice(getNextNumberVertice().toString());
            jb.setLayoutX(event.getSceneX() - jb.getPrefWidth() / 2 - 15);
            jb.setLayoutY(event.getSceneY() - jb.getPrefHeight() / 2 - 80);
            vertices.add(jb);
            painel.getChildren().add(jb);
        }
    }

    //problema
    private Integer getNextNumberVertice()
    {
        int s = vertices.size();
        boolean flag = true;
        int i = s;
        for (; i - 1 > 0 && flag; i--)
        {
            if (vertices.get(i - 1).getID() - 1 != vertices.get(i - 2).getID())
            {
                flag = false;
            }
        }
        return (flag) ? vertices.size() : i;
    }

    public static void recalcularArestas()
    {
        for (int i = 0; i < arestas.size(); i++)
        {
            arestas.get(i).calcPos();
        }
        for (Recursao recursao : recursidade) {
            recursao.calcPos();
        }
    }

    @FXML
    private void evtGrafo(MouseEvent event)
    {
        grafo = true;
        recalcularArestas();
    }

    @FXML
    private void evtDigrafo(MouseEvent event)
    {
        grafo = false;
        recalcularArestas();
    }

    public static boolean isGrafo()
    {
        return grafo;
    }

    public static Vertice getVaux()
    {
        return vaux;
    }

    public static List<Vertice> getVertices()
    {
        return vertices;
    }

    public static List<Aresta> getArestas()
    {
        return arestas;
    }

    public static Pane getPainelAcessivel()
    {
        return painelAcessivel;
    }
    
}
