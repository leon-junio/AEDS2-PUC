class Tp1Q14 {
    /**
     * Função responsável por gerar a resposta Verdadeiro ou Falso para a expressão
     * booleana
     * Ela chama outras funções para tratar da String e gerar uma resposta
     * 
     * @param valores Array com os inteiros passados para as posições da expressão
     * @param exp     A expressão tal como foi digitada sem sofrer alterações
     * @return A expressão retorna 1 caso seja verdadeira e 0 caso seja falsa
     */
    public static String calcularBool(int[] valores, String exp) {
        String values = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < valores.length; i++) {
            String num = "" + valores[i];
            exp = myReplace(exp, ((char) values.charAt(i)), num.charAt(0));
        }
        return scanString(exp, 0);
    }

    /**
     * Função responsável por verificar uma boleano que esta dentro de parenteses
     * de uma expressão booleana e retornar o valor referente a sua resposta
     * 
     * @param txt boleano informada com um char para informar qual operação está
     *            sendo
     *            tratada
     *            (d - and(); r - or(); t - not();) + números (0 & 1)
     * @return 1 ou 0 dependendo da expressão e seu resultado
     */
    public static String verificar(String txt) {
        String resp = "1";
        for (int i = 0; i < txt.length(); i++) {
            if (txt.charAt(i) == 'd') {
                while (i < txt.length()) {
                    if (txt.charAt(i) != ',') {
                        if (txt.charAt(i) == '0') {
                            resp = "0";
                            i = txt.length();
                            break;
                        }
                    }
                    i++;
                }
            } else if (txt.charAt(i) == 't') {
                while (i < txt.length()) {
                    if (txt.charAt(i) != ',') {
                        if (txt.charAt(i) == '0') {
                            resp = "1";
                            i = txt.length();
                            break;
                        } else if (txt.charAt(i) == '1') {
                            resp = "0";
                            i = txt.length();
                            break;
                        }
                    }
                    i++;
                }
            } else if (txt.charAt(i) == 'r') {
                resp = "0";
                while (i < txt.length()) {
                    if (txt.charAt(i) != ',') {
                        if (txt.charAt(i) == '1') {
                            resp = "1";
                            i = txt.length();
                            break;
                        }
                    }
                    i++;
                }
            }
        }
        return resp;
    }

    // Função responsável por pegar o último char de uma operação e definir qual é a
    // mesma
    // Podendo ser and, or ou not (d,r,t)
    public static String checkOperation(char letter) {
        if (letter == 'd') {
            return "and";
        } else if (letter == 'r') {
            return "or";
        } else if (letter == 't') {
            return "not";
        } else {
            return null;
        }
    }

    /**
     * Função responsável por realizar a separação da expressão em pequenos pedaços
     * de boleanos únicas
     * Cada pedaço é resolvido separadamente, a expressão é resolvida de dentro para
     * fora sempre resolvendo
     * a última boleano possível da expressão até restar uma resposta válida
     * 
     * @param expressao Expressão já formatada com os devidos valores e sem espaços
     * @return Verdade caso o número resultante for 1 e falso caso o número
     *         resultante for 0
     */
    public static String scanString(String expressao, int index) {
        String boleano = "";
        if (index < expressao.length()) {
            if (expressao.charAt(index) == '(') {
                for (int hindex = index + 1; hindex < expressao.length(); hindex++) {
                    if (expressao.charAt(hindex) == ')') {
                        char letter = expressao.charAt(index - 1);
                        expressao = myReplace(expressao, checkOperation(letter) + "(" + boleano + ")",
                                verificar(letter + boleano));
                        index = 0;
                        hindex = expressao.length();
                        boleano = "";
                    } else if (expressao.charAt(hindex) == '(') {
                        hindex = expressao.length();
                        boleano = "";
                    } else {
                        boleano += expressao.charAt(hindex);
                    }
                }
            }
            expressao = scanString(expressao, index + 1);
        }
        if (index == expressao.length()) {
            return expressao;
        }
        return expressao;
    }

    /**
     * Função que simula a String.replace() e a String.trim()
     * 
     * @param frase  Frase para ser formatada
     * @param antiga Char para ser alterado
     * @param nova   Novo char para ser adicionado
     * @return Frase de expressao formatada e pronta para uso
     */
    public static String myReplace(String frase, char antiga, char nova) {
        String resp = "";
        for (int i = 0; i < frase.length(); i++) {
            if (frase.charAt(i) == antiga) {
                resp += nova;
            } else {
                // trim para remover espaços
                if (frase.charAt(i) != ' ') {
                    resp += frase.charAt(i);
                }
            }
        }
        return resp;
    }

    /**
     * Função que simula a String.replace() para strings
     * 
     * @param frase  Frase para ser formatada
     * @param antiga String para ser alterado
     * @param nova   String para ser adicionado
     * @return Frase de expressao formatada e pronta para uso
     */
    public static String myReplace(String str, String str_old, String str_new) {
        String resp = "", auxresp = "";
        boolean eql = false;
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            // System.out.println(str.charAt(i) + " && " + str_old.charAt(j));
            if (str.charAt(i) == str_old.charAt(j)) {
                eql = true;
                j++;
                auxresp += str.charAt(i);
            } else {
                if (eql) {
                    j = 0;
                    if (str.charAt(i) == str_old.charAt(j)) {
                        resp += auxresp;
                        auxresp = "";
                        eql = true;
                        j++;
                        auxresp += str.charAt(i);
                    } else {
                        eql = false;
                        resp += auxresp += str.charAt(i);
                        auxresp = "";
                    }
                } else {
                    resp += str.charAt(i);
                }
            }
            if (eql) {
                if (j == str_old.length()) {
                    resp += str_new;
                    auxresp = "";
                    j = 0;
                    eql = false;
                }
            }
        }
        return resp;
    }

    /**
     * Função para preenchimento do vetor de valores para a expressão
     * 
     * @param valores Array já declarado de valores
     * @param qtd     Quantidade informada pelo usuário de valores que vão ser
     *                inseridos
     * @return Um array preenchido com um número em cada posição
     */
    public static int[] preencherVetor(int[] valores, int qtd) {
        valores = new int[qtd];
        for (int i = 0; i < valores.length; i++) {
            valores[i] = MyIO.readInt();
        }
        return valores;
    }

    public static void main(String[] args) {
        String exp = "";
        int qtdValores = 0;
        int[] valores = null;
        // System.out.println(myReplace("or(or(0,0),0)", "or(0,0)", "0"));
        do {
            qtdValores = MyIO.readInt();
            if (qtdValores != 0) {
                valores = preencherVetor(valores, qtdValores);
                exp = MyIO.readLine();
                MyIO.println(calcularBool(valores, exp));
            }
        } while (qtdValores != 0);

    }
}
