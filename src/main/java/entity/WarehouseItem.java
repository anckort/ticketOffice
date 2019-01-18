package entity;

public class WarehouseItem {
    int count;
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
