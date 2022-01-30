/*
    represents a general player, provides inheritance relationship to HumanPlayer and CPUPlayer
 */
public abstract class Player{

    public Monster getMonster() {return this.getMonster();}

    /*
        uses the current objects monster to attack the enemy monster
        @param enemy - Player object to provide access to enemies private members
               moveNo - the number representing the move the computer will play
    */
    public void attack(Player enemy, int moveNo) {
        Move currentMove = null;

        switch (moveNo) {
            case 1:
                currentMove = this.getMonster().getMove1();
                break;
            case 2:
                currentMove = this.getMonster().getMove2();
                break;
            case 3:
                currentMove = this.getMonster().getMove3();
                break;
            case 4:
                currentMove = this.getMonster().getMove4();
                break;
            default:
                break;
        }

        System.out.printf("%s uses %s.\n", this.getMonster().getName(), currentMove.getName());

        if (currentMove.getName().equals("Dragon Dance")){
            this.getMonster().setAttack(this.getMonster().getAttack() + 10);
            this.getMonster().setDefense(this.getMonster().getDefense() + 5);
            System.out.printf("%s's attack rose sharply!\n", this.getMonster().getName());
            System.out.printf("%s's defense rose!\n", this.getMonster().getName());
        }

        else if(currentMove.getAccuracy() >= (float)(Math.random())) {
            int damageDealt = (this.getMonster().getAttack() + currentMove.getPower()) - enemy.getMonster().getDefense();
            enemy.getMonster().setHP(enemy.getMonster().getHP() - damageDealt);
            System.out.printf("%s hit for %d points of damage.\n", this.getMonster().getName(), damageDealt);
        }

        else{
            System.out.println(this.getMonster().getName() + " missed the attack!");
        }
    }

    /*
        determines if the current player has lost by comparing it's monster's HP to 0
        @return true if its monster's HP <=0, false otherwise
    */
    public boolean hasLost() {
        return this.getMonster().getHP() <= 0;
    }

    /*
        Determines if the players monster's speed is greater than the opponents monster's
        @param enemy - Player object to provide access to the opposing monsters speed
        @return true if speed is greater and false otherwise
    */
    public boolean isFasterThan(Player enemy) {
        return this.getMonster().getSpeed() >= enemy.getMonster().getSpeed();
    }

    public abstract int chooseMove();
}