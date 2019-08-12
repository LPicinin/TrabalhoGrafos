/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import trabalhografos.TelaPrincipalController;

/**
 *
 * @author Luish
 */
public class Rotulo extends Label
{

    public Rotulo()
    {
    }

    public Rotulo(String text)
    {
        super(text);
        iniciaInfos();
    }

    private void iniciaInfos()
    {
        this.setOnScroll((ScrollEvent event) -> {
            int deltaY = (event.getDeltaY() > 0)? 1 : -1;
            setText(Integer.toString(Integer.parseInt(getText())+deltaY));
            TelaPrincipalController.processaEstruturas();
        });
        /*
        this.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                if(event.getButton().equals(MouseButton.MIDDLE))
                {
                    if(event.getClickCount() > 1)
                        setText(Integer.toString(Integer.parseInt(getText())-1));
                    else
                        setText(Integer.toString(Integer.parseInt(getText())+1));
                }
                    
            }
        });
        */
    }
    
    public void dispose()
    {
        ObservableList<Node> ob = TelaPrincipalController.painelAcessivel.getChildren();
        ob.remove(this);
        /*for (int i = 0; i < ob.size(); i++)
        {
            
        }*/
    }
}
