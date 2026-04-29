import java.io.*;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Handles all the user operations relating to book objects like sorting, adding, searching etc.
 *
 * OOP Concept: Polymorphism, each method accepts the book list which is the parent class
 * at runtime the java picks the specific subtype needed, printed book or audiobook
 */
public class BookApplication {
    //Search for books containg a string the user wants to find
    public void searchTitle(List<Book> books, String title){
        //iterate over books
        for(Book book: books){
            //if the book contains the user string, we print it
            //to lowercase is used so that searching isnt case sensitive
            if(book.getTitle().toLowerCase().contains(title.toLowerCase())){
                //OOP concept: Polymorphism, we call book and at runtime java calls the
                //appropriate tostring either printed or audio book
                System.out.println(book);
            }
        }
    }

    //Search for all books by an author
    public void searchAuthor(List<Book> books, String author){
        //iterate over book objects
        for(Book book: books){
            //if the book author equals the user input, print the book information
            if(book.getAuthor().equalsIgnoreCase(author)){
                //OOP concept: Polymorphism, we call book and at runtime java calls the
                //appropriate tostring either printed or audio book
                System.out.println(book);
            }
        }
    }

    //Search for all books in a genre
    public void searchGenre(List<Book> books, String genre){
        //Iterate over book objects
        for(Book book: books){
            //look for matches of the genre and print if its there
            if(book.getGenre().equalsIgnoreCase(genre)){
                //OOP concept: Polymorphism, we call book and at runtime java calls the
                //appropriate tostring either printed or audio book
                System.out.println(book);
            }
        }
    }

    //search for audiobooks or printed books
    public void searchByType(List<Book> books, String type){
        //Iterate over book objects
        for(Book book: books){
            //look for matches of type and print if there is a match
            if(book.getBookType().equalsIgnoreCase(type)){
                //OOP concept: Polymorphism, we call book and at runtime java calls the
                //appropriate tostring either printed or audio book
                System.out.println(book);
            }
        }
    }

    //add a book
    //OOP concept: Encapsulation, hides information gatherin and object construction when called
    public void addBook(){
        //scanner for user input
        Scanner keyboard = new Scanner(System.in);
        //boolean to run while loop under a specific condition
        boolean checker = true;
        while(checker) {
            //gather the information for a book
            System.out.println("Enter title:");
            String title = keyboard.nextLine();

            System.out.println("Enter author:");
            String author = keyboard.nextLine();

            System.out.println("Enter genre:");
            String genre = keyboard.nextLine();

            System.out.println("Enter type (Printed or Audio):");
            String type = keyboard.nextLine();

            //figure out if we are creating a printed book or an audio book
            if (type.equalsIgnoreCase("Printed")) {
                //pages are required for printed
                System.out.println("Enter pages:");
                int pages = 0;

                //Only true when pages is assigned
                boolean validInput = false;

                //loop to make sure user correctly inputs pages
                while(!validInput) {
                    try {
                        pages = keyboard.nextInt();
                        keyboard.nextLine();
                        validInput = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input: please enter a number.");
                        keyboard.nextLine();
                    }
                }
                validInput = false;

                //use the constructor to make a new printed book object
                //OOP concept: inheritence and polymorphism, printedbook is created and stored in the book reference
                PrintedBook printedBook = new PrintedBook(title, author, genre, type, pages, pages * 0.5);
                //add the printed book to list of books
                Book.books.add(printedBook);
                //ends loop
                checker = false;
            } else if (type.equalsIgnoreCase("Audio")) {
                //duration is needed to make an audiobook
                System.out.println("Enter duration:");
                int duration = 0;

                //make sure user correctly inputs duration
                boolean validInput= false;
                while(!validInput) {
                    try {
                        duration = keyboard.nextInt();
                        keyboard.nextLine();
                        validInput = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input: please enter a number.");
                        keyboard.nextLine();
                    }
                }
                validInput = false;

                //construct a audiobook object
                //OOP concept: inheritence and polymorphism, audiobook is created and stored in the book reference
                AudioBook audioBook = new AudioBook(title, author, genre, type, duration, duration * 0.25);
                Book.books.add(audioBook);
                //end loop
                checker = false;
            } else {
                //if input is not printed or audio book
                System.out.println("Invalid input, try again.");
            }
        }
    }

    //sorts books alphabetically
    public static void sortBooks(){
        Book.books.sort(Comparator.comparing(Book :: getTitle));
        System.out.println("Books sorted by title.");
    }

    //OOP concept: Encapsulation, hides file handling when user calls the method
    //saves the list of books
    public static void saveToFile(String filename){
        //create a new printwriter
        try(PrintWriter printWriter = new PrintWriter(filename)){
            //file header
            printWriter.println("Title,Author,Genre,BookType,Pages,Duration,Cost");

            //iterates over books
            for(Book book: Book.books){
                //writes printed books to the file, else condition handles audiobooks
                if(book instanceof PrintedBook){
                    PrintedBook printedBook = (PrintedBook) book;
                    printWriter.println(printedBook.getTitle() + "," + printedBook.getAuthor() + "," + printedBook.getGenre() + ",Printed," + printedBook.getPages() + ",null," + printedBook.getCost());
                }else{
                    AudioBook audioBook = (AudioBook) book;
                    printWriter.println(audioBook.getTitle() + "," + audioBook.getAuthor() + "," + audioBook.getGenre() + ",Audio,null," + audioBook.getDuration() + "," + audioBook.getCost());
                }
            }
        }catch(Exception e){
            //exception handling
            System.out.println("Error saving file: " + e.getMessage());
        }

    }

    //OOP concept: Encapsulation, hides file handling from user when method is called
    public static void loadFromFile(String filename){
        //create a buffered reader to read the file
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))){
            //read the title line
            String line = bufferedReader.readLine();

            //read all lines containing information
            while ((line = bufferedReader.readLine()) != null){
                //separate lines
                String[] parts = line.split(",");

                //get each field from the file
                String title = parts[0];
                String author = parts[1];
                String genre = parts[2];
                String type = parts[3];

                //get pages from printed book or duration from audiobooks
                if(type.equalsIgnoreCase("Printed")){
                    int pages = Integer.parseInt(parts[4]);
                    PrintedBook printedBook = new PrintedBook(title, author, genre, type, pages, pages * 0.5);
                    Book.books.add(printedBook);
                }else if (type.equalsIgnoreCase("Audio")){
                    int duration = Integer.parseInt(parts[5]);
                    AudioBook audioBook = new AudioBook(title, author, genre, type, duration, duration * 0.25);
                    Book.books.add(audioBook);
                }else{
                    //if there is an issue with the type, file reading will fail
                    System.out.println("Error reading file: Types are not properly defined in the file.");
                }
            }
            System.out.println("File loaded.");
        }catch(NumberFormatException e){
            System.out.println("Error reading file: Invalid Number Format." + e.getMessage());
        }catch(InputMismatchException e){
            //catch an input mismatch
            System.out.println("Error handling file: Input Mismatch" + e.getMessage());
        }catch(Exception e){
            //catch exceptions
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

}
