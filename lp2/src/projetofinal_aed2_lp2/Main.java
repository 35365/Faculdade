/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal_aed2_lp2;

import edu.princeton.cs.algs4.BellmanFordSP;
import projetofinal_aed2_lp2.aux.GrafoDirigido;
import projetofinal_aed2_lp2.aux.NoFonteEnergia;
import projetofinal_aed2_lp2.aux.NoMoradia;
import projetofinal_aed2_lp2.aux.RedBlackBST_Draw_Aed2;
import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import projetofinal_aed2_lp2.aux.GraphNode;

public class Main {
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        //testeRedBlack();
        //testeArrayList();
        testeEquipamentos();
        //testeSeparateChainingContadores();
        //testeAutonomia();
        //escreverFicheiro();
        //testeGraph();
        //lerGrafoBin();
    }
    public static void testeRedBlack(){// @author patricia
        Moradia m = new Moradia("Patricia","Rua numero 1","Senhora da hora","4460",false);
        
        Equipamento eq1 =  new Equipamento("Maquina da loiça",1900.0,true,m);
        Equipamento eq2 = new Equipamento("Aquecedor eletrico",2000.0,false,m);
        Equipamento eq3 = new Equipamento("Microondas",2100.0,true,m);
        Equipamento eq4 = new Equipamento("Televisão ",31.0,true,m);
        
        m.inserirEquipamento(eq4);
        m.inserirEquipamento(eq3);
        m.inserirEquipamento(eq2);
        m.inserirEquipamento(eq1);
        
        m.removerEquipamento(eq2);
        
        //listar equipamentos por potencia
        m.listarEquipamentos("Televisão");
        
        m.editarEquipamento("Televisão","Televisão", 3100.0, Boolean.TRUE);
        m.listarEquipamentos("Televisão");
    }
    
    public static void testeArrayList(){// @author patricia
        Moradia m = new Moradia("Patricia","Rua numero 1","Senhora da hora","4460",true);
        
        FonteEnergiaP f1 = new FonteEnergiaP("A 2017 x L 1175 x P 87 mm",m,"Painel Solar",2100.0,1) ;
        FonteEnergiaP f2 = new FonteEnergiaP("A 2017 x L 1175 x P 87 mm",m,"Painel Solar",2800.0,2) ;
        FonteEnergiaP f3 = new FonteEnergiaP("A 2017 x L 1175 x P 87 mm",m,"Painel Solar",3000.0,3) ;
        
        m.inserirFonteEnergiaP(f3);
        m.inserirFonteEnergiaP(f2);
        m.inserirFonteEnergiaP(f1);
        
        //imprimir arrayList depois de inserção
        for(FonteEnergiaP gi : m.getFontesp())
            System.out.println(gi.toString());
        
        //imprimir arrayList depois de remoção
        System.out.println("\n");
        m.removerFonteEnergiaP(f3);
        for(FonteEnergiaP gi : m.getFontesp())
            System.out.println(gi.toString());
        
        m.editarFonteEnergiaP(2, 4, 4000.0, "A 2017 x L 1175 x P 87 mm", "Painel Solar");
        
        m.listarFonteEnergiaP(4000.0);// listar por energia produzida                
        
    }
    
    public static void testeEquipamentos(){// @author patricia
        Moradia m = new Moradia("Patricia","Rua numero 1","Senhora da hora","4460",false);
        
        Equipamento eq =  new Equipamento("Maquina da loiça",1900.0,true,m);
        
        HistoricoConsumo h1 = new HistoricoConsumo(new Date(18,5,10,12,35),new Date(18,5,10,18,15),eq);
        HistoricoConsumo h2 = new HistoricoConsumo(new Date(17,10,24,13,35),new Date(18,4,12,18,15),eq);
        
        //System.out.println(h1.toString()+"\nConsumo energetico: "+h1.consumoEnergetico()+" kW");
        
        eq.inserirHistoricoConsumo(h1);
        eq.inserirHistoricoConsumo(h2);
        
        RedBlackBST_Draw_Aed2.drawRedBlackTreeDateKeys(eq.getHistoricos());
      
        eq.listarHistoricoConsumo(new Date(19,1,1,0,0));
        
        eq.listarHistoricoConsumoEntreDatas(new Date(19,1,1,0,0),new Date(19,1,1,0,9));
    }
    
    public static void testeSeparateChainingContadores(){// @author patricia
        RedeEletrica r = new RedeEletrica("Rede Eletricia Porto",300.0,1);
        
        //  Moradias
        Moradia m1 = new Moradia("Patricia","Rua numero 1","Senhora da hora","4460",false);
        Moradia m2 = new Moradia("Maria","Rua numero 2","Senhora da hora","4460",false);
        Moradia m3 = new Moradia("Raquel","Rua numero 3","Matosinhos","4450",false);
        
        //  Contadores
        Contador c1 = new Contador(1,m1,r);
        Contador c2 = new Contador(2,m2,r);
        Contador c3 = new Contador(30,m3,r);
        
        //  inserçoes na Separate chaining 
        r.inserirContador(c1);
        r.inserirContador(c2);
        r.inserirContador(c3);
        
        System.out.println(r.getContadores());//imprime a ST
        
        r.removerContador(c3);// remoção da Separate chaining
        
        System.out.println(r.getContadores());
        
        r.listarContador(c2);// lista o contador caso exista
        
        r.EditarContador(c2.getContadorID(), 20);// Edita os valores 
        
        r.listarContador(c2);
        
        System.out.println(r.getContadores());
        
    }
    
    public static void testeAutonomia(){
        RedeEletrica r = new RedeEletrica("Rede Eletricia Porto",300.0,1);
        Moradia m = new Moradia("Rita","Rua da Ranha","Rio Tinto","4465",false);
        FonteEnergiaG g = new FonteEnergiaG("Solar", 32100.0, r, 32);
        
        Equipamento eq1 =  new Equipamento("Maquina da loiça",1900.0,true,m);
        Equipamento eq2 = new Equipamento("Aquecedor eletrico",2000.0,false,m);
        Equipamento eq3 = new Equipamento("Microondas",2100.0,true,m);
        Equipamento eq4 = new Equipamento("Televisão ",31.0,true,m);
        
        m.inserirEquipamento(eq1);
        m.inserirEquipamento(eq2);
        m.inserirEquipamento(eq3);
        m.inserirEquipamento(eq4);
        
        int sum = (int) (eq1.getPotencia()+eq2.getPotencia()+eq3.getPotencia()+eq4.getPotencia());
        if(sum <= g.getEnergiaProduzida()){
            System.out.println("A moradia tem autonomia!");
        }else{
            System.out.println("A moradia nao tem autonomia energetica!");
        }
            
    }
    
    public static void escreverFicheiro(){       
        RedeEletrica r = new RedeEletrica("Rede Eletricia Porto",300.0,1);
        
        //  Moradias
        Moradia m1 = new Moradia("Patricia","Rua numero 1","Senhora da hora","4460",true);
        Moradia m2 = new Moradia("Maria","Rua numero 2","Senhora da hora","4460",false);
        Moradia m3 = new Moradia("Raquel","Rua numero 3","Matosinhos","4450",false);
        
        //  Contadores
        Contador c1 = new Contador(1,m1,r);
        Contador c2 = new Contador(2,m2,r);
        Contador c3 = new Contador(30,m3,r);
        
        //inserir contadores
        m1.setContador(c1);
        m2.setContador(c2);
        m3.setContador(c3);
        
        //FonteEnergiaG
        FonteEnergiaG fg1 = new FonteEnergiaG("Barragem",300.0,r,1);
        FonteEnergiaG fg2 = new FonteEnergiaG("Aeólica",450.0,r,2);
        FonteEnergiaG fg3 = new FonteEnergiaG("Painel Solar G",320.0,r,3);
        
        //  inserçoes na Separate chaining 
        r.inserirContador(c1);
        r.inserirContador(c2);
        r.inserirContador(c3);
        r.inserirFonteEnergiaG(fg1);
        r.inserirFonteEnergiaG(fg2);
        r.inserirFonteEnergiaG(fg3);
        
        r.gravarRedeEletrica(r.getNome());
        
        //Equipamentos
        Equipamento eq1 =  new Equipamento("Maquina da loiça",1900.0,true,m1);
        Equipamento eq2 = new Equipamento("Aquecedor eletrico",2000.0,false,m1);
        Equipamento eq3 = new Equipamento("Microondas",2100.0,true,m1);
        Equipamento eq4 = new Equipamento("Televisão ",31.0,true,m1);
        
        //inserçao na redBlack
        m1.inserirEquipamento(eq1);
        m1.inserirEquipamento(eq2);
        m1.inserirEquipamento(eq3);
        m1.inserirEquipamento(eq4);
        
        
        //Fonte energia P
        FonteEnergiaP f1 = new FonteEnergiaP("A 2017 x L 1175 x P 87 mm",m1,"Painel Solar",2100.0,1) ;
        FonteEnergiaP f2 = new FonteEnergiaP("A 2017 x L 1175 x P 87 mm",m1,"Painel Solar",2800.0,2) ;
        FonteEnergiaP f3 = new FonteEnergiaP("A 2017 x L 1175 x P 87 mm",m1,"Painel Solar",3000.0,3) ;
        
        //inserçao no ArrayList
        m1.inserirFonteEnergiaP(f3);
        m1.inserirFonteEnergiaP(f2);
        m1.inserirFonteEnergiaP(f1);
        
        m1.gravarMoradia(m1.getMorada());
        
        //Historico consumo
        HistoricoConsumo h1 = new HistoricoConsumo(new Date(18,5,10,12,35),new Date(18,5,10,18,15),eq1);
        HistoricoConsumo h2 = new HistoricoConsumo(new Date(17,10,24,13,35),new Date(18,4,12,18,15),eq1);
        
        //inseçao na redBlack
        eq1.inserirHistoricoConsumo(h1);
        eq1.inserirHistoricoConsumo(h2);
        
        eq1.gravarEquipamento(eq1.getNome());
    }
    
    
    public static void testeGraph() throws IOException, FileNotFoundException, ClassNotFoundException{
        RedeEletrica r = new RedeEletrica("Rede Eletricia Porto",300.0,1);
        
        //  Moradias
        Moradia m1 = new Moradia("Patricia","Rua numero um 55","Senhora da hora","4460",true);
        Moradia m2 = new Moradia("Maria","Rua numero um 47","Senhora da hora","4460",false);
        Moradia m3 = new Moradia("Raquel","Rua das Laranjas 1008","Matosinhos","4450",false);
        Moradia m4 = new Moradia("Rita", "Rua da Ranha 7", "Rio Tinto", "4435", false);
        Moradia m5 = new Moradia("Filipa", "Rua Aval Baixo 104", "Porto", "4000", false);
        Moradia m6 = new Moradia("Helder", "Rua Capitao", "Matosinhos", "4325", true);
        Moradia m7 = new Moradia("João","Rua de Cima 2037","Senhora da hora","4460",false);
        Moradia m8 = new Moradia("Ana","Rua das Laranjas 934","Matosinhos","4450",false);
        Moradia m9 = new Moradia("Tiago", "Rua da Ranha 29", "Rio Tinto", "4435", false);
        Moradia m10 = new Moradia("Marta", "Rua Aval Baixo 212", "Porto", "4000", false);
        Moradia m11 = new Moradia("Eduardo","Rua das Laranjas 1010","Matosinhos","4450",false);

        
        //  Contadores
        Contador c1 = new Contador(1,m1,r);
        Contador c2 = new Contador(2,m2,r);
        Contador c3 = new Contador(3,m3,r);
        Contador c4 = new Contador(4,m4,r);
        Contador c5 = new Contador(5,m5,r);
        Contador c6 = new Contador(6,m6,r);
        Contador c7 = new Contador(7,m7,r);
        Contador c8 = new Contador(8,m8,r);
        Contador c9 = new Contador(9,m9,r);
        Contador c10 = new Contador(10,m10,r);
        Contador c11 = new Contador(11,m11,r);
        
        //inserir contadores
        m1.setContador(c1);
        m2.setContador(c2);
        m3.setContador(c3);
        m4.setContador(c4);
        m5.setContador(c5);
        m6.setContador(c6);
        m7.setContador(c7);
        m8.setContador(c8);
        m9.setContador(c9);
        m10.setContador(c10);
        m11.setContador(c11);
        
        //FonteEnergiaG
        FonteEnergiaG fg1 = new FonteEnergiaG("Barragem",300.0,r,1);
        FonteEnergiaG fg2 = new FonteEnergiaG("Aeólica",450.0,r,2);
        FonteEnergiaG fg3 = new FonteEnergiaG("Painel Solar G",320.0,r,3);
        
        //  inserçoes na Separate chaining 
        r.inserirContador(c1);
        r.inserirContador(c2);
        r.inserirContador(c3);
        r.inserirContador(c4);
        r.inserirContador(c5);
        r.inserirContador(c6);
        r.inserirContador(c7);
        r.inserirContador(c8);
        r.inserirContador(c9);
        r.inserirContador(c10);
        r.inserirContador(c11);
        r.inserirFonteEnergiaG(fg1);
        r.inserirFonteEnergiaG(fg2);
        r.inserirFonteEnergiaG(fg3);
        
        
        //Equipamentos
        Equipamento eq1 =  new Equipamento("Maquina da loiça",1900.0,true,m1);
        Equipamento eq2 = new Equipamento("Aquecedor eletrico",2000.0,false,m1);
        Equipamento eq3 = new Equipamento("Microondas",2100.0,true,m1);
        Equipamento eq4 = new Equipamento("Televisão ",31.0,true,m1);
        Equipamento eq11 = new Equipamento("Televisao", 150.0, true, m2);
        Equipamento eq12 = new Equipamento("Fogao", 800.0, true, m2);
        Equipamento eq13 = new Equipamento("Aquecedor", 1550.0, true, m2);
        Equipamento eq14 = new Equipamento("Maquina lavar", 500.0, true, m3);
        Equipamento eq5 = new Equipamento("Ferro", 1000.0, true, m3);
        Equipamento eq6 = new Equipamento("Frigorifico", 90.0, true, m3);
        Equipamento eq7 = new Equipamento("Ar condicionado", 2100.0, true, m4);
        Equipamento eq8 = new Equipamento("Aspirador", 100.0, true, m4);
        Equipamento eq9 = new Equipamento("Secador de cabelo", 600.0, true, m5);
        Equipamento eq10 = new Equipamento("Computador", 190.0, true, m5);
        Equipamento eq15 = new Equipamento("Ferro", 1000.0, true, m5);
        Equipamento eq16 = new Equipamento("Frigorifico", 90.0, true, m6);
        Equipamento eq17 = new Equipamento("Ar condicionado", 2100.0, true, m6);
        Equipamento eq18 = new Equipamento("Aspirador", 100.0, true, m6);
        Equipamento eq19 = new Equipamento("Secador de cabelo", 600.0, true, m7);
        Equipamento eq20 = new Equipamento("Computador", 190.0, true, m7);
        Equipamento eq21 = new Equipamento("Ferro", 1000.0, true, m7);
        Equipamento eq22 = new Equipamento("Frigorifico", 90.0, true, m7);
        Equipamento eq23 = new Equipamento("Ar condicionado", 2100.0, true, m7);
        Equipamento eq24 = new Equipamento("Aspirador", 100.0, true, m8);
        Equipamento eq25 =  new Equipamento("Maquina da loiça",1900.0,true,m8);
        Equipamento eq26 = new Equipamento("Aquecedor eletrico",2000.0,false,m8);
        Equipamento eq27 = new Equipamento("Microondas",2100.0,true,m8);
        Equipamento eq28 = new Equipamento("Televisão ",31.0,true,m8);
        Equipamento eq29 = new Equipamento("Maquina lavar", 500.0, true, m9);
        Equipamento eq30 = new Equipamento("Ferro", 1000.0, true, m9);
        Equipamento eq31 = new Equipamento("Frigorifico", 90.0, true, m9);
        Equipamento eq32 = new Equipamento("Ar condicionado", 2100.0, true, m9);
        Equipamento eq33 = new Equipamento("Aspirador", 100.0, true, m9);
        Equipamento eq34 = new Equipamento("Frigorifico", 90.0, true, m10);
        Equipamento eq35 =  new Equipamento("Maquina da loiça",1900.0,true,m10);
        Equipamento eq36 = new Equipamento("Secador de cabelo", 600.0, true, m11);
        Equipamento eq37 = new Equipamento("Computador", 190.0, true, m11);
        Equipamento eq38 = new Equipamento("Ferro", 1000.0, true, m11);
        Equipamento eq39 = new Equipamento("Frigorifico", 90.0, true, m11);
        
        //inserçao na redBlack
        m1.inserirEquipamento(eq1);
        m1.inserirEquipamento(eq2);
        m1.inserirEquipamento(eq3);
        m1.inserirEquipamento(eq4);
        m2.inserirEquipamento(eq11);
        m2.inserirEquipamento(eq12);
        m2.inserirEquipamento(eq13);
        m3.inserirEquipamento(eq14);
        m3.inserirEquipamento(eq5);
        m3.inserirEquipamento(eq6);
        m4.inserirEquipamento(eq7);
        m4.inserirEquipamento(eq8);
        m5.inserirEquipamento(eq9);
        m5.inserirEquipamento(eq10);
        m5.inserirEquipamento(eq15);
        m6.inserirEquipamento(eq16);
        m6.inserirEquipamento(eq17);
        m6.inserirEquipamento(eq18);
        m7.inserirEquipamento(eq19);
        m7.inserirEquipamento(eq20);
        m7.inserirEquipamento(eq21);
        m7.inserirEquipamento(eq22);
        m7.inserirEquipamento(eq23);
        m8.inserirEquipamento(eq24);
        m8.inserirEquipamento(eq25);
        m8.inserirEquipamento(eq26);
        m8.inserirEquipamento(eq27);
        m8.inserirEquipamento(eq28);
        m9.inserirEquipamento(eq29);
        m9.inserirEquipamento(eq30);
        m9.inserirEquipamento(eq31);
        m9.inserirEquipamento(eq32);
        m9.inserirEquipamento(eq33);
        m10.inserirEquipamento(eq34);
        m10.inserirEquipamento(eq35);
        m11.inserirEquipamento(eq36);
        m11.inserirEquipamento(eq37);
        m11.inserirEquipamento(eq38);
        m11.inserirEquipamento(eq39);
        
        //Fonte energia P
        FonteEnergiaP f1 = new FonteEnergiaP("A 2017 x L 1175 x P 87 mm",m1,"Painel Solar",2100.0,1) ;
        FonteEnergiaP f2 = new FonteEnergiaP("A 2017 x L 1175 x P 87 mm",m1,"Painel Solar",2800.0,2) ;
        FonteEnergiaP f3 = new FonteEnergiaP("A 2017 x L 1175 x P 87 mm",m1,"Painel Solar",3000.0,3) ;
        FonteEnergiaP f4 = new FonteEnergiaP("A 2017 x L 1175 x P 87 mm",m6,"Painel Solar",2000.0,4) ;
        FonteEnergiaP f5 = new FonteEnergiaP("A 2017 x L 1175 x P 87 mm",m6,"Painel Solar",1000.0,5) ;
        
        //inserçao no ArrayList
        m1.inserirFonteEnergiaP(f3);
        m1.inserirFonteEnergiaP(f2);
        m1.inserirFonteEnergiaP(f1);
        m6.inserirFonteEnergiaP(f5);
        m6.inserirFonteEnergiaP(f4);
                
        //Historico consumo
        HistoricoConsumo h1 = new HistoricoConsumo(new Date(18,5,10,12,35),new Date(18,5,10,18,15),eq1);
        HistoricoConsumo h2 = new HistoricoConsumo(new Date(17,10,24,13,35),new Date(18,4,12,18,15),eq1);
        HistoricoConsumo h3 = new HistoricoConsumo(new Date(18,01,01,19,01), new Date(18,01,02,11,00), eq2);
        HistoricoConsumo h4 = new HistoricoConsumo(new Date(18,01,02,19,00), new Date(18,01,03,10,29), eq2);
        HistoricoConsumo h5 = new HistoricoConsumo(new Date(18,01,03,19,30), new Date(18,01,04,10,32), eq2);
        HistoricoConsumo h6 = new HistoricoConsumo(new Date(18,01,04,19,00), new Date(18,01,05,10,30), eq2);
        HistoricoConsumo h7 = new HistoricoConsumo(new Date(18,01,05,19,16), new Date(18,01,06,11,00), eq2);
        HistoricoConsumo h8 = new HistoricoConsumo(new Date(18,01,01,19,01), new Date(18,01,02,11,00), eq13);
        HistoricoConsumo h9 = new HistoricoConsumo(new Date(18,01,02,19,00), new Date(18,01,03,10,29), eq13);
        HistoricoConsumo h10 = new HistoricoConsumo(new Date(18,01,03,19,30), new Date(18,01,04,10,32), eq13);
        HistoricoConsumo h11 = new HistoricoConsumo(new Date(18,01,04,19,00), new Date(18,01,05,10,30), eq13);
        HistoricoConsumo h12 = new HistoricoConsumo(new Date(18,01,05,19,16), new Date(18,01,06,11,00), eq13);
        HistoricoConsumo h13 = new HistoricoConsumo(new Date(18,06,03,15,16), new Date(), eq3);
        HistoricoConsumo h14 = new HistoricoConsumo(new Date(18,06,03,10,06), new Date(), eq4);
        HistoricoConsumo h15 = new HistoricoConsumo(new Date(18,06,06,22,49), new Date(), eq11);
        HistoricoConsumo h16 = new HistoricoConsumo(new Date(18,06,04,15,04), new Date(), eq12);
        HistoricoConsumo h17 = new HistoricoConsumo(new Date(18,06,03,15,16), new Date(18,06,04,15,04), eq14);
        HistoricoConsumo h18 = new HistoricoConsumo(new Date(18,06,03,10,13), new Date(), eq5);
        HistoricoConsumo h19 = new HistoricoConsumo(new Date(18,06,03,13,46), new Date(), eq6);
        HistoricoConsumo h20 = new HistoricoConsumo(new Date(18,06,02,12,01), new Date(), eq7);
        HistoricoConsumo h21 = new HistoricoConsumo(new Date(18,06,03,18,45), new Date(), eq8);
        HistoricoConsumo h22 = new HistoricoConsumo(new Date(18,06,04,13,27), new Date(), eq9);
        HistoricoConsumo h23 = new HistoricoConsumo(new Date(18,06,03,14,56), new Date(), eq10);
        HistoricoConsumo h24 = new HistoricoConsumo(new Date(18,06,8,1,27), new Date(), eq15);
        HistoricoConsumo h25 = new HistoricoConsumo(new Date(18,06,04,6,58), new Date(), eq16);
        HistoricoConsumo h26 = new HistoricoConsumo(new Date(18,05,03,13,27), new Date(), eq17);
        HistoricoConsumo h27 = new HistoricoConsumo(new Date(18,06,03,10,38), new Date(), eq18);
        HistoricoConsumo h28 = new HistoricoConsumo(new Date(18,01,04,19,00), new Date(18,01,05,10,30), eq19);
        HistoricoConsumo h29 = new HistoricoConsumo(new Date(18,01,05,19,16), new Date(18,01,06,11,00), eq20);
        HistoricoConsumo h30 = new HistoricoConsumo(new Date(18,06,03,15,16), new Date(), eq21);
        HistoricoConsumo h31 = new HistoricoConsumo(new Date(18,06,03,10,06), new Date(), eq22);
        HistoricoConsumo h32 = new HistoricoConsumo(new Date(18,06,06,22,49), new Date(), eq23);
        HistoricoConsumo h33 = new HistoricoConsumo(new Date(18,06,8,1,27), new Date(), eq24);
        HistoricoConsumo h34 = new HistoricoConsumo(new Date(18,06,04,6,58), new Date(), eq25);
        HistoricoConsumo h35 = new HistoricoConsumo(new Date(18,05,03,13,27), new Date(), eq26);
        HistoricoConsumo h36 = new HistoricoConsumo(new Date(18,06,03,10,38), new Date(), eq27);
        HistoricoConsumo h37 = new HistoricoConsumo(new Date(18,01,04,19,00), new Date(18,01,05,10,30), eq28);
        HistoricoConsumo h38 = new HistoricoConsumo(new Date(17,10,24,13,35),new Date(18,4,12,18,15),eq29);
        HistoricoConsumo h39 = new HistoricoConsumo(new Date(18,01,01,19,01), new Date(18,01,02,11,00), eq30);
        HistoricoConsumo h40 = new HistoricoConsumo(new Date(18,01,02,19,00), new Date(18,01,03,10,29), eq31);
        HistoricoConsumo h41 = new HistoricoConsumo(new Date(18,01,03,19,30), new Date(18,01,04,10,32), eq32);
        HistoricoConsumo h42 = new HistoricoConsumo(new Date(18,01,04,19,00), new Date(18,01,05,10,30), eq33);
        HistoricoConsumo h43 = new HistoricoConsumo(new Date(18,01,05,19,16), new Date(18,01,06,11,00), eq34);
        HistoricoConsumo h44 = new HistoricoConsumo(new Date(18,06,03,10,06), new Date(), eq35);
        HistoricoConsumo h45 = new HistoricoConsumo(new Date(18,06,06,22,49), new Date(), eq36);
        HistoricoConsumo h46 = new HistoricoConsumo(new Date(18,06,02,12,01), new Date(), eq37);
        HistoricoConsumo h47 = new HistoricoConsumo(new Date(18,06,03,18,45), new Date(), eq38);
        HistoricoConsumo h48 = new HistoricoConsumo(new Date(18,06,04,13,27), new Date(), eq39);
        
        //inseçao na redBlack
        eq1.inserirHistoricoConsumo(h1);
        eq1.inserirHistoricoConsumo(h2);
        eq2.inserirHistoricoConsumo(h7);
        eq2.inserirHistoricoConsumo(h6);
        eq2.inserirHistoricoConsumo(h5);
        eq2.inserirHistoricoConsumo(h4);
        eq2.inserirHistoricoConsumo(h3);
        eq13.inserirHistoricoConsumo(h8);
        eq13.inserirHistoricoConsumo(h9);
        eq13.inserirHistoricoConsumo(h10);
        eq13.inserirHistoricoConsumo(h11);
        eq13.inserirHistoricoConsumo(h12);
        eq3.inserirHistoricoConsumo(h13);
        eq4.inserirHistoricoConsumo(h14);
        eq5.inserirHistoricoConsumo(h18);
        eq6.inserirHistoricoConsumo(h19);
        eq7.inserirHistoricoConsumo(h20);
        eq8.inserirHistoricoConsumo(h21);
        eq9.inserirHistoricoConsumo(h22);
        eq10.inserirHistoricoConsumo(h23);
        eq11.inserirHistoricoConsumo(h15);
        eq12.inserirHistoricoConsumo(h16);
        eq14.inserirHistoricoConsumo(h17);
        eq15.inserirHistoricoConsumo(h24);
        eq16.inserirHistoricoConsumo(h25);
        eq17.inserirHistoricoConsumo(h26);
        eq18.inserirHistoricoConsumo(h27);
        eq19.inserirHistoricoConsumo(h28);
        eq20.inserirHistoricoConsumo(h29);
        eq21.inserirHistoricoConsumo(h30);
        eq22.inserirHistoricoConsumo(h31);
        eq23.inserirHistoricoConsumo(h32);
        eq24.inserirHistoricoConsumo(h33);
        eq25.inserirHistoricoConsumo(h34);
        eq26.inserirHistoricoConsumo(h35);
        eq27.inserirHistoricoConsumo(h36);
        eq28.inserirHistoricoConsumo(h37);
        eq29.inserirHistoricoConsumo(h38);
        eq30.inserirHistoricoConsumo(h39);
        eq31.inserirHistoricoConsumo(h40);
        eq32.inserirHistoricoConsumo(h41);
        eq33.inserirHistoricoConsumo(h42);
        eq34.inserirHistoricoConsumo(h43);
        eq35.inserirHistoricoConsumo(h44);
        eq36.inserirHistoricoConsumo(h45);
        eq37.inserirHistoricoConsumo(h46);
        eq38.inserirHistoricoConsumo(h47);
        eq39.inserirHistoricoConsumo(h48);
        
        //Crianção do grafo
        GrafoDirigido g = new GrafoDirigido(19);
        
        //Criação dos nós
        NoMoradia n0 = new NoMoradia(m1);
        NoMoradia n1 = new NoMoradia(m2);
        NoMoradia n2 = new NoMoradia(m3);
        NoMoradia n3 = new NoMoradia(m4);
        NoMoradia n4 = new NoMoradia(m5);
        NoMoradia n5 = new NoMoradia(m6);
        NoFonteEnergia n6 = new NoFonteEnergia(fg1);
        NoFonteEnergia n7 = new NoFonteEnergia(fg2);
        NoFonteEnergia n8 = new NoFonteEnergia(fg3);
        NoFonteEnergia n9 = new NoFonteEnergia(f4);
        NoFonteEnergia n10 = new NoFonteEnergia(f5);
        NoFonteEnergia n11 = new NoFonteEnergia(f1);
        NoFonteEnergia n12 = new NoFonteEnergia(f2);
        NoFonteEnergia n13 = new NoFonteEnergia(f3);
        NoMoradia n14 = new NoMoradia(m7);
        NoMoradia n15 = new NoMoradia(m8);
        NoMoradia n16 = new NoMoradia(m9);
        NoMoradia n17 = new NoMoradia(m10);
        NoMoradia n18 = new NoMoradia(m11);
        
        
        //Adiciona-se os nós ao grafo
        g.addNode(n0);
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);
        g.addNode(n5);
        g.addNode(n6);
        g.addNode(n7);
        g.addNode(n8);
        g.addNode(n9);
        g.addNode(n10);
        g.addNode(n11);
        g.addNode(n12);
        g.addNode(n13);
        g.addNode(n14);
        g.addNode(n15);
        g.addNode(n16);
        g.addNode(n17);
        g.addNode(n18);
        
        
        //Adiciona-se as arestas
        g.addEdge(n6, n2, 1000, r);
        g.addEdge(n6, n1, 1050, r);
        g.addEdge(n11, n0, 2000);
        g.addEdge(n0, n1, 2000,r);// energia vendida pela casa á rede
        g.addEdge(n12, n0, 2070);
        g.addEdge(n13, n0, 2970);
        g.addEdge(n7, n3, 1500, r);
        g.addEdge(n8, n4, 2000, r); 
        g.addEdge(n9, n5, 3000);
        g.addEdge(n10, n5, 3000);
        g.addEdge(n6, n14, 1050, r);
        g.addEdge(n7, n15, 1050, r);
        g.addEdge(n8, n16, 1050, r);
        g.addEdge(n8, n17, 1050, r);
        g.addEdge(n7, n18, 1050, r);
        
        //GuardarGrafoBin(g);
        
        System.out.println("Graph: " + g);
        System.out.println("Graph -> V: " + g.V());
        System.out.println("Graph -> edges: ");
        for (FlowEdge de : g.edges()) {
            System.out.println(de.from() + " " + de.to() + " " + de.capacity());
        }
        System.out.println("Graph -> ADJ - n6:");
        for (FlowEdge de : g.adj(n6.getId())) {
            System.out.println(de.from() + " " + de.to() + " " + de.flow());
        }

        int source = n6.getId(), destination = n1.getId();

        FordFulkerson maxflow = new FordFulkerson(g, source, destination);
        // compute maximum flow and minimum cut
        System.out.println("Max flow from " + source + " to " + destination);
        for (int v = 0; v < g.V(); v++) {
            for (FlowEdge e : g.adj(v)) {
                if ((v == e.from()) && e.flow() > 0) {
                    System.out.println("   " + e);
                }
            }
        }

        // print min-cut
        System.out.print("Min cut: ");
        for (int v = 0; v < g.V(); v++) {
            if (maxflow.inCut(v)) {
                System.out.print(v + " ");
            }
        }
        System.out.println();

        System.out.println("Max flow value = " + maxflow.value());
        
        EdgeWeightedDigraph gAux = new EdgeWeightedDigraph(g.V());
        for(FlowEdge f : g.edges()){
            gAux.addEdge(new DirectedEdge(f.from(),f.to(),f.capacity()));
        }
        System.out.println("Graph -> edges: ");
        System.out.println("Dijkstra version:");
        System.out.println(g);
        DijkstraSP dijMST = new DijkstraSP(gAux,n6.getId());
        for( DirectedEdge e : dijMST.pathTo(n1.getId()))
            System.out.println(e);
        
    }
    public static void lerGrafoBin() throws FileNotFoundException, IOException, ClassNotFoundException{
        String filename = ".//data//GrafoDirigidoBin.bin";
        File f = new File(filename);
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        GrafoDirigido g = (GrafoDirigido)ois.readObject();
        for (FlowEdge de : g.edges()) {
            for(NoFonteEnergia gi : g.getNodesF())
                if(gi.getId()==de.from())
                    System.out.print("[" + gi.getId() +"]"+ gi.getFonte().getTipo() +"->");
            for(NoMoradia gi :  g.getNodesM()){
                if(gi.getId()==de.from())
                    System.out.print("[" + gi.getId() +"]"+gi.getM().getProprietario() +" -> ");
                else if(gi.getId()==de.to())
                    System.out.print("[" + gi.getId() +"]"+gi.getM().getProprietario());
            }
            System.out.println("\t"+de.capacity());
        }
    }
    public static void GuardarGrafoBin(GrafoDirigido g) throws FileNotFoundException, IOException{
        String filename = ".//data//GrafoDirigidoBin.bin";
    File j = new File(filename);
    FileOutputStream fos = new FileOutputStream(j);
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(g);
    }

}
