package src.pl.coderslab.guess;

import java.util.Random;
import java.util.Scanner;

public class Guess {

    public static void main(String[] args) {

        System.out.println("Zgadnij liczbę:");
        Scanner scan = new Scanner(System.in);
        Random random = new Random();
        int r = random.nextInt(100) + 1;
        int a = getNumber(scan);

        while (a != r) {
            if (a > r) {
                System.out.println("Za dużo!");
                a = getNumber(scan);
            } else {
                System.out.println("Za mało!");
                a = getNumber(scan);
            }

        }
        scan.close();
        System.out.println("Zgadłeś!");
    }

    static int getNumber(Scanner scan) {
        while (!scan.hasNextInt()) {
            scan.nextLine();
            System.out.println("To nie jest liczba.");
        }
        int number = scan.nextInt();
        return number;
    }
}
