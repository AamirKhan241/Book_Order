package purchaseReport;

public class PurchaseReport {
    private int Purchase_Note_Id;
    private int Supplier_Id;
    private String Amount_To_Pay;
    private String Book_ISBN;
    private String Copies;
    private String Price;
    private String Amount_Paid;
    private String Balance;

    public int getPurchase_Note_Id() {
        return Purchase_Note_Id;
    }

    public void setPurchase_Note_Id(int Purchase_Note_Id) {
        this.Purchase_Note_Id = Purchase_Note_Id;
    }

    public int getSupplier_Id() {
        return Supplier_Id;
    }

    public void setSupplier_Id(int Supplier_Id) {
        this.Supplier_Id = Supplier_Id;
    }

    
    public String getAmount_To_Pay() {
        return Amount_To_Pay;
    }

    public void setAmount_To_Pay(String Amount_To_Pay) {
        this.Amount_To_Pay = Amount_To_Pay;
    }

    public String getBook_ISBN() {
        return Book_ISBN;
    }

    public void setBook_ISBN(String Book_ISBN) {
        this.Book_ISBN = Book_ISBN;
    }

    public String getCopies() {
        return Copies;
    }

    public void setCopies(String Copies) {
        this.Copies = Copies;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String getAmount_Paid() {
        return Amount_Paid;
    }

    public void setAmount_Paid(String Amount_Paid) {
        this.Amount_Paid = Amount_Paid;
    }

    public String getBalance() {
        return Balance;
    }

    public void setBalance(String Balance) {
        this.Balance = Balance;
    }

    public PurchaseReport() {
    }
}
