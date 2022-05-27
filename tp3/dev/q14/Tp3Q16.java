import java.util.ArrayList;

class Celula {
    public int elemento;
    public Celula inf, sup, esq, dir;

    public Celula() {
        this(0);
    }

    public Celula(int elemento) {
        this(elemento, null, null, null, null);
    }

    public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir) {
        this.elemento = elemento;
        this.inf = inf;
        this.sup = sup;
        this.esq = esq;
        this.dir = dir;
    }
}

class Matriz {
    private Celula inicio;
    private int linha, coluna;

    public Matriz() {
        this(3, 3);
    }

    public Matriz(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        // 1 parte da construção da matriz - linha 1
        inicio = new Celula();
        Celula temp = inicio;
        for (int i = 1; i <= coluna; i++) {
            temp.dir = new Celula();
            temp.dir.esq = temp;
            temp = temp.dir;
        }
        // 2 parte da construção - n linhas abaixo que tiver
        Celula tmp = inicio;
        for (int l = 1; l < linha; l++, tmp = tmp.inf) {
            Celula i = tmp; // linha superior que vai ser usada para preencher o sup
            i.inf = new Celula();
            i.inf.sup = i;
            Celula j = i.inf;
            for (int c = 1; c <= coluna; c++, j = j.dir) {
                i = i.dir; // linha inferior para preencher lateralmente e inferiormente
                i.inf = new Celula();
                i.inf.sup = i;
                i.inf.esq = j;
                j.dir = i.inf;
            }
        }
    }

    // Função para inserir dentro da matriz de inteiros
    public void insert(ArrayList<Integer> lista) {
        Celula tmp = this.inicio.dir;
        int num = 0;
        for (int l = 0; l < this.linha; l++, tmp = tmp.inf) {
            Celula j = tmp;
            for (int c = 1; c <= this.coluna; c++, j = j.dir) {
                j.elemento = lista.get(num);
                num++;
            }
        }
    }

    // Função para inserir dentro da matriz de inteiros
    public void insert(int[] lista) {
        Celula tmp = this.inicio.dir;
        int num = 0;
        for (int l = 0; l < this.linha; l++, tmp = tmp.inf) {
            Celula j = tmp;
            for (int c = 1; c <= this.coluna; c++, j = j.dir) {
                j.elemento = lista[num];
                num++;
            }
        }
    }

    // função de soma das matrizes de inteiros
    public Matriz soma(Matriz m) throws Exception {
        Matriz resp = null;
        if (this.linha == m.linha && this.coluna == m.coluna) {
            resp = new Matriz(this.linha, this.coluna);
            Celula tmpa = this.inicio.dir, celi, celj;
            Celula tmpb = m.inicio.dir;
            int[] result = new int[this.linha * this.coluna];
            int i = 0;
            // Somando cada numero em cada index igual da matriz 1 e 2
            for (int l = 0; l < this.linha; l++, tmpa = tmpa.inf, tmpb = tmpb.inf) {
                celi = tmpa;
                celj = tmpb;
                for (int c = 1; c <= this.coluna; c++, celi = celi.dir, celj = celj.dir) {
                    result[i] = celi.elemento + celj.elemento;
                    i++;
                }
            }
            resp.insert(result);
            tmpa = null;
            tmpb = null;
            celi = null;
            celj = null;
        } else {
            throw new Exception("Erro ao tentar somar devido a diferença de tamanho entre as matrizes");
        }
        return resp;
    }

