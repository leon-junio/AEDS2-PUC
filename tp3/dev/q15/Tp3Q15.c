#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <dirent.h>
#include <time.h>
// BIBLIOTECAS USADAS PARA REALIZAÇÃO DESSA ATIVIDADE

// CONSTANTE DE TAMANHO MÁXIMO DE STRING E ARRAYS DE CHAR
#define STRMAX 1200
int mov = 0, comp = 0;

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

typedef struct Celula
{
    Filme elemento;      // Elemento inserido na celula.
    struct Celula *prox; // Aponta a celula prox.
    struct Celula *ant;  // Aponta a celula ant.
} Celula;

Celula *novaCelula(Filme elemento)
{
    Celula *nova = (Celula *)malloc(sizeof(Celula));
    nova->elemento = elemento;
    nova->prox = NULL;
    nova->ant = NULL;
    return nova;
}

int n, MAXTAM;
Celula *primeiro, *ultimo;

void start()
{
    // gerando lista de filmes em C
    Filme f;
    primeiro = novaCelula(f);
    ultimo = primeiro;
}

void gerarLog(double tempo)
{
    FILE *file;
    char *buffer = (char *)malloc(sizeof(char) * STRMAX);
    char *cp = (char *)malloc(sizeof(char) * STRMAX);
    char *mv = (char *)malloc(sizeof(char) * STRMAX);
    sprintf(mv, "%d", mov);
    sprintf(cp, "%d", comp);
    sprintf(buffer, "%f", tempo);
    file = fopen("1369371_quicksort2.txt", "w");
    fputs("1369371", file);
    fputs("\t", file);
    fputs(cp, file);
    fputs("\t", file);
    fputs(mv, file);
    fputs("\t", file);
    fputs(buffer, file);
    fputs("\t", file);
    fclose(file);
    free(buffer);
    free(cp);
    free(mv);
}

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

