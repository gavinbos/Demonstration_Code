public class Monster{
    private String name;
    private String type;
    private int hp;
    private int speed;
    private int attack;
    private int defense;
    private Move move1;
    private Move move2;
    private Move move3;
    private Move move4;
    private int initialAttack;
    private int initialDefense;
    private int initialHP;

    public Monster(String name, String type, int hp, int speed, int attack, int defense, Move move1, Move move2, Move move3, Move move4){
        this.name = name;
        this.type = type;
        this.hp = hp;
        this.speed = speed;
        this.attack = attack;
        this.defense = defense;
        this.move1 = move1;
        this.move2 = move2;
        this.move3 = move3;
        this.move4 = move4;
        initialAttack = attack;
        initialDefense = defense;
        initialHP = hp;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getHP() {
        return hp;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public Move getMove1() {
        return move1;
    }

    public Move getMove2() {
        return move2;
    }

    public Move getMove3() {
        return move3;
    }

    public Move getMove4() {
        return move4;
    }

    public int getInitialAttack() {
        return initialAttack;
    }

    public int getInitialDefense() {
        return initialDefense;
    }

    public int getInitialHP() {
        return initialHP;
    }

    public void setHP(int hp) {
        this.hp = hp;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setName(String name) {this.name = name;}

    public void setSpeed(int speed) { this.speed = speed;}

    public void setMove1(Move move1) {
        this.move1 = move1;
    }

    public void setMove2(Move move2) {
        this.move2 = move2;
    }

    public void setMove3(Move move3) {
        this.move3 = move3;
    }

    public void setMove4(Move move4) {
        this.move4 = move4;
    }

    public void setInitialAttack(int initialAttack) {
        this.initialAttack = initialAttack;
    }

    public void setInitialDefense(int initialDefense) {
        this.initialDefense = initialDefense;
    }

    public void setInitialHP(int initialHP) {
        this.initialHP = initialHP;
    }
}