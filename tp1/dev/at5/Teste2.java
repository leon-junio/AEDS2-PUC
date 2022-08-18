class Teste2{
    public static boolean isFim(int entrada)
    {
        return(entrada==0);
    }

    public static String replace(String entrada, char antiga, char nova) {
        String resp = "";
        for (int i = 0; i < entrada.length(); i++) 
        {
            if (entrada.charAt(i) == antiga) 
            {
                resp=resp+nova;
            } 
            else 
            {
                if (entrada.charAt(i) != ' ') 
                {
                    resp=resp+entrada.charAt(i);
                }
            }
        }
        return resp;
    }

    public static String substring(String entrada, int inicio, int fim) {
        String resp = "";
        for (int i = inicio; i < fim; i++) 
        {
            resp=resp+entrada.charAt(i);
        }
        return resp;
    }

    public static String CalculoBooleano(String entrada, int[] valores, int i)
    {
        String conta="", aux="";

        for (int p=0;p<valores.length;p++) 
        {
            String num=""+valores[p];

            if(p==0)
            {
                entrada = replace(entrada,'A', num.charAt(0));
            }
            else if(p==1)
            {
                entrada = replace(entrada,'B', num.charAt(0)); 
            }
            else if(p==2)
            {
                entrada = replace(entrada,'C', num.charAt(0));
            }
        }

        if(i<entrada.length())
        {
            if(entrada.charAt(i)=='(')
            {
                for(int h=i+1;h<entrada.length();h++)
                {
                    if(entrada.charAt(h)==')')
                    {
                        if(entrada.charAt(i-1)=='d')
                        {
                            for(int r=0;r<conta.length();r++)
                            {
                                if(conta.charAt(r) != ',')
                                {
                                    if(conta.charAt(r)=='0')
                                    {
                                        aux="0";
                                        r=conta.length();
                                    }

                                }
                                if(conta.length()-1==r)
                                {
                                    aux="1";
                                }
                            }

                            String help="and(";
                            entrada=entrada.replace(help+conta+")", aux);
                            i=0;
                            h=entrada.length();
                            conta="";

                        }
                        else if(entrada.charAt(i-1)=='t')
                        {
                            for(int r=0;r<conta.length();r++)
                            {
                                
                                if(conta.charAt(r)=='0')
                                {
                                    aux="1";
                                    r=conta.length();
                                }
                                
                                else if(conta.charAt(r)=='1')
                                {
                                    aux="0";
                                    r=conta.length();
                                }
                            }

                            String help="not(";
                            entrada=entrada.replace(help+conta+")", aux);
                            i=0;
                            h=entrada.length();
                            conta="";

                        }

                        else if(entrada.charAt(i-1)=='r')
                        {
                            for(int r=0;r<conta.length();r++)
                            {
                                if(conta.charAt(r) != ',')
                                {
                                    if(conta.charAt(r)=='1')
                                    {
                                        aux="1";
                                        r=conta.length();
                                    }

                                }
                                if(conta.length()-1==r)
                                {
                                    aux="0";
                                }
                            }

                            String help="or(";
                            entrada=entrada.replace(help+conta+")", aux);
                            i=0;
                            h=entrada.length();
                            conta="";

                        }

                    }
                    else if(entrada.charAt(h)=='(')
                    {
                        h=entrada.length();
                        conta="";
                    }
                    else
                    {
                        conta=conta+entrada.charAt(h);
                    }

                }
            }

            entrada=CalculoBooleano(entrada, valores, i+1);
        }
        if(i==entrada.length())
        {
            return entrada;
        }

        return entrada;

    }

    public static void main(String args[])
    {
        int n=1, valores[];
        String entrada="", saida="";
        
        do 
        {
            n=MyIO.readInt();

            if(isFim(n)==false)
            {
                valores=new int[n];

                for(int i=0;i<n;i++)
                {
                    valores[i]=MyIO.readInt();
                }

                entrada=MyIO.readLine();
                saida=CalculoBooleano(entrada, valores, 0);

                MyIO.println(saida);
            }
            
        }while(isFim(n)==false);
    }
}
