#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <dirent.h>
// BIBLIOTECAS USADAS PARA REALIZAÇÃO DESSA ATIVIDADE

// CONSTANTE DE TAMANHO MÁXIMO DE STRING E ARRAYS DE CHAR
#define STRMAX 1200

// STRUCTS
typedef struct
{
	int dia, mes, ano;
} Date;

typedef struct
{
	char nome[500], titulo[500], genero[2500], idioma[100], situacao[50];
	float orcamento;
	int duracao;
	Date lancamento;
	char *keywords[1000];
	int numkey;
} Filme;

// Metodo de comparação de duas char*s e retorna sua igualdade em forma de
// bool
bool myEquals(char str1[STRMAX], char str2[STRMAX])
{
	bool resp = false;
	if (str1 != NULL && str2 != NULL)
	{
		if (strlen(str1) == strlen(str2))
		{
			resp = true;
			for (int i = 0; i < strlen(str1); i++)
			{
				if (str1[i] != str2[i])
				{
					resp = false;
				}
			}
		}
	}
	return resp;
}

/*
Função de replace entre String e chars, passamos uma string alvo para realizar as trocas
um char que deve ser localizado e é o que vai ser alterado dentro da String alvo e um char
que vai ser a opção de mudança
Simula o funcionamento da String.replace() do Java
*/
char *myReplaceCh(char frase[STRMAX], char antiga, char nova)
{
	char *resp;
	resp = malloc(sizeof(char) * strlen(frase));
	int pos = 0;
	if (frase != NULL)
	{
		for (int i = 0; i < strlen(frase); i++)
		{
			if (frase[i] == antiga)
			{
				resp[pos] = nova;
				pos++;
			}
			else
			{
				// trim para remover espaços
				if (frase[i] != ' ')
				{
					resp[pos] = frase[i];
					pos++;
				}
			}
		}
	}
	return resp;
}

// função que junta e retorna duas strings
char *juntar(char *frase, char *juncao)
{
	char *resp;
	int n = 0, j = 0;
	resp = malloc(sizeof(char *) * (strlen(frase) + strlen(juncao)));
	for (int i = 0; i < (strlen(frase)); i++)
	{
		resp[n] = frase[i];
		n++;
	}
	for (int i = 0; i < (strlen(juncao)); i++)
	{
		resp[n] = juncao[i];
		n++;
	}
	return resp;
}

// Função que retorna o index de um char dentro de um array de chars
int myIndexOf(char frase[STRMAX], char letra)
{
	int resp = -1;
	if (frase != NULL)
	{
		for (int i = 0; i < strlen(frase); i++)
		{
			if (frase[i] == letra)
			{
				resp = i;
				i = strlen(frase);
			}
		}
	}
	return resp;
}

/**
 * Função que simula o trim da classe char*
 */
char *myTrim(char frase[STRMAX])
{
	char *resp;
	resp = malloc(sizeof(char) * strlen(frase));
	int pos = 0;
	if (frase != NULL)
	{
		for (int i = 0; i < strlen(frase); i++)
		{
			if (frase[i] != ' ')
			{
				resp[pos] = frase[i];
				pos++;
			}
		}
	}
	return resp;
}

/**
 * Função que simula o Subchar* da classe char*
 */
char *mySubString(char frase[STRMAX], int inic, int fim)
{
	char *resp;
	resp = (char *)malloc(strlen(frase) * sizeof(char));
	int pos = 0;
	if (frase != NULL)
	{
		if (fim - inic <= strlen(frase))
		{
			for (int i = inic; i < fim; i++)
			{
				resp[pos] = frase[i];
				pos++;
			}
		}
	}
	if (strlen(resp) > fim - inic)
	{
		for (int i = fim - inic; i < strlen(resp); i++)
		{
			resp[i] = '\0';
		}
	}
	return resp;
}

/**
 * Função que simula o Subchar* da classe char*
 */
char *mySubStringCh(char frase[STRMAX], char cInic, char cFim)
{
	char *resp;
	resp = malloc((sizeof(char *) * strlen(frase)) + 1);
	if (frase != NULL)
	{
		int inic = myIndexOf(frase, cInic);
		int fim = myIndexOf(frase, cFim);
		int pos = 0;
		if (fim - inic <= strlen(frase))
		{
			for (int i = inic; i < fim + 1; i++)
			{
				resp[pos] = frase[i];
				pos++;
			}
		}
	}
	return resp;
}

