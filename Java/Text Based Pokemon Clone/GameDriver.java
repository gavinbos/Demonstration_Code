import java.util.Scanner;
public class GameDriver {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		Move move1 = new Move("Vine Whip", "Grass", 45, 1.0f);
		Move move2 = new Move("Tackle", "Normal", 50, 1.0f);
		Move move3 = new Move("Take Down", "Normal", 90, 0.85f);
		Move move4 = new Move("Razor Leaf", "Grass", 55, 0.95f);
		Monster monster = new Monster("Bulbasaur", "Grass", 240, 45, 49, 49, move1, move2, move3, move4);
		HumanPlayer player = new HumanPlayer(monster);

		move1 = new Move("Scratch", "Normal", 40, 1.0f);
		move2 = new Move("Ember", "Fire", 40, 1.0f);
		move3 = new Move("Peck", "Flying", 35, 1.0f);
		move4 = new Move("Fire Spin", "Fire", 35, 0.85f);
		monster = new Monster("Torchic", "Fire", 240, 45, 60, 40, move1, move2, move3, move4);
		CPUPlayer enemy = new CPUPlayer(monster, false);

		System.out.println("Welcome to the pokemon battle simulator!");
		System.out.println("You will choose options and attacks to help you and your pokemon win the battle!");
		System.out.println("(enter any character to begin)\n");
		String temp = scanner.next();
		Battle battle1 = new Battle(player, enemy, false);
		battle1.Run();

		move1 = new Move("Dragon Claw", "Dragon", 80, 0.70f);
		move2 = new Move("Dragon Dance", "Dragon", 0, 1.0f);
		move3 = new Move("Bite", "Dark", 60, 0.85f);
		move4 = new Move("Dragon Tail", "Dragon", 60, 0.85f);
		Monster gymMonster = new Monster("Garchomp", "Dragon-Ground", 500, 60, 70, 35, move1, move2, move3, move4);
		CPUPlayer gymLeader = new CPUPlayer(gymMonster, true);

		Battle battle2 = new Battle(player, gymLeader, true);

		System.out.println("Wow! you won your first battle!");
		System.out.printf("\nWait... whats this? ... %s is evolving!\n", player.getMonster().getName());
		System.out.println("(enter any character to continue...)");

		String buffer = scanner.next();
		Evolution evo1 = new Evolution(player.getMonster());
		evo1.evolve();

		System.out.printf("Your pokemon evolved into...   %s!\n", player.getMonster().getName());
		System.out.printf("%s learned the move %s!\n", player.getMonster().getName(), player.getMonster().getMove1().getName());
		System.out.println("(your pokemon's stats have increased!)");

		System.out.println("\nAre you ready to challenge the gym leader?");

		String faceGym = "";
		while(!faceGym.equals("r")) {
			System.out.println("(input r for ready)");
			faceGym = scanner.next();
		}

		battle2.Run();
		System.out.println("You have defeated the gym leader! (and the game)");
		System.out.println("Thanks for playing :)");
		System.exit(0);
	}
}