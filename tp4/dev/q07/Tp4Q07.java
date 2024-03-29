import java.util.Date;
import java.util.ArrayList;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class Ferramentas {

	// MATRICULA COMO ALUNO DEFINIDA PARA USO INTERNO NO SISTEMA
	private final static String matricula = "1369371";

	public static String getMatricula() {
		return matricula;
	}

	public static String myReplace(String frase, char antiga, char nova) {
		String resp = "";
		if (frase != null) {
			for (int i = 0; i < frase.length(); i++) {
				if (frase.charAt(i) == antiga) {
					resp += nova;
				} else {
					// trim para remover espa?os
					if (frase.charAt(i) != ' ') {
						resp += frase.charAt(i);
					}
				}
			}
		}
		return resp;
	}

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

	public static boolean gerarLog(double inic, double fim, int comp) {
		boolean resp = true;
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(getMatricula() + "_hashIndireta.txt"));
			bw.write(getMatricula() + "\t" + (fim - inic) / 1000.0 + "\t" + comp);
			bw.close();
		} catch (IOException io) {
			io.printStackTrace();
			resp = false;
		}
		return resp;
	}

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
			conta = Integer.parseInt(mn);
			if (hr.length() > 0) {
				conta += Integer.parseInt(hr) * 60;
			}
		}
		return conta;
	}

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
			while (!Ferramentas.myContains(line, "T�tulo original")) {
				line = buff.readLine();
				if (Ferramentas.myContains(line, "<bdi>Situa��o</bdi>")) {
					setTitulo(getNome());
					chk = true;
					break;
				}
			}
			if (chk == false) {
				setTitulo(tratarLinha(line, 4));
				line = buff.readLine();
			}
			while (!Ferramentas.myContains(line, "Situa��o")) {
				line = buff.readLine();
			}
			setSituacao(tratarLinha(line, 5));
			line = buff.readLine();
			while (!Ferramentas.myContains(line, "Idioma original")) {
				line = buff.readLine();
			}
			setIdioma(tratarLinha(line, 6));
			line = buff.readLine();
			while (!Ferramentas.myContains(line, "Or�amento")) {
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
			System.err.println("Erro desconhecido dentro da funcao de leitura: " + e.getMessage());
			e.printStackTrace();
		}

	}

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

	private String tratarLinha(String linha, int op) {
		String resp = "";
		linha = Ferramentas.inicioTrim(linha, '<');
		// Para cada entrada tem um tratamento de linha diferenciado
		switch (op) {
			case 1:
				String locale = Ferramentas.mySubstring(linha, Ferramentas.myIndexOf(linha, '('),
						Ferramentas.myIndexOf(linha, ')') + 1);
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
				resp = Ferramentas.myReplace(linha, "<p class=\"wrap\"><strong>T�tulo original</strong> ", "");
				resp = Ferramentas.myReplace(resp, "</p>", "");
				break;
			case 5:
				resp = Ferramentas.myReplace(linha, "<strong><bdi>Situa��o</bdi></strong> ", "");
				break;
			case 6:
				resp = Ferramentas.myReplace(linha, "<p><strong><bdi>Idioma original</bdi></strong> ", "");
				resp = Ferramentas.myReplace(resp, "</p>", "");
				break;
			case 7:
				resp = Ferramentas.myReplace(linha, "<p><strong><bdi>Or�amento</bdi></strong> ", "");
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
				resp = Ferramentas.myReplace(resp, "&amp;", "");
				break;
			default:
				break;
		}
		return resp;
	}

	private File getFile(String name) throws IOException {
		File file;
		file = new File("/tmp/filmes/" + name);
		// file = new File("../tmp/filmes/" + name);
		if (!file.isFile()) {
			throw new IOException("O arquivo nao foi encontrado na pasta tmp arquivo:" + name);
		} else {
			return file;
		}
	}

}

class Lista {

	private Celula primeiro, ultimo;

	public Lista() {
		primeiro = new Celula();
		ultimo = primeiro;
	}

	public void inserirFim(Filme obj) {
		Celula tmp = new Celula(obj);
		ultimo.prox = tmp;
		ultimo = ultimo.prox;
		tmp = null;
	}

	public void inserirInicio(Filme fm) {
		Celula tmp = new Celula(fm);
		tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		if (primeiro == ultimo) {
			ultimo = tmp;
		}
		tmp = null;
	}

	public void inserir(Filme fm, int pos) throws Exception {
		int tam = tamanho();
		if (pos == 0) {
			inserirInicio(fm);
		} else if (pos == tam - 1) {
			inserirFim(fm);
		} else if (pos < 0 || pos >= tam) {
			throw new Exception("Posição de inserção invalida - pos:" + pos + " size:" + tam);
		} else {
			Celula i = primeiro;
			for (int j = 0; j < pos; j++, i = i.prox)
				;
			Celula tmp = i.prox;
			i.prox = new Celula(fm);
			i.prox.prox = tmp;
			tmp = i = null;
		}
	}

	public Filme removerInicio() throws Exception {
		Filme resp = null;
		if (primeiro == ultimo) {
			throw new Exception("A lista esta vazia!");
		}
		Celula tmp = primeiro;
		resp = tmp.prox.elemento;
		primeiro = tmp.prox;
		tmp.prox = null;
		tmp = null;
		return resp;
	}

