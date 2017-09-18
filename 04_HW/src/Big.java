import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Big extends Player {
	// TODO: use enums instead
	private List<String> possibleSkills;
	
	public Big(String n) {
		// TODO: instead of using setters, just initialize with the Player constructor
		super();
		Random randRating = new Random();
		Random randHeight = new Random();
		
		this.setName(n);
		
		// TODO: Create static final variables for these rating and height ranges
		this.setRating(randRating.nextInt((100 - 50) + 1) + 50);
		this.setHeight(randHeight.nextInt((89 - 83) + 1) + 83);
		
		this.setPrice(this.getRating());
		this.setPlayerType(PlayerType.BIG); // TODO: Switch to ENUM
		
		// TODO: Make this into an enum
		// Ex. ATTRIBUTE1 (10), ATTRIBUTE2 (5), etc.
		possibleSkills = new ArrayList<String>();
		
		possibleSkills.add("Rebounder");
		possibleSkills.add("Shot Blocker");
		possibleSkills.add("Post Scorer");
		possibleSkills.add("Post Playermaker");
		possibleSkills.add("Outlet Passer");
		
		this.generateAttributes();
	}
	
	// TODO: Use enum implementation
	protected void generateAttributes() {
		Random rand = new Random();
		int randomNum = rand.nextInt(possibleSkills.size());
		if(randomNum == 0) randomNum++;
		for(int i = 0; i < randomNum; i++) {
			this.addAttribute(possibleSkills.get(i));
		}
	}
}
