package entity;

/**
 * class of entity warehouse item with attributes <b>id</b> <b>name</b> <b>code</b>
 * @author  Stas Bondarchuk
 */
public class WarehouseItem {
    /** attribute count*/
    int count;
    /** attribute item*/
    Item item;

    public WarehouseItem( int count, Item item) {
        this.count = count;
        this.item = item;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
