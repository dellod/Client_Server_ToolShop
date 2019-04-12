package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.TimeZone;

/**
 *  The class is created as a Database to connect to the database on localhost
 *  The data for the suppliers and items are stored externally and the program
 *  just accesses it using the .getConnection
 *
 * @author Ilyas Ganiyev, Daryl Dang, Will Huang
 * @since April 8, 2019
 * @version 1.0
 */
public class Database {

    Connection myConn;
    Statement stm;

    /**
     * Constructor for the Database class, which connects to the database
     * as well as creates a statement
     */

    public Database() {
        try {

            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Shop_Database?serverTimezone=" + TimeZone.getDefault().getID(), "root", ".2539539Dd");
            stm = myConn.createStatement();

            System.out.println();

        } catch (Exception e) {
            System.out.println("Connection error " + e.getMessage());
        }
    }

    /**
     * The method is accessing Item table on the Shop_Database and
     * adds every single item into an arrayList and then returns it
     * @return ArrayList<Item> arrayList of all items
     */


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

    /**
     * The method is accessing Supplier table on the Shop_Database
     * adds every single supplier into an arrayList and then returns it
     * Catches an SQL error if it occurs
     * @return ArrayList<Supplier> arrayList of all suppliers
     */
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

    /**
     * Accesses the database Item table and decreases the quantity of an item by the amount provided
     * Catches an SQL error if it occurs
     * @param id of an item
     * @param num amount to be decreased
     */

    public synchronized void decreaseQuantity(int id, int num) {
        try {

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

    /**
     * Accesses the database Item table and sets the quantity of an item to a specified amount
     * Catches an SQL error if it occurs
     * @param id of an item
     * @param num amount to be set to
     */

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

    /* Not used in the project since we store everything in an ArrayList

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


*/



}