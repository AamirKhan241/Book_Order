package profit;

public class Profit {
    private int transaction_no;
    private int saleBY;
    private int saleFROM;
    private String amount;
    private String Date;

    public int getTransaction_no() {
        return transaction_no;
    }

    public void setTransaction_no(int transaction_no) {
        this.transaction_no = transaction_no;
    }

    public int getSaleBY() {
        return saleBY;
    }

    public void setSaleBY(int saleBY) {
        this.saleBY = saleBY;
    }

    public int getSaleFROM() {
        return saleFROM;
    }

    public void setSaleFROM(int saleFROM) {
        this.saleFROM = saleFROM;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public Profit() {
    }
}
