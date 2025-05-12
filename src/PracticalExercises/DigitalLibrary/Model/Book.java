package PracticalExercises.DigitalLibrary.Model;

import java.time.LocalDate;

public final class Book extends Publication {
    private final String genre;

    public Book(String id, String title, LocalDate releaseDate, String author, int borrowCount, int pages, boolean borrowed, String genre) {
        super(id, title, releaseDate, author, borrowCount, pages, borrowed);
        this.genre = genre;
    }

    @Override
    public String getCategory() {
        return "Book - Genre: " + genre;
    }
}
