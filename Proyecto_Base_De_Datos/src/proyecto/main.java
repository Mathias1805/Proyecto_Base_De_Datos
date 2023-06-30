
package proyecto;
import bdproyectofile.*;
import java.sql.Connection;
/**
 *
 * @author MATHIAS
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        

        Login login = new Login();
        Homepage home = new Homepage();
        login.setVisible(false);
        home.setVisible(true);

        
    }
    
}