	public Filme removerFim() throws Exception {
		Filme resp = null;
		if (primeiro == ultimo) {
			throw new Exception("A lista esta vazia!");
		}
		Celula i;
		for (i = primeiro; i.prox != ultimo; i = i.prox)
			;
		resp = ultimo.elemento;
		ultimo = i;
		ultimo.prox = null;
		i = null;
		return resp;
	}

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
			Celula i = primeiro;
			for (int j = 0; j < pos; j++, i = i.prox)
				;
			Celula tmp = i.prox;
			resp = tmp.elemento;
			i.prox = tmp.prox;
			tmp.prox = null;
			i = tmp = null;
		}
		return resp;
	}

	public int tamanho() {
		int resp = 0;
		for (Celula tmp = primeiro; tmp != ultimo; tmp = tmp.prox) {
			resp++;
		}
		return resp;
	}

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
			Celula i = primeiro;
			for (int j = 0; j < pos; i = i.prox, j++)
				;
			resp = i.prox.elemento;
		}
		return resp;
	}

	public boolean localizar(Filme obj) throws Exception {
		boolean resp = false;
		if (primeiro == ultimo) {
			throw new Exception("A lista se encontra vazia!");
		} else {
			for (Celula i = primeiro.prox; i != null; i = i.prox) {
				if (i.elemento.getTitulo().equals(obj.getTitulo())) {
					resp = true;
					i = ultimo;
				}
			}
		}
		return resp;
	}

	public boolean localizar(String str) throws Exception {
		boolean resp = false;
		if (primeiro == ultimo) {
			throw new Exception("A lista se encontra vazia!");
		} else {
			for (Celula i = primeiro.prox; i != null; i = i.prox) {
				if (i.elemento.getTitulo().equals(str)) {
					resp = true;
					i = ultimo;
				}
			}
		}
		return resp;
	}

	public void mostrar() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Lista se encontra vazia!");
		} else {
			for (Celula i = primeiro.prox; i != null; i = i.prox) {
				MyIO.println(i.elemento.toString());
			}
		}
	}

	public void imprimirReverso() {
		mostrarRec(primeiro.prox);
	}

	public void imprimir() {
		int j = 0;
		for (Celula tmp = primeiro.prox; tmp != null; tmp = tmp.prox, j++) {
			MyIO.print("[" + j + "] ");
			tmp.elemento.imprimir();
		}
		MyIO.println();
	}

	public void mostrarRec(Celula i) {
		if (i != null) {
			mostrarRec(i.prox);
			i.elemento.imprimir();
		}
	}

	public Filme removerInicioDiff() throws Exception {
		Filme resp = null;
		if (primeiro == ultimo) {
			throw new Exception("A lista se encontra vazia no momento!");
		} else {
			Celula tmp = primeiro.prox;
			resp = tmp.elemento;
			primeiro.prox = primeiro.prox.prox;
			tmp.prox = null;
			tmp = null;
		}
		return resp;
	}

}

class Celula {

	public Celula(Filme obj) {
		this.elemento = obj;
	}

	public Celula() {
		this(null);
	}

	public Filme elemento;
	public Celula prox;

}

class HashList {

	public HashList() {
		this(21);
	}

	public HashList(int num) {
		filmes = new Lista[num];
		for (int i = 0; i < num; filmes[i] = new Lista(), i++)
			;
	}

	private Lista[] filmes;

	public void inserir(Filme obj) {
		int hash = 0;
		for (char lt : obj.getTitulo().toCharArray()) {
			hash += (int) lt;
		}
		hash = hash(obj.getTitulo());
		filmes[hash].inserirInicio(obj);
	}

	private int hash(String frase) {
		int hash = 0;
		for (char lt : frase.toCharArray()) {
			hash += (int) lt;
		}
		return hash % filmes.length;
	}

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

	public boolean findSequencial(String nome) throws Exception {
		boolean resp = false;
		int comp = 0;
		int hash = 0;
		hash = hash(nome);
		comp++;
		try {
			if (filmes[hash].localizar(nome)) {
				MyIO.println("Posicao: " + hash);
				resp = true;
			} else {
				// System.out.println("verificando");
				resp = false;
			}
		} catch (Exception e) {
			resp = false;
		}
		setComp(comp);
		return resp;
	}

}

public class Tp4Q07 {

	private static boolean isFim(String entrada) {
		return entrada.length() == 3 && Ferramentas.myEquals(entrada, "FIM");
	}

	public static void main(String[] args) {
		try {
			ArrayList<String> entradas = new ArrayList<>();
			ArrayList<String> verificacoes = new ArrayList<>();
			HashList lista;
			double fim, inic;
			int comp = 0;
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
					verificacoes.add(entrada);
				}
			} while (!isFim(entrada));

			lista = new HashList();
			for (String ent : entradas) {
				Filme filme = new Filme(ent);
				lista.inserir(filme);
			}

			// procurando sequencialmente
			inic = lista.now();
			for (String ver : verificacoes) {
				MyIO.println("=> " + ver);
				if (!lista.findSequencial(ver)) {
					MyIO.println("NAO");
				}
				comp += lista.getComparacoes();
			}
			fim = lista.now();
			Ferramentas.gerarLog(inic, fim, comp);
		} catch (Exception e) {
			MyIO.println("Erro no main --> " + e.getMessage());
			e.printStackTrace();
		}
	}

}
