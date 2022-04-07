#include <stdio.h>
#include <stdbool.h>

/**
 * Função que simula a char*.contains() para char*s
 */
bool myContains(char * frase, char * ver)
{
	printf("mc start\n");
	printf("f: %s \n",frase);
	bool resp = false;
	char *aux = NULL;
	int count = 0;
	if (frase != NULL)
	{
		printf("diff\n");
		for (int i = 0; i < strlen(frase); i++)
		{
			printf("%c \n",frase[i]);
			if (frase[i] == ver[0])
			{
				if ((strlen(frase) - i) >= strlen(ver))
				{
					for (int j = i; j < strlen(frase); j++)
					{
						*aux += frase[j];
						count++;
						if (count == strlen(ver))
						{
							j = strlen(frase);
							count = 0;
						}
					}
					if (myEquals(aux, ver))
					{
						resp = true;
						i = strlen(frase);
					}
					else
					{
						aux = "";
						count = 0;
					}
				}
				else
				{
					aux = "";
				}
			}
		}
	}
	printf("mc out\n");
	return resp;
}

int main()
{
    char *texto[1000];
    int n = 0;
    char aux[1000];
    while (!isFim(aux))
    {
        fgets(aux, 1000, stdin);
        texto[n] = (char *)malloc((strlen(aux) + 1) * sizeof(char));
        strcpy(texto[n], aux);
        n++;
    }
    return 0;
}