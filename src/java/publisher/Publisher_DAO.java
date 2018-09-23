package publisher;

import java.util.List;

public interface Publisher_DAO {
    
    boolean insert(Publisher p);
    boolean update(Publisher p);
    boolean delete(int id);
    boolean deleteS(int sID);
    int getNextId();
    
    Publisher display(int id);
    Publisher displaySi(String name);
    Publisher displaySi(int sId);
    List<Publisher> displayP(String name);
    List<Publisher> displayS(int sID);
    List<Publisher> display();
    
}
