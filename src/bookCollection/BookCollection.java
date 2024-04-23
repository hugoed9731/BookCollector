package bookCollection;

import enums.Genre;
import model.Book;

import java.util.InputMismatchException;
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

  // TODO: move these methods to util class - 18/04/2024 - HUGOEDD
  public static boolean bookExists(String title) {
    for (Map.Entry<Integer, Book> entry : bookCollection.entrySet()) {
      if (entry.getValue().getTitle().equalsIgnoreCase(title)) {
        return true;
      }
    }
    return false;
  }

  public static boolean bookExistsByKey(int bookSelected) {
    for (Map.Entry<Integer, Book> entry : bookCollection.entrySet()) {
      if (entry.getKey().equals(bookSelected)) {
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

        input.nextLine();
      int bookSelected = Integer.parseInt(Util.validateInput(input, "What book you'd like to edit. Type the number: ", "[1-9]\\d*"));

      do {
          if(bookSelected > bookCollection.size()) {
            bookSelected = Integer.parseInt(Util.validateInput(input, "Type a valid number of your collection:: ", "[1-9]\\d*"));
          }

        // Integer.valueOf() para convertir la cadena devuelta por Util.validateInput(...) en un número entero.
      } while(bookSelected > bookCollection.size());


        if(bookExistsByKey(bookSelected)) {

          Book item = bookCollection.get(bookSelected);
          int optionSelected;

          do{

            System.out.println("What would you like to edit: ");
            System.out.println("1.-Title ");
            System.out.println("2.-Genre ");
            System.out.println("3.-Author ");
            System.out.println("4.-Editorial ");
            System.out.println("5.-Total of volumes ");
            System.out.println("6.-Volume ");
            System.out.println("7.-Exit");

            System.out.println("Type an option: ");
            optionSelected = input.nextInt();

            try{
              input.nextLine();

              switch (optionSelected) {
                case 1:
                  System.out.println("Current Title: " + item.getTitle());
                  String newTitle = Util.validateInput(input, "Type the new title: ", "[a-zA-Z\\s,.\\-'']+");
                  item.setTitle(newTitle);
                  System.out.println("Title edited successfully");
                  break;
                case 2:
                  System.out.println("Current Genre: " + item.getGenre());
                  Genre newGenre = Util.selectGenre(input);
                  item.setGenre(newGenre);
                  System.out.println("Genre edited successfully");
                  break;
                case 3:
                  System.out.println("Current Author: " + item.getAuthor());
                  String newAuthor = Util.validateInput(input, "Type the new author: ", "[a-zA-Z\\s,.\\-'']+");
                  item.setAuthor(newAuthor);
                  System.out.println("Author edited successfully");
                  break;
                case 4:
                  System.out.println("Current Editorial: " + item.getEditorial());
                  String newEditorial = Util.validateInput(input, "Type the new editorial: ", "[a-zA-Z\\s,.\\-'']+");
                  item.setEditorial(newEditorial);
                  System.out.println("Editorial edited successfully");
                  break;
                case 5:
                  System.out.println("Current Total of volumes: " + item.gettotalOfvolumes());
                  int newTotalOfVolumes = Integer.parseInt(Util.validateInput(input, "How many volumes are there? ", "[1-9]\\d*"));

                  item.settotalOfvolumes(newTotalOfVolumes);
                  System.out.println("Total of volumes edited successfully");
                  break;
                case 6:
                  System.out.println("Current volume of this book: " + item.getVolume());

                  int newNumberOfvolume = Integer.parseInt(Util.validateInput(input, "What is the new number of volume? ", "[1-9]\\d*"));

                    do {
                      if(newNumberOfvolume > item.gettotalOfvolumes()) {
                        newNumberOfvolume = Integer.parseInt(Util.validateInput(input, "Volume number is larger than the collection. Type a valid one:  ", "[1-9]\\d*"));
                      }
                    } while (newNumberOfvolume > item.gettotalOfvolumes());

                 item.setVolume(newNumberOfvolume);
                 System.out.println("Volume edited successfully");
                  break;
                case 7:
                  System.out.println("See you soon!");
                  return;
                default:
                  break;
              }

              System.out.println(); // Space so that menu looks better

            } catch(InputMismatchException e) {
              System.out.println("Error: Please type a valid option.");
              input.nextLine();

              optionSelected = 0;
            }

          } while (optionSelected != 8);


        } else {
          System.out.println("Book has not been found!");

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
