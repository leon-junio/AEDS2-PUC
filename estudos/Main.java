import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // qtd de letras no nome
        HashMap<String, Integer> hashYes = new HashMap<String, Integer>();
        HashMap<String, Integer> hashNo = new HashMap<String, Integer>();
        ArrayList<String> lista = new ArrayList<>();
        ArrayList<String> aux = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int pos = 0;
        String name = "", option = "", maior = "";
        do {
            name = sc.next();
            if (!name.equals("FIM")) {
                option = sc.next();
                if (option.contains("N")) {
                    pos++;
                    hashNo.put(name, pos);
                } else {
                    if (hashYes.get(name) == null) {
                        pos++;
                        hashYes.put(name, pos);
                    }
                }
            }
        } while (!name.equals("FIM"));
        for (Object key : hashYes.keySet()) {
            lista.add(key.toString());
            if (key.toString().length() > maior.length()) {
                maior = key.toString();
            } else if (key.toString().length() == maior.length()) {
                maior = (int) hashYes.get(key) < (int) hashYes.get(maior) ? key.toString() : maior;
            }
        }
        Collections.sort(lista);
        for (Object key : hashNo.keySet()) {
            aux.add(key.toString());
        }
        Collections.sort(aux);
        if (hashYes.isEmpty()) {
            for (String txt : aux) {
                if (txt.length() > maior.length()) {
                    maior = txt;
                } else if (txt.length() == maior.length()) {
                    maior = (int) hashNo.get(txt) < (int) hashYes.get(maior) ? txt : maior;
                }
            }
        }
        lista.addAll(aux);
        for (String resp : lista) {
            System.out.println(resp);
        }
        System.out.println("");
        System.out.println("Amigo do Habay:");
        System.out.print(maior);
        sc.close();
    }
}