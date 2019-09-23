/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buscas.Estrutura;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.paint.Color;

/**
 *
 * @author Aluno
 */
public class Node
{

    private int info;
    private List<Node> filhos;
    private Node pai;
    private Integer[] premium;
    private Color cor;

    public Node(int info, int c)
    {
        this.info = info;
        filhos = new ArrayList<>();
        premium = new Integer[3];
        premium[0] = c;
    }

    public int getInfo()
    {
        return info;
    }

    public void setInfo(int info)
    {
        this.info = info;
    }

    public List<Node> getFilhos()
    {
        return filhos;
    }

    public void setFilhos(List<Node> filhos)
    {
        this.filhos = filhos;
    }

    public Node getPai()
    {
        return pai;
    }

    public void setPai(Node pai)
    {
        this.pai = pai;
    }

    public Integer getPremium(int index)
    {
        return premium[index];
    }

    public void setPremium(Integer premium, int index)
    {
        this.premium[index] = premium;
    }

    public Color getCor()
    {
        return cor;
    }

    public void setCor(Color cor)
    {
        this.cor = cor;
    }

    public Integer[] getPremium()
    {
        return premium;
    }
    public boolean isFolha()
    {
        return filhos.isEmpty();
    }
    public Integer getMenor()
    {
        /*
        Integer menor = Arrays.stream(premium).min((o1, o2) ->
        {
            if (o1 != null && o2 != null)
            {
                return (o1 < o2) ? o1 : o2;
            }
            return (o1 == null) ? o2 : o1;
        }).get();
        */
        Integer menor = null;
        for (Integer n : premium)
        {
            if(menor == null || n < menor)
            {
                menor = n;
            }
        }
        return menor;
    }
}
