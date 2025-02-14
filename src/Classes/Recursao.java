/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import javafx.scene.input.MouseButton;
import javafx.scene.paint.Paint;
import javafx.scene.shape.QuadCurve;
import trabalhografos.TelaPrincipalController;

/**
 *
 * @author Aluno
 */
public class Recursao extends QuadCurve
{
    private Rotulo rotulo;
    private Vertice v;
    
    public Recursao(Vertice v, String rot)
    {
        rotulo = new Rotulo(rot);
        this.v = v;
        setStartX(-60);
        setStartY(-70);
        
        setEndX(-30);
        setEndY(-70);
        
        setControlX(-45);
        setControlY(-24);
        setFill(Paint.valueOf("#323232"));
        setStroke(Paint.valueOf("#ffffff"));
        initEventos();
        calcPos();
        
    }

    public Rotulo getRotulo() {
        return rotulo;
    }
    
    public int getValor()
    {
        return Integer.parseInt(rotulo.getText());
    }

    public Vertice getV() {
        return v;
    }
    
    
    public void dispose()
    {
        rotulo.dispose();
        TelaPrincipalController.painelAcessivel.getChildren().remove(this);
        TelaPrincipalController.recursidade.remove(this);
        TelaPrincipalController.processaEstruturas();
    }

    public void calcPos() 
    {
        double x = v.getLayoutX();
        double y = v.getLayoutY();
        double w = v.getWidth();
        double h = v.getHeight();
        
        setLayoutX(x+60);
        setLayoutY(y+108);
        
        rotulo.setLayoutX(x+w/2-3);
        rotulo.setLayoutY(y+60);
    }

    private void initEventos()
    {
        this.setOnMouseClicked((event) ->
        {
            if(event.getButton() == MouseButton.SECONDARY)
            {
                dispose();
            }
        });
    }
}
