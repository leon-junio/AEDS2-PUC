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

    // Função para comparar Strings e retornar um inteiro
    // retorna > 1 se a str1 for maior que a str2
    // retorna < 1 se a str1 for menor que a str2
    // retorna == 0 se a str1 for igual a str2
    public static int comparadorStr(Filme str, Filme str2) {
        return str.toString().compareTo(str2.toString());
    }

    // Função responsavel por gerar o log contendo as informações de execução do
    // código
    // Tais como o tempo, comparações e minha matricula
    public static boolean gerarLog(double inic, double fim, int comp) {
        boolean resp = true;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(getMatricula() + "_alvinegra.txt"));
            bw.write(getMatricula() + "\t" + (fim - inic) / 1000.0 + "\t" + comp);
            bw.close();
        } catch (IOException io) {
            io.printStackTrace();
            resp = false;
        }
        return resp;
    }

    // Função para ler entre espaços dentro de uma frase
    // NoAn maximo dois espaços
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
        file = new File("/tmp/filmes/" + name);
        // file = new File("../tmp/filmes/" + name);
        if (!file.isFile()) {
            throw new IOException("O arquivo não foi encontrado na pasta tmp arquivo:" + name);
        } else {
            return file;
        }
    }

    @Override
    public String toString() {
        return getTitulo();
    }

}

class NoAn {
    public NoAn esq, dir;
    public Filme elemento;
    public boolean cor;

    public NoAn(Filme x) {
        this(x, null, null, false);
    }

    public NoAn(Filme x, boolean cor) {
        this(x, null, null, cor);
    }

    public NoAn(Filme x, NoAn esq, NoAn dir, boolean cor) {
        this.esq = esq;
        this.dir = dir;
        this.elemento = x;
        this.cor = cor;
    }

    public Filme getElemento() {
        return elemento;
    }

    public void setElemento(Filme elemento) {
        this.elemento = elemento;
    }

}

class ArvoreColorida {
    NoAn raiz;

    public ArvoreColorida() {
        raiz = null;
    }

    public boolean isNoTipoQuatro(NoAn no) {
        return (no.esq != null && no.dir != null && no.dir.cor == true && no.esq.cor == true);
    }

    public void inserir(Filme x) throws Exception {
        if (raiz == null) {
            raiz = new NoAn(x);
        } else if (raiz.esq == null && raiz.dir == null) {
            if (Ferramentas.comparadorStr(x.getTitulo(), raiz.elemento.getTitulo()) < 0) {
                raiz.esq = new NoAn(x);
            } else {
                raiz.dir = new NoAn(x);
            }
        } else if (raiz.esq == null) {
            if (Ferramentas.comparadorStr(x, raiz.elemento) < 0) {
                raiz.esq = new NoAn(x);
            } else if (Ferramentas.comparadorStr(x, raiz.dir.elemento) < 0) {
                raiz.esq = new NoAn(raiz.elemento);
                raiz.elemento = x;
            } else {
                raiz.esq = new NoAn(raiz.elemento);
                raiz.elemento = raiz.dir.elemento;
                raiz.dir.elemento = x;
            }
            raiz.esq.cor = raiz.dir.cor = false;
        } else if (raiz.dir == null) {
            if (Ferramentas.comparadorStr(x, raiz.elemento) > 0) {
                raiz.dir = new NoAn(x);
            } else if (Ferramentas.comparadorStr(x, raiz.esq.elemento) > 0) {
                raiz.dir = new NoAn(raiz.elemento);
                raiz.elemento = x;
            } else {
                raiz.dir = new NoAn(raiz.elemento);
                raiz.elemento = raiz.esq.elemento;
                raiz.esq.elemento = x;
            }
            raiz.esq.cor = raiz.dir.cor = false;
        } else {
            inserir(x, null, null, null, raiz);
        }
        raiz.cor = false;
    }

    public void balancear(NoAn bisavo, NoAn avo, NoAn pai, NoAn i) throws Exception {
        if (pai.cor == true) {
            if (Ferramentas.comparadorStr(pai.elemento, avo.elemento) > 0) {
                if (Ferramentas.comparadorStr(i.elemento, pai.elemento) > 0) {
                    avo = rotacionarEsq(avo);
                } else {
                    avo = rotacionarDirEsq(avo);
                }
            } else {
                if (Ferramentas.comparadorStr(i.elemento, pai.elemento) < 0) {
                    avo = rotacionarDir(avo);
                } else {
                    avo = rotacionarEsqDir(avo);
                }
            }
        }
        if (bisavo == null) {
            raiz = avo;
        } else if (Ferramentas.comparadorStr(avo.elemento, bisavo.elemento) < 0) {
            bisavo.esq = avo;
        } else {
            bisavo.dir = avo;
        }
        avo.cor = false;
        avo.esq.cor = avo.dir.cor = true;
    }

