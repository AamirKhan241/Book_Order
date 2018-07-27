package purchase;

import java.util.List;

public interface Purchase_DAO {
    
    boolean insert(Purchase p);
    boolean update(Purchase p);
    boolean delete(int pID);
    int getNextId();
    String getPur_Date(int PurchaseNote);
    
    Purchase display(int pID);
    List<Purchase> display();
    List<Purchase> displayS(int sID);
}
