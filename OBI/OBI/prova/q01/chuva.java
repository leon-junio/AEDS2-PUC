import java.util.Scanner;

public class chuva {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int intervalos = 0;
        int n = 0, s = 0, vec[], som = 0, aux = 0;
        n = sc.nextInt();
        s = sc.nextInt();
        vec = new int[n];
        for (int i = 0; i < n; i++) {
            vec[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            if (vec[i] == s) {
                intervalos++;
            }
            som += vec[i];
            aux += vec[i];
            for (int j = i + 1; j < n; j++) {
                som += vec[j];
                aux += vec[j];
                if (som == s) {
                    intervalos++;
                } else {
                    som = vec[i];
                    if (aux == s) {
                        intervalos++;
                        aux = 0;
                    }
                }
            }
            aux = 0;
        }
        System.out.println(intervalos);
    }

}