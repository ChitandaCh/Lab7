package com.company;

import com.company.Models.*;

import java.sql.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

public class DataBase {

    private Optional<Connection> connection;

    public DataBase(String url, String user, String password) {
        try {
            connection =  Optional.ofNullable(DriverManager.getConnection(url, user, password));
        } catch (SQLException e) {
            Main.logger.Error(e.getMessage());
            Main.logger.Info("база данных не доступна");
        }
    }

    public synchronized void InsertUser(user user) {
        String sql = "INSERT INTO users (name, password) "
                + "VALUES(?, ?)";

        connection.flatMap((Function<Connection, Optional<?>>) connection -> {
            Optional<Integer> Id = Optional.empty();
            try (PreparedStatement statement =
                         connection.prepareStatement(
                                 sql,
                                 Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, user.getUserLogin());
                statement.setString(2, user.getUserPassword());

                int numberOfInsertedRows = statement.executeUpdate();

                if (numberOfInsertedRows > 0) {
                    try (ResultSet resultSet = statement.getGeneratedKeys()) {
                        if (resultSet.next()) {
                            Id = Optional.of(resultSet.getInt(1));
                        }
                    }
                }

            } catch (SQLException ex) {
                Main.logger.Error(ex.getMessage());
                Main.logger.Info("при попытке запроса возникла ошибка");
                return Optional.empty();
            }

            return Id;
        });
    }
    public synchronized user SelectUser(String username, String password) {
        try {
            return connection.flatMap(conn -> {
                Optional<user> customer = Optional.empty();
                String sql = "SELECT * FROM users WHERE \"name\" = '" + username + "' AND \"password\" ='" + password + "'";

                try (PreparedStatement statement = conn.prepareStatement(sql);
                     ResultSet resultSet = statement.executeQuery()) {

                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");

                        return Optional.of(
                                new user(username, password, id));
                    }
                } catch (SQLException e) {
                    Main.logger.Error(e.getMessage());
                    Main.logger.Info("при попытке запроса возникла ошибка");
                    return Optional.empty();
                }
                return customer;
            }).orElse(null);
        }
        catch (NoSuchElementException e){
            return null;
        }
    }


    public synchronized void InsertTicket(Ticket ticket) {
        String sql = "INSERT INTO ticket (name, x, y , creationdate, price, discount, type, name_venue,capacity_venue, type_venue) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";

        connection.flatMap((Function<java.sql.Connection, Optional<?>>) connection -> {
            Optional<Integer> Id = Optional.empty();
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, ticket.getName());
                statement.setInt(2, ticket.getCoordinates().getX());
                statement.setInt(3, (int)(float)ticket.getCoordinates().getY());

                statement.setString(4, ticket.getCreationDate().toString());
                statement.setInt(5, ticket.getPrice());
                statement.setInt(6, ticket.getDiscount());
                statement.setString(7, ticket.getType().toString());
                statement.setString(8, ticket.getVenue().getName());
                statement.setInt(9, ticket.getVenue().getCapacity());
                statement.setString(10, ticket.getVenue().getType().toString());

                statement.executeUpdate();
            }
            catch (SQLException e) {
                Main.logger.Error(e.getMessage());
                Main.logger.Info("при попытке запроса возникла ошибка");
                e.printStackTrace();
                return Optional.empty();
            }

            return Id;
        });
    }
    public synchronized Collection<Ticket> SelectTickets(){
        Collection<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM ticket";

        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    ZonedDateTime Create = ZonedDateTime.parse(resultSet.getString("creationDate"));
                    TicketType ticketType = TicketType.valueOf(resultSet.getString("type"));
                    int x = resultSet.getInt("x");
                    int y = resultSet.getInt("y");
                    int price = resultSet.getInt("price");
                    int id = resultSet.getInt("id");
                    int IdCreate = resultSet.getInt("IdCreate");
                    int discount = resultSet.getInt("discount");
                    Venue venue = new Venue((long)resultSet.getInt("id_venue"),
                            resultSet.getString("name_venue"),
                            resultSet.getInt("capacity_venue"),
                            VenueType.valueOf(resultSet.getString("type_venue")));
                    Ticket ticket = new Ticket();
                    ticket.setName(name);
                    ticket.setIdCreate(IdCreate);
                    ticket.setCreationDate(Create);
                    ticket.setType(ticketType);
                    ticket.setCoordinates(new Coordinates(x,(long)y));
                    ticket.setPrice(price);
                    ticket.setId((long)id);
                    ticket.setDiscount(discount);
                    ticket.setVenue(venue);


                    tickets.add(ticket);
                }

            } catch (SQLException e) {
                Main.logger.Error(e.getMessage());
                Main.logger.Info("при попытке запроса возникла ошибка");
            }
        });
        return tickets;
    }
    public synchronized void DeleteTickets() {
        String sql = "DELETE FROM ticket";

        connection.ifPresent(conn -> {
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.executeUpdate();
            } catch (SQLException e) {
                Main.logger.Error(e.getMessage());
                Main.logger.Info("при попытке запроса возникла ошибка");
            }
        });
    }

}
