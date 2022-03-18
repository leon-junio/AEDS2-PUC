import java.lang.Math;
import java.util.Random;

class Tp1Q4{

	/*
	 * Método para avaliar se uma palavra é a condição de parada FIM
	 * @args str É a palavra para ser verificada
	 * @return refere-se ao retorno verdadeiro ou falso de acordo com a comparação 
	 */
	public static boolean isFim(String str){
		return (str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M');
	}

	/*
	 * Método para alterar as letras de uma frase aleatoriamente por meio de sorteios de dois chars
	 * @args str É a frase para ser alterada
	 * @return refere-se a frase altearada 
	 */
	public static String alterarAleatorio(String str){
		char charAle1 = gerarAleatorio();
		char charAle2 = gerarAleatorio();
		String strAleatoria = "";
		for(int i=0;i<str.length();i++){
			if(str.charAt(i) == charAle1){//realizo a troca de caracteres com base no gerado aleatoriamente
				strAleatoria += charAle2;
			}else{
				strAleatoria += str.charAt(i);
			}	
		}
		return strAleatoria;
	}

	/*
	 * Função para gerar um char aleatório para ser comparado dentro do metódo de troca de caracteres
	 * @return Um caractere aleatorio gerado pela biblioteca RAND
	 */
	public static char gerarAleatorio(){
		return ((char)('a'+ (Math.abs(rand.nextInt()))%26));
	}

	//declaração do rand como global para conservar a aleatoriedade
	private static Random rand = new Random();

	public static void main (String[] args){
			String word = "";
		rand.setSeed(4); //definição da minha seed no main para alterar globalmente em todos os metodos
		do {
			word = MyIO.readLine();
			if(!isFim(word)){
				MyIO.println(alterarAleatorio(word));
			}
		} while (!isFim(word));
	}
}
