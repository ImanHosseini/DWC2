/**
 * Created by ImanH on 1/28/2018.
 * Seyed Iman Hosseini Zavaraki
 * Github @ https://github.com/ImanHosseini
 * Wordpress @ https://imanhosseini.wordpress.com/
 */
public class Gold extends Resource {

    public Gold(Cordinate c, int val, int oid){
        this.imgType="gold";
        this.lossRate=Resource.GoldLR;
        this.value=val;
        this.pos=c;
        this.owner_id=oid;
    }

}
