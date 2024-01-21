public class ComboItem {
    private final String key;
    private final Object value;

    public ComboItem(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }
}
