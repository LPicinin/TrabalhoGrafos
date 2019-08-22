/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import com.jfoenix.controls.JFXButton;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.event.EventHandler;
import javafx.scene.input.*;
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

        //super(text);
        super(new String(Character.toChars(Integer.parseInt(text) + 65)));
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
                    //System.out.println("Drag Detectado ID:" + Integer.toString(id));
                    setLayoutX(event.getSceneX() - getPrefWidth() / 2 - 15);
                    setLayoutY(event.getSceneY() - getPrefHeight() / 2 - 80);

                    TelaPrincipalController.recalcularArestas();
                    TelaPrincipalController.processaEstruturas();
                }

            }
        });
        this.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                if (event.getButton().equals(MouseButton.MIDDLE))
                {
                    //System.out.println("Click ligação ID:" + Integer.toString(id));
                    if (TelaPrincipalController.vaux == null)
                    {
                        TelaPrincipalController.vaux = buscaId(id);
                    } else if (TelaPrincipalController.vaux.getID() != getID())//não é recursao
                    {
                        Aresta ar = new Aresta("1", TelaPrincipalController.vaux, buscaId(id));
                        //Arrow seta = new Arrow(TelaPrincipalController.vaux.getLayoutX(), TelaPrincipalController.vaux.getLayoutY(), getLayoutX(), getLayoutY(), 0);
                        //TelaPrincipalController.painelAcessivel.getChildren().add(seta);
                        int index = arestaJaCriada(ar);
                        if (index >= 0)
                        {
                            TelaPrincipalController.arestas.get(index).setDeslocamentoParaEvitarSobreposição(20);
                            ar.setDeslocamentoParaEvitarSobreposição(-20);
                        }
                        if (index >= -1)
                        {
                            TelaPrincipalController.arestas.add(ar);
                            TelaPrincipalController.painelAcessivel.getChildren().add(ar);
                            TelaPrincipalController.painelAcessivel.getChildren().add(ar.getRotulo());
                            TelaPrincipalController.painelAcessivel.getChildren().add(ar.getCabeca());
                            TelaPrincipalController.vaux = null;
                        } else
                        {
                            System.out.println("Aresta já existe");
                        }
                    } else//recursao
                    {
                        Recursao rec = new Recursao(TelaPrincipalController.vaux, "1");
                        TelaPrincipalController.recursidade.add(rec);
                        TelaPrincipalController.painelAcessivel.getChildren().add(rec);
                        TelaPrincipalController.painelAcessivel.getChildren().add(rec.getRotulo());
                        TelaPrincipalController.vaux = null;
                    }
                    TelaPrincipalController.processaEstruturas();
                }
                if (event.getButton().equals(MouseButton.SECONDARY))
                {
                    //System.out.println("Click exclusão ID:" + Integer.toString(id));
                    dispose();
                    TelaPrincipalController.processaEstruturas();
                    /*
                    System.out.println(TelaPrincipalController.vertices.indexOf(this));
                    TelaPrincipalController.vertices.remove(this);
                    TelaPrincipalController.painelAcessivel.getChildren().remove(this);
                     */
 /*
                    int i;
                    for (i = 0; i < TelaPrincipalController.vertices.size(); i++)
                    {
                        if (TelaPrincipalController.vertices.get(i).id == id)
                        {
                            TelaPrincipalController.vertices.remove(i);
                            TelaPrincipalController.painelAcessivel.getChildren().remove(i);
                            i = TelaPrincipalController.vertices.size();
                        }
                    }*/
                }
            }

            private int arestaJaCriada(Aresta ar)
            {
                int index = -1;
                boolean flag = true;
                List<Aresta> ars = TelaPrincipalController.arestas;
                for (int i = 0; i < ars.size() && flag; i++)
                {
                    if (ars.get(i).getV1() != ar.getV1() || ars.get(i).getV2() != ar.getV2())
                    {
                        if (ar.getSomaVertices() == ars.get(i).getSomaVertices())
                        {
                            index = i;
                            flag = false;
                        }
                    } else
                    {
                        flag = false;
                        index = -2;
                    }
                }
                return index;
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
        if (obj instanceof Vertice)
        {
            Vertice aux = (Vertice) obj;
            f = this.id == aux.id;
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
                + "-fx-background-radius: 50;");

    }

    public int getID()
    {
        return id;
    }

    public void setID(int id)
    {
        this.id = id;
        //setText(new String(Character.toChars(this.id+65)));
    }

    public void dispose()
    {
        Vertice v;
        int index = TelaPrincipalController.vertices.size();
        for (int i = 0; i < TelaPrincipalController.vertices.size(); i++)
        {
            v = TelaPrincipalController.vertices.get(i);
            if (v.getID() == getID())
            {
                index = TelaPrincipalController.vertices.indexOf(v);
                TelaPrincipalController.vertices.remove(v);
            }
        }
        ObservableList<Node> obAux = TelaPrincipalController.painelAcessivel.getChildren();
        for (int i = 0; i < obAux.size(); i++)
        {
            Node vc = obAux.get(i);
            if (vc instanceof Vertice)
            {
                if (((Vertice) vc).getID() == getID())
                {
                    obAux.remove(vc);
                }
            }
        }

        List<Aresta> arestas = TelaPrincipalController.arestas;
        List<Aresta> MarcadosParaRemocao = new ArrayList<>();
        for (Aresta aresta : arestas)
        {
            if (aresta.getV1().getID() == this.id || aresta.getV2().getID() == this.id)
            {
                MarcadosParaRemocao.add(aresta);
            }
        }
        for (Aresta aresta : MarcadosParaRemocao)
        {
            aresta.dispose();
        }
        List<Recursao> recMarc = new ArrayList<>();
        for (Recursao r : TelaPrincipalController.recursidade)
        {
            if (r.getV().getID() == id)
            {
                recMarc.add(r);
            }
        }
        for (Recursao recursao : recMarc)
        {
            recursao.dispose();
        }
        /*
        for (; index < TelaPrincipalController.vertices.size(); index++)
        {
            TelaPrincipalController.vertices.get(index).setID(TelaPrincipalController.vertices.get(index).getID() - 1);
        }
         */
    }
}