    private void inserir(Filme x, NoAn bisavo, NoAn avo, NoAn pai, NoAn i) throws Exception {
        if (i == null) {
            if (Ferramentas.comparadorStr(x, pai.elemento) < 0) {
                i = pai.esq = new NoAn(x, true);
            } else {
                i = pai.dir = new NoAn(x, true);
            }
            if (pai.cor) {
                balancear(bisavo, avo, pai, i);
            }
        } else {
            if (isNoTipoQuatro(i)) {
                i = fragmentarNoTipoQuatro(i);
                if (i == raiz) {
                    i.cor = false;
                } else if (pai.cor) {
                    balancear(bisavo, avo, pai, i);
                }
            }
            if (Ferramentas.comparadorStr(x, i.elemento) < 0) {
                inserir(x, avo, pai, i, i.esq);
            } else if (Ferramentas.comparadorStr(x, i.elemento) > 0) {
                inserir(x, avo, pai, i, i.dir);
            } else {
                throw new Exception("Erro interno ao inserir elemento (elemento repetido)");
            }
        }
    }

    public boolean pesquisar(Filme x) {
        boolean resp = false;
        if (x.getTitulo().equals(raiz.elemento.getTitulo())) {
            resp = true;
        } else if (Ferramentas.comparadorStr(x.getTitulo(), raiz.elemento.getTitulo()) < 0) {
            resp = pesquisar(x, raiz.esq);
        } else if (Ferramentas.comparadorStr(x.getTitulo(), raiz.elemento.getTitulo()) > 0) {
            resp = pesquisar(x, raiz.dir);
        }
        return resp;
    }

    private boolean pesquisar(Filme x, NoAn no) {
        boolean resp = false;
        if (no == null) {
            resp = false;
        } else if (Ferramentas.comparadorStr(x.getTitulo(), no.elemento.getTitulo()) < 0) {
            resp = pesquisar(x, no.esq);
        } else if (Ferramentas.comparadorStr(x.getTitulo(), no.elemento.getTitulo()) > 0) {
            resp = pesquisar(x, no.dir);
        } else if (x.getTitulo().equals(no.elemento.getTitulo())) {
            resp = true;
        }
        return resp;
    }

    public Filme pesquisar(String x) {
        Filme resp = null;
        if (x.equals(raiz.elemento.getTitulo())) {
            resp = raiz.elemento;
        } else if (Ferramentas.comparadorStr(x, raiz.elemento.getTitulo()) < 0) {
            resp = pesquisar(x, raiz.esq);
        } else if (Ferramentas.comparadorStr(x, raiz.elemento.getTitulo()) > 0) {
            resp = pesquisar(x, raiz.dir);
        }
        return resp;
    }

    public Filme pesquisar(String x, NoAn i) {
        Filme resp = null;
        if (i == null) {
            resp = null;
        } else if (Ferramentas.comparadorStr(x, i.elemento.getTitulo()) < 0) {
            resp = pesquisar(x, i.esq);
        } else if (Ferramentas.comparadorStr(x, i.elemento.getTitulo()) > 0) {
            resp = pesquisar(x, i.dir);
        } else if (x.equals(i.elemento.getTitulo())) {
            resp = i.elemento;
        }
        return resp;
    }

    public boolean pesquisarDbg(String x) {
        boolean resp = false;
        MyIO.println(x);
        MyIO.print("raiz ");
        comp = 0;
        resp = pesquisarDbg(x, raiz);
        return resp;
    }

    public boolean pesquisarDbg(String x, NoAn i) {
        boolean resp = false;
        comp++;
        if (i == null) {
            resp = false;
        } else if (x.equals(i.elemento.getTitulo())) {
            resp = true;
        } else if (Ferramentas.comparadorStr(x, i.elemento.getTitulo()) < 0) {
            MyIO.print("esq ");
            comp++;
            resp = pesquisarDbg(x, i.esq);
        } else if (Ferramentas.comparadorStr(x, i.elemento.getTitulo()) > 0) {
            MyIO.print("dir ");
            comp++;
            resp = pesquisarDbg(x, i.dir);
        }
        return resp;
    }

