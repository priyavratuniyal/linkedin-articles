package client;

import java.io.*;
import java.net.Socket;

public class CacheClient {
    private String host;
    private int port;

    public CacheClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private String sendRequest(String request) {
        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            out.println(request);
            return in.readLine();
        } catch (IOException e) {
            return "❌ Error: Could not connect to cache server!";
        }
    }

    public void put(String key, String value) {
        System.out.println(sendRequest("PUT " + key + " " + value));
    }

    public String get(String key) {
        return sendRequest("GET " + key);
    }

    public void delete(String key) {
        System.out.println(sendRequest("DELETE " + key));
    }
}
