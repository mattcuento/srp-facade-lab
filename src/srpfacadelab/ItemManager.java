package srpfacadelab;

import java.util.List;
import java.util.ArrayList;

public class ItemManager {

    private IGameEngine gameEngine;

    public ItemManager(IGameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void useItem(RpgPlayer rpgPlayer, Item item) {
        if (item.getName().equals("Stink Bomb")) {
            List<IEnemy> enemies = gameEngine.getEnemiesNear(rpgPlayer);

            for (IEnemy enemy : enemies) {
                enemy.takeDamage(100);
            }
        }
    }

    public boolean pickUpItem(RpgPlayer rpgPlayer, Item item) {
        int weight = calculateInventoryWeight(rpgPlayer);
        if (weight + item.getWeight() > rpgPlayer.getCarryingCapacity())
            return false;

        if (item.isUnique() && checkIfItemExistsInInventory(rpgPlayer, item))
            return false;

        // Don't pick up items that give health, just consume them.
        if (item.getHeal() > 0) {
            int newHealth = rpgPlayer.getHealth() + item.getHeal();

            if (newHealth > rpgPlayer.getMaxHealth())
                rpgPlayer.setHealth(rpgPlayer.getMaxHealth());

            if (item.getHeal() > 500) {
                gameEngine.playSpecialEffect("green_swirly");
            }

            return true;
        }

        if (item.isRare())
            gameEngine.playSpecialEffect("cool_swirly_particles");

        rpgPlayer.getInventory().add(item);

        calculateStats(rpgPlayer);

        return true;
    }

    public void calculateStats(RpgPlayer player) {
        for (Item i: player.getInventory()) {
            player.setArmour(player.getArmour() + i.getArmour();
        }
    }

    private boolean checkIfItemExistsInInventory(RpgPlayer player, Item item) {
        for (Item i : player.getInventory()) {
            if (i.getId() == item.getId())
                return true;
        }
        return false;
    }

    private int calculateInventoryWeight(RpgPlayer player) {
        int sum = 0;
        for (Item i : player.getInventory()) {
            sum += i.getWeight();
        }
        return sum;
    }
}