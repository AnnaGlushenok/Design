package design.design;

public enum Online {
    YESTERDAY("yesterday"), LAST_WEEK("last week"), LAST_MONTH("last month"), JUST_NOW("just now");

    private String online;

    Online(String online) {
        this.online = online;
    }

    public String getOnline() {
        return this.online;
    }
}
