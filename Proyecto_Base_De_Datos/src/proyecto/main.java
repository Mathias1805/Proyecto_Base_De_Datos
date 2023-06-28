
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
        
        ConexionDB connect_dat= new ConexionDB();
        Connection test = null;
        connect_dat.setData("sqlproject_","Oracle19c");
        try{
            test = connect_dat.getConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
        //PRELOAD
        GetAllPcts getpcts = new GetAllPcts(test);
        getAllEnferm getenfrm = new getAllEnferm(test);
        Login login = new Login(getenfrm.GetEfrmList());
        Homepage home = new Homepage();
        login.setVisible(true);

        
    }
    
}
