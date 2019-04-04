package com.odyssey.dataAnalytics;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

class DataQueries {
    private final String topicCountQuery = "select topic_name, COUNT(*) FROM odyssey group by topic_name";

    public String getTopicCountQuery() {
        return topicCountQuery;
    }
}

