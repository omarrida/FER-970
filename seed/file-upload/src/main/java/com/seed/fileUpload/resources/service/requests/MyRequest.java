package com.seed.fileUpload.resources.service.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.seed.fileUpload.resources.service.types.MaybeList;
import com.seed.fileUpload.resources.service.types.MaybeListOrSet;
import com.seed.fileUpload.resources.service.types.MyObject;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = MyRequest.Builder.class)
public final class MyRequest {
    private final Optional<String> maybeString;

    private final int integer;

    private final Optional<Integer> maybeInteger;

    private final List<String> listOfStrings;

    private final Set<String> setOfStrings;

    private final Optional<List<String>> optionalListOfStrings;

    private final Optional<Set<String>> optionalSetOfStrings;

    private final MaybeList maybeList;

    private final Optional<MaybeList> optionalMaybeList;

    private final MaybeListOrSet maybeListOrSet;

    private final Optional<MaybeListOrSet> optionalMaybeListOrSet;

    private final List<MyObject> listOfObjects;

    private MyRequest(
            Optional<String> maybeString,
            int integer,
            Optional<Integer> maybeInteger,
            List<String> listOfStrings,
            Set<String> setOfStrings,
            Optional<List<String>> optionalListOfStrings,
            Optional<Set<String>> optionalSetOfStrings,
            MaybeList maybeList,
            Optional<MaybeList> optionalMaybeList,
            MaybeListOrSet maybeListOrSet,
            Optional<MaybeListOrSet> optionalMaybeListOrSet,
            List<MyObject> listOfObjects) {
        this.maybeString = maybeString;
        this.integer = integer;
        this.maybeInteger = maybeInteger;
        this.listOfStrings = listOfStrings;
        this.setOfStrings = setOfStrings;
        this.optionalListOfStrings = optionalListOfStrings;
        this.optionalSetOfStrings = optionalSetOfStrings;
        this.maybeList = maybeList;
        this.optionalMaybeList = optionalMaybeList;
        this.maybeListOrSet = maybeListOrSet;
        this.optionalMaybeListOrSet = optionalMaybeListOrSet;
        this.listOfObjects = listOfObjects;
    }

    @JsonProperty("maybeString")
    public Optional<String> getMaybeString() {
        return maybeString;
    }

    @JsonProperty("integer")
    public int getInteger() {
        return integer;
    }

    @JsonProperty("maybeInteger")
    public Optional<Integer> getMaybeInteger() {
        return maybeInteger;
    }

    @JsonProperty("listOfStrings")
    public List<String> getListOfStrings() {
        return listOfStrings;
    }

    @JsonProperty("setOfStrings")
    public Set<String> getSetOfStrings() {
        return setOfStrings;
    }

    @JsonProperty("optionalListOfStrings")
    public Optional<List<String>> getOptionalListOfStrings() {
        return optionalListOfStrings;
    }

    @JsonProperty("optionalSetOfStrings")
    public Optional<Set<String>> getOptionalSetOfStrings() {
        return optionalSetOfStrings;
    }

    @JsonProperty("maybeList")
    public MaybeList getMaybeList() {
        return maybeList;
    }

    @JsonProperty("optionalMaybeList")
    public Optional<MaybeList> getOptionalMaybeList() {
        return optionalMaybeList;
    }

    @JsonProperty("maybeListOrSet")
    public MaybeListOrSet getMaybeListOrSet() {
        return maybeListOrSet;
    }

    @JsonProperty("optionalMaybeListOrSet")
    public Optional<MaybeListOrSet> getOptionalMaybeListOrSet() {
        return optionalMaybeListOrSet;
    }

    @JsonProperty("listOfObjects")
    public List<MyObject> getListOfObjects() {
        return listOfObjects;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof MyRequest && equalTo((MyRequest) other);
    }

    private boolean equalTo(MyRequest other) {
        return maybeString.equals(other.maybeString)
                && integer == other.integer
                && maybeInteger.equals(other.maybeInteger)
                && listOfStrings.equals(other.listOfStrings)
                && setOfStrings.equals(other.setOfStrings)
                && optionalListOfStrings.equals(other.optionalListOfStrings)
                && optionalSetOfStrings.equals(other.optionalSetOfStrings)
                && maybeList.equals(other.maybeList)
                && optionalMaybeList.equals(other.optionalMaybeList)
                && maybeListOrSet.equals(other.maybeListOrSet)
                && optionalMaybeListOrSet.equals(other.optionalMaybeListOrSet)
                && listOfObjects.equals(other.listOfObjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.maybeString,
                this.integer,
                this.maybeInteger,
                this.listOfStrings,
                this.setOfStrings,
                this.optionalListOfStrings,
                this.optionalSetOfStrings,
                this.maybeList,
                this.optionalMaybeList,
                this.maybeListOrSet,
                this.optionalMaybeListOrSet,
                this.listOfObjects);
    }

