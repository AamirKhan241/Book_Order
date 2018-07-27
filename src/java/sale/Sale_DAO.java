package sale;

import java.util.List;

public interface Sale_DAO {
    
    boolean insert(Sale s);
    boolean update(Sale s);
    boolean delete(int Sales_Id);
    boolean deleteI(int invoice);
    
    int getNextId();
    String getDate(int invoice);
    
    Sale display(int Sales_id);
    List<Sale> display();
    List<Sale> displayI(int invoice);
    List<Sale> displayS(int sID);
}
