public class Evolution {

    private Monster monster;

    public Evolution(Monster monster){
        this.monster = monster;
    }

    public void evolve(){
        if(monster.getName().equals("Bulbasaur")) {
            monster.setName("Ivysaur");
        }
        else {
            monster.setName(monster.getName() + "2");
        }
        Move temp = new Move("Hyper Beam", "Normal", 120, 0.65f);
        monster.setMove1(temp);

        monster.setHP(monster.getInitialHP());
        monster.setHP((int)(monster.getHP()*1.5));

        monster.setAttack(monster.getInitialAttack());
        monster.setAttack(monster.getAttack() + 20);

        monster.setDefense(monster.getInitialDefense());
        monster.setDefense(monster.getDefense() + 20);

        monster.setSpeed(monster.getSpeed() + 10);

        monster.setInitialAttack(monster.getAttack());
        monster.setInitialDefense(monster.getDefense());
        monster.setInitialHP(monster.getHP());

    }
}
