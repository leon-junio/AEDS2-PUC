class Tp1Q13{

	/*
	 * Método para avaliar se uma palavra é a condição de parada FIM
	 * @args str É a palavra para ser verificada
	 * @return refere-se ao retorno verdadeiro ou falso de acordo com a comparação 
	 */
	public static boolean isFim(String str){
		return (str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M');
	}

	/*
	 * Método para cifrar uma palavra usando uma key de cifra de cesar
	 * @args str É a frase para ser cifrada
	 * @return refere-se a palavra criptografada 
	 */
	public static String cryptCesar(String str,int index){
		String cesarStr = "",resp = "";
		char aux = 0;
		int key = 3;
		if(index <= str.length()-1){
			resp += ""+ (char) (((int) str.charAt(index))+key); 
			cesarStr += resp;
			cesarStr += cryptCesar(str,index+1);
			return cesarStr;			
		}else if(index == str.length()){	
			return cesarStr;	
		}
		return cesarStr;
	}

	public static void main (String[] args){
		String word = "";
		do {
			word = MyIO.readLine();
			if(!isFim(word)){
				MyIO.println(cryptCesar(word,0));
			}
		} while (!isFim(word));
	}
}
