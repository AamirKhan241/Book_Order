package cart;

public class Cart {
    
    private int serial_no;
    private int cID;
    private String Book_id;
    private int quantity;

    public int getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(int serial_no) {
        this.serial_no = serial_no;
    }

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public String getBook_id() {
        return Book_id;
    }

    public void setBook_id(String Book_id) {
        this.Book_id = Book_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }    

    public Cart() {
    }
    
}
