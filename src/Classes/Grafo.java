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
    
    public Grafo(List<Vertice> vertices, List<Aresta> arestas)
    {
        this.vertices = vertices;
        this.arestas = arestas;
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
        return ma;
    }
    public int[][] getMatrizIncidencia()
    {

        Vertice or;
        Vertice dst;
        Aresta aaux;
        int [][]ma = new int[vertices.size()][arestas.size()];
        
        for(int i = 0; i < arestas.size(); i++) 
        {
            aaux = arestas.get(i);
            or = aaux.getV1();
            dst = aaux.getV2();
            
            ma[or.getID()][i] = aaux.getValor();
            ma[dst.getID()][i] = -aaux.getValor();
            /*
            for (int j = 0; j < vertices.size(); j++) 
            {
              if(vertices == or || vertices == dst)
              {
                  ma[i][j] = a.getValor();
              }
            }*/
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
}
