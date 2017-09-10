import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Forward extends Player {
	private List<String> possibleSkills;
	
	public Forward(String n) {
		super();
		Random randRating = new Random();
		Random randHeight = new Random();
		
		this.setName(n);
		this.setRating(randRating.nextInt((100 - 50) + 1) + 50);
		this.setHeight(randHeight.nextInt((82 - 78) + 1) + 78);
		this.setPrice(this.getRating());
		this.setPlayerType("Forward");
		
		possibleSkills = new ArrayList<String>();
		
		possibleSkills.add("Lockdown Defender");
		possibleSkills.add("Interceptor");
		possibleSkills.add("3-Point Specialist");
		possibleSkills.add("Microwave");
		possibleSkills.add("Point Forward");
		
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
