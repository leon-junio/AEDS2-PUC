import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

class Filme {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private String nome;
	private int duracao;
	private Date dataLancamento;
	private String genero;
	private String idioma;
	private String situacao;
	private float orcamento;
	private String titulo;
	private ArrayList<String> palavrasChaves = new ArrayList<>();

	// construtor
	public Filme(String entrada) {
		try {
			ler(entrada);
		} catch (Exception e) {
			System.out.println(e.getMessage() + "Erro");
		}

	}

	public Filme() {

	}

	// tratando a classe runtime
	public int getFuncaoDuracao(String linha) {
		String horas = "";
		String minutos = "";
		int total = 0, H;
		boolean verificacao = false;

		for (int i = 0; i < linha.length(); i++) {
			if (verificacao == true) {
				if (linha.charAt(i) != ' ') {
					minutos += linha.charAt(i);
				}
			} else {
				if (linha.charAt(i) != ' ') {
					horas += linha.charAt(i);
				} else {
					verificacao = true;
				}
			}
		}

		total = Integer.parseInt(minutos);

		if (horas.length() > 0) {
			H = Integer.parseInt(horas);
			total += H * 60;
		}
		return total;
	}

	// gets e sets

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public String getNome() {
		return nome;
	}

	public ArrayList<String> getPalavrasChaves() {
		return palavrasChaves;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getGenero() {
		return genero;
	}

	public int getDuracao() {
		return duracao;
	}

	public float getOrcamento() {
		return orcamento;
	}

	public String getIdioma() {
		return idioma;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setOrcamento(float orcamento) {
		this.orcamento = orcamento;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void addPalavrasChaves(String palavras) {
		palavrasChaves.add(palavras);
	}

	// metodos

	public Filme clonar() {
		Filme clone = new Filme();
		clone.setDataLancamento(dataLancamento);
		clone.setDuracao(duracao);
		clone.setNome(nome);
		return clone;
	}

	public String imprimir() {
		return (getNome().replace("    ", "") + " " + getTitulo().replace("    ", " ") + " "
				+ sdf.format(getDataLancamento()) + " " + getDuracao() + " " + getGenero() + getIdioma() + " "
				+ getSituacao() + " " + getOrcamento() + " " + getPalavrasChaves().toString() + " ");
	}

	public String removeTags(String original) {
		String limpa = "";
		for (int i = 0; i < original.length(); i++) {

			// se char for tag de abertura
			if (original.charAt(i) == '<') {
				while (original.charAt(i) != '>') {
					i++;
				}
			} else {
				limpa += original.charAt(i);
			}
		}
		return limpa;
	}

	public String buscaAteParenteses(String original) {
		String limpa = "";

		for (int i = 0; i < original.length(); i++) {
			if (original.charAt(i) != '(') {
				limpa += original.charAt(i);
			} else {
				i = original.length();
			}
		}

		return limpa;
	}

	public void ler(String nomeArq) throws Exception {
		// trocar Exception para a mais adequada
		try {
			String caminho = "tmp/filmes/";
			FileReader fr = new FileReader(caminho + nomeArq);
			BufferedReader br = new BufferedReader(fr);

			// lida primeira linha
			String linha = br.readLine();

			// saltando linha ate encontrar tittle
			while (!linha.contains("og:title")) {
				linha = br.readLine();
			}

			// atribuo nome na variavel nome (tratado)
			setNome(linha.replace("<meta property=\"og:title\" content=\"", "").replace("\">", "").trim()
					.replace("       ", "").replace("    ", ""));

			// pulando a linha de nome
			linha = br.readLine();

			// saltando linha ate encontrar release (date)
			while (!linha.contains("class=\"release\"")) {
				linha = br.readLine();
			}

			// saltando uma linha a mais
			linha = br.readLine();

			// setar variavel data
			setDataLancamento(sdf.parse(buscaAteParenteses(linha).trim()));

			// buscar genero
			while (!linha.contains("class=\"genres\"")) {
				linha = br.readLine();
			}

			// saltando uma linha a mais
			br.readLine();

			// saltando uma linha a mais
			linha = br.readLine();

			linha = linha.replace("      ", "");

			// setar variavel genero
			setGenero(removeTags(linha).replace("&nbsp;", " ").replace("       ", " "));

			// saltando uma linha a mais
			linha = br.readLine();

			// buscando duracao
			while (!linha.contains("class=\"runtime\"")) {
				linha = br.readLine();
			}

			// saltando uma linha a mais
			br.readLine();

			// saltando uma linha a mais
			String aux = "";
			linha = br.readLine().trim();
			linha = linha.replace('m', ' ');
			if (linha.contains("h")) {
				linha = linha.replace('h', ' ');
			} else {
				aux = " " + linha;
				linha = aux;
				aux= "";
			}

			// setando a duracao
			setDuracao(getFuncaoDuracao(linha));

			// saltando uma linha a mais
			linha = br.readLine();

			// buscar o titulo original
			while (!linha.contains("Título original")) {
				linha = br.readLine();

				if (linha.contains("<bdi>Situação</bdi>")) {
					setSituacao(removeTags(linha).replace("       ", " "));

					setTitulo((getNome()).replace("      ", "").replace("    ", ""));

					break;
				}
			}

			if (linha.contains("Título original")) {
				setTitulo(removeTags(linha).replace("Título original ", "").replace("      ", ""));
			}

			while (!linha.contains("<bdi>Situação</bdi>")) {
				linha = br.readLine();
			}

			setSituacao(removeTags(linha).replace("       ", " ").replace("    Situação ", ""));

			// buscar idioma
			while (!linha.contains("Idioma original")) {
				linha = br.readLine();
			}

			setIdioma(removeTags(linha).replace(" Idioma original ", "").replace("       ", " "));

			// buscar orcamento
			while (!linha.contains("<bdi>Orçamento</bdi>")) {
				linha = br.readLine();
			}

			linha = removeTags(linha).replace("Orçamento ", "");

			if (linha.contains("-")) {
				linha = "0.0";
			} else {
				linha = linha.replace("$", "");
				linha = linha.replace(",", "");
			}

			setOrcamento(Float.parseFloat(linha));

			br.readLine();

			linha = br.readLine();

			// buscar palavras chaves
			while (!linha.contains("Palavras-chave")) {
				linha = br.readLine();
			}

			linha = br.readLine();
			linha = br.readLine();

			if (linha.contains("Nenhuma palavra-chave foi adicionada.")) {
				linha = "</ul>";
			}

			while (!linha.contains("</ul>")) {
				linha = br.readLine();

				if (linha.contains("li")) {
					// setar a palavra chave nesta linha em array temp
					addPalavrasChaves(removeTags(linha).replace("        ", ""));
				}
			}

			// palavrasChave = new String[contador];
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class tp02 {
	public static boolean isFim(String filme) {
		return (filme.length() == 3 && filme.charAt(0) == 'F' && filme.charAt(1) == 'I' && filme.charAt(2) == 'M');
		// verifica se o usuario digitou a condicao de parada
	}

	public static void main(String[] args) throws Exception {

		MyIO.setCharset("UTF-8");
		// trocar Exception para a mais adequada
		String entrada[] = new String[100];
		int contador = 0;

		String nomeFilme;

		do {
			nomeFilme = MyIO.readLine();
			entrada[contador] = nomeFilme;
			contador++;
		} while (isFim(nomeFilme) == false);

		contador--;

		// visualizacao -> entrada[batman.html, luca.html, encanto.html, dog.html]
		// criar array filmes do tamanho da entrada

		Filme filmes[] = new Filme[contador];
		// instanciar objetos

		for (int i = 0; i < contador; i++) {
			filmes[i] = new Filme(entrada[i]);
		}

		// percorrer o array imprimindo

		for (int i = 0; i < contador; i++) {
			MyIO.println(filmes[i].imprimir());
		}
	}
}