/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import javafx.scene.shape.CubicCurve;
import trabalhografos.TelaPrincipalController;

/**
 *
 * @author Aluno
 */
public class Recursao extends CubicCurve
{
    private Rotulo rotulo;
    private Vertice v;
    
    public Recursao(Vertice v, String rot) 
    {
        rotulo = new Rotulo(rot);
        this.v = v;
        calcPos();
        
    }
    public void dispose()
    {
        TelaPrincipalController.painelAcessivel.getChildren().remove(this);
        TelaPrincipalController.recursidade.remove(this);
    }

    public void calcPos() 
    {
        double x = v.getLayoutX();
        double y = v.getLayoutY();
        double w = v.getWidth();
        double h = v.getHeight();
        
        
        y += h;
        
        setStartX(x+w/h);
        setStartY(y);
        
        setEndX(x+w);
        setEndY(y);
        
        setControlX1(y+5);
        setControlX2(y+5);
        
        setControlY1(x-30);
        setControlY2(x-30);
        
    }
}
