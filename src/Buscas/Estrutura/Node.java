/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buscas.Estrutura;

import java.util.ArrayList;
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

    public Node(int info)
    {
        this.info = info;
        filhos = new ArrayList<>();
        premium = new Integer[3];
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

    public Node getPai() {
        return pai;
    }

    public void setPai(Node pai) {
        this.pai = pai;
    }

    public Integer getPremium(int index) {
        return premium[index];
    }

    public void setPremium(Integer premium, int index) {
        this.premium[index] = premium;
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

}
