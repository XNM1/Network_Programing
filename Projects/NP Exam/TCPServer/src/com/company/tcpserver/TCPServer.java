package com.company.tcpserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        Socket socket = null;
        ServerSocket servSocket = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        int port = 8030;
        try {
            servSocket = new ServerSocket(port);
            socket = servSocket.accept();
            System.out.println("Connection established [" + socket.getInetAddress() + "]");
            while(true) {
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                String msg = bufferedReader.readLine();
                System.out.println("String: " + msg);

                if(msg.toLowerCase().equals("exit")) {
                    break;
                }
                String[] words = splitWords(msg);
                //System.out.println(words[1]);
                String str = "Word count: ";
                str += words.length;
                str += "; Longest word: ";
                str += longestWord(words);

                bufferedWriter.write(str);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                bufferedWriter.close();
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                servSocket.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static String[] splitWords(String input) {
        if (input == null || input.isEmpty()) { return null; }
        String[] words = input.split("\\s+");
        return words;
    }

    public static String longestWord(String[] input) {
        if (input == null) { return null; }
        String rts = "";
        for(int i=0 ; i< input.length ; i++)
        {
            if(input[i].length() >= rts.length())
            {
                rts = input[i];
            }
        }
        return rts;
    }
}
