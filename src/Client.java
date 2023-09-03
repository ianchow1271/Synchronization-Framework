import java.util.Scanner;
import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) throws Exception {
        SocketProgramming socket = new SocketProgramming();
        JDBCa jdbc = new JDBCa();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Rock Paper Scissors Game");
        System.out.println("Enter the number 5 to continue");
        
        

        if (scanner.nextInt() == 5) {
            try (Socket s = new Socket("localhost", 8888);
                 BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                 BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
                 DataOutputStream dos = new DataOutputStream(s.getOutputStream())) {

                while (true) {
                	System.out.println("Start the game by selecting Rock(1), Paper(3), or Scissors(2)");
                	
                    socket.sendClient(kb, dos);
                    String clientData = socket.getServer(br);
                    jdbc.writeClientData(clientData); 
                	if(jdbc.getServer().equals(jdbc.getClient())) {
                		
                		System.out.println("The game is a draw");
                	}
                	else if (jdbc.getServer().equals("1") && jdbc.getClient().equals("2")) {
                		
                		System.out.println("You win");
                	}
                	else if (jdbc.getServer().equals("2") && jdbc.getClient().equals("3")) {
             
                		System.out.println("You win.");
                	}
                	else if (jdbc.getServer().equals("1") && jdbc.getClient().equals("3")) {
                		
                		System.out.println("You lose");
                	}
                	else if(jdbc.getServer().equals("3") && jdbc.getClient().equals("1")) {
                	
                		System.out.println("You win");
                	}
                	else if (jdbc.getServer().equals("2") && jdbc.getClient().equals("1")){
                		
                		System.out.println("You lose");          
                	}
                	else if (jdbc.getServer().equals("3") && jdbc.getClient().equals("2")) {
                		
                		System.out.println("You lose");                    		
                	}
                    jdbc.clearClientDataColumn();
                }
            }
        }
    }
}