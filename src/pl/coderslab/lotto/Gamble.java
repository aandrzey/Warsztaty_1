package src.pl.coderslab.lotto;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Gamble {

    public static void main(String[] args) {

        int[] userNumbersTab = givenNumbers();
        String userNumbers = Arrays.toString(userNumbersTab);
        System.out.println("Twoje liczby to: " + userNumbers);

        int[] winningNumbersTab = numbersToGuess();
        String winningNumbers = Arrays.toString(winningNumbersTab);
        System.out.println("Wylosowane liczby to: " + winningNumbers);

        numbersGot(userNumbersTab, winningNumbersTab);

    }

    static int[] numbersToGuess() {
        Random random = new Random();
        int r = random.nextInt(49) + 1;
        int[] tab = new int[6];
        for (int i = 0; i < tab.length; i++) {
            for (int number : tab)
                while (r == number) {
                    r = random.nextInt(49) + 1;
                }
            tab[i] = r;
        }
        Arrays.sort(tab);
        return tab;
    }

    static int[] givenNumbers() {
        int[] numbers = new int[6];
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj 6 liczb z zakresu 1-49...");
        int userNum = getNumber(scan);
        scan.nextLine();
        for (int i = 0; i < numbers.length; i++) {
            while (ArrayUtils.contains(numbers, userNum) || userNum < 1 || userNum > 49) {
                System.out.println("Podana liczba została już podana lub jest poza zakresem (1-49)");
                userNum = getNumber(scan);
            }
            numbers[i] = userNum;
            if (i < numbers.length - 1) {
                System.out.println("Podaj następną liczbę");
                userNum = getNumber(scan);
            }

        }
        Arrays.sort(numbers);
        return numbers;
    }

    static int getNumber(Scanner scan) {
        while (!scan.hasNextInt()) {
            System.out.println("Podaj liczbę");
            scan.nextLine();
        }
        return scan.nextInt();

    }

    static void numbersGot(int[] numbersPicked, int[] winningNumbers) {
        int got = 0;
        for (int i = 0; i < 6; i++)
            if (ArrayUtils.contains(winningNumbers, numbersPicked[i])) {
                got++;
            }

        switch (got) {
            case 3:
                System.out.println("Wygrałeś! Trafiłeś 3 liczby");
                break;
            case 4:
                System.out.println("Wygrałeś! Trafiłeś 4 liczby");
                break;
            case 5:
                System.out.println("Wygrałeś! Trafiłeś 5 liczb");
                break;
            case 6:
                System.out.println("Wygrałeś! Trafiłeś 6 liczb");
                break;
            default:
                System.out.println("Przegrałeś :( Liczba trafionych liczb: " + got);
        }
    }

}


