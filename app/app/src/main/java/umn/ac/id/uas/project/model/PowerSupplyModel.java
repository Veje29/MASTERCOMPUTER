package umn.ac.id.uas.project.model;

import java.io.Serializable;
import java.util.ArrayList;

public class PowerSupplyModel implements Serializable {
    int id;
    String description;
    ArrayList<PowerSupplyModel> power_supplies;
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

    public ArrayList<PowerSupplyModel> getPowerSupplies() {
        return power_supplies;
    }

    @Override
    public String toString() {
        return "PowerSupplyModel{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", power_supplies=" + power_supplies +
                ", price=" + price +
                '}';
    }
}
