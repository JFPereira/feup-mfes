package mastermind;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Utilities {
    public Utilities() {
    }

    public static Number Count(final VDMSeq key, final Number elem) {
        Number count = 0L;

        for (Iterator iterator_6 = key.iterator(); iterator_6.hasNext();) {
            Number e = (Number) iterator_6.next();

            if (Utils.equals(e, elem)) {
                count = count.longValue() + 1L;
            }
        }

        return count;
    }

    public static Number Min(final Number a, final Number b) {
        if (a.longValue() < b.longValue()) {
            return a;
        }

        return b;
    }

    public String toString() {
        return "Utilities{}";
    }
}