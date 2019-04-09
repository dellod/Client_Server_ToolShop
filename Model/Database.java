package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.TimeZone;

public class Database {

    Connection myConn;
    Statement stm;

    public Database() {
        try {

            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Shop_Database?serverTimezone=" + TimeZone.getDefault().getID(), "root", ".2539539Dd");
            stm = myConn.createStatement();

        } catch (Exception e) {
            System.out.println("Connection error " + e.getMessage());
        }
    }


    public ArrayList<Item> getItemList() {
        try {
            ArrayList<Item> toSend = new ArrayList<>();
            ResultSet rs = stm.executeQuery("select * from items");
            while (rs.next()) {
                String s = rs.getInt("id") + ";" + rs.getString("tool_name") + ";" + rs.getInt("quantity") +
                        ";" + rs.getDouble("price") + ";" + rs.getInt("supplier_id");
                Item item = new Item(s);
                toSend.add(item);

            }
            return toSend;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return null;
    }

    public ArrayList<Supplier> getSupplierList(){

        try {
            ArrayList<Supplier> toSend = new ArrayList<>();
            ResultSet rs = stm.executeQuery("select * from suppliers");
            while (rs.next()) {
                String s = rs.getInt("id") + ";" + rs.getString("company_name") + ";" + rs.getString("address") +
                        ";" + rs.getString("sales_contact");
                Supplier sup = new Supplier(s);
                toSend.add(sup);

            }
            return toSend;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return null;

    }


    public Item searchById(int id) {
        try {

            ResultSet rs = stm.executeQuery("select * from items");
            while (rs.next()) {
                if (rs.getInt("id") == id){
                    String s = rs.getInt("id") + rs.getString("tool_name") + rs.getInt("quantity") +
                            rs.getDouble("price") + rs.getInt("supplier_id");
                    Item toSend = new Item(s);
                    return toSend;
            }
            }
        } catch (SQLException e) {
            System.out.println("Problem with resultSet = " + e.getMessage());
        }

        return null;
    }

    public Item searchByName(String item) {
        try {

            ResultSet rs = stm.executeQuery("select * from items");
            while (rs.next()) {
                if (rs.getString("tool_name").equals(item)){
                    String s = rs.getInt("id") + rs.getString("tool_name") + rs.getInt("quantity") +
                            rs.getDouble("price") + rs.getInt("supplier_id");
                    Item toSend = new Item(s);
                    return toSend;
                }
            }
        } catch (SQLException e) {
            System.out.println("Problem with resultSet = " + e.getMessage());
        }

        return null;
    }

    public int checkQuantity(String item){

        try {
            PreparedStatement ps = myConn.prepareStatement("select * from items where tool_name = ?");
            ps.setString(1, item);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return(rs.getInt("id"));
            }



        } catch (SQLException e) {
            System.out.println("Problem with resultSet = " + e.getMessage());
        }

        return -1;
    }



    public synchronized void decreaseQuantity(int id, int num) {
        try {

            System.out.println("CAME TO DECREASE");
            int old = 0, tobe;
            System.out.println(id);
            PreparedStatement ps = myConn.prepareStatement("select * from items where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                System.out.println(rs.getInt("quantity"));
                old = rs.getInt("quantity");
            }

            tobe = old - num;

            if(tobe >= 0) {

                ps = myConn.prepareStatement("UPDATE items SET quantity = ? WHERE id = ?");
                System.out.println("test");
                ps.setInt(1, tobe);
                ps.setInt(2, id);
                //rs = ps.executeQuery();
                ps.executeUpdate();

            }

        }catch (SQLException e){
            System.out.println("No such item" + e.getErrorCode());
        }

    }

    public synchronized void setQuantity(int id, int num) {
        try {

            System.out.println("CAME TO SET");

            System.out.println(id);
                PreparedStatement  ps = myConn.prepareStatement("UPDATE items SET quantity = ? WHERE id = ?");
                ps.setInt(1, num);
                ps.setInt(2, id);
                //rs = ps.executeQuery();
                ps.executeUpdate();


        }catch (SQLException e){
            System.out.println("No such item" + e.getErrorCode());
        }

    }
}