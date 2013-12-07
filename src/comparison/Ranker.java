package comparison;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Ranker<K> {
    private Map<K, Double> map;

    private Double sum;

    public Ranker(){
        map = new HashMap<K, Double>();
        sum = 0.0;
    }

    public void zero (K key){
        if (map.containsKey(key)){
            sum -= map.get(key);
        }
        map.put(key, 0.0);
    }

    public Double increase(K key, Double increaseBy){
        if (map.containsKey(key)){
            map.put(key, map.get(key)+increaseBy);
        }
        else {
            map.put(key, increaseBy);
        }

        sum += increaseBy;
        return map.get(key);
    }

    public Double get(K key) {
        return map.get(key);
    }

    public K getMax(){
        K maxKey = null;
        Double maxValue = Double.valueOf(Double.MIN_VALUE);
        for (Map.Entry<K, Double> entry : map.entrySet()){
            //if this value is greater than the maxValue found so far
            if (entry.getValue().compareTo(maxValue)>0){
                maxValue = entry.getValue();
                maxKey = entry.getKey();
            }
        }
        return maxKey;
    }

    public K getMin(){
        K minKey = null;
        Double minValue = Double.valueOf(Double.MAX_VALUE);
        for (Map.Entry<K, Double> entry : map.entrySet()){
            //if this value is less than the minValue found so far
            if (entry.getValue().compareTo(minValue)<0){
                minValue = entry.getValue();
                minKey = entry.getKey();
            }
        }
        return minKey;
    }

    public double getProportion(K key){
        return map.get(key)/sum;
    }

    public K popMax(){
        K max = getMax();
        sum -= map.get(max);
        map.remove(max);
        return max;
    }

    public K popMin(){
        K min = getMin();
        sum -= map.get(min);
        map.remove(min);
        return min;
    }

    public Map<K, Double> getMap() {
        return map;
    }

    public Double getSum() {
        return sum;
    }
}