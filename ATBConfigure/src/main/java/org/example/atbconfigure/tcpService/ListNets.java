package org.example.atbconfigure.tcpService;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import static java.lang.System.out;

public class ListNets {

    public static void main(String args[]) throws SocketException {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets))
            displayInterfaceInformation(netint);
    }

    static void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
        out.printf("Display name: %s\n", netint.getDisplayName());
        out.printf("Name: %s\n", netint.getName());
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();


        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
            out.printf("InetAddress: %s\n", inetAddress);
            out.printf("InetAddress: %s\n", inetAddress.getAddress());
            out.printf("InetAddress: %s\n", inetAddress.getHostAddress());
            out.printf("InetAddress: %s\n", inetAddress.isLoopbackAddress());
            out.printf("InetAddress: %s\n", inetAddress.getCanonicalHostName());
            out.printf("InetAddress: %s\n", inetAddress.getHostName());
        }
        List<InterfaceAddress> interFace = netint.getInterfaceAddresses();
        for (InterfaceAddress ad : interFace){
            out.printf("InetFaceAddress: %s\n", ad);
        }
        out.printf("\n");
    }
}