#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#define MAX 1000

/*
 * Método para avaliar se uma palavra é a condição de parada FIM
 * @args str É a palavra para ser verificada
 * @return refere-se ao retorno verdadeiro ou falso de acordo com a comparação 
 */
bool isFim(char *str){
	str[strcspn(str, "\n")] = 0;
	return (strlen(str) == 3 && str[0] == 'F' && str[1] == 'I' && str[2] == 'M');
}

/*
 * Método para avaliar se uma palavra é um palindromo
 * @args str É a palavra para ser verificada
 * @return refere-se a SIM para palindromo e NAO para não palindromo 
 */
char * palindromo(char *str, int index){
	str[strcspn(str, "\n")] = 0;
	//printf("T: %d %s \n",(int)strlen(str),str);
	int jindex = (strlen(str)-1)-index;
	char *resp;
	if(index == strlen(str)-1){
		return "SIM";
	}
	if(str[index] != str[jindex]){
		return "NAO";
	}else{
		resp = palindromo(str,index+1);
		return resp;
	}
}

int main (){
	char word[MAX];
	do {
		fgets(word,MAX,stdin);
		if(!isFim(word)){
			printf("%s\n",palindromo(word,0));
		}
	} while (!isFim(word));
	return 0;
}
