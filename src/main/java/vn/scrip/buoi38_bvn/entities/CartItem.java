package vn.scrip.buoi38_bvn.entities;

<<<<<<< HEAD
public class CartItem {
    private Book book;
    private int quantity;
    public CartItem(Book book, int quantity)
    {
        this.book = book;

        this.quantity = quantity;
    }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

=======
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItem {
    private Book book;
    private int quantity;
    public CartItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }
>>>>>>> 31513c7b17ddf40d8746ad8a3b230501aa5905c4
}