package PracticalExercises.DigitalLibrary.Service;

import PracticalExercises.DigitalLibrary.Model.Publication;
import PracticalExercises.DigitalLibrary.Persistence.PublicationRepository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LibraryReportService {
    private final PublicationRepository repository;

    public LibraryReportService(PublicationRepository repository) {
        this.repository = repository;
    }

    public long getTotalPublications(){
        return repository.findAll().size();
    }

    public Map<String, Long> getPublicationCountByCategory(){
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(Publication::getCategory, Collectors.counting()));
    }

    public Map<String, Long> getBorrowedAndAvailableCount() {
        long borrowed = repository.findAll().stream()
                .filter(Publication::isBorrowed)
                .count();

        long available = repository.findAll().size() - borrowed;

        Map<String, Long> report = new HashMap<>();
        report.put("Borrowed", borrowed);
        report.put("Available", available);

        return report;
    }

    public List<Publication> getMostRecentPublications(int limit){
        return repository.findAll().stream()
                .sorted(Comparator.comparing(Publication::getReleaseDate).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<Publication> getMostBorrowedPublications(int limit){
        return repository.findAll().stream()
                .sorted(Comparator.comparing(Publication::getBorrowCount).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public Map<String, Long> getPublicationCountByAuthor(){
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(Publication::getAuthor, Collectors.counting()));
    }

    public double getBorrowedRate(){
        long borrowed = repository.findAll().stream()
                .filter(Publication::isBorrowed)
                .count();
        long total = repository.findAll().size();

        return (double) borrowed / total * 100;
    }
}
