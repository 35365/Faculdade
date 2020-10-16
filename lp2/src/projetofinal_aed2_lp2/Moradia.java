package projetofinal_aed2_lp2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import projetofinal_aed2_lp2.aux.SeparateChainingHash_ST_AED2;

public class Moradia implements Serializable{

  private String proprietario;

  private String morada;

  private String localidade;

  private String codigoPostal;

  private Boolean autonomia;

  private Contador contador;
    /**
   * 
   * @element-type Equipamento
   */
  private SeparateChainingHash_ST_AED2<Integer,Equipamento >   equipamentos;
    /**
   * 
   * @element-type FonteEnergiaP
   */
  private ArrayList<FonteEnergiaP> fontesp;

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Boolean getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(Boolean autonomia) {
        this.autonomia = autonomia;
    }

    public Contador getContador() {
        return contador;
    }

    public void setContador(Contador contador) {
        this.contador = contador;
    }

    public SeparateChainingHash_ST_AED2<Integer, Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public ArrayList<FonteEnergiaP> getFontesp() {
        return fontesp;
    }
/**
 * O contrutor da Moradia inicianaliza a SeparateChainingHash e o ArrayList.
 * @param proprietario
 * @param morada
 * @param localidade
 * @param codigoPostal
 * @param autonomia 
 */
    public Moradia(String proprietario, String morada, String localidade, String codigoPostal, Boolean autonomia) {
        this.proprietario = proprietario;
        this.morada = morada;
        this.localidade = localidade;
        this.codigoPostal = codigoPostal;
        this.autonomia = autonomia;
        equipamentos = new SeparateChainingHash_ST_AED2<>();// inicialização da SeparateChainingHash 
        fontesp = new ArrayList<>();//inicializaçao do arraylist
    }
/**
 * Insere o equipamento na SeparateChainingHash.
 * @param e Equipamento
 * @author patricia
 */
  public void inserirEquipamento(Equipamento e) {
      Equipamento eqaux= equipamentos.get(e.hashCode());
      if(eqaux==null){// se não houver nenhum equipamento com o mesmo consumo insere-se
          equipamentos.put(e.hashCode(), e);
          return;
      }
  }
  
  /**
   * Remove o equipamento da SeparateChainingHash.
   * @param e Equipamento
   * @return Equipamento removido
   * @author patricia
   */
  public Equipamento removerEquipamento(Equipamento e) {
      equipamentos.delete(e.hashCode());
      return e;
      }
  
  /**
   * Insere no ArrayList a FonteEnergiaP.
   * @param f Fonte de Energia de pequenas dimensões (Paineis Solares)
   * @author patricia
   */
  public void inserirFonteEnergiaP(FonteEnergiaP f) {
      fontesp.add(f);
  }
  
