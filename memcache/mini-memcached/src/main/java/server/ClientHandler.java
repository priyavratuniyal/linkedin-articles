package server;

import cache.LRUCache;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket client;
    private static final LRUCache<String, String> cache = new LRUCache<>(100);

    public ClientHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {

            String request;
            while ((request = in.readLine()) != null) {
                String[] parts = request.split(" ", 3);
                String command = parts[0];

                if ("GET".equals(command)) {
                    String value = cache.get(parts[1]);
                    out.println(value != null ? value : "NULL");
                } else if ("PUT".equals(command)) {
                    cache.put(parts[1], parts[2]);
                    out.println("STORED");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
