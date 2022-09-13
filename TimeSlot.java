import java.util.Map;
import java.util.TreeMap;

public class TimeSlot {
    Map<String,Record> map;
    public TimeSlot() {
        this.map = new TreeMap<>();
    }
    public void addRecord(String ticker, double price, int quantity){
        if(this.map.containsKey(ticker)){
            this.map.get(ticker).insert(price, quantity);
        } else {
            this.map.put(ticker,new Record(price,quantity));
        }
    }
    public Map<String,Record> getMap(){
        Map<String, Record> temp = new TreeMap<>(this.map);
        return temp;
    }
}
