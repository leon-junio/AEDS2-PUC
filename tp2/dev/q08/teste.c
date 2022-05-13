#include <stdio.h>

int main(){
    int i = 10;
    while(i > 0){
        i = i >> 1;
        printf("oi %d \n",i);
    }
    return 0;
}