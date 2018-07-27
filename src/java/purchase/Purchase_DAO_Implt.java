package purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class Purchase_DAO_Implt implements Purchase_DAO{
    Connection conn=singleCopy.Database.getInstance().getConnection();
    
    @Override
    public boolean insert(Purchase p) {
        try {
            PreparedStatement ps=conn.prepareStatement("insert into purchase(purchase_id,purchase_note_no,supplier_id,amount) values("+getNextId()+","+p.getPurchase_Note_ID()+","
                                                        +p.getSupplier_ID()+",'"+p.getAmount()+"')");
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
    public boolean update(Purchase p) {
        try {
            PreparedStatement ps=conn.prepareStatement("update purchase set purchase_note_id="+p.getPurchase_Note_ID()+",supplier_id="+
                                                        p.getSupplier_ID()+",amount='"+p.getAmount()+"',purchase_date='"+p.getPur_Date()+"'"
                                                                + "where purchase_id="+p.getPurchase_ID());
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
    public boolean delete(int pID) {
        try {
            PreparedStatement ps=conn.prepareStatement("delete from purchase where purchase_id="+pID);
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
    public int getNextId() {
        try {
            PreparedStatement ps=conn.prepareStatement("select max(purchase_id) id from purchase");
            ResultSet rs= ps.executeQuery();
            while (rs.next()) {                
                return rs.getInt("id")+1;
            }          
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
    @Override
    public String getPur_Date(int PurchaseNote) {
        String msg="";
        try {
            PreparedStatement ps=conn.prepareStatement("select purchase_date from purchase where purchase_note_no="+PurchaseNote);
            ResultSet rs= ps.executeQuery();
            while (rs.next()) {                
                msg=DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.SHORT).format(rs.getDate("purchase_date"));
            }          
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public Purchase display(int pID) {
        Purchase p=new Purchase();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from purchase where purchase_id="+pID);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                p.setPurchase_ID(rs.getInt("purchase_id"));
                p.setPurchase_Note_ID(rs.getInt("purchase_note_no"));
                p.setSupplier_ID(rs.getInt("Supplier_Id"));
                p.setAmount(rs.getString("Amount"));
                p.setPur_Date(rs.getString("purchase_date"));
            }          
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public List<Purchase> display() {
        List<Purchase> list=new ArrayList<>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from purchase order by purchase_id");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                Purchase p=new Purchase();
                p.setPurchase_ID(rs.getInt("purchase_id"));
                p.setPurchase_Note_ID(rs.getInt("purchase_note_no"));
                p.setSupplier_ID(rs.getInt("Supplier_Id"));
                p.setAmount(rs.getString("Amount"));
                p.setPur_Date(rs.getString("purchase_date"));
                list.add(p);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Purchase> displayS(int sID) {
        List<Purchase> list=new ArrayList<>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from purchase where supplier_id="+sID);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                Purchase p=new Purchase();
                p.setPurchase_ID(rs.getInt("purchase_id"));
                p.setPurchase_Note_ID(rs.getInt("purchase_note_no"));
                p.setSupplier_ID(rs.getInt("Supplier_Id"));
                p.setAmount(rs.getString("Amount"));
                p.setPur_Date(rs.getString("purchase_date"));
                list.add(p);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
