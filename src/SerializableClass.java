import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SerializableClass {
  private List<SerializableField> fields;
  private String className;

  public SerializableClass() {}

  public List<SerializableField> getFields() { return fields; }

  public String getClassName() {
    return className;
  }

  public void serialize(StringBuilder sb, Object obj) throws IllegalAccessException {
    sb.append("\t<").append(className).append(">\n");

    for (SerializableField serializableField : fields)
      sb.append(serializableField.serialize(obj));

    sb.append("\t</").append(className).append(">\n");
  }

  public void setFields(List<SerializableField> fields) {
    this.fields = fields;
  }

  public void setClassName(String className) {
    this.className = className;
  }
}

