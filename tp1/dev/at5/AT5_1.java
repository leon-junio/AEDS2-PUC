class AT5_1 {

    public static String replace(String frase, char antiga, char nova) {
        String resp = "";
        for (int i = 0; i < frase.length(); i++) {
            if (frase.charAt(i) == antiga) {
                resp = resp + nova;
            } else {
                if (frase.charAt(i) != ' ') {
                    resp = resp + frase.charAt(i);
                }
            }
        }
        return resp;
    }

    public static String replace(String frase, String antiga, String nova) {
        String resp = "", aux = "";
        int j = 0;
        System.out.println(frase + " = " + antiga + " e " + nova);
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
        return resp;
    }

    public static String substring(String frase, int inicio, int fim) {
        String resp = "";
        for (int i = inicio; i < fim; i++) {
            resp = resp + frase.charAt(i);
        }
        return resp;
    }

    public static String and(String conta) {
        String resposta = "1";
        for (int r = 0; r < conta.length(); r++) {
            if (conta.charAt(r) != ',') {
                if (conta.charAt(r) == '0') {
                    resposta = "0";
                    r = conta.length();
                }
            }
        }
        return resposta;
    }

    public static String or(String conta) {
        String resposta = "1";
        for (int r = 0; r < conta.length(); r++) {
            if (conta.charAt(r) != ',') {
                if (conta.charAt(r) == '1') {
                    resposta = "1";
                    r = conta.length();
                }
            }
        }
        return resposta;
    }

    public static String not(String conta) {
        String resposta = "1";
        for (int r = 0; r < conta.length(); r++) {
            if (conta.charAt(r) != ',') {
                if (conta.charAt(r) == '0') {
                    resposta = "1";
                    r = conta.length();
                } else if (conta.charAt(r) == '1') {
                    resposta = "0";
                    r = conta.length();
                }
            }
        }
        return resposta;
    }

    public static String CalculoBooleano(String expressao, int[] valores) {
        //montando a expressao e substituindo as letras por numeros 0 ou 1
        String letrasBooleanas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < valores.length; i++) {
            String num = "" + valores[i];
            expressao = replace(expressao, letrasBooleanas.charAt(i), num.charAt(0));
        }
        //Calculos com a expressão ja montada
        String conta = "";
        char letraConta = ' ';
        int index = 0;
        while (expressao.length() != 1) {
            if (expressao.charAt(index) == '(') {
                letraConta = expressao.charAt(index - 1);
                for (int caminhar = index + 1; caminhar < expressao.length(); caminhar++) {
                    if (expressao.charAt(caminhar) == ')') {
                        if (letraConta == 'd') {
                            expressao = replace(expressao, "and(" + conta + ")", and(conta));
                        } else if (letraConta == 't') {
                            expressao = replace(expressao, "not(" + conta + ")", not(conta));
                        } else if (letraConta == 'r') {
                            expressao = replace(expressao, "or(" + conta + ")", or(conta));
                        }
                        index = 0;
                        conta = "";
                        caminhar = expressao.length();
                    } else if (expressao.charAt(caminhar) == '(') {
                        caminhar = expressao.length();
                        conta = "";
                    } else {
                        conta += expressao.charAt(caminhar);
                    }
                }
            }
            index++;
        }
        return expressao;
    }

    public static boolean isFim(int expressao) {
        boolean resp = true;
        if (expressao == 0) {
            resp = true;
        } else {
            resp = false;
        }
        return resp;
    }

    public static void main(String args[]) {
        int num = 1, valores[];
        String expressao = "", saida = "";
        do {
            num = MyIO.readInt();
            if (isFim(num) == false) {
                valores = new int[num];
                for (int i = 0; i < num; i++) {
                    valores[i] = MyIO.readInt();
                }
                expressao = MyIO.readLine();
                saida = CalculoBooleano(expressao, valores);
                MyIO.println(saida);
            }
        } while (isFim(num) == false);
    }
}
