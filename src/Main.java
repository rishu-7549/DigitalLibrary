import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.*;

// Book class to store book details
class Book {
    private final String bookId;
    private String title;
    private String author;
    private String genre;
    private String availability;

    public Book(String bookId, String title, String author, String genre, String availability) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.setAvailability(availability);
    }

    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getGenre() { return genre; }

    public void setGenre(String genre) { this.genre = genre; }

    public String getAvailability() { return availability; }
    public void setAvailability(String availability) {
        if (availability.equalsIgnoreCase("Available") || availability.equalsIgnoreCase("Checked Out")) {
            this.availability = availability;
        } else {
            throw new IllegalArgumentException("Invalid availability status. Must be 'Available' or 'Checked Out'.");
        }
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Genre: " + genre + ", Availability: " + availability;
    }
}


class Lib {
    private final Map<String, Book> books = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    public void addBook() {
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine().trim();
        if (books.containsKey(bookId)) {
            System.out.println("Book ID already exists!");
            return;
        }

        System.out.print("Enter Title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine().trim();
        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine().trim();
        System.out.print("Enter Availability (Available/Checked Out): ");
        String availability = scanner.nextLine().trim();

        try {
            Book book = new Book(bookId, title, author, genre, availability);
            books.put(bookId, book);
            System.out.println("Book added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            books.values().forEach(System.out::println);
        }
    }

    public void searchBook() {
        System.out.print("Search by (1) ID or (2) Title: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.print("Enter Book ID: ");
            String bookId = scanner.nextLine().trim();
            if (books.containsKey(bookId)) {
                System.out.println(books.get(bookId));
            } else {
                System.out.println("Book not found!");
            }
        } else if (choice == 2) {
            System.out.print("Enter Title: ");
            String title = scanner.nextLine().trim();
            boolean found = false;
            for (Book book : books.values()) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    System.out.println(book);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("Book not found!");
            }
        } else {
            System.out.println("Invalid choice.");
        }
    }

    public void updateBook() {
        System.out.print("Enter Book ID to update: ");
        String bookId = scanner.nextLine().trim();
        if (!books.containsKey(bookId)) {
            System.out.println("Book not found!");
            return;
        }
        Book book = books.get(bookId);
        System.out.println("Updating details for: " + book);

        System.out.print("New Title (or press Enter to keep unchanged): ");
        String newTitle = scanner.nextLine().trim();
        if (!newTitle.isEmpty()) book.setTitle(newTitle);

        System.out.print("New Author (or press Enter to keep unchanged): ");
        String newAuthor = scanner.nextLine().trim();
        if (!newAuthor.isEmpty()) book.setAuthor(newAuthor);

        System.out.print("New Availability (Available/Checked Out): ");
        String newAvailability = scanner.nextLine().trim();
        try {
            book.setAvailability(newAvailability);
            System.out.println("Book updated successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteBook() {
        System.out.print("Enter Book ID to delete: ");
        String bookId = scanner.nextLine().trim();
        if (books.remove(bookId) != null) {
            System.out.println("Book deleted successfully!");
        } else {
            System.out.println("Book not found!");
        }
    }

    public void start() {
        while (true) {
            System.out.println("\n1. Add Book\n2. View All Books\n3. Search Book\n4. Update Book\n5. Delete Book\n6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> viewAllBooks();
                case 3 -> searchBook();
                case 4 -> updateBook();
                case 5 -> deleteBook();
                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option, please try again.");
            }
        }
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        Lib system = new Lib();
        system.start();
    }
}
