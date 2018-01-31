import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Tooltip;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ImanH on 1/29/2018.
 * Seyed Iman Hosseini Zavaraki
 * Github @ https://github.com/ImanHosseini
 * Wordpress @ https://imanhosseini.wordpress.com/
 */
public class HCell {
    Cordinate pos;
    int i,j;
    ArrayList<Drawable> itemz;
    HubCntrl hc;
    String ttip;
    public HCell(HubCntrl hc, int i, int j, Cordinate pos){
        itemz=new ArrayList<>();
        this.i=i;
        this.j=j;
        this.hc=hc;
        this.pos=pos;
        this.ttip=pos.toString();
    }
    public void DrawCell(){
        String msg=pos.toString();
        if(itemz.size()==0) {
            ttip=msg;
            return;
        }
        Canvas mapview=hc.mapview;
        if(itemz.size()==1){
            if(itemz.get(0) instanceof Troop){
                GraphicsContext gc = mapview.getGraphicsContext2D();
                gc.drawImage(hc.imgs.get(itemz.get(0).imgType),(1.0/7.0)*(double)i*mapview.getWidth(),(1.0/7.0)*(double)j*mapview.getHeight(),(1.0/7.0)*mapview.getWidth(),(1.0/7.0)*mapview.getHeight());
                msg+="\n"+hc.guih.ml.widToName.get(itemz.get(0).owner_id)+":"+1;
                ttip=msg;
                return;
            }
            GraphicsContext gc = mapview.getGraphicsContext2D();
            gc.drawImage(hc.imgs.get(itemz.get(0).imgType),(1.0/7.0)*(double)i*mapview.getWidth(),(1.0/7.0)*(double)j*mapview.getHeight(),(1.0/7.0)*mapview.getWidth(),(1.0/7.0)*mapview.getHeight());
            if(itemz.get(0).owner_id!=-1){
                msg+="\n"+"owner:"+hc.guih.ml.widToName.get(itemz.get(0).owner_id);
            }
            if(itemz.get(0) instanceof Resource){
                msg+="\n"+"value:"+((Resource) itemz.get(0)).value;
            }
            ttip=msg;
            return;
        }
        boolean isCastle=false;
        boolean isRes=false;
        Resource res=null;
        HashMap<Integer,Integer> armies=new HashMap<>();
        Castle c=null;
        boolean isLnx=false;
        boolean isFb=false;
        for(Drawable d :itemz){
            if(d instanceof Castle){
                isCastle=true;
                c=(Castle)d;
            }
            if(d instanceof Resource){
                isRes=true;
                res=(Resource)d;
            }
            if(d instanceof Troop){
                if(d.imgType.equals("Lnx")){
                    isLnx=true;
                }else{
                    isFb=true;
                }
                if(armies.keySet().contains(d.owner_id)){
                    armies.put(d.owner_id,armies.get(d.owner_id)+1);
                }else{
                    armies.put(d.owner_id,1);
                }
            }
        }
        if(isCastle || isRes ){
            if(armies.keySet().size()>1){
                if(isCastle){
                    msg+="\nowner:"+hc.guih.ml.widToName.get(c.owner_id);
                }else{
                    msg+="\nowner:"+hc.guih.ml.widToName.get(res.owner_id);
                }
                GraphicsContext gc = mapview.getGraphicsContext2D();
                gc.drawImage(hc.imgs.get("war"),(1.0/7.0)*(double)i*mapview.getWidth(),(1.0/7.0)*(double)j*mapview.getHeight(),(1.0/7.0)*mapview.getWidth(),(1.0/7.0)*mapview.getHeight());
                msg+="\nFighting";
                for(Integer a : armies.keySet()){
                    msg+="\n"+hc.guih.ml.widToName.get(a)+" army:"+armies.get(a).toString();
                }
            }else{
                GraphicsContext gc = mapview.getGraphicsContext2D();

                if(isCastle){
                    gc.drawImage(hc.imgs.get(c.imgType),(1.0/7.0)*(double)i*mapview.getWidth(),(1.0/7.0)*(double)j*mapview.getHeight(),(1.0/7.0)*mapview.getWidth(),(1.0/7.0)*mapview.getHeight());
                    msg+="\nowner:"+hc.guih.ml.widToName.get(c.owner_id);
                }else{
                    gc.drawImage(hc.imgs.get(res.imgType),(1.0/7.0)*(double)i*mapview.getWidth(),(1.0/7.0)*(double)j*mapview.getHeight(),(1.0/7.0)*mapview.getWidth(),(1.0/7.0)*mapview.getHeight());
                    msg+="\nowner:"+hc.guih.ml.widToName.get(res.owner_id);
                }

                for(Integer a:armies.keySet()){
                    msg+="\nGarrison:"+armies.get(a).toString();
                }

            }
            ttip=msg;
            return;
        }


        for(Integer a : armies.keySet()){
            msg+="\n"+hc.guih.ml.widToName.get(a)+" army:"+armies.get(a).toString();
        }
        GraphicsContext gc = mapview.getGraphicsContext2D();

        if(isLnx && isFb){
            gc.drawImage(hc.imgs.get("Lnx"),(1.0/7.0)*(double)i*mapview.getWidth(),(1.0/7.0)*(double)j*mapview.getHeight(),0.5*(1.0/7.0)*mapview.getWidth(),0.5*(1.0/7.0)*mapview.getHeight());
            gc.drawImage(hc.imgs.get("Fbsd"),(1.0/7.0)*(double)i*mapview.getWidth()+0.5*(1.0/7.0)*mapview.getWidth(),(1.0/7.0)*(double)j*mapview.getHeight()+0.5*(1.0/7.0)*mapview.getHeight(),0.5*(1.0/7.0)*mapview.getWidth(),0.5*(1.0/7.0)*mapview.getHeight());

        }else{
            if(isLnx)             gc.drawImage(hc.imgs.get("Lnx"),(1.0/7.0)*(double)i*mapview.getWidth(),(1.0/7.0)*(double)j*mapview.getHeight(),(1.0/7.0)*mapview.getWidth(),(1.0/7.0)*mapview.getHeight());
            else gc.drawImage(hc.imgs.get("Fbsd"),(1.0/7.0)*(double)i*mapview.getWidth(),(1.0/7.0)*(double)j*mapview.getHeight(),(1.0/7.0)*mapview.getWidth(),(1.0/7.0)*mapview.getHeight());
        }


        ttip=msg;



    }

}
