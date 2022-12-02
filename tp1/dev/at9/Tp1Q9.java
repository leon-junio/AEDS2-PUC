

import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

class Tp1Q9 {

	//Função recursiva usada para acessar posições de um arquivo de texto contendo n doubles escritos de forma sequencial. Após, abrir os mesmos serão impressos de trás para frente usando a estrategia de pilha da função recursiva, para acessar as posições do arquivo e imprimir as doubles é utilizado o getFilePointer()
	public static int exibir(RandomAccessFile ra, int count){
		try{
			int sk = 0;
			if(count < ra.length()-1){
				sk = (int) ra.getFilePointer();
				ra.readLine();
				count = exibir(ra,(int)ra.getFilePointer());
				ra.seek(sk);
				MyIO.println(ra.readLine());
			}
			if(count >= ra.length()-1){
				return count;
			}
			return 0;
		}catch(IOException io){
			io.printStackTrace();
			return 0;
		}
	}


	public static void main(String[] args) {
		try {
			int totalNum = MyIO.readInt(), i = 0;
			BufferedWriter file = new BufferedWriter(new FileWriter("temp.txt"));
			try {
				double num = 0;
				while (i < totalNum) {
					num = MyIO.readDouble();
					if(((num*100)%100) == 0){
						file.append((int)num+"\n");	
					}else if(((num*100)%100) != 0){
						file.append(num + "\n");
					}
					i++;
				}
			} catch (IOException io) {
				System.out.println("Erro no arquivo: " + io.getMessage());
			}
			file.close();
			RandomAccessFile ra = new RandomAccessFile("temp.txt", "r");
			exibir(ra,0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
