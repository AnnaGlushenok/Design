package design.design;

public class Profile {
    private String imagePath, description, name;
    private Online lastOnline;
    private int countViews, countComments, countLikes, countRecommended;

    public Profile(String imagePath, String description, String name, Online lastOnline,  int countViews, int countComments, int countLikes, int countRecommended) {
        this.imagePath = imagePath;
        this.description = description;
        this.name = name;
        this.lastOnline = lastOnline;
        this.countViews = countViews;
        this.countComments = countComments;
        this.countLikes = countLikes;
        this.countRecommended = countRecommended;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Online getLastOnline() {
        return lastOnline;
    }

    public int getCountViews() {
        return countViews;
    }

    public int getCountComments() {
        return countComments;
    }

    public int getCountLikes() {
        return countLikes;
    }

    public int getCountRecommended() {
        return countRecommended;
    }
}
