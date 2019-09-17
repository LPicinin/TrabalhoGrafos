/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buscas;

import Buscas.Estrutura.No;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Luish
 */
public class Profundidade extends Busca
{

    @Override
    protected String buscaPriv()
    {
        Stack<No> pilha = new Stack<>();
        No aux;
        int value, i;
        boolean caminho_sem_fim = true;
        List<No> listaAux;
        boolean[] vizitados = new boolean[maiorVertice + 1];
        Arrays.fill(vizitados, false);

        List<No> mElementos = getNoMAiorGrau();
        pilha.push(mElementos.get(0));//primeio nó da busca

        while (!pilha.isEmpty())
        {
            aux = pilha.peek();
            value = aux.getValue();
            caminho_sem_fim = true;
            listaAux = buscaListaDoELemento(aux.getInfo());
            for (i = 1; i < listaAux.size() && vizitados[listaAux.get(i).getValue()]; i++)
            {
            }
            if(vizitados[listaAux.get(i).getValue()])//já vizitou todas as ligações do vertice
            {
                //???
            }
            else
            {
                vizitados[listaAux.get(i).getValue()] = true;
                pilha.add(listaAux.get(i));
            }
        }
        return null;
    }

}
