import java.util.ArrayList;
import java.util.List;

public class Player {
	private int rating;
	private int height;
	private String name;
	private int price;
	private String playerType;
	private List<String> attributes;
	
	public Player(int r, int h, String n, int p, String playerType) {
		this.rating = r;
		this.height = h;
		this.name = n;
		this.price = p;
		this.playerType = playerType;
		this.attributes = new ArrayList<String>();
	}
	
	public Player() {
		this.attributes = new ArrayList<String>();
	}

	public void printAttributes() {
		System.out.println("Name: " + this.getName());
		System.out.println("Rating: " + this.getRating());
		System.out.println("Position: " + this.getPlayerType());
		System.out.println("Height (inches): " + this.getHeight());
		System.out.println("Price: " + this.getPrice());
		System.out.println("Skills: " + this.getAttributes().toString());
	}
	
	public int getRating() {
		return rating;
	}
	public void addAttribute(String s) {
		attributes.add(s);
	}
	public List<String> getAttributes() {
		return attributes;
	}
	public String getPlayerType() {
		return playerType;
	}
	public void setPlayerType(String p) {
		this.playerType = p;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}


