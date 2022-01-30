public class Move{
    private String name;
    private String type;
    private int power;
    private float accuracy;

    public Move(String name, String type, int power, float accuracy) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public float getAccuracy() {
        return accuracy;
    }
}