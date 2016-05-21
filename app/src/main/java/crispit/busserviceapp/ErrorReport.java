package crispit.busserviceapp;

/**
 * Created by Mikael on 2016-04-20.
 */
public class ErrorReport {
    private String id;
    private String symptom;
    private String comment;
    private String pubdate;
    private int grade;
    private String busid;
    private String status;

    public ErrorReport(String id, String symptom, String comment, String busid, String pubdate, int grade, String status) {

        this.id = id;
        this.symptom = symptom;
        this.comment = comment;
        this.busid = busid;
        this.pubdate = pubdate;
        this.grade = grade;
        this.status = status;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setBusid(String id) {
        this.busid = id;
    }

    public String getBusid() {
        return busid;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
