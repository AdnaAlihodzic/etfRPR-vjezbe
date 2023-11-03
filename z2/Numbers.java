package ba.unsa.etf.rpr.t1;

import java.util.Scanner;

public class Numbers {
    public static void main(String[] arg){
        Scanner ulaz=new Scanner(System.in);
        System.out.println("Please enter integer number: ");
        int number=ulaz.nextInt();
        for(int i=1; i<number; i++){
            if(isDividableBySumOfDigits(i)){
                System.out.println(i);
            }
        }
    }
    private static boolean isDividableBySumOfDigits(int number){
        int sum=sumDigits(number);
        return number%sum==0;
    }

    private static int sumDigits(int number){
        int sum=0;
        while(number>0){
            sum+=number%10;
            number=number/10;
        }
        return sum;
    }

    private static int[] getDigits(int number){
        int numberOfDigits=String.valueOf(number).length();
        int[] digits=new int[numberOfDigits];
        int i=0;
        while(number>0){
            digits[i++]=number%10;
            number=number/10;
        }
        return digits;
    }
}
