public class teste {
    public static int array[] = new int[100];

    public static void main(String[] args) {

    }

    // PIOR FORMA DE RESOLUÇÃO SEMPRE VAI SER O(N^2)
    // MUITO CUSTOSO PARA CADA NUM PERCORRE TUDO ATÉ O FIM
    public static void bolha() { // FORMA MAIS ARCAICA DE ORDENAR (PRIMEIRO PENSAMENTO BÁSICO DE ORDENAÇÃO)
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(j, j + 1); // FAZ MUITAS TROCAS DESNECESSARIAS E REPETE MUITO
                }
            }
        }
    } // NUNCA USAR*

    // realiza a seleção entre um numero e guarda numa variavel menor pra realizar o
    // swap
    public static void selecao() {
        int menor;
        for (int i = 0; i < array.length; i++) {
            menor = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[menor]) {
                    menor = j;
                }
            }
            swap(i, menor);
        }
    }// CUSTO: sempre O(n^2) --> executa dois fors sempre até o fim para arrays ord e
     // nao ord (dec e asc)

    // Conceito baseado em uma parte ordenada no qual devemos fazer o shift de
    // elementos caso encontre um menor
    public static void insercao() {
        for (int i = 0; i < array.length; i++) {
            int tmp = array[i]; // variavel que vai ser testada internamente
            int fimDaParteOrdenada = i - 1; // devemos lembrar onde a parte ordenada esta acabando no array
            while ((fimDaParteOrdenada >= 0) && array[fimDaParteOrdenada] > tmp) { // so entra se tiver elementos
                                                                                   // menores que algum
                // da parte ordenada tal que tmp é o elemento atual e fimDaParteOrdenada são os
                // numeros ordenados que estamos vendo
                array[fimDaParteOrdenada + 1] = array[fimDaParteOrdenada]; // shift dos elementos
                fimDaParteOrdenada--; // decrementando os indices da parte ordenada caso tenha elementos menores para
                                      // levar
            }
            array[fimDaParteOrdenada + 1] = tmp; // salva o valor da temp na posição correta do array ordenado
        }
    }

    // O quickSort divide o vetor pela metade n vezes até ordenar todos os valores
    // para isso ele tem que saber os valores da esq, dir e o pivo = valor do meio
    // do array analisado. Primeiros particionamos o array com maiores que o pivo na
    // direita e menores na esquerda
    // depois disso temos que ver se ainda precisamos particionar mais e mais
    // recursivamente comparando as entradas i e j com esq e dir
    public static void quickSort(int esq, int dir) {
        int i = esq, j = dir, pivo = array[(dir + esq / 2)]; // pivo já é o valor do meio não é um index
        // vamos particionar definindo que tudo acima do pivo é maior e tudo abaixo é
        // menor
        // primeira parte --> particionamento
        while (i <= j) { // Vai definindo oque é para verificar ou não
            while (array[i] < pivo) // pivo é um valor --> vamos verificar tudo que está antes do pivo em busca de
                                    // um que não seja menor e assim vamos mudar ele de lado
                i++; // vamos vendo se tudo que ta na esquerda realmente é menor que o pivo caso
                     // contrario o i vai ter
            // a pos do numero falso intrometido dentro do lado esquerdo
            // o I vai ter a pos do numero fake pois quando encontrar nao vai fazer i-- vai
            // pular o while
            while (array[j] > pivo) // dessa lado vamos ver se tudo que na direita é maior ou não que o pivô
                j--; // vamos da direita até o pivo procurando um falso elemento e se encontrarmos
                     // vamos altera-lo
            // o J vai ter a pos do numero fake pois quando encontrar nao vai fazer j-- vai
            // pular o while
            if (i <= j) {// se a gente nao topou a esquerda com a direita
                swap(i, j); // troca o numero fake maior da esquerda com o fake menor da direita
                // assim vamos ter sempre uma troca entre menor e maior dos dois lados esq e dir
                i++; // avanço minha esquerda rumo ao meio
                j--; // avanço minha direita rumo ao meio
            }
        }
        // Se os valores originais de Esquerda forem menores que o nosso J temos que
        // ainda há numeros para ordenar na esquerda
        // vamos definir essa parte como um novo vetor agora pela metade do original e
        // assim sucessivimente quantas vezes for vamos ir ordenando
        if (esq < j)
            quickSort(esq, j);

        // Se os valores originais de Direita forem maiores que o nosso I temos que
        // ainda há numeros para ordenar na direita
        // vamos definir essa parte como um novo vetor agora pela metade do original e
        // assim sucessivimente quantas vezes for vamos ir ordenando
        if (dir > i)
            quickSort(i, dir);
    } // CUSTO: N * LOG N --> MELHOR CASO && N^2 ---> PIOR CASO
      // MOVIMENTACAO: 3*(N/2) --> PIOR CASO && 3 MOVS (troca do i == j) --> MELHOR
      // CASO

    // implementa o metodo da inserção internamente com apoio de uma serie
    // matematica
    // definida com serie do H (3H(5-1)+1 --> H(1) = 1)
    // Isso para encontrarmos o primeiro H que vai definir o total de subarrays que
    // vamos ter
    // Se for H == 4 (4 subarrays e vai diminuindo com base na metrica da serie H)
    // A gente deve aplicar uma mudança no inserção no que diz respeito a
    // inicialização do I
    // devemos setar-lo como (H+cor) --> inserção(int cor, int H) e o i variando de
    // acordo com H
    // i += H e o J interno deve ser j -= H
    public static void shellSort() {
        int h = 1;
        do {
            // serie de cagar = cagar tres vezes + uma mijada
            h = (h * 3) + 1; // serie do H e sua formula matemática
        } while (h < array.length); // definindo o valor de H enquanto ele for menor que o total do array
        do {
            h /= 3; // Apos ter o total de H sempre dividimos por 3 para ter o novo total
            // A cagada é a cada 3 dia para continuar a serie de cagadas so depois de 3 dias
            // vamos criar os subvetores e ir ordenando chamando o seleção
            for (int cor = 0; cor < h; cor++) {
                insercao(cor, h);
            }
        } while (h != 1);
    }

    // insercao modificado para uso no shellsort
    public static void insercao(int cor, int h) {
        for (int i = (h + cor); i < array.length; i += h) { // uso a variação baseada em H ao inves de I
            // indice inicial é o total de H + o total da Cor
            int tmp = array[i]; // variavel que vai ser testada internamente
            int fimDaParteOrdenada = i - 1; // devemos lembrar onde a parte ordenada esta acabando no array
            while ((fimDaParteOrdenada >= 0) && array[fimDaParteOrdenada] > tmp) { // so entra se tiver elementos
                                                                                   // menores que algum
                // da parte ordenada tal que tmp é o elemento atual e fimDaParteOrdenada são os
                // numeros ordenados que estamos vendo
                array[fimDaParteOrdenada + 1] = array[fimDaParteOrdenada]; // shift dos elementos
                fimDaParteOrdenada -= h; // substituo o jump de i para H
            }
            array[fimDaParteOrdenada + 1] = tmp; // salva o valor da temp na posição correta do array ordenado
        }
    } // Complexidade desconhecida --> problemas matemáticos

    // Ordenação baseada na construção e destruição de um heap de elementos do maior
    // para o menor (HEAP INVERTIDO)
    // Na hora de destruir estamos salvando os elementos em ordem
    public static void heapSort() {
        // Alterar o vetor ignorando a posicao zero
        int[] tmp = new int[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            tmp[i + 1] = array[i];
        }
        array = tmp;

        // Contrucao do heap
        for (int tamHeap = 2; tamHeap <= array.length; tamHeap++) {
            construir(tamHeap);
        }

        // Ordenacao propriamente dita
        int tamHeap = array.length;
        while (tamHeap > 1) {
            swap(1, tamHeap--);
            reconstruir(tamHeap);
        }

        // Alterar o vetor para voltar a posicao zero
        tmp = array;
        array = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = tmp[i + 1];
        }
    }

    // metodo para construir o heap
    public static void construir(int tam) {
        for (int i = tam; i > 1 && array[i] > array[i / 2]; i /= 2) {
            swap(i, i / 2);
        }
    }


    //metodo de reconstrução do heap pós remoção
    public static void reconstruir(int tamHeap) {
        int i = 1;
        while (i <= (tamHeap / 2)) {
            int filho = getMaiorFilho(i, tamHeap);
            if (array[i] < array[filho]) {
                swap(i, filho);
                i = filho;
            } else {
                i = tamHeap;
            }
        }
    }

    //retorna o maior filho de nó
    public static int getMaiorFilho(int i, int tamHeap) {
        int filho;
        if (2 * i == tamHeap || array[2 * i] > array[2 * i + 1]) {
            filho = 2 * i;
        } else {
            filho = 2 * i + 1;
        }
        return filho;
    }

    public static void swap(int i, int j) {
        int aux = array[i];
        array[i] = array[j];
        array[j] = aux;
    }
}
