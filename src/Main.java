import com.inventory.db.DBConnection;
import com.inventory.view.MainMenu;
import java.sql.Connection;
import com.inventory.db.TableCreator;

public class Main {

    static {
        try{
            Connection conn= DBConnection.getInstance();
            System.out.println("DB CONNECT ESTABLISHED");

            TableCreator.createAllTables();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {




        System.out.println(" WELCOME TO INVENTORY MANAGEMENT SYSTEM");

        MainMenu mainMenu = new MainMenu();
        mainMenu.show();
    }
}