/* Leitura de P ́agina HTML em Java - Leia duas strings sendo que a primeira  ́e o nome de
uma p ́agina web e a segunda, seu endere ̧co. Por exemplo, “Pontif ́ıcia Universidade Cat ́olica de
Minas Gerais” e “www.pucminas.br”. Em seguida, mostre na tela o n ́umero de vogais (sem e
com acento), consoantes e dos padr ̃oes “< br >” e “< table >” que aparecem no c ́odigo dessa
p ́agina. A entrada padr ̃ao  ́e composta por v ́arias linhas. Cada uma cont ́em v ́arias strings sendo
que a primeira  ́e um endere ̧co web e as demais o nome dessa p ́agina web. A  ́ultima linha da
entrada padr ̃ao cont ́em a palavra “FIM”. A sa ́ıda padr ̃ao cont ́em v ́arias linhas sendo que cada
uma apresenta o n ́umero de ocorrˆencia (valor xi entre parˆenteses) de cada caractere ou string
solicitado. Cada linha de sa ́ıda ser ́a da seguinte forma: a(x1) e(x2) i(x3) o(x4) u(x5)  ́a(x6)  ́e(x7)
 ́ı(x8)  ́o(x9)  ́u(x10) `a(x11) `e(x12) `ı(x13) `o(x14) `u(x15)  ̃a(x16)  ̃o(x17) ˆa(x19) ˆe(x19) ˆı(x20) ˆo(x21)
ˆu(x22) consoante(x23) < br >(x24) < table >(x25) nomep ́agina(x26). */

//Autor: Rubens Castro
//data de criação: 
//última atualização:

import java.io.*;
import java.net.*;

class tp1exe8{
    public static boolean isFim(String entrada){
        return(entrada.length()==3 && entrada.charAt(0)=='F' && entrada.charAt(1)=='I' && entrada.charAt(2)=='M');
    }

    public static String getHtml(String link) {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String resp = "", line;
        
        try
        {
            url = new URL(link);
            is = url.openStream(); // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));
   
            while ((line = br.readLine()) != null) {
               resp += line + "\n";
            }
        } 
        catch (MalformedURLException mue) 
        {
            mue.printStackTrace();
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
        }
   
        try {
            is.close();
        } catch (IOException ioe) {
            // nothing to see here
        }
   
        return resp;
      
    }

    public static boolean verifyA(char in)
    {
        boolean resp=false;

        if(in=='a')
        {
            resp=true;
        }

        return resp;
    }
    public static boolean verifyE(char in)
    {
        boolean resp=false;

        if(in=='e')
        {
            resp=true;
        }

        return resp;
    }
    public static boolean verifyI(char in)
    {
        boolean resp=false;

        if(in=='i')
        {
            resp=true;
        }

        return resp;
    }
    public static boolean verifyO(char in)
    {
        boolean resp=false;

        if(in=='o')
        {
            resp=true;
        }

        return resp;
    }
    public static boolean verifyU(char in)
    {
        boolean resp=false;

        if(in=='u')
        {
            resp=true;
        }

        return resp;
    }
    public static boolean verifyAagudo(char in)
    {
        boolean resp=false;

        if(in=='á')
        {
            resp=true;
        }

        return resp;
    }
    public static boolean verifyEagudo(char in)
    {
        boolean resp=false;

        if(in=='é')
        {
            resp=true;
        }

        return resp;
    }
    public static boolean verifyIagudo(char in)
    {
        boolean resp=false;

        if(in=='í')
        {
            resp=true;
        }

        return resp;
    }
    public static boolean verifyOagudo(char in)
    {
        boolean resp=false;

        if(in=='ó')
        {
            resp=true;
        }

        return resp;
    }
    public static boolean verifyUagudo(char in)
    {
        boolean resp=false;

        if(in=='ú')
        {
            resp=true;
        }

        return resp;
    }
    public static boolean verifyAcrase(char in)
    {
        boolean resp=false;

        if(in=='à')
        {
            resp=true;
        }

        return resp;
    }
    public static boolean verifyEcrase(char in)
    {
        boolean resp=false;

        if(in=='è')
        {
            resp=true;
        }

        return resp;
    }
    public static boolean verifyIcrase(char in)
    {
        boolean resp=false;

        if(in=='ì')
        {
            resp=true;
        }

        return resp;
    }
    public static boolean verifyOcrase(char in)
    {
        boolean resp=false;

        if(in=='ò')
        {
            resp=true;
        }

        return resp;
    }
    public static boolean verifyUcrase(char in)
    {
        boolean resp=false;

        if(in=='ù')
        {
            resp=true;
        }

        return resp;
    }
    public static boolean verifyAtio(char in)
    {
        boolean resp=false;

        if(in=='ã')
        {
            resp=true;
        }

        return resp;
    }
    public static boolean verifyOtio(char in)
    {
        boolean resp=false;

        if(in=='õ')
        {
            resp=true;
        }

        return resp;
    }
    public static boolean verifyAcircun(char in)
    {
        boolean resp=false;

        if(in=='â')
        {
            resp=true;
        }

        return resp;
    }
    public static boolean verifyEcircun(char in)
    {
        boolean resp=false;

        if(in=='ê')
        {
            resp=true;
        }

        return resp;
    }
    public static boolean verifyIcircun(char in)
    {
        boolean resp=false;

        if(in=='î')
        {
            resp=true;
        }

        return resp;
    }
    public static boolean verifyOcircun(char in)
    {
        boolean resp=false;

        if(in=='ô')
        {
            resp=true;
        }

        return resp;
    }
    public static boolean verifyUcircun(char in)
    {
        boolean resp=false;

        if(in=='û')
        {
            resp=true;
        }

        return resp;
    }
    public static boolean verifyConsoante(char in)
    {
        boolean resp=false;
        if(in>='a' && in<='z')
        {
            if(in!='a'&& in!='e'&& in!='i'&& in!='o'&& in!='u')
            {
                resp=true;
            }
        }

        return resp;
    }

