package publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Publisher_DAO_Implt implements Publisher_DAO{

    Connection conn=singleCopy.Database.getInstance().getConnection();
    
    @Override
    public boolean insert(Publisher p) {
        try {
            PreparedStatement ps=conn.prepareStatement("insert into publisher values("+getNextId()+",'"+p.getName()+"',"+p.getSupplier_Id()+")");
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
    public boolean update(Publisher p) {
        try {
            PreparedStatement ps=conn.prepareStatement("update publisher set publisher_name='"+p.getName()+"' where publisher_id="+p.getId());
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
            PreparedStatement ps=conn.prepareStatement("delete from publisher where publisher_id="+id);
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
    public boolean deleteS(int sID) {
        try {
            PreparedStatement ps=conn.prepareStatement("delete from publisher where supplier_id="+sID);
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
    public List<Publisher> displayP(String name) {
        List<Publisher> list=new ArrayList<>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from publisher where publisher_name='"+name+"'");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                Publisher p=new Publisher();
                p.setId(rs.getInt("publisher_id"));
                p.setSupplier_Id(rs.getInt("Supplier_Id"));
                p.setName(rs.getString("publisher_name"));
                list.add(p);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    @Override
    public Publisher display(int  id) {
        Publisher p=new Publisher();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from publisher where publisher_id="+id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                p.setId(rs.getInt("publisher_id"));
                p.setSupplier_Id(rs.getInt("Supplier_Id"));
                p.setName(rs.getString("publisher_name"));
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public List<Publisher> display() {
        List<Publisher> list=new ArrayList<>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from publisher order by publisher_id");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                Publisher p=new Publisher();
                p.setId(rs.getInt("publisher_id"));
                p.setSupplier_Id(rs.getInt("Supplier_Id"));
                p.setName(rs.getString("publisher_name"));
                list.add(p);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public int getNextId(){
        try {
            PreparedStatement ps=conn.prepareStatement("select max(Publisher_id) id from publisher");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                return rs.getInt("id")+1;
            }          
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }   

    @Override
    public List<Publisher> displayS(int sID) {
        List<Publisher> list=new ArrayList<>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from publisher where Supplier_id="+sID);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                Publisher p=new Publisher();
                p.setId(rs.getInt("publisher_id"));
                p.setSupplier_Id(rs.getInt("Supplier_Id"));
                p.setName(rs.getString("publisher_name"));
                list.add(p);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Publisher displaySi(String name){
        Publisher p=new Publisher();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from publisher where Publisher_name='"+name+"'");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                p.setId(rs.getInt("publisher_id"));
                p.setSupplier_Id(rs.getInt("Supplier_Id"));
                p.setName(rs.getString("publisher_name"));
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }
    @Override
    public Publisher displaySi(int sID){
        Publisher p=new Publisher();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from publisher where Supplier_id="+sID);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                p.setId(rs.getInt("publisher_id"));
                p.setSupplier_Id(rs.getInt("Supplier_Id"));
                p.setName(rs.getString("publisher_name"));
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

 
}