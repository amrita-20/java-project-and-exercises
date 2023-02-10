public class Book {
    private String publisher;
    private int yearPublished;
    private int noOfPages;
    private String name;
    private String author;
    private String contentRating;
    private String genre;
    private int ISBN;

    public Book() {
        System.out.println("Inside default constructor of book class");
    }

    public Book(String publisher, int yearPublished, int noOfPages, String name, String author, String contentRating, String genre, int ISBN) {
        this.publisher = publisher;
        this.yearPublished = yearPublished;
        this.noOfPages = noOfPages;
        this.name = name;
        this.author = author;
        this.contentRating = contentRating;
        this.genre = genre;
        this.ISBN = ISBN;
        System.out.println("Inside parametrized constructor of book class");
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public int getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(int noOfPages) {
        this.noOfPages = noOfPages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContentRating() {
        return contentRating;
    }

    public void setContentRating(String contentRating) {
        this.contentRating = contentRating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public void purchase() {
        System.out.println("If you want to purchase this book you can visit amazon website");
    }

    public void displayBookDetails() {
        System.out.println("Book Name " + this.name);
        System.out.println("genre " + genre);
    }

    public void read() {
        System.out.println("Reading book");
    }
}
