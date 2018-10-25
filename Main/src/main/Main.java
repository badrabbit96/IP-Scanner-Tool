package main;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 *
 * @author tomekniemczyk
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SocketException {
        
        getNetworkIPs();
       
     
    
    }
    
    public static void getNetworkIPs() {
    final byte[] ip;
    try {
        ip = InetAddress.getLocalHost().getAddress();
        
    } catch (Exception e) {
        return;     // exit method, otherwise "ip might not have been initialized"
    }
 
    for(int i=1;i<=254;i++) {
        final int j = i;  // i as non-final variable cannot be referenced from inner class
        new Thread(new Runnable() {   // new thread for parallel execution
            public void run() {
                try {
                    ip[3] = (byte)j;
                    //String hostName = iAddress.getHostName();
                    InetAddress address = InetAddress.getByAddress(ip);
                    String output = address.toString().substring(1);
                    long start_time = System.nanoTime();
                    if (address.isReachable(5000)) {
                        String hostname = address.getHostName();
                        String ip = address.getHostAddress();
                        
                       
                        System.out.println("Adres IP: " + output);
                        
                     
                        long end_time = System.nanoTime();
                        double difference = (end_time - start_time) / 1e6;
                        String ping = Double.toString(difference);
 
                        System.out.println("Ping: " + ping.substring(0,1) + "ms");
                        
                        if(hostname.equals(output)){
                        System.out.println("Brak nazwy hosta");
                        }
                        else{
                        System.out.println("Nazwa hosta:" + hostname);
                        }
                      
                     
 
                     
//                        InetAddress ip1 = InetAddress.getByName(output);
//
//                       NetworkInterface network = NetworkInterface.getByInetAddress(ip1);
//                       
//                       byte[] mac = network.getHardwareAddress();
//                      
//      //        System.out.print("Current MAC address : ");
//                      
//              StringBuilder sb = new StringBuilder();
//              for (int i = 0; i < mac.length; i++) {
//                      sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));            
//              }
//              System.out.println(sb.toString());
// 
                   
                       
 
                    
                    } else {
                      //  System.out.println("Not Reachable: "+output);
                    }
                } catch (Exception e) {
                   // e.printStackTrace();
                }
            }
        }).start();     // dont forget to start the thread
    }
    }
    
}
