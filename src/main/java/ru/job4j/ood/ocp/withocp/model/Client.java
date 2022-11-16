package ru.job4j.ood.ocp.withocp.model;

import java.util.Objects;

public class Client {
    private String name;
    private String surname;
    private boolean active;

    public Client(String name, String surname, boolean active) {
        this.name = name;
        this.surname = surname;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Client client = (Client) o;
        return active == client.active && Objects.equals(name, client.name) && Objects.equals(surname, client.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, active);
    }

    @Override
    public String toString() {
        return "Client{"
                + "name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", active=" + active
                + '}';
    }
}
