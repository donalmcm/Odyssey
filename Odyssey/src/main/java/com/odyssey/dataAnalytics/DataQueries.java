package com.odyssey.dataAnalytics;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

public class DataQueries {
    private String topic;
    private int occurrences;

    public DataQueries(String topic, int occurrences) {
        this.topic = topic;
        this.occurrences = occurrences;
    }
}

