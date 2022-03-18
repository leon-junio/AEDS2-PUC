#include <stdio.h>
#include <string.h>
#include <stdbool.h>

#define MAX 1000

//funcao que checa se a palavra digitada foi "FIM"
bool checkFim(char palavra[MAX])
{
	palavra[strcspn(palavra, "\n")] = 0;
	return (strlen(palavra) == 3 && palavra[0] == 'F' && palavra[1] == 'I' && palavra[2] == 'M');
}

//funcao que retorna um boolean se uma palavra e ou nao palindromo 
bool palindromo(char palavra[MAX])
{
	palavra[strcspn(palavra, "\n")] = 0;
	bool resp = true;
	int j = 0;
	for (int i = strlen(palavra) - 1; i >= 0; i--)
	{
		if (palavra[i] != palavra[j])
		{
			resp = false;
		}
		j++;
	}
	return resp;
}

//funcao main responsavel por iniciar as funcoes e realizar a leitura das palavras
int main()
{
	char word[MAX];
	do
	{
		fgets(word,MAX,stdin);
		if (!checkFim(word))
		{
			if (palindromo(word))
			{
				printf("SIM\n");
			}
			else
			{
				printf("NAO\n");
			}
		}
	} while (!checkFim(word));
	return 0;
}
