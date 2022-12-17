package Pages;

public class Page {
    private String title;

    public Page() { }

    public Page(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Pages.Page{"
                + "title='" + title + '\''
                + '}';
    }
}
