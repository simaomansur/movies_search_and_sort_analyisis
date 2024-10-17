import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class SortAlgorithms {

    // Bubble Sort
    public static void bubbleSort(List<Movie> movies, int sortOption) {
        int n = movies.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (compareMovies(movies.get(j), movies.get(j + 1), sortOption) > 0) {
                    Collections.swap(movies, j, j + 1);
                }
            }
        }
    }

    // Bubble Sort for array
    public static void bubbleSortArray(Movie[] movies, int sortOption) {
        int n = movies.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (compareMovies(movies[j], movies[j + 1], sortOption) > 0) {
                    Movie temp = movies[j];
                    movies[j] = movies[j + 1];
                    movies[j + 1] = temp;
                }
            }
        }
    }

    // Bubble Sort for linked list
    public static void bubbleSortLinkedList(List<Movie> movies, int sortOption) {
        int n = movies.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (compareMovies(movies.get(j), movies.get(j + 1), sortOption) > 0) {
                    Movie temp = movies.get(j);
                    movies.set(j, movies.get(j + 1));
                    movies.set(j + 1, temp);
                }
            }
        }
    }

    // Selection Sort
    public static void selectionSort(List<Movie> movies, int sortOption) {
        int n = movies.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (compareMovies(movies.get(j), movies.get(minIdx), sortOption) < 0) {
                    minIdx = j;
                }
            }
            Collections.swap(movies, i, minIdx);
        }
    }

    // Selection Sort for array
    public static void selectionSortArray(Movie[] movies, int sortOption) {
        int n = movies.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (compareMovies(movies[j], movies[minIdx], sortOption) < 0) {
                    minIdx = j;
                }
            }
            Movie temp = movies[i];
            movies[i] = movies[minIdx];
            movies[minIdx] = temp;
        }
    }

    // Selection Sort for linked list
    public static void selectionSortLinkedList(List<Movie> movies, int sortOption) {
        int n = movies.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (compareMovies(movies.get(j), movies.get(minIdx), sortOption) < 0) {
                    minIdx = j;
                }
            }
            Movie temp = movies.get(i);
            movies.set(i, movies.get(minIdx));
            movies.set(minIdx, temp);
        }
    }

    // Merge Sort
    public static void mergeSort(List<Movie> movies, int sortOption) {
        if (movies.size() <= 1) return;
        int mid = movies.size() / 2;
        List<Movie> left = new ArrayList<>(movies.subList(0, mid));
        List<Movie> right = new ArrayList<>(movies.subList(mid, movies.size()));

        mergeSort(left, sortOption);
        mergeSort(right, sortOption);

        merge(movies, left, right, sortOption);
    }

    private static void merge(List<Movie> movies, List<Movie> left, List<Movie> right, int sortOption) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (compareMovies(left.get(i), right.get(j), sortOption) <= 0) {
                movies.set(k++, left.get(i++));
            } else {
                movies.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) movies.set(k++, left.get(i++));
        while (j < right.size()) movies.set(k++, right.get(j++));
    }

    // Merge Sort for array
    public static void mergeSortArray(Movie[] movies, int sortOption) {
        if (movies.length <= 1) return;
        int mid = movies.length / 2;
        Movie[] left = new Movie[mid];
        Movie[] right = new Movie[movies.length - mid];
        System.arraycopy(movies, 0, left, 0, mid);
        System.arraycopy(movies, mid, right, 0, movies.length - mid);

        mergeSortArray(left, sortOption);
        mergeSortArray(right, sortOption);

        mergeArray(movies, left, right, sortOption);
    }

    private static void mergeArray(Movie[] movies, Movie[] left, Movie[] right, int sortOption) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (compareMovies(left[i], right[j], sortOption) <= 0) {
                movies[k++] = left[i++];
            } else {
                movies[k++] = right[j++];
            }
        }
        while (i < left.length) movies[k++] = left[i++];
        while (j < right.length) movies[k++] = right[j++];
    }

    // Merge Sort for linked list
    public static void mergeSortLinkedList(List<Movie> movies, int sortOption) {
        if (movies.size() <= 1) return;
        int mid = movies.size() / 2;
        List<Movie> left = new ArrayList<>(movies.subList(0, mid));
        List<Movie> right = new ArrayList<>(movies.subList(mid, movies.size()));

        mergeSortLinkedList(left, sortOption);
        mergeSortLinkedList(right, sortOption);

        mergeLinkedList(movies, left, right, sortOption);
    }

    private static void mergeLinkedList(List<Movie> movies, List<Movie> left, List<Movie> right, int sortOption) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (compareMovies(left.get(i), right.get(j), sortOption) <= 0) {
                movies.set(k++, left.get(i++));
            } else {
                movies.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) movies.set(k++, left.get(i++));
        while (j < right.size()) movies.set(k++, right.get(j++));
    }

    // Quick Sort
    public static void quickSort(List<Movie> movies, int low, int high, int sortOption) {
        if (low < high) {
            int pi = partition(movies, low, high, sortOption);
            quickSort(movies, low, pi - 1, sortOption);
            quickSort(movies, pi + 1, high, sortOption);
        }
    }

    // Quick sort for array
    public static void quickSortArray(Movie[] movies, int low, int high, int sortOption) {
        if (low < high) {
            int pi = partitionArray(movies, low, high, sortOption);
            quickSortArray(movies, low, pi - 1, sortOption);
            quickSortArray(movies, pi + 1, high, sortOption);
        }
    }

    // Quick Sort for LinkedList
    public static void quickSortLinkedList(List<Movie> movies, int low, int high, int sortOption) {
        if (low < high) {
            int pi = partitionLinkedList(movies, low, high, sortOption);
            quickSortLinkedList(movies, low, pi - 1, sortOption);
            quickSortLinkedList(movies, pi + 1, high, sortOption);
        }
    }

    // Partition function for arrayList
    private static int partition(List<Movie> movies, int low, int high, int sortOption) {
        Movie pivot = movies.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (compareMovies(movies.get(j), pivot, sortOption) < 0) {
                i++;
                Collections.swap(movies, i, j);
            }
        }
        Collections.swap(movies, i + 1, high);
        return i + 1;
    }

    // Partition function for array
    private static int partitionArray(Movie[] movies, int low, int high, int sortOption) {
        Movie pivot = movies[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (compareMovies(movies[j], pivot, sortOption) < 0) {
                i++;
                Movie temp = movies[i];
                movies[i] = movies[j];
                movies[j] = temp;
            }
        }
        Movie temp = movies[i + 1];
        movies[i + 1] = movies[high];
        movies[high] = temp;
        return i + 1;
    }

    // Partition function for linked list
    private static int partitionLinkedList(List<Movie> movies, int low, int high, int sortOption) {
        Movie pivot = movies.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (compareMovies(movies.get(j), pivot, sortOption) < 0) {
                i++;
                Movie temp = movies.get(i);
                movies.set(i, movies.get(j));
                movies.set(j, temp);
            }
        }
        Movie temp = movies.get(i + 1);
        movies.set(i + 1, movies.get(high));
        movies.set(high, temp);
        return i + 1;
    }

    // Helper function to compare movies based on sort option
    private static int compareMovies(Movie m1, Movie m2, int sortOption) {
        switch (sortOption) {
            case 1: return m1.title.compareToIgnoreCase(m2.title);
            case 2: return m1.leadActor.compareToIgnoreCase(m2.leadActor);
            case 3: return Integer.compare(m1.releaseYear, m2.releaseYear);
            case 4: return m1.genre.compareToIgnoreCase(m2.genre);
            default: return 0;
        }
    }
}