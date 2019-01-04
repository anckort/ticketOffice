package entity;

public class Item {
    int id;
    String name;
    String code;
    boolean deletionMark;

    public Item(int id, String name, String code, boolean deletionMark) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.deletionMark = deletionMark;
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

    public boolean isDeletionMark() {
        return deletionMark;
    }

    public void setDeletionMark(boolean deletionMark) {
        this.deletionMark = deletionMark;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", deletionMark=" + deletionMark +
                '}';
    }
}
