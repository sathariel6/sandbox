package helper;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class Car {

    private final int id;
    private final String name;
    private final String type;

    private Car(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.type = builder.type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        return new EqualsBuilder()
                .append(id, car.id)
                .append(name, car.name)
                .append(type, car.type)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(type)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public static class Builder {
        private int id;
        private String name;
        private String type;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}