/*
Função de replace entre Strings, passamos uma string alvo para realizar as trocas
uma string que deve ser o que vai ser mudado e uma string contendo o texto de mudança
Simula o funcionamento da String.replace() do Java
*/
char *myReplace(char *orig, char *rep, char *with)
{
	char *result, *ins, *tmp;
	int len_rep, len_with, len_front, count;
	// condicionamento se os parametros foram passados corretamente
	if (!orig || !rep)
		return NULL;
	len_rep = strlen(rep);
	if (len_rep == 0)
		return NULL;
	if (!with)
		with = "";
	len_with = strlen(with);
	ins = orig;
	for (count = 0; tmp = strstr(ins, rep); ++count)
	{
		ins = tmp + len_rep;
	}
	tmp = result = malloc(strlen(orig) + (len_with - len_rep) * count + 1);
	if (!result)
		return NULL;
	while (count--)
	{
		ins = strstr(orig, rep);
		len_front = ins - orig;
		tmp = strncpy(tmp, orig, len_front) + len_front;
		tmp = strcpy(tmp, with) + len_with;
		orig += len_front + len_rep;
	}
	strcpy(tmp, orig);
	return result;
}

// Função para ler entre espaços dentro de uma frase
// No maximo dois espaços
char *lerEntreSpaces(char frase[STRMAX])
{
	int count = 0;
	char *resp;
	resp = (char *)malloc(strlen(frase) * sizeof(char));
	bool check = false;
	for (int i = 0; i < strlen(frase); i++)
	{
		if (frase[i] == ' ')
		{
			if (check)
			{
				i = strlen(frase);
			}
			else
			{
				check = true;
			}
		}
		else if (check)
		{
			resp[count] = frase[i];
			count++;
		}
	}
	for (int i = 0; i < strlen(resp); i++)
	{
		if (resp[i] < 48 || resp[i] > 57)
		{
			resp[i] = '\0';
		}
	}
	return resp;
}

/**
 * Função que remove espaços do inicio de frases
 */
char *inicioTrim(char line[STRMAX], char end)
{
	char *resp;
	resp = malloc(sizeof(char) * strlen(line));
	bool next = false;
	int count = 0;
	if (line != NULL)
	{
		for (int i = 0; i < strlen(line); i++)
		{
			if (line[i] == end)
			{
				next = true;
			}
			if (next)
			{
				resp[count] = line[i];
				count++;
			}
		}
	}
	if (strlen(resp) == 0)
	{
		return line;
	}
	return resp;
}

/**
 * Função que remove tags e puxa tudo que esta entre elas
 */
char *removeTags(char *line)
{
	char *resp;
	resp = malloc(sizeof(char) * strlen(line));
	int pos = 0;
	bool next = false;
	if (line != NULL)
	{
		for (int i = 0; i < strlen(line); i++)
		{
			if (line[i] == '>')
				next = true;
			else if (line[i] == '<')
				next = false;
			else if (next)
			{
				resp[pos] = line[i];
				pos++;
			}
		}
	}
	return resp;
}

/**
 * Função que remove tags e puxa tudo que esta entre elas e adiciona separadores
 * para palavras
 */
char *removeTagsSe(char line[STRMAX], char sep)
{
	char *resp;
	resp = malloc(sizeof(char) * strlen(line));
	int count = 0, pos = 0;
	bool next = false;
	if (line != NULL)
	{
		for (int i = 0; i < strlen(line); i++)
		{
			if (line[i] == '>')
			{
				next = true;
			}
			else if (line[i] == '<')
			{
				next = false;
				if (count > 0)
				{
					resp[pos] = sep;
					pos++;
				}
				count = 0;
			}
			else if (next)
			{
				count++;
				resp[pos] = line[i];
				pos++;
			}
		}
	}
	return resp;
}

// função para printar a data de uma Struct Date
void printDate(Date date)
{
	printf("%02d/%02d/%d", date.dia, date.mes, date.ano);
}

