package books;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import profile.Profiles_DAO;
import profile.Profiles_DAO_Implt;
import publisher.Publisher_DAO;
import publisher.Publisher_DAO_Implt;

public class Books_DAO_Implt implements Books_DAO{
    Connection conn=singleCopy.Database.getInstance().getConnection();

    @Override
    public boolean insert(Books b) {
        try {
            PreparedStatement ps=conn.prepareStatement("insert into books (Serial_No,name,ISBN,Publisher,Store_Location,Copies,"
                    + "Category,Edition,Price,Author,image) values("+getNextId()+",?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, b.getName());
            ps.setString(2, b.getISBN());
            ps.setString(3, b.getPublisher());
            ps.setString(4, b.getStore_Location());
            ps.setString(5, b.getCopies());
            ps.setString(6, b.getCategory());
            ps.setString(7, b.getEdition());
            ps.setString(8, b.getPrice());
            ps.setString(9, b.getAuthor());
            ps.setString(10, b.getImage());
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
    public boolean update(Books b) {
        try {
            PreparedStatement ps=conn.prepareStatement("update books set name=?,ISBN=?,Publisher=?,Store_Location=?,Copies=?,"
                    + "Category=?,Edition=?,Price=?,Author=?,image=? where serial_No="+b.getSerial_No()+"");
            ps.setString(1, b.getName());
            ps.setString(2, b.getISBN());
            ps.setString(3, b.getPublisher());
            ps.setString(4, b.getStore_Location());
            ps.setString(5, b.getCopies());
            ps.setString(6, b.getCategory());
            ps.setString(7, b.getEdition());
            ps.setString(8, b.getPrice());
            ps.setString(9, b.getAuthor());
            ps.setString(10, b.getImage());
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
    public boolean delete(int Sno) {
        try {
            PreparedStatement ps=conn.prepareStatement("delete from books where serial_no="+Sno);
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
    public Books display(int sno) {
        Books b=new Books();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from books where serial_no="+sno);
            ResultSet rs=ps.executeQuery();
            
            while (rs.next()) {                
                b.setSerial_No(rs.getInt("serial_no"));
                b.setName(rs.getString("name"));
                b.setISBN(rs.getString("isbn"));
                b.setPublisher(rs.getString("publisher"));
                b.setStore_Location(rs.getString("Store_Location"));
                b.setCopies(rs.getString("copies"));
                b.setCategory(rs.getString("category"));
                b.setEdition(rs.getString("edition"));
                b.setPrice(rs.getString("price"));
                b.setAuthor(rs.getString("author"));
                b.setImage(rs.getString("image"));
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public List<Books> display() {
        List<Books> list=new ArrayList<>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from books order by serial_no");
            ResultSet rs=ps.executeQuery();
            
            while (rs.next()) {                
                Publisher_DAO pdao=new Publisher_DAO_Implt();
                Profiles_DAO pdao1=new Profiles_DAO_Implt();
                if(pdao1.display(pdao.displaySi(rs.getString("publisher")).getSupplier_Id()).getStatus().equalsIgnoreCase("active")){
                    Books b=new Books();
                    b.setSerial_No(rs.getInt("serial_no"));
                    b.setName(rs.getString("name"));
                    b.setISBN(rs.getString("isbn"));
                    b.setPublisher(rs.getString("publisher"));
                    b.setStore_Location(rs.getString("Store_Location"));
                    b.setCopies(rs.getString("copies"));
                    b.setCategory(rs.getString("category"));
                    b.setEdition(rs.getString("edition"));
                    b.setPrice(rs.getString("price"));
                    b.setAuthor(rs.getString("author"));
                    b.setImage(rs.getString("image"));
                    list.add(b);
                }
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public int getNextId(){
        try {
            PreparedStatement ps=conn.prepareStatement("select max(serial_no) id from books");
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
    public List<Books> display(String ISBN) {
        List<Books> list=new ArrayList<>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from books where isbn='"+ISBN+"' order by name");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                Publisher_DAO pdao=new Publisher_DAO_Implt();
                Profiles_DAO pdao1=new Profiles_DAO_Implt();
                if(pdao1.display(pdao.displaySi(rs.getString("publisher")).getSupplier_Id()).getStatus().equalsIgnoreCase("active")){
                    Books b=new Books();
                    b.setSerial_No(rs.getInt("serial_no"));
                    b.setName(rs.getString("name"));
                    b.setISBN(rs.getString("isbn"));
                    b.setPublisher(rs.getString("publisher"));
                    b.setStore_Location(rs.getString("Store_Location"));
                    b.setCopies(rs.getString("copies"));
                    b.setCategory(rs.getString("category"));
                    b.setEdition(rs.getString("edition"));
                    b.setPrice(rs.getString("price"));
                    b.setAuthor(rs.getString("author"));
                    b.setImage(rs.getString("image"));
                    list.add(b);
                }
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Books> displayC(String category) {
        List<Books> list=new ArrayList<>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from books where category='"+category+"' order by name");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                Publisher_DAO pdao=new Publisher_DAO_Implt();
                Profiles_DAO pdao1=new Profiles_DAO_Implt();
                if(pdao1.display(pdao.displaySi(rs.getString("publisher")).getSupplier_Id()).getStatus().equalsIgnoreCase("active")){
                    Books b=new Books();
                    b.setSerial_No(rs.getInt("serial_no"));
                    b.setName(rs.getString("name"));
                    b.setISBN(rs.getString("isbn"));
                    b.setPublisher(rs.getString("publisher"));
                    b.setStore_Location(rs.getString("Store_Location"));
                    b.setCopies(rs.getString("copies"));
                    b.setCategory(rs.getString("category"));
                    b.setEdition(rs.getString("edition"));
                    b.setPrice(rs.getString("price"));
                    b.setAuthor(rs.getString("author"));
                    b.setImage(rs.getString("image"));
                    list.add(b);
                }
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
