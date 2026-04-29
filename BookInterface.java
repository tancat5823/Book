import java.util.List;
import java.util.Map;

/**
 * OOP Concept: abstraction, the interface specifies what the lower level classes
 * can, but they still require concrete implementation
 *
 * This is an interface for that defines an abstract method to get the total
 * cost of all books and the number of books by genre, it also defines a default
 * method that displays all the books
 */
public interface BookInterface {

    //default method to display all the books stored in the book application
    //OOP concept: Abstraction, the default method provides the implementation to all classes that inherit from the interface
    //to minimize code duplication
    default void displayAll(List<Book> books){
        //if there are no books we print out that there are no books
        if(books.isEmpty()){
            System.out.println("There are no books stored!");
            return;
        }
        //cycle through books and print their information
        //OOP concept: Polymorphism, each time book is called in the println function, the toString for specific instance
        // of the book class that we are calling is printed. this can call either the printedbook or audiobook classes
        //toString
        for(Book book : books){
            System.out.println(book);
        }
    };

    //abstract method to get the number of books in each genre
    //OOP concept: Abstraction, abstract method in the interface forces inheriting classes to define capabilities for the
    //method, it does not define the function of the method itself but inheriting classes must
    Map<String, Integer> numberBooksPerGenre();

    //Abstract method to get the total cost of printed and audio books
    //OOP concept: Abstraction, abstract method in the interface forces inheriting classes to define capabilities for the
    //method, it does not define the function of the method itself but inheriting classes must
    double getTotalCost();

}
