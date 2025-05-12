package PracticalExercises.DigitalLibrary.Model;

import java.time.LocalDate;

public final class AcademicPaper extends Publication {
    private final String university;

    public AcademicPaper(String id, String title, LocalDate releaseDate, String author, int borrowCount, int pages, boolean borrowed, String university) {
        super(id, title, releaseDate, author, borrowCount, pages, borrowed);
        this.university = university;
    }

    @Override
    public String getCategory() {
        return "Academic Paper - " + university;
    }
}
