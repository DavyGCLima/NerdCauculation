/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Davy-san
 */
public class Aula1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner dados = new Scanner(System.in);
        int escolha;
        double ini;
        double fim;
        String exp;
        String info;
        int sens = 0;
        boolean valida = true;
        
        do{
            System.out.println("Decida a operação: 0 para montar gráfico, 1 para calcular limite, 2 para calcular qualquer expressão");
            escolha = dados.nextInt();
            switch (escolha) {
                case 0:
                    System.out.println("Informe o inicio do dominio");
                    ini = dados.nextDouble();
                    System.out.println("Informe o fim do dominio");
                    fim = dados.nextDouble();
                    System.out.println("Informe a quantidade de casas decimais desejadas: ");
                    sens = dados.nextInt();
                    System.out.println("Informe a expressão: ");
                    exp = dados.next();
                    double[] x = montaDominio(ini, fim, sens);
                    calcula(x, exp);                    
                    valida = false;
                    break;
                case 1:
                    System.out.println("Informe a expressão: ");
                    exp = dados.next();
                    System.out.println("Informe o ponto limite: ");
                    info = dados.next();
                    try{
                        calculaLimite(exp,info);
                    }catch(Exception ex){
                        System.out.println("Erro: "+ex.getMessage()+"Tentativade aplcação do teorema do confronto");
                        
                    }
                    valida = false;
                    break;
                case 2:
                    System.out.println("Informe a expressão:");
                    info = dados.next();
                    System.out.println(conversor(info,"0"));
                    valida = false;
                    break;
                default:
                    System.out.println("Escolha inválida!");
                    break;
            }
        }while(valida);
        
