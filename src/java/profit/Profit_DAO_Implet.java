package profit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class Profit_DAO_Implet implements Profit_DAO{
    Connection conn=singleCopy.Database.getInstance().getConnection();

    @Override
    public boolean insert(Profit p) {
        try {
            PreparedStatement ps=conn.prepareStatement("insert into profit(transaction_no,sale_by,sale_from,profit_amount) values("+getNextId()+",?,?,?)");
            ps.setInt(1, p.getSaleBY());
            ps.setInt(2, p.getSaleFROM());
            ps.setString(3, p.getAmount());
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
    public boolean delete(int tNo) {
        try {
            PreparedStatement ps=conn.prepareStatement("delete from profit where transaction_no="+tNo);
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
    public List<Profit> display() {
        List<Profit> list=new ArrayList<>();
        try {
            PreparedStatement ps=conn.prepareStatement("select * from profit order by transaction_no");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Profit p=new Profit();
                p.setTransaction_no(rs.getInt("transaction_no"));
                p.setSaleBY(rs.getInt("sale_by"));
                p.setSaleFROM(rs.getInt("sale_from"));
                p.setAmount(rs.getString("profit_Amount"));
                p.setDate(DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.SHORT).format(rs.getDate("transaction_date")));
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
            PreparedStatement ps=conn.prepareStatement("select max(transaction_no) no from profit");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                return rs.getInt("no")+1;
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
}
