package trabalhografos;

import Buscas.Busca;
import Buscas.Estrutura.Node;
import Buscas.Largura;
import Buscas.Profundidade;
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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
    @FXML
    private ImageView imgvSimples;
    private static ImageView simgvSimples;
    @FXML
    private ImageView imgvRegular;
    private static ImageView simgvRegular;
    @FXML
    private ImageView imgvCompleto;
    private static ImageView simgvCompleto;
    @FXML
    private ToggleGroup classificacaoGroup;
    @FXML
    private JFXRadioButton rbMA;
    private static JFXRadioButton srbMA;
    @FXML
    private JFXRadioButton rbMi;
    private static JFXRadioButton srbMi;
    @FXML
    private JFXRadioButton rbLista;
    private static JFXRadioButton srbLista;
    @FXML
    private Label lblKCompleto;
    private static Label slblKCompleto;
    @FXML
    private Label lblGrauRegular;
    private static Label slblGrauRegular;
    @FXML
    private JFXTextArea txArticulacao;
    @FXML
    private JFXTextArea txColoracao;
    private static JFXTextArea stxColoracao;

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

        srbMA = rbMA;
        srbMi = rbMi;
        srbLista = rbLista;

        simgvCompleto = imgvCompleto;
        simgvRegular = imgvRegular;
        simgvSimples = imgvSimples;

        stxColoracao = txColoracao;
        slblGrauRegular = lblGrauRegular;
        slblKCompleto = lblKCompleto;
        sequencia = 0;
        //FontAwesomeIcon.CUT
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

            verifica_Tipo(ma, mi, la);

            ///////////////////matriz de articulação
            //if (vertices.size() >= 6 && arestas.size() >= 8)
            Busca b = new Largura();
            try
            {
                b.buscar(la);
                b.processaCoresBusca();

                vertices.sort((t, t1) ->
                {
                    return Character.compare(t.getText().charAt(0), t1.getText().charAt(0));
                });

                Color[] cores = b.getCoresV();
                for (int i = 0; i < vertices.size(); i++)
                {
                    if (cores[i] != null)
                    {
                        vertices.get(i).setStyle("-fx-background-color: rgb(" + cores[i].getRed() * 255 + "," + cores[i].getGreen() * 255 + "," + cores[i].getBlue() * 255 + ")");
                    }
                }

            } catch (Exception ex)
            {
                System.out.println(ex.getCause());
            }
            
            String sa[] = Arrays.deepToString(b.getMatriz()).replace("[[", "").replace("]]", "").split("\\], \\[");
            saidaMA = "";
            for (int i = 0; i < sa.length; i++)
            {
                saidaMA += "[" + lr.get(i) + "]\t" + sa[i];
                saidaMA += "\n";
            }
            stxColoracao.setText(saidaMA);
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

    @FXML
    private void evtMA_Option(MouseEvent event)
    {
        verifica_Tipo(gf.getMatrizAdjacencia(), null, null);
    }

    @FXML
    private void evtMI_Option(MouseEvent event)
    {
        verifica_Tipo(null, gf.getMatrizIncidencia(), null);
    }

    @FXML
    private void evtLista_Option(MouseEvent event)
    {
        verifica_Tipo(null, null, gf.getListaAdjacencia());
    }

    private static void verifica_Tipo(int[][] ma, int[][] mi, List<List<String>> lista)
    {
        Integer[] grau;
        Integer g;
        Integer k;
        Integer s = 0;
        /*for (int i = 0; i < ma.length; i++) {
            s += Arrays.stream(ma[i]).sum();
        }*/
        if (srbMA.selectedProperty().get())
        {
            simgvSimples.setVisible(gf.gfsimplesAdjacencia(ma));
            if ((grau = gf.gfRegularAdjacencia(ma)) != null /*&& s != 0*/)
            {
                simgvRegular.setVisible(true);
                if (grau[0] != grau[1])
                {
                    if (grau[0] > 0)
                    {
                        slblGrauRegular.setText("E = " + grau[0].toString());//"G = "+grau[0].toString()+
                    } else
                    {
                        slblGrauRegular.setText("R = " + grau[1].toString());
                    }
                }
            } else
            {
                simgvRegular.setVisible(false);
                slblGrauRegular.setText("");
            }
            if (gf.gfCompletoAdjacencia(ma))
            {
                simgvCompleto.setVisible(true);
                slblKCompleto.setText("K = " + Integer.toString(vertices.size()));
            } else
            {
                simgvCompleto.setVisible(false);
                slblKCompleto.setText("");
            }

        } else if (srbMi.selectedProperty().get())
        {
            simgvSimples.setVisible(gf.gfsimplesIncidencia(mi));

            if ((grau = gf.gfRegularIncidencia(mi)) != null)
            {
                simgvRegular.setVisible(true);
                if (!Objects.equals(grau[0], grau[1]))
                {
                    if (grau[0] > 0)
                    {
                        slblGrauRegular.setText("E = " + grau[0].toString());//"G = "+grau[0].toString()+
                    } else
                    {
                        slblGrauRegular.setText("R = " + grau[1].toString());
                    }
                }
                //slblGrauRegular.setText("G = "+grau.toString());
            } else
            {
                simgvRegular.setVisible(false);
                slblGrauRegular.setText("");
            }
            if (gf.gfCompletoIncidencia(mi))
            {
                simgvCompleto.setVisible(true);
                slblKCompleto.setText("K = " + Integer.toString(vertices.size()));
            } else
            {
                simgvCompleto.setVisible(false);
                slblKCompleto.setText("");
            }

        } else
        {
            simgvSimples.setVisible(gf.gfsimplesLista(lista));

            if ((g = gf.gfRegularLista(lista)) != null)
            {
                simgvRegular.setVisible(true);
                slblGrauRegular.setText("G = " + g.toString());
            } else
            {
                simgvRegular.setVisible(false);
                slblGrauRegular.setText("");
            }
            if (gf.gfCompletoLista(lista))
            {
                simgvCompleto.setVisible(true);
                slblKCompleto.setText("K = " + Integer.toString(vertices.size()));
            } else
            {
                simgvCompleto.setVisible(false);
                slblKCompleto.setText("");
            }
        }
    }
}
