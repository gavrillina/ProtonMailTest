package buissnes_object;

public class Mail {

    private String senderName;
    private String topic;
    private String contain;

    public Mail(String senderName, String topic, String contain) {
        this.senderName = senderName;
        this.topic = topic;
        this.contain = contain;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setContain(String contain) {
        this.contain = contain;
    }

    public String getSenderName() {

        return senderName;
    }

    public String getTopic() {
        return topic;
    }

    public String getContain() {
        return contain;
    }
}
