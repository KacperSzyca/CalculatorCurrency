package com.company.app;

import java.util.List;
import java.util.Scanner;

public class Calc {

    public static void main(String[] args) {
        ParseXml parseXml = new ParseXml();

        // Tworzenie listy walut
        List<Currency> cubeList = parseXml.parse();

        // deklaracja skanera
        Scanner scan = new Scanner(System.in);

        // zmienna kontrolna  - odpowiada za ponowne uruchomienie metody Calc
        String again;

        do {
            calc(cubeList);

            System.out.print("\nDo you want to convert another currency? (Yes/No): ");

            // przypisanie wpisanej wartości do zmiennej kontrolnej
            again = scan.nextLine();

        } while (again.toLowerCase().equals("yes"));
    }

    private static void calc(List<Currency> cubeList) {
        Scanner scan = new Scanner(System.in); // dekleracja skanera
        System.out.println("Enter the amount in Euro (Example 250,50)");

        // deklaracja oraz sprawdzenie zmiennej wpisywanej przez uzytkownika
        double eur = 0;
        double checkValue = 0;

        do {
            checkValue = checkInputvalue();
            if (checkValue > 0) {
                eur = checkValue;
                break;
            }
        } while (eur > 0);

        System.out.println("Available currencies: \n");

        // deklaracja zmiennej kontrolnej pomocnej przy wyswietlaniu
        int line = 0;

        for (Currency x : cubeList) {
            // wyswietlanie dostępnych walut
            System.out.print(" | Number (Id):" + x.getId() + " Currency: " + x.getCurrency());

            //Za pomocą zmiennej kontrolnej co 5 wyświetlonych walut tworzona jest nowa linia
            line++;
            if (line == 3) {
                System.out.print("\n");
                line = 0;
            }

        }
        System.out.print("\nEnter the currency number (id) or three-letter abbreviation (symbol): ");

        // deklaracja zmiennej do przypisania id
        int id = 0;

        // zmienna odpowiedzialna za przechowywanie wpisanej liczby
        int check = 0;
        do {
            // sprawdzenie czy wpisana wartość nie jest liczbą
            if (!scan.hasNextInt()) {

                // pobranie wpisanej wartosci
                String s = scan.next();

                // przeszukanie listy w celu porównania zmiennej
                for (Currency x : cubeList) {
                    if (s.toUpperCase().equals(x.getCurrency())) {
                        id = x.getId();
                        break;
                    }
                }
                System.out.print("Enter correct number or symbol!\n");
            } else {
                check = scan.nextInt();

                //sprawdzenie czy wpisana liczba zgadza się z liczbą dostępnych id
                if (check > 0 && check <= cubeList.size()) {
                    id = check;
                } else {
                    System.out.print("Enter correct number or symbol!\n");
                }
            }

        } while (id <= 0);

        // obliczanie kwoty
        double endValue = calculationOftheAmount(cubeList.get(id - 1).getRate(), eur);

        System.out.print("\nThe amount after conversion is: " + endValue + " " + cubeList.get(id - 1).getCurrency());
    }

    public static double calculationOftheAmount(Double value1, Double value2) {
        double endvalue = value1 * value2;
        return endvalue;
    }

    public static double checkInputvalue() {
        Scanner scan = new Scanner(System.in);
        int checkPositiveValue = 0;
        double eur;
        do {

            // sprawdzenie zmiennej kontrolnej
            if (checkPositiveValue == 1) {
                System.out.print("Enter an amount greater than 0!\n"); }

            // sprawdzenie czy podana wartość jest kwotą
            while (!scan.hasNextDouble()) {
                System.out.println("That's not the amount! Try to use a separator  ' , '");
                scan.next(); }

            // przypisanie wartości podanej przez uzykownika
            eur = scan.nextDouble();

            // przypisanie wartości zmiennej kontrolnej
            checkPositiveValue = 1;

        } while (eur <= 0);

        return eur;
    }
}