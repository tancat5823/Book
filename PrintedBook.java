import java.util.ArrayList;
import java.util.List;

/**
 * OOP Concept: Inheritancy, this class extends the book class, its parent class,
 * and inherits all of its fields and methods
 *
 * This class is used to store the data of printed books
 */
public class PrintedBook extends Book{
    //fields
    //OOP Concept Encapsulation, pages is private and can only be accessed from outside the class by getPages()
    private int pages;

    //tracks the last three PrintedBook objects
    private static List<PrintedBook> lastThreeBooks = new ArrayList<>();

    //OOP Concept: Inheritancy, super initializes fields shared with parent class
    //constructor
    public PrintedBook(String title, String author, String genre, String bookType, int pages, double cost) {
        //get fields from parent class
        super(title, author, genre, "Printed", cost);
        //pages are defined in this class
        this.pages = pages;

        //keeps the last three printed books so we can print them
        if (lastThreeBooks.size() >= 3){
            lastThreeBooks.remove(0);
        }
        lastThreeBooks.add(this);
    }

    //getter and setter for pages
    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public static List<PrintedBook> getLastThree(){
        return lastThreeBooks;
    }

    //OOP Concept: Polymorphism, this extends the parents toString() to add pages, it also
    //calls the parents to string to avoid duplicate code
    @Override
    public String toString(){
        return super.toString() + ", Pages: " + getPages() + ", Cost: $" + getCost();
    }

    //OOP Concept: Polymorphism, get cost is defined here with pages, when we call get
    //cost on a book pointing to printed book, java will call this version of the function
    @Override
    public double getCost(){
        return pages * 0.50;
    }

    //method to compute the average pages of all the stored printed books
    public static double averagePrintedPages(){
        //list of printed books
        List<PrintedBook> list = new ArrayList<>();

        //iterate over books
        for(Book book: Book.books){
            //if the book is a printed book
            if(book instanceof PrintedBook){
                //we add it to the list of printed books
                list.add((PrintedBook) book);
            }
        }
        //return the list of printed books
        return list.stream().mapToInt(PrintedBook :: getPages).average().orElse(0);
    }

    //method to display the last three printed books
    public static void displayLastThree(){
        for(PrintedBook book: lastThreeBooks){
            System.out.println(book);
        }
    }

}
