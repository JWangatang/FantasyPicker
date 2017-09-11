import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PlayerFactory {
	Map<Player, Boolean> guards;
	Map<Player, Boolean> forwards;
	Map<Player, Boolean> bigs;


	
	public PlayerFactory() {
		guards = new HashMap<Player, Boolean>();
		forwards = new HashMap<Player, Boolean>();
		bigs = new HashMap<Player, Boolean>();
		this.fillMaps();
	}
	
	public Map<Player, Boolean> getGuards() {
		return guards;
	}

	public Map<Player, Boolean> getForwards() {
		return forwards;
	}

	public Map<Player, Boolean> getBigs() {
		return bigs;
	}
	
	private List<Player> generatePlayers() {
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
	
	private void fillMaps() {
		List<Player> players = this.generatePlayers();
		for(int i = 0; i < 51; i++) {
			this.guards.put(players.get(i), true);
		}
		for(int i = 51; i < 101; i++) {
			this.forwards.put(players.get(i), true);
		}
		for(int i = 101; i < players.size(); i++) {
			this.bigs.put(players.get(i), true);
		}
	}

}
