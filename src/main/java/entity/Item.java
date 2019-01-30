package entity;


/**
 * class of entity item with attributes <b>id</b> <b>name</b> <b>code</b>
 * @author  Stas Bondarchuk
*/
public class Item {
    /** attribute id*/
    int id;
    /** attribute name*/
    String name;
    /** attribute code*/
    String code;

    public Item(int id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
