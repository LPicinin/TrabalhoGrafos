/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhografos;

import Classes.Aresta;
import Classes.Grafo;
import Classes.Recursao;
import Classes.Vertice;
import Util.Util;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
    private static int sequencia;
    private static Grafo gf;
    private static boolean grafo;//Digrafo ou grafo
    //para o controle de ligações entre 2 vertices
    public static Vertice vaux;

    //para o controle do grafo
    public static List<Vertice> vertices;
    public static List<Aresta> arestas;
    public static List<Recursao> recursidade;
    @FXML
    private Pane painel;
    public static Pane painelAcessivel;
    @FXML
    private JFXRadioButton rbGrafo;
    @FXML
    private ToggleGroup menuOP;
    @FXML
    private JFXRadioButton rbDrigrafo;
    @FXML
    private JFXTextArea txMA;
    private static JFXTextArea stxMA;
    @FXML
    private JFXTextArea txMI;
    @FXML
    private JFXTextArea txLista;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        arestas = new ArrayList<Aresta>();
        vertices = new ArrayList<Vertice>();
        recursidade = new ArrayList<Recursao>();
        painelAcessivel = painel;
        grafo = true;
        gf = new Grafo(vertices, arestas);
        stxMA = txMA;
        sequencia = 0;
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
        processaEstruturas();
    }

    //problema
    private Integer getNextNumberVertice()
    {
        /*int s = vertices.size();
        boolean flag = true;
        int i = s;
        for (; i - 1 > 0 && flag; i--)
        {
            if (vertices.get(i - 1).getID() - 1 != vertices.get(i - 2).getID())
            {
                flag = false;
            }
        }
        return (flag) ? vertices.size() : i;*/
        return sequencia++;
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

    public static void processaEstruturas() 
    {
        int[][] ma = gf.getMatrizAdjacencia();
        System.out.println(Arrays.deepToString(ma));
        List<String> lr = gf.getListaRotulos();
        String saidaMA = "[-]\t"+lr.toString().replace("[", "").replace("]", "").replaceAll(",", "")+"\n";
        for (int i = 0; i < ma.length; i++) 
        {
            saidaMA += "["+lr.get(i)+"]\t";
            for (int j = 0; j < ma[0].length; j++) 
            {
                //saidaMA += Integer.toString(ma[i][j]);
                saidaMA += ma[i][j]+" ";
            }
            saidaMA+="\n";
        }
        stxMA.setText(saidaMA);
        /*System.out.println(ma.length + " " + ma[0].length);
        for (int[] ma1 : ma) {
            Util.addLinha(stbMA, Arrays.asList(ma1));
        }*/
        /*
        ArrayList<String> l = new ArrayList<String>();
        l.add("5");
        l.add(" ");
        Util.addLinha(stbMA, l);
        l = new ArrayList<String>();
        l.add(" ");
        l.add("3");
        Util.addLinha(stbMA, l);*/
    }
    
}
