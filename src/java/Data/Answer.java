/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author ajmiro
 */
public class Answer {
    private String applicantNumber, answerText;

    public Answer(String applicantNumber, String answerText) {
        this.applicantNumber = applicantNumber;
        this.answerText = answerText;
    }
        
    /**
     * @return the applicantNumber
     */
    public String getApplicantNumber() {
        return applicantNumber;
    }

    /**
     * @return the answerText
     */
    public String getAnswerText() {
        return answerText;
    }       
}
