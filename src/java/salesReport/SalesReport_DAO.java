package salesReport;

import java.util.List;

public interface SalesReport_DAO {
    
    boolean insert(SalesReport s);
    boolean update(SalesReport s);
    boolean delete(int invoice);
    int getNextInvoice();

    
    SalesReport display(int invoice);
    List<SalesReport> display();
    List<SalesReport> displayC(int cID);
    List<SalesReport> displayS(int sID);
}
