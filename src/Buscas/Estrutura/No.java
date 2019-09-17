/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buscas.Estrutura;

/**
 *
 * @author Luish
 */
public class No
{
    private char info;

    public No(char info)
    {
        this.info = info;
    }

    public char getInfo()
    {
        return info;
    }
    public int getValue()
    {
        return info-'a';
    }
    public void setInfo(char info)
    {
        this.info = info;
    }    
}
