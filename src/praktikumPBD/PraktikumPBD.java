package praktikumPBD;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author LENOVO
 */
public class PraktikumPBD {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL      = "jdbc:mysql://localhost/instagram_store";
    static final String username    = "root";
    static final String password    = "";
    
    static Connection con;
    static Statement st;
    static ResultSet rs;
    
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
     
          koneksi();
          String pilihanhidup = JOptionPane.showInputDialog("pilih\n 1.registrasi\n"
                  + " 2.login");
          switch(pilihanhidup){
              case "1" :regis();
                  break;
              case "2" :
                  try {
                            if (login()) {
                                tampildata();
                            } else {
                                System.out.println("salah");
                            }
                        } catch (Exception e) {
                            e.getMessage();
                        }
                  break;    
          }
    }      
    static void koneksi() {      
        try {//biasanya digunakan pada error ending
            Class.forName(JDBC_DRIVER);
            con    = DriverManager.getConnection(DB_URL,username,password);
            st    = con.createStatement();
            System.out.println("Koneksi Berhasil");
            
        } catch (Exception e) {
            System.out.println("Koneksi Gagal");
            e.getMessage();
        }
    }
    
    static void regis() {

        String username = JOptionPane.showInputDialog("Masukkan Username :");
        String password = JOptionPane.showInputDialog("Masukkan Password :");
        
        String sql = "insert into user (username,password) values ('"+ username + "','" + password + "')";
        try {
            st.executeUpdate(sql);
            login();
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    static boolean login() {
        boolean cek;
        try {
            cek = false;
            String user = JOptionPane.showInputDialog("Masukkan Username Anda :");
            String pass = JOptionPane.showInputDialog("Masukkan password :");
            String sql = "select * from user where username='" + user + "'and password = '" + pass + "'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                cek = true;
//                tampildata();
            }
            return cek;
        } catch (Exception e) {
            cek = false;
            return cek;
        }
    }
    
     static void tampildata() {
        try {
            String sql = "SELECT * FROM datatoko";
            rs      = st.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getString("nama_toko"));
            }
            
        } catch (Exception e) {
        }
    }
    
     
}
    
    
    

