package wooter.spring.annotation.eg1;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ObjectToJsonConverter {

    private void checkIfSerializable(Object object) {
        if (Objects.isNull(object)) {
            throw new RuntimeException("The object to serialize is null");
        }

        Class<?> clazz = object.getClass();
        if (!clazz.isAnnotationPresent(JsonSerializable.class)) {
            throw new RuntimeException(
                "The class " + clazz.getSimpleName() + " is not annotated with JsonSerializable");
        }
    }

    private void initializeObject(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Init.class)) {
                method.setAccessible(true);
                method.invoke(object);
            }
        }
    }

    private String getJsonString(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        Map<String, String> jsonElementsMap = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(JsonElement.class)) {
                jsonElementsMap.put(getKey(field), (String)field.get(object));
            }
        }

        String jsonString = jsonElementsMap.entrySet().stream()
            .map(entry -> "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"").collect(Collectors.joining(","));
        return "{" + jsonString + "}";
    }

    private String getKey(Field field) {
        JsonElement jsonElement = field.getAnnotation(JsonElement.class);
        if (StringUtils.isNotEmpty(jsonElement.key())) {
            return jsonElement.key();
        }
        return field.getName();
    }

    public String convertToJson(Object object) throws Exception {
        checkIfSerializable(object);
        initializeObject(object);
        return getJsonString(object);
    }

}
