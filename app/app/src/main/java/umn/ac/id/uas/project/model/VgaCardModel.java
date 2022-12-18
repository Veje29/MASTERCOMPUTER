package umn.ac.id.uas.project.model;

import java.io.Serializable;
import java.util.ArrayList;

public class VgaCardModel implements Serializable {
    int id;
    int picture;
    String description;
    ArrayList<VgaCardModel> vgas;
    String generation;
    int price;

    public int getId() {
        return id;
    }

    public int getPicture() {
        return picture;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<VgaCardModel> getVga() {
        return vgas;
    }

    public ArrayList<VgaCardModel> getVgas() {
        return vgas;
    }

    public String getGeneration() {
        return generation;
    }

    public int getPrice() {
        return price;
    }
}
