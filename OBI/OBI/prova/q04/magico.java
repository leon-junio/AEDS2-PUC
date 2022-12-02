import java.util.ArrayList;
import java.util.Scanner;

public class magico {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<String> listlts = new ArrayList<>();
        int[][] quad = new int[n][n];
        int som = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                quad[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                som += quad[i][j];
            }
            list.add(som);
            listlts.add("n");
            som = 0;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                som += quad[j][i];
            }
            list.add(som);
            listlts.add("h");
            som = 0;
        }

        int ji = 0;
        for (int i = 0; i < n; i++) {
            ji = 0;
            som += quad[i][ji];
            ji += 1;
        }

        list.add(som);
        listlts.add("d1");
        som = 0;
        ji = n-1;

        for (int i = n-1; i >= 0; i--) {
            som += quad[i][ji];
            ji -= 1;
        }

        list.add(som);
        listlts.add("d2");
        som = 0;

        int ct = list.get(0), diff = 0;
        int index = 0;
        for (int ts : list) {
            if (ts != ct) {
                if (diff == ts) {
                    ct = diff;
                    diff = 0;
                    index = 0;
                } else {
                    diff = ts;
                    index = list.indexOf(ts);
                }
            }
        }
        int linha = 0, col = 0, num = 0;
        switch (listlts.get(index)) {
            case "n":
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        som += quad[i][j];
                    }
                    if (som == diff) {
                        linha = i+1;
                        num = ct - diff;
                        for (int j = 0; j < n; j++) {
                            if (quad[i][j] == 0) {
                                col = j+1;
                                j = n;
                            }
                        }
                    }
                    som = 0;
                }
                break;
            case "h":
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        som += quad[i][j];
                    }
                    if (som == diff) {
                        linha = i+1;
                        int aux = 0;
                        num = ct - diff;
                        for (int j = 0; j < n; j++) {
                            if (quad[j][i] == 0) {
                                col = j+1;
                                j = n;
                            }
                        }
                    }
                    som = 0;
                }
                break;
            case "d1":
                ji = 0;
                for (int i = 0; i < n; i++) {
                    som += quad[i][ji];
                    ji += 1;
                    if (som == diff) {
                        linha = i+1;
                        int aux = 0;
                        num = ct - diff;
                        for (int j = 0; j < n; j++) {
                            if (quad[i][j] == 0) {
                                col = j+1;
                                j = n;
                            }
                        }
                    }
                    som = 0;
                }
                break;
            case "d2":
                ji = n-1;
                for (int i = n-1; i >= 0; i--) {
                    som += quad[i][ji];
                    ji -= 1;
                    if (som == diff) {
                        linha = i+1;
                        int aux = 0;
                        num = ct - diff;
                        for (int j = 0; j < n; j++) {
                            if (quad[i][j] == 0) {
                                col = j+1;
                                j = n;
                            }
                        }
                    }
                    som = 0;
                }
                break;
        }
        System.out.print(num+"\n");
        System.out.print(linha+"\n");
        System.out.print(col+"\n");
        sc.close();
    }
}