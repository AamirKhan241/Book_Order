package userQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserQuery_DAO_Implt implements UserQuery_DAO{
    Connection conn=singleCopy.Database.getInstance().getConnection();
    @Override
    public boolean insert(UserQuery u) {
        try {
            PreparedStatement ps=conn.prepareStatement("insert into userquery values("+getNextInt()+",'"+u.getName()+"','"+u.getEmail()
            +"','"+u.getSubject()+"','"+u.getMessage()+"','--none--')");
            int i=ps.executeUpdate();
            ps.close();
            if(i>0)
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean update(UserQuery u) {
        try {
            PreparedStatement ps=conn.prepareStatement("update userquery set reply='"+u.getReply()+"' where serial_no="+u.getSerial());
            int i=ps.executeUpdate();
            ps.close();
            if(i>0)
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int sNo) {
        try {
            PreparedStatement ps=conn.prepareStatement("delete from userquery where serial_no="+sNo);
            int i=ps.executeUpdate();
            ps.close();
            if(i>0)
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<UserQuery> display() {
        List<UserQuery> list=new ArrayList<>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from userquery order by serial_no");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                UserQuery u=new UserQuery();
                u.setSerial(rs.getInt("serial_no"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setSubject(rs.getString("subject"));
                u.setMessage(rs.getString("message"));
                u.setReply(rs.getString("reply"));
                list.add(u);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    private int getNextInt(){
        try {
            PreparedStatement ps=conn.prepareStatement("select max(serial_no) id from userQuery");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                return rs.getInt("id")+1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public UserQuery display(int sNo) {
        UserQuery u=new UserQuery();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from userquery where serial_no="+sNo);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                u.setSerial(rs.getInt("serial_no"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setSubject(rs.getString("subject"));
                u.setMessage(rs.getString("message"));
                u.setReply(rs.getString("reply"));
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }
}
