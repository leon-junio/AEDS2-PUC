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
        //file = new File("/tmp/filmes/" + name);
        file = new File("../tmp/filmes/" + name);
        if (!file.isFile()) {
            throw new IOException("O arquivo não foi encontrado na pasta tmp arquivo:" + name);
        } else {
            return file;
        }
    }

}

class Lista {

    private CelulaDupla primeiro, ultimo;

    public Lista() {
        primeiro = new CelulaDupla();
        ultimo = primeiro;
    }

    // Inserir dentro da lista de filmes na última posição
    public void inserirFim(Filme obj) {
        CelulaDupla tmp = new CelulaDupla(obj);
        ultimo.prox = tmp;
        ultimo = ultimo.prox;
        tmp = null;
    }

    /**
     * Insere um elemento na primeira posicao da lista e move os demais
     * elementos para o fim da lista.
     * 
     * @param fm Filme elemento a ser inserido.
     */
    public void inserirInicio(Filme fm) {
        CelulaDupla tmp = new CelulaDupla(fm);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        }
        tmp = null;
    }

    /**
     * Insere um elemento em uma posicao especifica e move os demais
     * elementos para o fim da lista.
     * 
     * @param fm  Filme elemento a ser inserido.
     * @param pos Posicao de insercao.
     */
    public void inserir(Filme fm, int pos) throws Exception {
        int tam = tamanho();
        if (pos == 0) {
            inserirInicio(fm);
        } else if (pos == tam - 1) {
            inserirFim(fm);
        } else if (pos < 0 || pos >= tam) {
            throw new Exception("Posição de inserção invalida - pos:" + pos + " size:" + tam);
        } else {
            CelulaDupla i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox)
                ;
            CelulaDupla tmp = i.prox;
            i.prox = new CelulaDupla(fm);
            i.prox.prox = tmp;
            tmp = i = null;
        }
    }

    /**
     * Remove um elemento da primeira posicao da lista e movimenta
     * os demais elementos para o inicio da mesma.
     * 
     * @return resp int elemento a ser removido.
     */
    public Filme removerInicio() throws Exception {
        Filme resp = null;
        if (primeiro == ultimo) {
            throw new Exception("A lista esta vazia!");
        }
        CelulaDupla tmp = primeiro;
        resp = tmp.prox.elemento;
        primeiro = tmp.prox;
        tmp.prox = null;
        tmp = null;
        return resp;
    }

    /**
     * Remove um elemento da ultima posicao da lista.
     * 
     * @return resp int elemento a ser removido.
     */
    public Filme removerFim() throws Exception {
        Filme resp = null;
        if (primeiro == ultimo) {
            throw new Exception("A lista esta vazia!");
        }
        CelulaDupla i;
        for (i = primeiro; i.prox != ultimo; i = i.prox)
            ;
        resp = ultimo.elemento;
        ultimo = i;
        ultimo.prox = null;
        i = null;
        return resp;
    }

    /**
     * Remove um elemento de uma posicao especifica da lista e
     * movimenta os demais elementos para o inicio da mesma.
     * 
     * @param pos Posicao de remocao.
     * @return resp int elemento a ser removido.
     */
    public Filme remover(int pos) throws Exception {
        Filme resp = null;
        int tamanho = tamanho();
        if (primeiro == ultimo) {
            throw new Exception("A lista esta vazia!");
        } else if (pos < 0 || pos >= tamanho) {
            throw new Exception("Posição de remoção inválida nesse contexto! pos:" + pos + " size:" + tamanho);
        } else if (pos == 0) {
            removerInicio();
        } else if (pos == tamanho() - 1) {
            removerFim();
        } else {
            CelulaDupla i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox)
                ;
            CelulaDupla tmp = i.prox;
            resp = tmp.elemento;
            i.prox = tmp.prox;
            tmp.prox = null;
            i = tmp = null;
        }
        return resp;
    }

    // retornar o tamanho da lista dinamica no momento
    public int tamanho() {
        int resp = 0;
        for (CelulaDupla tmp = primeiro; tmp != ultimo; tmp = tmp.prox) {
            resp++;
        }
        return resp;
    }

    // Localizar filme dentro da lista baseado na sua posição
    public Filme localizar(int pos) throws Exception {
        Filme resp = null;
        int tam = tamanho();
        if (primeiro == ultimo) {
            throw new Exception("A lista se encontra vazia!");
        } else if (pos == 0) {
            resp = primeiro.prox.elemento;
        } else if (pos == tam - 1) {
            resp = ultimo.elemento;
        } else if (pos < 0 || pos >= tam) {
            throw new Exception("Posição para busca inválida na lista!");
        } else {
            CelulaDupla i = primeiro;
            for (int j = 0; j < pos; i = i.prox, j++)
                ;
            resp = i.prox.elemento;
        }
        return resp;
    }

    // Localizar objeto de filme dentro da lista
    public boolean localizar(Filme obj) throws Exception {
        boolean resp = false;
        if (primeiro == ultimo) {
            throw new Exception("A lista se encontra vazia!");
        } else {
            for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
                if (i.elemento.getTitulo().equals(obj.getTitulo())) {
                    resp = true;
                    i = ultimo;
                }
            }
        }
        return resp;
    }

    // Mostrar o elemento de todas as células
    public void mostrar() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Lista se encontra vazia!");
        } else {
            for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
                MyIO.println(i.elemento.toString());
            }
        }
    }

    // Chama a recursão para imprimir o inverso
    public void imprimirReverso() {
        mostrarRec(primeiro.prox);
    }

    // Imprimir informações de todos os filmes cadastrados
    public void imprimir() {
        int j = 0;
        for (CelulaDupla tmp = primeiro.prox; tmp != null; tmp = tmp.prox, j++) {
            MyIO.print("[" + j + "] ");
            tmp.elemento.imprimir();
        }
        MyIO.println();
    }

    // Função para mostrar os elementos de tras para frente da lista --> começar por
    // primeiro.prox
    public void mostrarRec(CelulaDupla i) {
        if (i != null) {
            mostrarRec(i.prox);
            i.elemento.imprimir();
        }
    }

    // Uma versçao do remover do inicio que não remove o nó cabeça da lista e sim o
    // elemento desejado
    public Filme removerInicioDiff() throws Exception {
        Filme resp = null;
        if (primeiro == ultimo) {
            throw new Exception("A lista se encontra vazia no momento!");
        } else {
            CelulaDupla tmp = primeiro.prox;
            resp = tmp.elemento;
            primeiro.prox = primeiro.prox.prox;
            tmp.prox = null;
            tmp = null;
        }
        return resp;
    }

}

