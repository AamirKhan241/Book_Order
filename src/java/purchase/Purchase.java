
package purchase;

public class Purchase {
    
    private int purchase_ID;
    private int purchase_Note_ID;
    private int supplier_ID;
    private String Amount;
    private String Pur_Date;

    public int getPurchase_ID() {
        return purchase_ID;
    }

    public void setPurchase_ID(int purchase_ID) {
        this.purchase_ID = purchase_ID;
    }

    public int getPurchase_Note_ID() {
        return purchase_Note_ID;
    }

    public void setPurchase_Note_ID(int purchase_Note_ID) {
        this.purchase_Note_ID = purchase_Note_ID;
    }

    public int getSupplier_ID() {
        return supplier_ID;
    }

    public void setSupplier_ID(int supplier_ID) {
        this.supplier_ID = supplier_ID;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    public String getPur_Date() {
        return Pur_Date;
    }

    public void setPur_Date(String Pur_Date) {
        this.Pur_Date = Pur_Date;
    }

    public Purchase() {
    }
    
}
