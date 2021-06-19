package com.company.Models;

import com.company.Manager.TicketManager;

import java.time.ZonedDateTime;

public class Ticket implements Comparable<Ticket>{
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer price; //Поле не может быть null, Значение поля должно быть больше 0
    private Integer discount; //Поле не может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 100
    private TicketType type; //Поле может быть null
    private Venue venue; //Поле не может быть null



    @Override
    public String toString() {
        return new StringBuilder().append("Ticket{\r\n\t").append("id=").append(id).append(",\r\n\tname='").append(name).append('\'').append(",\n\tcoordinates=").append(coordinates).append(",\n\tcreationDate=").append(creationDate).append(",\n\tprice=").append(price).append(",\n\tdiscount=").append(discount).append(",\n\ttype=").append(type).append(",\n\tvenue=").append(venue).append("\n}").toString();
    }

    public Ticket(Long id, String name, Coordinates coordinates, ZonedDateTime creationDate, Integer price, Integer discount, TicketType type, Venue venue) {
        this.id = id;
        setName(name);
        setCoordinates(coordinates);
        setCreationDate(creationDate);
        setPrice(price);
        setDiscount(discount);
        setType(type);
        setVenue(venue);
    }

    public Ticket(String name, Coordinates coordinates, Integer price, Integer discount, TicketType type, Venue venue) {
        this.id = TicketManager.ids_ticket;
        TicketManager.ids_ticket++;
        setName(name);
        setCoordinates(coordinates);
        setPrice(price);
        setDiscount(discount);
        setType(type);
        setVenue(venue);
        creationDate = ZonedDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) { this.coordinates = coordinates; }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) { this.creationDate = creationDate; }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) { this.price = price; }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) { this.discount = discount; }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) { this.venue = venue; }

    @Override
    public int compareTo(Ticket o) {
        return name.compareTo(o.name);
    }
}