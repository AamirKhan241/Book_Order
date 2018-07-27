package sale;

public class Sale {
    private int Sale_id;
    private int Customer_id;
    private int Supplier_id;
    private String date;
    private int Invoice;
    private String amount;
    private String ISBN;
    private String Copies;

    public int getSale_id() {
        return Sale_id;
    }

    public void setSale_id(int Sale_id) {
        this.Sale_id = Sale_id;
    }

    public int getCustomer_id() {
        return Customer_id;
    }

    public void setCustomer_id(int Customer_id) {
        this.Customer_id = Customer_id;
    }

    public int getSupplier_id() {
        return Supplier_id;
    }

    public void setSupplier_id(int Supplier_id) {
        this.Supplier_id = Supplier_id;
    }

    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getInvoice() {
        return Invoice;
    }

    public void setInvoice(int Invoice) {
        this.Invoice = Invoice;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getCopies() {
        return Copies;
    }

    public void setCopies(String Copies) {
        this.Copies = Copies;
    }

    public Sale() {
    }
    
}
