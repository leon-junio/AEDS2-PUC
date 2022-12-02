import java.io.*;
import java.net.*;

class Tp1Q8 {

	/*
	 * Função que checa se uma palavra digitada é a condição de parada FIM
	 * 
	 * @param txt String para checar se a condição é verdadeira ou falsa
	 */
	private static boolean checkFim(String txt) {
		return (txt.length() == 3 && txt.charAt(0) == 'F' && txt.charAt(1) == 'I' && txt.charAt(2) == 'M');
	}

	/*
	 * Função que verifica se um char é vogal
	 * 
	 * @param txt char que vai ser verificado
	 * 
	 * @return retorna o vetor que armazena as contas atualizado
	 */
	private static int[] isVogal(int[] vetor, char txt) {
		String vogais = "aeiouáéíóúàèìòùãõâêîôû";
		for (int j = 0; j < vogais.length(); j++) {
			if (txt == vogais.charAt(j)) {
				vetor[j]++;
				j = vogais.length() - 1;
			}
		}
		return vetor;
	}

	/*
	 * Função que verifica se um char é consoante
	 * 
	 * @param txt Char que vai ser verificado
	 * 
	 * @return retorno de verdadeiro ou falso satisfazendo a verificação
	 */
	private static boolean isCons(char txt) {
		String consoantes = "bcdfghjklmnpqrstvxywz";
		for (int j = 0; j < consoantes.length(); j++) {
			if (txt == consoantes.charAt(j)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Função responsável por pegar o html de um site passado por parametro
	 * 
	 * @param site endereço do site na internet
	 * 
	 * @return o html em formato de string da página passada por parametro
	 */
	private static String getHtml(String site) {
		URL url;
		InputStream is = null;
		BufferedReader br;
		String resp = "", line;
		try {
			url = new URL(site);
			is = url.openStream();
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				resp += line + "\n";
			}
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		try {
			is.close();
		} catch (IOException ioe) {
			System.err.println("Erro interno");
			ioe.printStackTrace();
		}
		return resp;
	}

	/**
	 * Função responsável por preencher um vetor e inicializar o mesmo
	 * 
	 * @param vetor vetor que vai sofrer as modificações
	 * @return um novo vetor totalmente inicializado
	 */
	private static int[] inicializarVetor(int[] vetor) {
		for (int i = 0; i < vetor.length; i++) {
			vetor[i] = 0;
		}
		return vetor;
	}

	/**
	 * Função principal que verifica e trabalha com o HTML gerado, por essa função
	 * é chamadas outras funções para desenvolver a resposta e gerar o total
	 * necessário
	 * 
	 * @param html O HTML completo gerado pelo link passado como parametro
	 * @param nome Nome do site do html
	 */
	private static void verificar(String html, String nome) {
		int[] respostas = new int[25];
		respostas = inicializarVetor(respostas);
		int ct = 0;
		String inTag = "";
		for (int i = 0; i < html.length(); i++) {
			if (html.charAt(i) == '<') {
				for (int j = i + 1; j < html.length(); j++) {
					if (html.charAt(j) == '>') {
						j = html.length() - 1;
					} else {
						ct++;
						inTag += html.charAt(j);
					}
				}
				if (ct == 2) {
					if (inTag.charAt(0) == 'b' && inTag.charAt(1) == 'r') {
						respostas[23]++;
						i += ct + 2;
					}
				} else if (ct == 5) {
					if (inTag.charAt(0) == 't' && inTag.charAt(1) == 'a' && inTag.charAt(2) == 'b'
							&& inTag.charAt(3) == 'l' && inTag.charAt(4) == 'e') {
						respostas[24]++;
						i += ct + 2;
							}
				}
				ct = 0;
				inTag = "";
			}
			if (isCons(html.charAt(i))) {
				respostas[22]++;
			} else {
				respostas = isVogal(respostas, html.charAt(i));
			}
		}
		printResps(respostas, nome); // print das respostas
	}

	/**
	 * Função responsavel apenas por imprimir o resultado na tela exibindo um vetor
	 * e suas posições
	 * 
	 * @param resps Vetor contendo todas as respostas
	 * @param nome  Nome do site informado anteriormente
	 */
	private static void printResps(int[] resps, String nome) {
		MyIO.println("a(" + resps[0] + ") e(" + resps[1] + ") i(" + resps[2] + ") o("
				+ resps[3] + ") u(" + resps[4]
				+ ") á(" + resps[5] + ") é(" + resps[6] + ") í(" + resps[7] +
				") ó(" + resps[8] + ") ú(" + resps[9] + ") à(" + resps[10] + ") è(" +
				resps[11] + ") ì(" + resps[12]
				+ ") ò(" + resps[13] + ") ù(" + resps[14] + ") ã(" + resps[15] +
				") õ(" + resps[16] + ") â(" + resps[17] + ") ê(" + resps[18] + ") î(" +
				resps[19] + ") ô(" + resps[20]
				+ ") û(" + resps[21] + ") consoante(" + resps[22] +
				") <br>(" + resps[23] + ") <table>(" + resps[24] + ") " + nome);
	}

	public static void main(String[] args) {
		String site = "", nome = "", html = "";
		MyIO.setCharset("UTF-8");
		do {
			nome = MyIO.readLine();
			if (!checkFim(nome)) {
				site = MyIO.readLine();
				html = getHtml(site);
				verificar(html, nome);
			}
		} while (!checkFim(nome));
	}
}
