class Product {
    private String name;
    private String type;
    private String category;
    private String place;
    private int warranty;

    public Product()
    {

    }

    public Product(String name, String type, String category,String place, int warranty)
    {
        this.name=name;
        this.type=type;
        this.category=category;
        this.place=place;
        this.warranty=warranty;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name=name;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type=type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place=place;
    }

    public int getWarranty() {
        return warranty;
    }
    public void setWarranty(int warranty) {
        this.warranty=warranty;
    }

    @Override
    public String toString() {
        return "Products {" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", place='" + place + '\'' +
                ", warranty=" + warranty +
                '}';

    }





}
