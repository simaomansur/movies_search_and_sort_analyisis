# movies_search_and_sort_analyisis

Overview:
This project is a movie store application designed to handle two modes: Manager Mode and User Mode. In Manager Mode, users can add or remove movies from a CSV database containing movie details such as the title, lead actor, release year, and genre. In User Mode, users can search for movies based on various fields or sort the movie list using different sorting algorithms. The program compares search performance using linear and binary search across three different data types: ArrayList, Array, and LinkedList, and evaluates different sorting algorithms.

Desired Output:
The desired output of the program is the following:
1.	For Manager Mode: (Password = 123)
  o	Successfully added or removed movie entries in the CSV file.
  o	Appropriate messages for success or error during operations like adding or removing movies.
2.	For User Mode:
  o	Results from the search operations (movie details that match the search keyword).
  o	Time taken for sorting operations using different sorting algorithms.
  o	Time taken for search operations using linear and binary search.
  o	Timing comparisons for ArrayList, Array, and LinkedList for both searching and sorting.

Inputs:
The inputs for this program include:
1.	Manager Mode:
  o	Movie details: Title, Lead Actor, Release Year, Genre.
  o	Selection of menu options to add or remove movies.
2.	User Mode:
  o	Selection of search algorithm (linear search or binary search).
  o	Search keyword (for searching by title, lead actor, release year, or genre).
  o	Selection of sorting algorithm (Bubble Sort, Selection Sort, Merge Sort, Quick Sort).
  o	Sorting option (Title, Lead Actor, Release Year, or Genre).

Process:
1. Initialize the Scanner:
  A Scanner object is created to take input from the user. This scanner is used to handle both the selection of the mode (Manager or User) and
  the input required for various operations such as adding, removing, searching, and sorting movies.
3. Choose Mode:
  •	Prompt: The user is prompted to choose between Manager Mode and User Mode.
  •	Manager Mode:
    o	The program prompts for a password.
    o	Validate Password: The password is compared to the hardcoded value (123 in this case).
    o	Valid Password: If correct, the user is granted access to Manager Mode.
    o	Invalid Password: If incorrect, the user is informed, and the prompt is repeated.
  •	User Mode: The user is granted direct access to User Mode without requiring a password.
4. Manager Mode:
  •	Manager Menu:
    o	The manager is presented with three options:
      1.	Add a Movie: Allows the manager to add a new movie to the list and save it to the CSV file.
      2.	Remove a Movie: Allows the manager to remove an existing movie from the list and the CSV file.
      3.	Exit: Allows the manager to exit Manager Mode.
  •	Add Movie Process:
    o	Input Validation:
      The program ensures that the movie title, lead actor, and genre are non-empty strings.
      The release year is validated to be a positive integer between 1880 and the current year.
    o	Save to CSV:
      Once valid inputs are provided, the new movie is added to both the in-memory list and the CSV file. The program appends the movie to the
      CSV file and prints a confirmation message.
  •	Remove Movie Process:
    o	Movie Search: The manager is prompted to enter the title of the movie to remove.
    o	Removal from CSV: If the movie is found, it is removed from both the in-memory list and the CSV file. The program rewrites the CSV file without
      the removed movie and prints a confirmation message.
4. User Mode:
  •	User Menu:
    o	The user is presented with three options:
      1.	Search for a Movie: Allows the user to search for a movie using either linear or binary search.
      2.	Sort Movies: Allows the user to sort the movie list using one of four sorting algorithms.
      3.	Exit: Allows the user to exit the User Mode.
5. Search Process:
  •	Prompt for Search Algorithm: The user is prompted to choose between two search algorithms: Linear Search or Binary Search.
  •	Linear Search:
    o	Input: The user enters a search keyword, which could be a movie title, actor, release year, or genre.
    o	Execution: The program performs a linear search by iterating over the entire movie list and checking each field for a match with the keyword.
    o	Output: The program prints the movies that match the keyword. If no matches are found, it prints a message indicating that no matches were found.
    o	Timing: The time taken for the search is measured using System.currentTimeMillis() for each data type (Array, ArrayList, and LinkedList), and
       the result is printed in milliseconds.
  •	Binary Search:
    o	Input: The user selects a field (title, actor, year, or genre) to sort the list by before performing the binary search.
    o	Sorting: The program first sorts the movie list based on the selected field using the Quick Sort algorithm.
    o	Binary Search Execution: After sorting, the program performs a binary search to find a match.
    o	Output: The program prints the results and the time taken for both sorting and searching.
    o	Timing: The time taken for both sorting and searching is measured for each data type (Array, ArrayList, and LinkedList) and displayed in
       milliseconds.
7. Sort Process:
  •	Prompt for Sorting Algorithm: The user is prompted to choose from four sorting algorithms: Bubble Sort, Selection Sort, Merge Sort, or Quick Sort.
  •	Field Selection: The user selects the field by which the list should be sorted: title, actor, release year, or genre.
  •	Sorting Execution:
    o	The selected sorting algorithm is applied to the movie list based on the chosen field.
    o	The time taken to perform the sorting is measured using System.currentTimeMillis() for each data type (Array, ArrayList, and LinkedList) and
       printed in milliseconds.
  •	Reverting the List: After sorting, the movie list is reverted to its original state by restoring the initial in-memory list.
9. Exiting the Program:
  When the user selects the Exit option in either Manager Mode or User Mode, the scanner is closed, and the program ends gracefully.
