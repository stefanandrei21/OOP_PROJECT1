import java.util.List;

public class Actions {
    private String type;
    private String page;
    private String feature;
    private String name;

    private String password;

    public Actions(){
        this.type = null;
        this.page = null;
        this.feature = null;
        this.name = null;
        this.password = null;
    }

    public Actions(String type, String page, String feature, String name, String password) {
        this.type = type;
        this.page = page;
        this.feature = feature;
        this.name = name;
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Actions{" +
                "type='" + type + '\'' +
                ", page='" + page + '\'' +
                ", feature='" + feature + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
