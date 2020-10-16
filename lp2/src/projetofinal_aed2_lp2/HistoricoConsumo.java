package projetofinal_aed2_lp2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;

public class HistoricoConsumo implements Serializable{

  private Date inicio;

  private Date fim;

  private Equipamento equipamento;

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public HistoricoConsumo(Date inicio, Date fim, Equipamento equipamento) {
        this.inicio = inicio;
        this.fim = fim;
        this.equipamento = equipamento;
    }
    /**
     * Calcula o consumo de um equipamento durante o periodo de tempo que esteve ativo.
     * @return consumo energético
     * @author patricia
     */
    public Double consumoEnergetico(){
        int min,h,dia,mes,ano;
        min = this.fim.getMinutes() - this.inicio.getMinutes();
        h = this.fim.getHours() - this.inicio.getHours();
        dia = this.fim.getDay() - this.inicio.getDay();
        mes = this.fim.getMonth() - this.inicio.getMonth();
        ano = this.fim.getYear() - this.inicio.getYear();
        // se a unidade de tempo(min,h,dia ou mes) for menor que 0 quer dizer que tem de se diminuir 1 á unidade de tempo a seguir
        if(min < 0){
            min= Math.abs(min);// valor absoluto
            h--;
        }
        if(h < 0){
            h = Math.abs(h);
            dia--;
        }
        if(dia < 0 ){
            dia = Math.abs(dia);
            mes--;
        }
        if( mes < 0){
            mes = Math.abs(mes);
            ano--;
        }
        
        return (((min/60.0)+h+(dia*24.0)+(mes*720.0)+(ano*8640.0))*this.equipamento.getPotencia())/1000.0;
    }
    /**
     * Grava para um ficheiro de texto as informações do Historico de consumo
     * @param fileName Nome do ficheiro
     * @author rita
     */
    public void gravarHistoricoConsumo(String fileName){
        try{
            String file = ".//data//"+fileName+".txt";
            File fc = new File(file);

        if(!fc.exists()){
            fc.createNewFile();
        }

        PrintWriter pw = new PrintWriter(fc);
        String inicio = "Inicio:" + this.inicio.toString();
        String fim = "Fim:" + this.fim.toString();
        String equipamento= "Equipamento: "+this.equipamento.getNome();      
        pw.println(inicio);
        pw.println(fim);
        pw.println(equipamento);               
        pw.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "HistoricoConsumo{" + "inicio=" + inicio + ", fim=" + fim +"\n"+ equipamento + '}';
    }
    
}