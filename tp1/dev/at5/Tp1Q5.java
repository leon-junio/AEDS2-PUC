class Tp1Q5 {

	/**
	 * Função responsável por gerar a resposta Verdadeiro ou Falso para a expressão
	 * booleana
	 * Ela chama outras funções para tratar da String e gerar uma resposta
	 * 
	 * @param valores Array com os inteiros passados para as posições da expressão
	 * @param exp     A expressão tal como foi digitada sem sofrer alterações
	 * @return A expressão retorna 1 caso seja verdadeira e 0 caso seja falsa
	 */
	public static int calcularBool(int[] valores, String exp) {
		String values = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < valores.length; i++) {
			String num = "" + valores[i];
			exp = myReplace(exp, ((char) values.charAt(i)), num.charAt(0));
		}
		if (scanString(exp)) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Função responsável por verificar uma conta que esta dentro de parenteses
	 * de uma expressão booleana e retornar o valor referente a sua resposta
	 * 
	 * @param txt Conta informada com um char para informar qual operação está sendo
	 *            tratada
	 *            (d - and(); r - or(); t - not();) + números (0 & 1)
	 * @return 1 ou 0 dependendo da expressão e seu resultado
	 */
	public static String verificar(String txt) {
		String resp = "1"; // Inicio a resposta como verdade caso altere fica falsa
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

	/**
	 * Função responsável por realizar a separação da expressão em pequenos pedaços
	 * de contas únicas
	 * Cada pedaço é resolvido separadamente, a expressão é resolvida de dentro para
	 * fora sempre resolvendo
	 * a última conta possível da expressão até restar uma resposta válida
	 * 
	 * @param expressao Expressão já formatada com os devidos valores e sem espaços
	 * @return Verdade caso o número resultante for 1 e falso caso o número
	 *         resultante for 0
	 */
	public static boolean scanString(String expressao) {
		int index = 0, count = 0;
		String contaBkp = "", lixo = "", contaAtual = "", letraFinal = "";
		boolean isFinal = false;
		while (true) {
			if (expressao.charAt(index) == '(') {
				for (int j = index + 1; j < expressao.length(); j++) {
					if (expressao.charAt(j) == '(') {
						isFinal = true;
					}
				}
				if (isFinal) {
					contaBkp += expressao.charAt(index);
					isFinal = false;
				} else {
					index++;
					while (expressao.charAt(index) != ')') {
						contaAtual += expressao.charAt(index);
						index++;
					}
					count = contaBkp.length() - 1;
					letraFinal = "" + contaBkp.charAt(count);
					if (contaBkp.charAt(count) == 'd') {
						count = contaBkp.length() - 3;
					} else if (contaBkp.charAt(count) == 't') {
						count = contaBkp.length() - 3;
					} else if (contaBkp.charAt(count) == 'r') {
						count = contaBkp.length() - 2;
					}
					for (int i = 0; i < count; i++) {
						lixo += contaBkp.charAt(i);
					}
					letraFinal += contaAtual;
					contaAtual = letraFinal;
					contaBkp = lixo;
					contaBkp += verificar(contaAtual);
					contaAtual = "";
					lixo = "";
					letraFinal = "";
				}
			} else {
				contaBkp += expressao.charAt(index);
			}
			if (index == expressao.length() - 1) {
				expressao = contaBkp;
				contaBkp = "";
				index = 0;
			} else {
				index++;
			}
			if (expressao.length() == 1) {
				if (expressao.charAt(0) == '0') {
					return false;
				} else if (expressao.charAt(0) == '1') {
					return true;
				}
			}
		}
	}

	/**
	 * Função que simula a String.replace() e a String.trim()
	 * 
	 * @param frase  Frase para ser formatada
	 * @param antiga Char para ser alterado
	 * @param nova   Novo char para ser adicionado
	 * @return Frase de entrada formatada e pronta para uso
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

	public static String myReplace(String frase, String antiga, String nova) {
		String resp = "";
		int j = 0;
		for (int i = 0; i < frase.length(); i++) {
			if (frase.charAt(i) == antiga.charAt(j)) {
				if (j == antiga.length() - 1) {
					resp += nova;
					j = 0;
				} else {
					j++;
				}
			} else {
				j = 0;
				resp += frase.charAt(i);
			}
		}
		return resp;
	}

	public static String mySubstring(String frase, int inicio, int fim) {
		String resp = "";
		for (int i = inicio; i < fim; i++) {
			resp += frase.charAt(i);
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
