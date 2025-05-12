package PracticalExercises.DigitalLibrary.Model;

import java.time.LocalDate;
import java.util.Objects;

public sealed abstract class Publication permits Book, Magazine, AcademicPaper {
    protected String id;
    protected String title;
    protected String author;
    protected LocalDate releaseDate;
    protected int pages;
    protected int borrowCount;
    protected boolean borrowed;

    public Publication(String id, String title, LocalDate releaseDate, String author, int borrowCount, int pages, boolean borrowed) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.author = author;
        this.borrowCount = borrowCount;
        this.pages = pages;
        this.borrowed = borrowed;
    }

    public String getSummary(){
        return String.format("[%s] %s - %d pages",id, title, pages);
    }

    public abstract String getCategory();

    @Override
    public String toString() {
        return getSummary();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AcademicPaper that = (AcademicPaper) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public int getBorrowCount() {
        return borrowCount;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }
}
