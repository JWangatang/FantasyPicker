import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Guard extends Player {
	// TODO: use enums instead
	private List<String> possibleSkills;
	
	public Guard(String n) {
		// TODO: instead of using setters, just initialize with the Player constructor
		super();
		Random randRating = new Random();
		Random randHeight = new Random();
		
		this.setName(n);
		
		// TODO: Create static final variables for these rating and height ranges
		this.setRating(randRating.nextInt((100 - 50) + 1) + 50);
		this.setHeight(randHeight.nextInt((77 - 69) + 1) + 69);
		
		// TODO: Define price as the sum of all attribute values 
		this.setPrice(this.getRating());
		this.setPlayerType("Guard"); // TODO: Switch to ENUM
		
		// TODO: Make this into an enum
		// Ex. ATTRIBUTE1 (10), ATTRIBUTE2 (5), etc.
		possibleSkills = new ArrayList<String>();
	
		possibleSkills.add("Finisher");
		possibleSkills.add("Spot Up Shooter");
		possibleSkills.add("Shot Creator");
		possibleSkills.add("Ankle Breaker");
		possibleSkills.add("Pinpoint Passer");
		
		this.generateAttributes();
	}
	
	// TODO: Use enum implementation
	private void generateAttributes() {
		Random rand = new Random();
		int randomNum = rand.nextInt(possibleSkills.size());
		if(randomNum == 0) randomNum++;
		for(int i = 0; i < randomNum; i++) {
			this.addAttribute(possibleSkills.get(i));
		}
	}
}
