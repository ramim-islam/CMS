package view;


// Encapsulation 


public class Order {
    private Integer id;
    private String name;
    private String contact_no;
    private String Address;
    private String order_date;
    private String release_date;
    public Order(Integer id,String name,String contact_no,String Address, String order_date,String release_date){
        this.id           = id;
        this.name         = name;
        this.contact_no   = contact_no;
        this.Address      = Address;
        this.order_date   = order_date;
        this.release_date = release_date;
        System.out.println("id           : " + this.id);
        System.out.println("name         : " + this.name);
        System.out.println("contact      : " + this.contact_no);
        System.out.println("address      : " + this.Address);
        System.out.println("order date   : " + this.order_date);
        System.out.println("release date : " + this.release_date);
    }
    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getContact_no() {
        return this.contact_no;
    }
    public String getAddress() {
        return this.Address;
    }
    public String getOrder_date() {
        return this.order_date;
    }
    public String getRelease_date() {
        return this.release_date;
    }
}
