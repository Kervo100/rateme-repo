package database;

/**
 * Created by kervinzeller on 26.07.15.
 */
        import java.sql.*;
        import java.util.logging.Level;
        import java.util.logging.Logger;

public class Db_Connection
{
    public Connection Connection()
    {
        try
        {

            Class.forName("com.mysql.jdbc.Driver");
            String rateMe = "jdbc:mysql://localhost:3306/techworld3g?user=root";
            Connection myConnection = DriverManager.getConnection(rateMe);

            return myConnection;

        } catch (ClassNotFoundException | SQLException ex) {Logger.getLogger(Db_Connection.class.getName()).log(Level.SEVERE, null, ex);}
        return null;
    }
}

