class Arvere {
    private No raiz;

    public Arvere() {
        this.raiz = null;
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

    private boolean isNoTipoQuatro(No i) {
        return i.dir != null && i.esq != null && i.esq.cor == true && i.dir.cor == true;
    }

    private void fragmentarTipo4(No i){
        i.esq.cor = false;
        i.dir.cor = false;
        i.cor = true;
    }

}

class No {
    public int elemento;
    public No esq, dir;
    public boolean cor;

    public No(int x) {
        this.elemento = x;
        this.esq = null;
        this.dir = null;
        this.cor = false;
    }

    public No(int x, boolean cor) {
        this.elemento = x;
        this.esq = null;
        this.dir = null;
        this.cor = cor;
    }

    public No(int x, boolean cor, No esq, No dir) {
        this.elemento = x;
        this.esq = esq;
        this.dir = dir;
        this.cor = cor;
    }

    public int getElemento() {
        return elemento;
    }

    public void setElemento(int elemento) {
        this.elemento = elemento;
    }

}

public class RubroNegra {
    public static void main(String[] args) {
        try {
            Arvere arv = new Arvere();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}