  /**
   * 
   * @param f Fonte de Energia de pequenas dimensões (Paineis Solares)
   * @return FonteEnergiaP removida
   * @author patricia
   */
  public FonteEnergiaP removerFonteEnergiaP(FonteEnergiaP f){
      for (FonteEnergiaP fi: this.fontesp){
          if(fi.getDimensao()== f.getDimensao()){
              fontesp.remove(f);
               return f;
          }
      }
      return null;
  }
  /**
   * Lista todas as FonteEnergiaP com energia produzida igual a energiaProduzida.
   * Se não existir imprime para a consola a mensagem:(@code System.out.println("\nNão existe fontes de energia com "+energiaProduzida+" de energia produzida");).
   * @param energiaProduzida 
   * @author patricia
   */
  public void listarFonteEnergiaP(Double energiaProduzida){
      int count = 0;
      for(FonteEnergiaP f : this.fontesp){
          if( f.getEnergiaProduzida().equals(energiaProduzida)){
              System.out.println("\n"+ f.toString());
              count++;
          }
      }
      if(count == 0)
          System.out.println("\nNão existe fontes de energia com "+energiaProduzida+" de energia produzida");
      
  }
  /**
   * Altera as caracteristicas da FonteEnergiaP com fonteIDAntiga para as caracteristicas novas.
   * Esta alteração é feita em todo o sitema, isto é, volta a gravar a FonteEnergiaP com as informações alteradas.
   * Se não existir imprime para a consola a mensagem:"Não existe essa Fonte de Energia".
   * @param fonteIDAntiga
   * @param fonteIDNova
   * @param energiaProduzidaNova
   * @param dimensaoNova
   * @param tipoNovo 
   * @author patricia
   */
  public void editarFonteEnergiaP(Integer fonteIDAntiga,Integer fonteIDNova, Double energiaProduzidaNova, String dimensaoNova, String tipoNovo){
      for(FonteEnergiaP f : this.fontesp){
          if( f.getFonteID() == fonteIDAntiga){
              //Eliminar ficheiros com os dados antigos
                File diretorioE = new File(".//data//FonteEnergiaP"+f.getFonteID().toString()+".txt");
                diretorioE.delete();
                File diretorioM = new File(".//data//"+this.morada+".txt");
                diretorioM.delete();
              f.setDimensao(dimensaoNova);
              f.setEnergiaProduzida(energiaProduzidaNova);
              f.setFonteID(fonteIDNova);
              f.setTipo(tipoNovo);
              //Guardar os novos ficheiros com as alterações feitas
                this.gravarMoradia(this.morada);
                f.gravarFonteEnergiaP(f.getFonteID().toString());
              return;
          }
      }
      System.out.println("Não existe essa Fonte de Energia");      
  }
  /**
   * Lista todos os Equipamentos com esse nome.
   * @param nome do Equipamento
   * @author patricia
   */
  public void listarEquipamentos(String nome){
      SeparateChainingHash_ST_AED2<Integer,Equipamento > eq = this.equipamentos;
      for(int i : eq.keys()){
          if(eq.get(i).getNome().compareTo(nome)==0)
             System.out.println(eq.get(i).toString()); 
      }
  }
  /**
   * Altera as caracteristicas do Equipamento com nomeAntigo para as caracteristicas novas.
   * Esta alteração é feita em todo o sitema, isto é, volta a gravar o equipamento com as informações alteradas.
   * Se não existir imprime para a consola a mensagem:"Não existe esse Equipamento".
   * @param nomeAntigo
   * @param nomeNovo
   * @param potenciaNova
   * @param ativoNovo 
   * @author patricia
   */
  public void editarEquipamento(String nomeAntigo,String nomeNovo,Double potenciaNova, Boolean ativoNovo ){//@author patricia
      Equipamento e =null;
      for(int i : this.equipamentos.keys()){
          if(this.equipamentos.get(i).getNome().compareTo(nomeAntigo)==0)
             e= this.equipamentos.get(i);
      }
      if(e == null){
          System.out.println("Não existe esse Equipamento");
          return;
      }
      //Eliminar ficheiros com os dados antigos
      File diretorioE = new File(".//data//"+e.getNome()+".txt");
      diretorioE.delete();
      File diretorioM = new File(".//data//"+this.morada+".txt");
      diretorioM.delete();
      
      
      e.setNome(nomeNovo);
      e.setAtivo(ativoNovo);
      e.setPotencia(potenciaNova);
      //Guardar os novos ficheiros com as alterações feitas
      e.gravarEquipamento(nomeNovo);
      this.gravarMoradia(this.morada);
  }
  /**
   * Grava para um ficheiro de texto as informações da moradia
   * @param fileName Nome do ficheiro
   * @author rita
   */
  public void gravarMoradia(String fileName){
        try{
            String file = ".//data//"+fileName+".txt";
            File fc = new File(file);

        if(!fc.exists()){
            fc.createNewFile();
        }

        PrintWriter pw = new PrintWriter(fc);
        String p = "Proprietario:" + this.proprietario;
        String m = "Morada:" + this.morada;
        String l= "Localidade: " + this.localidade;   
        String cP= "CodigoPostal: " + this.codigoPostal;  
        String a= "Autonomia: " + this.autonomia.toString(); 
        String c= "Contador: " + this.contador.toString();        
        pw.println(p);
        pw.println(m);
        pw.println(l);  
        pw.println(cP);
        pw.println(a);
        pw.println(c); 
        pw.println("Equipamentos:\n");        
        for (int key : this.equipamentos.keys()){// vai a todas as chaves
            String equipamento ="\t"+ this.equipamentos.get(key).toString();
            pw.println(equipamento);
        }
        if (this.autonomia==true){// se for autonoma tem uma fonteEnergiaP
            pw.println("FontesEnergiaP:\n\t");
            for (FonteEnergiaP fi : this.fontesp){//guarda todas as fontes
                String fontes = "\t"+fi.toString();
                pw.println(fontes);
            }
        }
        pw.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
  /**
   * Obtem o valor do consumo total do equipamento indo a todos os equipamentos e somando o consumo energetico desses equipamentos.
   * @return consumoTotal
   * @author patricia
   */
  public double consumoEnergeticoEq(){
      double consumoTotal=0;
        for (int i : this.equipamentos.keys())
            consumoTotal += this.equipamentos.get(i).consumoEnergetico();
        return consumoTotal;
  }
  
    @Override
    public String toString() {
        return "Moradia{" + "proprietario=" + proprietario + ", morada=" + morada + ", localidade=" + localidade + ", codigoPostal=" + codigoPostal + ", autonomia=" + autonomia + ", contador=" + contador + '}';
    }
}