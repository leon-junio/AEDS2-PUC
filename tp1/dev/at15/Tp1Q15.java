class Tp1Q15 {

	/*
	 * Função que checa se uma palavra digitada é a condição de parada FIM
	 * 
	 * @param txt String para checar se a condição é verdadeira ou falsa
	 */
	private static boolean checkFim(String txt) {
		return (txt.length() == 3 && txt.charAt(0) == 'F' && txt.charAt(1) == 'I' && txt.charAt(2) == 'M');
	}

	/*
	 * Função que verifica se a String apresenta apenas vogais
	 * 
	 * @param txt String que vai ser verificada
	 * 
	 * @return retorno de sim ou não em texto satisfazendo a verificação
	 */
	private static String onVogal(String txt, int i) {
		String vogais = "AEIOUaeiouàèìòùÀÈÌÒÙáéíóúÁÉÍÓÚâêîôûÂÊÎÔÛãõÃÕ";
		String resp = "NAO";
		if (i < txt.length()) {
			for (int j = 0; j < vogais.length(); j++) {
				if (txt.charAt(i) == vogais.charAt(j)) {
					j = vogais.length() - 1;
					resp = "SIM";
					break;
				}
				resp = "NAO";
			}
			if (resp.charAt(0) == 'N') {
				return resp;
			}
			if (i + 1 < txt.length()) {
				resp = onVogal(txt, i + 1);
			}
		}
		if (i == txt.length()) {
			return resp;
		}
		return resp;
	}

	/*
	 * Função que verifica se a String apresenta apenas consoantes
	 * 
	 * @param txt String que vai ser verificada
	 * 
	 * @return retorno de sim ou não em texto satisfazendo a verificação
	 */

	private static String onCons(String txt, int i) {
		String resp = "NAO";
		if (i == txt.length()) {
			resp = "SIM";
		} else if ((txt.charAt(i) >= 'A' && txt.charAt(i) <= 'Z')
				|| (txt.charAt(i) >= 'a' && txt.charAt(i) <= 'z')) {
			if (onVogal("" + txt.charAt(i), 0).charAt(0) == 'S') {
				return "NAO";
			} else {
				resp = onCons(txt, i + 1);
			}
		}
		return resp;
	}

	/*
	 * Função para verificar se uma string é um numero inteiro
	 * 
	 * @param txt String para ser verificada
	 * 
	 * @return retorno da verificação da string
	 */
	private static String isInt(String txt, int i) {
		String resp = "NAO";
		if (i == txt.length()) {
			resp = "SIM";
		} else if (txt.charAt(i) < 48 || txt.charAt(i) > 57) {
			resp = "NAO";
		} else {
			resp = isInt(txt, i + 1);
		}
		return resp;
	}

	/*
	 * Função para verificar se uma string é um numero real
	 * 
	 * @param txt String para ser verificada
	 * 
	 * @return resposta com base na comparação
	 */
	private static String isReal(String txt, int i) {
		boolean point = false;
		String resp = "SIM";
		if (i < txt.length()) {
			if (txt.charAt(i) < 48 || txt.charAt(i) > 57) {
				if (txt.charAt(i) == '.' || txt.charAt(i) == ',') {
					if (hasDots(txt) > 1) {
						return "NAO";
					}
				} else {
					return "NAO";
				}
			}
			resp = isReal(txt, i + 1);
		}
		if (i == txt.length()) {
			return resp;
		}
		return resp;
	}

	private static int hasDots(String txt) {
		int total = 0;
		for (int i = 0; i < txt.length(); i++) {
			if (txt.charAt(i) == '.') {
				total++;
			}
			if (txt.charAt(i) == ',') {
				total++;
			}
		}
		return total;
	}

	public static void main(String[] args) {
		String word = "";
		do {
			word = MyIO.readLine();
			if (!checkFim(word)) {
				MyIO.print(onVogal(word, 0) + " ");
				MyIO.print(onCons(word, 0) + " ");
				MyIO.print(isInt(word, 0) + " ");
				MyIO.print(isReal(word, 0) + "\n");
			}
		} while (!checkFim(word));

	}

}
