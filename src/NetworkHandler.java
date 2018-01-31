import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by ImanH on 12/3/2017.
 * Seyed Iman Hosseini Zavaraki
 * Github @ https://github.com/ImanHosseini
 * Wordpress @ https://imanhosseini.wordpress.com/
 */
public class NetworkHandler  {
    MainLoop ml;
    public final String hostStr = "127.0.0.1";
    private static final int PORT = 1234;
    private InetAddress host;
    private static DatagramSocket datagramSocket;
    private static DatagramPacket inPacket, outPacket;
    private static byte[] buffer;

    public NetworkHandler(MainLoop ml) {
        this.ml = ml;
        try {
            this.host = InetAddress.getLocalHost();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }


    public void sendMsg(int wId,String txt){
        Socket link = null;

        try
        {
            link = new Socket(host,PORT);
            PrintWriter output =
                    new PrintWriter(
                            link.getOutputStream(),true);

            //  Scanner userEntry = new Scanner(System.in);
            String message, response;


            message = "MSG"+"*"+ml.key+"*"+Integer.toString(wId)+"*"+txt;
            output.println(message);


        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
        }
        finally
        {
            try
            {
                System.out.println(
                        "\n* Closing connection... *");
                link.close();

            }
            catch(IOException ioEx)
            {
                System.out.println(
                        "Unable to disconnect!");
                System.exit(1);
            }
        }
    }
    public boolean login(String usr, String pass) {
        Socket link = null;

        try
        {
            link = new Socket(host,PORT);
            Scanner input =
                    new Scanner(link.getInputStream());
            PrintWriter output =
                    new PrintWriter(
                            link.getOutputStream(),true);

          //  Scanner userEntry = new Scanner(System.in);
            String message, response;


                message = "SIGNIN"+"*"+usr+"*"+pass;
                output.println(message);

            response="";
               if(input.hasNextLine()){
                   response = input.nextLine();
               }

                System.out.println("\nSERVER> "+response);
               if(response.startsWith("OK")){
                   ml.player_name=usr;
                   String[] splitz=response.split("\\$")[0].split("\\*");
                   ml.key=Integer.parseInt(splitz[1]);
                    ml.credit=Integer.parseInt(splitz[2]);
                    ml.centre=new Cordinate(splitz[3].split(","));
                    ml.fetchMsg(response.split("\\$")[1]);
                   return true;
               }else{
                   return false;
               }

        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
        }
        finally
        {
            try
            {
                System.out.println(
                        "\n* Closing connection... *");
             if(link!=null)   link.close();

            }
            catch(IOException ioEx)
            {
                System.out.println(
                        "Unable to disconnect!");
                System.exit(1);
            }
        }
        return false;
    }

    public void mapReq(){
        Socket link = null;

        try
        {
            link = new Socket(host,PORT);

            Scanner input =
                    new Scanner(link.getInputStream());
            PrintWriter output =
                    new PrintWriter(
                            link.getOutputStream(),true);

            //  Scanner userEntry = new Scanner(System.in);
            String message, response;


            message = "MAP"+"*"+ml.key;
            output.println(message);

            response="";
            if(input.hasNextLine()){
                response = input.nextLine();
            }

            System.out.println("\nSERVER> "+response);
            if(response.startsWith("MAP")){
                Gson gson = new GsonBuilder().create();
               System.out.println("SPED"+response.split("\\$")[1]);
              ml.map= gson.fromJson(response.split("\\$")[1],World.class);
              ml.setNames(response.split("\\$")[2]);
            }else{
                System.out.println("ERROR IN MAP RESPONSE!");
            }
            ml.hubcntrl.drawMap();

        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
        }
        finally
        {
            try
            {
                System.out.println(
                        "\n* Closing connection... *");
                link.close();

            }
            catch(IOException ioEx)
            {
                System.out.println(
                        "Unable to disconnect!");
                System.exit(1);
            }
        }
    }

    public void updReq(){
        Socket link = null;

        try
        {
            link = new Socket(host,PORT);

            Scanner input =
                    new Scanner(link.getInputStream());
            PrintWriter output =
                    new PrintWriter(
                            link.getOutputStream(),true);

            //  Scanner userEntry = new Scanner(System.in);
            String message, response;


            message = "UPD"+"*"+ml.key;
            output.println(message);

            response="";
            if(input.hasNextLine()){
                response = input.nextLine();
            }

            System.out.println("\nSERVER> "+response);
            if(response.startsWith("UPD")){
                Gson gson = new GsonBuilder().create();
                String[] parted=response.split("\\$");
               // System.out.println("SPED"+response.split("\\$")[1]);
                ml.map= gson.fromJson(parted[2],World.class);
               ml.updateCred(Integer.parseInt(parted[1]));
               if(parted.length==4){
                   System.out.println("PAYAM AMAD! ");
                   ml.addMsg(parted[3]);
               }

            }else{
                System.out.println("ERROR IN UPD RESPONSE!");
            }

         ml.hubcntrl.reDraw();

        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
        }
        finally
        {
            try
            {
                System.out.println(
                        "\n* Closing connection... *");
                link.close();

            }
            catch(IOException ioEx)
            {
                System.out.println(
                        "Unable to disconnect!");
                System.exit(1);
            }
        }
    }

}


