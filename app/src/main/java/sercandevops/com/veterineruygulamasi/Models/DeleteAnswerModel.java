package sercandevops.com.veterineruygulamasi.Models;

public class DeleteAnswerModel {

    private String text;
    private boolean tf;


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

    @Override
    public String toString() {
        return "DeleteAnswerModel{" +
                "text='" + text + '\'' +
                ", tf=" + tf +
                '}';
    }
}
