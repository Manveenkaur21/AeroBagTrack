package service;

import model.Baggage;
import java.util.Comparator;

public class BaggageComparator {
    public static final Comparator<Baggage> BY_ID =
            Comparator.comparingInt(Baggage::getBaggageId);
    public static final Comparator<Baggage> BY_COLOR =
            Comparator.comparing(Baggage::getColor, String.CASE_INSENSITIVE_ORDER);
    public static final Comparator<Baggage> BY_STATUS =
            Comparator.comparing(Baggage::getCurrentStatus, String.CASE_INSENSITIVE_ORDER);
}
