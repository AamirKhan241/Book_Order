package sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class Sale_DAO_Implt implements Sale_DAO{
    Connection conn= singleCopy.Database.getInstance().getConnection();
    
    @Override
    public boolean insert(Sale s) {
        try {
            PreparedStatement ps=conn.prepareStatement("insert into sales(sales_id,customer_id,Amount,invoice_no,book_isbn,copies,supplier_ID) values("+getNextId()+",?,?,?,?,?,?)");
            ps.setInt(1, s.getCustomer_id());
            ps.setString(2, s.getAmount());
            ps.setInt(3, s.getInvoice());
            ps.setString(4, s.getISBN());
            ps.setString(5, s.getCopies());
            ps.setInt(6, s.getSupplier_id());
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
    public boolean update(Sale s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int Sales_Id) {
        try {
            PreparedStatement ps=conn.prepareStatement("delete from sales wher sales_id="+Sales_Id);
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
    public boolean deleteI(int invoice) {
        try {
            PreparedStatement ps=conn.prepareStatement("delete from sales where invoice_no="+invoice);
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
    public Sale display(int Sales_id) {
        Sale s=new Sale();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from sales where sales_id="+Sales_id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                s.setSale_id(rs.getInt("Sales_id"));
                s.setCustomer_id(rs.getInt("Customer_id"));
                s.setDate( DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.SHORT).format(rs.getDate("sale_date")));
                s.setAmount(rs.getString("Amount"));
                s.setInvoice(rs.getInt("invoice_no"));
                s.setISBN(rs.getString("Book_isbn"));
                s.setCopies(rs.getString("Copies"));
                s.setSupplier_id(rs.getInt("Supplier_id"));
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public List<Sale> display() {
        List<Sale> list=new ArrayList<>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from sales order by sales_id");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Sale s=new Sale();
                s.setSale_id(rs.getInt("Sales_id"));
                s.setCustomer_id(rs.getInt("Customer_id"));
                s.setDate( DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.SHORT).format(rs.getDate("sale_date")));
                s.setAmount(rs.getString("Amount"));
                s.setInvoice(rs.getInt("invoice_no"));
                s.setISBN(rs.getString("Book_isbn"));
                s.setCopies(rs.getString("Copies"));
                s.setSupplier_id(rs.getInt("Supplier_id"));
                list.add(s);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Sale> displayI(int invoice) {
        List<Sale> list=new ArrayList<>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from sales where invoice_no="+invoice+" order by sales_id");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Sale s=new Sale();
                s.setSale_id(rs.getInt("Sales_id"));
                s.setCustomer_id(rs.getInt("Customer_id"));
                s.setDate( DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.SHORT).format(rs.getDate("sale_date")));
                s.setAmount(rs.getString("Amount"));
                s.setInvoice(rs.getInt("invoice_no"));
                s.setISBN(rs.getString("Book_isbn"));
                s.setCopies(rs.getString("Copies"));
                s.setSupplier_id(rs.getInt("Supplier_id"));
                list.add(s);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public int getNextId(){
        try {
            PreparedStatement ps=conn.prepareStatement("select max(Sales_id) id from sales");
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
    public String getDate(int invoice) {
        String msg="";
        try {
            PreparedStatement ps=conn.prepareStatement("select * from sales where invoice_no="+invoice);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                msg=DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.SHORT).format(rs.getDate("sale_date"));
                break;
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public List<Sale> displayS(int sID) {
        List<Sale> list=new ArrayList<>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from sales where Supplier_id="+sID+" order by sales_id");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Sale s=new Sale();
                s.setSale_id(rs.getInt("Sales_id"));
                s.setCustomer_id(rs.getInt("Customer_id"));
                s.setDate( DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.SHORT).format(rs.getDate("sale_date")));
                s.setAmount(rs.getString("Amount"));
                s.setInvoice(rs.getInt("invoice_no"));
                s.setISBN(rs.getString("Book_isbn"));
                s.setCopies(rs.getString("Copies"));
                s.setSupplier_id(rs.getInt("Supplier_id"));
                list.add(s);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
}
