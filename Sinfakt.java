package ba.unsa.etf.rpr.t2;

public class Sinfakt {

    public static double calculateSin(double x) {
        return Math.sin(x);  // Using the built-in Math.sin function
    }

    public static long calculateFactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Faktorijel ne mozemo izracunati za negtivne brojeve.");
        }
        if (n == 0) {
            return 1;
        }
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Unesite 2 argumenta: <funkcija> <broj>");
            return;
        }

        String function = args[0];
        double number = Double.parseDouble(args[1]);

        if (function.equalsIgnoreCase("sin")) {
            double result = calculateSin(number);
            System.out.println("Sin(" + number + ") = " + result);
        } else if (function.equalsIgnoreCase("factorial")) {
            int intNumber = (int) number;
            long result = calculateFactorial(intNumber);
            System.out.println(intNumber + "! = " + result);
        } else {
            System.out.println("Nepodrzana funkcija. Koristite 'sin' ili 'factorial'.");
        }
    }
}

