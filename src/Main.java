import com.inventory.db.DBConnection;
import com.inventory.view.MainMenu;
import java.sql.Connection;
public class Main {
    public static void main(String[] args) {


        try{
            Connection conn= DBConnection.getInstance();
            System.out.println("DB CONNECT ESTABLISHED");

        }
        catch(Exception e){
            e.printStackTrace();
        }

        System.out.println(" WELCOME TO INVENTORY MANAGEMENT SYSTEM");

        MainMenu mainMenu = new MainMenu();
        mainMenu.show();
    }
}