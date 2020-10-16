package projetofinal_aed2_lp2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

public class FonteEnergiaG implements Serializable{

  private String tipo;
  
  private Double EnergiaProduzida;

  private RedeEletrica rede;
  
  private Integer fonteID;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getEnergiaProduzida() {
        return EnergiaProduzida;
    }

    public void setEnergiaProduzida(Double EnergiaProduzida) {
        this.EnergiaProduzida = EnergiaProduzida;
    }

    public RedeEletrica getRede() {
        return rede;
    }

    public void setRede(RedeEletrica rede) {
        this.rede = rede;
    }

    public Integer getFonteID() {
        return fonteID;
    }

    public void setFonteID(Integer fonteID) {
        this.fonteID = fonteID;
    }

    public FonteEnergiaG(String tipo, Double EnergiaProduzida, RedeEletrica rede, Integer fonteID) {
        this.tipo = tipo;
        this.EnergiaProduzida = EnergiaProduzida;
        this.rede = rede;
        this.fonteID = fonteID;
    }

    /**
     * Construtor para a fonte de energia de pequenas dimensoes sem rede eletrica
     * @param tipo
     * @param EnergiaProduzida
     * @param fonteID 
     */
    public FonteEnergiaG(String tipo, Double EnergiaProduzida, Integer fonteID) {
        this.tipo = tipo;
        this.EnergiaProduzida = EnergiaProduzida;
        this.fonteID = fonteID;
    }

   public boolean produzEnergiaEsperada(double eEsperada){
       return false;
   }
   
   /**
    *Grava para um ficheiro de texto as informações da fonte de energia de grandes dimensões
    * @param fileName Nome do ficheiro
    * @author rita
    */
   public void gravarFonteEnergiaG(String fileName){
        try{
            String file = ".//data//FonteEnergiaG"+fileName+".txt";
            File fc = new File(file);

        if(!fc.exists()){
            fc.createNewFile();
        }

        PrintWriter pw = new PrintWriter(fc);
        String tipo = "Tipo:" + this.tipo;
        String energiaProduzida = "EnergiaProduzida: "+ Double.toString(this.EnergiaProduzida);
        String rede = "Rede: " + this.rede.getNome();
        String fonteID = "FonteID: "+Integer.toString(this.fonteID);
        pw.println(tipo);
        pw.println(energiaProduzida);
        pw.println(rede);       
        pw.println(fonteID);
        pw.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "FonteEnergiaG{" + "tipo=" + tipo + ", EnergiaProduzida=" + EnergiaProduzida + ", rede=" + rede.getNome() + ", fonteID=" + fonteID + '}';
    }

   
}