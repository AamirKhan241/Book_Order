package profile;

import java.util.List;

public interface Profiles_DAO {
    
    boolean insert(Profiles p);
    boolean update(Profiles p);
    boolean delete(int id);
    boolean changeStatus(int id);
    Profiles display(int id);
    Profiles displayE(String email);
    List<Profiles> displays(int id);
    List<Profiles> display();
    List<Profiles> displayA();
    List<Profiles> displayS();
    List<Profiles> displayB();
    String getMessage();
    
}
