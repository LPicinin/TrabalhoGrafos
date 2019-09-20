/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import trabalhografos.TelaPrincipalController;

/**
 *
 * @author Luish
 */
public class Aresta extends Line
{

    private Circle cabeca;
    private Rotulo rotulo;
    private Vertice v1;
    private Vertice v2;

    //processamento de tela
    private double v1x;
    private double v1y;
    private double v2x;
    private double v2y;
    private int deslocamentoParaEvitarSobreposição = 0;

    public Aresta()
    {
    }

    public Aresta(String rotulo, Vertice v1, Vertice v2)
    {
        this.rotulo = new Rotulo(rotulo);
        this.v1 = v1;
        this.v2 = v2;
        cabeca = new Circle(3, Paint.valueOf("ffffff"));//Circle(v2.getLayoutX(), v2.getLayoutY(), 3, Paint.valueOf("ffffff"));
        setStroke(Paint.valueOf("#ffffff"));
        calcPos();
        iniciaEventos();
    }

    public Rotulo getRotulo()
    {
        return rotulo;
    }

    public int getValor()
    {
        return Integer.parseInt(rotulo.getText());
    }

    public void setRotulo(Rotulo rotulo)
    {
        this.rotulo = rotulo;
    }

    public Vertice getV1()
    {
        return v1;
    }

    public void setV1(Vertice v1)
    {
        this.v1 = v1;
    }

    public Vertice getV2()
    {
        return v2;
    }

    public void setV2(Vertice v2)
    {
        this.v2 = v2;
    }

    public Circle getCabeca()
    {
        return cabeca;
    }

    public void setCabeca(Circle cabeca)
    {
        this.cabeca = cabeca;
    }

    public int getDeslocamentoParaEvitarSobreposição()
    {
        return deslocamentoParaEvitarSobreposição;
    }

    public void setDeslocamentoParaEvitarSobreposição(int deslocamentoParaEvitarSobreposição)
    {
        this.deslocamentoParaEvitarSobreposição = deslocamentoParaEvitarSobreposição;
    }

    public void calcPos()
    {
        v1x = v1.getLayoutX() + v1.getWidth() / 2;
        v1y = v1.getLayoutY() + v1.getHeight() / 2;
        v2x = v2.getLayoutX() + v2.getWidth() / 2;
        v2y = v2.getLayoutY() + v2.getHeight() / 2;

        //processaCoordenadas();
        /*if (v1x > v2x)
        {
            v1x -= v1.getWidth();
            v2x += v2.getWidth();
        }*/
        setStartX(v1x);
        setStartY(v1y + deslocamentoParaEvitarSobreposição);

        setEndX(v2x);
        setEndY(v2y + deslocamentoParaEvitarSobreposição);

        rotulo.setLayoutX((v1x + v2x) / 2);
        rotulo.setLayoutY((v1y + v2y) / 2 + deslocamentoParaEvitarSobreposição);

        cabeca.setLayoutX(getEndX() - (getEndX() - getStartX()) / 8);
        cabeca.setLayoutY(getEndY() - (getEndY() - getStartY()) / 8);
        cabeca.setVisible(!TelaPrincipalController.isGrafo());
    }

    public void dispose()
    {
        ObservableList<Node> ob = TelaPrincipalController.painelAcessivel.getChildren();
        List<Aresta> ar = TelaPrincipalController.arestas;
        rotulo.dispose();
        ob.remove(cabeca);
        ob.remove(this);
        ar.remove(this);
    }

    /**
     *
     * @return soma dos IDs de ambos os vértices
     */
    public int getSomaVertices()
    {
        return v1.getID() + v2.getID();
    }

    private void iniciaEventos()
    {
        this.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                if (event.getButton() == MouseButton.SECONDARY)
                {
                    dispose();
                    TelaPrincipalController.processaEstruturas();
                }
            }

        });
    }

    private void processaCoordenadas()
    {
        int dirh = (v1x > v2x) ? 1 : -1;
        int dirv = (v1y > v2y) ? 1 : -1;
        int angulo = processaAngulo();
        v1x += v1.getWidth() / 2 * (-dirh);
        v2x += v2.getWidth() / 2 * dirh;

        v1y += v1.getHeight() / 2 * (-dirv);
        v2y += v2.getHeight() / 2 * dirv;

    }

    private int processaAngulo()
    {
        double theta = Math.atan((v2y - v1y) / (v2x - v1x));
        theta *= 180 / Math.PI;
        return (int) theta;
    }
}
