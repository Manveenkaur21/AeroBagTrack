package service;

import model.Baggage;
import java.util.Iterator;
import java.util.List;

public class BaggageIterator {
    public static void display(List<Baggage> list) {
        Iterator<Baggage> iterator = list.iterator();
        while (iterator.hasNext()) System.out.println(iterator.next());
    }

    public static int count(List<Baggage> list) {
        int count = 0;
        for (Baggage ignored : list) count++;
        return count;
    }
}
