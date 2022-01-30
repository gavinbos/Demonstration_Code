public class CPUPlayer extends Player{

    private Monster monster;
    private int hyperPotions;
    private int oranBerry;
    private int x_defend;
    private int x_attack;
    private int totalItems;

    public CPUPlayer(Monster monster, boolean isGymLeader){
        this.monster = monster;
        if(isGymLeader){
            hyperPotions = 3;
            x_defend = 1;
            x_attack = 1;
            totalItems = 4;
        }
        else{
            hyperPotions = 0;
            x_attack = 0;
            oranBerry = 3;
            x_defend = 1;
            totalItems =4;
        }
    }

    @Override
    public int chooseMove() {
        return (int)(Math.random()*4) + 1;
    }

    public Monster getMonster() {
        return monster;
    }

    public int getOranBerry() {return oranBerry;}   //getter and setter methods

    public int getX_defend(){
        return x_defend;
    }

    public int getX_attack() {return x_attack;}

    public int getHyperPotions() {return hyperPotions;}

    public int getTotalItems() {
        return totalItems;
    }

    public void setOranBerry(int oranBerry) {
        this.oranBerry = oranBerry;
    }

    public void setX_defend(int x_defend) {
        this.x_defend = x_defend;
    }

    public void setHyperPotions(int hyperPotions) {
        this.hyperPotions = hyperPotions;
    }

    public void setX_attack(int x_attack) {
        this.x_attack = x_attack;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

}