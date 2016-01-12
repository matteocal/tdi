package parser;

public class Main {

	    /**
	     * @param args the command line arguments
	     */
	    public static void main(String[] args) {
	        for (int i=0; i<10; i++) {
	        String nome="/Users/Matteo/Desktop/UNPARSERED/BELVEDERE_SS18_"+(i+1)+".tcl";
	        Parser p=new Parser();
	        p.settaFile(nome);
	        p.parse(i);
	        }
	        // TODO code application logic here
	    }

	}

