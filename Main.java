package ba.unsa.etf.rpr.t1;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner ulaz = new Scanner(System.in);
        System.out.print("Unesite prvi broj: ");
        int a = ulaz.nextInt();
        System.out.print("Unesite drugi broj: ");
        int b = ulaz.nextInt();
        System.out.printf("Prvi broj je %d, a drugi broj je %d.", a, b);
        ulaz.close();
    }
}
