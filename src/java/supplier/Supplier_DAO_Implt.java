package supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import publisher.Publisher;
import publisher.Publisher_DAO;
import publisher.Publisher_DAO_Implt;

public class Supplier_DAO_Implt implements Supplier_DAO{
    private Connection conn=singleCopy.Database.getInstance().getConnection();
    
      @Override
    public boolean insert(Supplier s) {
        try {
            PreparedStatement ps=conn.prepareStatement("Insert into Supplier_details values("+getNextId()+",?,?,?)");
            ps.setInt(1, s.getSID());
            ps.setString(2, s.getEmail());
            ps.setInt(3, s.getPublisher());
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
    public List<Supplier> display() {
        List<Supplier> list=new ArrayList<>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from Supplier_Details order by Serial_No");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                Supplier s=new Supplier();
                s.setSNo(rs.getInt("Serial_No"));
                s.setSID(rs.getInt("Supplier_Id"));
                s.setEmail(rs.getString("email"));
                s.setPublisher(rs.getInt("Publisher_id"));
                list.add(s);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    private int getNextId(){

        try {
            Connection conn=singleCopy.Database.getInstance().getConnection();
            PreparedStatement ps=conn.prepareStatement("select max(Serial_No) id from SUPPLIER_DETAILS");
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
    public boolean deleteS(int Sid) {
        try {
            PreparedStatement ps=conn.prepareStatement("delete from SUPPLIER_DETAILS where supplier_id="+Sid);
            int i=ps.executeUpdate();
            ps.close();
            if(i>0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Supplier display(int Sid) {
        Supplier s=new Supplier();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from Supplier_Details where Supplier_Id="+Sid);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                s.setSNo(rs.getInt("Serial_No"));
                s.setSID(rs.getInt("Supplier_Id"));
                s.setEmail(rs.getString("email"));
                s.setPublisher(rs.getInt("Publisher_id"));
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public List<Supplier> displayP(int pid) {
        List<Supplier> list=new ArrayList<>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from Supplier_Details where publisher_id="+pid+" order by Serial_No");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                Supplier s=new Supplier();
                s.setSNo(rs.getInt("Serial_No"));
                s.setSID(rs.getInt("Supplier_Id"));
                s.setEmail(rs.getString("email"));
                s.setPublisher(rs.getInt("Publisher_id"));
                list.add(s);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Supplier> getSupplierP(String pname) {
        List<Supplier> list=new ArrayList<>();
        try {
            Publisher_DAO pdao=new Publisher_DAO_Implt();
            PreparedStatement ps=conn.prepareStatement("select * from Supplier_Details where publisher_id="+pdao.displaySi(pname).getId()+" order by Serial_No");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                Supplier s=new Supplier();
                s.setSNo(rs.getInt("Serial_No"));
                s.setSID(rs.getInt("Supplier_Id"));
                s.setEmail(rs.getString("email"));
                s.setPublisher(rs.getInt("Publisher_id"));
                list.add(s);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
