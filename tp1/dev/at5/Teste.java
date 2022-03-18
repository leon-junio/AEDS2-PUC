class Teste {
    public static boolean isFim(int entrada) {
        boolean resp = true;
        if (entrada == 0) {
            resp = true;
        } else {
            resp = false;
        }
        return resp;
    }

    public static String replace(String entrada, char antiga, char nova) {
        String resp = "";
        for (int i = 0; i < entrada.length(); i++) {
            if (entrada.charAt(i) == antiga) {
                resp = resp + nova;
            } else {
                if (entrada.charAt(i) != ' ') {
                    resp = resp + entrada.charAt(i);
                }
            }
        }
        return resp;
    }

    public static String replace(String frase, String antiga, String nova) {
        String resp = "", aux = "";
        int j = 0;
        System.out.println(frase+" = "+antiga+" e "+nova);
        for (int i = 0; i < frase.length(); i++) {
            if (frase.charAt(i) == antiga.charAt(j)) {
                if (j == antiga.length() - 1) {
                    resp += nova;
                    j = 0;
                    aux = "";
                } else {
                    j++;
                    aux += frase.charAt(i);
                }
            } else {
                j = 0;
                if (aux.length() > 0) {
                    resp += aux + frase.charAt(i);
                    aux = "";
                } else {
                    resp += frase.charAt(i);
                }
            }
        }
        System.out.println("R: "+resp);
        return resp;
    }

    public static String substring(String entrada, int inicio, int fim) {
        String resp = "";
        for (int i = inicio; i < fim; i++) {
            resp = resp + entrada.charAt(i);
        }
        return resp;
    }

    public static String CalculoBooleano(String entrada, int[] valores) {
        String conta = "", aux = "";
        int i = 0;

        for (int p = 0; p < valores.length; p++) {
            String num = "" + valores[p];
            if (p == 0) {
                entrada = replace(entrada, 'A', num.charAt(0));
            } else if (p == 1) {
                entrada = replace(entrada, 'B', num.charAt(0));
            } else if (p == 2) {
                entrada = replace(entrada, 'C', num.charAt(0));
            }
        }

        while (entrada.length() != 1) {
            if (entrada.charAt(i) == '(') {
                for (int h = i + 1; h < entrada.length(); h++) {
                    if (entrada.charAt(h) == ')') {
                        System.out.println("CT: " + conta);
                        if (entrada.charAt(i - 1) == 'd') {
                            aux = "1";
                            for (int r = 0; r < conta.length(); r++) {
                                if (conta.charAt(r) != ',') {
                                    if (conta.charAt(r) == '0') {
                                        aux = "0";
                                        r = conta.length();
                                    }

                                }
                            }
                            String help = "and(";
                            entrada = replace(entrada, help + conta + ")", aux);
                            // entrada = entrada.replace(help + conta + ")", aux);
                            System.out.println("Zerou 1 " + entrada);
                            i = 0;
                            conta = "";
                            h = entrada.length();
                        } else if (entrada.charAt(i - 1) == 't') {
                            for (int r = 0; r < conta.length(); r++) {
                                if (conta.charAt(r) != ',') {
                                    if (conta.charAt(r) == '0') {
                                        aux = "1";
                                        r = conta.length();
                                    } else if (conta.charAt(r) == '1') {
                                        aux = "0";
                                        r = conta.length();
                                    }
                                }
                            }
                            String help = "not(";
                            entrada = replace(entrada, help + conta + ")", aux);
                            // entrada = entrada.replace(help + conta + ")", aux);
                            System.out.println("Zerou 2 " + entrada);
                            i = 0;
                            conta = "";
                            h = entrada.length();
                        } else if (entrada.charAt(i - 1) == 'r') {
                            aux = "0";
                            for (int r = 0; r < conta.length(); r++) {
                                if (conta.charAt(r) != ',') {
                                    if (conta.charAt(r) == '1') {
                                        aux = "1";
                                        r = conta.length();
                                    }
                                }
                            }
                            String help = "or(";
                            entrada = replace(entrada, help + conta + ")", aux);
                            // entrada = entrada.replace(help + conta + ")", aux);
                            System.out.println("Zerou 3 " + entrada);
                            i = 0;
                            conta = "";
                            h = entrada.length();
                        }

                    } else if (entrada.charAt(h) == '(') {
                        h = entrada.length();
                        conta = "";
                    } else {
                        conta = conta + entrada.charAt(h);
                    }

                }
            }
            i++;
        }
        return entrada;
    }

    public static void main(String args[]) {
        int n = 1, valores[];
        String entrada = "", saida = "";

        do {
            n = MyIO.readInt();
            if (isFim(n) == false) {
                valores = new int[n];
                for (int i = 0; i < n; i++) {
                    valores[i] = MyIO.readInt();
                }
                entrada = MyIO.readLine();
                saida = CalculoBooleano(entrada, valores);
                MyIO.println(saida);
            }
        } while (isFim(n) == false);
    }
}
