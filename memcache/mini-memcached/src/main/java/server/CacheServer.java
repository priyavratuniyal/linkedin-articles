package server;

import cache.LRUCache;

import java.io.*;
import java.net.*;

public class CacheServer {
    private static final int PORT = 5000;
    private static LRUCache<String, String> cache = new LRUCache<>(100);

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Cache Server running on port " + PORT);

        while (true) {
            Socket client = serverSocket.accept();
            new Thread(new ClientHandler(client)).start();
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket client;

        ClientHandler(Socket client) {
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
}
