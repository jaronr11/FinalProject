public abstract class Character {
    protected double health;
    protected double damage;
    protected Position position;

    protected void loseHealth(double health) {
        this.health -= health;
    }
    protected void gainHealth(double health) {
        this.health += health;
    }

    protected void moveTo(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public double getHealth() {
        return health;
    }
    public double getDamage() {
        return damage;
    }

    public void attack() {
        System.out.println(damage);
    }

}
