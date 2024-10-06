package com.chatapp;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Scanner;

public class ChatClient {
    private WebSocketClient client;

    public static void main(String[] args) {
        new ChatClient().start();
    }

    public void start() {
        try {
            client = new WebSocketClient(new URI("ws://localhost:3000")) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    System.out.println("Connected to the server");
                }

                @Override
                public void onMessage(String message) {
                    System.out.println("Message: " + message);
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    System.out.println("Disconnected from server");
                }

                @Override
                public void onError(Exception ex) {
                    ex.printStackTrace();
                }
            };

            client.connect();

            Scanner scanner = new Scanner(System.in);
            while (true) {
                String message = scanner.nextLine();
                client.send(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
