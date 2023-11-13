package edu.hw6.task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task6 {

    public static final Map<Integer, String> PORTS = new HashMap<>(){};

    static {
        PORTS.put(0, new String("резерв".getBytes(), StandardCharsets.UTF_8));
        PORTS.put(1, new String("TCPMUX".getBytes(), StandardCharsets.UTF_8));
        PORTS.put(15, new String("".getBytes(), StandardCharsets.UTF_8));
        PORTS.put(53, new String("DOMAIN".getBytes(), StandardCharsets.UTF_8));
        PORTS.put(80, new String("HTTP".getBytes(), StandardCharsets.UTF_8));
        PORTS.put(21, new String("FTP".getBytes(), StandardCharsets.UTF_8));
        PORTS.put(25, new String("SMTP".getBytes(), StandardCharsets.UTF_8));
        PORTS.put(22, new String("SSH".getBytes(), StandardCharsets.UTF_8));
        PORTS.put(443, new String("HTTPS".getBytes(), StandardCharsets.UTF_8));
        PORTS.put(3306, new String("MySQL".getBytes(), StandardCharsets.UTF_8));
        PORTS.put(5432, new String("PostgreSQL".getBytes(), StandardCharsets.UTF_8));
        PORTS.put(3389, new String("Microsoft Terminal Server".getBytes(), StandardCharsets.UTF_8));
        PORTS.put(1521, new String("Oracle database".getBytes(), StandardCharsets.UTF_8));
        PORTS.put(5353, new String("Multicast DNS".getBytes(), StandardCharsets.UTF_8));
        PORTS.put(5672, new String("".getBytes(), StandardCharsets.UTF_8));
        PORTS.put(5355, new String("LLMNR".getBytes(), StandardCharsets.UTF_8));
        PORTS.put(67, new String("BOOTPS".getBytes(), StandardCharsets.UTF_8));
        PORTS.put(68, new String("BOOTPC".getBytes(), StandardCharsets.UTF_8));
    }



    public static void occupiedPorts() {
        List<Connection> usedConnectionsInformation = new ArrayList<>();
        for (int port : PORTS.keySet()) {
            try {
                var ss = new ServerSocket(port);
                ss.close();
            } catch (IOException e) {
                usedConnectionsInformation.add(new Connection(Protocol.TCP, port, PORTS.getOrDefault(port, "")));
            }
            try {
                var ds = new DatagramSocket(port);
                ds.close();
            } catch (SocketException e) {
                usedConnectionsInformation.add(new Connection(Protocol.UDP, port, PORTS.getOrDefault(port, "")));
            }
        }
        System.out.println(" Протокол  Порт   Сервис\n");
        for (var conInf : usedConnectionsInformation) {
            System.out.printf("%s       %d    %s%n", conInf.protocol(), conInf.port(), conInf.service());
        }
    }

    public static void main(String[] args) {
        occupiedPorts();
    }

}
