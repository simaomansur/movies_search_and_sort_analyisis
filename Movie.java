class Movie {
    String title;
    String leadActor;
    int releaseYear;
    String genre;

    public Movie(String title, String leadActor, int releaseYear, String genre) {
        this.title = title;
        this.leadActor = leadActor;
        this.releaseYear = releaseYear;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Actor: " + leadActor + ", Year: " + releaseYear + ", Genre: " + genre;
    }
}