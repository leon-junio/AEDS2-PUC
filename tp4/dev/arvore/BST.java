class Arvere {
    private No raiz;

    public Arvere() {
        this.raiz = null;
    }

    public void inserir(int x) throws Exception {
        raiz = inserir(x, raiz);
    }

    private No inserir(int x, No i) throws Exception {
        if (i == null) {
            i = new No(x);
        } else if (x < i.elemento) {
            i.esq = inserir(x, i.esq);
        } else if (x > i.elemento) {
            i.dir = inserir(x, i.dir);
        } else {
            throw new Exception("Erro ao inserir");
        }
        return i;
    }

    public void remover(int x) throws Exception {
        raiz = remover(x, raiz);
    }

    private No remover(int x, No i) throws Exception {
        if (i == null) {
            throw new Exception("Erro ao remover");
        } else if (x < i.elemento) {
            i.esq = remover(x, i.esq);
        } else if (x > i.elemento) {
            i.dir = remover(x, i.dir);
        } else if (i.dir == null) {
            i = i.esq;
        } else if (i.esq == null) {
            i = i.dir;
        } else {
            i.esq = getMaiorEsq(i, i.esq);
        }
        return i;
    }

    private No getMaiorEsq(No i, No j) {
        if (j.dir == null) {
            i.elemento = j.elemento;
            j = j.esq;
        } else {
            j.dir = getMaiorEsq(i, j.dir);
        }
        return j;
    }

    public void balancearNivel3() throws Exception {
        if (raiz != null) {
            if (raiz.esq != null && raiz.dir != null) {
                System.out.println("Balanceada");
            } else if (raiz.dir != null && raiz.dir.esq != null) {
                System.out.println("Rotação direita esquerda");
                raiz.dir = rotacionarDir(raiz.dir);
                raiz = rotacionarEsq(raiz);
            } else if (raiz.dir != null && raiz.dir.dir != null) {
                System.out.println("Rotação esquerda");
                raiz = rotacionarEsq(raiz);
            } else if (raiz.esq != null && raiz.esq.dir != null) {
                System.out.println("Rotação esquerda direita");
                raiz.esq = rotacionarEsq(raiz.esq);
                raiz = rotacionarDir(raiz);
            } else if (raiz.esq != null && raiz.esq.esq != null) {
                System.out.println("Rotação direita");
                raiz = rotacionarDir(raiz);
            }
        } else {
            throw new Exception("Erro ao balancear!");
        }
    }

    private No rotacionarDir(No no) {
        No noe = no.esq;
        No noed = noe.dir;
        noe.dir = no;
        no.esq = noed;
        return noe;
    }

    private No rotacionarEsq(No no) {
        No nod = no.dir;
        No node = nod.esq;
        nod.esq = no;
        no.dir = node;
        return nod;
    }

}

class No {
    public int elemento;
    public No esq, dir;

    public No(int x) {
        this.elemento = x;
        this.esq = null;
        this.dir = null;
    }

    public int getElemento() {
        return elemento;
    }

    public void setElemento(int elemento) {
        this.elemento = elemento;
    }

}

public class BST {
    public static void main(String[] args) {
        try {
            Arvere arv = new Arvere();
            arv.inserir(2);
            arv.inserir(1);
            arv.inserir(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}