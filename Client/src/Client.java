import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
// CREATE JAVA SOCKET CONNECTION: TCP
    // Two Programs communicate over sockets
    public static void main(String[] args) {
        Socket socket = null;

        // INPUT STREAM (SEQUENTIAL DATA) : READ DATA FROM SOURCE & OUTPUT STREAM (BYTE VS CHAR)
        InputStreamReader inputStreamReader = null;
        //  OUTPUT STREAM : TWO TYPES (BYTE VS CHAR)
        OutputStreamWriter outputStreamWriter = null;
        //BUFFER - SPEEDS I/O OPERATION BUY READING LARGE BLOCKS AT A TIME
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null; // I/0

        try {
            // Host, Port
            socket = new Socket("localHost",1234);
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            Scanner scanner = new Scanner(System.in);
            // Infinite iteration
            while (true) {
                String msgSend = scanner.nextLine();
                bufferedWriter.write(msgSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                // SERVER RESPONSE
                System.out.println("Server: "+bufferedReader.readLine());

                if (msgSend.equalsIgnoreCase("BYE")) {
                    // if term BYE is mentioned in chat: END CONVO
                    break;
                }
            }



        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null)
                    socket.close();
                if (inputStreamReader != null)
                    inputStreamReader.close();
                if (outputStreamWriter != null)
                    outputStreamWriter.close();
                if (bufferedReader != null)
                    bufferedReader.close();
                if (bufferedWriter != null)
                    bufferedWriter.close();
            } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

