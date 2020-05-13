package srpfacadelab;

public class Facade {

    private ItemManager itemManager;

    private DamageManager damageManager;

    public Facade(IGameEngine gameEngine) {
        this.damageManager = new DamageManager(gameEngine);
        this.itemManager = new ItemManager(gameEngine);
    }

    public void useItem(RpgPlayer player, Item item) {
        itemManager.useItem(player, item);
    }

    public boolean pickUpItem(RpgPlayer player, Item item) {
        return itemManager.pickUpItem(player, item);
    }

    public void takeDamage(RpgPlayer player, int damage) {
        damageManager.takeDamage(player, damage);
    }

}