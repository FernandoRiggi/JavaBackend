package PracticalExercises.FirstPractical;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Post {
    private String quote;
    private LocalDate date;
    private int claps;
    private int boos;
    private final UserAccount user;


    public Post(UserAccount user, String quote) {
        this.user = user;
        this.date = LocalDate.now();
        this.quote = quote;
        this.claps = 0;
        this.boos = 0;
    }

    public String show(){
        return String.format("Date: [%s] %s says %s Claps: %s Boos: %s \n" ,
                date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                user.getUserName(), quote, claps, boos);
    }

    public void clap(){
        this.claps++;
    }

    public void boo(){
        this.boos++;
    }
    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }


    public LocalDate getDate() {
        return date;
    }

}
