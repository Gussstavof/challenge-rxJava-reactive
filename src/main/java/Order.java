public class Order {
    private Integer id;
    private String custer;
    private Double price;

    public Order(Integer id, String custer, Double price) {
        this.id = id;
        this.custer = custer;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCuster() {
        return custer;
    }

    public void setCuster(String custer) {
        this.custer = custer;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", custer='" + custer + '\'' +
                ", price=" + price +
                '}';
    }
}
