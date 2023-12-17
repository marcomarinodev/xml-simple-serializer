public class Main {
  public static void main(String[] args) throws Exception {
    Object[] arr = new Object[5];

    arr[0] = new Student("Jane", "Doe", 28);
    arr[1] = new NonSerializable();
    arr[2] = new Order(13.4, "00:00:00", false);

    // Student has been already introspected, therefore there's no need
    // to introspect it again
    arr[3] = new Student("Marco", "Marino", 23); 

    // same here
    arr[4] = new Order(91.2, "18:00:00", true);

    XMLSerializer.serialize(arr, "arr.xml");
  }
}


class NonSerializable {
  int foo;
  int bar;

  NonSerializable() {
    foo = 0;
    bar = 0;
  }
}
