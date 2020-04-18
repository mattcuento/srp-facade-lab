package srpfacadelab;

public class InventoryManager {
    protected boolean checkIfItemExistsInInventory(RpgPlayer rpgPlayer, Item item) {
        for (Item i: rpgPlayer.getInventory()) {
            if (i.getId() == item.getId())
                return true;
        }
        return false;
    }

    protected int calculateInventoryWeight(RpgPlayer rpgPlayer) {
        int sum=0;
        for (Item i: rpgPlayer.getInventory()) {
            sum += i.getWeight();
        }
        return sum;
    }
}