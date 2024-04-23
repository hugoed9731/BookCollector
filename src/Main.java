import bookCollection.BookCollection;
import bookCollection.Util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int menuOption;
        String name;

        System.out.println("Welcome to the MangaBook!");

        do {
            name = Util.notAllowEmptyInput(sc, "Please type your name: ");

        } while (!name.matches("[a-zA-Z]+"));


        do {
            try{
                System.out.println("Hello " + name);
                System.out.println("What would you like to do today?");
                System.out.println("1.- Add a book.");
                System.out.println("2.- Edit a Book.");
                System.out.println("3.- Delete a Book.");
                System.out.println("4.- Show my collection.");
                System.out.println("5.- Search a book.");
                System.out.println("6.- Mark as read.");
                System.out.println("7.-Exit");

                menuOption = sc.nextInt();

                switch (menuOption) {
                    case 1:
                        BookCollection.addBook(sc);
                        break;
                    case 2:
                        BookCollection.editBook(sc);
                        break;
                    case 3:
                        BookCollection.deleteBook(sc);
                        break;
                    case 4:
                        BookCollection.showMyCollection();
                        break;
                    case 5:
                        sc.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Please type a valid number");
                        break;
                }

                System.out.println();
            }catch (InputMismatchException e){
                System.out.println("Error: Please type a valid number.");
                sc.nextLine(); // Cleaning the Java Buffer

                menuOption = 0;
            }
        } while (menuOption != 8);


    }
}