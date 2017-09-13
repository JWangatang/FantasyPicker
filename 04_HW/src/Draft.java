import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Draft {
	
	private PlayerFactory factory;
	private ArrayList<User> teams;
	private int numTeams;
	private int numPlayers = 15;
	
	// Prompt Messages
	private static final String 
			INTRO_TEXT = "Welcome to Fantasy Basketball!\n"
					+ "You will be competing against other fantasy users to see who can create the best team.\n"
					+ "Are you ready?!",
			SEPARATOR_TEXT = "**********************",
			YES_NO_TEXT = "Enter y (or yes) or anything else for no - case insensitive: ",
			GET_STARTED_TEXT = "Awesome! Let's get started!",
			
			NUM_OF_USERS_TEXT = "Enter the number of competing teams: ",
			NUM_PLAYERS_TEXT = "The default number of players per team is 15. Would you like to change this?",
			EDIT_NUM_PLAYERS_TEXT = "Enter the number of players per team: ",
			NAME_TEAMS_TEXT = "Perfect. Now let's come up with some funny team names.",
			NAME_TEXT = "Enter your team name: ",
			ORDER_TEXT = "The teams have now been created and shuffled. Here's the order for team selection:",
			DONE_SETUP_TEXT = "Sweet. Now that we're all set up, let's start drafting!",
			MENU_TEXT = "Here's what you can do for your turn:"
					+ "Commands:\n"
					+ "1 - View/Select Guards\n"
					+ "2 - View/Select Forwards\n"
					+ "3 - View/Select Bigs\n"
					+ "4 - View Team\n",
			VIEW_GUARDS_TEXT = "Here's the list of available guards:\n",
			SELECT_GUARDS_TEXT = "Would you like to select a guard? "
					+ "(Enter the guard index to select. Anything else to return.)",
			VIEW_FORWARDS_TEXT = "Here's the list of available forwards:\n",
			SELECT_FORWARDS_TEXT = "Would you like to select a forward? "
					+ "(Enter the forward index to select. Anything else to return.)",
			VIEW_BIGS_TEXT = "Here's the list of available bigs:\n",
			SELECT_BIGS_TEXT = "Would you like to select a big? "
				+ "(Enter the big index to select. Anything else to return.)",
			SELECTED_TEXT = "Congratulations! You've added this player to your team",
			VIEW_TEAM_TEXT = "Here's your current team: ",
			END_TURN_TEXT = "Your turn is now over.",
			END_DRAFT_TEXT = "The draft is now over! Here are the rankings:\n",
			THANKS_TEXT = "Thanks for playing!";
	
	// Error Messages
	private static final String 
		EMPTY_STRING_ERROR = "Nothing entered. Please try again.",
		INVALID_INT_ERROR = "Invalid integer. Please try again.",
		NEGATIVE_INT_ERROR = "Please enter a number greater than 1.",
		COMMAND_ERROR = "Invalid command. Please try again.", 
		UNAVAILABLE_ERROR = "The player you entered is no longer available. Please try again.";
	
	public Draft (PlayerFactory factory) {
		this.factory = factory;
		this.teams = new ArrayList<User>();
	}
	
	// Returns true if user enters y or yes, false otherwise
	private boolean getBooleanInput (String prompt, Scanner scan) {
		System.out.println(prompt);
		String input = scan.nextLine();
		return input.toLowerCase().equals("y") || input.toLowerCase().equals("yes");
	}
	
	// Gets an integer value of at least 2
	private int getIntegerInput (String prompt, Scanner scan) {
		System.out.println(prompt);
		String input = scan.nextLine();
		try {
			int integer = Integer.parseInt(input);
			if (integer < 2) {
				System.out.println(NEGATIVE_INT_ERROR);
				return this.getIntegerInput(prompt, scan);
			}
			return integer;
		} catch (NumberFormatException e) {
			System.out.println(INVALID_INT_ERROR);
			return this.getIntegerInput(prompt, scan);
		}	
	}
	
	// Gets a non-empty String input
	private String getStringInput (String prompt, Scanner scan) {
		System.out.println(prompt);
		String input = scan.nextLine();
		while (input.equals("")) {
			System.out.println(EMPTY_STRING_ERROR);
			return this.getStringInput(prompt, scan);
		}
		return input;
	}
	
	// Prompts user for a player index
	private int getPlayerIndexSelection (String prompt, Scanner scan) {
		System.out.println(prompt);
		String input = scan.nextLine();
		try {
			int integer = Integer.parseInt(input);
			if (integer < 0 || integer > 149) {
				return -1;
			}
			return integer;
		} catch (NumberFormatException e) {
			return -1;
		}	
	}
	
	public void start () {
		Scanner scan = new Scanner (System.in);
		System.out.println(INTRO_TEXT);
		System.out.println(SEPARATOR_TEXT);
		if (this.getBooleanInput(YES_NO_TEXT, scan)) {
			
			// Creates teams and defines number of players per team
			System.out.println(GET_STARTED_TEXT);
			System.out.println(SEPARATOR_TEXT);
			this.numTeams = this.getIntegerInput(NUM_OF_USERS_TEXT, scan);
			System.out.println(NUM_PLAYERS_TEXT);
			if (this.getBooleanInput(YES_NO_TEXT, scan)) {
				this.numPlayers = this.getIntegerInput(EDIT_NUM_PLAYERS_TEXT, scan);
			}
			
			// Create Users (aka teams) with names and a budget
			System.out.println(NAME_TEAMS_TEXT);
			for (int i=0; i<this.numTeams; i++) {
				String name = this.getStringInput("Team " + (i+1) + " - " + NAME_TEXT, scan);
				int budget = this.numPlayers * 75;
				User newUser = new User(name, budget);
				this.teams.add(newUser);
			}
			
			// Shuffle order of teams
			Collections.shuffle(this.teams);
			System.out.println(ORDER_TEXT);
			for (User u : this.teams) {
				System.out.println(u.getName());
			}
			
			System.out.println(SEPARATOR_TEXT);
			System.out.println(DONE_SETUP_TEXT);
			
			// Start selecting players
			for (int i=0; i<this.numPlayers; i++) {
				for (User u : this.teams) {
					System.out.println("It is Team " + u.getName() + "'s turn.");
					executeCommand(u, scan);			
				}
			}
			
		} 
		System.out.println(THANKS_TEXT);
		scan.close();
	}
	
	private void executeCommand (User user, Scanner scan) {
		int command = this.getIntegerInput(MENU_TEXT, scan);
		
		if (command == 1) {
			System.out.println(VIEW_GUARDS_TEXT);
			int selection = this.getPlayerIndexSelection(SELECT_GUARDS_TEXT, scan);
			if (selection != -1) {
				if (selection > 50) { // TODO: CHECK INDEX
					executeCommand(user, scan);
				} else if (this.factory.selectPlayer(user, selection)) {
					System.out.println(SELECTED_TEXT);
				} else {
					System.out.println(UNAVAILABLE_ERROR);
					executeCommand(user, scan);
				}
			} else {
				executeCommand(user, scan);
			}
		} else if (command == 2) {
			System.out.println(VIEW_FORWARDS_TEXT);
			int selection = this.getPlayerIndexSelection(SELECT_FORWARDS_TEXT, scan);
			if (selection != -1) {
				if (selection < 50 || selection > 100) { // TODO: CHECK INDEX
					executeCommand(user, scan);
				} else if (this.factory.selectPlayer(user, selection)) {
					System.out.println(SELECTED_TEXT);
				} else {
					System.out.println(UNAVAILABLE_ERROR);
					executeCommand(user, scan);
				}
			} else {
				executeCommand(user, scan);
			}
		} else if (command == 3) {
			System.out.println(VIEW_BIGS_TEXT);
			int selection = this.getPlayerIndexSelection(SELECT_BIGS_TEXT, scan);
			if (selection != -1) {
				if (selection < 100) { // TODO: CHECK INDEX
					executeCommand(user, scan);
				} else if (this.factory.selectPlayer(user, selection)) {
					System.out.println(SELECTED_TEXT);
				} else {
					System.out.println(UNAVAILABLE_ERROR);
					executeCommand(user, scan);
				}
			} else {
				executeCommand(user, scan);
			}
		} else if (command == 4) {
			System.out.println(VIEW_TEAM_TEXT);
			// TODO: Print team
			
		} else {
			System.out.println(COMMAND_ERROR);
			executeCommand(user, scan);
		}
	}
}
