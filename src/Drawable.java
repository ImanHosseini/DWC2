/**
 * Created by ImanH on 1/28/2018.
 * Seyed Iman Hosseini Zavaraki
 * Github @ https://github.com/ImanHosseini
 * Wordpress @ https://imanhosseini.wordpress.com/
 */
public class Drawable {
    public Cordinate pos;
    public String imgType;
    public int owner_id;

    public void setOwner(int noid){
        this.owner_id=noid;
    }

    public void setPos(Cordinate np){
        this.pos=np;
    }

}
