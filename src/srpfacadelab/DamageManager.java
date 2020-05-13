package srpfacadelab;

public class DamageManager {

    private IGameEngine gameEngine;

    public DamageManager(IGameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void takeDamage(RpgPlayer rpgPlayer, int damage) {
        if (damage < rpgPlayer.getArmour()) {
            gameEngine.playSpecialEffect("parry");
        }

        int damageToDeal = damage - rpgPlayer.getArmour();
        if (rpgPlayer.getCarryingCapacity() < RpgPlayer.MAX_CARRYING_CAPACITY / 2) {
            damageToDeal *= .75;
        }

        int newHealth = rpgPlayer.getHealth();
        rpgPlayer.setHealth(newHealth - damageToDeal);

        gameEngine.playSpecialEffect("lots_of_gore");
    }
}