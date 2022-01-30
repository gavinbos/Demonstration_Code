import java.util.Scanner;

public class Battle {

    public HumanPlayer player;
    public CPUPlayer comp;
    public boolean isGymBattle;

    public Battle(HumanPlayer player, CPUPlayer comp, boolean isGymBattle) { //battle constructor
        this.player = player;
        this.comp = comp;
        this.isGymBattle = isGymBattle;
    }

    public int Run(){ //runs the main battle loop

        if (isGymBattle) {
            System.out.println("You've challenged the Gym Leader!...");
            Scanner scanner = new Scanner(System.in);

            System.out.println("Time to Battle!");

            while ((!player.hasLost()) && (!comp.hasLost())) {
                System.out.printf("\n%s has %d HP\n", player.getMonster().getName(), player.getMonster().getHP());
                System.out.printf("%s has %d HP\n", comp.getMonster().getName(), comp.getMonster().getHP());
                System.out.println("What would you like to do?");
                System.out.println("(enter a to attack, or i to access items)");
                String choice = scanner.next();

                boolean compUseItem = false;
                boolean playerUseItem = false;
                boolean compHeal = false;

                if (choice.equals("i")){
                    useItem();
                    playerUseItem = true;
                }

                if (Math.random() <= 0.20) {
                    compUseItem = true;
                }

                if(compUseItem && comp.getTotalItems() == 0){
                    compUseItem =  false;
                }

                if (choice.equals("a")){
                    Attack(player, comp, compUseItem);
                }

                if((comp.getMonster().getHP() <= 75) && (comp.getMonster().getHP() > 0)) {
                    System.out.println("The Gym Leader used a Hyper Potion!");
                    comp.getMonster().setHP(comp.getMonster().getHP() + 100);
                    System.out.printf("%s has been healed by 100 HP!\n\n", comp.getMonster().getName());
                    comp.setHyperPotions(comp.getHyperPotions() -1);
                    comp.setTotalItems(comp.getTotalItems() -1);
                    compHeal = true;
                }

                if(compUseItem && !compHeal){
                    compUseItem(comp);
                }

                if (playerUseItem && !compUseItem){
                    comp.attack(player, comp.chooseMove());
                }

            }

            if (player.hasLost()) {
                System.out.printf("You and %s have lost the battle.\n", player.getMonster().getName());
                return 0;
            }

            else {
                System.out.printf("You and %s are victorious!\n", player.getMonster().getName());
                return 0;
            }
        } else {
            System.out.println("You've encountered a trainer...");
            Scanner scanner = new Scanner(System.in);

            System.out.println("Time to Battle!");

            while ((!player.hasLost()) && (!comp.hasLost())) {
                System.out.printf("\n%s has %d HP\n", player.getMonster().getName(), player.getMonster().getHP());
                System.out.printf("%s has %d HP\n", comp.getMonster().getName(), comp.getMonster().getHP());
                System.out.println("What would you like to do?");
                System.out.println("(enter a to attack, or i to access items)");
                String choice = scanner.next();

                boolean compUseItem = false;
                boolean playerUseItem = false;

                if (choice.equals("i")){
                    useItem();
                    playerUseItem = true;
                }
                if (Math.random() >= 0.80) {
                    compUseItem = true;
                }
                if (choice.equals("a")){
                    Attack(player, comp, compUseItem);
                }
                if(compUseItem && comp.getTotalItems() > 0 && !comp.hasLost()) {
                    compUseItem(comp);
                }
                if (playerUseItem && !compUseItem){
                    comp.attack(player, comp.chooseMove());
                }

            }

            if (player.hasLost()) {
                System.out.printf("You and %s have lost the battle.\n", player.getMonster().getName());
                return 0;
            }

            else {
                System.out.printf("You and %s are victorious!\n", player.getMonster().getName());
                return 0;
            }
        }
    }

    public void Attack(HumanPlayer player, CPUPlayer comp, boolean itemUsed){ //commences the attacks
        int moveChoice = player.chooseMove();
        int compChoice = comp.chooseMove();

        if (itemUsed) {
            player.attack(comp, moveChoice);
        }
        else{
            if (player.isFasterThan(comp)){
                player.attack(comp, moveChoice);
                if (!comp.hasLost()){
                    comp.attack(player, compChoice);
                }
            }

            else{
                comp.attack(player, compChoice);
                if (!player.hasLost()){
                    player.attack(comp, moveChoice);
                }
            }
        }
    }

