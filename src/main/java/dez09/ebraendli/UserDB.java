package dez09.ebraendli;

import java.net.URISyntaxException;
import java.sql.*;

/**
 * Created by fusions on 18.02.16.
 */
public class UserDB {




    /* Due to sqlite shittig errors it is not possible to connect
    *see*/


    private static Connection getConnection() throws URISyntaxException, SQLException {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("pg not found");
        }
        return DriverManager.getConnection(dbUrl+"?ssl=true");
    }

    public static String getUser(String uname, String upwd) {
        uname = uname.toLowerCase();
        //LOG.info("getting name : " + this.name);
        try {
//            Context ctx = new InitialContext();
//            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/dez09");
            Connection conn = getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select uemail from \"user\" WHERE uname LIKE '" + uname + "' AND upwd LIKE '" + upwd + "'");
            if (rs.next())
                return "Hello user of email-adr: "+ rs.getString(1);
            return "Meh. I dont think you should access that....";
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static  String putUser(String uemail, String uname, String upwd){
        uemail = uemail.toLowerCase();
        uname = uname.toLowerCase();
      /*  Context ctx = null;

        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.FSContextFactory");
        env.put(Context.PROVIDER_URL, "file:/");
        env.put(Context.OBJECT_FACTORIES, "foo.bar.ObjFactory");
        env.put("foo", "bar");
*/
        try {
        //    ctx = new InitialContext(env);
          //  ctx = (Context)ctx.lookup("java:comp/env");
          //  System.out.println(ctx.getEnvironment());
          //  DataSource ds = (DataSource)ctx.lookup("jdbc/dez09");
            Connection conn = getConnection();
            Statement stat = conn.createStatement();
            if(!stat.executeQuery("SELECT *  from \"user\" where uemail like '"+uemail+"'").next()) {
                stat.close();
                stat = conn.createStatement();
                stat.execute("INSERT INTO \"user\" VALUES ('" + uemail + "','" + uname + "','" + upwd + "')");
                return "Successfully created!";
            }
            else
                return "Something is wrong with your credentials";
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "that should never happen. Im so sorry for that!";
    }

}
