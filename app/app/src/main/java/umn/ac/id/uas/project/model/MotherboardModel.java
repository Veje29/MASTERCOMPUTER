package umn.ac.id.uas.project.model;

import java.io.Serializable;
import java.util.ArrayList;

public class MotherboardModel implements Serializable {
    int id;
    String description;
    ArrayList<MotherboardModel> motherboards;
    int price;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public ArrayList<MotherboardModel> getMotherboards() {
        return motherboards;
    }

    @Override
    public String toString() {
        return "MotherboardModel{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", motherboards=" + motherboards +
                ", price=" + price +
                '}';
    }
}