// Conversão de String para Struct Date (formada por inteiros de dia/mes/ano)
Date convertDate(char *linha)
{
	Date date;
	date.dia = atoi(mySubString(linha, 0, 2));
	date.mes = atoi(mySubString(linha, 3, 5));
	date.ano = atoi(mySubString(linha, 6, 10));
	return date;
}

// Função que retorna o arquivo HTML com base no nome passado por parametro
FILE *getFile(char name[STRMAX])
{
	FILE *file;
	char *buffer;
	buffer = malloc(sizeof(char *) * (strlen(name) + strlen("/tmp/filmes/") + 1));
	buffer = juntar("/tmp/filmes/", name);
	file = fopen(buffer, "r");
	if (file == NULL)
	{
		printf("ERRO AO TENTAR PROCURAR ARQUIVO\n");
		printf("file bad: %s.\n", name);
		printf("path: %s.\n", buffer);
		exit(1);
	}
	free(buffer);
	return file;
}

// funcao que remove letras e gera um horario em formato de minutos pronto para
// uso
// realizando o calculo automatico das horas se possível (Horas * 60 = horas em
// minutos)
int getMinutos(char linha[STRMAX])
{
	char *hr, *mn;
	hr = malloc(sizeof(char) * strlen(linha));
	mn = malloc(sizeof(char) * strlen(linha));
	int posh = 0, posm = 0;
	int conta = 0;
	bool chk = false;
	if (linha != NULL)
	{
		for (int i = 0; i < strlen(linha); i++)
		{
			if (chk)
			{
				if (linha[i] != ' ')
				{
					mn[posm] = linha[i];
					posm++;
				}
			}
			else
			{
				if (linha[i] != ' ')
				{
					hr[posh] = linha[i];
					posh++;
				}
				else
				{
					chk = true;
				}
			}
		}
		conta = atoi(mn);
		if (strlen(hr) > 0)
		{
			conta += atoi(hr) * 60;
		}
	}
	free(hr);
	free(mn);
	return conta;
}

// Método responsável por ler a linha e remover suas tags/tratar os dados
char *tratarLinha(char linha[STRMAX], int op)
{
	linha[strcspn(linha, "\n")] = 0; // removo o \n de dentro da linha que foi lida
	char *resp;
	resp = malloc(sizeof(char) * strlen(linha)); // Aloco espaço para a resposta em String
	int pos = 0;
	char *locale;
	linha = inicioTrim(linha, '<');
	// Dentro desse Switch são utilizados diversos métodos e funções diferentes que
	// extraem os dados e preparam a linha para ser inserida dentro da Struct de filme
	switch (op)
	{
	case 1:
		locale = mySubString(linha, myIndexOf(linha, '('), myIndexOf(linha, ')') + 1);
		char buffer[STRMAX];
		// memccpy(memccpy(buffer, " ", '\0', STRMAX) - 1, locale, '\0', STRMAX);
		strcpy(buffer, juntar(" ", locale));
		linha = myReplace(linha, buffer, " ");
		resp = myTrim(linha);
		break;
	case 2:
		resp = removeTagsSe(linha, ',');
		resp = myReplace(resp, ",,&nbsp;", "");
		break;
	case 3:
		linha = myReplaceCh(linha, 'm', ' ');
		if (strstr(linha, "h"))
		{
			linha = myReplaceCh(linha, 'h', ' ');
		}
		else
		{
			linha = juntar(" ", linha);
		}
		int x = getMinutos(linha);
		int length = snprintf(NULL, 0, "%d", x);
		snprintf(resp, length + 1, "%d", x);
		break;
	case 4:
		resp = myReplace(linha, "<p class=\"wrap\"><strong>Título original</strong> ", "");
		resp = myReplace(resp, "</p>", "");
		break;
	case 5:
		resp = myReplace(linha, "<strong><bdi>Situação</bdi></strong> ", "");
		break;
	case 6:
		resp = myReplace(linha, "<p><strong><bdi>Idioma original</bdi></strong> ", "");
		resp = myReplace(resp, "</p>", "");
		break;
	case 7:
		resp = myReplace(linha, "<p><strong><bdi>Orçamento</bdi></strong> ", "");
		resp = myReplace(resp, "</p>", "");
		if (strstr(linha, "-"))
		{
			resp = "0.0";
		}
		else
		{
			resp = myReplace(resp, ",", "");
			resp = myReplace(resp, "$", "");
		}
		break;
	case 8:
		resp = removeTags(linha);
		break;
	case 9:
		resp = myReplace(linha, "<meta property=\"og:title\" content=\"", "");
		resp = myReplace(resp, "\">", "");
		break;
	default:
		break;
	}
	return resp;
}

