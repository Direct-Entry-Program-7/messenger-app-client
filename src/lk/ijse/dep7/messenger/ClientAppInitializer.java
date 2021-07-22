package lk.ijse.dep7.messenger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientAppInitializer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Socket clientSocket = new Socket("localhost", 8585)) {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            try (OutputStream os = clientSocket.getOutputStream();
                 OutputStreamWriter osw = new OutputStreamWriter(os);
                 BufferedWriter bw = new BufferedWriter(osw)) {

                while (true) {
                    System.out.print("Enter your message: ");
                    String message = scanner.nextLine();

                    if (message.equalsIgnoreCase("exit")) {
                        System.exit(0);
                    }

                    bw.write(name + ": " + message);
                    bw.newLine();
                    bw.flush();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
