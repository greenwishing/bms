package cn.greenwishing.bms.utils;

import com.google.gson.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wufan
 * @date 2017/12/29
 */
public class GsonUtil {


    private static final Map<Class, CustomSerializer> SERIALIZER_MAP = new HashMap<>();

    static {
        SERIALIZER_MAP.put(Date.class, new DateSerializer());
        SERIALIZER_MAP.put(LocalDate.class, new LocalDateSerializer());
        SERIALIZER_MAP.put(DateTime.class, new DateTimeSerializer());
        SERIALIZER_MAP.put(LocalTime.class, new LocalTimeSerializer());
    }

    private static Gson gson;

    public static Gson buildGson() {
        if (gson == null) {
            gson = builder().create();
        }
        return gson;
    }

    /**
     * @see Gson#toJson(Object)
     */
    public static String toJson(Object src) {
        return buildGson().toJson(src);
    }

    /**
     * @see Gson#toJson(JsonElement)
     */
    public static String toJson(JsonElement jsonElement) {
        return buildGson().toJson(jsonElement);
    }

    /**
     * @see Gson#fromJson(String, Class)
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        return buildGson().fromJson(json, classOfT);
    }

    /**
     * @see Gson#fromJson(String, Type)
     */
    public static <T> T fromJson(String json, Type typeOfT) {
        return buildGson().fromJson(json, typeOfT);
    }

    public static GsonBuilder builder() {
        GsonBuilder builder = new GsonBuilder();
        for (Map.Entry<Class, CustomSerializer> entry : SERIALIZER_MAP.entrySet()) {
            builder.registerTypeAdapter(entry.getKey(), entry.getValue());
        }
        return builder;
    }


    private interface CustomSerializer<T> extends JsonSerializer<T>, JsonDeserializer<T> {

    }

    private static class DateSerializer implements CustomSerializer<Date> {
        @Override
        public Date deserialize(JsonElement el, Type type, JsonDeserializationContext context) throws JsonParseException {
            return new Date(el.getAsLong());
        }

        @Override
        public JsonElement serialize(Date src, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(src.getTime());
        }
    }

    private static class LocalDateSerializer implements CustomSerializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonElement el, Type type, JsonDeserializationContext context) throws JsonParseException {
            return new LocalDate(el.getAsLong());
        }

        @Override
        public JsonElement serialize(LocalDate src, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(src.toDateTimeAtStartOfDay().getMillis());
        }
    }

    private static class DateTimeSerializer implements CustomSerializer<DateTime> {
        @Override
        public DateTime deserialize(JsonElement el, Type type, JsonDeserializationContext context) throws JsonParseException {
            return new DateTime(el.getAsLong());
        }

        @Override
        public JsonElement serialize(DateTime src, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(src.getMillis());
        }
    }

    private static class LocalTimeSerializer implements CustomSerializer<LocalTime> {
        @Override
        public LocalTime deserialize(JsonElement el, Type type, JsonDeserializationContext context) throws JsonParseException {
            return new LocalTime(el.getAsLong(), DateTimeZone.UTC);
        }

        @Override
        public JsonElement serialize(LocalTime src, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(src.getMillisOfDay());
        }
    }
}
