import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

public class BookTest {
    public static void main(String[] args) {
        //Create book application to call user facing methods
        BookApplication app = new BookApplication();
        //create scanner to get user inputs
        Scanner keyboard = new Scanner(System.in);
        //field to catch user choice
        int option;
        //fields
        String title, author, genre, type;

        //do loop to perform function while user doesnt want to exit
        do{
            //Interface for the user to select what type of function to choose
            //functions are broken up to more manageable sections since there are so many
            System.out.println("----------------------------------");
            System.out.println("         Select A Function        ");
            System.out.println("----------------------------------");
            System.out.println("0.............................Exit");
            System.out.println("1..........................Display");
            System.out.println("2...........................Search");
            System.out.println("3.............................Edit");

            //gets option from user
            try {
                option = keyboard.nextInt();
                keyboard.nextLine();
            } catch(InputMismatchException e){
                System.out.println("Invalid input: please enter a number.");

                //clear bad input
                keyboard.nextLine();

                //set to invalid input to reset loop
                option = -1;
            }

            //switch case to execute the code the user wants
            switch(option){
                case 0:
                    //exit
                    System.out.println("Exiting");
                    break;
                case 1:
                    //Interface for the user to select what information they want to display
                    System.out.println("----------------------------------");
                    System.out.println("  What Would You Like To Display  ");
                    System.out.println("----------------------------------");
                    System.out.println("1................Display All Books");
                    System.out.println("2..........Display Books Per Genre");
                    System.out.println("3............Display Average Pages");
                    System.out.println("4.........Display Average Duration");
                    System.out.println("5...............Display Total Cost");
                    System.out.println("6........Display Printed Book Cost");
                    System.out.println("7...........Display Audiobook Cost");
                    System.out.println("8.......Display Last Three Printed");
                    System.out.println("9.........Display Last Three Audio");
                    System.out.println("10..........Display Last Six Books");

                    //get the users option
                    try {
                        option = keyboard.nextInt();
                        keyboard.nextLine();
                    } catch(InputMismatchException e){
                        System.out.println("Invalid input: please enter a number.");

                        //clear bad input
                        keyboard.nextLine();

                        //set to invalid input to reset loop
                        option = -1;
                    }

                    //switch case to execute their option
                    switch(option){
                        case 1:
                            //uses the display all command
                            new PrintedBook("","","","",0,0).displayAll(Book.books);
                            break;
                        case 2:
                            //displays books per genre
                            System.out.println(new PrintedBook("","","","",0,0).numberBooksPerGenre());
                            break;
                        case 3:
                            //displays average pages for printed books
                            System.out.println("Avg pages: " + new PrintedBook("","","","",0,0).averagePrintedPages());
                            break;
                        case 4:
                            //displays average duration for audio books
                            System.out.println("Avg duration: " + new AudioBook("","","","",0,0).averageDuration());
                            break;
                        case 5:
                            //displays the total cost of all books
                            System.out.println("Total cost: " + new PrintedBook("","","","",0,0).getTotalCost());
                            break;
                        case 6:
                            //tracks cost of printed books
                            double printedCost = 0;

                            //iterates over books
                            for(Book book : Book.books)
                                //Add cost if the book is a printed book
                                if(book instanceof PrintedBook)
                                    printedCost += book.getCost();

                            //display cost of all printed books
                            System.out.println("Printed cost: " + printedCost);
                            break;
                        case 7:
                            //tracks cost of audio books
                            double audioCost = 0;

                            //iterate over books
                            for(Book book : Book.books)
                                //add cost if the book is a audio book
                                if(book instanceof AudioBook)
                                    audioCost += book.getCost();

                            //display cost of all audio books
                            System.out.println("Audio cost: " + audioCost);
                            break;
                        case 8:
                            //display the last three printed books
                            PrintedBook.displayLastThree();
                            break;
                        case 9:
                            //display the last three audio books
                            AudioBook.displayLastThree();
                            break;
                        case 10:
                            //display last six books
                            Book.getLastSix().forEach(System.out::println);
                            break;
                        default:
                            //if the user inputs an invalid value
                            System.out.println("Invalid choice, please try again.");
                            break;
                    }
                    break;
                case 2:
                    //Interface for the user to select what to search
                    System.out.println("----------------------------------");
                    System.out.println("   What Would You Like To Search  ");
                    System.out.println("----------------------------------");
                    System.out.println("1.....................Search Title");
                    System.out.println("2....................Search Author");
                    System.out.println("3.....................Search Genre");
                    System.out.println("4......................Search Type");

                    //get the user option
                    try {
                        option = keyboard.nextInt();
                        keyboard.nextLine();
                    } catch(InputMismatchException e){
                        System.out.println("Invalid input: please enter a number.");

                        //clear bad input
                        keyboard.nextLine();

                        //set to invalid input to reset loop
                        option = -1;
                    }

                    //execute their option
                    switch(option){
                        case 1:
                            //get the title to search
                            System.out.println("Enter the title you would like to search:");
                            title = keyboard.nextLine();

                            //search for the title
                            app.searchTitle(Book.books, title);
                            break;
                        case 2:
                            //get the author to search
                            System.out.println("Enter the author you would like to search:");
                            author = keyboard.nextLine();

                            //search for the author
                            app.searchAuthor(Book.books, author);
                            break;
                        case 3:
                            //get the genre to search
                            System.out.println("Enter the genre you would like to search:");
                            genre = keyboard.nextLine();

                            //search genre
                            app.searchGenre(Book.books, genre);
                            break;
                        case 4:
                            //get the type to search
                            System.out.println("Enter the type of book you would like to search:");
                            type = keyboard.nextLine();

                            //search type
                            app.searchByType(Book.books, type);
                            break;
                        default:
                            //if the user puts an invalid option
                            System.out.println("Invalid choice, please try again.");
                            break;
                    }
                    break;
                case 3:
                    //Interface displaying the functions the user can perform on the file
                    System.out.println("----------------------------------");
                    System.out.println("  Select A File Handling Function ");
                    System.out.println("----------------------------------");
                    System.out.println("1.......................Add A Book");
                    System.out.println("2.......................Sort Books");
                    System.out.println("3........................Save Data");
                    System.out.println("4........................Load Data");

                    //get user option
                    try {
                        option = keyboard.nextInt();
                        keyboard.nextLine();
                    } catch(InputMismatchException e){
                        System.out.println("Invalid input: please enter a number.");

                        //clear bad input
                        keyboard.nextLine();

                        //set to invalid input to reset loop
                        option = -1;
                    }

                    //execute user option
                    switch(option) {
                        case 1:
                            //add a book
                            app.addBook();
                            break;
                        case 2:
                            //sort books
                            BookApplication.sortBooks();
                            break;
                        case 3:
                            //get the gile name
                            System.out.println("What is the name of the file you would like to save?");
                            String saveFilename = keyboard.nextLine();

                            //save the file
                            app.saveToFile(saveFilename);
                            break;
                        case 4:
                            //get the file name
                            System.out.println("What is the file name you would like to load?");
                            String loadFilename = keyboard.nextLine();

                            //load the file
                            app.loadFromFile(loadFilename);
                            break;
                        default:
                            //if the users input is invalid
                            System.out.println("Invalid choice, please try again.");
                            break;
                    }
                    break;
            }
        }while(option != 0);
    }
}