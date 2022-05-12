package wooter.spring.annotation.eg1;

/**
 * https://www.baeldung.com/java-custom-annotation
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Person person = new Person("soufiane", "cheouati", "34", "China");
        ObjectToJsonConverter serializer = new ObjectToJsonConverter();
        String jsonString = serializer.convertToJson(person);
        System.out.println(jsonString);
    }
}
