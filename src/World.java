import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by ImanH on 1/28/2018.
 * Seyed Iman Hosseini Zavaraki
 * Github @ https://github.com/ImanHosseini
 * Wordpress @ https://imanhosseini.wordpress.com/
 */
public class World {
    ArrayList<Castle> castles;
    ArrayList<Troop> troops;
    ArrayList<Resource> resources;
    public World(){
        this.castles=new ArrayList<>();
        this.troops=new ArrayList<>();
        this.resources=new ArrayList<>();
    }
}
