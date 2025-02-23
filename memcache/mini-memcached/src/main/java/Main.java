package main;

import client.CacheClient;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("⚡ Mini-Memcached CLI ⚡");
        System.out.println("Commands: PUT <key> <value> | GET <key> | DELETE <key> | EXIT");

        CacheClient client = new CacheClient("localhost", 8080);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("EXIT")) {
                System.out.println("Exiting CLI...");
                break;
            }

            String[] parts = command.split(" ", 3);
            if (parts.length < 2) {
                System.out.println("Invalid command! Use: PUT key value | GET key | DELETE key");
                continue;
            }

            switch (parts[0].toUpperCase()) {
                case "PUT":
                    if (parts.length == 3) {
                        client.put(parts[1], parts[2]);
                        System.out.println("✔ Stored: " + parts[1]);
                    } else {
                        System.out.println("Usage: PUT <key> <value>");
                    }
                    break;
                case "GET":
                    String value = client.get(parts[1]);
                    System.out.println(value != null ? "🔍 Value: " + value : "❌ Key not found.");
                    break;
                case "DELETE":
                    client.delete(parts[1]);
                    System.out.println("🗑 Deleted: " + parts[1]);
                    break;
                default:
                    System.out.println("Invalid command!");
                    break;
            }
        }

        scanner.close();
    }
}
