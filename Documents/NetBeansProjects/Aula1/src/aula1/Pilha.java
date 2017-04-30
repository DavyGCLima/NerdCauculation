/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula1;

import java.util.Iterator;

/**
 *
 * @author Davy-san
 */
public class Pilha<Item> implements Iterable<Item>{ 
    private Nodo primeiro = new Nodo();
    private Nodo ultimo = new Nodo();
    private int tamanho = 0;
    
    public class Nodo{
        Item item;
        Nodo proximo;
    }
    
    public boolean isEMpty() {
        return tamanho == 0;
    }
    
    public int size() {
        return tamanho;
    }
    
    
    public void push(Item item) {
        Nodo antigoUltimo = ultimo;
        ultimo = new Nodo();
        ultimo.item = item;
        
        if(isEMpty())
            primeiro = ultimo;
        else
            antigoUltimo.proximo = ultimo;
        
        tamanho++;
    }
    
    public Item pop(){
        Item item  = primeiro.item;
        primeiro = primeiro.proximo;
        tamanho--;
        if(isEMpty())
            ultimo = null;
        return item;
    }
   
    public class PilhaIterator implements Iterator<Item>{
        private Nodo primeiroNo = primeiro;
        private int N = tamanho;

        @Override
        public boolean hasNext() {
            return N > 0;
        }

        @Override
        public Item next() {
            Nodo exPrimeiro = primeiroNo;
            primeiroNo = primeiroNo.proximo;
            N--;
            return exPrimeiro.item;
        }        
    }
    
    @Override
    public Iterator<Item> iterator() {
        return new PilhaIterator();
    }
}
