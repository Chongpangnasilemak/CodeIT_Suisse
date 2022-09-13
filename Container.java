import java.util.Map;
import java.util.TreeMap;

public class Container {
    Map<String,TimeSlot> map;
    public Container() {
        this.map = new TreeMap<>();
    }
    public void insert(String input){
        String[] entries = input.split(",");
        String time = entries[0];
        String ticker = entries[1];
        int quantity = Integer.parseInt(entries[2]);
        double price = Double.parseDouble(entries[3]);
        if(this.map.containsKey(time)){
            map.get(time).addRecord(ticker,price,quantity);
        } else {
            TimeSlot temp = new TimeSlot();
            temp.addRecord(ticker,price,quantity);
            map.put(time,temp);
        }
    }
}
