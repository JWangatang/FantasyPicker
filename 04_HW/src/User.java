import java.util.ArrayList;

public class User {
	
	private String name;
	private int budget; // Purchasing power.
	private ArrayList<Player> team;
	
	public User (String name, int budget) {
		this.name = name;
		this.budget = budget;
		this.team = new ArrayList<Player>();
	}
	
	public String getName () {
		return this.name;
	}
	
	public int getBudget () {
		return this.budget;
	}

	// Adds player to team and lowers budget
	public boolean purchasePlayer (Player player) {
		// If player exceeds budget, return false
		if (player.getPrice() > this.budget) {
			return false;
		} else {
			this.team.add(player);
			this.budget -= player.getPrice();
			return true;
		}
	}
	
	// Calculates total rating for team
	public int getTotalRating () {
		int totalRating = 0;
		for (Player p : this.team) {
			totalRating += p.getRating();
		}
		return totalRating;
	}
	
	public void printTeam () {
		System.out.println("Current team:");
		for (Player p : team) {
			p.printAttributes();
		}
	}
}
