/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.Node;
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
    public Aresta()
    {
    }

    public Aresta(String rotulo, Vertice v1, Vertice v2)
    {
        this.rotulo = new Rotulo(rotulo);
        this.v1 = v1;
        this.v2 = v2;
        cabeca = new Circle(3,  Paint.valueOf("ffffff"));//Circle(v2.getLayoutX(), v2.getLayoutY(), 3, Paint.valueOf("ffffff"));
        calcPos();
    }

    public Rotulo getRotulo()
    {
        return rotulo;
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

    public void calcPos()
    {
        setStartX(v1.getLayoutX());
        setStartY(v1.getLayoutY());
        
        setEndX(v2.getLayoutX());
        setEndY(v2.getLayoutY());
        
        rotulo.setLayoutX((v1.getLayoutX() + v2.getLayoutX())/2);
        rotulo.setLayoutY((v1.getLayoutY() + v2.getLayoutY())/2);
        
        cabeca.setLayoutX(getEndX());
        cabeca.setLayoutY(getEndY());
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
}
