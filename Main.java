import java.util.Scanner;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        String csvFilePath = "movies.csv";  // Specify the path to your CSV file

        // Create a MovieList object
        MovieList movieList = new MovieList(csvFilePath);
        
        // Load movie data from the CSV file
        movieList.loadMoviesFromCSV();

        // Convert the list to an array for comparison
        Movie[] movieArray = new Movie[movieList.getAllMovies().size()];
        movieArray = movieList.getAllMovies().toArray(movieArray);

        // Convert the list to linked list for comparison
        LinkedList<Movie> movieLinkedList = new LinkedList<>(Arrays.asList(movieArray));

        Scanner scanner = new Scanner(System.in);
        boolean validMode = false;

        // Keep prompting the user until they choose a valid mode
        while (!validMode) {
            System.out.println("Choose mode:");
            System.out.println("1. Manager Mode");
            System.out.println("2. User Mode");
            int mode = scanner.nextInt();
            scanner.nextLine();  // consume newline

            if (mode == 1) {
                // Prompt for manager password
                System.out.print("Enter Manager Password: ");
                String password = scanner.nextLine().trim();

                // Hardcoded password for demonstration purposes
                if (password.equals("123")) {
                    validMode = true;
                    ManagerMode.managerMenu(movieList);
                } else {
                    System.out.println("Invalid password. Try again.");
                }
            } else if (mode == 2) {
                validMode = true;
                UserMode.userMenu(movieList, movieArray, movieLinkedList);
            } else {
                System.out.println("Invalid choice. Please select 1 for Manager Mode or 2 for User Mode.");
            }
        }

        scanner.close(); // Ensure we close the scanner at the end
    }
}