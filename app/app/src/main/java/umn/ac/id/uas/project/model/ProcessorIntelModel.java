package umn.ac.id.uas.project.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ProcessorIntelModel implements Serializable {
    private int id;
    private String image;
    private String description;
    private String generation;
    private String type;
    private int price;
    private ArrayList<ProcessorIntelModel> processors;

    public String getImage() {
        return image;
    }

    public String getType() { return type; }

    public String getDescription() {
        return description;
    }

    public ArrayList<ProcessorIntelModel> getProcessors() {
        return processors;
    }

    public ArrayList<ProcessorIntelModel> getAmdProcessors() {
        return processors;
    }

    public String getGeneration() {
        return generation;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ProcessorIntelModel{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", generation='" + generation + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", processors=" + processors +
                '}';
    }
}
