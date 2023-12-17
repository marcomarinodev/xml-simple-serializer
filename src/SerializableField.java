import java.lang.reflect.Field;

public class SerializableField {
    private String name;
    private String type;
    private Field field;

    public SerializableField() {}

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String serialize(Object obj) throws IllegalAccessException {
        return "\t\t<" + name + " type=\"" + type + "\">" + field.get(obj) + "</" + name + ">\n";
    }

}