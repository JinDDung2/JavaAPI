package kr.api.dto;

public class BookDto {
    private String title;
    private int price;
    private String author;
    private int page;

    public BookDto() {}
    public BookDto(String title, int price, String author, int page) {
        this.title = title;
        this.price = price;
        this.author = author;
        this.page = page;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", page=" + page +
                '}';
    }
}
