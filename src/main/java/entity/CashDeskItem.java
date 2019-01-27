package entity;

import java.sql.Date;

public class CashDeskItem {
    Item item;
    int count;
    int ticketID;
    Date date;
    int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public CashDeskItem(Item item, int count, int ticketID, Date date, int ID) {
        this.item = item;
        this.count = count;
        this.ticketID = ticketID ;
        this.date = date;
        this.ID = ID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