    @Override
    public String toString() {
        return "MyRequest{" + "maybeString: " + maybeString + ", integer: " + integer + ", maybeInteger: "
                + maybeInteger + ", listOfStrings: " + listOfStrings + ", setOfStrings: " + setOfStrings
                + ", optionalListOfStrings: " + optionalListOfStrings + ", optionalSetOfStrings: "
                + optionalSetOfStrings + ", maybeList: " + maybeList + ", optionalMaybeList: " + optionalMaybeList
                + ", maybeListOrSet: " + maybeListOrSet + ", optionalMaybeListOrSet: " + optionalMaybeListOrSet
                + ", listOfObjects: " + listOfObjects + "}";
    }

    public static IntegerStage builder() {
        return new Builder();
    }

    public interface IntegerStage {
        MaybeListStage integer(int integer);

        Builder from(MyRequest other);
    }

    public interface MaybeListStage {
        MaybeListOrSetStage maybeList(MaybeList maybeList);
    }

    public interface MaybeListOrSetStage {
        _FinalStage maybeListOrSet(MaybeListOrSet maybeListOrSet);
    }

    public interface _FinalStage {
        MyRequest build();

        _FinalStage maybeString(Optional<String> maybeString);

        _FinalStage maybeString(String maybeString);

        _FinalStage maybeInteger(Optional<Integer> maybeInteger);

        _FinalStage maybeInteger(Integer maybeInteger);

        _FinalStage listOfStrings(List<String> listOfStrings);

        _FinalStage addListOfStrings(String listOfStrings);

        _FinalStage addAllListOfStrings(List<String> listOfStrings);

        _FinalStage setOfStrings(Set<String> setOfStrings);

        _FinalStage addSetOfStrings(String setOfStrings);

        _FinalStage addAllSetOfStrings(Set<String> setOfStrings);

        _FinalStage optionalListOfStrings(Optional<List<String>> optionalListOfStrings);

        _FinalStage optionalListOfStrings(List<String> optionalListOfStrings);

        _FinalStage optionalSetOfStrings(Optional<Set<String>> optionalSetOfStrings);

        _FinalStage optionalSetOfStrings(Set<String> optionalSetOfStrings);

        _FinalStage optionalMaybeList(Optional<MaybeList> optionalMaybeList);

        _FinalStage optionalMaybeList(MaybeList optionalMaybeList);

        _FinalStage optionalMaybeListOrSet(Optional<MaybeListOrSet> optionalMaybeListOrSet);

        _FinalStage optionalMaybeListOrSet(MaybeListOrSet optionalMaybeListOrSet);

        _FinalStage listOfObjects(List<MyObject> listOfObjects);

        _FinalStage addListOfObjects(MyObject listOfObjects);