    public static void resposta(String html, String endereco)
    {
        int a=0, e=0, i=0, o=0, u=0, aAg=0, eAg=0, iAg=0, oAg=0, uAg=0, aCra=0, eCra=0, iCra=0, oCra=0, uCra=0, aTio=0, oTio=0, aCir=0,
        eCir=0, iCir=0, oCir=0, uCir=0, consoante=0, br=0, table=0;   

        for(int j=0;j<html.length();j++)
        {
            if(verifyA(html.charAt(j))==true)
            {
                a++;
            }
            else if(verifyE(html.charAt(j))==true)
            {
                e++;
            }
            else if(verifyI(html.charAt(j))==true)
            {
                i++;
            }
            else if(verifyO(html.charAt(j))==true)
            {
                o++;
            }
            else if(verifyU(html.charAt(j))==true)
            {
                u++;
            }
 	        else if(verifyAagudo(html.charAt(j))==true)
            {
                aAg++;
            }
	        else if(verifyEagudo(html.charAt(j))==true)
            {
                eAg++;
            }
	        else if(verifyIagudo(html.charAt(j))==true)
            {
                iAg++;
            }
	        else if(verifyOagudo(html.charAt(j))==true)
            {
                oAg++;
            }
	        else if(verifyUagudo(html.charAt(j))==true)
            {
                uAg++;
            }
	        else if(verifyAcrase(html.charAt(j))==true)
            {
                aCra++;
            }
	        else if(verifyEcrase(html.charAt(j))==true)
            {
                eCra++;
            }
	        else if(verifyIcrase(html.charAt(j))==true)
            {
                iCra++;
            }
	        else if(verifyOcrase(html.charAt(j))==true)
            {
                oCra++;
            }
	        else if(verifyUcrase(html.charAt(j))==true)
            {
                uCra++;
            }
	        else if(verifyAtio(html.charAt(j))==true)
            {
                aTio++;
            }
	        else if(verifyOtio(html.charAt(j))==true)
            {
                oTio++;
            }
	        else if(verifyAcircun(html.charAt(j))==true)
            {
                aCir++;
            }
	        else if(verifyEcircun(html.charAt(j))==true)
            {
                eCir++;
            }
	        else if(verifyIcircun(html.charAt(j))==true)
            {
                iCir++;
            }
	        else if(verifyOcircun(html.charAt(j))==true)
            {
                oCir++;
            }
	        else if(verifyUcircun(html.charAt(j))==true)
            {
                uCir++;
            }
	        else if(verifyConsoante(html.charAt(j))==true)
            {
                consoante++;
            }
	    
	        if(html.charAt(j)=='<')
	        {
                if(html.charAt(j+1)=='b' && html.charAt(j+2)=='r' && html.charAt(j+3)=='>')
                {
                    br++;
					consoante -= 2;
                }
            
                if(html.charAt(j+1)=='t' && html.charAt(j+2)=='a' && html.charAt(j+3)=='b' && html.charAt(j+4)=='l'
                && html.charAt(j+5)=='e' && html.charAt(j+6)=='>')
                {
                    table++;
					consoante -= 3;
					a--;
					e--;
                }
	        }
            
        }

	    System.out.print("a(" + a + ") e(" + e + ") i(" + i + ") o(" + o + ") u(" + u + ") á(" + aAg + ") é(" + eAg 
        + ") í(" + iAg + ") ó(" + oAg+ ") ú(" + uAg + ") à(" + aCra + ") è(" + eCra + ") ì(" + iCra + ") ò("+ oCra 
        + ") ù(" + uCra + ") ã(" + aTio + ") õ("  + oTio + ") â(" + aCir + ") ê(" + eCir + ") î(" + iCir 
        + ") ô(" + oCir + ") û(" + uCir + ") consoante(" + consoante + ") <br>(" + br + ") <table>(" + table + ") " + 
        endereco + "\n");
    }

    public static void main(String args[])
    {
        String html, endereco="", name="";

        while(isFim(name)==false)
        {
            name=MyIO.readLine();

            if(isFim(name)==false)
            {
                //MyIO.println(""+verifyConsoante(name.charAt(0)));
				endereco=MyIO.readLine();
                html=getHtml(endereco);
                resposta(html, name);
            }
        }
    }
}