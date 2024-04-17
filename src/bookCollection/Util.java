package bookCollection;

import enums.Genre;
import model.Book;

import java.util.Map;
import java.util.Scanner;

public class Util {

    public static String notAllowEmptyInput(Scanner sc, String message) {
        String input;
        do {
            System.out.println(message);
            input = sc.nextLine().toUpperCase();
        } while (input.isEmpty());
        return input;
    }

    public static String validateInput(Scanner input, String message, String pattern) {
        String userInput;
        //String userInput: Esta variable se utiliza para almacenar la entrada del usuario que se valida.
        do {
            userInput = notAllowEmptyInput(input, message);
        } while (!userInput.matches(pattern));
        return userInput;
        /*
        return userInput;: Una vez que la entrada del usuario cumple con el patrón especificado en pattern,
        el bucle termina y se devuelve la entrada validada como resultado del método.
        */
    }

    public static boolean validateYesOrNo(Scanner input, String message){
        String userInput;
        do {
            userInput = notAllowEmptyInput(input, message);
        } while(!userInput.equalsIgnoreCase("yes") && !userInput.equalsIgnoreCase("no"));
        return userInput.equalsIgnoreCase("yes");
    }


    public static Genre selectGenre(Scanner input) {
        boolean validGenre = false;
        Genre selectGenre = null;

        do {
            // Show available Genres
            System.out.println("Select the genre: ");
            for(Genre genre : Genre.values()){
                System.out.println(genre);
            }

            System.out.println("Type the genre: ");
            // Verify the input of the user
            String genreInput = input.next();

            // verify if we have a valid genre
            for(Genre genre : Genre.values()){
                if(genre.name().equalsIgnoreCase(genreInput)){
                    validGenre = true;
                    selectGenre = genre;
                    break;
                }
            }

            if(!validGenre){
                System.out.println("Invalid genre. Please select from the available genres.");
            }
        }while(!validGenre);

        return selectGenre;
    }

    public static boolean emptyCollection(Map<Integer, Book> collection) {

        if(collection.isEmpty()) {
            return true;
        }

        return false;
    }

}
