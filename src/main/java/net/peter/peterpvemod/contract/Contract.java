package net.peter.peterpvemod.contract;

public class Contract {
    private String contractName;
    private int itemsRequired;
    private String contractString;
    private String itemRequiredName;

    public String getMobToKill() {
        return mobToKill;
    }

    public void setMobToKill(String mobToKill) {
        this.mobToKill = mobToKill;
    }

    private String mobToKill;

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public int getItemsRequired() {
        return itemsRequired;
    }

    public void setItemsRequired(int itemsRequired) {
        this.itemsRequired = itemsRequired;
    }

    public String getContractString() {
        return contractString;
    }

    public void setContractString(String contractString) {
        this.contractString = contractString;
    }

    public String getItemRequiredName() {
        return itemRequiredName;
    }

    public void setItemRequiredName(String itemRequiredName) {
        this.itemRequiredName = itemRequiredName;
    }
}
