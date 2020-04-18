package srpfacadelab;

public class DamageManager {
    public void takeDamage(RpgPlayer rpgPlayer, int damage) {
    if (damage < rpgPlayer.getArmour()) {
            rpgPlayer.getGameEngine().playSpecialEffect("parry");
        }

        int damageToDeal = damage - rpgPlayer.getArmour();
        if (rpgPlayer.getCarryingCapacity() < RpgPlayer.MAX_CARRYING_CAPACITY / 2) {
            damageToDeal *= .75;
        }

        int newHealth = rpgPlayer.getHealth();
        rpgPlayer.setHealth( newHealth - damageToDeal);

        rpgPlayer.getGameEngine().playSpecialEffect("lots_of_gore");
    }
}