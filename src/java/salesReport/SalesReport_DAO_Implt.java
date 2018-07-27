package salesReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SalesReport_DAO_Implt implements SalesReport_DAO{
    Connection conn=singleCopy.Database.getInstance().getConnection();
    @Override
    public boolean insert(SalesReport s) {
        try {
            PreparedStatement ps=conn.prepareStatement("insert into sales_report(invoice_no,customer_id,amount_to_pay,reciever_name,"
                    + "reciever_address,reciever_contact,transaction_mode,amount_paid,balance,description)"
                    + " values("+getNextInvoice()+",?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, s.getcID());
            ps.setString(2, s.getAmountToPay());
            ps.setString(3, s.getrName());
            ps.setString(4, s.getrAddress());
            ps.setString(5, s.getrContact());
            ps.setString(6, s.getTransMode());
            ps.setString(7, s.getAmountPaid());
            ps.setString(8, s.getBalance());
            ps.setString(9, s.getDescription());
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
    public boolean delete(int invoice) {
        try {
            PreparedStatement ps=conn.prepareStatement("delete from sales_report where invoice_no="+invoice);
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
    public boolean update(SalesReport s) {
        try {
            PreparedStatement ps=conn.prepareStatement("update sales_report set status=?,amount_paid=?,balance=? where invoice_no="+s.getInvoice());
            ps.setString(1, s.getStatus());
            ps.setString(2, s.getAmountPaid());
            ps.setString(3, s.getBalance());
            int i=ps.executeUpdate();
            if(i>0)
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public SalesReport display(int invoice) {
        SalesReport s=new SalesReport();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from sales_report where invoice_no="+invoice);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                s.setInvoice(rs.getInt("invoice_no"));
                s.setcID(rs.getInt("customer_id"));
                s.setAmountToPay(rs.getString("amount_to_pay"));
                s.setrName(rs.getString("reciever_name"));
                s.setrAddress(rs.getString("reciever_address"));
                s.setrContact(rs.getString("reciever_Contact"));
                s.setTransMode(rs.getString("transaction_mode"));
                s.setAmountPaid(rs.getString("amount_paid"));
                s.setBalance(rs.getString("balance"));
                s.setDescription(rs.getString("description"));
                s.setStatus(rs.getString("status"));
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public List<SalesReport> display() {
        List<SalesReport> list=new ArrayList<>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from sales_report order by invoice_no");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                SalesReport s=new SalesReport();
                s.setInvoice(rs.getInt("invoice_no"));
                s.setcID(rs.getInt("customer_id"));
                s.setAmountToPay(rs.getString("amount_to_pay"));
                s.setrName(rs.getString("reciever_name"));
                s.setrAddress(rs.getString("reciever_address"));
                s.setrContact(rs.getString("reciever_Contact"));
                s.setTransMode(rs.getString("transaction_mode"));
                s.setAmountPaid(rs.getString("amount_paid"));
                s.setBalance(rs.getString("balance"));
                s.setDescription(rs.getString("description"));
                s.setStatus(rs.getString("status"));
                list.add(s);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public int getNextInvoice(){
        try {
            PreparedStatement ps=conn.prepareStatement("select max(Invoice_No) id from sales_report");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                return rs.getInt("id")+1;
            }            
            ps.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public List<SalesReport> displayC(int cID) {
        List<SalesReport> list=new ArrayList<>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from sales_report where customer_id="+cID+" order by invoice_no");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                SalesReport s=new SalesReport();
                s.setInvoice(rs.getInt("invoice_no"));
                s.setcID(rs.getInt("customer_id"));
                s.setAmountToPay(rs.getString("amount_to_pay"));
                s.setrName(rs.getString("reciever_name"));
                s.setrAddress(rs.getString("reciever_address"));
                s.setrContact(rs.getString("reciever_Contact"));
                s.setTransMode(rs.getString("transaction_mode"));
                s.setAmountPaid(rs.getString("amount_paid"));
                s.setBalance(rs.getString("balance"));
                s.setDescription(rs.getString("description"));
                s.setStatus(rs.getString("status"));
                list.add(s);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<SalesReport> displayS(int sID) {
        List<SalesReport> list=new ArrayList<>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from sales_report where invoice_no in (select invoice_no from sales where supplier_id ="+sID+" )");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                SalesReport s=new SalesReport();
                s.setInvoice(rs.getInt("invoice_no"));
                s.setcID(rs.getInt("customer_id"));
                s.setAmountToPay(rs.getString("amount_to_pay"));
                s.setrName(rs.getString("reciever_name"));
                s.setrAddress(rs.getString("reciever_address"));
                s.setrContact(rs.getString("reciever_Contact"));
                s.setTransMode(rs.getString("transaction_mode"));
                s.setAmountPaid(rs.getString("amount_paid"));
                s.setBalance(rs.getString("balance"));
                s.setDescription(rs.getString("description"));
                s.setStatus(rs.getString("status"));
                list.add(s);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
