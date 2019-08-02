package src.pl.coderslab.pcguess;

import java.util.Scanner;

public class PCGuess {

    public static void main(String[] args) {

        System.out.println("Pomyśl liczbę od 0 do 1000, a ja ją zgadnę w max. 10 próbach");
        Scanner scan = new Scanner(System.in);
        int min = 0;
        int max = 1000;
        String result = "";
        while (!result.equals("wygrałeś")) {
            int guess = ((max - min) / 2) + min;
            System.out.println("Zgaduję: " + guess);
            System.out.println("Wpisz: za mało/za dużo/wygrałeś...");
            result = scan.nextLine();
            switch (result) {
                case "za mało":
                    min = guess;
                    break;
                case "za dużo":
                    max = guess;
                    break;
                case "wygrałeś":
                    System.out.println("Wygrałem!");
                    break;
                default:
                    System.out.println("nie oszukuj!");
                    break;
            }
        }
    }


}