//        Double[] vet = new Double[3];         
//        vet = lerDados();
//        montaDominio(vet[0], vet[1], vet[2].intValue());
//        calculaLimite(3);
    }
    
    public static void teoremaDoConfronto(String exp, String expSup, String expInf, String ponto){
        String fSup;
        try {
            fSup = calculaLimite(expSup, ponto);
            String fInf = calculaLimite(expInf, ponto);
            if(expSup.equals(expInf))
                System.out.println("Limite da função existe: "+fSup);
            else{
                System.out.println("Limite não pode ser encotrado! Lim Sup: "+fSup+" Lim Inf: "+fInf);
            }
        } catch (Exception ex) {
            System.out.println("Erro: "+ex.getMessage());
        }
    }
    
    public static String calculaPolonesaINversa(String entrada, String info){
        Stack<String> pilha = new Stack<>();
        Stack<Op> operadores = new Stack<>();
        String[] operadoresSuportados = {"+","-","*","/","^","(",")"};
        
        for(int i = 0; i < entrada.length(); i++){
            String s = String.valueOf(entrada.charAt(i));
            if(Character.isDigit(entrada.charAt(i)) || entrada.charAt(i) == 'x'){
                if(Character.isDigit(entrada.charAt(i))){
                    s = String.valueOf(entrada.charAt(i));
                    while(Character.isDigit(entrada.charAt(i+1))){
                        s += String.valueOf(entrada.charAt(i+1));
                        i++;
                    }         
                }
                pilha.push(s);                
            }else if(Arrays.asList(operadoresSuportados).contains(s)){
                String n1 = pilha.pop();
                String n2 = pilha.pop();
                String resultado;
                if(n1.equals("x"))
                    n1 = info;
                else if(n2.equals("x"))
                    n2 = info;
                try{
                    switch(s){
                        case "+":
                            resultado = Operadores.soma(n2, n1);
                            pilha.push(resultado);
                            break;
                        case "-":
                            resultado = Operadores.sub(n2, n1);
                            pilha.push(resultado);
                            break;
                        case "*":
                            resultado = Operadores.M(n2, n1);
                            pilha.push(resultado);
                            break;
                        case "/":
                            resultado = Operadores.D(n2, n1);
                            pilha.push(resultado);
                            break;
                        case "^":
                            resultado = Operadores.pow(n2, n1);
                            pilha.push(resultado);
                            break;
                    }
                }catch(Exception e){
                    System.out.println("Erro: "+e.getMessage());
                }
            }
        }
        return pilha.peek();
    }
    
    public static String conversor(String entrada, String info){
        Pilha<String> input = new Pilha<>();
        Pilha<String> simbolos = new Pilha<>();
        Stack<Op> operadores = new Stack<>();
        Pilha<String> saida = new Pilha<>();
        String[] operadoresSuportados = {"+","-","*","/","^","(",")"};
        
        for(int i = 0; i < entrada.length(); i++){
            String s;
            try{
            if(Character.isDigit(entrada.charAt(i))){
                s = String.valueOf(entrada.charAt(i));
                while(Character.isDigit(entrada.charAt(i+1))){
                    s += String.valueOf(entrada.charAt(i+1));
                    i++;
                }            
            }else
                s = String.valueOf(entrada.charAt(i));                
            simbolos.push(s);
            input.push(s);
            }catch(IndexOutOfBoundsException ex){
                s = String.valueOf(entrada.charAt(i));
                simbolos.push(s);
                input.push(s);
            }
        }
        while(!simbolos.isEMpty()){
            String simbolo = simbolos.pop();
            
            if(Character.isDigit(simbolo.charAt(0)) || simbolo.charAt(0) == 'x')
                saida.push(simbolo);
            else if(Arrays.asList(operadoresSuportados).contains(simbolo)){
                Op operador = new Op(simbolo);
                Op topOperador;
                try{
                    topOperador = operadores.peek();
                }catch(EmptyStackException e){
                    topOperador = null;
                }
                if(simbolo.equals(")")){
                    while(topOperador != null && !topOperador.op().equals("(")){
                        saida.push(topOperador.op());
                        operadores.pop();
                        topOperador = operadores.peek();
                    }
                    operadores.pop();
                }else if(simbolo.equals("(")){
                    operadores.push(operador);
                }else{
                    while(topOperador != null && topOperador.Precedencia() > operador.Precedencia()){
                        saida.push(topOperador.op());
                        operadores.pop();
                        try{
                            topOperador = operadores.peek();
                        }catch(EmptyStackException e){
                            topOperador = null;
                        }
                    }
                    operadores.push(operador);
                }
            }
        }
        while(!operadores.isEmpty()){
            Op operador  = operadores.pop();
            saida.push(operador.op());
        }        
        String resultado = "";
        for(String s : saida){
            System.out.println("saida: "+s);
            resultado += s + " ";
        }
        resultado = calculaPolonesaINversa(resultado, info);
        return resultado;
        
    }
    
    public static double[] montaDominio(double inicio, double fim, int sensibilidade){
        
        double tamanho = Math.round(Math.abs(fim - inicio) * Math.pow(10,sensibilidade)) ;
        
        double[] x = new double[(int)tamanho];
        String s = "0.";
        
        if(sensibilidade == 0)
            s = "1";
        else{
            for(int i = 0; i < sensibilidade-1; i++){
                s = s + "0";
            }
            s = s + "1";
        }
        double d = Double.parseDouble(s);
        
        for(int i = 0; i < x.length; i++){
            x[i] = inicio;
            System.out.println("Dominio "+x[i]);
            inicio += d;
        }
        return x;
    }
    
    public static String calculaLimite(String expressao ,String info)throws Exception{
            double ponto = Double.parseDouble(info);
            double limSup = ponto + 1;
            double limInf = ponto - 1;
            String[] vetLimSup = new String[10];
            String[] vetLimInf = new String[10];

            int i;
            for(i = 0; i < 10; i++){
                vetLimSup[i] = String.valueOf(limSup);
                limSup = limSup - 0.1;
                vetLimInf[i] = String.valueOf(limInf);
                limInf = limInf + 0.1;
            }
            for(i = 0; i < 10; i++){
                 //y[i] = 3.5 + (0.7 * Math.pow(x[i],23));
                 vetLimSup[i] = conversor(expressao, vetLimSup[i]);
                 vetLimInf[i] = conversor(expressao, vetLimInf[i]);
            }
            i = 0;
            for(String c : vetLimInf){
                System.out.println("LimInf: "+c+" LimSup: "+vetLimSup[i]);
                i++;
            }

            int count = 9;
            boolean valida = true;
            String[] limite = new String[3];
            for(i = 0; i < 3; i++){
                limite[i] = Operadores.D(Operadores.soma(vetLimInf[count], vetLimSup[count]), "2");
                count--;
            }
            if((limite[0] == null ? limite[1] == null : limite[0].equals(limite[1]))&& (limite[1] == null ? limite[2] == null : limite[1].equals(limite[2]))){
                System.out.println("Limite da função é: "+limite[0]);
                return limite[0];
            }else{
                System.out.println("Sem limite");
                return null;
            }
    }
    
    public static Double[] lerDados(){
       
        Scanner dados = new Scanner(System.in);
        Double[] vetor = new Double[3];
        
        System.out.println("Isira o valor do inicio do dominio");
        vetor[0] = dados.nextDouble();        
        System.out.println("Insira o valor do fim do dominio");
        vetor[1] = dados.nextDouble();
        System.out.println("Insira o valor da sensibilidade");
        vetor[2] = dados.nextDouble();
        
        return vetor;
    }
    
    public static void calcula(double[] x, String entrada){
        String[] y = new String[x.length];
        String[] lx = new String[x.length];
        int i = 0;
        for(Double c : x){
            lx[i] = c.toString();
            i++;
        }
        for(i = 0; i < x.length; i++){
             y[i] = conversor(entrada,  lx[i]);
             System.out.println("F("+x[i]+") = "+y[i]);
        }
        double[] ly = new double[y.length];
        i = 0;
        for(String c : y){
            ly[i] = Double.parseDouble(c);
            i++;
        }
        geraGrafico(x,ly);
    }
    
    public static void geraGrafico(double[] x, double[] y){
        DefaultCategoryDataset data;
        data = new DefaultCategoryDataset();
        int i = 0;
        String s = "";
        
        for(double d : x){
            s = String.valueOf(d);
            data.addValue(y[i], "f(x)", s);
            i++;
        }
        
        
        JFreeChart grafico = ChartFactory.createLineChart("Aula 3", "X", "Y", data);
        try{
            System.out.println("Gerando Gráfico");
            OutputStream arq = new FileOutputStream("Grafico2.png");
            
            ChartUtilities.writeChartAsPNG(arq, grafico, 2000, 1500);
            arq.close();
            System.out.println("Feito!");
        }catch(IOException error){
            System.out.println("Erro inesperado com arquivo: "+error.getMessage());
        }
        
    }
}
