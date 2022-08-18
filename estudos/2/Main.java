import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = 0;
        int resp = 0;
        String last = "";
        ArrayList<String> lista = new ArrayList<String>();
        do {
            n = sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < n; i++) {
                lista.add(sc.nextLine());
            }
            for (String str : lista) {
                resp += compStrs(last, str);
                last = str;
            }
            System.out.println(resp);
            resp = 0;
            lista.clear();
            last = "";
        } while (sc.hasNext());
        sc.close();
    }

    public static int compStrs(String last, String str) {
        int resp = 0;
        for (int i = 0; i < last.length(); i++) {
            if (last.charAt(i) == str.charAt(i)) {
                resp++;
            } else {
                i = last.length();
            }
        }
        return resp;
    }
}