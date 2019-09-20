/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buscas;

import static Buscas.Busca.maiorVertice;
import Buscas.Estrutura.No;
import Buscas.Estrutura.Node;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Luish
 */
public class Largura extends Busca
{

    public Largura()
    {
    }

    public Largura(List<List<String>> la)
    {
        super(la);
    }
    @Override
    protected void buscaPriv()
    {
        Queue<No> fila = new LinkedList<>();
        No aux;
        int value;
        List<No> listaAux;
        boolean[] vizitados = new boolean[maiorVertice + 1];
        boolean caminho_sem_fim;
        Arrays.fill(vizitados, false);
        
        List<No> mElementos = getNoMAiorGrau();
        fila.add(mElementos.get(0));
        //possivel erro
        vizitados[mElementos.get(0).getValue()] = true;
        arv.init(new Node(mElementos.get(0).getValue()));
        
        while (!fila.isEmpty())
        {
            aux = fila.peek();
            value = aux.getValue();
            listaAux = buscaListaDoELemento(aux.getInfo());
            caminho_sem_fim = true;
            for (int i = 0; i < listaAux.size(); i++) 
            {
                if(!vizitados[listaAux.get(i).getValue()])
                {
                    caminho_sem_fim = false;
                    vizitados[listaAux.get(i).getValue()] = true;
                    
                    Node pai = arv.busca(aux.getValue());
                    arv.insereFilho(pai, listaAux.get(i).getValue());
                    
                    fila.add(listaAux.get(i));
                }
            }
            if(caminho_sem_fim)
            {
                fila.remove();
            }
        }
    }
    
}
