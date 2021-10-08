import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        //SERVER SOCKET: WAITS (listens) FOR REQUEST/CONNECTION OVER THE NETWORK
        ServerSocket serverSocket = null;

        // PORT NUM
        serverSocket = new ServerSocket(1234);

        // SERVER ALWAYS ON
        while (true) {
            try {
                // SERVER CREATES NEW SOCKETS EVERYTIME IT ACCEPTS A CONNECTION
                socket = serverSocket.accept();
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true) {
                // THIS WHILE IS USED TO SEND MSG BACK & FORTH

                String msgFromClient = bufferedReader.readLine();
                System.out.println("Client: " + msgFromClient);

                bufferedWriter.write("Message Recieved.");
                bufferedWriter.newLine();
                bufferedWriter.flush();

                if (msgFromClient.equalsIgnoreCase("BYE"))
                    break;

            }
            socket.close();
            bufferedReader.close();
            bufferedWriter.close();
            inputStreamReader.close();
            outputStreamWriter.close();


        }


    }
}
