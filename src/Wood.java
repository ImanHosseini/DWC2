/**
 * Created by ImanH on 1/28/2018.
 * Seyed Iman Hosseini Zavaraki
 * Github @ https://github.com/ImanHosseini
 * Wordpress @ https://imanhosseini.wordpress.com/
 */
public class Wood extends Resource {

    public Wood(Cordinate c, int val, int oid){
        this.owner_id=oid;
        this.imgType="wood";
        this.lossRate=Resource.WoodLR;
        this.value=val;
        this.pos=c;
    }

}
