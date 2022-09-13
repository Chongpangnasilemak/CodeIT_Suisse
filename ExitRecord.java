public class ExitRecord {
    private int cumulativeQuantity;
    private double cumulativePrice;

    public ExitRecord(int cumulativeQuantity, double cumulativePrice) {
        this.cumulativeQuantity = cumulativeQuantity;
        this.cumulativePrice = cumulativePrice;
    }

    public String returnInformation(){
        String s = String.format("%.1f",this.cumulativePrice);
        return ""+ this.cumulativeQuantity+","+ s;
    }
    public boolean addRecord(int quantity, double price, int i){
        int requiredCount= i -this.cumulativeQuantity;
        if(requiredCount>=quantity){
            this.cumulativeQuantity+=quantity;
            this.cumulativePrice+=quantity*price;
        } else {
            this.cumulativeQuantity+=requiredCount;
            this.cumulativePrice+=requiredCount*price;
        }
        return (this.cumulativeQuantity==i);
    }
    public int getCumulativeQuantity() {
        return cumulativeQuantity;
    }
}
