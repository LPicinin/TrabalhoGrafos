/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
    }
    
}
