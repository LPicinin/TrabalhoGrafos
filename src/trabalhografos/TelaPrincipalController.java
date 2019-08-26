
package trabalhografos;

import Classes.Aresta;
import Classes.Grafo;
import Classes.Recursao;
import Classes.Vertice;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    private static JFXTextArea stxLista;
    @FXML
    private JFXTextField txval;
    public static JFXTextField stxval;

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
        stxval = txval;
        stxLista = txLista;
        sequencia = 0;
    }

    @FXML
    private void evtClkMouse(MouseEvent event)
    {
        if (event.getButton().equals(MouseButton.PRIMARY) && vertices.size() + 1 <= 10)
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
        return sequencia++;
    }

    public static void recalcularArestas()
    {
        for (Aresta aresta : arestas)
        {
            aresta.calcPos();
        }
        for (Recursao recursao : recursidade)
        {
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
        for (Vertice vertice : vertices)
        {
            System.out.println(vertice.getText() + ": " + vertice.getID());
        }
        System.out.println("------------------");
        try
        {
            //////////////////////////////////////////////////MA
            int[][] ma = gf.getMatrizAdjacencia();
            gf.gfRegularAdjacencia(ma);
            //System.out.println(Arrays.deepToString(ma));
            List<String> lr = gf.getListaRotulos();
            String saidaMA = "[-]\t" + lr.toString().replace("[", "").replace("]", "").replaceAll(",", "") + "\n";
            for (int i = 0; i < ma.length; i++)
            {
                saidaMA += "[" + lr.get(i) + "]\t";
                for (int j = 0; j < ma[0].length; j++)
                {
                    //saidaMA += Integer.toString(ma[i][j]);
                    saidaMA += ma[i][j] + " ";
                }
                saidaMA += "\n";
            }
            stxMA.setText(saidaMA);

            /////////////////////////////////////////////////MI
            int[][] mi = gf.getMatrizIncidencia();
            List<String> lr2 = gf.getListaRotulosMI();
            if (arestas.size() > 0)
            {
                saidaMA = "[-]\t" + lr2.toString().replace("[", "").replace("]", "").replaceAll(",", "") + "\n";
                for (int i = 0; i < mi.length; i++)
                {
                    saidaMA += "[" + lr.get(i) + "]\t  ";
                    for (int j = 0; j < mi[0].length; j++)
                    {
                        saidaMA += mi[i][j] + "\t";
                    }
                    saidaMA += "\n";
                }
                stxMI.setText(saidaMA);
            } else
            {
                stxMI.setText("");
            }

            List<List<String>> la = gf.getListaAdjacencia();
            String saidaLA = "";
            for (List<String> list : la)
            {
                saidaLA += list.toString().replace(",", "->") + "\n";
            }
            stxLista.setText(saidaLA);

        } catch (Exception ex)
        {
            System.out.println(ex.getCause());
        }

    }

    @FXML
    private void evtHelp(MouseEvent event)
    {
        try
        {
            Stage st = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("TelaHelp.fxml"));

            Scene scene = new Scene(root);

            st.setScene(scene);
            st.setTitle("Help!!!");
            st.setResizable(false);
            //st.initStyle(StageStyle.DECORATED);
            st.show();
        } catch (IOException ex)
        {
            System.out.println(ex.getCause());
        }
    }

}
