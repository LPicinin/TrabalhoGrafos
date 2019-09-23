/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buscas.Estrutura;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import javafx.scene.paint.Color;

/**
 *
 * @author Aluno
 */
public class Tree
{

    private Node raiz;
    private final Color[] c = new Color[]
    {
        Color.color(1, 0, 0), Color.color(0, 1, 0), Color.color(0, 0, 1), Color.color(0, 1, 1)
    };
    private Color[] cr;

    public Tree(Node raiz)
    {
        this.raiz = raiz;
    }

    public Tree()
    {
    }

    public void init(Node n)
    {
        raiz = n;
    }

    public void insereFilho(Node pai, int info, int c)
    {
        Node n;
        if (pai != null)
        {
            n = new Node(info, c);
            n.setPai(pai);
            pai.getFilhos().add(n);
        } else
        {
            raiz = new Node(info, c);
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
                    } else
                    {
                        pilha.push(aux.getFilhos().get(i));
                    }
                }

            }
        }
        return r;
    }

    public void processaPremiums()
    {
        List<Node> folhas = getFolhas();
        postOrder(raiz);
    }

    public void processaCores(int tl_vertice)
    {
        cr = new Color[tl_vertice];
        setCores(raiz);
    }

    private void setCores(Node n)
    {
        ArrayList<Color> cores = new ArrayList<>(Arrays.asList(c));
        if (n != null)
        {
            if (raiz == n)
            {
                n.setCor(c[0]);
                cr[n.getInfo()] = c[0];
                //
                n.setCor(cr[n.getInfo()]);
                //
            } else
            {
                int index = cores.indexOf(n.getPai().getCor());
                cores.remove(index);
                for (Node filho : n.getFilhos())
                {
                    if (filho.getCor() != null)
                    {
                        index = cores.indexOf(filho.getCor());
                        cores.remove(index);
                    }
                }
                if (cores.size() > 0)
                {
                    n.setCor(cores.get(0));
                    cr[n.getInfo()] = cores.get(0);
                    //
                    n.setCor(cr[n.getInfo()]);
                    //
                    char c = (char) ('A' + n.getInfo());
                } else
                {
                    char c = (char) ('A' + n.getInfo());
                }

            }
            for (Node filho : n.getFilhos())
            {
                setCores(filho);
            }
        }
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
        int menorNo;
        if (r != null)
        {
            for (Node filho : r.getFilhos())
            {
                inOrdem(filho);
            }
            if(r.getFilhos().size() > 0)
                inOrdem(r.getFilhos().get(r.getFilhos().size() - 1));
        }
    }
    private void postOrder(Node r)
    {
        int m = 11;
        if(r != null)
        {
            for (int i = r.getFilhos().size()-1; i > 0; i--)
            {
                postOrder(r.getFilhos().get(i));
                if(r.getFilhos().get(i).getMenor() < m)
                    m = r.getFilhos().get(i).getMenor();
            }
            if(r.getMenor() < m)
                m = r.getMenor();
            r.setPremium(m, 2);
            if(r.getFilhos().size() > 0)
                postOrder(r.getFilhos().get(r.getFilhos().size()-1));
        }

    }

    private List<Node> getFolhas()
    {
        List<Node> folhas = new ArrayList<>();
        Queue<Node> fila = new LinkedList<>();
        Node n;

        if (raiz != null)
        {
            fila.add(raiz);
            while (!fila.isEmpty())
            {
                n = fila.remove();

                if (n.getFilhos().isEmpty())
                {
                    folhas.add(n);
                } else
                {
                    for (Node filho : n.getFilhos())
                    {
                        fila.add(filho);
                    }
                }
            }
        }
        return folhas;
    }

    public Color[] getCr()
    {
        return cr;
    }

    public void processaLigacoesOciosas(List<List<No>> eb)
    {
        Node aux;
        Node aux2;
        Node filho;
        boolean achou;
        int  i;
        for (List<No> list : eb)
        {
            aux = busca(list.get(0).getValue());
            achou = true;
            for (i = 1; i < list.size() && achou; i++)
            {
                //achou = !(list.get(i).getValue()== aux.getInfo());
                for (int j = 0; j < aux.getFilhos().size(); j++)
                {
                    filho = aux.getFilhos().get(j);
                    achou = !(list.get(i).getValue() == filho.getInfo());
                }
            }
            if(i < list.size())
            {
                aux2 = busca(list.get(i).getValue());
                aux.setPremium(aux2.getInfo(), 1);
                aux2.setPremium(aux.getInfo(), 1);
            }/*
            else
            {
                aux.setPremium(null, 1);
            }*/
        }
    }

    public List<Integer[]> getPremiums()
    {
        List<Integer[]> prem = new ArrayList<>();
        Queue<Node> fila = new LinkedList<>();
        Node aux;
        fila.add(raiz);
        while(!fila.isEmpty())
        {
            aux = fila.remove();
            prem.add(aux.getPremium());
            for (Node filho : aux.getFilhos())
            {
                fila.add(filho);
            }
        }
        return prem;
    }

}