class CelulaDupla {

    public CelulaDupla(Filme obj) {
        this.elemento = obj;
    }

    public CelulaDupla() {
        this(null);
    }

    public Filme elemento;
    public CelulaDupla prox, ant;

}

public class Tp3Q13 {

    private static boolean isFim(String entrada) {
        return entrada.length() == 3 && Ferramentas.myEquals(entrada, "FIM");
    }

    public static void main(String[] args) {
        try {
            ArrayList<String> entradas = new ArrayList<>();
            ArrayList<String> removes = new ArrayList<>();
            String verificacoes[];
            Lista listaFilmes;
            int n = 0, pos = 0;
            String entrada = "", comando, aux = "";
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

            // criação dos objetos de filme/leitura/impressao
            listaFilmes = new Lista();
            for (String ent : entradas) {
                Filme filme = new Filme(ent);
                listaFilmes.inserirFim(filme);
            }

            // executando comandos da lista de acordo com a demanda
            for (int i = 0; i < n; i++) {
                comando = verificacoes[i];
                // System.out.println(comando);
                if (Ferramentas.myContains(comando, "II")) {
                    listaFilmes.inserirInicio(new Filme(Ferramentas.mySubstring(comando, 3, comando.length())));
                } else if (Ferramentas.myContains(comando, "IF")) {
                    listaFilmes.inserirFim(new Filme(Ferramentas.mySubstring(comando, 3, comando.length())));
                } else if (Ferramentas.myContains(comando, "I*")) {
                    aux = Ferramentas.lerEntreSpaces(comando);
                    pos = Integer.parseInt(aux);
                    listaFilmes.inserir(new Filme(Ferramentas.mySubstring(comando, aux.length() + 4, comando.length())),
                            pos);
                } else if (Ferramentas.myContains(comando, "RI")) {
                    removes.add(listaFilmes.removerInicio().getNome());
                } else if (Ferramentas.myContains(comando, "RF")) {
                    removes.add(listaFilmes.removerFim().getNome());
                } else if (Ferramentas.myContains(comando, "R*")) {
                    pos = Integer.parseInt(Ferramentas.lerEntreSpaces(comando));
                    removes.add(listaFilmes.remover(pos).getNome());
                }
                /*
                 * System.out.println(
                 * "----------------------------------------------------------------------");
                 * System.out.println("COMANDO: "+comando);
                 * listaFilmes.imprimir();
                 * System.out.println(
                 * "----------------------------------------------------------------------");
                 */
            }
            // Printando resultados das inserções e remoções
            for (String name : removes) {
                MyIO.println("(R) " + name);
            }
            listaFilmes.imprimir();
            /*
             * Filme f = new Filme();
             * f.setTitulo("Gold");
             * System.out.println(listaFilmes.localizar(f));
             * System.out.println(listaFilmes.localizar(3).getNome());
             */
        } catch (Exception e) {
            System.out.println("Um erro ocorreu durante a execução do código\n" +
                    "Erro reportado no main --> " + e.getMessage() + " -- " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

}