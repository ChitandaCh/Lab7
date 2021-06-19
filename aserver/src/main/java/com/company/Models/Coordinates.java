package com.company.Models;


import java.util.Objects;

public class Coordinates implements Comparable<Coordinates> {// интерфейс для сравнния обьектов
    private int x;
    private Long y; //Максимальное значение поля: 292, Поле не может быть null

    @Override
    public String toString() {
        return new StringBuilder().append("Coordinates{\r\n\t").append("\tx=").append(x).append(",\n\t\ty=").append(y).append("\n\t}").toString();
    }

    public Coordinates(int x, Long y) {
        setX(x);
        setY(y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) { this.y = y; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int compareTo(Coordinates o) {
        return y.compareTo(o.y);
    }
}