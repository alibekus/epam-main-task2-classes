package kz.akbar.service;

public enum SortType {
    ASC("Ascending"),
    DESC("Descending");

    private String type;

    SortType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return type;
    }
}
