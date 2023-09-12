package design.design;

public class User {
    private String firstName;
    private String lastName;
    private int age;
    private Sex sex;
    private String squareAvatarUrl = "https://image.cnbcfm.com/api/v1/image/105773423-1551716977818rtx6p9yw.jpg?v=1551717428&w=700&h=700";
    private String description;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }

    public String getSquareAvatarUrl() {
        return squareAvatarUrl;
    }

    public String getDescription() {
        return description;
    }

    public User(String firstName, String lastName, int age, Sex sex, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
        this.description = description;
    }
}
