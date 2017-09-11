import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Forward extends Player {
	// TODO: use enums instead
	private List<String> possibleSkills;
	
	public Forward(String n) {
		// TODO: instead of using setters, just initialize with the Player constructor
		super();
		Random randRating = new Random();
		Random randHeight = new Random();
		
		this.setName(n);

		// TODO: Create static final variables for these rating and height ranges
		this.setRating(randRating.nextInt((100 - 50) + 1) + 50);
		this.setHeight(randHeight.nextInt((82 - 78) + 1) + 78);
		 
		this.setPrice(this.getRating());
		this.setPlayerType("Forward");// TODO: Switch to ENUM
		
		// TODO: Make this into an enum, with a point value
		// Ex. Microwave (10), Point Forward (5), etc.
		possibleSkills = new ArrayList<String>();
		
		possibleSkills.add("Lockdown Defender");
		possibleSkills.add("Interceptor");
		possibleSkills.add("3-Point Specialist");
		possibleSkills.add("Microwave");
		possibleSkills.add("Point Forward");
		
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
