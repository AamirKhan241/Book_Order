package supplier;

import java.util.List;

public interface Supplier_DAO {

    boolean insert(Supplier s);
    boolean deleteS(int Sid);
    Supplier display(int Sid);
    List<Supplier> displayP(int pid);
    List<Supplier> getSupplierP(String pname);
    List<Supplier> display();
    
}
