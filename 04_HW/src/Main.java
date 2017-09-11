import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// holds a list of all the players (0-50 guards, 51-100 forwards, 101-150 bigs)
		List<Player> players = generatePlayers();
		
		
		
		Scanner scan = new Scanner(System.in);
		// TODO: Game logic here 
		// while loop until game flow ends
		scan.close();
	}
	
	
	// TODO: Add some comments here about how the players are being generated from the huge text file
	public static List<Player> generatePlayers() {
		List<String> names = new ArrayList<String>();
		List<Player> players = new ArrayList<Player>();
		String line;
		try {
			InputStream fis = new FileInputStream("data/first-names.txt");
			InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
			BufferedReader br = new BufferedReader(isr);
			
			while(br.readLine() != null) {
				if(names.size() == 2471) break;
				line = br.readLine();
				names.add(line.trim());
			}
			Random rand = new Random();

			while(players.size() < 150) {
				int i = rand.nextInt(((names.size() - 1 - 0) + 1) + 0);
				if(players.size() >= 0 && players.size() <= 50) {
					players.add(new Guard(names.get(i)));
				}
				else if(players.size() >= 51 && players.size() <= 100) {
					players.add(new Forward(names.get(i)));
				}
				else {
					players.add(new Big(names.get(i)));
				}
			}
			br.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return players;
	}

}
