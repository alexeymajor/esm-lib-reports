package ru.avm.reports;

import org.hibernate.transform.BasicTransformerAdapter;

import java.sql.Timestamp;

public class ReportResultTransformer extends BasicTransformerAdapter {

    public static final ReportResultTransformer INSTANCE = new ReportResultTransformer();

    private ReportResultTransformer() {
    }

    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        for (int i = 0; i < tuple.length; i++) {
            if (tuple[i] instanceof Timestamp) {
                tuple[i] = ((Timestamp) tuple[i]).toLocalDateTime();
            }
        }
        return tuple;
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
