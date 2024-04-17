package bookCollection;

import enums.Genre;
import model.Book;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class BookCollection {
  // Attributes
  private static Map<Integer, Book> bookCollection = new LinkedHashMap<>();

  private static int bookId = 1;

  // Constructor
  public BookCollection() {
    bookCollection = new LinkedHashMap<Integer, Book>();
  }

  public static boolean bookExists(String title) {
    for (Map.Entry<Integer, Book> entry : bookCollection.entrySet()) {
      if (entry.getValue().getTitle().equalsIgnoreCase(title)) {
        return true;
      }
    }
    return false;
  }

  public static void addBook(Scanner input) {
    boolean bookAdded = false;
    String title;
    Genre genreSelected;
    String author;
    String editorial;
    boolean onlyVolume;
    int totalOfVolumes = 0;
    int volume = 0;

    do {
      input.nextLine();

      title = Util.validateInput(input, "Type the title: ", "[a-zA-Z\\s,.\\-'']+");

      /*do {
        title = Util.notAllowEmptyInput(input, "Type the title: ");
      } while (!title.matches("[a-zA-Z\\s,.\\-'']+"));*/

      if(bookExists(title)){
        System.out.println("The book already exists in the collection. Please enter another title.");
        continue; // Volver al inicio del loop si el titulo ya existe
        /*
      cuando bookExists(title) es true, la línea continue; provoca que el programa regrese al inicio del bucle do-while, evitando que
      se continúe con la lógica restante dentro del bucle y permitiendo al usuario ingresar otro título sin ejecutar el resto de
       las instrucciones dentro del bucle para ese ciclo. Esto ayuda a evitar que se agreguen títulos duplicados a la colección.
        */
      }
      genreSelected = Util.selectGenre(input);

      input.nextLine();

      author = Util.validateInput(input, "Type the author: ", "[a-zA-Z\\s,.\\-'']+");
      editorial = Util.validateInput(input, "Type the editorial: ", "[a-zA-Z\\s,.\\-'']+");
      onlyVolume = Util.validateYesOrNo(input, "Is it a only volume? (yes/no) ");


      if (onlyVolume) {
          totalOfVolumes = 1;
          volume = 1;
      } else {
        // Integer.parseInt es un método en Java que toma una cadena de texto (String) que representa un número y la convierte en un tipo de dato entero (int).
        totalOfVolumes = Integer.parseInt(Util.validateInput(input, "How many volumes are there? ", "[1-9]\\d*"));

        if(totalOfVolumes > 1) {
          volume = Integer.parseInt(Util.validateInput(input, "What volume number is this? ", "[1-9]\\d*"));
        } else {
            totalOfVolumes = 1;
        }
      }

      // Verificar si todos los campos obligatorios están llenos
      if(!title.isEmpty() &&
              genreSelected !=null &&
              !author.isEmpty() &&
              !editorial.isEmpty()){
        // Agregar el nuevo libro a la colección
        Book newBook = new Book(title, genreSelected, author, editorial, totalOfVolumes, volume);
        bookCollection.put(bookId++, newBook);
        System.out.println("Book added to the collection!");
        bookAdded = true;
      } else {
        System.out.println("Please fill in all required fields.");
      }

    } while (!bookAdded);

  }

  public static void editBook(Scanner input) {

    // TODO evitar escribir codigo dos veces, simplificarlo
    if(Util.emptyCollection(bookCollection)) {
      System.out.println("Your collection is empty!");
    } else {
      System.out.println("Welcome to your collection!");
      for (Map.Entry<Integer, Book> entry : bookCollection.entrySet()) {
        Integer index = entry.getKey();
        String title = entry.getValue().getTitle();
        Genre genre = entry.getValue().getGenre();
        String author = entry.getValue().getAuthor();
        String editorial = entry.getValue().getEditorial();
        int totalOfVolumes = entry.getValue().gettotalOfvolumes();
        int volume = entry.getValue().getVolume();
        String readIt = entry.getValue().toString();

        System.out.println("(" + index + ")");
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Genre: " + genre);
        System.out.println("Editorial: " + editorial);
        System.out.println("Volume " + volume + " of " + totalOfVolumes);
        System.out.println(readIt);
        System.out.println("------ 🐱‍👤 ------");
      }

      // TODO validar esto que no funciona
      boolean bookFound = false;
      int bookSelected = 0;

      do {
        input.nextLine();
        bookSelected = Integer.parseInt(Util.validateInput(input, "What book you'd like to edit. Type the number: ", "[1-9]\\d*"));

        System.out.println("Valio verga apaaaa");

        if(bookSelected > bookCollection.size()) {
          System.out.println("Type a valid number of your collection: ");
          continue;
        }

        // Integer.valueOf() para convertir la cadena devuelta por Util.validateInput(...) en un número entero.
        for (Map.Entry<Integer, Book> entry : bookCollection.entrySet()) {
          if (entry.getKey().equals(bookSelected)) {
            bookFound = true;
            break;
          }
        }

        if (!bookFound) {
          System.out.println("Book has not been found!");
        }

      } while(bookSelected > bookCollection.size());


      if (bookFound) {

        int optionSelected;

        do{
          System.out.println("What would you like to edit: ");
          System.out.println("1.-Title ");
          System.out.println("2.-Genre ");
          System.out.println("3.-Author ");
          System.out.println("4.-Editorial ");
          System.out.println("5.-Total of volumes ");
          System.out.println("6.-Volume ");

          System.out.println("Type an option: ");
          optionSelected = input.nextInt();
        } while (optionSelected != 8);

        switch (optionSelected) {
          case 1:
          default:
            break;
        }

      }

    }
  }


  public static void showMyCollection() {
    if(Util.emptyCollection(bookCollection)) {
      System.out.println("Your collection is empty!");
    } else {
      System.out.println("Welcome to your collection!");
      for (Map.Entry<Integer, Book> entry : bookCollection.entrySet()) {
        Integer index = entry.getKey();
        String title = entry.getValue().getTitle();
        Genre genre = entry.getValue().getGenre();
        String author = entry.getValue().getAuthor();
        String editorial = entry.getValue().getEditorial();
        int totalOfVolumes = entry.getValue().gettotalOfvolumes();
        int volume = entry.getValue().getVolume();
        String readIt = entry.getValue().toString();

        System.out.println("(" + index + ")");
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Genre: " + genre);
        System.out.println("Editorial: " + editorial);
        System.out.println("Volume " + volume + " of " + totalOfVolumes);
        System.out.println(readIt);
        System.out.println("------ 🐱‍👤 ------");
      }
    }

  }



}