    public int altura() {
        return altura(raiz, 0);
    }

    private int altura(NoAn no, int tam) {
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

    public void imprimirNivel(int altura) throws Exception {
        if (raiz == null) {
            throw new Exception("Erro a arvore se encontra vazia");
        } else if (altura == 0) {
            System.out.println(raiz.elemento.getTitulo());
        } else {
            imprimirNivel(raiz, 0, altura);
            System.out.println("");
        }
    }

    private void imprimirNivel(NoAn no, int num, int nivel) throws Exception {
        if (no == null) {
            num--;
        } else if (num == nivel) {
            System.out.print(no.elemento.getTitulo() + " ");
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
        System.out.print("[ ");
        caminharCentral(raiz);
        System.out.println("]");
    }

    private void caminharCentral(NoAn i) {
        if (i != null) {
            caminharCentral(i.esq); // Elementos da esquerda.
            System.out.print(i.elemento.getTitulo() + " "); // Conteudo do no.
            caminharCentral(i.dir); // Elementos da direita.
        }
    }

    public void caminharPre() {
        System.out.print("[ ");
        caminharPre(raiz);
        System.out.println("]");
    }

    private void caminharPre(NoAn i) {
        if (i != null) {
            System.out.print(i.elemento.getTitulo() + " "); // Conteudo do no.
            caminharPre(i.esq); // Elementos da esquerda.
            caminharPre(i.dir); // Elementos da direita.
        }
    }

    public void caminharPos() {
        System.out.print("[ ");
        caminharPos(raiz);
        System.out.println("]");
    }

    private void caminharPos(NoAn i) {
        if (i != null) {
            caminharPos(i.esq); // Elementos da esquerda.
            caminharPos(i.dir); // Elementos da direita.
            System.out.print(i.elemento.getTitulo() + " "); // Conteudo do no.
        }
    }

    public NoAn fragmentarNoTipoQuatro(NoAn i) throws Exception {
        if (isNoTipoQuatro(i)) {
            i.cor = true;
            i.dir.cor = false;
            i.esq.cor = false;
        } else {
            throw new Exception("Erro ao tentar fragmentar um nó que não é 4 nós");
        }
        return i;
    }

    public NoAn rotacionarEsq(NoAn no) {
        NoAn nodir = no.dir;
        NoAn noDirEsq = nodir.esq;
        nodir.esq = no;
        no.dir = noDirEsq;
        return nodir;
    }

    public NoAn rotacionarDir(NoAn no) {
        NoAn noesq = no.esq;
        NoAn noEsqDir = noesq.dir;
        noesq.dir = no;
        no.esq = noEsqDir;
        return noesq;
    }

    public NoAn rotacionarDirEsq(NoAn no) {
        no.dir = rotacionarDir(no.dir);
        return rotacionarEsq(no);
    }

    public NoAn rotacionarEsqDir(NoAn no) {
        no.esq = rotacionarEsq(no.esq);
        return rotacionarDir(no);
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

public class Tp4Q04 {

    private static boolean isFim(String entrada) {
        return entrada.length() == 3 && Ferramentas.myEquals(entrada, "FIM");
    }

    public static void main(String[] args) {
        try {
            ArrayList<String> entradas = new ArrayList<>();
            ArrayList<String> pesquisas = new ArrayList<>();
            ArvoreColorida arvore;
            int comp = 0;
            double fim, inic;
            String entrada = "";
            MyIO.setCharset("UTF-8");
            do {
                entrada = MyIO.readLine();
                if (!isFim(entrada)) {
                    entradas.add(entrada);
                }
            } while (!isFim(entrada));
            do {
                entrada = MyIO.readLine();
                if (!isFim(entrada)) {
                    pesquisas.add(entrada);
                }
            } while (!isFim(entrada));

            // criação dos objetos de filme/leitura/impressao
            arvore = new ArvoreColorida();
            inic = arvore.now();
            for (String ent : entradas) {
                Filme filme = new Filme(ent);
                arvore.inserir(filme);
            }
            for (String find : pesquisas) {
                if (arvore.pesquisarDbg(find)) {
                    MyIO.print("SIM\n");
                } else {
                    MyIO.print("NAO\n");
                }
                comp += arvore.getComparacoes();
            }
            fim = arvore.now();
            Ferramentas.gerarLog(inic, fim, comp);
        } catch (Exception e) {
            System.out.println("Um erro ocorreu durante a execução do código\n" +
                    "Erro reportado no main --> " + e.getMessage() + " -- " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}