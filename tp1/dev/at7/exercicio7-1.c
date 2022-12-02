#include <stdio.h>
#include <stdbool.h>
#include <string.h>

bool isVogal(char vetor[1000])
{
  bool resp = false;
  int cont = 0;
  for (int i = 0; i < strlen(vetor); i++)
  {
    if (vetor[i] == 'A' || vetor[i] == 'E' || vetor[i] == 'I' ||
        vetor[i] == 'O' || vetor[i] == 'U' || vetor[i] == 'a' ||
        vetor[i] == 'e' || vetor[i] == 'i' || vetor[i] == 'o' ||
        vetor[i] == 'u')
    {
      cont++;
    }
  }
  if (cont == strlen(vetor))
  {
    resp = true;
  }
  return resp;
}

bool isConsoante(char vetor[1000])
{
  bool resp = false;
  int cont = 0;
  for (int i = 0; i < strlen(vetor); i++)
  {
    if (vetor[i] == 'B' || vetor[i] == 'C' || vetor[i] == 'D' ||
        vetor[i] == 'F' || vetor[i] == 'G' || vetor[i] == 'H' ||
        vetor[i] == 'J' || vetor[i] == 'K' || vetor[i] == 'L' ||
        vetor[i] == 'M' || vetor[i] == 'N' || vetor[i] == 'P' ||
        vetor[i] == 'Q' || vetor[i] == 'R' || vetor[i] == 'S' ||
        vetor[i] == 'T' || vetor[i] == 'V' || vetor[i] == 'W' ||
        vetor[i] == 'X' || vetor[i] == 'Y' || vetor[i] == 'Z' ||
        vetor[i] == 'b' || vetor[i] == 'c' || vetor[i] == 'd' ||
        vetor[i] == 'f' || vetor[i] == 'g' || vetor[i] == 'h' ||
        vetor[i] == 'j' || vetor[i] == 'k' || vetor[i] == 'l' ||
        vetor[i] == 'm' || vetor[i] == 'n' || vetor[i] == 'p' ||
        vetor[i] == 'q' || vetor[i] == 'r' || vetor[i] == 's' ||
        vetor[i] == 't' || vetor[i] == 'v' || vetor[i] == 'w' ||
        vetor[i] == 'x' || vetor[i] == 'y' || vetor[i] == 'z')
    {
      cont++;
    }
  }
  if (cont == strlen(vetor))
  {
    resp = true;
  }
  return resp;
}

bool isInt(char vetor[1000])
{
  bool resp = false;
  int cont = 0;
  for (int i = 0; i < strlen(vetor); i++)
  {
    if (vetor[i] == '1' || vetor[i] == '2' || vetor[i] == '3' ||
        vetor[i] == '4' || vetor[i] == '5' || vetor[i] == '6' ||
        vetor[i] == '7' || vetor[i] == '8' || vetor[i] == '9' ||
        vetor[i] == '0')
    {
      cont++;
    }
  }

  if (cont == strlen(vetor))
  {
    resp = true;
  }

  return resp;
}

bool isReal(char string[1000]) {
  bool resp = true;
  int cont = 0;
  for (int i = 0; i < strlen(string); i++) {
    int c = string[i];
    if (c == '1' || c != '2' || c != '3' || c != '4' || c != '5' || c != '6' ||
        c != '7' || c != '8' || c != '5' || c != '9' || c != ',' || c != '.') {
      resp = false;
      i = strlen(string);
    }
    if (c == ',' || c == '.') {
      cont++;
    }
  }
  if (cont > 1) {
    resp = false;
  }
  return resp;
}

bool checkFim(char *str)
{
  return (strlen(str) == 3 && str[0] == 'F' && str[1] == 'I' && str[2] == 'M');
}

int main(void)
{
  char str[1000];
  str[0] = 'O';
  str[1] = 'l';
  str[2] = 'a';
  while (!checkFim(str))
  {
    fgets(str, 1000, stdin);
    str[strcspn(str, "\n")] = 0;
    str[strcspn(str, "\r")] = 0;
    if (!checkFim(str))
    {
      if (isVogal(str) == true)
      {
        printf("SIM ");
      }
      else
      {
        printf("NAO ");
      }

      if (isConsoante(str) == true)
      {
        printf("SIM ");
      }
      else
      {
        printf("NAO ");
      }

      if (isInt(str) == true)
      {
        printf("SIM ");
      }
      else
      {
        printf("NAO ");
      }

      if (isReal(str) == true)
      {
        printf("SIM \n");
      }
      else if (isReal(str) == 0)
      {
        printf("NAO \n");
      }
    }
  }
  return 0;
}