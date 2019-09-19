/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buscas;

import Buscas.Estrutura.No;
import Buscas.Estrutura.Node;
import Buscas.Estrutura.Tree;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;

/**
 *
 * @author Luish
 */
public abstract class Busca
{
    protected Tree arv;
    protected List<List<No>> eb;//estruturaBusca
    protected static int maiorVertice;

    public Busca()
    {
        arv = new Tree();
    }
    
    public  final void buscar(List<List<String>> la)
    {
        eb = converteEstrutura(la);
        buscaPriv();
    }

    public static List<List<No>> converteEstrutura(List<List<String>> la)
    {
        List<List<No>> estruturaBusca = new ArrayList<>();//
        int i = 0;
        maiorVertice = 0;
        char c;
        for (List<String> list : la)
        {
            estruturaBusca.add(new ArrayList<>());
            //String []vs = (String[]) list.toArray();
            for (int j = 0; j < list.size(); j++)
            {
                c = list.get(j).charAt(0);
                estruturaBusca.get(i).add(new No(c));
                if(maiorVertice < c - 'A')
                    maiorVertice = c - 'A';
            }
            i++;
        }
        return estruturaBusca;
    }

    protected abstract void buscaPriv();
    protected final List<No> getNoMAiorGrau()
    {
        List<No> Mlist = null;
        for (List<No> list : eb)
        {
            if(Mlist == null || Mlist.size() < list.size())
                Mlist = list;
        }
        return Mlist;
    }
    protected final List<No> buscaListaDoELemento(char c)
    {
        List<No> item = null;
        for (int i = 0; i < eb.size(); i++)
        {
            if(eb.get(i).get(0).getInfo() == c)
            {
                item = eb.get(i);
                i = eb.size();
            }
        }
        return item;
    }

    public Tree getArv()
    {
        return arv;
    }

    public int[][] getMatrixArticulation()
    {
        return arv.getMatrixArticulacao(eb.size());
    }
    
    
}
