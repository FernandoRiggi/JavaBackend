package PracticalExercises.DigitalLibrary.Model;

import java.time.LocalDate;

public final class Magazine extends Publication {
    private final int issueNumber;

    public Magazine(String id, String title, LocalDate releaseDate, String author, int borrowCount, int pages, boolean borrowed, int issueNumber) {
        super(id, title, releaseDate, author, borrowCount, pages, borrowed);
        this.issueNumber = issueNumber;
    }

    @Override
    public String getCategory() {
        return "Magazine- Issue #" + issueNumber;
    }
}
