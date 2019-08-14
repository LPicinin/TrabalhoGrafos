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
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private static JFXTextArea stxMI;
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
        gf = new Grafo(vertices, arestas, recursidade);
        stxMA = txMA;
        stxMI = txMI;
        sequencia = 0;
    }

    @FXML
    private void evtClkMouse(MouseEvent event)
    {
        if (event.getButton().equals(MouseButton.PRIMARY) && vertices.size()+1 <= 10)
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
        //return sequencia++;

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
        processaEstruturas();
    }

    @FXML
    private void evtDigrafo(MouseEvent event)
    {
        grafo = false;
        recalcularArestas();
        processaEstruturas();
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
        for (Vertice vertice : vertices) {
            System.out.println(vertice.getText()+": "+vertice.getID());
        }
        System.out.println("------------------");
        try
        {
            int[][] ma = gf.getMatrizAdjacencia();
            //System.out.println(Arrays.deepToString(ma));
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

            /////////////////////////////////////////////////
            int[][] mi = gf.getMatrizIncidencia();
            List<String> lr2 = gf.getListaRotulosMI();
            if(arestas.size() > 0)
            {
                saidaMA = "[-]\t"+lr2.toString().replace("[", "").replace("]", "").replaceAll(",", "")+"\n";
                for (int i = 0; i < mi.length; i++) {
                    saidaMA += "["+lr.get(i)+"]\t  ";
                    for (int j = 0; j < mi[0].length; j++) 
                    {
                        saidaMA += mi[i][j]+"\t";
                    }
                    saidaMA+="\n";
                }
                stxMI.setText(saidaMA);
            }
            else
                stxMI.setText("");

        }catch(Exception ex)
        {
            System.out.println(ex.getCause());
        }
        
        
    }
    
}