// Função responsável por realizar a leitura do arquivo e capturar o dado bruto das linhas
// que precisam ter a extração de dados.
// Utilizando a função strstr para verificar se uma linha contém uma condição de extração
Filme ler(Filme filme, char *entrada)
{
	char *line;
	char *aux;
	size_t len = 0;
	FILE *buff = getFile(entrada); // Recebendo o file HTML
	getline(&line, &len, buff);
	while (!strstr(line, "og:title"))
	{
		line = "\0"; // Por precaução zero a linha que foi lida anteriormente
		len = 0;
		getline(&line, &len, buff);
	}
	strcpy(filme.nome, tratarLinha(line, 9));
	// Zero a linha para evitar um erro de Lixo nas Strings que estava ocorrendo anteriormente
	line = "\0";
	len = 0;
	getline(&line, &len, buff);
	while (!strstr(line, "class=\"release\""))
	{
		line = "\0";
		len = 0;
		getline(&line, &len, buff);
	}
	line = "\0";
	len = 0;
	getline(&line, &len, buff);
	filme.lancamento = convertDate(tratarLinha(line, 1));
	line = "\0";
	len = 0;
	getline(&line, &len, buff);
	while (!strstr(line, "class=\"genres\""))
	{
		line = "\0";
		len = 0;
		getline(&line, &len, buff);
	}
	line = "\0";
	len = 0;
	getline(&line, &len, buff);
	line = "\0";
	len = 0;
	getline(&line, &len, buff);
	strcpy(filme.genero, tratarLinha(line, 2));
	strcpy(filme.genero, mySubString(filme.genero, 0, strlen(filme.genero) - 1));
	line = "\0";
	len = 0;
	getline(&line, &len, buff);
	while (!strstr(line, "class=\"runtime\""))
	{
		line = "\0";
		len = 0;
		getline(&line, &len, buff);
	}
	getline(&line, &len, buff);
	line = "\0";
	len = 0;
	getline(&line, &len, buff);
	filme.duracao = atoi(tratarLinha(line, 3));
	line = "\0";
	len = 0;
	getline(&line, &len, buff);
	bool chk = false;
	while (!strstr(line, "Título original"))
	{
		line = "\0";
		len = 0;
		getline(&line, &len, buff);
		if (strstr(line, "<bdi>Situação</bdi>"))
		{
			strcpy(filme.titulo, filme.nome);
			chk = true;
			break;
		}
	}
	if (chk == false)
	{
		strcpy(filme.titulo, tratarLinha(line, 4));
		line = "\0";
		len = 0;
		getline(&line, &len, buff);
	}
	while (!strstr(line, "Situação"))
	{
		line = "\0";
		len = 0;
		getline(&line, &len, buff);
	}
	strcpy(filme.situacao, tratarLinha(line, 5));
	line = "\0";
	len = 0;
	getline(&line, &len, buff);
	while (!strstr(line, "Idioma original"))
	{
		line = "\0";
		len = 0;
		getline(&line, &len, buff);
	}
	strcpy(filme.idioma, tratarLinha(line, 6));
	line = "\0";
	len = 0;
	getline(&line, &len, buff);
	while (!strstr(line, "Orçamento"))
	{
		line = "\0";
		len = 0;
		getline(&line, &len, buff);
	}
	filme.orcamento = atof(tratarLinha(line, 7));
	line = "\0";
	len = 0;
	getline(&line, &len, buff);
	while (!strstr(line, "Palavras-chave"))
	{
		line = "\0";
		len = 0;
		getline(&line, &len, buff);
	}
	getline(&line, &len, buff);
	line = "\0";
	len = 0;
	getline(&line, &len, buff);
	if (strstr(line, "Nenhuma palavra-chave foi adicionada"))
	{
		strcpy(line, "</ul>");
	}
	int n = 0;
	while (!strstr(line, "</ul>"))
	{
		if (strstr(line, "<li>"))
		{
			aux = tratarLinha(line, 8);
			filme.keywords[n] = (char *)malloc((strlen(aux) + 1) * sizeof(char)); // Salvando varias palavras em um vetor de strings
			strcpy(filme.keywords[n], aux);
			n++;
		}
		line = "\0";
		len = 0;
		getline(&line, &len, buff);
	}
	filme.numkey = n;
	fclose(buff); // encerrando Buff de entrada do arquivo
	return filme;
}

