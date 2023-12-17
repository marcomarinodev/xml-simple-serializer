import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class XMLSerializer {

  /**
   * For each object in arr, it introspects the object class searching for info to serialize the
   * object. It generates an XML file containing one element for each object in arr.
   */
  public static void serialize(Object[] arr, String fileName) {
    StringBuilder xmlStringBuilder = new StringBuilder();
    Map<String, SerializableClass> observedClasses = new HashMap<>();

    xmlStringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
    xmlStringBuilder.append("<list>\n");

    for (Object obj : arr) {
      Class<?> observedClass = obj.getClass();
      String className = observedClass.getSimpleName();

      if (!observedClass.isAnnotationPresent(XMLable.class)) {
        xmlStringBuilder.append("\t<notXMLable />\n");
        continue;
      }

      SerializableClass classToSerialize;

      if (!observedClasses.containsKey(className)) {
        // observed class has not been serialized yet, so let's serialize it and store in cache
        classToSerialize = introspect(observedClass);
        observedClasses.put(className, classToSerialize);
      } else {
        System.out.println("class " + className + " already introspected, taking the info from observed classes cache");
        classToSerialize = observedClasses.get(className);
      }

      try {
        classToSerialize.serialize(xmlStringBuilder, obj);
      } catch (IllegalAccessException e) {
        System.out.println(e);
      }

    }

    xmlStringBuilder.append("</list>\n");

    try {
      FileWriter myWriter = new FileWriter(fileName);
      myWriter.write(xmlStringBuilder.toString());
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public static SerializableClass introspect(Class<?> classToIntrospect) {
    SerializableClass introspectedClass = new SerializableClass();

    introspectedClass.setClassName(classToIntrospect.getSimpleName());

    // get the fields
    List<SerializableField> fields = new ArrayList<>();

    for (Field field: classToIntrospect.getDeclaredFields()) {
      SerializableField serializableField = new SerializableField();
      XMLfield xmlAnnotation = field.getAnnotation(XMLfield.class);

      if ((field.getType().isPrimitive() || field.getType().equals(String.class)) && xmlAnnotation != null) {
        field.setAccessible(true);

        serializableField.setName(xmlAnnotation.name().isEmpty() ? field.getName() : xmlAnnotation.name());
        serializableField.setType(xmlAnnotation.type());
        serializableField.setField(field);

        fields.add(serializableField);
      }
    }

    introspectedClass.setFields(fields);

    return introspectedClass;
  }
}
