/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buscas.Estrutura;

import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author Luish
 */
public class No
{
    private char info;
    private Color cor;
    private ArrayList<Color> coresNegadas;

    public No(char info)
    {
        this.info = info;
        coresNegadas = new ArrayList<>();
    }

    public char getInfo()
    {
        return info;
    }
    public int getValue()
    {
        return info - 'A';
    }
    public void setInfo(char info)
    {
        this.info = info;
    }  

    public ArrayList<Color> getCoresNegadas()
    {
        return coresNegadas;
    }

    public Color getCor()
    {
        return cor;
    }

    public void setCor(Color cor)
    {
        this.cor = cor;
    }
    
}
