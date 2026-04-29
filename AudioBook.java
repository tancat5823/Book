import java.util.ArrayList;
import java.util.List;

/**
 * OOP Concept: Inheritancy, this class extends the book class, its parent class,
 * and inherits all of its fields and methods
 *
 * This class is used to store the data of audio books
 */
public class AudioBook extends Book {
    //fields
    //OOP Concept Encapsulation, pages is private and can only be accessed from outside the class by getDuration()
    private int duration;

    //tracks the last three AudioBook objects
    private static List<AudioBook> lastThreeAudioBooks = new ArrayList<>();

    //OOP Concept: Inheritancy, super initializes fields shared with parent class
    //constructor
    public AudioBook(String title, String author, String genre, String bookType, int duration, double cost){
        //get fields from parent class
        super(title, author, genre, "Audio", cost);
        //duration is defined in this class
        this.duration = duration;

        //keeps the last three audio books so we can print them
        if (lastThreeAudioBooks.size() >= 3){
            lastThreeAudioBooks.remove(0);
        }
        lastThreeAudioBooks.add(this);
    }

    //getters and setters
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public static List<AudioBook> getLastThree() {
        return lastThreeAudioBooks;
    }

    //OOP Concept: Polymorphism, this extends the parents toString() to add pages, it also
    //calls the parents to string to avoid duplicate code
    @Override
    public String toString(){
        return super.toString() + ", Duration: " + getDuration() + ", Cost $" + getCost();
    }

    //OOP Concept: Polymorphism, get cost is defined here with duration, when we call get
    //cost on a book pointing to audio book, java will call this version of the function
    @Override
    public double getCost(){
        return duration * 0.25;
    }

    //method to compute the average pages of all the stored audio books
    public static double averageDuration(){
        //list of audio books
        List<AudioBook> list = new ArrayList<>();

        //iterate over books
        for(Book book: Book.books){
            //if the book is a audio book
            if (book instanceof AudioBook){
                //we add it to the list of audio books
                list.add((AudioBook) book);
            }
        }

        //return the list of printed books
        return list.stream().mapToInt(AudioBook :: getDuration).average().orElse(0);
    }

    //method to display the last three audiobooks
    public static void displayLastThree(){
        for(AudioBook book: lastThreeAudioBooks){
            System.out.println(book);
        }
    }

}
