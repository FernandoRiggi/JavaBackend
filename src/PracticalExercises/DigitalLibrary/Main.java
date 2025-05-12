package PracticalExercises.DigitalLibrary;

import PracticalExercises.DigitalLibrary.Model.AcademicPaper;
import PracticalExercises.DigitalLibrary.Model.Magazine;
import PracticalExercises.DigitalLibrary.Model.Publication;
import PracticalExercises.DigitalLibrary.Model.Book;
import PracticalExercises.DigitalLibrary.Service.LibraryCatalogService;
import PracticalExercises.DigitalLibrary.Service.LibraryReportService;
import PracticalExercises.DigitalLibrary.Persistence.InMemoryPublicationRepository;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InMemoryPublicationRepository publicationRepository = new InMemoryPublicationRepository();
        LibraryCatalogService catalogService = new LibraryCatalogService(publicationRepository);
        LibraryReportService reportService = new LibraryReportService(publicationRepository);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== DIGITAL LIBRARY MENU ===");
            System.out.println("1. Register new publication");
            System.out.println("2. Search publication by title");
            System.out.println("3. List all publications");
            System.out.println("4. Borrow a publication");
            System.out.println("5. Reports");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> registerPublication(scanner, catalogService);
                case 2 -> searchByTitle(scanner, catalogService);
                case 3 -> listAllPublications(catalogService);
                case 4 -> borrowPublication(scanner, catalogService);
                case 5 -> showReports(reportService);
                case 6 -> running = false;
                default -> System.out.println("Invalid option. Try again.");
            }
        }

        System.out.println("Program terminated.");
    }

    private static void registerPublication(Scanner scanner, LibraryCatalogService catalogService) {
        System.out.println("\n=== Register New Publication ===");
        System.out.println("1. Book");
        System.out.println("2. Magazine");
        System.out.println("3. Academic Paper");

        int option = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("Pages: ");
        int pages = scanner.nextInt();
        System.out.print("Release year (e.g., 2023): ");
        int year = scanner.nextInt();
        System.out.print("Release month (1-12): ");
        int month = scanner.nextInt();
        System.out.print("Release day (1-31): ");
        int day = scanner.nextInt();
        scanner.nextLine();

        LocalDate releaseDate = LocalDate.of(year, month, day);

        switch (option) {
            case 1 -> {
                System.out.print("Genre: ");
                String genre = scanner.nextLine();
                Publication book = new Book(id, title, releaseDate, author, 0, pages, false, genre);
                catalogService.registerPublication(book);
            }
            case 2 -> {
                System.out.print("Issue Number: ");
                int issueNumber = scanner.nextInt();
                Publication magazine = new Magazine(id, title, releaseDate, author, 0, pages, false, issueNumber);
                catalogService.registerPublication(magazine);
            }
            case 3 -> {
                System.out.print("University: ");
                String university = scanner.nextLine();
                Publication academicPaper = new AcademicPaper(id, title, releaseDate, author, 0, pages, false, university);
                catalogService.registerPublication(academicPaper);
            }
            default -> System.out.println("Invalid option. Try again.");
        }

        System.out.println("Publication successfully registered!");
    }

    private static void searchByTitle(Scanner scanner, LibraryCatalogService catalogService) {
        System.out.println("\n=== Search Publication By Title ===");
        System.out.print("Enter the title or part of it: ");
        String title = scanner.nextLine();

        var results = catalogService.searchByTitle(title);

        if (results.isEmpty()) {
            System.out.println("No publications found with the given title.");
        } else {
            System.out.println("Results found:");
            results.forEach(System.out::println);
        }
    }

    private static void listAllPublications(LibraryCatalogService catalogService) {
        System.out.println("\n=== List of All Publications ===");
        var publications = catalogService.getAllPublications();

        if (publications.isEmpty()) {
            System.out.println("No publications registered.");
        } else {
            publications.forEach(System.out::println);
        }
    }

    private static void borrowPublication(Scanner scanner, LibraryCatalogService catalogService) {
        System.out.println("\n=== Borrow Publication ===");
        System.out.print("Enter the publication ID: ");
        String id = scanner.nextLine();

        try {
            catalogService.borrowPublication(id);
            System.out.println("Publication successfully borrowed!");
        } catch (Exception e) {
            System.out.println("Error while borrowing publication: " + e.getMessage());
        }
    }

    private static void showReports(LibraryReportService reportService) {
        System.out.println("\n=== Library Reports ===");
        System.out.println("1. Total publications");
        System.out.println("2. Publications by category");
        System.out.println("3. Borrowed and available publications");
        System.out.println("4. Most recent publications");
        System.out.println("5. Most borrowed publications");
        System.out.print("Choose an option: ");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        switch (option) {
            case 1 -> System.out.println("Total registered publications: " + reportService.getTotalPublications());
            case 2 -> {
                System.out.println("Publications by category:");
                reportService.getPublicationCountByCategory().forEach((category, count) ->
                        System.out.println(category + ": " + count));
            }
            case 3 -> {
                System.out.println("Borrowed and available publications:");
                reportService.getBorrowedAndAvailableCount().forEach((status, count) ->
                        System.out.println(status + ": " + count));
            }
            case 4 -> {
                System.out.println("Most recent publications:");
                reportService.getMostRecentPublications(5).forEach(System.out::println);
            }
            case 5 -> {
                System.out.println("Most borrowed publications:");
                reportService.getMostBorrowedPublications(5).forEach(System.out::println);
            }
            default -> System.out.println("Invalid option.");
        }
    }
}
