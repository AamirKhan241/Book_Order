package purchaseReport;

import java.util.List;

public interface PurchaseReport_DAO {
    
    boolean insert(PurchaseReport pr);
    boolean update(PurchaseReport pr);
    boolean delete(int pnID);
    int getNextID();
    
    PurchaseReport display(int pnID);
    List<PurchaseReport> display();
    List<PurchaseReport> displayS(int sID);
    
}
