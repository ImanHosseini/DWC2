import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ImanH on 12/3/2017.
 * Seyed Iman Hosseini Zavaraki
 * Github @ https://github.com/ImanHosseini
 * Wordpress @ https://imanhosseini.wordpress.com/
 */
public class MainLoop {
    public GUIHandler guih;
    public NetworkHandler neth;
    public int credit;
    HubCntrl hubcntrl;
    String player_name;
    public boolean running;
    public volatile String netQueue;
    public int key;
    public Cordinate centre;
    public World map;

    Timer timer;
    HashMap<Integer,String> widToName;
    public ArrayList<Msg> msgbox;
    public MainLoop(){
        timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(map!=null) neth.updReq();
            }
        }, 0, 6*1000);
        msgbox=new ArrayList<>();
        widToName=new HashMap<>();
        running=true;
        netQueue="x";
        this.running=true;
        guih=new GUIHandler(this);
        neth=new NetworkHandler(this);
        guih.start();
        //this.running=false;
    }

    public void printco(String s){
        System.out.println(s);
    }

    public void close(){
        this.running=false;
        guih.stop();
        timer.cancel();

    }

    public void fetchMsg(String msgz) {
        String[] spilt = msgz.split("\\*");
        for (String s : spilt) {
            String[] parts = s.split("%");
            if (parts[1].equals("S")) {
                msgbox.add(new Msg(-1, Integer.parseInt(parts[0]), parts[2], parts[3], true));
            } else {
                msgbox.add(new Msg(Integer.parseInt(parts[0]), -1, parts[2], parts[3], false));
            }

        }
    }

    public void addMsg(String msgz){
        String[] spilt = msgz.split("\\*");
        for (String s : spilt) {
            String[] parts = s.split("%");
            if (parts[1].equals("S")) {
                msgbox.add(new Msg(-1, Integer.parseInt(parts[0]), parts[2], parts[3], true));
            } else {
                msgbox.add(new Msg(Integer.parseInt(parts[0]), -1, parts[2], parts[3], false));
            }

        }
        hubcntrl.updateMbox();
    }

    public void setNames(String s){
        String[] valz=s.split("\\*");
        for(String ss:valz){
            String[] ans=ss.split("-");
            widToName.put(Integer.parseInt(ans[0]),ans[1]);
        }
    }

    public void updateCred(int ncred){
        this.credit=ncred;
        hubcntrl.PlayerCredit.setText(Integer.toString(ncred));
    }

}
