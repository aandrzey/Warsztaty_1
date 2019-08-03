package src.pl.coderslab.dice;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Random;
import java.util.Scanner;

public class Roll {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj ilość format oraz opcjonalnie modyfikator kości w formacie xDy+z...");
        processUserInput(scan);
        scan.close();

    }

    static void processUserInput(Scanner scan) {
        int numberOfDice = 1;
        int diceType = 0;
        int modifier = 0;

        String input = scan.nextLine();
        String[] inputTab = input.split("D");

        //Define number of dice
        if (!"".equals(inputTab[0])) {
            try {
                numberOfDice = Integer.parseInt(inputTab[0]);
            } catch (NumberFormatException e) {
                System.out.println("Niepoprawna liczba kości");
            }


            //Define number of dice and modifier
            if (inputTab[1].contains("+")) {
                String[] addTab = inputTab[1].split("\\+");
                try {
                    diceType = Integer.parseInt(addTab[0]);
                    modifier = Integer.parseInt(addTab[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Niepoprawny typ kości lub modyfikator");
                }
            } else if (inputTab[1].contains("-")) {
                String[] subtractTab = inputTab[1].split("-");
                try {
                    diceType = Integer.parseInt(subtractTab[0]);
                    modifier = -Integer.parseInt(subtractTab[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Niepoprawny typ kości lub modyfikator");
                }
            } else {
                try {
                    diceType = Integer.parseInt(inputTab[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Niepoprawny typ kości");
                }
            }


            rollResult(numberOfDice, diceType, modifier);


        }
    }

    static void rollResult(int numberOfDice, int diceType, int modifier) {
        int[] diceTypes = {3, 4, 6, 8, 10, 12, 20, 100};
        if (ArrayUtils.contains(diceTypes, diceType)) {
            for (int i = 1; i <= numberOfDice; i++) {
                Random random = new Random();
                int r = random.nextInt(diceType) + 1;
                int result = r + modifier;
                System.out.println("Wynik " + i + " rzutu (po modyfikatorze) to: " + result);
            }

        } else {
            System.out.println("Nie ma takich kostek");
        }
    }

}
