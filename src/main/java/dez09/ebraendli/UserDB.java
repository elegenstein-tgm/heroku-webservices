package dez09.ebraendli;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by fusions on 18.02.16.
 */
public class UserDB {




    /* Due to sqlite shittig errors it is not possible to connect
    *see*/
    public static String getUser(String uname, String upwd) {
        //LOG.info("getting name : " + this.name);
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/dez09");
            Connection conn = ds.getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select uemail from user WHERE uname LIKE '" + uname + "' AND upwd LIKE '" + upwd + "'");
            return rs.getString(1);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (NamingException ne) {
            ne.printStackTrace();
        }
        return null;
    }
    public static void putUser(String uemail, String uname, String upwd){
        Context ctx = null;

        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/dez09");
            Connection conn = ds.getConnection();
            Statement stat = conn.createStatement();
            stat.execute("INSERT INTO user VALUES ('" + uemail+"','" +uname+ "','"+upwd+"')");
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
