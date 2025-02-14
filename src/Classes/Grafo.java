/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import trabalhografos.TelaPrincipalController;

/**
 *
 * @author Luish
 */
public class Grafo
{

    private List<Vertice> vertices;
    private List<Aresta> arestas;
    private List<Recursao> recursoes;

    public Grafo(List<Vertice> vertices, List<Aresta> arestas, List<Recursao> recursoes)
    {
        this.vertices = vertices;
        this.arestas = arestas;
        this.recursoes = recursoes;
    }

    public int[][] getMatrizAdjacencia()
    {
        Vertice or;
        Vertice dst;
        int[][] ma = new int[vertices.size()][vertices.size()];
        int[] vaux;
        if (!isMultAresta())
        {
            if (vertices.size() > 1)
            {
                int c = 0;
                vaux = new int[vertices.get(vertices.size() - 1).getID() + 1];
                for (Vertice vertice : vertices)
                {
                    vaux[vertice.getID()] = c++;
                }
                for (Aresta a : arestas)
                {
                    or = a.getV1();
                    dst = a.getV2();
                    ma[vaux[or.getID()]][vaux[dst.getID()]] = a.getValor();
                    if (TelaPrincipalController.isGrafo())
                    {
                        ma[vaux[dst.getID()]][vaux[or.getID()]] = a.getValor();
                    }
                }
                for (Recursao recursoe : recursoes)
                {
                    ma[vaux[recursoe.getV().getID()]][vaux[recursoe.getV().getID()]] = recursoe.getValor();
                }
            }
        }

        return ma;
    }

    public int[][] getMatrizIncidencia()
    {

        Vertice or;
        Vertice dst;
        Aresta aaux;
        int[][] ma = new int[vertices.size()][arestas.size() + recursoes.size()];

        int i;
        int[] vaux;
        if (vertices.size() > 1)
        {
            int c = 0;
            vaux = new int[vertices.get(vertices.size() - 1).getID() + 1];
            for (Vertice vertice : vertices)
            {
                vaux[vertice.getID()] = c++;
            }
            for (i = 0; i < arestas.size(); i++)
            {
                aaux = arestas.get(i);
                or = aaux.getV1();
                dst = aaux.getV2();
                if (!TelaPrincipalController.isGrafo())
                {
                    ma[vaux[or.getID()]][i] = -aaux.getValor();
                } else
                {
                    ma[vaux[or.getID()]][i] = aaux.getValor();
                }
                ma[vaux[dst.getID()]][i] = aaux.getValor();
                /*
            for (int j = 0; j < vertices.size(); j++) 
            {
              if(vertices == or || vertices == dst)
              {
                  ma[i][j] = a.getValor();
              }
            }*/
            }
            for (Recursao recursoe : recursoes)
            {
                ma[recursoe.getV().getID()][i++] = recursoe.getValor();
            }
        }
        return ma;
    }

    public List<String> getListaRotulos()
    {
        List<String> la = new ArrayList<>();
        for (Vertice vertice : vertices)
        {
            la.add(new String(Character.toChars(vertice.getID() + 65)));
            //la.add(Integer.toString(vertice.getID()));
        }
        return la;
    }

    public List<String> getListaRotulosMI()
    {
        List<String> lrmi = new ArrayList<>();
        for (Aresta aresta : arestas)
        {
            lrmi.add(aresta.getV1().getText() + aresta.getV2().getText());
        }
        for (Recursao recursoe : recursoes)
        {
            lrmi.add(recursoe.getV().getText() + recursoe.getV().getText());
        }
        return lrmi;
    }

    public List<List<String>> getListaAdjacencia()
    {
        List<List<String>> ll = new ArrayList<>(vertices.size());
        List<String> linha;
        Vertice vaux;
        for (Vertice ver : vertices)
        {
            linha = new ArrayList<String>();
            if (!TelaPrincipalController.isGrafo())//considera apenas a ida
            {
                for (Aresta ar : arestas)
                {
                    if (ver.getID() == ar.getV1().getID())
                    {
                        linha.add(ar.getV2().getText() + "(" + ar.getValor() + ")");
                    }
                }
            } else
            {
                for (Aresta ar : arestas)
                {
                    vaux = (ver.getID() == ar.getV1().getID())
                            ? ar.getV2()
                            : (ver.getID() == ar.getV2().getID())
                            ? ar.getV1()
                            : null;
                    if (vaux != null)
                    {
                        linha.add(vaux.getText() + "(" + ar.getValor() + ")");
                    }
                }
            }
            for (Recursao recursoe : recursoes)
            {
                if (recursoe.getV().getID() == ver.getID())
                {
                    linha.add(ver.getText() + "(" + recursoe.getValor() + ")");
                }
            }

            linha.sort((o1, o2)
                    ->
            {
                return o1.compareToIgnoreCase(o2);
            });
            linha.add(0, ver.getText());
            ll.add(linha);
        }
        return ll;
    }

