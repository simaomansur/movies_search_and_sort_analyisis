import java.util.InputMismatchException;
import java.util.Scanner;

class ManagerMode {
    public static void managerMenu(MovieList movieList) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Manager Mode");
            System.out.println("1. Add Movie");
            System.out.println("2. Remove Movie");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                String title, leadActor, genre;

                // Ensure non-empty title
                while (true) {
                    System.out.print("Enter Movie Title: ");
                    title = scanner.nextLine().trim();
                    if (!title.isEmpty()) break;
                    System.out.println("Movie title cannot be empty.");
                }

                // Ensure non-empty lead actor
                while (true) {
                    System.out.print("Enter Lead Actor: ");
                    leadActor = scanner.nextLine().trim();
                    if (!leadActor.isEmpty()) break;
                    System.out.println("Lead actor cannot be empty.");
                }

                // Validate release year input
                int year = 0;
                boolean validInput = false;
                while (!validInput) {
                    System.out.print("Enter Release Year: ");
                    try {
                        year = scanner.nextInt();
                        if (year > 1880 && year <= 2024) {
                            validInput = true; // Exit the loop if input is valid
                        } else {
                            System.out.println("Please enter a valid year between 1880 and 2024.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number for the year.");
                        scanner.nextLine();  // Clear the invalid input
                    }
                }
                scanner.nextLine(); // consume newline after entering the year

                // Ensure non-empty genre
                while (true) {
                    System.out.print("Enter Genre: ");
                    genre = scanner.nextLine().trim();
                    if (!genre.isEmpty()) break;
                    System.out.println("Genre cannot be empty.");
                }

                movieList.addMovie(new Movie(title, leadActor, year, genre));
                System.out.println("Movie Added.");
            } else if (choice == 2) {
                System.out.print("Enter Movie Title to Remove: ");
                String title = scanner.nextLine();
                movieList.removeMovie(title);
                System.out.println("Movie Removed.");
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }
}