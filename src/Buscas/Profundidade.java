/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buscas;

import Buscas.Estrutura.No;
import Buscas.Estrutura.Node;
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
    protected void buscaPriv()
    {
        Stack<No> pilha = new Stack<>();
        No aux;
        int value, i;
        List<No> listaAux;
        boolean[] vizitados = new boolean[maiorVertice + 1];
        Arrays.fill(vizitados, false);

        List<No> mElementos = getNoMAiorGrau();
        pilha.push(mElementos.get(0));//primeio nó da busca
        //possivel erro
        vizitados[mElementos.get(0).getValue()] = true;
        arv.init(new Node(mElementos.get(0).getValue()));
        
        while (!pilha.isEmpty())
        {
            aux = pilha.peek();
            value = aux.getValue();
            listaAux = buscaListaDoELemento(aux.getInfo());
            for (i = 1; i < listaAux.size() && vizitados[listaAux.get(i).getValue()]; i++){}
            if(i >= listaAux.size())//já vizitou todas as ligações do vertice
            {
                pilha.pop();
            }
            else
            {
                vizitados[listaAux.get(i).getValue()] = true;
                Node pai = arv.busca(aux.getValue());
                arv.insereFilho(pai, listaAux.get(i).getValue());
                pilha.add(listaAux.get(i));
            }
        }
    }

}
