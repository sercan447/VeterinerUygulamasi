package sercandevops.com.veterineruygulamasi.Models;

public class AskQuestionModel{

    private String text;
    private boolean tf ;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isTf() {
        return tf;
    }

    public void setTf(boolean tf) {
        this.tf = tf;
    }
}
