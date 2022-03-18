#include <stdio.h>
#include <stdbool.h>


bool isInteger(double number){
    int prov = (int)number;
    return (number == prov);
}

void writeNumbers(int maximo){
    double atual;
    FILE* writeNumbers = fopen("numbers.txt","w");
    
    for (int i = 0; i < maximo; i++)
    {
        scanf("%lf",&atual);
        fprintf(writeNumbers,"%lf\n",atual);
    }
    fclose(writeNumbers);
}

void readNumbeFile(long pos,int atual,int max){
    FILE* readNumbers = fopen("numbers.txt","r");
    double number;
    char charPointer;
    char charLastPointer;
    if (atual < max)
    {
        fseek(readNumbers,pos,SEEK_SET);
        fscanf(readNumbers,"%lf",&number);
        fseek(readNumbers,pos,SEEK_SET);
        do
        {
            fscanf(readNumbers,"%c",&charPointer);
            pos++;
            //printf("pos: %ld\tcaractere: %c\n",pos,charPointer);
        }while(charPointer != '\n' && charPointer != EOF);
        //fscanf(readNumbers,"%c",&charLastPointer);
        fclose(readNumbers);
        //printf("charPointer: %c\n",charPointer);
        if (charPointer == '\n')
        {
            //printf("pos: %ld, \\n", pos);
            readNumbeFile(pos,atual+1,max);
        }
        
        
    }
    //printf("Pos: %ld\tNumer: %lf\tAtual: %d\n",pos,number,atual);
    if (atual < max)
    {
        if (isInteger(number))
        {
            printf("%d\n",(int)number);
        }
        else
        {
            printf("%g\n",number);
        }
    }
    
    
}

int main(){
    int maximo;
    //printf("tentativa 1: ");
    scanf("%d",&maximo);
    writeNumbers(maximo);
    printf("leitura\n");
    readNumbeFile(0,0,maximo);
    return 0;
}