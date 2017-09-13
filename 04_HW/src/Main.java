public class Main {
	public static void main(String[] args) {
		// Generates players categorized into 3 maps (50 guards, 50 forwards, 50 bigs)
		PlayerFactory factory = new PlayerFactory();
		// Handles CLI and game flow
		Draft draft = new Draft (factory);
		draft.start();
	}
}
