/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.List;

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
    public Integer[][] getMatrizAdjacencia()
    {
        return null;
    }
    public Integer[][] getMatrizIncidencia()
    {
        return null;
    }
    public List<?> getListaAdjacência()
    {
        return null;
    }
}
