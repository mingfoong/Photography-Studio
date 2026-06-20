package PhotographyStudio;

public class Product extends PhotographySession {
    private String productType;
    private int numOfProduct;
    private String editingLevel;

    public Product(String photoSessionID, String photoSessionName, String photoSessionType, double price, String isAPackage,
                   String productType, int numOfProduct, String editingLevel, String appointmentID) {
        super(photoSessionID, photoSessionName, photoSessionType, price, isAPackage, appointmentID);
        this.productType = productType;
        this.numOfProduct = numOfProduct;
        this.editingLevel = editingLevel;
    }

    // Getters and Setters
    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getNumOfProduct() {
        return numOfProduct;
    }

    public void setNumOfProduct(int numOfProduct) {
        this.numOfProduct = numOfProduct;
    }

    public String getEditingLevel() {
        return editingLevel;
    }

    public void setEditingLevel(String editingLevel) {
        this.editingLevel = editingLevel;
    }

    @Override
    public String toString() {
        StringBuilder strbuild = new StringBuilder();
        strbuild.append(String.format("%-20s | %s%n", "Photography Session ID", photoSessionID));
        strbuild.append(String.format("%-20s | %s%n", "Photography Session Name", photoSessionName));
        strbuild.append(String.format("%-20s | %s%n", "Photography Session Type", photoSessionType));
        strbuild.append(String.format("%-20s | %s%n", "Price", price));
        strbuild.append(String.format("%-20s | %s%n", "Number Of Product", numOfProduct));
        strbuild.append(String.format("%-20s | %s%n", "Product Type", productType));
        strbuild.append(String.format("%-20s | %s%n\n", "Editing Level", editingLevel));

        return strbuild.toString();
    }
}
