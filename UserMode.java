import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedList;

class UserMode {
    public static void userMenu(MovieList movieList, Movie[] movieArray, LinkedList<Movie> movieLinkedList) {
        Scanner scanner = new Scanner(System.in);
        Movie[] originalMovieArray = movieArray.clone(); // Create a copy of the original array
        LinkedList<Movie> originalMovieLinkedList = new LinkedList<>(movieLinkedList); // Create a copy of the original linked list

        while (true) {
            int choice = 0;

            while (true) {
                try {
                    System.out.println("User Mode");
                    System.out.println("1. Search Movie");
                    System.out.println("2. Sort Movies");
                    System.out.println("3. Exit");
                    choice = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    if (choice >= 1 && choice <= 3) {
                        break; // valid input, break out of loop
                    } else {
                        System.out.println("Please enter a valid choice (1, 2, or 3).");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // clear invalid input
                }
            }

            // Check if the movie list is empty before proceeding
            if (movieList.getAllMovies().isEmpty()) {
                System.out.println("The movie list is empty. Please add some movies first.");
                continue;
            }

            if (choice == 1) { // Search Movie
                int searchChoice = 0;
                while (true) {
                    try {
                        System.out.println("Choose search algorithm: 1. Linear Search 2. Binary Search");
                        searchChoice = scanner.nextInt();
                        scanner.nextLine(); // consume newline

                        if (searchChoice == 1 || searchChoice == 2) {
                            break; // valid input
                        } else {
                            System.out.println("Please enter 1 or 2.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number (1 or 2).");
                        scanner.nextLine(); // clear invalid input
                    }
                }

                System.out.print("Enter search keyword (title, actor, year, genre): ");
                String keyword = scanner.nextLine().trim();
                while (keyword.isEmpty()) {
                    System.out.print("Please enter a valid non-empty keyword: ");
                    keyword = scanner.nextLine().trim();
                }
                
                // arrayList search result
                List<Movie> results;

                if (searchChoice == 1) {
                    // Perform linear search and time it (array list)
                    long startTime = System.currentTimeMillis();
                    results = SearchAlgorithms.linearSearch(movieList, keyword);
                    long endTime = System.currentTimeMillis();
                    System.out.println("Search with arrayList took: " + (endTime - startTime) + " milliseconds");

                    // Perform linear search and time it (array)
                    long startTimeArray = System.currentTimeMillis();
                    results = SearchAlgorithms.linearSearchArray(movieArray, keyword);
                    long endTimeArray = System.currentTimeMillis();
                    System.out.println("Search with array took: " + (endTimeArray - startTimeArray) + " milliseconds");

                    // Perform linear search and time it (linked list)
                    long startTimeLinkedList = System.currentTimeMillis();
                    results = SearchAlgorithms.linearSearchLinkedList(movieLinkedList, keyword);
                    long endTimeLinkedList = System.currentTimeMillis();
                    System.out.println("Search with linked list took: " + (endTimeLinkedList - startTimeLinkedList) + " milliseconds");

                } else {
                    // Sort the list before performing binary search
                    int sortOption = 0;
                    while (true) {
                        try {
                            System.out.println("Choose the field to sort for binary search: 1. Title 2. Actor 3. Year 4. Genre");
                            sortOption = scanner.nextInt();
                            scanner.nextLine(); // consume newline

                            if (sortOption >= 1 && sortOption <= 4) {
                                break; // valid input
                            } else {
                                System.out.println("Please enter a valid option (1, 2, 3, or 4).");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a number.");
                            scanner.nextLine(); // clear invalid input
                        }
                    }

                    // Sorting the list before running binary search
                    System.out.println("Sorting the list for binary search...");
                    long sortStartTime = System.currentTimeMillis();
                    SortAlgorithms.quickSort(movieList.getAllMovies(), 0, movieList.getAllMovies().size() - 1, sortOption);
                    long sortEndTime = System.currentTimeMillis();
                    System.out.println("Sorting the arrayList took: " + (sortEndTime - sortStartTime) + " milliseconds");

                    // sort the array before running binary search
                    System.out.println("Sorting the array for binary search...");
                    long sortStartTimeArray = System.currentTimeMillis();
                    SortAlgorithms.quickSortArray(movieArray, 0, movieArray.length - 1, sortOption);
                    long sortEndTimeArray = System.currentTimeMillis();
                    System.out.println("Sorting the array took: " + (sortEndTimeArray - sortStartTimeArray) + " milliseconds");

                    // sort the linked list before running binary search
                    System.out.println("Sorting the linked list for binary search...");
                    long sortStartTimeLinkedList = System.currentTimeMillis();
                    SortAlgorithms.quickSortLinkedList(movieLinkedList, 0, movieLinkedList.size() - 1, sortOption);
                    long sortEndTimeLinkedList = System.currentTimeMillis();
                    System.out.println("Sorting the linked list took: " + (sortEndTimeLinkedList - sortStartTimeLinkedList) + " milliseconds");

                    // Perform binary search on the sorted list
                    long startTime = System.currentTimeMillis();
                    results = SearchAlgorithms.binarySearch(movieList.getAllMovies(), keyword, sortOption);
                    long endTime = System.currentTimeMillis();
                    System.out.println("Search with arrayList took: " + (endTime - startTime) + " milliseconds");

                    // Perform binary search on the sorted array
                    long startTimeArray = System.currentTimeMillis();
                    results = SearchAlgorithms.binarySearchArray(movieArray, keyword, sortOption);
                    long endTimeArray = System.currentTimeMillis();
                    System.out.println("Search with array took: " + (endTimeArray - startTimeArray) + " milliseconds");

                    // Perform binary search on the sorted linked list
                    long startTimeLinkedList = System.currentTimeMillis();
                    results = SearchAlgorithms.binarySearchLinkedList(movieLinkedList, keyword, sortOption);
                    long endTimeLinkedList = System.currentTimeMillis();
                    System.out.println("Search with linked list took: " + (endTimeLinkedList - startTimeLinkedList) + " milliseconds");

                    movieList.revertMovies();  // Revert to original list
                    movieArray = originalMovieArray.clone();  // Revert array
                    movieLinkedList = new LinkedList<>(originalMovieLinkedList);  // Revert linked list
                    System.out.println("Movies have been reverted to original order.");
                }

                if (results.isEmpty()) {
                    System.out.println("No matching movies found.");
                } else {
                    for (Movie movie : results) {
                        System.out.println(movie);
                    }
                }
            } else if (choice == 2) { // Sort Movies
                int sortChoice = 0;
                while (true) {
                    try {
                        System.out.println("Choose sort algorithm: 1. Bubble Sort 2. Selection Sort 3. Merge Sort 4. Quick Sort");
                        sortChoice = scanner.nextInt();
                        scanner.nextLine(); // consume newline

                        if (sortChoice >= 1 && sortChoice <= 4) {
                            break; // valid input
                        } else {
                            System.out.println("Please enter a valid option (1, 2, 3, or 4).");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.nextLine(); // clear invalid input
                    }
                }

                int sortOption = 0;
                while (true) {
                    try {
                        System.out.println("Choose sort option: 1. Title 2. Actor 3. Year 4. Genre");
                        sortOption = scanner.nextInt();
                        scanner.nextLine(); // consume newline

                        if (sortOption >= 1 && sortOption <= 4) {
                            break; // valid input
                        } else {
                            System.out.println("Please enter a valid option (1, 2, 3, or 4).");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.nextLine(); // clear invalid input
                    }
                }
                
                // Perform sorting and time

                if (sortChoice == 1) {
                    // arrayList sort
                    long startTime = System.currentTimeMillis();
                    SortAlgorithms.bubbleSort(movieList.getAllMovies(), sortOption);
                    long endTime = System.currentTimeMillis();
                    System.out.println("Bubble Sort for arrayList took: " + (endTime - startTime) + " milliseconds");

                    // array sort
                    long startTimeArray = System.currentTimeMillis();
                    SortAlgorithms.bubbleSortArray(movieArray, sortOption);
                    long endTimeArray = System.currentTimeMillis();
                    System.out.println("Bubble Sort for array took: " + (endTimeArray - startTimeArray) + " milliseconds");

                    // linked list sort
                    long startTimeLinkedList = System.currentTimeMillis();
                    SortAlgorithms.bubbleSortLinkedList(movieLinkedList, sortOption);
                    long endTimeLinkedList = System.currentTimeMillis();
                    System.out.println("Bubble Sort for linked list took: " + (endTimeLinkedList - startTimeLinkedList) + " milliseconds");

                    movieList.revertMovies();  // Revert to original list
                    movieArray = originalMovieArray.clone();  // Revert array
                    movieLinkedList = new LinkedList<>(originalMovieLinkedList);  // Revert linked list
                    System.out.println("Movies have been reverted to original order.");

                } else if (sortChoice == 2) {
                    // arrayList sort
                    long startTime = System.currentTimeMillis();
                    SortAlgorithms.selectionSort(movieList.getAllMovies(), sortOption);
                    long endTime = System.currentTimeMillis();
                    System.out.println("Selection Sort for arrayList took: " + (endTime - startTime) + " milliseconds");

                    // array sort
                    long startTimeArray = System.currentTimeMillis();
                    SortAlgorithms.selectionSortArray(movieArray, sortOption);
                    long endTimeArray = System.currentTimeMillis();
                    System.out.println("Selection Sort for array took: " + (endTimeArray - startTimeArray) + " milliseconds");

                    // linked list sort
                    long startTimeLinkedList = System.currentTimeMillis();
                    SortAlgorithms.selectionSortLinkedList(movieLinkedList, sortOption);
                    long endTimeLinkedList = System.currentTimeMillis();
                    System.out.println("Selection Sort for linked list took: " + (endTimeLinkedList - startTimeLinkedList) + " milliseconds");

                    movieList.revertMovies();  // Revert to original list
                    movieArray = originalMovieArray.clone();  // Revert array
                    movieLinkedList = new LinkedList<>(originalMovieLinkedList);  // Revert linked list
                    System.out.println("Movies have been reverted to original order.");

                } else if (sortChoice == 3) {
                    // arrayList sort
                    long startTime = System.currentTimeMillis();
                    SortAlgorithms.mergeSort(movieList.getAllMovies(), sortOption);
                    long endTime = System.currentTimeMillis();
                    System.out.println("Merge Sort for arrayList took: " + (endTime - startTime) + " milliseconds");

                    // array sort
                    long startTimeArray = System.currentTimeMillis();
                    SortAlgorithms.mergeSortArray(movieArray, sortOption);
                    long endTimeArray = System.currentTimeMillis();
                    System.out.println("Merge Sort for array took: " + (endTimeArray - startTimeArray) + " milliseconds");

                    // linked list sort
                    long startTimeLinkedList = System.currentTimeMillis();
                    SortAlgorithms.mergeSortLinkedList(movieLinkedList, sortOption);
                    long endTimeLinkedList = System.currentTimeMillis();
                    System.out.println("Merge Sort for linked list took: " + (endTimeLinkedList - startTimeLinkedList) + " milliseconds");

                    movieList.revertMovies();  // Revert to original list
                    movieArray = originalMovieArray.clone();  // Revert array
                    movieLinkedList = new LinkedList<>(originalMovieLinkedList);  // Revert linked list
                    System.out.println("Movies have been reverted to original order.");

                } else if (sortChoice == 4) {
                    // arrayList sort
                    long startTime = System.currentTimeMillis();
                    SortAlgorithms.quickSort(movieList.getAllMovies(), 0, movieList.getAllMovies().size() - 1, sortOption);
                    long endTime = System.currentTimeMillis();
                    System.out.println("Quick Sort for arrayList took: " + (endTime - startTime) + " milliseconds");

                    // array sort
                    long startTimeArray = System.currentTimeMillis();
                    SortAlgorithms.quickSortArray(movieArray, 0, movieArray.length - 1, sortOption);
                    long endTimeArray = System.currentTimeMillis();
                    System.out.println("Quick Sort for array took: " + (endTimeArray - startTimeArray) + " milliseconds");

                    // linked list sort
                    long startTimeLinkedList = System.currentTimeMillis();
                    SortAlgorithms.quickSortLinkedList(movieLinkedList, 0, movieLinkedList.size() - 1, sortOption);
                    long endTimeLinkedList = System.currentTimeMillis();
                    System.out.println("Quick Sort for linked list took: " + (endTimeLinkedList - startTimeLinkedList) + " milliseconds");

                    movieList.revertMovies();  // Revert to original list
                    movieArray = originalMovieArray.clone();  // Revert array
                    movieLinkedList = new LinkedList<>(originalMovieLinkedList);  // Revert linked list
                    System.out.println("Movies have been reverted to original order.");
                }

            } else if (choice == 3) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }
}