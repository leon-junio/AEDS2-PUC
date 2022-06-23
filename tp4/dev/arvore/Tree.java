class No {
    public No esq, dir;
    public int elemento;

    public No() {
        this.esq = null;
        this.dir = null;
        this.elemento = 0;
    }

    public No(int x) {
        this.esq = null;
        this.dir = null;
        this.elemento = x;
    }

    public int getElemento() {
        return elemento;
    }

    public void setElemento(int elemento) {
        this.elemento = elemento;
    }

}

class ArvoreBinaria {
    No raiz;

    public ArvoreBinaria() {
        raiz = null;
    }

    public void inserir(int x) throws Exception {
        raiz = inserir(x, raiz);
    }

    private No inserir(int x, No no) throws Exception {
        if (no == null) {
            no = new No(x);
        } else if (x < no.elemento) {
            no.esq = inserir(x, no.esq);
        } else if (x > no.elemento) {
            no.dir = inserir(x, no.dir);
        } else {
            throw new Exception("Erro o elemento informado já foi adicionado na arvore! -> " + x);
        }
        return no;
    }

    public void inserirPai(int x) throws Exception {
        if (raiz == null) {
            raiz = new No(x);
        } else if (x < raiz.elemento) {
            inserirPai(x, raiz.esq, raiz);
        } else if (x > raiz.elemento) {
            inserirPai(x, raiz.dir, raiz);
        } else {
            throw new Exception("Erro o elemento informado já foi adicionado na arvore! -> " + x);
        }
    }

    private void inserirPai(int x, No no, No pai) throws Exception {
        if (no == null) {
            if (x < pai.elemento) {
                pai.esq = new No(x);
            } else {
                pai.dir = new No(x);
            }
        } else if (no.elemento < x) {
            inserirPai(x, no.esq, no);
        } else if (no.elemento > x) {
            inserirPai(x, no.dir, no);
        } else {
            throw new Exception("Erro o elemento informado já foi adicionado na arvore! -> " + x);
        }
    }

    private No getMaiorEsq(No i, No j) {
        if (j.dir == null) {
            i.elemento = j.elemento;
            j = j.esq; // ao remover a gente pega o elemento na direita e conecta no nó removido
            // pode ser null ou uma outra subarvore e vai se conectar normalmente
        } else {
            j.dir = getMaiorEsq(i, j.dir); // se o elemento da direita nao for nulo continua indo ate encontrar
        }
        return j;
    }

    public void remover(int x) throws Exception {
        raiz = remover(x, raiz);
    }

    private No remover(int x, No no) throws Exception {
        if (no == null) {
            throw new Exception("Nó nulo encontrado erro ao remover n°: " + x);
        } else if (x < no.elemento) {
            no.esq = remover(x, no.esq);
        } else if (x > no.elemento) {
            no.dir = remover(x, no.dir);
        } else if (no.dir == null) {
            no = no.esq;
        } else if (no.esq == null) {
            no = no.dir;
        } else {
            no = getMaiorEsq(no, no.esq);
        }
        return no;
    }

    public boolean pesquisar(int x) {
        boolean resp = false;
        if (x == raiz.elemento) {
            resp = true;
        } else if (x < raiz.elemento) {
            resp = pesquisar(x, raiz.esq);
        } else if (x < raiz.elemento) {
            resp = pesquisar(x, raiz.dir);
        }
        return resp;
    }

    private boolean pesquisar(int x, No no) {
        boolean resp = false;
        if (no == null) {
            resp = false;
        } else if (x < no.elemento) {
            resp = pesquisar(x, no.esq);
        } else if (x > no.elemento) {
            resp = pesquisar(x, no.dir);
        } else if (x == no.elemento) {
            resp = true;
        }
        return resp;
    }

    public int altura() {
        return altura(raiz, 0);
    }

    private int altura(No no, int tam) {
        if (no == null) {
            tam--;
        } else {
            int alturaEsq = altura(no.esq, tam + 1);
            int alturaDir = altura(no.dir, tam + 1);
            tam = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
            // System.out.println("tam " + tam);
        }
        return tam;
    }

    public void imprimirNivel(int nivel) throws Exception {
        if (raiz == null) {
            throw new Exception("Erro a arvore se encontra vazia");
        } else if (nivel == 0) {
            System.out.println(raiz.elemento);
        } else {
            imprimirNivel(raiz, 0, nivel);
            System.out.println("");
        }
    }

    private void imprimirNivel(No no, int num, int nivel) throws Exception {
        if (no == null) {
            num--;
        } else if (num == nivel) {
            System.out.print(no.elemento + " ");
        } else {
            num++;
            imprimirNivel(no.esq, num, nivel);
            imprimirNivel(no.dir, num, nivel);
        }
    }

    public void mostrarNiveis() throws Exception {
        int alt = altura();
        System.out.println("ALTURA:" + alt + 1);
        System.out.println("ARVORE: ");
        for (int i = 0; i <= alt; i++) {
            imprimirNivel(i);
        }
    }

    public No rotacionarEsq(No no) {
        No nodir = no.dir;
        No noDirEsq = nodir.esq;
        nodir.esq = no;
        no.dir = noDirEsq;
        return nodir;
    }

    public No rotacionarDir(No no) {
        No noesq = no.esq;
        No noEsqDir = noesq.dir;
        noesq.dir = no;
        no.esq = noEsqDir;
        return noesq;
    }

}

public class Tree {
    public static void main(String[] args) {
        try {
            ArvoreBinaria arv = new ArvoreBinaria();
            int num;
            do {
                System.out.println("Insira um numero: ");
                num = MyIO.readInt();
                arv.inserir(num);
                System.out.println("RAIZ: " + arv.raiz.elemento);
                arv.mostrarNiveis();
            } while (num != 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}