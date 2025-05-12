package PracticalExercises.DigitalLibrary.Service;

import PracticalExercises.DigitalLibrary.Model.Publication;
import PracticalExercises.DigitalLibrary.Persistence.PublicationRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class LibraryCatalogService {
    private final PublicationRepository repository;

    public LibraryCatalogService(PublicationRepository repository) {
        this.repository = repository;
    }

    public void registerPublication(Publication publication){
        if(repository.findById(publication.getId()).isPresent()){
            throw new IllegalArgumentException("Publication with id " + publication.getId() + " already exists");}
        repository.save(publication);
    }

    public Collection<Publication> getAllPublications(){
        return repository.findAll();
    }

    public List<Publication> searchByTitle(String title){
        return repository.findAll().stream()
                .filter(p -> p.getTitle().contains(title))
                .toList();
    }

    public List<Publication> searchByAuthor(String author){
        return repository.findAll().stream()
                .filter(p -> p.getAuthor().contains(author))
                .toList();
    }

    public List<Publication> searchByYearRange(LocalDate releaseDate){
        return repository.findAll().stream()
                .filter(p -> p.getReleaseDate().isEqual(releaseDate))
                .toList();
    }

    public void deletePublication(Publication publication){
        repository.delete(publication);
    }

    public void updatePublication(Publication updatedPublication){
        repository.update(updatedPublication);
    }

    public List<Publication> getTopPublications(int limit){
        return repository.findAll().stream()
                .sorted(Comparator.comparingInt(Publication::getBorrowCount).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public void borrowPublication(String id){
        Publication publication = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Publication not found")
                );

        if(publication.isBorrowed()){
            throw new IllegalStateException("Publication is borrowed");
        }

        publication.setBorrowed(true);
        repository.update(publication);
    }
}
