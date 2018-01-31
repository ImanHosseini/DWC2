/**
 * Created by ImanH on 12/3/2017.
 * Seyed Iman Hosseini Zavaraki
 * Github @ https://github.com/ImanHosseini
 * Wordpress @ https://imanhosseini.wordpress.com/
 */
public class GUIHandler extends Thread{
    MainLoop ml;
    public GUIHandler(MainLoop ml){
        this.ml=ml;

    }
    public void run() {


        Thread login = new Thread(new Runnable() {
            @Override
            public void run(){
                try{
                    SignInApp sa=new SignInApp();
                    sa.launcher(ml.guih);


                }catch (Exception e){

                }
            }
        });
        login.start();
        // sapp.close();

    }
}
