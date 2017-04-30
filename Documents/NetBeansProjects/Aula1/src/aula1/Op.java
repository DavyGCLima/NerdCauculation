/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula1;

/**
 *
 * @author Davy-san
 */
public class Op {
    private String operador;

    public Op(String operador) {
        this.operador = operador;
    }
    
   public String op(){
       return operador;
   }
   
   public int Precedencia(){
       if(operador.equals("("))                             return 1;
       else if(operador.equals("+")||operador.equals("-"))  return 2;
       else if(operador.equals("*")||operador.equals("/"))  return 3;
       return -1; 
   }
}
