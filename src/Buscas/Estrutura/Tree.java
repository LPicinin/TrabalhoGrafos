/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buscas.Estrutura;

import java.util.Stack;

/**
 *
 * @author Aluno
 */
public class Tree
{

    private Node raiz;

    public Tree(Node raiz)
    {
        this.raiz = raiz;
    }

    public Tree()
    {
    }

    public void init(Node n)
    {
        Stack<Node> pilha = new Stack<>();
        pilha.push(raiz);
    }

    public void insereFilho(Node pai, int info)
    {
        if (pai != null)
        {
            pai.getFilhos().add(new Node(info));
        }
        else
        {
            raiz = new Node(info);
        }
    }

    public Node busca(int info)
    {
        Stack<Node> pilha = new Stack<>();
        Node aux = null, r = null;
        pilha.add(raiz);
        boolean N_achou = true;
        while (!pilha.isEmpty() && N_achou)
        {
            aux = pilha.pop();
            if (aux.getInfo() == info)
            {
                r = aux;
                N_achou = false;
            } else
            {
                for (int i = 0; i < aux.getFilhos().size(); i++)
                {
                    if (aux.getFilhos().get(i).getInfo() == info)
                    {
                        N_achou = false;
                        r = aux.getFilhos().get(i);
                    }
                }
            }
        }
        return r;
    }

    public int[][] getMatrixArticulacao(int n)
    {
        int[][] matrizA = new int[n][4];
        Stack<Node> pilha = new Stack<>();
        pilha.add(raiz);
        
        return matrizA;
    }
    private void inOrdem(Node r)
    {
        if(r != null)
        {
            for (Node filho : r.getFilhos())
            {
                inOrdem(filho);
                //executa
            }
            inOrdem(r.getFilhos().get(r.getFilhos().size()-1));
        }
    }
    
}