// Procedimento para imprimir os dados de um Filme especifico
void imprimir(Filme filme)
{
	printf("%s ", filme.nome);
	printf("%s ", filme.titulo);
	printDate(filme.lancamento);
	printf(" ");
	printf("%d ", filme.duracao);
	printf("%s ", filme.genero);
	printf("%s ", filme.idioma);
	printf("%s ", filme.situacao);
	if (filme.orcamento == 0)
	{
		printf("0 ");
	}
	else
	{
		printf("%g ", filme.orcamento);
	}
	printf("[");
	for (int j = 0; j < filme.numkey; j++)
	{
		if (j < filme.numkey - 1)
			printf("%s, ", filme.keywords[j]);
		else
			printf("%s", filme.keywords[j]);
	}
	printf("]\n");
}

bool isFim(char *entrada)
{
	entrada[strcspn(entrada, "\n")] = 0;
	entrada[strcspn(entrada, "\r")] = 0;
	return strlen(entrada) == 3 && entrada[0] == 'F' && entrada[1] == 'I' && entrada[2] == 'M';
}

Filme *filmes;
int n, MAXTAM;

void start(int count, int vers)
{
	n = 0;
	// gerando lista de filmes em C
	MAXTAM = count + vers;
	filmes = malloc((sizeof(Filme) + 1) * MAXTAM);
}

/**
 * Insere um elemento na primeira posicao da lista e move os demais
 * elementos para o fim da
 * @param fm int elemento a ser inserido.
 */
void inserirInicio(Filme fm)
{
	if (n >= MAXTAM)
	{
		printf("Erro ao inserir!");
		exit(1);
	}
	for (int i = n; i > 0; i--)
	{
		filmes[i] = filmes[i - 1];
	}
	filmes[0] = fm;
	n++;
}

/**
 * Insere um elemento na ultima posicao da
 * @param fm int elemento a ser inserido.
 */
void inserirFim(Filme fm)
{
	if (n >= MAXTAM)
	{
		printf("Erro ao inserir!");
		exit(1);
	}
	filmes[n] = fm;
	n++;
}

/**
 * Insere um elemento em uma posicao especifica e move os demais
 * elementos para o fim da
 * @param fm int elemento a ser inserido.
 * @param pos Posicao de insercao.
 */
void inserir(Filme fm, int pos)
{

	if (n >= MAXTAM || pos < 0 || pos > n)
	{
		printf("Erro ao inserir!");
		exit(1);
	}

	for (int i = n; i > pos; i--)
	{
		filmes[i] = filmes[i - 1];
	}

	filmes[pos] = fm;
	n++;
}

/**
 * Remove um elemento da primeira posicao da lista e movimenta
 * os demais elementos para o inicio da mesma.
 * @return resp Filme elemento a ser removido.
 */
Filme removerInicio()
{
	Filme resp;
	if (n == 0)
	{
		printf("Erro ao remover!");
		exit(1);
	}
	resp = filmes[0];
	n--;
	for (int i = 0; i < n; i++)
	{
		filmes[i] = filmes[i + 1];
	}
	return resp;
}

/**
 * Remove um elemento da ultima posicao da
 * @return resp Filme elemento a ser removido.
 */
Filme removerFim()
{
	if (n == 0)
	{
		printf("Erro ao remover!");
		exit(1);
	}

	return filmes[--n];
}

/**
 * Remove um elemento de uma posicao especifica da lista e
 * movimenta os demais elementos para o inicio da mesma.
 * @param pos Posicao de remocao.
 * @return resp Filme elemento a ser removido.
 */
Filme remover(int pos)
{
	Filme resp;
	if (n == 0 || pos < 0 || pos >= n)
	{
		printf("Erro ao remover!");
		exit(1);
	}
	resp = filmes[pos];
	n--;
	for (int i = pos; i < n; i++)
	{
		filmes[i] = filmes[i + 1];
	}
	return resp;
}

