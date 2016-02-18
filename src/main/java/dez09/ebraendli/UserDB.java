package dez09.ebraendli;

import javax.naming.Context;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

/**
 * Created by fusions on 18.02.16.
 */
public class UserDB {




    /* Due to sqlite shittig errors it is not possible to connect
    *see*/


    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        return DriverManager.getConnection(dbUrl, username, password);
    }

    public static String getUser(String uname, String upwd) {
        //LOG.info("getting name : " + this.name);
        try {
//            Context ctx = new InitialContext();
//            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/dez09");
            Connection conn = getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select uemail from user WHERE uname LIKE '" + uname + "' AND upwd LIKE '" + upwd + "'");
            return rs.getString(1);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void putUser(String uemail, String uname, String upwd){
        Context ctx = null;

        try {
            //ctx = new InitialContext();
            //DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/dez09");
            Connection conn = getConnection();
            Statement stat = conn.createStatement();
            stat.execute("INSERT INTO user VALUES ('" + uemail+"','" +uname+ "','"+upwd+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

}