        _FinalStage addAllListOfObjects(List<MyObject> listOfObjects);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements IntegerStage, MaybeListStage, MaybeListOrSetStage, _FinalStage {
        private int integer;

        private MaybeList maybeList;

        private MaybeListOrSet maybeListOrSet;

        private List<MyObject> listOfObjects = new ArrayList<>();

        private Optional<MaybeListOrSet> optionalMaybeListOrSet = Optional.empty();

        private Optional<MaybeList> optionalMaybeList = Optional.empty();

        private Optional<Set<String>> optionalSetOfStrings = Optional.empty();

        private Optional<List<String>> optionalListOfStrings = Optional.empty();

        private Set<String> setOfStrings = new LinkedHashSet<>();

        private List<String> listOfStrings = new ArrayList<>();

        private Optional<Integer> maybeInteger = Optional.empty();

        private Optional<String> maybeString = Optional.empty();

        private Builder() {}

        @Override
        public Builder from(MyRequest other) {
            maybeString(other.getMaybeString());
            integer(other.getInteger());
            maybeInteger(other.getMaybeInteger());
            listOfStrings(other.getListOfStrings());
            setOfStrings(other.getSetOfStrings());
            optionalListOfStrings(other.getOptionalListOfStrings());
            optionalSetOfStrings(other.getOptionalSetOfStrings());
            maybeList(other.getMaybeList());
            optionalMaybeList(other.getOptionalMaybeList());
            maybeListOrSet(other.getMaybeListOrSet());
            optionalMaybeListOrSet(other.getOptionalMaybeListOrSet());
            listOfObjects(other.getListOfObjects());
            return this;
        }

        @Override
        @JsonSetter("integer")
        public MaybeListStage integer(int integer) {
            this.integer = integer;
            return this;
        }

        @Override
        @JsonSetter("maybeList")
        public MaybeListOrSetStage maybeList(MaybeList maybeList) {
            this.maybeList = maybeList;
            return this;
        }

        @Override
        @JsonSetter("maybeListOrSet")
        public _FinalStage maybeListOrSet(MaybeListOrSet maybeListOrSet) {
            this.maybeListOrSet = maybeListOrSet;
            return this;
        }

        @Override
        public _FinalStage addAllListOfObjects(List<MyObject> listOfObjects) {
            this.listOfObjects.addAll(listOfObjects);
            return this;
        }

        @Override
        public _FinalStage addListOfObjects(MyObject listOfObjects) {
            this.listOfObjects.add(listOfObjects);
            return this;
        }

        @Override
        @JsonSetter(value = "listOfObjects", nulls = Nulls.SKIP)
        public _FinalStage listOfObjects(List<MyObject> listOfObjects) {
            this.listOfObjects.clear();
            this.listOfObjects.addAll(listOfObjects);
            return this;
        }

        @Override
        public _FinalStage optionalMaybeListOrSet(MaybeListOrSet optionalMaybeListOrSet) {
            this.optionalMaybeListOrSet = Optional.of(optionalMaybeListOrSet);
            return this;
        }

        @Override
        @JsonSetter(value = "optionalMaybeListOrSet", nulls = Nulls.SKIP)
        public _FinalStage optionalMaybeListOrSet(Optional<MaybeListOrSet> optionalMaybeListOrSet) {
            this.optionalMaybeListOrSet = optionalMaybeListOrSet;
            return this;
        }

        @Override
        public _FinalStage optionalMaybeList(MaybeList optionalMaybeList) {
            this.optionalMaybeList = Optional.of(optionalMaybeList);
            return this;
        }

        @Override
        @JsonSetter(value = "optionalMaybeList", nulls = Nulls.SKIP)
        public _FinalStage optionalMaybeList(Optional<MaybeList> optionalMaybeList) {
            this.optionalMaybeList = optionalMaybeList;
            return this;
        }

        @Override
        public _FinalStage optionalSetOfStrings(Set<String> optionalSetOfStrings) {
            this.optionalSetOfStrings = Optional.of(optionalSetOfStrings);
            return this;
        }

        @Override
        @JsonSetter(value = "optionalSetOfStrings", nulls = Nulls.SKIP)
        public _FinalStage optionalSetOfStrings(Optional<Set<String>> optionalSetOfStrings) {
            this.optionalSetOfStrings = optionalSetOfStrings;
            return this;
        }

        @Override
        public _FinalStage optionalListOfStrings(List<String> optionalListOfStrings) {
            this.optionalListOfStrings = Optional.of(optionalListOfStrings);
            return this;
        }

        @Override
        @JsonSetter(value = "optionalListOfStrings", nulls = Nulls.SKIP)
        public _FinalStage optionalListOfStrings(Optional<List<String>> optionalListOfStrings) {
            this.optionalListOfStrings = optionalListOfStrings;
            return this;
        }

        @Override
        public _FinalStage addAllSetOfStrings(Set<String> setOfStrings) {
            this.setOfStrings.addAll(setOfStrings);
            return this;
        }

        @Override
        public _FinalStage addSetOfStrings(String setOfStrings) {
            this.setOfStrings.add(setOfStrings);
            return this;
        }

        @Override
        @JsonSetter(value = "setOfStrings", nulls = Nulls.SKIP)
        public _FinalStage setOfStrings(Set<String> setOfStrings) {
            this.setOfStrings.clear();
            this.setOfStrings.addAll(setOfStrings);
            return this;
        }

        @Override
        public _FinalStage addAllListOfStrings(List<String> listOfStrings) {
            this.listOfStrings.addAll(listOfStrings);
            return this;
        }

        @Override
        public _FinalStage addListOfStrings(String listOfStrings) {
            this.listOfStrings.add(listOfStrings);
            return this;
        }

        @Override
        @JsonSetter(value = "listOfStrings", nulls = Nulls.SKIP)
        public _FinalStage listOfStrings(List<String> listOfStrings) {
            this.listOfStrings.clear();
            this.listOfStrings.addAll(listOfStrings);
            return this;
        }

        @Override
        public _FinalStage maybeInteger(Integer maybeInteger) {
            this.maybeInteger = Optional.of(maybeInteger);
            return this;
        }

        @Override
        @JsonSetter(value = "maybeInteger", nulls = Nulls.SKIP)
        public _FinalStage maybeInteger(Optional<Integer> maybeInteger) {
            this.maybeInteger = maybeInteger;
            return this;
        }

        @Override
        public _FinalStage maybeString(String maybeString) {
            this.maybeString = Optional.of(maybeString);
            return this;
        }

        @Override
        @JsonSetter(value = "maybeString", nulls = Nulls.SKIP)
        public _FinalStage maybeString(Optional<String> maybeString) {
            this.maybeString = maybeString;
            return this;
        }

        @Override
        public MyRequest build() {
            return new MyRequest(
                    maybeString,
                    integer,
                    maybeInteger,
                    listOfStrings,
                    setOfStrings,
                    optionalListOfStrings,
                    optionalSetOfStrings,
                    maybeList,
                    optionalMaybeList,
                    maybeListOrSet,
                    optionalMaybeListOrSet,
                    listOfObjects);
        }
    }
}
