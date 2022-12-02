import java.util.ArrayList;
import java.util.Scanner;

public class hotel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int d = sc.nextInt();
        int a = sc.nextInt();
        int n = sc.nextInt();
        if (n == 1) {
            System.out.println((31 * d));
        } else if(n < 16){
            int val = d+((n-1)*a);
            System.out.println((32-n)*val);
        }else{
            int val = d+(14*a);
            System.out.println((32-n)*val);
        }
    }
}
