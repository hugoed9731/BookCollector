package model;

public class Collector {
    private String name;
    private int totalOfBooks;
    private int totalOfMangaCollections;
    private String favoriteGenreOfBooks;
    private String getFavoriteGenreOfManga;

    public Collector(String name, int totalOfBooks, int totalOfMangaCollections, String favoriteGenreOfBooks, String getFavoriteGenreOfManga ) {
        this.name = name;
        this.totalOfBooks = totalOfBooks;
        this.totalOfMangaCollections = totalOfMangaCollections;
        this.favoriteGenreOfBooks = favoriteGenreOfBooks;
        this.getFavoriteGenreOfManga = getFavoriteGenreOfManga;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalOfBooks() {
        return totalOfBooks;
    }

    public void setTotalOfBooks(int totalOfBooks) {
        this.totalOfBooks = totalOfBooks;
    }

    public int getTotalOfMangaCollections() {
        return totalOfMangaCollections;
    }

    public void setTotalOfMangaCollections(int totalOfMangaCollections) {
        this.totalOfMangaCollections = totalOfMangaCollections;
    }

    public String getFavoriteGenreOfBooks() {
        return favoriteGenreOfBooks;
    }

    public void setFavoriteGenreOfBooks(String favoriteGenreOfBooks) {
        this.favoriteGenreOfBooks = favoriteGenreOfBooks;
    }

    public String getGetFavoriteGenreOfManga() {
        return getFavoriteGenreOfManga;
    }

    public void setGetFavoriteGenreOfManga(String getFavoriteGenreOfManga) {
        this.getFavoriteGenreOfManga = getFavoriteGenreOfManga;
    }
}
