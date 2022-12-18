package umn.ac.id.uas.project.model;

public class BuildDetailModel {
    private ProcessorIntelModel processor;
    private VgaCardModel vga;
    private MotherboardModel motherboard;
    private PowerSupplyModel power_supply;

    public ProcessorIntelModel getProcessor() {
        return processor;
    }

    public VgaCardModel getVga() {
        return vga;
    }

    public MotherboardModel getMotherboard() {
        return motherboard;
    }

    public PowerSupplyModel getPowerSupply() {
        return power_supply;
    }

    @Override
    public String toString() {
        return "BuildDetailModel{" +
                "processor=" + processor +
                ", vga=" + vga +
                ", motherboard=" + motherboard +
                ", powerSupply=" + power_supply +
                '}';
    }
}
