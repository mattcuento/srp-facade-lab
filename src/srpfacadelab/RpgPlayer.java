package srpfacadelab;

import java.util.List;
import java.util.ArrayList;


public class RpgPlayer {
    public static final int MAX_CARRYING_CAPACITY = 1000;

    private final IGameEngine gameEngine;

    private int health;

    private int maxHealth;

    private int armour;

    private List<Item> inventory;

    private ItemManager itemManager;

    private InventoryManager inventoryManager;

    private DamageManager damageManager;

    // How much the player can carry in pounds
    private int carryingCapacity;

    public RpgPlayer(IGameEngine gameEngine) {
        this.gameEngine = gameEngine;
        inventory = new ArrayList<Item>();
        carryingCapacity = MAX_CARRYING_CAPACITY;

        itemManager = new ItemManager();
        inventoryManager = new InventoryManager();
        damageManager = new DamageManager();
    }

    public void useItem(Item item) {
        itemManager.useItem(this, item);
    }

    public boolean pickUpItem(Item item) {
        return itemManager.pickUpItem(this, item);
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public void calculateStats() {
        for (Item i: inventory) {
            armour += i.getArmour();
        }
    }

    private boolean checkIfItemExistsInInventory(Item item) {
        return inventoryManager.checkIfItemExistsInInventory(this, item);
    }

    private int calculateInventoryWeight() {
        return inventoryManager.calculateInventoryWeight(this);
    }

    public void takeDamage(int damage) {
        damageManager.takeDamage(this, damage);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getArmour() {
        return armour;
    }

    private void setArmour(int armour) {
        this.armour = armour;
    }

    public int getCarryingCapacity() {
        return carryingCapacity;
    }

    private void setCarryingCapacity(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public IGameEngine getGameEngine() {
        return gameEngine;
    }
}
