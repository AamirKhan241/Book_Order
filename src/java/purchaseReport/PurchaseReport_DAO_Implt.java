package purchaseReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PurchaseReport_DAO_Implt implements PurchaseReport_DAO{
    Connection conn=singleCopy.Database.getInstance().getConnection();
    
    @Override
    public boolean insert(PurchaseReport pr) {
        try {
            PreparedStatement ps=conn.prepareStatement("insert into purchase_report values("+getNextID()+",'"+pr.getAmount_To_Pay()+"','"
                    +pr.getBook_ISBN()+"','"+pr.getCopies()+"','"+pr.getPrice()+"','"+pr.getAmount_Paid()+"','"+pr.getBalance()+"',"+
                    pr.getSupplier_Id()+")");
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
    public boolean update(PurchaseReport pr) {
        try {
            PreparedStatement ps=conn.prepareStatement("update purchase_Report set amount_to_pay='"+pr.getAmount_To_Pay()+"',amount_paid='"+
                    pr.getAmount_Paid()+"',balance='"+pr.getBalance()+"' where purchase_note_no="+pr.getPurchase_Note_Id());
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
    public boolean delete(int pnID) {
        try {
            PreparedStatement ps=conn.prepareStatement("delete from purchase_report where purchase_note_no="+pnID);
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
    public int getNextID() {
        try {
            PreparedStatement ps=conn.prepareStatement("select max(purchase_note_no) id from purchase_report");
            ResultSet rs=ps.executeQuery();
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
    public PurchaseReport display(int pnID) {
        PurchaseReport pr=new PurchaseReport();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from purchase_report where purchase_note_no="+pnID);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                pr.setPurchase_Note_Id(rs.getInt("purchase_note_no"));
                pr.setAmount_To_Pay(rs.getString("Amount_To_Pay"));
                pr.setBook_ISBN(rs.getString("Book_isbn"));
                pr.setCopies(rs.getString("Copies"));
                pr.setAmount_Paid(rs.getString("Amount_paid"));
                pr.setBalance(rs.getString("balance"));
                pr.setSupplier_Id(rs.getInt("Supplier_Id"));
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pr;
    }

    @Override
    public List<PurchaseReport> display() {
        List<PurchaseReport> list=new ArrayList<>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from purchase_report order by purchase_note_no");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                PurchaseReport pr=new PurchaseReport();
                pr.setPurchase_Note_Id(rs.getInt("purchase_note_no"));
                pr.setAmount_To_Pay(rs.getString("Amount_To_Pay"));
                pr.setBook_ISBN(rs.getString("Book_isbn"));
                pr.setCopies(rs.getString("Copies"));
                pr.setAmount_Paid(rs.getString("Amount_paid"));
                pr.setBalance(rs.getString("balance"));
                pr.setSupplier_Id(rs.getInt("Supplier_Id"));
                list.add(pr);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<PurchaseReport> displayS(int sID) {
        List<PurchaseReport> list=new ArrayList<>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from purchase_report where supplier_id="+sID+" order by purchase_note_no");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                PurchaseReport pr=new PurchaseReport();
                pr.setPurchase_Note_Id(rs.getInt("purchase_note_no"));
                pr.setAmount_To_Pay(rs.getString("Amount_To_Pay"));
                pr.setBook_ISBN(rs.getString("Book_isbn"));
                pr.setCopies(rs.getString("Copies"));
                pr.setAmount_Paid(rs.getString("Amount_paid"));
                pr.setBalance(rs.getString("balance"));
                pr.setBalance(rs.getString("balance"));
                pr.setSupplier_Id(rs.getInt("Supplier_Id"));
                list.add(pr);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