    // Multiplicação entre matrizes - custo elevado para realizar essa operação
    public Matriz multiplicacao(Matriz m) throws Exception {
        Matriz resp = null;
        if (this.linha == m.linha && this.coluna == m.coluna) {
            resp = new Matriz(this.linha, this.coluna);
            Celula tmpa = this.inicio.dir, celi, celj;
            Celula tmpb = m.inicio.dir;
            int[] result = new int[this.linha * this.coluna];
            int i = 0;
            // percorrer as linhas para poder fazer as multiplicações por colunas
            for (int l = 0; l < linha; l++, tmpa = tmpa.inf) {
                celi = tmpa;
                celj = tmpb;
                // para cada coluna percorro as linhas debaixo para cima
                // andando para direita
                for (int c = 1; c <= coluna; c++, celj = celj.dir) {
                    Celula tmp = celj;
                    // andando para baixo nas colunas e multiplicando linhas da matriz 1 por colunas
                    // da matriz 2 e guardando resultado em um vetor de inteiros
                    for (int j = 1; j <= coluna; j++, tmp = tmp.inf) {
                        result[i] += celi.elemento * tmp.elemento;
                        celi = celi.dir;
                    }
                    i++;
                    celi = tmpa;
                }
            }
            // inserindo os resultados da multiplicação dentro da matriz gerada
            resp.insert(result);
            tmpa = null;
            tmpb = null;
            celi = null;
            celj = null;
        } else {
            throw new Exception("Erro ao tentar multiplicar devido a diferença de tamanho entre as matrizes");
        }
        return resp;
    }

    public boolean isQuadrada() {
        return (this.linha == this.coluna);
    }

    // Função para mostrar a diagonal principal da matriz atual
    public void mostrarDiagonalPrincipal() throws Exception {
        if (isQuadrada() == true) {
            Celula tmp = this.inicio;
            for (int j = 0; j < this.coluna; j++, tmp = tmp.inf) {
                MyIO.print(tmp.dir.elemento + " ");
                tmp = tmp.dir;
            }
            MyIO.println("");
        } else {
            throw new Exception("A matriz não é quadrada impossivel imprimir sua diagonal!");
        }
    }

    // Função para mostrar a diagonal secundaria da matriz atual
    public void mostrarDiagonalSecundaria() throws Exception {
        if (isQuadrada() == true) {
            Celula tmp = this.inicio;
            for (int l = 0; l < this.linha; l++, tmp = tmp.dir)
                ;
            for (int c = 1; c <= this.coluna; c++, tmp = tmp.esq, tmp = tmp.inf) {
                MyIO.print(tmp.elemento + " ");
            }
            MyIO.println("");
            tmp = null;
        } else {
            throw new Exception("A matriz não é quadrada impossivel imprimir sua diagonal!");
        }
    }

    // Função para imprimir detalhadamente os dados da matriz
    public void print() {
        Celula tmp = this.inicio.dir;
        Celula j;
        for (int l = 0; l < this.linha; l++, tmp = tmp.inf) {
            j = tmp;
            for (int c = 1; c <= this.coluna; c++, j = j.dir) {
                MyIO.print(j.elemento + " ");
            }
            MyIO.println("");
        }
    }

}

public class Tp3Q16 {
    public static void main(String[] args) {
        int num = MyIO.readInt(), index = 0;
        Matriz mat_a, mat_b;
        // Rodando e executando os casos de uso
        try {
            while (index < num) {
                mat_a = gerarMatriz();
                mat_b = gerarMatriz();
                mat_a.mostrarDiagonalPrincipal();
                mat_a.mostrarDiagonalSecundaria();
                mat_a.soma(mat_b).print();
                mat_a.multiplicacao(mat_b).print();
                index++;
                mat_a = null;
                mat_b = null;
            }
        } catch (Exception e) {
            System.out.println("Erro interno no main - " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Função que gera e retorna uma matriz já preenchida para o main
    public static Matriz gerarMatriz() throws Exception {
        int l = 0, c = 0;
        ArrayList<Integer> listNums = new ArrayList<>();
        l = MyIO.readInt();
        c = MyIO.readInt();
        Matriz matriz = new Matriz(l, c);
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < c; j++) {
                listNums.add(MyIO.readInt());
            }
        }
        // inserindo os numeros lidos dentro da matriz de inteiros
        matriz.insert(listNums);
        return matriz;
    }

}
