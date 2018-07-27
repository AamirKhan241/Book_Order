package profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class Profiles_DAO_Implt implements Profiles_DAO{
    public String Message;
    Connection conn=singleCopy.Database.getInstance().getConnection();
    @Override
    public boolean insert(Profiles p){
        try {
            PreparedStatement ps=conn.prepareStatement("insert into profiles(user_id,first_name,last_name,roles,email,"
                    + "password,contact_no,address ) values("+getNextId()+",?,?,?,?,?,?,?)");
            ps.setString(1, p.getFirst_Name().toUpperCase());
            ps.setString(2, p.getLast_Name().toUpperCase());
            ps.setString(3, p.getRole().toUpperCase());
            ps.setString(4, p.getEmail().toUpperCase());
            ps.setString(5, p.getPassword());
            ps.setString(6, p.getContact());
            ps.setString(7, p.getAddress().toUpperCase());
            int i=ps.executeUpdate();
            System.out.println(i);
            ps.close();
            if(i>0)
                return true;
        } catch (SQLIntegrityConstraintViolationException e) {            
            Message= "Email Already Exists";
//            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Profiles p) {
        try {
            PreparedStatement ps=conn.prepareStatement("update profiles set "
                    + "first_name=?,last_name=?,roles=?,email=?,password=?,contact_no=?,address=? where User_id="+p.getId());
            ps.setString(1, p.getFirst_Name().toUpperCase());
            ps.setString(2, p.getLast_Name().toUpperCase());
            ps.setString(3, p.getRole().toUpperCase());
            ps.setString(4, p.getEmail().toUpperCase());
            ps.setString(5, p.getPassword());
            ps.setString(6, p.getContact());
            ps.setString(7, p.getAddress().toUpperCase());
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
    public boolean delete(int id) {
        try {
            PreparedStatement ps=conn.prepareStatement("delete from profiles where user_id="+id);
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
    public Profiles display(int id) {
        Profiles p=new Profiles();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from profiles where user_id="+id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("user_id"));
                p.setFirst_Name(rs.getString("first_name"));
                p.setLast_Name(rs.getString("last_name"));
                p.setEmail(rs.getString("email"));
                p.setPassword(rs.getString("password"));
                p.setRole(rs.getString("roles"));
                p.setContact(rs.getString("contact_no"));
                p.setAddress(rs.getString("address"));
                p.setStatus(rs.getString("status"));
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Data Redirected...");
        return p;
    }

    @Override
    public List<Profiles> display() {
        List<Profiles> list=new ArrayList<Profiles>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from profiles order by user_id");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Profiles p=new Profiles();
                p.setId(rs.getInt("user_id"));
                p.setFirst_Name(rs.getString("first_name"));
                p.setLast_Name(rs.getString("last_name"));
                p.setEmail(rs.getString("email"));
                p.setPassword(rs.getString("password"));
                p.setRole(rs.getString("roles"));
                p.setContact(rs.getString("contact_no"));
                p.setAddress(rs.getString("address"));
                p.setStatus(rs.getString("status"));
                list.add(p);
            }          
            ps.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean changeStatus(int id) {
        try {
            Profiles p=display(id);
            if(p.getStatus().equals("ACTIVE")){
                PreparedStatement ps=conn.prepareStatement("update profiles set status = 'INACTIVE' where user_id="+id);
                int i=ps.executeUpdate();
                ps.close();
                if(i>0)
                    return true;
            }else if(p.getStatus().equals("INACTIVE")){
                PreparedStatement ps=conn.prepareStatement("update profiles set status = 'ACTIVE' where user_id="+id);
                int i=ps.executeUpdate();
                ps.close();
                if(i>0)
                    return true;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }

    @Override
    public Profiles displayE(String email) {
        Profiles p=new Profiles();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from profiles where email='"+email+"' order by user_id");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("user_id"));
                p.setFirst_Name(rs.getString("first_name"));
                p.setLast_Name(rs.getString("last_name"));
                p.setEmail(rs.getString("email"));
                p.setPassword(rs.getString("password"));
                p.setRole(rs.getString("roles"));
                p.setContact(rs.getString("contact_no"));
                p.setAddress(rs.getString("address"));
                p.setStatus(rs.getString("status"));
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public List<Profiles> displays(int id) {
        List<Profiles> list=new ArrayList<Profiles>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from profiles where user_id="+id+" order by user_id");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Profiles p=new Profiles();
                p.setId(rs.getInt("user_id"));
                p.setFirst_Name(rs.getString("first_name"));
                p.setLast_Name(rs.getString("last_name"));
                p.setEmail(rs.getString("email"));
                p.setPassword(rs.getString("password"));
                p.setRole(rs.getString("roles"));
                p.setContact(rs.getString("contact_no"));
                p.setAddress(rs.getString("address"));
                p.setStatus(rs.getString("status"));
                list.add(p);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    private int getNextId(){
        try {
            PreparedStatement ps=conn.prepareStatement("select max(user_id) id from profiles");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                return rs.getInt("id")+1;
            }          
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public List<Profiles> displayA() {
        List<Profiles> list=new ArrayList<Profiles>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from profiles where roles='ADMINISTRATOR' order by user_id");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Profiles p=new Profiles();
                p.setId(rs.getInt("user_id"));
                p.setFirst_Name(rs.getString("first_name"));
                p.setLast_Name(rs.getString("last_name"));
                p.setEmail(rs.getString("email"));
                p.setPassword(rs.getString("password"));
                p.setRole(rs.getString("roles"));
                p.setContact(rs.getString("contact_no"));
                p.setAddress(rs.getString("address"));
                p.setStatus(rs.getString("status"));
                list.add(p);
            }          
            ps.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Profiles> displayS() {
        List<Profiles> list=new ArrayList<Profiles>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from profiles where roles='SUPPLIER' order by user_id");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Profiles p=new Profiles();
                p.setId(rs.getInt("user_id"));
                p.setFirst_Name(rs.getString("first_name"));
                p.setLast_Name(rs.getString("last_name"));
                p.setEmail(rs.getString("email"));
                p.setPassword(rs.getString("password"));
                p.setRole(rs.getString("roles"));
                p.setContact(rs.getString("contact_no"));
                p.setAddress(rs.getString("address"));
                p.setStatus(rs.getString("status"));
                list.add(p);
            }          
            ps.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Profiles> displayB() {
        List<Profiles> list=new ArrayList<Profiles>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from profiles where roles='BUYER' order by user_id");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Profiles p=new Profiles();
                p.setId(rs.getInt("user_id"));
                p.setFirst_Name(rs.getString("first_name"));
                p.setLast_Name(rs.getString("last_name"));
                p.setEmail(rs.getString("email"));
                p.setPassword(rs.getString("password"));
                p.setRole(rs.getString("roles"));
                p.setContact(rs.getString("contact_no"));
                p.setAddress(rs.getString("address"));
                p.setStatus(rs.getString("status"));
                list.add(p);
            }          
            ps.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String getMessage() {
        return Message;
    }
}