    public void compUseItem(CPUPlayer c) {
        double rand = Math.random();
        if (rand <= 0.25 && c.getX_defend() > 0){
            System.out.println("The opposing trainer used X_Defend!");
            System.out.printf("%s's defense has been increased temporarily!\n", c.getMonster().getName());
            c.getMonster().setDefense(c.getMonster().getDefense() + 25);
            c.setX_defend(c.getX_defend() -1);
            c.setTotalItems(c.getTotalItems() -1);
        }
        else if((rand > 0.25 && rand <= 0.5) && c.getX_attack() > 0){
            System.out.println("The opposing trainer used X_Attack!");
            System.out.printf("%s's Attack has been increased temporarily!\n", c.getMonster().getName());
            c.getMonster().setAttack(c.getMonster().getAttack() + 25);
            c.setX_attack(c.getX_attack() -1);
            c.setTotalItems(c.getTotalItems() -1);
        }

        else if (c.getHyperPotions() > 0) {
            System.out.println("The opposing trainer used a Hyper Potion!");
            int hpIncrease = 0;
            if ((c.getMonster().getInitialHP() - c.getMonster().getHP()) >= 100){
                hpIncrease = 100;
                c.getMonster().setHP(c.getMonster().getHP() + hpIncrease);
                System.out.printf("%s has been healed by %d HP!\n\n", c.getMonster().getName(), hpIncrease);
                c.setHyperPotions(c.getHyperPotions() -1);
                c.setTotalItems(c.getTotalItems() -1);
            }
            else{
                hpIncrease = (c.getMonster().getInitialHP())%(c.getMonster().getHP());
                c.getMonster().setHP(c.getMonster().getHP() + hpIncrease);
                System.out.printf("%s has been healed by %d HP!\n\n", c.getMonster().getName(), hpIncrease);
                c.setHyperPotions(c.getHyperPotions() -1);
                c.setTotalItems(c.getTotalItems() -1);
            }
        }
        else if (c.getOranBerry() > 0){
            System.out.println("The opposing trainer used an Oran Berry!");
            int hpIncrease = 0;
            if ((c.getMonster().getInitialHP() - c.getMonster().getHP()) >= 20){
                hpIncrease = 20;
                c.getMonster().setHP(c.getMonster().getHP() + hpIncrease);
                System.out.printf("%s has been healed by %d HP!\n\n", c.getMonster().getName(), hpIncrease);
                c.setHyperPotions(c.getOranBerry() -1);
                c.setTotalItems(c.getTotalItems() -1);
            }
            else{
                hpIncrease = (c.getMonster().getInitialHP())%(c.getMonster().getHP());
                c.getMonster().setHP(c.getMonster().getHP() + hpIncrease);
                System.out.printf("%s has been healed by %d HP!\n\n", c.getMonster().getName(), hpIncrease);
                c.setHyperPotions(c.getOranBerry() -1);
                c.setTotalItems(c.getTotalItems() -1);
            }
        }
        else{
            c.attack(player, c.chooseMove());
        }
    }

    public void useItem(){ //allows access to inventory and use of items
        Scanner scanner = new Scanner(System.in);
        final int[] itemChoice = new int[1];

        System.out.println("\n **** INVENTORY **** \n");
        if(player.getSuperPotions() != 0){
            System.out.println("1. Super Potions: " + player.getSuperPotions() + "\tadd 50 HP to your monster");
        }

        if(player.getHyperPotions() != 0){
            System.out.println("2. Hyper Potions: " + player.getHyperPotions() + "\tadd 100 HP to your monster");
        }

        if(player.getX_attack() != 0){
            System.out.println("3. X_Attack: " + player.getX_attack() + "\tincrease your monsters attack stat temporarily");
        }

        if(player.getX_defend() != 0){
            System.out.println("4. X_Defend: " + player.getX_defend() + "\tincrease your monsters defense stat temporarily");
        }

        System.out.println("\nWhich item would you like to use?");
        System.out.println("(enter a number 1-4 to select which item to use)");
        itemChoice[0] = scanner.nextInt();

        switch(itemChoice[0]) {
            case 1:
                player.getMonster().setHP(player.getMonster().getHP() + 50);
                player.setSuperPotions(player.getSuperPotions() - 1);
                System.out.printf("%s has been healed by 50 HP\n", player.getMonster().getName());
                break;
            case 2:
                player.getMonster().setHP(player.getMonster().getHP() + 100);
                player.setHyperPotions(player.getHyperPotions() - 1);
                System.out.printf("%s has been healed by 100 HP\n", player.getMonster().getName());
                break;
            case 3:
                player.getMonster().setAttack(player.getMonster().getAttack() + 25);
                player.setX_attack(player.getX_attack() -1);
                System.out.printf("%s's attack has been increased temporarily!\n", player.getMonster().getName());
                break;
            case 4:
                player.getMonster().setDefense(player.getMonster().getDefense() + 25);
                player.setX_defend(player.getX_defend() -1);
                System.out.printf("%s's defense has been increased temporarily!\n", player.getMonster().getName());
                break;
            default:
                break;
        }
    }

}