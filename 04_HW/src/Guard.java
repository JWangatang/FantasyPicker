import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Guard extends Player {
	private List<String> possibleSkills;
	
	public Guard(String n) {
		super();
		Random randRating = new Random();
		Random randHeight = new Random();
		
		this.setName(n);
		this.setRating(randRating.nextInt((100 - 50) + 1) + 50);
		this.setHeight(randHeight.nextInt((77 - 69) + 1) + 69);
		this.setPrice(this.getRating());
		this.setPlayerType("Guard");
		
		possibleSkills = new ArrayList<String>();
		
		possibleSkills.add("Finisher");
		possibleSkills.add("Spot Up Shooter");
		possibleSkills.add("Shot Creator");
		possibleSkills.add("Ankle Breaker");
		possibleSkills.add("Pinpoint Passer");
		
		this.generateAttributes();
	}
	
	private void generateAttributes() {
		Random rand = new Random();
		int randomNum = rand.nextInt(possibleSkills.size());
		if(randomNum == 0) randomNum++;
		for(int i = 0; i < randomNum; i++) {
			this.addAttribute(possibleSkills.get(i));
		}
	}
}
