public class Feedback {
    private String feedback; 
    private int point;

    public Feedback(String feedback, int point) {
        this.feedback = feedback;
        this.point = point;
    }

    public Feedback() {
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
    
    
} 
