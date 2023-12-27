package org.example.atbconfigure.tcpService;

import org.example.atbconfigure.domain.enums.ResponseState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPService {
    public static final String mHost = "192.168.128.59";
    public static final int mPort = 9999;
    private Socket mSocket;
    private PrintWriter out;
    private BufferedReader in;
    ResponseStateCallback callback;

    public TCPService(ResponseStateCallback callback) {
        this.callback = callback;
    }

    public void start(String mHost, int mPort) {
        try {
            mSocket = new Socket(mHost, mPort);
            out = new PrintWriter(mSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
            out.println(102020);
            callback.sendState(ResponseState.SUCCESS_CONNECT);
        } catch (Exception ex) {
            callback.sendState(ResponseState.WRONG_CONNECT);
            out.println("void start() error" + ex.getMessage());
        }
    }

    public void stop() {
        try {
            mSocket.close();
        } catch (Exception ex) {
            out.println("void stop() error" + ex.getMessage());
        }

    }

    private static class EchoClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        ResponseStateCallback callbackState;
        ResponseCallback callback;

        public EchoClientHandler(
                Socket socket,
                ResponseStateCallback callbackState,
                ResponseCallback callback) {

            this.clientSocket = socket;
            this.callbackState = callbackState;
            this.callback = callback;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                callbackState.sendState(ResponseState.WRONG_SEND);
                out.println("out = new PrintWriter error" + e.getMessage());
            }
            try {
                in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
                callbackState.sendState(ResponseState.WRONG_READ);
                out.println("out = new PrintWriter error" + e.getMessage());
            }

            String inputLine;
            while (true) {
                try {
                    if (!((inputLine = in.readLine()) != null)) break;

                    if (".".equals(inputLine)) {
                        out.println("bye");
                        break;
                    }
                    out.println(inputLine);
                    callback.sendResponse(inputLine);
                } catch (RuntimeException | IOException e) {
                    callbackState.sendState(ResponseState.WRONG_READ);
                    out.println("((inputLine = in.readLine()) error" + e.getMessage());
                }
            }

            try {
                in.close();
            } catch (IOException e) {
                out.println("in.close() error" + e.getMessage());
            }
            out.close();
            try {
                clientSocket.close();
            } catch (IOException e) {
                out.println("clientSocket.close() error" + e.getMessage());
            }
        }
    }
}
