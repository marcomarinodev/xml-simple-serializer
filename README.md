# XML Serializer
A Java serializer to export an array of
objects in XML format. (Note that there are several Java APIs for XML serialization. Here it's provided
something different and much simpler).

## How to use it
You can just simply use the following annotations:
- @XMLable provides information about the class. The presence of this annotation says that
the objects of this class should be serialized. In this case the corresponding element will
contain other elements for the instance variables, if any. If instead the annotation is absent,
the element corresponding to the object must contain only the empty element
<notXMLable />.
- @XMLfield identifies serializable fields (i.e., instance variables, only of primitive types or
strings). The presence of this annotation states that the field must be serialized. The annotation
has a mandatory argument type, which is the type of the field (a String, for example
"int", "String",…), and an optional argument name, also of type String, which is the
XML tag to be used for the field. If the argument is not provided, the variable’s name is used
as a tag.