// Função que checa se uma String é antes ou depois de outra String
// Gerando assim uma verificação de ordem alfabética
// frase --> String que vai ser comparada com outra
// ver --> String que vai ser usada como comparação
bool isStrMaior(char frase[STRMAX], char ver[STRMAX])
{
    bool resp = false;
    for (int i = 0; i < strlen(ver); i++)
    {
        if (frase[i] < ver[i])
        {
            resp = false;
            i = strlen(ver);
        }
        else if (frase[i] > ver[i])
        {
            resp = true;
            i = strlen(ver);
        }
        else if (i == strlen(ver))
        {
            if (strlen(ver) == strlen(frase))
            {
                resp = false;
            }
            else
            {
                resp = true;
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
    printf("%g ", filme.orcamento);
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

// CONSTRUIR LISTA AQUI

/**
 * Insere um elemento na primeira posicao da lista.
 * @param x int elemento a ser inserido.
 */
void inserirInicio(Filme x)
{
    Celula *tmp = novaCelula(x);
    tmp->ant = primeiro;
    tmp->prox = primeiro->prox;
    primeiro->prox = tmp;
    if (primeiro == ultimo)
    {
        ultimo = tmp;
    }
    else
    {
        tmp->prox->ant = tmp;
    }
    tmp = NULL;
}

/**
 * Insere um elemento na ultima posicao da lista.
 * @param x int elemento a ser inserido.
 */
void inserirFim(Filme x)
{
    ultimo->prox = novaCelula(x);
    ultimo->prox->ant = ultimo;
    ultimo = ultimo->prox;
}

/**
 * Remove um elemento da primeira posicao da lista.
 * @return resp int elemento a ser removido.
 */
Filme removerInicio()
{
    if (primeiro == ultimo)
    {
        printf("Erro ao remover (vazia)!");
    }

    Celula *tmp = primeiro;
    primeiro = primeiro->prox;
    Filme resp = primeiro->elemento;
    tmp->prox = primeiro->ant = NULL;
    free(tmp);
    tmp = NULL;
    return resp;
}

/**
 * Remove um elemento da ultima posicao da lista.
 * @return resp int elemento a ser removido.
 */
Filme removerFim()
{
    if (primeiro == ultimo)
    {
        printf("Erro ao remover (vazia)!");
    }
    Filme resp = ultimo->elemento;
    ultimo = ultimo->ant;
    ultimo->prox->ant = NULL;
    free(ultimo->prox);
    ultimo->prox = NULL;
    return resp;
}

/**
 *  Calcula e retorna o tamanho, em numero de elementos, da lista.
 *  @return resp int tamanho
 */
int tamanho()
{
    int tamanho = 0;
    Celula *i;
    for (i = primeiro; i != ultimo; i = i->prox, tamanho++)
        ;
    return tamanho;
}

/**
 * Insere um elemento em uma posicao especifica considerando que o
 * primeiro elemento valido esta na posicao 0.
 * @param x int elemento a ser inserido.
 * @param pos int posicao da insercao.
 * @throws Exception Se <code>posicao</code> invalida.
 */
void inserir(Filme x, int pos)
{
    int tam = tamanho();
    if (pos < 0 || pos > tam)
    {
        printf("Erro ao remover (posicao %d/%d invalida!", pos, tam);
    }
    else if (pos == 0)
    {
        inserirInicio(x);
    }
    else if (pos == tam)
    {
        inserirFim(x);
    }
    else
    {
        // Caminhar ate a posicao anterior a insercao
        Celula *i = primeiro;
        int j;
        for (j = 0; j < pos; j++, i = i->prox)
            ;
        Celula *tmp = novaCelula(x);
        tmp->ant = i;
        tmp->prox = i->prox;
        tmp->ant->prox = tmp->prox->ant = tmp;
        tmp = i = NULL;
    }
}

/**
 * Remove um elemento de uma posicao especifica da lista
 * considerando que o primeiro elemento valido esta na posicao 0.
 * @param posicao Meio da remocao.
 * @return resp int elemento a ser removido.
 * @throws Exception Se <code>posicao</code> invalida.
 */
Filme remover(int pos)
{
    Filme resp;
    int tam = tamanho();
    if (primeiro == ultimo)
    {
        printf("Erro ao remover (vazia)!");
    }
    else if (pos < 0 || pos >= tam)
    {
        printf("Erro ao remover (posicao %d/%d invalida!", pos, tam);
    }
    else if (pos == 0)
    {
        resp = removerInicio();
    }
    else if (pos == tam - 1)
    {
        resp = removerFim();
    }
    else
    {
        // Caminhar ate a posicao anterior a insercao
        Celula *i = primeiro->prox;
        int j;
        for (j = 0; j < pos; j++, i = i->prox)
            ;
        i->ant->prox = i->prox;
        i->prox->ant = i->ant;
        resp = i->elemento;
        i->prox = i->ant = NULL;
        free(i);
        i = NULL;
    }
    return resp;
}

/**
 * Mostra os elementos da lista separados por espacos.
 */
void mostrar()
{
    Celula *i;
    for (i = primeiro->prox; i != NULL; i = i->prox)
    {
        imprimir(i->elemento);
    }
}

/**
 * Mostra os elementos da lista de forma invertida
 * e separados por espacos.
 */
void mostrarInverso()
{

    Celula *i;
    for (i = ultimo; i != primeiro; i = i->ant)
    {
        imprimir(i->elemento);
    }
}

// forma de localizar celulas em uma lista duplamente encadeada
Celula *localizarCel(int pos)
{
    Celula *resp;
    int tam = tamanho();
    if (primeiro == ultimo)
    {
        printf("A lista se encontra vazia!");
    }
    else if (pos == 0)
    {
        resp = primeiro->prox;
    }
    else if (pos == tam)
    {
        resp = ultimo;
    }
    else if (pos < 0 || pos > tam)
    {
        printf("Posição para busca inválida na lista!");
    }
    else
    {
        Celula *tmp = primeiro;
        for (int i = 0; i < pos; i++, tmp = tmp->prox)
            ;
        resp = tmp->prox;
    }
    return resp;
}

// Swap para listas duplamente encadeadas
void swap(int esq, int dir)
{
    Celula *cel1 = localizarCel(esq);
    Celula *cel2 = localizarCel(dir);
    Celula *tmp = cel1;
    cel1->ant = cel2->ant;
    cel1->prox = cel2->prox;
    cel2->ant->prox = cel1;
    cel2->prox->ant = cel1;
    cel2->ant = tmp->ant;
    cel2->prox = tmp->prox;
    tmp->ant->prox = cel2;
    tmp->prox->ant = cel2;
}

// QUICKSORT MODIFICADO PARA LISTAS DUPLAMENTE ENCADEADAS
void quickSort(int esq, int dir)
{
    int meio = (esq + dir) / 2;
    Celula *pivo, *cele = localizarCel(esq), *celd = localizarCel(dir);
    pivo = localizarCel(meio);
    int i = esq, j = dir;
    mov += 6;
    if (!strstr(localizarCel(i)->elemento.situacao, localizarCel(j)->elemento.situacao) && !strstr(localizarCel(i)->elemento.situacao, pivo->elemento.situacao))
    {
        while (i <= j)
        {
            while (!isStrMaior(cele->elemento.situacao, pivo->elemento.situacao))
            {
                i++;
                cele = cele->prox;
            }
            while (isStrMaior(celd->elemento.situacao, pivo->elemento.situacao))
            {
                j--;
                celd = celd->ant;
            }
            comp++;
            if (i <= j)
            {
                swap(i, j);
                mov += 2;
                i++;
                j--;
            }
        }
        comp++;
        if (esq < j)
        {
            quickSort(esq, j);
        }
        comp++;
        if (i < dir)
        {
            quickSort(i, dir);
        }
    }
}

// FIM DA LISTA
bool isFim(char *entrada)
{
    entrada[strcspn(entrada, "\n")] = 0;
    entrada[strcspn(entrada, "\r")] = 0;
    return strlen(entrada) == 3 && entrada[0] == 'F' && entrada[1] == 'I' && entrada[2] == 'M';
}

int main()
{
    char *entradas[1000]; // Array de entradas com os endereços dos filmes
    char entrada[STRMAX], comando[STRMAX];
    clock_t Ticks[2];
    int count = 0, countver = 0, num = 0, ctr = 0;
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

    start();
    // criação dos objetos de filme/leitura/impressao
    for (int i = 0; i < count; i++)
    {
        Filme f;
        f = ler(f, entradas[i]);
        inserirInicio(f);
    }
    Ticks[0] = clock();
    quickSort(0, count);
    Ticks[1] = clock();
    double tempo = (Ticks[1] - Ticks[0]) * 1000.0 / CLOCKS_PER_SEC;
    gerarLog(tempo);
    mostrarInverso();
    return 0;
}
