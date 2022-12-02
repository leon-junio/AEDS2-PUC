class Tp1Q6{

	/*
	 * Função que checa se uma palavra digitada é a condição de parada FIM
	 * @param txt String para checar se a condição é verdadeira ou falsa
	 */
	private static boolean checkFim(String txt){
		return (txt.length()== 3 && txt.charAt(0) == 'F' && txt.charAt(1) == 'I' && txt.charAt(2) == 'M');	
	}

	/*
	 * Função que verifica se a String apresenta apenas vogais
	 * @param txt String que vai ser verificada
	 * @return retorno de sim ou não em texto satisfazendo a verificação
	 */
	private static String onVogal(String txt){
		String vogais = "AEIOUaeiouàèìòùÀÈÌÒÙáéíóúÁÉÍÓÚâêîôûÂÊÎÔÛãõÃÕ";
		boolean resp;
		for (int i=0;i<txt.length();i++){
			resp = false;
			for(int j=0;j<vogais.length();j++){
				if(txt.charAt(i) == vogais.charAt(j)){
					j = vogais.length()-1;
					resp = true;
				}
				if(j == vogais.length()-1){
					if(!resp){
						return "NAO";
					}
				}
			}
		}
		return "SIM";
	}	

	/*
	 * Função que verifica se a String apresenta apenas consoantes
	 * @param txt String que vai ser verificada
	 * @return retorno de sim ou não em texto satisfazendo a verificação
	 */

	private static String onCons(String txt){
		String consoantes = "bcdfghjklmnpqrstvxywzBCDFGHJKLMNPQRSTVXYWZ";
		boolean resp;
		for (int i=0;i<txt.length();i++){
			resp = false;
			for(int j=0;j<consoantes.length();j++){
				if(txt.charAt(i) == consoantes.charAt(j)){
					j = consoantes.length()-1;
					resp = true;
				}
				if(j == consoantes.length()-1){
					if(!resp){
						return "NAO";
					}
				}
			}
		}
		return "SIM";
	}

	/*
	 * Função para verificar se uma string é um numero inteiro
	 * @param txt String para ser verificada
	 * @return retorno da verificação da string
	 */
	private static String isInt(String txt){
		for(int i=0;i<txt.length();i++){
			if(txt.charAt(i)<48 || txt.charAt(i)>57){
				return "NAO";
			}
		}
		return "SIM";
	}
	/*
	 * Função para verificar se uma string é um numero real
	 *  @param txt String para ser verificada
	 *  @return resposta com base na comparação
	 */
	private static String isReal(String txt){
		boolean point = false;
		for(int i=0;i<txt.length();i++){
			if(txt.charAt(i)<48 || txt.charAt(i)>57){
				if(txt.charAt(i)=='.' || txt.charAt(i) == ','){
					if(point){
						return "NAO";
					}else{
						point = true;
					}
				}else{
					return "NAO";
				}
			}
		}
		return "SIM";
	}

	public static void main (String[] args){
		String word = "";
		do{
			word = MyIO.readLine();
			if(!checkFim(word)){
				MyIO.print(onVogal(word)+" ");
				MyIO.print(onCons(word)+" ");
				MyIO.print(isInt(word)+" ");
				MyIO.print(isReal(word)+"\n");
			}
		}while(!checkFim(word));

	}

}
