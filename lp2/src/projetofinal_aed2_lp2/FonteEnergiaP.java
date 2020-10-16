package projetofinal_aed2_lp2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

public class FonteEnergiaP extends FonteEnergiaG implements Serializable{

  private String dimensao;

  private Moradia moradia;

    public String getDimensao() {
        return dimensao;
    }

    public void setDimensao(String dimensao) {
        this.dimensao = dimensao;
    }

    public Moradia getMoradia() {
        return moradia;
    }

    public void setMoradia(Moradia moradia) {
        this.moradia = moradia;
    }

    public FonteEnergiaP(String dimensao, Moradia moradia, String tipo, Double EnergiaProduzida, Integer fonteID) {
        super(tipo, EnergiaProduzida,fonteID);
        this.dimensao = dimensao;
        this.moradia = moradia;
    }
    /**
     * Grava para um ficheiro de texto as informações da fonte de energia de dimensões pequenas(paineis solares).
     * @param fileName Nome do ficheiro
     * @author rita 
     */
    public void gravarFonteEnergiaP(String fileName){
        try{
            String file = ".//data//FonteEnergiaP"+fileName+".txt";
            File fc = new File(file);

        if(!fc.exists()){
            fc.createNewFile();
        }

        PrintWriter pw = new PrintWriter(fc);
        String tipo = "Tipo:" + super.getTipo();
        String energiaProduzida = "EnergiaProduzida: "+ Double.toString(super.getEnergiaProduzida());
        String Morada = "Morada: "+this.moradia.getMorada();
        String fonteID = "FonteID: "+Integer.toString(super.getFonteID());
        String dimensao = "Dimensão:" + this.dimensao;
        pw.println(tipo);
        pw.println(energiaProduzida);
        pw.println(Morada);       
        pw.println(fonteID);
        pw.println(dimensao);
        pw.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "FonteEnergiaP{"+ " fonteID=" + super.getFonteID() 
                + " dimensao=" + dimensao + ", morada=" + moradia.getMorada()
                + ", tipo=" + super.getTipo()+ ", energia produzida=" + super.getEnergiaProduzida()+'}';
    }
   
}