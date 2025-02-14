/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buscas;

import Buscas.Estrutura.No;
import Buscas.Estrutura.Tree;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import javafx.scene.paint.Color;

/**
 *
 * @author Luish
 */
public abstract class Busca
{

    protected final Color[] c = new Color[]
    {
        Color.color(1, 0, 0),
        Color.color(0, 1, 0),
        Color.color(0, 0, 1),
        Color.color(0, 1, 1),
        Color.color(1, 0, 1),
        Color.color(0.5, 0, 0),
        Color.color(0, 0.5, 0),
        Color.color(0, 0, 0.5),
        Color.color(0, 0.5, 0.5),
        Color.color(0.5, 0, 0.5),
    };
    protected Color[] coresV;
    protected Tree arv;
    protected List<List<No>> eb;//estruturaBusca
    protected static int maiorVertice;
    protected int[][] matriz;

    public Busca()
    {
        arv = new Tree();

    }

    public Busca(List<List<String>> la)
    {
        coresV = new Color[eb.size() + 1];
        arv = new Tree();
        eb = converteEstrutura(la);
    }

    public final void buscar(List<List<String>> la)
    {
        
        eb = converteEstrutura(la);
        buscaPriv();
        coresV = new Color[eb.size() + 1];
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
                if (maiorVertice < c - 'A')
                {
                    maiorVertice = c - 'A';
                }
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
            if (Mlist == null || Mlist.size() < list.size())
            {
                Mlist = list;
            }
        }
        return Mlist;
    }

    protected final List<No> buscaListaDoELemento(char c)
    {
        List<No> item = null;
        for (int i = 0; i < eb.size(); i++)
        {
            if (eb.get(i).get(0).getInfo() == c)
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

    public void processaCoresBusca()
    {
        ArrayList<Color> cores = new ArrayList<>(Arrays.asList(c));
        coresV = new Color[eb.size() + 1];
        matriz = new int[eb.size()][c.length];
        for (int i = 0; i < eb.size(); i++)
        {
            for (int j = 0; j < c.length; j++)
            {
                matriz[i][j] = 1;
            }
        }
        Stack<No> pilha = new Stack<>();
        List<No> inicio = getNoMAiorGrau();
        List<No> lista_Aux;
        No aux;
        int i;
        pilha.push(inicio.get(0));
        coresV[inicio.get(0).getValue()] = c[0];
        inicio.get(0).setCor(c[0]);
        while (!pilha.isEmpty())
        {
            //cores = new ArrayList<>(Arrays.asList(c));

            aux = pilha.peek();
            lista_Aux = getlistForIndex(aux.getValue());
            for (int j = 1; j < lista_Aux.size(); j++)
            {
                lista_Aux.get(j).getCoresNegadas().add(aux.getCor());
                matriz[j][getPosCor(aux.getCor())] = 0;
            }
            for (i = 1; i < lista_Aux.size() && coresV[lista_Aux.get(i).getValue()] != null; i++)
            {
            }
            if (i < lista_Aux.size())
            {
                lista_Aux = getlistForIndex(lista_Aux.get(i).getValue());
                Color nc = getCorNaoListada(lista_Aux);
                lista_Aux.get(0).setCor(nc);
                coresV[lista_Aux.get(0).getValue()] = nc;
                pilha.push(lista_Aux.get(0));
                matriz[lista_Aux.get(0).getValue()][getPosCor(lista_Aux.get(0).getCor())] = 1;

            } else
            {
                pilha.pop();
            }

        }

        for (int j = 0; j < eb.size(); j++)
        {
            try
            {
                matriz[j][getPosCor(eb.get(j).get(0).getCor())] = 1;
            } catch (Exception ex)
            {

            }

        }

    }

    public Integer[][] getMatrizColoracao()
    {
        Integer[][] m = new Integer[this.eb.size()][coresV.length];
        String[] corNegada;
        List<Color> lcneg;
        List<Integer> indicesNegados;
        List<Color> lc2 = Arrays.asList(c);
        for (int i = 0; i < m.length; i++)
        {
            for (int j = 0; j < m[0].length; j++)
            {
                m[i][j] = 1;
            }
        }
        int index = 0;
        for (int j = 0; j < eb.size(); j++)
        {
            indicesNegados = new ArrayList<>();
            lcneg = getCoresNeg(eb.get(j).get(0).getValue());
            for (Color color : lcneg)
            {
                indicesNegados.add(lc2.indexOf(color));
            }
            for (Integer in : indicesNegados)
            {
                m[j][in] = 0;
            }
        }
        int j;
        boolean flag = true;
        for (int i = 0; i < m.length; i++)
        {
            for (j = 0; j < m[0].length && m[i][j] == 1 && flag; j++)
            {
                m[i][j] = 0;
            }
            m[i][j] = 1;
            
        }
        return m;
    }

    private List<No> getlistForIndex(int index)
    {
        return eb.get(index);
    }

    private Color getCorNaoListada(List<No> lista_Aux)
    {
        ArrayList<Color> cores = new ArrayList<>(Arrays.asList(c));
        int index;
        for (No no : lista_Aux)
        {
            if (coresV[no.getValue()] != null)
            {
                index = cores.indexOf(coresV[no.getValue()]);
                if (index >= 0)
                {
                    cores.remove(index);
                }
            }
        }
        return cores.get(0);
    }

    public Color[] getCoresV()
    {
        return coresV;
    }

    private List<Color> getCoresNeg(int j)
    {
        List<Color> corn = new ArrayList<>();
        List<No> listAux = getlistForIndex(j);
        List<No> listAux2;

        for (int i = 1; i < listAux.size(); i++)
        {
            listAux2 = getlistForIndex(listAux.get(0).getValue());
            corn.add(listAux2.get(0).getCor());
        }
        return corn;
    }

    private int getPosCor(Color nc)
    {
        int i = 0;
        for (i = 0; i < c.length && !c[i].equals(nc); i++)
        {
        }
        return i;
    }

    public int[][] getMatriz()
    {
        return matriz;
    }

    public List<Integer[]> getPremiums()
    {
        return arv.getPremiums();
    }

}
