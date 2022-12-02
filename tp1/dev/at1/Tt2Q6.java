class Tt2Q6{

	/*
	 * Método para avaliar se uma palavra é a condição de parada FIM
	 * @args str É a palavra para ser verificada
	 * @return refere-se ao retorno verdadeiro ou falso de acordo com a comparação 
	 */
	public static boolean isFim(String str){
		return (str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M');
	}

	/*
	 * Método para avaliar se uma palavra é um palindromo
	 * @args str É a palavra para ser verificada
	 * @return refere-se a SIM para palindromo e NAO para não palindromo 
	 */
	public static String palindromo(String str){
		String resp = "SIM";
		int j = 0;
		for(int i=str.length()-1;i>=0;i--){
			if(str.charAt(i) != str.charAt(j)){
				resp = "NAO";
			}
			j++;
		}
		return resp;
	}

	public static void main (String[] args){
		String word = "";
		do {
			word = MyIO.readLine();
			if(!isFim(word)){
				MyIO.println(palindromo(word));
			}
		} while (isFim(word) == false);
	}
}
