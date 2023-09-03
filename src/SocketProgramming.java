import java.net.*;
import java.io.*;

public class SocketProgramming {

    public static void sendServer(BufferedReader kb, PrintStream ps) throws IOException {
        String str1 = kb.readLine();
        ps.println(str1);
    }

    public static String getClient(BufferedReader br) throws IOException {
    	String str = br.readLine();
        return str;
    }

    public static void sendClient(BufferedReader kb1, DataOutputStream dos) throws IOException {
        String str = kb1.readLine();
        dos.writeBytes(str + "\n");
    }

    public static String getServer(BufferedReader br1) throws IOException {
        String str1 = br1.readLine();
        return str1;
    }

}