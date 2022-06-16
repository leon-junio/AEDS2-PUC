import java.util.Date;
import java.util.ArrayList;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//Classe de ferramentas desenvolvida por Leon Júnio
//Essa classe substitui todos os métodos principais da
//biblioteca String e ainda possuí alguns conversores de data
//horas e demais funções
class Ferramentas {

    // MATRICULA COMO ALUNO DEFINIDA PARA USO INTERNO NO SISTEMA
    private final static String matricula = "1369371";

    public static String getMatricula() {
        return matricula;
    }

    // Função que checa se uma String é antes ou depois de outra String
    // Gerando assim uma verificação de ordem alfabética
    // frase --> String que vai ser comparada com outra
    // ver --> String que vai ser usada como comparação
    public static boolean isStrMaior(String frase, String ver) {
        boolean resp = false;
        for (int i = 0; i < ver.length(); i++) {
            if (frase.charAt(i) < ver.charAt(i)) {
                resp = false;
                i = ver.length();
            } else if (frase.charAt(i) > ver.charAt(i)) {
                resp = true;
                i = ver.length();
            } else if (i == ver.length()) {
                if (ver.length() == frase.length()) {
                    resp = false;
                } else {
                    resp = true;
                }
            }
        }
        return resp;
    }

    // Função para comparar Strings e retornar um inteiro
    // retorna > 1 se a str1 for maior que a str2
    // retorna < 1 se a str1 for menor que a str2
    // retorna == 0 se a str1 for igual a str2
    public static int comparadorStr(String str, String str2) {
        return str.compareTo(str2);
    }

