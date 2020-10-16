package projetofinal_aed2_lp2;

import projetofinal_aed2_lp2.aux.SeparateChainingHash_ST_AED2;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

public class RedeEletrica implements Serializable{

  private String nome;

  private Double alimentacao;

  private Integer centralID;

    /**
   * 
   * @element-type Contador
   */
  private SeparateChainingHash_ST_AED2<Integer,Contador >  contadores;
    /**
   * 
   * @element-type FonteEnergiaG
   */
  private SeparateChainingHash_ST_AED2<Integer,FonteEnergiaG > fontes;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getAlimentacao() {
        return alimentacao;
    }

    public void setAlimentacao(Double alimentacao) {
        this.alimentacao = alimentacao;
    }

    public Integer getCentralID() {
        return centralID;
    }

    public void setCentralID(Integer centralID) {
        this.centralID = centralID;
    }

    public SeparateChainingHash_ST_AED2<Integer, Contador> getContadores() {
        return contadores;
    }

    public SeparateChainingHash_ST_AED2<Integer,FonteEnergiaG> getFontes() {
        return fontes;
    }
/**
 * O construtor inicializa as SeparateChainingHash de contadores e fontes
 * @param nome
 * @param alimentacao
 * @param centralID 
 */
    public RedeEletrica(String nome, Double alimentacao, Integer centralID) {
        this.nome = nome;
        this.alimentacao = alimentacao;
        this.centralID = centralID;
        contadores = new SeparateChainingHash_ST_AED2<>();// inicialização da separateChaining
        fontes = new SeparateChainingHash_ST_AED2<>();
    }
    /**
     * Insere na SeparateChainingHash o contador, se já existir imprime a mensagem para a consola:"Já existe esse contador\n".
     * @param c Contador
     * @author patricia
     */
    public void inserirContador(Contador c){
        Contador aux = this.contadores.get(c.getContadorID());
        if(aux != null){// se já existir nao se insere
            System.out.println("Já existe esse contador");
            return;
        }
       this.contadores.put(c.getContadorID(), c);
    }
    /**
     * Remove da SeparateChainingHash o contador.
     * @param c Contador
     * @return c Contador removido
     */
    public Contador removerContador(Contador c){//@author patricia
        this.contadores.delete(c.getContadorID());
        return c;
    }
    /**
     * Lista o contador c que pertence á rede elétrica.
     * @param c Contador
     * @author patricia
     */
    public void listarContador(Contador c){
        System.out.println(this.contadores.get(c.getContadorID()));
    }
    /**
     * Altera as caracteristicas do contador com contadorIDAntigo para as caracteristicas novas.
     * Esta alteração é feita em todo o sitema, isto é, volta a gravar o contador com as informações alteradas.
     * Se não existir imprime a mensagem para a consola "Nao existe esse contador!".
     * @param contadorIDAntigo
     * @param contadorIDNovo 
     * @author patricia
     */
    public void EditarContador(Integer contadorIDAntigo,Integer contadorIDNovo) {
        Contador c = this.contadores.get(contadorIDAntigo);
        if(c==null){
            System.out.println("Nao existe esse contador!");
            return;
        }
        //Eliminar ficheiros com os dados antigos
        File diretorioc = new File(".//data//Contador"+c.getContadorID().toString()+".txt");
        diretorioc.delete();
        File diretorioR = new File(".//data//"+this.getNome()+".txt");
        diretorioR.delete();
      
        c.setContadorID(contadorIDNovo);
        this.contadores.delete(contadorIDAntigo);
        this.inserirContador(c);
        //Guardar os novos ficheiros com as alterações feitas
        this.gravarRedeEletrica(this.getNome());
        c.gravarContador(c.getContadorID().toString());
    }
    /**
     * Insere na SeparateChainingHash a FonteEnergiaG, se já existir imprime a mensagem para a consola:"Já existe essa Fonte de energia".
     * @param f FonteEnergiaG
     * @author patricia
     */
    public void inserirFonteEnergiaG(FonteEnergiaG f){
        FonteEnergiaG faux = this.fontes.get(f.getFonteID());
        if(faux != null){// se já existir nao se insere
            System.out.println("Já existe essa Fonte de energia");
            return;
        }
        this.fontes.put(f.getFonteID(), f);
    }
    /**
     * Remove da SeparateChainingHash a FonteEnergiaG.
     * @param f FonteEnergiaG
     * @return FonteEnergiaG removida
     */
    public FonteEnergiaG removerFonteEnergiaG(FonteEnergiaG f){//@author patricia
        this.fontes.delete(f.getFonteID());
        return f;
    }
    /**
     * Lista a FonteEnergiaG que pertence á rede.
     * @param f FonteEnergiaG
     * @author patricia
     */
    public void listarFonteEnergiaG(FonteEnergiaG f){
        System.out.println(this.fontes.get(f.getFonteID()));
    }
    /**
     * Altera as caracteristicas da FonteEnergiaG com fonteIDAntigo para as caracteristicas novas.
     * Esta alteração é feita em todo o sitema, isto é, volta a gravar a FonteEnergiaG com as informações alteradas.
     * Se não existir imprime a mensagem para a consola "Não existe essa Fonte de energia".
     * @param fonteIDAntigo
     * @param fonteIDNovo
     * @param tipoNovo
     * @param EnergiaProduzidaNova 
     * @author patricia
     */
    public void EditarFonteEnergiaG(Integer fonteIDAntigo, Integer fonteIDNovo,String tipoNovo, Double EnergiaProduzidaNova){
        FonteEnergiaG e = this.fontes.get(fonteIDAntigo);
        if (e == null){
            System.out.println("Não existe essa Fonte de energia");
            return;
        }
        //Eliminar ficheiros com os dados antigos
      File diretorioF = new File(".//data//FonteEnergiaG"+e.getFonteID().toString()+".txt");
      diretorioF.delete();
      File diretorioR = new File(".//data//"+this.getNome()+".txt");
      diretorioR.delete();
      
        e.setFonteID(fonteIDNovo);
        e.setEnergiaProduzida(EnergiaProduzidaNova);
        e.setTipo(tipoNovo);
        if(fonteIDAntigo != fonteIDNovo){
            this.fontes.delete(fonteIDAntigo);
            this.inserirFonteEnergiaG(e);
        }
        //Guardar os novos ficheiros com as alterações feitas
        this.gravarRedeEletrica(this.getNome());
        e.gravarFonteEnergiaG(e.getFonteID().toString());
    } 
    
