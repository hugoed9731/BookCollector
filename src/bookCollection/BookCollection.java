package bookCollection;

import enums.Genre;
import model.Book;

import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static bookCollection.Util.*;

public class BookCollection {
  protected static Map<Integer, Book> bookCollection = new LinkedHashMap<>();

  private static int bookId = 1;

  // Constructor
  public BookCollection() {
    bookCollection = new LinkedHashMap<Integer, Book>();
  }

  public static void addBook(Scanner input) {
    boolean bookAdded = false;
    String title;
    Genre genreSelected;
    String author;
    String editorial;
    boolean onlyVolume;
    int totalOfVolumes;
    int volume = 0;

    do {
      input.nextLine();

      title = validateAndSetBookTitle(input, bookCollection);

      genreSelected = selectGenre(input);

      input.nextLine();

      author = validateInput(input, "Type the author: ", "[a-zA-Z\\s,.\\-'']+");
      editorial = validateInput(input, "Type the editorial: ", "[a-zA-Z\\s,.\\-'']+");
      onlyVolume = validateYesOrNo(input, "Is it a only volume? (yes/no) ");


      if (onlyVolume) {
          totalOfVolumes = 1;
          volume = 1;
      } else {
        // Integer.parseInt es un método en Java que toma una cadena de texto (String) que representa un número y la convierte en un tipo de dato entero (int).
        totalOfVolumes = Integer.parseInt(validateInput(input, "How many volumes are there? ", "[1-9]\\d*"));

        if(totalOfVolumes > 1) {
          volume = Integer.parseInt(validateInput(input, "What volume number is this? ", "[1-9]\\d*"));
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

    if(emptyCollection(bookCollection)) {
      System.out.println("Your collection is empty!");
      return;
    }

    showBooks(bookCollection);

    input.nextLine();
    int bookSelected = Integer.parseInt(validateInput(input, "What book you'd like to edit. Type the number: ", "[1-9]\\d*"));

      do {
          if(bookSelected > bookCollection.size()) {
            bookSelected = Integer.parseInt(validateInput(input, "Type a valid number of your collection: ", "[1-9]\\d*"));
          }

        // Integer.valueOf() para convertir la cadena devuelta por Util.validateInput(...) en un número entero.
      } while(bookSelected > bookCollection.size());


        if(bookExistsByKey(bookSelected, bookCollection)) {

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
                  String newTitle = validateAndSetBookTitle(input, bookCollection);

                  item.setTitle(newTitle);
                  System.out.println("Title edited successfully");
                  break;
                case 2:
                  System.out.println("Current Genre: " + item.getGenre());
                  Genre newGenre = selectGenre(input);
                  item.setGenre(newGenre);
                  System.out.println("Genre edited successfully");
                  break;
                case 3:
                  System.out.println("Current Author: " + item.getAuthor());
                  String newAuthor = validateInput(input, "Type the new author: ", "[a-zA-Z\\s,.\\-'']+");
                  item.setAuthor(newAuthor);
                  System.out.println("Author edited successfully");
                  break;
                case 4:
                  System.out.println("Current Editorial: " + item.getEditorial());
                  String newEditorial = validateInput(input, "Type the new editorial: ", "[a-zA-Z\\s,.\\-'']+");
                  item.setEditorial(newEditorial);
                  System.out.println("Editorial edited successfully");
                  break;
                case 5:
                  System.out.println("Current Total of volumes: " + item.gettotalOfvolumes());
                  int newTotalOfVolumes = Integer.parseInt(validateInput(input, "How many volumes are there? ", "[1-9]\\d*"));

                  item.settotalOfvolumes(newTotalOfVolumes);
                  System.out.println("Total of volumes edited successfully");
                  break;
                case 6:
                  System.out.println("Current volume of this book: " + item.getVolume());

                  int newNumberOfvolume = Integer.parseInt(validateInput(input, "What is the new number of volume? ", "[1-9]\\d*"));

                    do {
                      if(newNumberOfvolume > item.gettotalOfvolumes()) {
                        newNumberOfvolume = Integer.parseInt(validateInput(input, "Volume number is larger than the collection. Type a valid one:  ", "[1-9]\\d*"));
                      }
                    } while (newNumberOfvolume > item.gettotalOfvolumes());

                 item.setVolume(newNumberOfvolume);
                 System.out.println("Volume edited successfully");
                  break;
                case 7:
                  System.out.println("Exiting of editing!");
                  return;
                default:
                  break;
              }

              System.out.println(); // Space so that menu looks better

            } catch(InputMismatchException e) {
              System.out.println("Error: Please type a valid option.");
              input.nextLine();
            }

          } while (true);


        } else {
          System.out.println("Book has not been found!");
        }


  }

  public static void deleteBook(Scanner input) {
    if(emptyCollection(bookCollection)) {
      System.out.println("Your collection is empty!");
      return;
    }

     showBooks(bookCollection);

      input.nextLine();
      int bookSelected = Integer.parseInt(validateInput(input, "What book you'd like to delete. Type the number: ", "[1-9]\\d*"));

      do{
        if(!bookExistsByKey(bookSelected, bookCollection)) {
          bookSelected = Integer.parseInt(validateInput(input, "Error type a valid number: ", "[1-9]\\d*"));
        }
      }while(!bookExistsByKey(bookSelected, bookCollection));

      bookCollection.remove(bookSelected);
      System.out.println("Book deleted successfully!");

    // Reorganizar las claves restantes en el LinkedHashMap
    Map<Integer, Book> newCollection = new LinkedHashMap<>();

    int newKey = 1;
    for(Map.Entry<Integer, Book> entry : bookCollection.entrySet()) {
        newCollection.put(newKey++, entry.getValue());
    }
    // Actualizar bookCollection con el nuevo LinkedHashMap reorganizado
    bookCollection = newCollection;
  }