int main()
{
	char *entradas[1000]; // Array de entradas com os endereços dos filmes
	char entrada[STRMAX], comando[STRMAX];
	char *verificacoes[1000]; // Array de entradas com os endereços dos filmes
	// char *remocoes[1000];	  // Array de remocoes contendo nome dos filmes removidos
	int count = 0, countver = 0, vers = 0, num = 0, ctr = 0;
	do
	{
		fgets(entrada, STRMAX, stdin);
		// printf("%s",entrada);
		if (!isFim(entrada))
		{
			entradas[count] = (char *)malloc((strlen(entrada) + 1) * sizeof(char));
			strcpy(entradas[count], entrada);
			count++;
		}
	} while (!isFim(entrada));
	// inserindo verificações
	scanf("%d%*c", &num);
	// fgets(entrada, STRMAX, stdin);
	for (int i = 0; i < num; i++)
	{
		fgets(entrada, STRMAX, stdin);
		// printf("%s\n",entrada);
		verificacoes[countver] = (char *)malloc((strlen(entrada) + 1) * sizeof(char));
		strcpy(verificacoes[countver], entrada);
		countver++;
		if (strstr(mySubString(verificacoes[i], 0, 1), "I"))
		{
			vers++;
		}
	}
	start(count, vers);
	// criação dos objetos de filme/leitura/impressao
	for (int i = 0; i < count; i++)
	{
		Filme f;
		f = ler(f, entradas[i]);
		inserirFim(f);
	}

	// executando comandos da lista de acordo com a demanda
	for (int i = 0; i < num; i++)
	{
		strcpy(comando, verificacoes[i]);
		comando[strcspn(comando, "\n")] = 0;
		comando[strcspn(comando, "\r")] = 0;
		/*
		printf("---------------------------------------------------------------------------------------------------------------> \n %s %d\n", comando, i);
		for (int i = 0; i < n; i++)
		{
			printf("[%d] ", i);
			imprimir(filmes[i]);
		}
		printf("--------------------------------------------------------------------------------------------------------------------- \n");
		printf("%s \n",comando);
		*/
		if (strstr(comando, "II"))
		{
			// printf("%s - %s -  %d \n", comando, mySubString(comando, 3, strlen(comando)), (int)strlen(comando));
			Filme fm = ler(fm, mySubString(comando, 3, strlen(comando)));
			inserirInicio(fm);
		}
		else if (strstr(comando, "IF"))
		{
			Filme fm = ler(fm, mySubString(comando, 3, strlen(comando)));
			inserirFim(fm);
		}
		else if (strstr(comando, "I*"))
		{
			char aux[STRMAX];
			strcpy(aux, lerEntreSpaces(comando));
			int pos = atoi(aux);
			Filme fm = ler(fm, mySubString(comando, strlen(aux) + 4, strlen(comando)));
			inserir(fm, pos);
		}
		else if (strstr(comando, "RI"))
		{
			printf("(R) %s\n", removerInicio().nome);
			// remocoes[ctr] = (char *)malloc(STRMAX * sizeof(char));
			// strcpy(remocoes[ctr], removerInicio().nome);
			// ctr++;
		}
		else if (strstr(comando, "RF"))
		{
			// remocoes[ctr] = (char *)malloc(STRMAX * sizeof(char));
			// strcpy(remocoes[ctr], removerFim().nome);
			printf("(R) %s\n", removerFim().nome);
			// ctr++;
		}
		else if (strstr(comando, "R*"))
		{
			int pos = atoi(lerEntreSpaces(comando));
			// remocoes[ctr] = (char *)malloc(STRMAX * sizeof(char));
			// strcpy(remocoes[ctr], remover(pos).nome);
			printf("(R) %s\n", remover(pos).nome);
			// ctr++;
		}
	}

	/* Printando resultados das inserções e remoções
	for (int j = 0; j < ctr; j++)
	{
		printf("(R) %s \n", remocoes[j]);
	}
	*/
	for (int i = 0; i < n; i++)
	{
		printf("[%d] ", i);
		imprimir(filmes[i]);
	}

	return 0;
}
