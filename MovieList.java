import java.io.*;
import java.util.ArrayList;
import java.util.List;

class MovieList {
    List<Movie> movies = new ArrayList<>();
    List<Movie> originalMovies = new ArrayList<>(); // Store original list
    String csvFilePath;

    // Constructor to initialize the CSV file path
    public MovieList(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    // Method to add a movie to the list and save it to the CSV file
    public void addMovie(Movie movie) {
        movies.add(movie);  // Add to the in-memory list
        originalMovies.add(movie); // Also add to the original copy

        // Append the new movie to the CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath, true))) {
            writer.write(movie.title + "," + movie.leadActor + "," + movie.releaseYear + "," + movie.genre);
            writer.newLine();  // Write a new line after each movie
            System.out.println("Movie added and saved to CSV.");
        } catch (IOException e) {
            System.out.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    // Method to remove a movie from the list and update the CSV file
    public void removeMovie(String title) {
        boolean removed = movies.removeIf(movie -> movie.title.equalsIgnoreCase(title));

        if (removed) {
            // Rewrite the entire CSV file excluding the removed movie
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
                // Write the header
                writer.write("Title,Lead Actor,Release Year,Genre");
                writer.newLine();
                
                // Write the remaining movies
                for (Movie movie : movies) {
                    writer.write(movie.title + "," + movie.leadActor + "," + movie.releaseYear + "," + movie.genre);
                    writer.newLine();
                }
                System.out.println("Movie removed and CSV updated.");
            } catch (IOException e) {
                System.out.println("Error writing to CSV file: " + e.getMessage());
            }
        } else {
            System.out.println("Movie not found.");
        }
    }

    // Get all movies
    public List<Movie> getAllMovies() {
        return movies;
    }

    // Revert the movie list back to its original state
    public void revertMovies() {
        movies = new ArrayList<>(originalMovies);
    }

    // Load movies from CSV while ignoring the first line (header) and trimming inputs
    public void loadMoviesFromCSV() {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip header
                }
                String[] values = line.split(",");
                if (values.length == 4) {
                    String title = values[0].trim();
                    String leadActor = values[1].trim();
                    int releaseYear = Integer.parseInt(values[2].trim());
                    String genre = values[3].trim();
                    Movie movie = new Movie(title, leadActor, releaseYear, genre);
                    movies.add(movie);
                    originalMovies.add(movie); // Add to the original copy as well
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the CSV file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing movie data from the file. Ensure the data is correctly formatted.");
        }
    }
}