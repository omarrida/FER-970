package com.seed.fileUpload.resources.service.types;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.seed.fileUpload.core.ObjectMappers;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@JsonDeserialize(using = MaybeListOrSet.Deserializer.class)
public final class MaybeListOrSet {
    private final Object value;

    private final int type;

    private MaybeListOrSet(Object value, int type) {
        this.value = value;
        this.type = type;
    }

    @JsonValue
    public Object get() {
        return this.value;
    }

    public <T> T visit(Visitor<T> visitor) {
        if (this.type == 0) {
            return visitor.visit((String) this.value);
        } else if (this.type == 1) {
            return visitor.visit((List<String>) this.value);
        } else if (this.type == 2) {
            return visitor.visit((int) this.value);
        } else if (this.type == 3) {
            return visitor.visit((List<Integer>) this.value);
        } else if (this.type == 4) {
            return visitor.visit((List<List<Integer>>) this.value);
        } else if (this.type == 5) {
            return visitor.visit((Set<String>) this.value);
        }
        throw new IllegalStateException("Failed to visit value. This should never happen.");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof MaybeListOrSet && equalTo((MaybeListOrSet) other);
    }

    private boolean equalTo(MaybeListOrSet other) {
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    public static MaybeListOrSet of(String value) {
        return new MaybeListOrSet(value, 0);
    }

    public static MaybeListOrSet of(List<String> value) {
        return new MaybeListOrSet(value, 1);
    }

    public static MaybeListOrSet of(int value) {
        return new MaybeListOrSet(value, 2);
    }

    public static MaybeListOrSet of(List<Integer> value) {
        return new MaybeListOrSet(value, 3);
    }

    public static MaybeListOrSet of(List<List<Integer>> value) {
        return new MaybeListOrSet(value, 4);
    }

    public static MaybeListOrSet of(Set<String> value) {
        return new MaybeListOrSet(value, 5);
    }

    public interface Visitor<T> {
        T visit(String value);

        T visit(List<String> value);

        T visit(int value);

        T visit(List<Integer> value);

        T visit(List<List<Integer>> value);

        T visit(Set<String> value);
    }

    static final class Deserializer extends StdDeserializer<MaybeListOrSet> {
        Deserializer() {
            super(MaybeListOrSet.class);
        }

        @Override
        public MaybeListOrSet deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            Object value = p.readValueAs(Object.class);
            try {
                return of(ObjectMappers.JSON_MAPPER.convertValue(value, String.class));
            } catch (IllegalArgumentException e) {
            }
            try {
                return of(ObjectMappers.JSON_MAPPER.convertValue(value, new TypeReference<List<String>>() {}));
            } catch (IllegalArgumentException e) {
            }
            if (value instanceof Integer) {
                return of((Integer) value);
            }
            try {
                return of(ObjectMappers.JSON_MAPPER.convertValue(value, new TypeReference<List<Integer>>() {}));
            } catch (IllegalArgumentException e) {
            }
            try {
                return of(ObjectMappers.JSON_MAPPER.convertValue(value, new TypeReference<List<List<Integer>>>() {}));
            } catch (IllegalArgumentException e) {
            }
            try {
                return of(ObjectMappers.JSON_MAPPER.convertValue(value, new TypeReference<Set<String>>() {}));
            } catch (IllegalArgumentException e) {
            }
            throw new JsonParseException(p, "Failed to deserialize");
        }
    }
}
