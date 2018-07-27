package cart;

import books.Books;
import books.Books_DAO;
import books.Books_DAO_Implt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Cart_DAO_Implt implements Cart_DAO{

    Connection conn=singleCopy.Database.getInstance().getConnection();
    
    @Override
    public boolean insert(Cart c) {
        try {
            PreparedStatement ps=conn.prepareStatement("insert into cart(Serial_No,Customer_id,book_id,Quantity) values("+getNextSNo()+",?,?,?)");
            ps.setInt(1, c.getcID());
            ps.setString(2, c.getBook_id());
            ps.setInt(3, c.getQuantity());
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
    public boolean update(Cart c) {
        try {
            PreparedStatement ps=conn.prepareStatement("Update cart set customer_id=?, book_id=?, quantity=? where serial_no=? ");
            ps.setInt(1, c.getcID());
            ps.setString(2, c.getBook_id());
            ps.setInt(3, c.getQuantity());
            ps.setInt(4, c.getSerial_no());
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
            PreparedStatement ps=conn.prepareStatement("delete from cart where serial_No="+sNo);
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
    public Cart display(int sNo) {
        Cart c=new Cart();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from cart where serial_no="+sNo);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                c.setSerial_no(rs.getInt("serial_no"));
                c.setcID(rs.getInt("customer_id"));
                c.setBook_id(rs.getString("Book_id"));
                c.setQuantity(rs.getInt("quantity"));
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public List<Cart> displayC(int cNo) {
        List<Cart> list=new ArrayList<>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from cart where customer_id="+cNo+" order by serial_no");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                Cart c=new Cart();
                c.setSerial_no(rs.getInt("serial_no"));
                c.setcID(rs.getInt("customer_id"));
                c.setBook_id(rs.getString("Book_id"));
                c.setQuantity(rs.getInt("quantity"));
                list.add(c);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Cart> display() {
        List<Cart> list=new ArrayList<>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from cart order by serial_no");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                Cart c=new Cart();
                c.setSerial_no(rs.getInt("serial_no"));
                c.setcID(rs.getInt("customer_id"));
                c.setBook_id(rs.getString("Book_id"));
                c.setQuantity(rs.getInt("quantity"));
                list.add(c);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    private int getNextSNo(){
        try {
            PreparedStatement ps=conn.prepareStatement("select max(serial_no) id from cart");
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
    public double sum(int cId) {
        double sum=0;
        try {
            PreparedStatement ps=conn.prepareStatement("select * from cart where customer_id="+cId);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                Books_DAO bdao=new Books_DAO_Implt();
                List<Books> list=bdao.display(rs.getString("book_id"));
                for (Books books : list) {
                    sum+=(Integer.valueOf(books.getPrice()) * rs.getInt("quantity"));
                }
            }
            ps.close();
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        return sum;
    }

    @Override
    public int item(int cId) {
        int count=0;
        try {
            PreparedStatement ps=conn.prepareStatement("select * from cart where customer_id="+cId);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                count++;
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
