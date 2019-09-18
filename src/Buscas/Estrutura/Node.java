/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buscas.Estrutura;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class Node
{

    private int info;
    private List<Node> filhos;

    public Node(int info)
    {
        this.info = info;
        filhos = new ArrayList<>();
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

}
