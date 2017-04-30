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
public class Operadores {
    // # = infinito 
    public static String pow(String a, String b) throws Exception{
        if("#".equals(a) || "#".equals(b))
            return "#";
        else if("-#".equals(b) || "-#".equals(a))
            return "#";
        else if(("#".equals(a)||"-#".equals(a))&&(b.equals("0"))){
            throw new Exception("Indeterminação de potência, infinito elevado a 0");
        }
        else{
            Double r = Math.pow(Double.parseDouble(a), Double.parseDouble(b));
            return r.toString();
        }    
    }
    
    public static String M(String a, String b) throws Exception{ // a * b
        if(("0".equals(a)||"0".equals(b))&&("#".equals(a)||"-#".equals(b)))
            throw new Exception("Erro, multiplicação não definida!");
        else if("#".equals(a)&&"#".equals(b))
            return "#";
        else if(("-#".equals(a)&&"#".equals(b))||("#".equals(a)&&"-#".equals(b)))
            return "-#";
        else if(Double.parseDouble(a) > 0 && "#".equals(b))
            return "#";
        else if(Double.parseDouble(a) > 0 && "-#".equals(b))
            return "-#";
        else if(Double.parseDouble(a) < 0 && "#".equals(b))
            return "-#";
        else if(Double.parseDouble(a) < 0 && "-#".equals(b))
            return "#";
        else{
            Double r = Double.parseDouble(a) * Double.parseDouble(b);
            return r.toString();
        }
    }
    
    public static String D(String a, String b) throws Exception{
        if(("#".equals(a) && "#".equals(b))||("-#".equals(a) && "-#".equals(b)))
            throw new Exception("Erro, divisão indefinida!");
        else if("#".equals(a) && (Double.parseDouble(b) > 0))
            return "#";
        else if(("#".equals(a) && (Double.parseDouble(b) < 0))||("-#".equals(a) && (Double.parseDouble(b) > 0)))
            return "-#";
        else if("-#".equals(a) && (Double.parseDouble(b) < 0))
            return "#";
        else{
            Double r = Double.parseDouble(a) / Double.parseDouble(b);
            return r.toString();
        }
    }
    
    public static String soma(String a, String b) throws Exception{
        if("#".equals(a) || "#".equals(b))
            return "#";
        else if("-#".equals(a) || "-#".equals(b))
            return "-#";
        else if("#".equals(a)&&"-#".equals(b))
            throw new Exception("Indeterminação de soma de infinitos");
        else{
            Double r = Double.parseDouble(a) + Double.parseDouble(b);
            return r.toString();
        }
    }
    
    public static String sub(String a, String b) throws Exception{
        if("#".equals(b)||"-#".equals(a))
            return "-#";
        else if("-#".equals(b)||"#".equals(a))
            return "#";
        else if(("#".equals(a)&&"#".equals(b))||("-#".equals(a)&&"-#".equals(b)))
            throw new Exception("SUbtração de infinito indeterminado!");
        else{
            Double r = Double.parseDouble(a) - Double.parseDouble(b);
            return r.toString();
        }
    }
}
