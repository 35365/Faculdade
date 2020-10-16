package projetofinal_aed2_lp2;

import java.util.*;
import java.io.*;
import java.lang.*;



public class Contador implements Serializable{
    
  private Integer contadorID;

  private Moradia moradia;
  
  private RedeEletrica rede;

    public Integer getContadorID() {
        return contadorID;
    }

    public void setContadorID(Integer contadorID) {
        this.contadorID = contadorID;
    }

    /**
     * @return the moradia
     */
    public Moradia getMoradia() {
        return moradia;
    }

    /**
     * @param moradia the moradia to set
     */
    public void setMoradia(Moradia moradia) {
        this.moradia = moradia;
    }

    /**
     * @return the rede
     */
    public RedeEletrica getRede() {
        return rede;
    }

    /**
     * @param rede the rede to set
     */
    public void setRede(RedeEletrica rede) {
        this.rede = rede;
    }

    public Contador(Integer contadorID, Moradia moradia, RedeEletrica rede) {
        this.contadorID = contadorID;
        this.moradia = moradia;
        this.rede = rede;
    }

    public Double contagem(){
        return 0.0;
    }
    /**
     * Grava as informações do contador num ficheiro de texto
     * @param fileName 
     * @author rita
     */
    public void gravarContador(String fileName){//@author rita
        try{
            String file = ".//data//Contador"+fileName+".txt";
            File fc = new File(file);

        if(!fc.exists()){
            fc.createNewFile();
        }

        PrintWriter pw = new PrintWriter(fc);
        String contadorId = "Contador ID:" + this.contadorID;
        String Morada = "Morada: "+this.moradia.getMorada();
        String rede = "Rede: "+this.rede.toString();
        pw.println(contadorId);
        pw.println(Morada);
        pw.println(rede);
        pw.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Contador{" + "contadorID=" + contadorID + ", morada=" + moradia.getMorada() + ", rede=" + rede.getNome() + '}';
    }
}
    
  
    
