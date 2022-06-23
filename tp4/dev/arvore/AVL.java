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
        return balancear(i);
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
        return balancear(i);
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

    public No balancear(No i) throws Exception {
        int fator = i.getNivel(i.dir) - i.getNivel(i.esq);
        if (Math.abs(fator) <= 1) {
            i.setNivel();
        } else if (fator == 2) {
            int fatFilho = i.getNivel(i.dir.dir) - i.getNivel(i.dir.esq);
            if (fatFilho == 0 || fatFilho == 1) {
                i = rotacionarEsq(i);
            } else if (fatFilho == -1) {
                i.dir = rotacionarDir(i.dir);
                i = rotacionarEsq(i);
            }
        } else if (fator == -2) {
            int fatFilho = i.getNivel(i.esq.dir) - i.getNivel(i.esq.esq);
            if (fatFilho == -1 || fatFilho == 0) {
                i = rotacionarDir(i);
            } else if (fatFilho == 1) {
                i.esq = rotacionarEsq(i.esq);
                i = rotacionarDir(i);
            }
        } else {
            throw new Exception("Erro ao balancear o nó");
        }
        return i;
    }

    private No rotacionarDir(No no) {
        No noe = no.esq;
        No noed = noe.dir;
        noe.dir = no;
        no.esq = noed;
        no.setNivel();
        noe.setNivel();
        return noe;
    }

    private No rotacionarEsq(No no) {
        No nod = no.dir;
        No node = nod.esq;
        nod.esq = no;
        no.dir = node;
        no.setNivel();
        nod.setNivel();
        return nod;
    }

}

class No {
    public int elemento;
    public No esq, dir;
    public int nivel;

    public No(int x) {
        this.elemento = x;
        this.esq = null;
        this.dir = null;
        this.nivel = 0;
    }

    public int getElemento() {
        return elemento;
    }

    public void setElemento(int elemento) {
        this.elemento = elemento;
    }

    public void setNivel() {
        this.nivel = 1 + Math.max(getNivel(this.dir), getNivel(this.esq));
    }

    public int getNivel(No i) {
        return i == null ? 0 : i.nivel;
    }

}

public class AVL {
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