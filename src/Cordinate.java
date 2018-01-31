/**
 * Created by ImanH on 1/28/2018.
 * Seyed Iman Hosseini Zavaraki
 * Github @ https://github.com/ImanHosseini
 * Wordpress @ https://imanhosseini.wordpress.com/
 */
public class Cordinate {
    public int x,y;
    public Cordinate(int x, int y){
        this.x=x;
        this.y=y;
    }
    public Cordinate(String[] s){
        this.x=Integer.parseInt(s[0]);
        this.y=Integer.parseInt(s[1]);
    }

    @Override
    public String toString() {
        return this.x+","+this.y;
    }
}
