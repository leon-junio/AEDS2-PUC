#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#define MAX 1000

// Função que checa se uma palavra digitada é a condição de parada
bool checkFim(char txt[MAX])
{
	txt[strcspn(txt, "\n")] = 0;
	return (strlen(txt) == 3 && txt[0] == 'F' && txt[1] == 'I' && txt[2] == 'M');
}

// Função que verifica se a String apresenta apenas vogais
bool onVogal(char txt[MAX])
{
	txt[strcspn(txt, "\n")] = 0;
	char vogais[MAX] = "AEIOUaeiouàèìòùÀÈÌÒÙáéíóúÁÉÍÓÚâêîôûÂÊÎÔÛãõÃÕ";
	bool resp;
	for (int i = 0; i < strlen(txt); i++)
	{
		resp = false;
		for (int j = 0; j < strlen(vogais); j++)
		{
			if (txt[i] == vogais[j])
			{
				j = ((int)strlen(vogais)) - 1;
				resp = true;
			}
			if (j == strlen(vogais) - 1)
			{
				if (!resp)
				{
					return false;
				}
			}
		}
	}
	return true;
}

// Função que verifica se a String apresenta apenas consoantes
bool onCons(char txt[MAX])
{
	txt[strcspn(txt, "\n")] = 0;
	char consoantes[MAX] = "bcdfghjklmnpqrstvxywzBCDFGHJKLMNPQRSTVXYWZ";
	bool resp;
	for (int i = 0; i < strlen(txt); i++)
	{
		resp = false;
		for (int j = 0; j < strlen(consoantes); j++)
		{
			if (txt[i] == consoantes[j])
			{
				j = strlen(consoantes) - 1;
				resp = true;
			}
			if (j == strlen(consoantes) - 1)
			{
				if (!resp)
				{
					return false;
				}
			}
		}
	}
	return true;
}

// Função para verificar se uma string é um numero inteiro
bool isInt(char txt[MAX])
{
	txt[strcspn(txt, "\n")] = 0;
	for (int i = 0; i < strlen(txt); i++)
	{
		if (txt[i] < 48 || txt[i] > 57)
		{
			return false;
		}
	}
	return true;
}

// Função para verificar se uma string é um numero real
bool isReal(char txt[MAX])
{
	bool point = false;
	txt[strcspn(txt, "\n")] = 0;
	for (int i = 0; i < strlen(txt); i++)
	{
		if (txt[i] < 48 || txt[i] > 57)
		{
			if (txt[i] == '.' || txt[i] == ',')
			{
				if (point)
				{
					return false;
				}
				else
				{
					point = true;
				}
			}
			else
			{
				return false;
			}
		}
	}
	return true;
}

int main()
{
	char word[MAX];
	do
	{
		fgets(word, MAX, stdin);
		if (!checkFim(word))
		{
			if (onVogal(word))
			{
				printf("SIM ");
			}
			else
			{
				printf("NAO ");
			}
			if (onCons(word))
			{
				printf("SIM ");
			}
			else
			{
				printf("NAO ");
			}
			if (isInt(word))
			{
				printf("SIM ");
			}
			else
			{
				printf("NAO ");
			}
			if (isReal(word))
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
