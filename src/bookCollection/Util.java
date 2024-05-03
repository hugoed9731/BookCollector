package bookCollection;

import enums.Genre;
import model.Book;

import java.util.Map;
import java.util.Scanner;

public class Util {
    //import static com.example.BookCollection.bookCollection;
    // Podriamos usarlo asi, pero
    /*Esta forma de importar estáticamente es útil cuando deseas utilizar campos estáticos de otra clase sin
     tener que calificarlos repetidamente. Sin embargo, es importante tener en cuenta que el uso excesivo de
      importaciones estáticas puede hacer que tu código sea menos legible, así que
    úsalo con moderación y asegúrate de que sea claro desde dónde provienen los campos y métodos que
    estás utilizando.*/
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

    public static boolean emptyCollection(Map<Integer, Book> bookCollection) {
        return bookCollection.isEmpty();
    }

    public static void showBooks(Map<Integer, Book> bookCollection) {
        System.out.println("Welcome to your collection!");

        for (Map.Entry<Integer, Book> entry : bookCollection.entrySet()) {
                Book book = entry.getValue();

                System.out.println("------(" + entry.getKey() + ")------");
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Genre: " + book.getGenre());
                System.out.println("Editorial: " + book.getEditorial());
                System.out.println("Volume " + book.getVolume() + " of " + book.gettotalOfvolumes());
                System.out.println(book);
                System.out.println("------ 🐱‍👤 ------");

        }
    }



    public static boolean bookExists(String title, Map<Integer, Book> bookCollection) {
        for(Map.Entry<Integer, Book> entry : bookCollection.entrySet()) {
            if(entry.getValue().getTitle().equalsIgnoreCase(title)) return true;
        }
        return false;
    }

    public static boolean bookExistsByKey(int bookSelected, Map<Integer, Book> bookCollection) {
        for (Map.Entry<Integer, Book> entry : bookCollection.entrySet()) {
            if (entry.getKey().equals(bookSelected)) {
                return true;
            }
        }
        return false;
    }

    public static boolean searchByAuthor(String author, Map<Integer, Book> bookCollection) {
        for (Map.Entry<Integer, Book> entry : bookCollection.entrySet()) {
            if (entry.getValue().getAuthor().equalsIgnoreCase(author)) {
                return true;
            }
        }
        return false;
    }

    public static boolean searchByTitle(String title, Map<Integer, Book> bookCollection ) {
        for (Map.Entry<Integer, Book> entry : bookCollection.entrySet()) {
            if (entry.getValue().getTitle().equalsIgnoreCase(title)) {
                return true;
            }
        }
        return false;
    }

    public static String validateAndSetBookTitle(Scanner input, Map<Integer, Book> bookCollection) {
        String newTitle;
        boolean isValidTitle = false;

        do {
            System.out.print("Type the title: ");
            newTitle = input.nextLine().trim();

            if (!newTitle.matches("[a-zA-Z\\s,.\\-']+")) {
                System.out.println("Invalid input. Title can only contain letters, spaces, commas, periods, hyphens, and apostrophes.");
            } else if (bookExists(newTitle, bookCollection)) {
                System.out.println("The title already exists in the collection. Type a new one.");
            } else {
                isValidTitle = true;
            }
        } while (!isValidTitle);

        return newTitle;
    }




}
