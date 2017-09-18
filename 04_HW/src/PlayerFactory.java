import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PlayerFactory {
	
	private ArrayList<Player> players;
	
	private Map<Player, Boolean> guards;
	private Map<Player, Boolean> forwards;
	private Map<Player, Boolean> bigs;
	
	public PlayerFactory() {
		guards = new HashMap<Player, Boolean>();
		forwards = new HashMap<Player, Boolean>();
		bigs = new HashMap<Player, Boolean>();
		this.fillMaps();
	}
	
	public ArrayList<Player> getPlayers(){
		return this.players;
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
	
	private ArrayList<Player> generatePlayers() {
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<Player> players = new ArrayList<Player>();
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
				if(players.size() < 50) {
					players.add(new Guard(names.get(i)));
				}
				else if(players.size() >= 50 && players.size() < 100) {
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
		this.players = this.generatePlayers();
		for(int i = 0; i < 50; i++) {
			this.guards.put(this.players.get(i), true);
		}
		for(int i = 50; i < 100; i++) {
			this.forwards.put(this.players.get(i), true);
		}
		for(int i = 100; i < this.players.size(); i++) {
			this.bigs.put(this.players.get(i), true);
		}
	}
	
	public void printGuards() {
		int index = 0;
		for (Player p: this.guards.keySet()) {
			System.out.println("Index: " + index);
			index++;
			p.printAttributes();
		}
	}
	
	public void printForwards() {
		int index = 50;
		for (Player p: this.forwards.keySet()) {
			System.out.println("Index: " + index);
			index++;
			p.printAttributes();
		}
	}
	
	public void printBigs() {
		int index = 99;
		for (Player p: this.bigs.keySet()) {
			System.out.println("Index: " + index);
			index++;
			p.printAttributes();
		}
	}
	
	public boolean selectPlayer (User user, int playerIndex) {
		Player p = this.players.get(playerIndex);
		if (guards.containsKey(p)) {
			if(guards.get(p)) {
				return user.purchasePlayer(p);
			}
			return false;
		} else if (forwards.containsKey(p)) {
			if(forwards.get(p)) {
				return user.purchasePlayer(p);
			}
			return false;
		} else if (bigs.containsKey(p)) {
			if(bigs.get(p)) {
				return user.purchasePlayer(p);
			}
			return false;
		} else {
			return false;
		}
	}

}