    // Função responsavel por gerar o log contendo as informações de execução do
    // código
    // Tais como o tempo, comparações e minha matricula
    public static boolean gerarLog(double inic, double fim, int comp) {
        boolean resp = true;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(getMatricula() + "_arvoreArvore.txt"));
            bw.write(getMatricula() + "\t" + (fim - inic) / 1000.0 + "\t" + comp);
            bw.close();
        } catch (IOException io) {
            io.printStackTrace();
            resp = false;
        }
        return resp;
    }

    // Função para ler entre espaços dentro de uma frase
    // No maximo dois espaços
    public static String lerEntreSpaces(String frase) {
        String resp = "";
        boolean check = false;
        for (int i = 0; i < frase.length(); i++) {
            if (frase.charAt(i) == ' ') {
                if (check) {
                    i = frase.length();
                } else {
                    check = true;
                }
            } else if (check) {
                resp += frase.charAt(i);
            }
        }
        return resp;
    }

    /**
     * Função que simula a String.replace() e a String.trim()
     * 
     * @param frase  Frase para ser formatada
     * @param antiga Char para ser alterado
     * @param nova   Novo char para ser adicionado
     * @return Frase de expressao formatada e pronta para uso
     */
    public static String myReplace(String frase, char antiga, char nova) {
        String resp = "";
        if (frase != null) {
            for (int i = 0; i < frase.length(); i++) {
                if (frase.charAt(i) == antiga) {
                    resp += nova;
                } else {
                    // trim para remover espaços
                    if (frase.charAt(i) != ' ') {
                        resp += frase.charAt(i);
                    }
                }
            }
        }
        return resp;
    }

    /**
     * Função que simula o indexOf da classe String
     * 
     * @param frase  para procurar o char
     * @param antiga Char para ser localizado
     * @return Frase de expressao formatada e pronta para uso
     */
    public static int myIndexOf(String frase, char letra) {
        int resp = -1;
        if (frase != null) {
            for (int i = 0; i < frase.length(); i++) {
                if (frase.charAt(i) == letra) {
                    resp = i;
                    i = frase.length();
                }
            }
        }
        return resp;
    }

    /**
     * Função que simula o trim da classe String
     * 
     * @param frase para remover os espaços e realizar formatação
     * @return Frase de expressao formatada e pronta para uso
     */
    public static String myTrim(String frase) {
        String resp = "";
        if (frase != null) {
            for (int i = 0; i < frase.length(); i++) {
                if (frase.charAt(i) != ' ') {
                    resp += frase.charAt(i);
                }
            }
        }
        return resp;
    }

    /**
     * Função que simula o Substring da classe String
     * 
     * @param frase para procurar a String interna
     * @param inic  index inicial
     * @param fim   index final
     * @return Frase de expressao formatada e pronta para uso
     */
    public static String mySubstring(String frase, int inic, int fim) {
        String resp = "";
        if (frase != null) {
            if (fim - inic <= frase.length()) {
                for (int i = inic; i < fim; i++) {
                    resp += frase.charAt(i);
                }
            }
        }
        return resp;
    }

    /**
     * Função que simula o Substring da classe String
     * 
     * @param frase para procurar a String interna
     * @param cInic char para localizar o index inicial
     * @param cFim  char para localizar o index final
     * @return Frase de expressao formatada e pronta para uso
     */
    public static String mySubstring(String frase, char cInic, char cFim) {
        String resp = "";
        if (frase != null) {
            int inic = Ferramentas.myIndexOf(frase, cInic);
            int fim = Ferramentas.myIndexOf(frase, cFim);
            if (fim - inic <= frase.length()) {
                for (int i = inic; i < fim + 1; i++) {
                    resp += frase.charAt(i);
                }
            }
        }
        return resp;
    }

    // Função responsavel por gerar o log contendo as informações de execução do
    // código
    // Tais como o tempo, comparações e minha matricula
    public static boolean gerarLog(double inic, double fim, int comp, int mov, String txt) {
        boolean resp = true;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(getMatricula() + "_" + txt + ".txt"));
            bw.write(getMatricula() + "\t" + comp + "\t" + mov + "\t" + (fim - inic) / 1000.0);
            bw.close();
        } catch (IOException io) {
            io.printStackTrace();
            resp = false;
        }
        return resp;
    }

    /**
     * Função que simula a String.contains() para strings
     * 
     * @param frase Frase para ser verificada
     * @param ver   verificação que vai ser usada
     * @return Verdade ou falso de acordo com a verificação
     */
    public static boolean myContains(String frase, String ver) {
        boolean resp = false;
        String aux = "";
        int count = 0;
        if (frase != null) {
            for (int i = 0; i < frase.length(); i++) {
                if (frase.charAt(i) == ver.charAt(0)) {
                    if ((frase.length() - i) >= ver.length()) {
                        for (int j = i; j < frase.length(); j++) {
                            aux += frase.charAt(j);
                            count++;
                            if (count == ver.length()) {
                                j = frase.length();
                                count = 0;
                            }
                        }
                        if (myEquals(aux, ver)) {
                            resp = true;
                            i = frase.length();
                        } else {
                            aux = "";
                            count = 0;
                        }
                    } else {
                        aux = "";
                    }
                }
            }
        }
        return resp;
    }

    // Metodo de comparação de duas Strings e retorna sua igualdade em forma de
    // boolean
    public static boolean myEquals(String str1, String str2) {
        boolean resp = false;
        if (str1 != null && str2 != null) {
            if (str1.length() == str2.length()) {
                resp = true;
                for (int i = 0; i < str1.length(); i++) {
                    if (str1.charAt(i) != str2.charAt(i)) {
                        resp = false;
                    }
                }
            }
        }
        return resp;
    }

    /**
     * Função que simula a String.replace() para strings
     * 
     * @param frase  Frase para ser formatada
     * @param antiga String para ser alterado
     * @param nova   String para ser adicionado
     * @return Frase de expressao formatada e pronta para uso
     */
    public static String myReplace(String str, String str_old, String str_new) {
        String resp = "", auxresp = "";
        boolean eql = false;
        int j = 0;
        if (str != null && str_new != null && str_old != null) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == str_old.charAt(j)) {
                    eql = true;
                    j++;
                    auxresp += str.charAt(i);
                } else {
                    if (eql) {
                        j = 0;
                        if (str.charAt(i) == str_old.charAt(j)) {
                            resp += auxresp;
                            auxresp = "";
                            eql = true;
                            j++;
                            auxresp += str.charAt(i);
                        } else {
                            eql = false;
                            resp += auxresp += str.charAt(i);
                            auxresp = "";
                        }
                    } else {
                        resp += str.charAt(i);
                    }
                }
                if (eql) {
                    if (j == str_old.length()) {
                        resp += str_new;
                        auxresp = "";
                        j = 0;
                        eql = false;
                    }
                }
            }
        }
        return resp;
    }

    /**
     * Função que remove espaços do inicio de frases
     * 
     * @param frase Frase para ser formatada
     * @param end   char de condição final
     * @return Frase de expressao formatada e pronta para uso
     */
    public static String inicioTrim(String line, char end) {
        String resp = "";
        boolean next = false;
        if (line != null) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == end) {
                    next = true;
                }
                if (next)
                    resp += line.charAt(i);
            }
        }
        if (resp.length() == 0)
            return line;
        else
            return resp;
    }

    /**
     * Função que remove tags e puxa tudo que esta entre elas
     * 
     * @param frase Frase para ser formatada sem as tags
     * @return Frase de expressao formatada e pronta para uso com TUDO que está fora
     *         das tags
     */
    public static String removeTags(String line) {
        String resp = "";
        boolean next = false;
        if (line != null) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '>')
                    next = true;
                else if (line.charAt(i) == '<')
                    next = false;
                else if (next)
                    resp += line.charAt(i);
            }
        }
        return resp;
    }

    /**
     * Função que remove tags e puxa tudo que esta entre elas e adiciona separadores
     * para palavras
     * 
     * @param frase Frase para ser formatada sem as tags
     * @param frase Separador para distribuir frases e etc
     * @return Frase de expressao formatada e pronta para uso com TUDO que está fora
     *         das tags
     */
    public static String removeTags(String line, char separador) {
        String resp = "";
        int count = 0;
        boolean next = false;
        if (line != null) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '>')
                    next = true;
                else if (line.charAt(i) == '<') {
                    next = false;
                    if (count != 0)
                        resp += separador;
                    count = 0;
                } else if (next) {
                    count++;
                    resp += line.charAt(i);
                }
            }
        }
        return resp;
    }

    // Essa função retorna uma data em String convertida para o objeto de Date do
    // Java
    public static Date getData(String data) {
        Date date = null;
        if (data != null) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                date = format.parse(data);
            } catch (ParseException pe) {
                MyIO.println(pe.getMessage());
                return null;
            }
        }
        return date;
    }

    // funcao que remove letras e gera um horario em formato de minutos pronto para
    // uso
    // realizando o calculo automatico das horas se possível (Horas * 60 = horas em
    // minutos)
    public static int getMinutos(String linha) {
        String hr = "", mn = "";
        int conta = 0;
        boolean chk = false;
        if (linha != null) {
            for (int i = 0; i < linha.length(); i++) {
                if (chk) {
                    if (linha.charAt(i) != ' ') {
                        mn += linha.charAt(i);
                    }
                } else {
                    if (linha.charAt(i) != ' ') {
                        hr += linha.charAt(i);
                    } else {
                        chk = true;
                    }
                }
            }
            if (mn.length() > 0) {
                conta = Integer.parseInt(mn);
            } else {
                conta = 0;
            }
            if (hr.length() > 0) {
                conta += Integer.parseInt(hr) * 60;
            }
        }
        return conta;
    }

    // Metodo responsavel por formatar uma nova entrada de data
    public static String formatDate(Date dt) {
        String resp = "";
        try {
            SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy");
            resp = sfd.format(dt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

}

class Filme {
    private String nome, titulo, genero, idioma, situacao;
    private float orcamento;
    private int duracao;
    private Date lancamento;
    private ArrayList<String> listChaves = new ArrayList<>();

    public int getDuracao() {
        return duracao;
    }

    public String getIdioma() {
        return idioma;
    }

    public String getGenero() {
        return genero;
    }

    public Date getLancamento() {
        return lancamento;
    }

    public String getNome() {
        return nome;
    }

    public float getOrcamento() {
        return orcamento;
    }

    public String getSituacao() {
        return situacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setLancamento(Date lancamento) {
        this.lancamento = lancamento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setOrcamento(float orcamento) {
        this.orcamento = orcamento;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<String> getListChaves() {
        return listChaves;
    }

    public void setListChaves(ArrayList<String> listChaves) {
        this.listChaves.addAll(listChaves);
    }

    public void addChave(String palavra) {
        listChaves.add(palavra);
    }

    public String getChave(int index) {
        return listChaves.get(index);
    }

    public Filme(String entrada) {
        ler(entrada);
    }

    public Filme() {
    }

    // Função de clonagem de objeto para evitar erros de acesso de memoria e perda
    // de dados
    public Filme clonar() {
        Filme dolly = new Filme();
        dolly.setDuracao(getDuracao());
        dolly.setGenero(getGenero());
        dolly.setIdioma(getIdioma());
        dolly.setLancamento(getLancamento());
        dolly.setNome(getNome());
        dolly.setSituacao(getSituacao());
        dolly.setOrcamento(getOrcamento());
        dolly.setTitulo(getTitulo());
        dolly.setListChaves(getListChaves());
        return dolly;
    }

    // Método que vai iniciar o objeto de Filme e realizar a pesquisa entre as
    // linhas
    // do arquivo atrás dos dados necessários para a extração. Esse método realiza
    // vários
    // whiles seguidos procurando por informações especificas dentro das linhas
    private void ler(String entrada) {
        try {
            String line = "";
            BufferedReader buff = new BufferedReader(new FileReader(getFile(entrada)));// Arquivo HTML
            line = buff.readLine();
            while (!Ferramentas.myContains(line, "og:title")) {
                line = buff.readLine();
            }
            setNome(tratarLinha(line, 9));
            line = buff.readLine();
            while (!Ferramentas.myContains(line, "class=\"release\"")) {
                line = buff.readLine();
            }
            line = buff.readLine();
            setLancamento(Ferramentas.getData(tratarLinha(line, 1)));
            line = buff.readLine();
            while (!Ferramentas.myContains(line, "class=\"genres\"")) {
                line = buff.readLine();
            }
            buff.readLine();
            line = buff.readLine();
            setGenero(tratarLinha(line, 2));
            line = buff.readLine();
            while (!Ferramentas.myContains(line, "class=\"runtime\"")) {
                line = buff.readLine();
            }
            buff.readLine();
            line = buff.readLine();
            setDuracao(Integer.parseInt(tratarLinha(line, 3)));
            line = buff.readLine();
            boolean chk = false;
            while (!Ferramentas.myContains(line, "Título original")) {
                line = buff.readLine();
                if (Ferramentas.myContains(line, "<bdi>Situação</bdi>")) {
                    setTitulo(getNome());
                    chk = true;
                    break;
                }
            }
            if (chk == false) {
                setTitulo(tratarLinha(line, 4));
                line = buff.readLine();
            }
            while (!Ferramentas.myContains(line, "Situação")) {
                line = buff.readLine();
            }
            setSituacao(tratarLinha(line, 5));
            line = buff.readLine();
            while (!Ferramentas.myContains(line, "Idioma original")) {
                line = buff.readLine();
            }
            setIdioma(tratarLinha(line, 6));
            line = buff.readLine();
            while (!Ferramentas.myContains(line, "Orçamento")) {
                line = buff.readLine();
            }
            setOrcamento(Float.parseFloat(tratarLinha(line, 7)));
            line = buff.readLine();
            while (!Ferramentas.myContains(line, "Palavras-chave")) {
                line = buff.readLine();
            }
            line = buff.readLine();
            line = buff.readLine();
            if (Ferramentas.myContains(line, "Nenhuma palavra-chave foi adicionada")) {
                line = "</ul>";
            }
            while (!Ferramentas.myContains(line, "</ul>")) {
                if (Ferramentas.myContains(line, "<li>")) {
                    addChave(tratarLinha(line, 8));
                }
                line = buff.readLine();
            }
            buff.close();
        } catch (IOException io) {
            System.err.println(io.getMessage());
            io.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erro desconhecido dentro da função de leitura: " + e.getMessage());
            e.printStackTrace();
        }

    }

    // Método para imprimir as informações do objeto
    public void imprimir() {
        MyIO.print(getNome() + " ");
        MyIO.print(getTitulo() + " ");
        MyIO.print(Ferramentas.formatDate(getLancamento()) + " ");
        MyIO.print(getDuracao() + " ");
        MyIO.print(getGenero() + " ");
        MyIO.print(getIdioma() + " ");
        MyIO.print(getSituacao() + " ");
        MyIO.print(getOrcamento() + " ");
        MyIO.print("[");
        ArrayList<String> chaves = getListChaves();
        for (int i = 0; i < chaves.size(); i++) {
            if (i < chaves.size() - 1) {
                MyIO.print(chaves.get(i) + ", ");
            } else {
                MyIO.print(chaves.get(i));
            }
        }
        MyIO.print("]");
        MyIO.println("");
    }

    // Metódo responsável por tratar uma linha e remover TAGS do html
    // Essa linha filtra os dados e pega somente o bruto necessário
    // Usando funções criadas dentro da classe de Ferramentas
    private String tratarLinha(String linha, int op) {
        String resp = "";
        linha = Ferramentas.inicioTrim(linha, '<');
        // Para cada entrada tem um tratamento de linha diferenciado
        switch (op) {
            case 1:
                String locale = Ferramentas.mySubstring(linha, Ferramentas.myIndexOf(linha, '('),
                        Ferramentas.myIndexOf(linha, ')') + 1); // remoção do (BR) (US) etc
                linha = Ferramentas.myReplace(linha, " " + locale, " ");
                resp = Ferramentas.myTrim(linha);
                break;
            case 2:
                resp = Ferramentas.removeTags(linha, ',');
                resp = Ferramentas.myReplace(resp, ",,&nbsp;", "");
                break;
            case 3:
                linha = Ferramentas.myReplace(linha, 'm', ' ');
                if (Ferramentas.myContains(linha, "h")) {
                    linha = Ferramentas.myReplace(linha, 'h', ' ');
                } else {
                    resp = " " + linha;
                    linha = resp;
                    resp = "";
                }
                resp = "" + Ferramentas.getMinutos(linha);
                break;
            case 4:
                resp = Ferramentas.myReplace(linha, "<p class=\"wrap\"><strong>Título original</strong> ", "");
                resp = Ferramentas.myReplace(resp, "</p>", "");
                break;
            case 5:
                resp = Ferramentas.myReplace(linha, "<strong><bdi>Situação</bdi></strong> ", "");
                break;
            case 6:
                resp = Ferramentas.myReplace(linha, "<p><strong><bdi>Idioma original</bdi></strong> ", "");
                resp = Ferramentas.myReplace(resp, "</p>", "");
                break;
            case 7:
                resp = Ferramentas.myReplace(linha, "<p><strong><bdi>Orçamento</bdi></strong> ", "");
                resp = Ferramentas.myReplace(resp, "</p>", "");
                if (Ferramentas.myContains(linha, "-")) {
                    resp = "0.0";
                } else {
                    resp = Ferramentas.myReplace(resp, ",", "");
                    resp = Ferramentas.myReplace(resp, "$", "");
                }
                break;
            case 8:
                resp = Ferramentas.removeTags(linha);
                break;
            case 9:
                resp = Ferramentas.myReplace(linha, "<meta property=\"og:title\" content=\"", "");
                resp = Ferramentas.myReplace(resp, "\">", "");
                break;
            default:
                break;
        }
        return resp;
    }

    // Função que retorna um arquivo de html para poder ser realizado as consultas e
    // extração de dados
    private File getFile(String name) throws IOException {
        File file;
        // file = new File("/tmp/filmes/" + name);
        file = new File("../tmp/filmes/" + name);
        if (!file.isFile()) {
            throw new IOException("O arquivo não foi encontrado na pasta tmp arquivo:" + name);
        } else {
            return file;
        }
    }

}

class No {
    public No esq, dir;
    public No2 no2;
    public char elemento;

    public No() {
        this.esq = null;
        this.dir = null;
        this.no2 = null;
        this.elemento = 0;
    }

    public No(char x) {
        this.esq = null;
        this.dir = null;
        this.no2 = null;
        this.elemento = x;
    }

    public char getElemento() {
        return elemento;
    }

    public void setElemento(char elemento) {
        this.elemento = elemento;
    }

}

class No2 {
    public No2 esq, dir;
    public Filme elemento;
    public String chave;

    public No2() {
        this.esq = null;
        this.dir = null;
        this.elemento = null;
        this.chave = "";
    }

    public No2(Filme x) {
        this.esq = null;
        this.dir = null;
        this.elemento = x;
        this.chave = x.getTitulo();
    }

    public Filme getElemento() {
        return elemento;
    }

    public void setElemento(Filme elemento) {
        this.elemento = elemento;
    }

}

class ArvoreCaracteres {
    No raiz;

    public ArvoreCaracteres() {
        raiz = null;
    }

    public void inserir(char x) throws Exception {
        raiz = inserir(x, raiz);
    }

    private No inserir(char x, No no) throws Exception {
        comp++;
        if (no == null) {
            no = new No(x);
        } else if (x < no.elemento) {
            comp++;
            no.esq = inserir(x, no.esq);
        } else if (x > no.elemento) {
            comp++;
            no.dir = inserir(x, no.dir);
        } else {
            throw new Exception("Erro o elemento informado já foi adicionado na arvore! -> " + x);
        }
        return no;
    }

    public boolean inserir(Filme x) throws Exception {
        boolean resp = false;
        comp++;
        if (x.getTitulo().charAt(0) == raiz.elemento) {
            resp = true;
            raiz.no2 = inserir(x, raiz.no2);
        } else if (x.getTitulo().charAt(0) < raiz.elemento) {
            comp++;
            raiz.esq = inserir(x, raiz.esq);
        } else if (x.getTitulo().charAt(0) > raiz.elemento) {
            comp++;
            raiz.dir = inserir(x, raiz.dir);
        }
        return resp;
    }

    private No inserir(Filme x, No no) throws Exception {
        if (no == null) {
            comp++;
            throw new Exception("Erro o elemento é nulo");
        } else if (x.getTitulo().charAt(0) < no.elemento) {
            comp++;
            no.esq = inserir(x, no.esq);
        } else if (x.getTitulo().charAt(0) > no.elemento) {
            comp++;
            no.dir = inserir(x, no.dir);
        } else if (x.getTitulo().charAt(0) == no.elemento) {
            comp++;
            no.no2 = inserir(x, no.no2);
        }
        return no;
    }

    private No2 inserir(Filme x, No2 no) throws Exception {
        comp++;
        if (no == null) {
            no = new No2(x);
        } else if (Ferramentas.comparadorStr(x.getTitulo(), no.chave) < 0) {
            comp++;
            no.esq = inserir(x, no.esq);
        } else if (Ferramentas.comparadorStr(x.getTitulo(), no.chave) > 0) {
            comp++;
            no.dir = inserir(x, no.dir);
        } else {
            throw new Exception("Erro o elemento informado já foi adicionado na arvore! -> " + x);
        }
        return no;
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

    public void remover(char x) throws Exception {
        raiz = remover(x, raiz);
    }

    private No remover(char x, No no) throws Exception {
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
            no.esq = getMaiorEsq(no, no.esq);
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

    public void caminharCentral() {
        System.out.print("[\n ");
        caminharCentral(raiz);
        System.out.println("]");
    }

    private void caminharCentral(No i) {
        if (i != null) {
            caminharCentral(i.esq);
            System.out.print(i.elemento + " \n");
            caminharCentral(i.no2);
            caminharCentral(i.dir);
        }
    }

    private void caminharCentral(No2 i) {
        if (i != null) {
            caminharCentral(i.esq);
            System.out.print(i.chave + " \n");
            caminharCentral(i.dir);
        }
    }

    public void caminharPre(String x) {
        comp++;
        MyIO.println("=> " + x);
        MyIO.print("raiz ");
        if (caminharPre(x, raiz)) {
            MyIO.print(" SIM\n");
        } else {
            MyIO.print(" NAO\n");
        }
    }

    private boolean caminharPre(String x, No i) {
        boolean resp = false;
        comp++;
        if (i != null) {
            comp++;
            if (pesquisarDbg2(x, i.no2)) {
                return true;
            } else {
                MyIO.print(" ESQ ");
                comp++;
                if (resp != true) {
                    resp = caminharPre(x, i.esq);
                    comp++;
                    if (resp == false) {
                        MyIO.print(" DIR ");
                        resp = caminharPre(x, i.dir);
                    }
                }
            }
        }
        return resp;
    }

    public boolean pesquisarDbg2(String x, No2 i) {
        boolean resp = false;
        comp++;
        if (i == null) {
            resp = false;
        } else if (x.equals(i.chave)) {
            comp++;
            resp = true;
        } else if (Ferramentas.comparadorStr(x, i.chave) < 0) {
            MyIO.print("esq ");
            comp++;
            resp = pesquisarDbg2(x, i.esq);
        } else if (Ferramentas.comparadorStr(x, i.chave) > 0) {
            MyIO.print("dir ");
            comp++;
            resp = pesquisarDbg2(x, i.dir);
        }
        return resp;
    }

    public void caminharPos() {
        System.out.print("[ ");
        caminharPos(raiz);
        System.out.println("]");
    }

    private void caminharPos(No i) {
        if (i != null) {
            caminharPos(i.esq); // Elementos da esquerda.
            caminharPos(i.dir); // Elementos da direita.
            System.out.print(i.elemento + " "); // Conteudo do no.
        }
    }

    /**
     * Retorna o timestamp atual
     * 
     * @return timestamp atual
     */
    public long now() {
        return new Date().getTime();
    }

    private int comp;

    public void setComp(int comp) {
        this.comp = comp;
    }

    public int getComparacoes() {
        return this.comp;
    }

}

public class Tp4Q02 {

    private static boolean isFim(String entrada) {
        return entrada.length() == 3 && Ferramentas.myEquals(entrada, "FIM");
    }

    public static void main(String[] args) {
        try {
            ArrayList<String> entradas = new ArrayList<>();
            ArrayList<String> pesquisas = new ArrayList<>();
            String verificacoes[];
            ArvoreCaracteres arvore;
            int n = 0, comp = 0;
            double fim, inic;
            String entrada = "", comando;
            MyIO.setCharset("UTF-8");
            do {
                entrada = MyIO.readLine();
                if (!isFim(entrada)) {
                    entradas.add(entrada);
                }
            } while (!isFim(entrada));
            n = MyIO.readInt();
            verificacoes = new String[n];
            // salvando comandos de verificação para serem executados
            for (int i = 0; i < n; i++) {
                verificacoes[i] = entrada = MyIO.readLine();
            }

            do {
                entrada = MyIO.readLine();
                if (!isFim(entrada)) {
                    pesquisas.add(entrada);
                }
            } while (!isFim(entrada));

            // Iniciando arvore 1 que contempla as letras
            arvore = new ArvoreCaracteres();
            inic = arvore.now();
            String chars = "D, R, Z, X, V, B, F, P, U, I, G, E, J, L, H, T, A, W, S, O, M, N, K, C, Y, Q";
            chars = chars.replace(", ", "");
            for (int i = 0; i < chars.length(); i++) {
                arvore.inserir(chars.charAt(i));
            }

            for (String ent : entradas) {
                Filme filme = new Filme(ent);
                arvore.inserir(filme);
            }
            for (int i = 0; i < n; i++) {
                comando = verificacoes[i];
                if (Ferramentas.myContains(comando, "I ")) {
                    arvore.inserir(new Filme(Ferramentas.mySubstring(comando, 2, comando.length())));
                }
            }
            for (String find : pesquisas) {
                arvore.caminharPre(find);
            }
            comp += arvore.getComparacoes();
            fim = arvore.now();
            Ferramentas.gerarLog(inic, fim, comp);
        } catch (Exception e) {
            System.out.println("Um erro ocorreu durante a execução do código\n" +
                    "Erro reportado no main --> " + e.getMessage() + " -- " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}