import java.util.Scanner;
public class HumanPlayer extends Player{

    private Monster monster;
    private int hyperPotions = 2;
    private int superPotions = 5;
    private int x_attack = 1;
    private int x_defend = 1;

    public HumanPlayer(Monster monster){
        this.monster = monster;
    }

    @Override
    public int chooseMove() {
        System.out.println("1.  " + monster.getMove1().getName() + "  power:" + monster.getMove1().getPower());
        System.out.println("2.  " + monster.getMove2().getName() + "  power:" + monster.getMove2().getPower());
        System.out.println("3.  " + monster.getMove3().getName() + "  power:" + monster.getMove3().getPower());
        System.out.println("4.  " + monster.getMove4().getName() + "  power:" + monster.getMove4().getPower());

        Scanner scanner = new Scanner(System.in);
        int moveChoice = 0;
        System.out.println("Which move? ");
        moveChoice = scanner.nextInt();
        while(moveChoice <= 0 || moveChoice > 4) {
            System.out.println("Invalid Move Choice... Please Try Again: ");
            moveChoice = scanner.nextInt();
        }
        return moveChoice;
    }

    public Monster getMonster() {return monster;}

    public int getHyperPotions() {return hyperPotions;}

    public int getSuperPotions() {return superPotions;}

    public int getX_attack() {return x_attack;}

    public int getX_defend() {return x_defend;}

    public void setHyperPotions(int hyperPotions) {this.hyperPotions = hyperPotions;}

    public void setSuperPotions(int superPotions) {this.superPotions = superPotions;}

    public void setX_attack(int x_attack) {this.x_attack = x_attack;}

    public void setX_defend(int x_defend) {this.x_defend = x_defend;}
}