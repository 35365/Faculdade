package projetofinal_aed2_lp2;

import projetofinal_aed2_lp2.aux.RedBlackBST_Aed2;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;

public class Equipamento implements Serializable{

  private String nome;

  private Double potencia;

  private Boolean ativo;

  private Moradia moradia;
    /**
   * 
   * @element-type HistoricoConsumo
   */
  private RedBlackBST_Aed2<Date,HistoricoConsumo>   historicos;

  /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the potencia
     */
    public Double getPotencia() {
        return potencia;
    }

    /**
     * @param potencia the potencia to set
     */
    public void setPotencia(Double potencia) {
        this.potencia = potencia;
    }

    /**
     * @return the ativo
     */
    public Boolean getAtivo() {
        return ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
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

    public RedBlackBST_Aed2<Date, HistoricoConsumo> getHistoricos() {
        return historicos;
    }
/**
 * O construtor do Equipamento inicializa a RedBlackBST de historicos de consumo.
 * @param nome
 * @param potencia
 * @param ativo
 * @param moradia 
 */
    public Equipamento(String nome, Double potencia, Boolean ativo, Moradia moradia) {
        this.nome = nome;
        this.potencia = potencia;
        this.ativo = ativo;
        this.moradia = moradia;
        historicos = new RedBlackBST_Aed2<>();
    }
    /**
     * Insere na RedBlackBST o historico de consumo, se já existir imprime a mensagem para a consola:"Já existe esse historico\n".
     * @param hc Historico de consumo  
     * @author patricia
     */
    public void inserirHistoricoConsumo(HistoricoConsumo hc){
       HistoricoConsumo hcaux = historicos.get(hc.getInicio());
       if(hcaux == null){
           historicos.put(hc.getInicio(), hc);
           return;
       }
        System.out.println("Já existe esse historico\n");   
    }
    // não tem remover historico porque estou a considerar historico permanente
    /**
     * Lista todos os históricos de consumo do equipamento que tiveram como data de inicio d.
     * Se não existir imprime para a consola "null".
     * @param d Data de inicio
     * @author patricia
     */
    public void listarHistoricoConsumo(Date d){
        System.out.println( this.historicos.get(d));
    }
    /**
     * Lista todos os históricos de consumo do equipamento que tiveram data de inicio entre di e df 
     * @param di data de inicio
     * @param df data de fim
     * @author patricia
     */
    public void listarHistoricoConsumoEntreDatas(Date di, Date df){
        RedBlackBST_Aed2<Date,HistoricoConsumo> hs = this.historicos;
        System.out.println( hs.keys(di,df));
    }
    /**
     * Altera as caracteristicas do histórico de consumo com inicioAntigo para as caracteristicas novas.
     * Esta alteração é feita em todo o sitema, isto é, volta a gravar o historico com as informações alteradas.
     * Se não existir imprime a mensagem para a consola "Não existe esse historico\n".
     * @param inicioAntigo data de inicio do histórico que queremos alterar
     * @param inicioNovo data de inicio novo
     * @param fimNovo data de fim novo
     * @author patricia
     */
    public void editarHistoricoConsumo(Date inicioAntigo,Date inicioNovo, Date fimNovo){
        HistoricoConsumo h = this.historicos.get(inicioAntigo);
        if(h == null){
            System.out.println("Não existe esse historico\n");
            return;       
        }
        h.setInicio(inicioNovo);
        h.setFim(fimNovo);
        if(inicioAntigo.compareTo(inicioNovo)!=0){
            this.historicos.delete(inicioAntigo);
            this.inserirHistoricoConsumo(h);
        }
        this.gravarEquipamento(nome);
    }
    /**
     * Grava para um ficheiro de texto as informações do equipamento
     * @param fileName Nome do ficheiro
     * @author rita
     */
    public void gravarEquipamento(String fileName){
        try{
            String file = ".//data//"+fileName+".txt";
            File fc = new File(file);

        if(!fc.exists()){
            fc.createNewFile();
        }

        PrintWriter pw = new PrintWriter(fc);
        String nome = "Nome:" + this.nome;
        String potencia = "Potencia: "+ Double.toString(this.potencia);
        String ativo = this.getAtivo().toString();
        String Morada = "Morada: "+this.moradia.getMorada();
        pw.println(nome);
        pw.println(Morada);
        pw.println(potencia);       
        pw.println(ativo);
        pw.println("HistoricoConsumo: \n");
        Iterable<Date> st = this.historicos.keys();
        for (Date key : st){
            String h ="\t" + this.historicos.get(key).toString();
            pw.println(h);
            }
        pw.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }  
    /**
     * Obtem o valor do consumo total do equipamento indo a todos os hitóricos desse equipamento e somando o consumo.
     * @return consumoTotal
     * @author patricia
     */
    public double consumoEnergetico(){
        double consumoTotal=0;
        for (Date d : this.historicos.keys())
            consumoTotal += this.historicos.get(d).consumoEnergetico();
        return consumoTotal;
    }

    @Override
    public String toString() {
        return "Equipamento{" + "nome=" + nome + ", potencia=" + potencia + ", ativo=" + ativo 
                +"\n\t,Proprietario: "+ moradia.getProprietario() + ", morada: " + moradia.getMorada()+ '}';
    }
    
}