package parser;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//global String nome="traccia.txt";

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author peppino
 */
public class Parser {

    String nomefile;
    void parser() {nomefile="";}
    void settaFile(String s) {
        this.nomefile=s;
    }

    void parse(int run) {
        File f=new File(this.nomefile);
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String s;
            int i =0 ;
            System.out.println("Parsing...");
            //solo per stampare output...
            int oldID=0; int count=0;
            //Creazione file
            FileOutputStream file = new FileOutputStream("/Users/Matteo/Desktop/PARSERED/training_"+run+".txt");
            PrintStream Output = new PrintStream(file);
            //Per le coordinate iniziali scriviamo su file ogni tre righe
            int idp=0; double xp=0; double yp=0; //v e t sono pari a 0 inizialmente
            //while per l'intero file
            while (true){
              s = br.readLine();
              //condizione di uscita fine file
              if (s==null) break;
              //stringa non vuota quindi si può procedere alla ricerca
              //definiamo cosa si cerca nella stringa s
              //Pattern p = Pattern.compile(".*?(\\d+\\.?\\d*)\\s+(\\d+\\.?\\d*)");
              //Matcher matcher = p.matcher(s);
              //while(matcher.find()) {
              //  System.out.println("Sottosequenza : "+matcher.group());
              //  //System.out.println("Sottogruppo 1 : "+matcher.group(1));
              //}
             String[] tmp = s.split("[ \t]+");
             char[] ch = s.toCharArray();
             int n = tmp.length;
             //Si tratta di una stringa per la scelta della posizione iniziale del nodo
             //$node_
             if ((ch.length!=0)&&(ch[0]=='$')&&(ch[2]=='o')) {
                //avremo la stringa $node_(XX)
                String[] idstring = tmp[n-4].split("[_\t]+");
                //togliamo le parentesi
                int nid = idstring.length;
                String idpar=idstring[nid-1];
                String[] idok=idstring[nid-1].split("[()\t]+");
                int id = Integer.parseInt(idok[idok.length-1]);
                double val = Double.parseDouble(tmp[n - 1]);
                if (ch[14]=='X') {
                 //System.out.println("id: "+id+" X: "+val);
                 idp=id;
                 xp=val;
                }
                 else if (ch[14]=='Y') {
                 //System.out.println("id: "+id+" Y: "+val);
                 yp=val;
                 //Commenta questa per disabilitare questa scrittura su file, tanto la posizione iniziale
                 //è trascurabile
                 Output.println(idp+"\t"+xp+"\t"+yp+"\t0.0\t0.0");
                 }
                 else {
                 //System.out.println("id: "+id+" v: "+val+" t: 0s");
                 }
             }//if stringa posizione iniziale
             //si tratta di una stringa relativa allo spostamento
             //$ns
             else if ((ch.length!=0)&&(ch[0]=='$')&&(ch[2]=='s')){
             //in tmp ho già le stringhe derivanti dall'originale splittata in base al carattere SPACE
             //avremo la stringa $node_(XX)
                String[] idstring = tmp[n-5].split("[_\t]+");
                //togliamo le parentesi
                int nid = idstring.length;
                String idpar=idstring[nid-1];
                String[] idok=idstring[nid-1].split("[()\t]+");
                int id = Integer.parseInt(idok[idok.length-1]);
                //la stringa velocità contiene un doppio apice alla fine
                StringBuffer velocita = new StringBuffer(tmp[n - 1]);
                //eliminiamo il doppio apice
                velocita=velocita.delete(velocita.length()-1,velocita.length());
                double v = Double.parseDouble(velocita.toString());
                double y = Double.parseDouble(tmp[n - 2]);
                double x = Double.parseDouble(tmp[n - 3]);
                double t = Double.parseDouble(tmp[n - 6]);
                //System.out.println ("id: "+id+" x: "+x+" y: "+y+ " v: "+v+" t: "+t+"s.");
                //System.out.print("...");
                if (count++>=20) {
                    count=0;
                    System.out.println();
                }
                if (id!=oldID) {
                    System.out.println("id: "+oldID+" done!");
                    oldID=id;
                }
                Output.println(id+"\t"+x+"\t"+y+"\t"+v+"\t"+t);
             }
             //System.out.println(i++);
            }//while della stringa
            System.out.println("\n... last id done!");
        } catch (Exception ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }

}

}

