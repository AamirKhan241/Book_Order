package category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Category_DAO_Implt implements Category_DAO{
    Connection conn=singleCopy.Database.getInstance().getConnection();
    @Override
    public boolean insert(Category c) {
        try {
            
//            PreparedStatement psT=conn.prepareStatement("create table Category(id int primary key,name varchar(50),description varchar(200),"
//                    + "image varchar(100))");
//            psT.executeUpdate();
            PreparedStatement ps=conn.prepareStatement("insert into category values("+getNextId()+",?,?,?)");
            ps.setString(1, c.getName());
            ps.setString(2, c.getDescription());
            ps.setString(3, c.getImage());
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
    public boolean update(Category c) {
        try {
            PreparedStatement ps=conn.prepareStatement("update category set category_name='"+c.getName()+"',description='"+c.getDescription()+"',"
                    + "image='"+c.getImage()+"' where id="+c.getId());
            
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
            PreparedStatement ps=conn.prepareStatement("delete from category where id="+id);
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
    public Category display(int id) {
        Category c=new Category();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from category order by id");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("category_name"));
                c.setDescription(rs.getString("description"));
                c.setImage(rs.getString("image"));
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public List<Category> display() {
        List<Category> list=new ArrayList<>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from category order by id");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                Category c=new Category();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("category_name"));
                c.setDescription(rs.getString("description"));
                c.setImage(rs.getString("image"));
                list.add(c);
            }
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    private int getNextId(){
        try {
            PreparedStatement ps=conn.prepareStatement("select max(id) id from category");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                return rs.getInt("id")+1;
            }                        
            ps.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
}
