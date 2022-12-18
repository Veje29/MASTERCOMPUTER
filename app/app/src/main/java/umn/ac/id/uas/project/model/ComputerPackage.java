package umn.ac.id.uas.project.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ComputerPackage implements Serializable {
    private String title, description, ranking, image_path;
    private double price;
    private ArrayList<ComputerPackage> computers;

//    public ComputerPackage(String title, double price, String description, int image, String ranking) {
//        this.title = title;
//        this.description = description;
//        this.ranking = ranking;
//        this.image = image;
//        this.price = price;
//    }

    public ArrayList<ComputerPackage> getTopSpecificationComputers() {
        return computers;
    }

    public ArrayList<ComputerPackage> getAllComputers() {
        return computers;
    }

    public String getRanking() {
        return ranking;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImage_path() {
        return image_path;
    }
}
