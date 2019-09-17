/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buscas;

import Buscas.Estrutura.No;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luish
 */
public abstract class Busca
{
    protected List<List<No>> eb;//estruturaBusca
    protected static int maiorVertice;
    
    public  final String buscar(List<List<String>> la)
    {
        eb = converteEstrutura(la);
        return buscaPriv();
    }

    private static List<List<No>> converteEstrutura(List<List<String>> la)
    {
        List<List<No>> estruturaBusca = new ArrayList<>();//
        int i = 0;
        maiorVertice = 0;
        char c;
        for (List<String> list : la)
        {
            estruturaBusca.add(new ArrayList<>());
            String []vs = (String[]) list.toArray();
            for (int j = 1; j < vs.length; j++)
            {
                c = vs[j].charAt(0);
                estruturaBusca.get(i).add(new No(c));
                if(maiorVertice < c - 'a')
                    maiorVertice = c - 'a';
            }
            i++;
        }
        return estruturaBusca;
    }

    protected abstract String buscaPriv();
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
}
