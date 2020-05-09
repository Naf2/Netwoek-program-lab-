
import java.io.*;
import java.net.*;

import java.util.concurrent.TimeUnit;

/*
 *    @Name: UDPClient1
 *       @Purpose: Sending message to server.
 *       */
class UDPClient1
{
   public static void main(String args[]) throws Exception
   {
       BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
       
       InetAddress IPAddress = InetAddress.getByName("netprog1.csit.rmit.edu.au");
       byte[] sendData = new byte[1024];
       byte[] receiveData = new byte[1024];
       System.out.println("Please type any number and press enter: ");
       String sentence = inFromUser.readLine();
 
       
	      try {
		   DatagramSocket clientSocket = new DatagramSocket();
		   sendData = sentence.getBytes();
		   DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 61103);
		   clientSocket.send(sendPacket);

                   DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		   clientSocket.receive(receivePacket);
		   String modifiedSentence = new String(receivePacket.getData());
		   System.out.println(modifiedSentence);
		
           TimeUnit.SECONDS.sleep(5);
		   /* if(receivePacket == null)break; */
		   
		   clientSocket.close();
		   
	   } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
       }
    }
}
