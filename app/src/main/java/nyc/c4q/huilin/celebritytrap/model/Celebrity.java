package nyc.c4q.huilin.celebritytrap.model;

/**
 * Created by huilin on 12/14/16.
 */
public class Celebrity {
    private Long _id;
    private String name;
    private String imgUrl;

    public Celebrity() {
        this.name = "Unknown";
    }

    public Celebrity(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
