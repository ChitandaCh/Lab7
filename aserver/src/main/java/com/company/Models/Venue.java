package com.company.Models;


import com.company.Managers.TicketManager;

import java.util.Objects;

public class Venue {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private int capacity; //Значение поля должно быть больше 0
    private VenueType type; //Поле может быть null

    @Override
    public String toString() {
        return new StringBuilder().append("Venue{\r\n").append("\t\tid=").append(id).append(",\n\t\tname='").append(name).append('\'').append(",\n\t\tcapacity=").append(capacity).append(",\n\t\ttype=").append(type).append("\n\t}").toString();
    }

    public Venue(Long id, String name, int capacity, VenueType type) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.type = type;
    }

    public Venue(String name, int capacity, VenueType type) {
        id = TicketManager.getIds_venue();
        this.name = name;
        this.capacity = capacity;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public VenueType getType() {
        return type;
    }

    public void setType(VenueType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venue venue = (Venue) o;
        return capacity == venue.capacity && Objects.equals(name, venue.name) && type == venue.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, capacity, type);
    }
}