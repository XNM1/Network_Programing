package com.company.tcpclient;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        Scanner sc = new Scanner(System.in);
        String serverAddr = "localhost";
        int port = 8030;
        try {
            socket = new Socket(serverAddr, port);
            while(true) {
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                String str = sc.nextLine();

                bufferedWriter.write(str);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                if(str.toLowerCase().equals("exit")) {
                    break;
                }

                String msg = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                bufferedReader.close();
                bufferedWriter.close();
                socket.close();
                sc.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}