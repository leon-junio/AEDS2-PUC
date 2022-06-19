import java.util.Locale;
import java.util.Scanner;

class No {
    public No esq, dir;
    public char elemento;

    public No() {
        this.esq = null;
        this.dir = null;
        this.elemento = 0;
    }

    public No(char x) {
        this.esq = null;
        this.dir = null;
        this.elemento = x;
    }

    public char getElemento() {
        return elemento;
    }

    public void setElemento(char elemento) {
        this.elemento = elemento;
    }

}

class ArvoreBinaria {
    No raiz;

    public ArvoreBinaria() {
        raiz = null;
    }

    public void inserir(char x) throws Exception {
        raiz = inserir(x, raiz);
    }

    private No inserir(char x, No no) throws Exception {
        if (no == null) {
            no = new No(x);
        } else if (x < no.elemento) {
            no.esq = inserir(x, no.esq);
        } else if (x > no.elemento) {
            no.dir = inserir(x, no.dir);
        } else {
            throw new Exception("Erro o elemento informado ja foi adicionado na arvore! -> " + x);
        }
        return no;
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

    public void caminharPre() {
        caminharPre(raiz);
        MyIO.println("");
    }

    private void caminharPre(No i) {
        if (i != null) {
            MyIO.print(i.elemento + " ");
            caminharPre(i.esq);
            caminharPre(i.dir);
        }
    }

    public void caminharCentral() {
        caminharCentral(raiz);
        MyIO.println("");
    }

    private void caminharCentral(No i) {
        if (i != null) {
            caminharCentral(i.esq);
            MyIO.print(i.elemento + " ");
            caminharCentral(i.dir);
        }
    }

    public void caminharPos() {
        caminharPos(raiz);
        MyIO.println("");
    }

    private void caminharPos(No i) {
        if (i != null) {
            caminharPos(i.esq);
            caminharPos(i.dir);
            MyIO.print(i.elemento + " ");
        }
    }

    public void remover(char x) throws Exception {
        raiz = remover(x, raiz);
    }

    private No remover(char x, No no) throws Exception {
        if (no == null) {
            throw new Exception("No nulo encontrado erro ao remover n°: " + x);
        } else if (x < no.elemento) {
            no = remover(x, no.esq);
        } else if (x > no.elemento) {
            no = remover(x, no.dir);
        } else if (no.dir == null) {
            no = no.dir;
        } else if (no.esq == null) {
            no = no.esq;
        } else {
            no = getMaiorEsq(no, no.esq);
        }
        return no;
    }

    public boolean pesquisar(char x) {
        boolean resp = false;
        if (x == raiz.elemento) {
            resp = true;
        } else if (x < raiz.elemento) {
            resp = pesquisar(x, raiz.esq);
        } else if (x > raiz.elemento) {
            resp = pesquisar(x, raiz.dir);
        }
        return resp;
    }

    private boolean pesquisar(char x, No no) {
        boolean resp = false;
        if (no == null) {
            resp = false;
        } else if (x == no.elemento) {
            resp = true;
        } else if (x < no.elemento) {
            resp = pesquisar(x, no.esq);
        } else if (x > no.elemento) {
            resp = pesquisar(x, no.dir);
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

}

public class Tp4q09 {

    public static final Locale loc = new Locale("pt", "BR");
    public static final Scanner sc = new Scanner(System.in, "UTF-8");

    public static void main(String[] args) {
        try {
            sc.useLocale(loc);
            ArvoreBinaria arv = new ArvoreBinaria();
            String comando = "";
            while (sc.hasNext()) {
                comando = sc.nextLine();
                if (comando.contains("I ")) {
                    arv.inserir(comando.charAt(2));
                } else if (comando.contains("P ")) {
                    if (arv.pesquisar(comando.charAt(2))) {
                        MyIO.println(comando.charAt(2) + " existe");
                    } else {
                        MyIO.println(comando.charAt(2) + " nao existe");
                    }
                } else if (comando.contains("INFIXA")) {
                    arv.caminharCentral();
                } else if (comando.contains("POSFIXA")) {
                    arv.caminharPos();
                } else if (comando.contains("PREFIXA")) {
                    arv.caminharPre();
                }
            }
        } catch (Exception e) {
            System.out.println("Erro no main --> " + e.getMessage());
            e.printStackTrace();
        }
    }
}