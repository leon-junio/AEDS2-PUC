import java.util.Scanner;

public class maior {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), s = sc.nextInt(), soma = 0, maior = -1;
        String aux = "";
        for (int i = n; i <= m; i++) {
            aux = String.valueOf(i);
            // System.out.println(aux);
            for (int j = 0; j < aux.length(); j++) {
                soma += Integer.valueOf(aux.charAt(j)+"");
            }
            if (soma == s) {
                if (i > maior) {
                    maior = i;
                }
            }
            soma = 0;
        }
        System.out.println(maior);
    }
}