

import java.net.*;
import java.nio.charset.Charset;
import java.io.*; 

/*
 *    @Name: UDPServer
 *       @Purpose: concurrent connection by client
 *       */
public class UDPServer {

    public static void main (String args[]){
		System.out.println("Server is running.");
        DatagramSocket aSocket = null;
        try{
            aSocket = new DatagramSocket(61103);
            byte[] buffer = new byte[1000];
            while(true){
				/*Datagram object*/
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
				System.out.println(request.getAddress()+" "+request.getPort()+ " is connected.");
                new ClientThread(aSocket,request);
            }
        }
        catch(SocketException e){
            System.out.println("Socket: " + e.getMessage());
        }
        catch(IOException e){
                System.out.println("IO: " + e.getMessage());
        }
        finally {if(aSocket!=null)aSocket.close();}
    }
}

class ClientThread extends Thread{

    DatagramSocket sock;
    DatagramPacket request;
    public ClientThread(DatagramSocket sock, DatagramPacket request){
        this.sock=sock;
        this.request=request;
        this.start();

    }

    public void run() {
        try{
            String input = new String(request.getData());
            String result, result1;
            result ="Connection close in 5 seconds \n";
			/*Message send to client*/
            DatagramPacket reply =  new DatagramPacket(result.getBytes(Charset.forName("UTF-8")),
                    result.length(),request.getAddress(),request.getPort());
            sock.send(reply);
			
			
			Thread.sleep(5);
			
  }
        catch(SocketException e){
            System.out.println("Socket: " + e.getMessage());
        }
        catch(IOException e){
                System.out.println("IO: " + e.getMessage());
        }
		catch (InterruptedException e) {
            Thread.currentThread().interrupt(); 
            System.out.println("Close`");
        }
		
		

    }

    }         