  public static void showMyCollection() {
    if(emptyCollection(bookCollection)) {
      System.out.println("Your collection is empty!");
      return;
    }

    showBooks(bookCollection);
  }

  public static void searchBook(Scanner input) {
    if(emptyCollection(bookCollection)) {
      System.out.println("Your collection is empty!");
      return;
    }

      int optionSelected;
      do {
        System.out.println("Would you like to search by: ");
        System.out.println("1.- Author");
        System.out.println("2.- Title");
        System.out.println("3.- Read");
        System.out.println("4.- No read");
        System.out.println("5.- Exit");

        System.out.println("Please select type an option: ");
        optionSelected = input.nextInt();

        try{
          input.nextLine();

          switch (optionSelected) {
            case 1:
              String searchAuthor = validateInput(input, "Type the name of the Author:  ", "[a-zA-Z\\s,.\\-'']+");

              if (searchByAuthor(searchAuthor, bookCollection)) {
                for (Map.Entry<Integer, Book> entry : bookCollection.entrySet()) {
                  Book book = entry.getValue();

                  if (entry.getValue().getAuthor().equalsIgnoreCase(searchAuthor)) {
                    Integer index = entry.getKey();

                    System.out.println("------(" + index + ")------");
                    System.out.println("Title: " + book.getTitle());
                    System.out.println("Author: " + book.getAuthor());
                    System.out.println("Genre: " + book.getGenre());
                    System.out.println("Editorial: " + book.getEditorial());
                    System.out.println("Volume " + book.getVolume() + " of " + book.gettotalOfvolumes());
                    System.out.println(book.isReadIt());
                    System.out.println("------ 🐱‍👤 ------");
                  }
                }
                break;
              } else {
                System.out.println("This Author does not exist in your collection");
                break;
              }

            case 2:
              String searchTitle = validateInput(input, "Type the title:  ", "[a-zA-Z\\s,.\\-'']+");

              if (searchByTitle(searchTitle, bookCollection)) {
                for (Map.Entry<Integer, Book> entry : bookCollection.entrySet()) {
                  Book book = entry.getValue();

                  if (entry.getValue().getTitle().equalsIgnoreCase(searchTitle)) {
                    Integer index = entry.getKey();

                    System.out.println("------(" + index + ")------");
                    System.out.println("Title: " + book.getTitle());
                    System.out.println("Author: " + book.getAuthor());
                    System.out.println("Genre: " + book.getGenre());
                    System.out.println("Editorial: " + book.getEditorial());
                    System.out.println("Volume " + book.getVolume() + " of " + book.gettotalOfvolumes());
                    System.out.println(book.isReadIt());
                    System.out.println("------ 🐱‍👤 ------");
                  }
                }
                break;
              } else {
                System.out.println("This title does not exist in your collection");
                break;
              }
            case 3:
              for (Map.Entry<Integer, Book> entry : bookCollection.entrySet()) {
                Book book = entry.getValue();

                if (entry.getValue().isReadIt()) {
                  Integer index = entry.getKey();

                  System.out.println("------(" + index + ")------");
                  System.out.println("Title: " + book.getTitle());
                  System.out.println("Author: " + book.getAuthor());
                  System.out.println("Genre: " + book.getGenre());
                  System.out.println("Editorial: " + book.getEditorial());
                  System.out.println("Volume " + book.getVolume() + " of " + book.gettotalOfvolumes());
                  System.out.println(book);
                  System.out.println("------ 🐱‍👤 ------");
                } else {
                  System.out.println("You do not have any read books! 😭");
                  return;
                }
              }

              break;
            case 4:
              for (Map.Entry<Integer, Book> entry : bookCollection.entrySet()) {
                Book book = entry.getValue();

                if (!entry.getValue().isReadIt()) {
                  Integer index = entry.getKey();
                  String readIt = entry.getValue().toString();

                  System.out.println("------(" + index + ")------");
                  System.out.println("Title: " + book.getTitle());
                  System.out.println("Author: " + book.getAuthor());
                  System.out.println("Genre: " + book.getGenre());
                  System.out.println("Editorial: " + book.getEditorial());
                  System.out.println("Volume " + book.getVolume() + " of " + book.gettotalOfvolumes());
                  System.out.println(readIt);
                  System.out.println("------ 🐱‍👤 ------");
                } else {
                  System.out.println("You don't have unread books!");
                  return;
                }
              }
              break;

              case 5:
              System.out.println("Exiting of the searching!");
              return;
            default:
              break;
          }
          System.out.println();

        }catch(InputMismatchException e) {
          System.out.println("Error: Please type a valid option.");
          input.nextLine();
        }
      } while(true);

  }

  public static void markAsRead(Scanner input) {
    if(emptyCollection(bookCollection)) {
      System.out.println("Your collection is empty!");
      return;
    }

    showBooks(bookCollection);

      input.nextLine();
      int bookSelected = Integer.parseInt(validateInput(input, "What book you would like to mark as read. Type one: ", "[1-9]\\d*"));


      do{
        if(!bookExistsByKey(bookSelected, bookCollection)) {
          bookSelected = Integer.parseInt(validateInput(input, "Error type a valid number: ", "[1-9]\\d*"));
        }
      }while(!bookExistsByKey(bookSelected, bookCollection));

      Book item = bookCollection.get(bookSelected);
      item.setReadIt(true);

      System.out.println("'" + item.getTitle() + "'" + " mark as read successfully!");

  }

}
