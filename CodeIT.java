import java.util.*;
import java.util.stream.Collectors;

public class CodeIT {
    public static void main(String[] args) {
//        long startTime = System.nanoTime();
//        List<String> test = new ArrayList<>();

//            test.add("00:00,A,5,27.5");
//            test.add("00:00,B,4,17.6");
//            test.add("00:10,C,6,6.6");
//            test.add("00:15,B,6,6.2");
//            test.add("00:18,A,6,23.6");
//            test.add("00:04,C,6,5.6");
//            test.add("00:23,B,6,22.6");
//            test.add("00:51,B,6,6.6");
//            test.add("01:12,A,5,5.2");
//            test.add("05:10,B,5,5.5");

//        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
//        List<String> partOne = to_cumulative(test);
//        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
//        System.out.println(partOne.toString());
//        long stopTime = System.nanoTime();
//        long actualMemUsed=afterUsedMem-beforeUsedMem;
//        System.out.println("Execution time in milliseconds: "+(stopTime - startTime)/1000000);
//        System.out.println("Total Memory Used: "+actualMemUsed);

        List<String> test2 = new ArrayList<>();
        test2.add("00:06,A,1,5.6");
        test2.add("00:05,A,1,5.6");
        test2.add("00:00,A,1,5.6");
        test2.add("00:02,A,1,5.6");
        test2.add("00:03,A,1,5.6");

        List<String> partTwo = to_cumulative_delayed(test2,5);
        System.out.println(partTwo.toString());
    }

    // Part One Solution
    private static List<String> to_cumulative(List<String> test) {

        List<String> temp = new ArrayList<>(); // This is the list that will be returned from this function
        Container container = new Container();
        Map<String,Record> cumulative_map = new HashMap<>();

        // Storing all the CSV information
        for(String s:test){
            container.insert(s);
        }

        // Iterating through each TimeSlot in Container
        for(String key:container.map.keySet()){
            // Add the current time to the list (to be returned)
            String helper="";
            helper+=key+",";
            TimeSlot currTimeSlot = container.map.get(key);
            // Iterating through each ticker that exists in the current TimeSlot
            for(String ticker:currTimeSlot.map.keySet()){
                helper+=ticker;
                Record currentRecord = currTimeSlot.map.get(ticker);
                int quantity  = currentRecord.getCumulative_quantity();
                double notional = currentRecord.getCumulative_notional();

                if(cumulative_map.containsKey(ticker)){
                    cumulative_map.get(ticker).insert_cumulative(notional,quantity);
                } else {
                    Record currRecord = new Record(notional/quantity,quantity);
                    cumulative_map.put(ticker,currRecord);
                }

                Record target = cumulative_map.get(ticker);
                String notionalStr = String.format("%.1f", target.getCumulative_notional());
                String quantityStr = String.valueOf(target.getCumulative_quantity());
                helper+=","+quantityStr;
                helper+=","+notionalStr+",";

            }
            temp.add(helper);
        }
        return temp;
    }

    // Part Two Solution
    private static List<String> to_cumulative_delayed(List<String> test, int i) {
        // Sorting given CSV
        Collections.sort(test);
        Map<String,Queue<EntryRecord>> map = new TreeMap<>();
        // Storing all the CSV information
        for(String entry:test){
            String[] parser=entry.split(",");
            String time=parser[0];
            String ticker=parser[1];
            int quantity=Integer.parseInt(parser[2]);
            double price=Double.parseDouble(parser[3]);

            // Creating new instance of an EntryRecord
            EntryRecord newInput=new EntryRecord(quantity,price,time);
            if(map.containsKey(ticker)){
                map.get(ticker).add(newInput);
            } else {
                Queue<EntryRecord> q = new LinkedList<>();
                q.add(newInput);
                map.put(ticker,q);
            }
        }

        // Producing output
        List<String> returnList= new ArrayList<>();
        boolean firstIteration = true;

        // Looping through each Queue
        for(String key:map.keySet()){
            String tempStr="";
            if(!firstIteration){
                tempStr+=",";
            }
            List<EntryRecord> temp = new ArrayList<>();
            Queue<EntryRecord> currentQ = map.get(key);
            firstIteration =false;

            // Creating a new instance of an ExitRecord
            ExitRecord curr = new ExitRecord(0,0);
            boolean result =false;
            EntryRecord currentRecord = currentQ.peek();
            EntryRecord prevRecord=currentQ.peek();
            // Adding EntryRecords into ExitRecords until required i  is met
            while(!currentQ.isEmpty() && !result ){
                prevRecord=currentRecord;
                currentRecord  = currentQ.poll();
                result = curr.addRecord(currentRecord.getQuantity(),currentRecord.getPrice(),i);
            }
            if(curr.getCumulativeQuantity()==i){
                // returnList.add(currentRecord.getTime());
                tempStr=prevRecord.getTime()+","+key+","+curr.returnInformation();
            }
            returnList.add(tempStr);
        }
        return returnList;
    }





}
