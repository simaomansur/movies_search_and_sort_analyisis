import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

class SearchAlgorithms {

    // Linear Search
    public static List<Movie> linearSearch(MovieList movieList, String keyword) {
        List<Movie> results = new ArrayList<>();
        String lowerCaseKeyword = keyword.toLowerCase(); // Case-insensitive

        for (Movie movie : movieList.getAllMovies()) {
            if (movie.title.toLowerCase().contains(lowerCaseKeyword) ||  
                movie.leadActor.toLowerCase().contains(lowerCaseKeyword) ||  
                Integer.toString(movie.releaseYear).contains(lowerCaseKeyword) ||  
                movie.genre.toLowerCase().contains(lowerCaseKeyword)) {
                results.add(movie);
            }
        }
        return results;
    }

    // Binary Search
    public static List<Movie> binarySearch(List<Movie> movies, String keyword, int sortOption) {
        int low = 0;
        int high = movies.size() - 1;
        List<Movie> results = new ArrayList<>();
        keyword = keyword.toLowerCase();

        while (low <= high) {
            int mid = (low + high) / 2;
            Movie movie = movies.get(mid);
            String fieldToCompare = "";

            // Ensure the field being compared matches the sortOption used
            switch (sortOption) {
                case 1: fieldToCompare = movie.title.toLowerCase(); break;
                case 2: fieldToCompare = movie.leadActor.toLowerCase(); break;
                case 3: fieldToCompare = Integer.toString(movie.releaseYear); break;
                case 4: fieldToCompare = movie.genre.toLowerCase(); break;
            }

            // Check for match
            if (fieldToCompare.contains(keyword)) {
                results.add(movie);
                // Continue to search for possible duplicates (in case there are multiple matching results)
                int temp = mid - 1;
                while (temp >= 0 && movies.get(temp).title.toLowerCase().contains(keyword)) {
                    results.add(movies.get(temp));
                    temp--;
                }
                temp = mid + 1;
                while (temp < movies.size() && movies.get(temp).title.toLowerCase().contains(keyword)) {
                    results.add(movies.get(temp));
                    temp++;
                }
                break; // Match found, no need to continue
            } else if (fieldToCompare.compareTo(keyword) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return results;
    }

    // Linear Search for Movie[] array
    public static List<Movie> linearSearchArray(Movie[] movieArray, String keyword) {
        List<Movie> results = new ArrayList<>();
        String lowerCaseKeyword = keyword.toLowerCase(); // Case-insensitive search

        for (Movie movie : movieArray) {
            if (movie.title.toLowerCase().contains(lowerCaseKeyword) ||  
                movie.leadActor.toLowerCase().contains(lowerCaseKeyword) ||  
                Integer.toString(movie.releaseYear).contains(lowerCaseKeyword) ||  
                movie.genre.toLowerCase().contains(lowerCaseKeyword)) {
                results.add(movie); // Add matching movie to results list
            }
        }
        return results;
    }


    // Binary Search for Movie[] array
    public static List<Movie> binarySearchArray(Movie[] movieArray, String keyword, int sortOption) {
        int low = 0;
        int high = movieArray.length - 1;
        List<Movie> results = new ArrayList<>();
        keyword = keyword.toLowerCase();

        while (low <= high) {
            int mid = (low + high) / 2;
            Movie movie = movieArray[mid];  // Get the movie at the middle index
            String fieldToCompare = "";

            // Ensure the field being compared matches the sortOption used
            switch (sortOption) {
                case 1: fieldToCompare = movie.title.toLowerCase(); break;
                case 2: fieldToCompare = movie.leadActor.toLowerCase(); break;
                case 3: fieldToCompare = Integer.toString(movie.releaseYear); break;
                case 4: fieldToCompare = movie.genre.toLowerCase(); break;
            }

            // Check for match
            if (fieldToCompare.contains(keyword)) {
                results.add(movie);

                // Continue to search for possible duplicates (in case there are multiple matching results)
                int temp = mid - 1;
                while (temp >= 0 && movieArray[temp].title.toLowerCase().contains(keyword)) {
                    results.add(movieArray[temp]);
                    temp--;
                }
                temp = mid + 1;
                while (temp < movieArray.length && movieArray[temp].title.toLowerCase().contains(keyword)) {
                    results.add(movieArray[temp]);
                    temp++;
                }
                break; // Match found, no need to continue
            } else if (fieldToCompare.compareTo(keyword) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return results;
    }

    // Linear search for LinkedList<Movie>
    public static List<Movie> linearSearchLinkedList(LinkedList<Movie> movieList, String keyword) {
        List<Movie> results = new ArrayList<>();
        String lowerCaseKeyword = keyword.toLowerCase(); // Case-insensitive search

        // Iterate through the LinkedList of movies
        for (Movie movie : movieList) {
            if (movie.title.toLowerCase().contains(lowerCaseKeyword) ||  
                movie.leadActor.toLowerCase().contains(lowerCaseKeyword) ||  
                Integer.toString(movie.releaseYear).contains(lowerCaseKeyword) ||  
                movie.genre.toLowerCase().contains(lowerCaseKeyword)) {
                results.add(movie); // Add matching movie to results list
            }
        }
        return results;
    }

    // Helper method to get the element at a specific index in a LinkedList
    private static Movie getElementAtIndex(LinkedList<Movie> movieList, int index) {
        ListIterator<Movie> iterator = movieList.listIterator();
        for (int i = 0; i < index; i++) {
            iterator.next();  // Move to the desired index
        }
        return iterator.next();  // Return the movie at the index
    }

    // Binary Search for LinkedList without converting to ArrayList
    public static List<Movie> binarySearchLinkedList(LinkedList<Movie> movieList, String keyword, int sortOption) {
        int low = 0;
        int high = movieList.size() - 1;
        List<Movie> results = new LinkedList<>();
        keyword = keyword.toLowerCase();

        while (low <= high) {
            int mid = (low + high) / 2;

            // Use the helper method to get the movie at index `mid` in the LinkedList
            Movie movie = getElementAtIndex(movieList, mid);
            String fieldToCompare = "";

            // Ensure the field being compared matches the sortOption used
            switch (sortOption) {
                case 1: fieldToCompare = movie.title.toLowerCase(); break;
                case 2: fieldToCompare = movie.leadActor.toLowerCase(); break;
                case 3: fieldToCompare = Integer.toString(movie.releaseYear); break;
                case 4: fieldToCompare = movie.genre.toLowerCase(); break;
            }

            // Check for match
            if (fieldToCompare.contains(keyword)) {
                results.add(movie);

                // Continue to search for possible duplicates
                int temp = mid - 1;
                while (temp >= 0) {
                    Movie tempMovie = getElementAtIndex(movieList, temp);
                    if (tempMovie.title.toLowerCase().contains(keyword)) {
                        results.add(tempMovie);
                    }
                    temp--;
                }
                temp = mid + 1;
                while (temp < movieList.size()) {
                    Movie tempMovie = getElementAtIndex(movieList, temp);
                    if (tempMovie.title.toLowerCase().contains(keyword)) {
                        results.add(tempMovie);
                    }
                    temp++;
                }
                break; // Match found, no need to continue
            } else if (fieldToCompare.compareTo(keyword) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return results;
    }
}