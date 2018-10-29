package main.objects;

public class SubTopic  {

    private Topic parentTopicName;
    private String subTopicName;

    public SubTopic(Topic topic) {
        if (topic == null) {
            // throw exception
        }
        this.parentTopicName = topic;
    }

    public String getSubTopicName() {
        return subTopicName;
    }

    public void setSubTopicName(String subTopicName) {
        this.subTopicName = subTopicName;
    }
}

