import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class HubCntrl {
    public Cordinate viewp;

    public Tooltip info;

    public HashMap<String,Image> imgs;

    public HCell[][] hcellz;

    public HashMap<String,Integer> nameToWid;


    public GUIHandler guih;

    @FXML
    private Button logout_button;

    @FXML
    public Canvas mapview;

    @FXML
    private Button left_button;

    @FXML
    private Button right_button;

    @FXML
    private Button up_button;

    @FXML
    private Button down_button;

    @FXML
    private Tab MsgTab;

    @FXML
    public ChoiceBox<String> MsgList;

    @FXML
    private TextArea MsgFromText;

    @FXML
    private ChoiceBox<String> msgTo;

    @FXML
    private TextArea msgToSend;

    @FXML
    private Button sendmsg_button;

    @FXML
    private Tab BattleTab;

    @FXML
    private ListView<?> BattleList;

    @FXML
    private Button showBRep;

    @FXML
    private Tab TroopsTab;

    @FXML
    private TableView<?> TroopsTable;

    @FXML
    private TableColumn<?, ?> TroopsTableType;

    @FXML
    private TableColumn<?, ?> TroopsTablePos;

    @FXML
    private TableColumn<?, ?> TroopsTableStat;

    @FXML
    private Button MoveTroopButton;

    @FXML
    private ChoiceBox<?> troop_selection;

    @FXML
    private Button recruit_button;

    @FXML
    private Label PlayerName;

    @FXML
    public Label PlayerCredit;

    @FXML
    void MoveTroop(ActionEvent event) {

    }

    @FXML
    void handledown_buttonAction(ActionEvent event) {
        viewp.y--;
        reDraw();


    }

    @FXML
    void handleleft_buttonAction(ActionEvent event) {
        viewp.x++;
        reDraw();
    }

    @FXML
    void handlelogout_buttonAction(ActionEvent event) {

    }

    @FXML
    void handleright_buttonAction(ActionEvent event) {
        viewp.x--;
        reDraw();
    }

    @FXML
    void handleup_buttonAction(ActionEvent event) {
        viewp.y++;
        reDraw();
    }

    @FXML
    void recruitTroop(ActionEvent event) {

    }

    @FXML
    void sendMsg(ActionEvent event) {
        String txt = msgToSend.getText();
        if(msgTo.getValue()!=null && nameToWid.containsKey(msgTo.getValue())){
            int to = nameToWid.get(msgTo.getValue());
            guih.ml.neth.sendMsg(to,txt);
            guih.ml.msgbox.add(new Msg(0,to,txt,new java.sql.Timestamp(new Date().getTime()).toString().split(".")[0],true));
            updateMbox();
        }


    }

    public void reDraw(){
        hcellz=new HCell[7][7];
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                hcellz[i][j]=new HCell(this,i,j,new Cordinate(viewp.x+(i-3),viewp.y+(j-3)));
            }
        }

        // Set cellz
        for(Drawable d:guih.ml.map.castles){
            int i = d.pos.x-viewp.x;
            int j = d.pos.y - viewp.y;
            if(Math.abs(i)<4 && Math.abs(j)<4){
                hcellz[i+3][j+3].itemz.add(d);
            }
        }

        for(Drawable d:guih.ml.map.resources){
            int i = d.pos.x-viewp.x;
            int j = d.pos.y - viewp.y;
            if(Math.abs(i)<4 && Math.abs(j)<4){
                hcellz[i+3][j+3].itemz.add(d);
            }
        }
        for(Drawable d:guih.ml.map.troops){
            int i = d.pos.x-viewp.x;
            int j = d.pos.y - viewp.y;
            if(Math.abs(i)<4 && Math.abs(j)<4){
                hcellz[i+3][j+3].itemz.add(d);
            }
        }
        drawhcellz();
    }


    public void updateMbox(){
        ArrayList<String> options=new ArrayList<>();
        for(int i=0;i<guih.ml.msgbox.size();i++){
            String str="";
            Msg m=guih.ml.msgbox.get(i);
            if(m.sent){
                str+=Integer.toString(i)+"#To:"+guih.ml.widToName.get(m.to)+"-"+m.dtime;
            }else{
                str+=Integer.toString(i)+"#From:"+guih.ml.widToName.get(m.from)+"-"+m.dtime;
            }
            options.add(str);
        }
        System.out.println("SIZE IS:"+options.size());
        MsgList.setItems(FXCollections.observableArrayList(options));

    }
    public void drawMap(){
        PlayerName.setText(guih.ml.player_name);
        PlayerCredit.setText(Integer.toString(guih.ml.credit));
        ArrayList<String> options=new ArrayList<>();
        for(int i=0;i<guih.ml.msgbox.size();i++){
            String str="";
            Msg m=guih.ml.msgbox.get(i);
            if(m.sent){
                str+=Integer.toString(i)+"#To:"+guih.ml.widToName.get(m.to)+"-"+m.dtime;
            }else{
                str+=Integer.toString(i)+"#From:"+guih.ml.widToName.get(m.from)+"-"+m.dtime;
            }
            options.add(str);
        }
        MsgList.setItems(FXCollections.observableArrayList(options));

        nameToWid=new HashMap<>();
        ArrayList<String> otherz=new ArrayList<>();
        for(Integer id:guih.ml.widToName.keySet()){
            if(guih.ml.widToName.get(id).equals(guih.ml.player_name)) continue;
            nameToWid.put(guih.ml.widToName.get(id),id);
            otherz.add(guih.ml.widToName.get(id));
        }

        msgTo.setItems(FXCollections.observableArrayList(otherz));




        MsgList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                MsgFromText.setText(guih.ml.msgbox.get(Integer.parseInt(newValue.toString())).txt);
            }
        });

        viewp=new Cordinate(guih.ml.centre.x,guih.ml.centre.y);
        imgs=new HashMap<>();
        info=new Tooltip();
        imgs.put("castle",new Image("castle.png"));
        imgs.put("gold",new Image("gold.png"));
        imgs.put("wood",new Image("wood.png"));
        imgs.put("Fbsd",new Image("Fbsd.png"));
        imgs.put("Lnx",new Image("Lnx.png"));
        imgs.put("war",new Image("fight.png"));

      reDraw();

        // tooltip
        mapview.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
               double event_x = event.getX();
               double event_y = event.getY();
               int i= (int) (7.0*(event_x/mapview.getWidth()));
               int j= (int) (7.0*(event_y/mapview.getHeight()));
               info.setText(hcellz[i][j].ttip);
               info.show(mapview,event.getScreenX()+5,event.getScreenY());
            }
        });
        mapview.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                info.hide();
            }
        });


        drawhcellz();

    }
    public void drawhcellz(){
        GraphicsContext gc = mapview.getGraphicsContext2D();

        gc.clearRect(0.0,0.0,mapview.getWidth(),mapview.getHeight());
        gc.setFill(Color.LIGHTGREEN);
        gc.fillRect(0.0,0.0,mapview.getWidth(),mapview.getHeight());

        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                hcellz[i][j].DrawCell();
            }
        }
    }


}
