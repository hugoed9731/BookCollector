package model;

import enums.Genre;

public class Book {
    private String title;
    private Genre genre;
    private String author;
    private String editorial;
    private int totalOfvolumes;
    private int volume;
    private boolean readIt;

    public Book(String title, Genre genre, String author, String editorial, int totalOfvolumes, int volume){
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.editorial = editorial;
        this.totalOfvolumes = totalOfvolumes;
        this.volume = volume;
        this.readIt = false;
    }

    @Override
    public String toString() {
        return isReadIt() ? "Read ✔" : "No Read ❌";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int gettotalOfvolumes() {
        return totalOfvolumes;
    }

    public void settotalOfvolumes(int totalOfvolumes) {
        this.totalOfvolumes = totalOfvolumes;
    }

    public int getVolume() {return volume;}

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public boolean isReadIt() {
        return readIt;
    }

    public void setReadIt(boolean readIt) {
        this.readIt = readIt;
    }
}
