package umn.ac.id.uas.project.model;

import java.io.Serializable;

public class TopSpecification implements Serializable {
    private String name, processor, motherboard, cpu_cooler, ram, graphic_card;
    private int imageResource;

    public TopSpecification(String name, String processor, String motherboard, String cpu_cooler, String ram, String graphic_card, int imageResource) {
        this.name = name;
        this.processor = processor;
        this.motherboard = motherboard;
        this.cpu_cooler = cpu_cooler;
        this.ram = ram;
        this.graphic_card = graphic_card;
        this.imageResource = imageResource;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getName() {
        return name;
    }

    public String getProcessor() {
        return processor;
    }

    public String getMotherboard() {
        return motherboard;
    }

    public String getCpu_cooler() {
        return cpu_cooler;
    }

    public String getRam() {
        return ram;
    }

    public String getGraphic_card() {
        return graphic_card;
    }
}
