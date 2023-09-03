import java.util.Scanner;
import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) throws Exception {
        SocketProgramming socket = new SocketProgramming();
        JDBCa jdbc = new JDBCa();
        Scanner scanner = new Scanner(System.in);
        String inClient1 = "";
        String inClient2 = "";
        String resClient1="";
        String resClient2="";
       
        System.out.println("Welcome to the Rock Paper Scissors Game");
        System.out.println("Please enter the number 5 to continue");
 
        
        if (scanner.nextLine().equals("5")) {
            try (ServerSocket serverSocket = new ServerSocket(8888)) {
                System.out.println("Waiting for connection...");
                Socket s = serverSocket.accept();
                System.out.println("Connection established");
                
                
                
               try( BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
            		   //Output Stream of client
                       PrintStream ps = new PrintStream(s.getOutputStream());
                       BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
               
                     while (true) {
                    	System.out.println("Start the game by selecting Rock(1), Paper(3), or Scissors(2)");
                    	//System.out.println("You have been assigned as player one");
                    	socket.sendServer(kb, ps);
                    	String serverData = socket.getClient(br);
                    	

                    	jdbc.writeServerData(serverData);  
                    	if(jdbc.getServer().equals(jdbc.getClient())) {
                    		
                    		System.out.println("The game is a draw");
                    	}
                    	else if (jdbc.getServer().equals("1") && jdbc.getClient().equals("2")) {
                    		
                    		System.out.println("You lose");
                    	}
                    	else if (jdbc.getServer().equals("2") && jdbc.getClient().equals("3")) {
                 
                    		System.out.println("You lose.");
                    	}
                    	else if (jdbc.getServer().equals("1") && jdbc.getClient().equals("3")) {
                    		
                    		System.out.println("You win");
                    	}
                    	else if(jdbc.getServer().equals("3") && jdbc.getClient().equals("1")) {
                    	
                    		System.out.println("You lose");
                    	}
                    	else if (jdbc.getServer().equals("2") && jdbc.getClient().equals("1")){
                    		
                    		System.out.println("You win");          
                    	}
                    	else if (jdbc.getServer().equals("3") && jdbc.getClient().equals("2")) {
                    		
                    		System.out.println("You win");                    		
                    	}
                    	jdbc.clearServerDataColumn();
                    }
                    
                }
            }
        } 
    }
}

