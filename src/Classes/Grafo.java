/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;
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
        int [][]ma = new int[vertices.size()][vertices.size()];
        
        
        for (Aresta a : arestas) 
        {
            or = a.getV1();
            dst = a.getV2();
            ma[or.getID()][dst.getID()] = a.getValor();
            if(TelaPrincipalController.isGrafo())
                ma[dst.getID()][or.getID()] = a.getValor();
        }
        for (Recursao recursoe : recursoes) {
            ma[recursoe.getV().getID()][recursoe.getV().getID()] = recursoe.getValor();
        }
        return ma;
    }
    public int[][] getMatrizIncidencia()
    {

        Vertice or;
        Vertice dst;
        Aresta aaux;
        int [][]ma = new int[vertices.size()][arestas.size()+recursoes.size()];
        
        int i;
        for(i = 0; i < arestas.size(); i++) 
        {
            aaux = arestas.get(i);
            or = aaux.getV1();
            dst = aaux.getV2();
            
            ma[or.getID()][i] = -aaux.getValor();
            ma[dst.getID()][i] = aaux.getValor();
            /*
            for (int j = 0; j < vertices.size(); j++) 
            {
              if(vertices == or || vertices == dst)
              {
                  ma[i][j] = a.getValor();
              }
            }*/
        }
        for (Recursao recursoe : recursoes) {
            ma[recursoe.getV().getID()][i++] = recursoe.getValor();
        }
        return ma;
    }
    public List<?> getListaAdjacência()
    {
        return null;
    }
    public List<String> getListaRotulos()
    {
        List<String> la = new ArrayList<>();
        for (Vertice vertice : vertices) {
            la.add(new String(Character.toChars(vertice.getID()+65)));
            //la.add(Integer.toString(vertice.getID()));
        }
        return la;
    }

    public List<String> getListaRotulosMI() {
        List<String> lrmi = new ArrayList<>();
        for (Aresta aresta : arestas) {
            lrmi.add(aresta.getV1().getText()+aresta.getV2().getText());
        }
        for (Recursao recursoe : recursoes) {
            lrmi.add(recursoe.getV().getText()+recursoe.getV().getText());
        }
        return lrmi;
    }
}