    /**
     * Grava para um ficheiro de texto as informações do equipamento
     * @param fileName Nome do ficheiro
     * @author rita 
     */
    public void gravarRedeEletrica(String fileName){//@author rita
        try{
            String file = ".//data//"+fileName+".txt";
            File fc = new File(file);

        if(!fc.exists()){
            fc.createNewFile();
        }

        PrintWriter pw = new PrintWriter(fc);
        String nome = "Nome:" + this.nome;
        String alimentacao = "Alimentação:" + this.alimentacao;
        String centralId= "CentralID: "+this.centralID;                        
        pw.println(nome);
        pw.println(alimentacao);
        pw.println(centralId); 
        pw.println("Fontes Energia Grandes: \n");       
        Iterable<Integer> st = this.fontes.keys();
        for (Integer key : st){
            String fontes ="\t"+ this.fontes.get(key).toString();
            pw.println(fontes);
        }
        pw.println("Contadores: \n");       
        Iterable<Integer> st2 = this.contadores.keys();
        for (Integer key : st2){
            String contadores ="\t"+ this.contadores.get(key).toString();
            pw.println(contadores);
        }
        pw.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "RedeEletrica{" + "nome=" + nome + ", alimentacao=" + alimentacao + ", centralID=" + centralID + '}';
    }
    
    
}