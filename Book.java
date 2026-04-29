import java.io.*;
import java.util.*;

/**
 * OOP Concept: Abstraction, this class is declared as an abstract class and
 * cannot be instantiated
 *
 * OOP Concept: Encaptulation, the fields and methods are bundled into a
 * single space with the book class
 *
 * OOP Concept: Inheritance, this is a parent class with child directories
 * PrintedBook and AudioBook. This class also implements the displayAll()
 * default method from BookInterface
 */
public abstract class Book implements BookInterface{
    //fields
    //OOP concept: Encapsulation, fields are protected so they are accessable to inheriting classes but not to other
    //classes, external code has to use the getter and setter methods to alter these
    protected String title, author, genre, bookType;
    protected double cost, totalCost;

    //List used to store books
    public static List<Book> books = new ArrayList<>();

    //constructor
    //OOP concept: Encapsulation, the constructor defines how books are created, inheriting classes are forced to call
    //super to ensure all the required fields are defined
    public Book(String title, String author, String genre, String bookType, double cost) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookType = bookType;
        this.cost = cost;
    }

    //getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    //tostring used when printing info about books
    //OOP concept: Polymorphism, overides the default tostring to make one that humans can read, subclasses override
    // further altering the tostring
    public String toString(){
        return "Title: " + getTitle() + ", Author: " + getAuthor() + ", Genre: " + getGenre() + ", Type: " + getBookType();
    }

    //overridden method from book interface that gets the total cost of all books
    //OOP Concept: Inheritency, inherited from the bookinterface
    @Override
    public double getTotalCost() {
        //variable to track cost
        double total = 0;
        //iterate over the book objects
        for(Book book: books){
            //Add up the cost
            total += book.getCost();
        }
        //returns the total cost of all stored book objects
        return total;
    }

    //overridden method from book interface used to display the number of books of each genre
    //OOP Concept: Inheritance, overides method from bookinterface
    @Override
    public Map<String, Integer> numberBooksPerGenre() {
        //hash map to store pairs of data, genre and number of books in the genre
        Map<String, Integer> genreCount = new HashMap<>();

        //iterate over the book objects
        for(Book book: books){
            //get the genre
            String g = book.getGenre();
            //add the genre name and increase the paired integer count by 1 or if the genre already exists, increse the
            //count by 1 and do not add a repeat of the genre name
            genreCount.put(g, genreCount.getOrDefault(g, 0) + 1);
        }
        //returns the hash map
        return genreCount;
    }

    //Method to display only the last six books
    public static List<Book> getLastSix(){
        //find the size of the list of books
        int size = books.size();
        //return last 6 books
        return books.subList(Math.max(0, size - 6), size);
    }

    //abstract method to get the cost
    //OOP concept: Abstraction, forces inheriting classes to define a getcost method
    public abstract double getCost();
}
