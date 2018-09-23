package profit;

import java.util.List;

public interface Profit_DAO {
    
    boolean insert(Profit p);
    boolean delete(int tNo);
    List<Profit> display();
}