    private boolean isMultAresta()
    {
        int c = 0;
        int s;
        boolean flag = true;

        for (int i = 0; i < arestas.size() && flag; i++)
        {
            s = arestas.get(i).getSomaVertices();
            c = 0;
            for (Aresta aresta2 : arestas)
            {
                if (s == aresta2.getSomaVertices())
                {
                    c++;
                }
            }
            flag = c > 1;
        }
        return flag;
    }

    public boolean gfsimplesAdjacencia(int[][] mat)
    {
        for (int i = 0; i < mat[0].length; i++)
        {
            if (mat[i][i] != 0)
            {
                return false;
            }
        }
        return true;
    }

    public boolean gfsimplesIncidencia(int[][] mat)
    {
        Vertice or, dst;
        Aresta aaux;
        for (int i = 0; i < arestas.size(); i++)
        {
            aaux = arestas.get(i);
            or = aaux.getV1();
            dst = aaux.getV2();

            if (or == dst)
            {
                return false;
            }
        }
        return true;
    }

    public boolean gfsimplesLista(List<List<String>> lista)
    {
        List<String> list;
        Aresta aaux;
        String r;
        boolean flag = true;
        int c;
        for (int i = 0; i < lista.size() && flag; i++)
        {
            list = lista.get(i);
            r = list.toString();
            c = r.length();
            r = r.replaceAll(Character.toString(r.charAt(1)), "");
            if (c == r.length() - 1)
            {
                flag = false;
            }
        }
        return flag;
    }

    public boolean gfCompletoLista(List<List<String>> lista)
    {
        List<String> list;
        Aresta aaux;
        String r;
        boolean flag = true;
        int c;
        for (int i = 0; i < lista.size() && flag; i++)
        {
            list = lista.get(i);
            r = list.toString();
            for (int j = 0; j < vertices.size() && flag; j++)
            {
                flag = r.contains(vertices.get(j).getText());
            }
        }
        return flag;
    }

    public boolean gfCompletoAdjacencia(int[][] mat)
    {
        boolean flag = true;
        for (int i = 0; i < mat.length && flag; i++)
        {
            for (int j = 0; j < mat[0].length && flag; j++)
            {
                if (i != j)
                {
                    flag = (mat[i][j] != 0);
                }
            }
        }
        return flag;
    }

    public boolean gfCompletoIncidencia(int[][] mat)
    {
        Vertice or, dst;
        Aresta aaux;
        int c;
        boolean flag = true;
        for (int i = 0; i < mat.length && flag; i++)
        {
            c = 0;
            for (int j = 0; j < mat[0].length; j++)
            {
                if (mat[i][j] != 0)
                {
                    c++;
                }
            }
            flag = c == vertices.size() - 1;
        }
        /*
        for (int i = 0; i < arestas.size(); i++)
        {
            aaux = arestas.get(i);
            or = aaux.getV1();
            dst = aaux.getV2();

            if (or == dst)
            {
                return false;
            }
        }
        return true;
         */
        return flag;
    }

    //////////////////////regular
    public Integer[] gfRegularAdjacencia(int[][] mat)
    {
        int c = 0, maiore = 0, maiorr = 0;
        boolean flag = true;
        if (mat.length > 0)
        {
            for (int i = 0; i < mat[0].length; i++)
            {
                if (mat[0][i] != 0)
                {
                    maiore++;
                }
            }
            for (int i = 0; i < mat.length; i++) 
            {
                if(mat[i][0] != 0)
                    maiorr++;
            }
        }
        for (int i = 1; i < mat.length && flag; i++)
        {
            c = 0;
            for (int j = 0; j < mat[0].length && flag; j++)
            {
                if (mat[i][j] != 0)
                {
                    c++;
                }
            }
            if (maiore != c)
            {
                flag = false;
            }
        }
        return (flag)? new Integer[]{maiore, maiorr} : null;
    }

    public Integer[] gfRegularIncidencia(int[][] mat)
    {
        int c = 0, maiore = 0, maiorr = 0;
        boolean flag = true;
        if (mat.length > 0)
        {
            for (int i = 0; i < mat[0].length; i++)
            {
                if (mat[0][i] != 0)
                {
                    maiore++;
                }
            }
            
            for (int i = 0; i < mat.length; i++)
            {
                if (mat[0][i] != 0)
                {
                    maiorr++;
                }
            }
        }
        for (int i = 1; i < mat.length && flag; i++)
        {
            c = 0;
            for (int j = 0; j < mat[0].length && flag; j++)
            {
                if (mat[i][j] != 0)
                {
                    c++;
                }
            }
            if (maiore != c)
            {
                flag = false;
            }
        }
        return (flag)? new Integer[]{maiore, maiorr} : null;
    }
    public Integer gfRegularLista(List<List<String>> lista)
    {
        boolean flag = true;
        List<String> max = null;
        if(lista.size() > 0)
            max = lista.get(0);
        for (int i = 0;i < lista.size() && flag; i++)
        {
            if(max.size() != lista.get(i).size())
                flag = false;
        }
        return (flag)? ((max != null)? max.size()-1 : 0) : null;
    }

}
