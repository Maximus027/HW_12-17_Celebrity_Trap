package nyc.c4q.huilin.celebritytrap.model;

/**
 * Created by huilin on 12/14/16.
 */
public class Celebrity {
    private Long _id;
    private String name;
    private int image;

    public Celebrity() {
        this.name = "Unknown";
    }

    public Celebrity(String name, int resId) {
        this.name = name;
        this.image = resId;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
