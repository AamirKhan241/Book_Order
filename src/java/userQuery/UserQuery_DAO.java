package userQuery;

import java.util.List;

public interface UserQuery_DAO {
    
    boolean insert(UserQuery u);
    boolean update(UserQuery u);
    boolean delete(int sNo);
    
    UserQuery display(int sNo);
    List<UserQuery> display();
}
