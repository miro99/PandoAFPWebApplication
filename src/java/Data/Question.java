/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ajmiro
 */
public class Question {

    private String questionText;
    private int reponses;
    
    /**
     *
     * @param questionID
     */
    public Question() {
    }
    
    public void Initialize(String questionID){
        this.questionText = getQuestionText(questionID);
        this.reponses = getNumberOfResponses(questionID);
    }

    private String getQuestionText(String questionID) {
        return "What did you think of this survey [DYNAMIC]";
    }

    /**
     * @return the questionText
     */
    public String getQuestionText() {
        return questionText;
    }

    private int getNumberOfResponses(String questionID) {
        return 20;
    }

    /**
     * @return the reponses
     */
    public int getReponses() {
        return reponses;
    }
    
    public List<Answer> getPageOfAnswers(int page){
        Answer answer1 = new Answer("694123", "This is a dynamic answer.");
        Answer answer2 = new Answer("567432", "Wow this place is great.");
        
        List<Answer> answers = new ArrayList<Answer>();
        answers.add(answer1);
        answers.add(answer2);
        return answers;
    }
}
