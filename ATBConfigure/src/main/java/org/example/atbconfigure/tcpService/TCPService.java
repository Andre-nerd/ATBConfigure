package org.example.atbconfigure.tcpService;

import org.example.atbconfigure.domain.enums.ResponseState;

import java.io.*;
import java.net.Socket;

import static org.example.atbconfigure.util.CommonUtils.isCRCValid;
import static org.example.atbconfigure.util.CommonUtils.printArray;


public class TCPService {
    //    public static final String mHost = "192.168.128.59";
    public static final String mHost = "192.168.43.218";
    public static final int mPort = 9999;
    private Socket mSocket;
    private PrintWriter out;
    private BufferedReader in;
    ResponseStateCallback stateCallback;
    ResponseCallback responseCallback;

    public TCPService(ResponseStateCallback stateCallback, ResponseCallback responseCallback) {
        this.stateCallback = stateCallback;
        this.responseCallback = responseCallback;
    }

    public void start(String mHost, int mPort) {
        try {
            mSocket = new Socket(mHost, mPort);
            out = new PrintWriter(mSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
//            byte[] messageForCheck = {9, 8, 7, 6, 5, 3, 3, 3, 1, 1};
//            sendMessage(messageForCheck);

            stateCallback.sendState(ResponseState.SUCCESS_CONNECT);
            EchoClientHandler handler = new EchoClientHandler(mSocket, stateCallback, responseCallback);
            handler.start();

        } catch (Exception ex) {
            stateCallback.sendState(ResponseState.WRONG_CONNECT);
            System.out.println("void start() error" + ex.getMessage());
        }
    }

    public void sendMessage(byte[] message) {
        try {
            DataOutputStream dOut = new DataOutputStream(mSocket.getOutputStream());
            dOut.write(message);
            dOut.flush();
            System.out.println("send message :");
            printArray(message);
        } catch (IOException e) {
            stateCallback.sendState(ResponseState.WRONG_SEND);
            System.out.println("void sendMessage error" + e.getMessage());
        }
    }


    public void stop() {
        try {
            mSocket.close();
        } catch (Exception ex) {
            System.out.println("void stop() error" + ex.getMessage());
        }

    }

    private static class EchoClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        ResponseStateCallback callbackState;
        ResponseCallback callbackResponse;

        public EchoClientHandler(
                Socket socket,
                ResponseStateCallback callbackState,
                ResponseCallback callback) {

            this.clientSocket = socket;
            this.callbackState = callbackState;
            this.callbackResponse = callback;
        }

        public void run() {
            try {
                DataInputStream dInp = new DataInputStream(clientSocket.getInputStream());
                while (clientSocket.isConnected()) {
                    byte[] header = new byte[4];
                    dInp.read(header, 0, 4);
                    byte[] message = new byte[header[3]+1];
                    dInp.readFully(message); // read the message
                    int size = header.length + message.length;
                    byte[] fullResponseArray = new byte[size];
                    System.arraycopy(header, 0, fullResponseArray, 0, header.length);
                    System.arraycopy(message, 0, fullResponseArray, header.length, message.length);
                    if(!isCRCValid(fullResponseArray)){
                        readWrongSequence(dInp);
                        continue;
                    }
                    if(fullResponseArray[2] == 0) {
                        printArray(fullResponseArray);
                    }
//                    System.out.println();
                    callbackResponse.sendResponse(fullResponseArray);
                }
            } catch (IOException e) {
                callbackState.sendState(ResponseState.WRONG_CONNECT);
                System.out.println("run() reading error" + e.getMessage());
            }
        }

        private void readWrongSequence(DataInputStream dInp) throws IOException {
            while (clientSocket.isConnected()) {
                byte bt = dInp.readByte();
                if(bt==36){
                    byte[] header = new byte[3];
                    dInp.read(header, 0, 3);
                    byte[] message = new byte[header[2]+1];
                    dInp.readFully(message);
                    return;
                }
            }
        }

//        public void run() {
//            try {
//                out = new PrintWriter(clientSocket.getOutputStream(), true);
//            } catch (IOException e) {
//                callbackState.sendState(ResponseState.WRONG_SEND);
//                out.println("out = new PrintWriter error" + e.getMessage());
//            }
//            try {
//                int lenght = new DataInputStream(clientSocket.getInputStream()).readInt();
//                in = new BufferedReader(
//                        new InputStreamReader(clientSocket.getInputStream()));
//            } catch (IOException e) {
//                callbackState.sendState(ResponseState.WRONG_READ);
//                out.println("out = new PrintWriter error" + e.getMessage());
//            }
//
//            String inputLine;
//            while (true) {
//                try {
//                    if (!((inputLine = in.readLine()) != null)) break;
//
//                    if (".".equals(inputLine)) {
//                        out.println("bye");
//                        break;
//                    }
//                    out.println(inputLine);
//                    callback.sendResponse(inputLine);
//                } catch (RuntimeException | IOException e) {
//                    callbackState.sendState(ResponseState.WRONG_READ);
//                    out.println("((inputLine = in.readLine()) error" + e.getMessage());
//                }
//            }
//
//            try {
//                in.close();
//            } catch (IOException e) {
//                out.println("in.close() error" + e.getMessage());
//            }
//            out.close();
//            try {
//                clientSocket.close();
//            } catch (IOException e) {
//                out.println("clientSocket.close() error" + e.getMessage());
//            }
//        }
    }
}
