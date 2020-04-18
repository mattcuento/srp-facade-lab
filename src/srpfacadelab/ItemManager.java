package srpfacadelab;

import java.util.List;
import java.util.ArrayList;

public class ItemManager {

    public void useItem(RpgPlayer rpgPlayer, Item item) {
        if (item.getName().equals("Stink Bomb"))
        {
            List<IEnemy> enemies = rpgPlayer.getGameEngine().getEnemiesNear(rpgPlayer);

            for (IEnemy enemy: enemies){
                enemy.takeDamage(100);
            }
        }
    }

    public boolean pickUpItem(RpgPlayer rpgPlayer, Item item) {
        int weight = rpgPlayer.getInventoryManager().calculateInventoryWeight(rpgPlayer);
        if (weight + item.getWeight() > rpgPlayer.getCarryingCapacity())
            return false;

        if (item.isUnique() && rpgPlayer.getInventoryManager().checkIfItemExistsInInventory(rpgPlayer, item))
            return false;

        // Don't pick up items that give health, just consume them.
        if (item.getHeal() > 0) {
            int newHealth = rpgPlayer.getHealth() + item.getHeal();

            if (newHealth > rpgPlayer.getMaxHealth())
                rpgPlayer.setHealth(rpgPlayer.getMaxHealth());

            if (item.getHeal() > 500) {
                rpgPlayer.getGameEngine().playSpecialEffect("green_swirly");
            }

            return true;
        }

        if (item.isRare())
            rpgPlayer.getGameEngine().playSpecialEffect("cool_swirly_particles");

        rpgPlayer.getInventory().add(item);

        rpgPlayer.calculateStats();

        return true;
    }
}