package main.objects;

public class SubTopic extends Topic {

    private String subTopicName;

    public SubTopic(String subTopicName) {
        this.subTopicName = subTopicName;
    }

    public String getSubTopicName() {
        return subTopicName;
    }

    public void setSubTopicName(String subTopicName) {
        this.subTopicName = subTopicName;
    }
}

