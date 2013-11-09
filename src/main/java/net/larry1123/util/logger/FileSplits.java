package net.larry1123.util.logger;

public enum FileSplits {

    NONE("None"), //
    DAY("Day"), //
    HOUR("Hour"), //
    MONTH("Month"), //
    WEEK("Week");

    private final String ths;

    FileSplits(String type) {
        ths = type;
    }

    public String getValue() {
        return ths;
    }

    public static FileSplits getFromString(String type) {
        for (FileSplits t : FileSplits.values()) {
            if (t.getValue().toLowerCase().equals(type.toLowerCase())) {
                return t;
            }
        }
        return NONE;
    }

}
