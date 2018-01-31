/**
 * Created by ImanH on 1/29/2018.
 * Seyed Iman Hosseini Zavaraki
 * Github @ https://github.com/ImanHosseini
 * Wordpress @ https://imanhosseini.wordpress.com/
 */
public class Msg {
    public int from;
    public int to;
    public String txt;
    public String dtime;
    public boolean sent;
    public Msg(int from, int to, String txt, String dtime, boolean sent){
        this.from=from;
        this.to=to;
        this.txt=txt;
        this.dtime=dtime;
        this.sent=sent;
    }
}
