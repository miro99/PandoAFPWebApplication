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
    private String questionID;
    
    /**
     *
     * @param questionID
     */
    public Question() {
    }
    
    public void Initialize(String questionID){
        this.questionID = questionID;
        this.questionText = getQuestionText(questionID);
        this.reponses = getNumberOfResponses(questionID);
    }

    private String getQuestionText(String questionID) {
        
        if (questionID != null) {
            if (questionID.equals("Q1")) {
                return "Do you like your eggs sunny side up?";
            }
            
            if(questionID.equals("Q2")) {
                return "Which came first. The chicken or the egg?";
            }
            
            return "What's up doc?";
        }
        return "What did you think of this survey [DYNAMIC]";
    }

    /**
     * @return the questionText
     */
    public String getQuestionText() {
        return questionText;
    }

    private int getNumberOfResponses(String questionID) {
        if (questionID != null) {
            if (questionID.equals("Q1")) {
                return 500;
            }
            
            if(questionID.equals("Q2")) {
                return 250;
            }
            
            return 10;
        }
        return 20;
    }

    /**
     * @return the reponses
     */
    public int getReponses() {
        return reponses;
    }
    
    public List<Answer> getPageOfAnswers(int page){
        List<Answer> answers = new ArrayList<Answer>();
        
         if (questionID != null) {
            if ("q1".equals(questionID.toLowerCase())) {
                Answer answer1 = 
                        new Answer("694123", "This is a dynamic answer.");
                Answer answer2 = 
                        new Answer("567432", "Wow this place is great.");
                answers.add(answer1);
                answers.add(answer2);
            }
            
            if(questionID.toLowerCase().equals("q2")) {
                Answer answer1 =
                        new Answer("659843", "This is crazy.");
                Answer answer2 =
                        new Answer("984332", "Did not like it at all.");
                
                Answer answer3 =
                        new Answer("854697", "This is wonderful.");
                Answer answer4 =
                        new Answer("592301", "Love it.");
                
                answers.add(answer1);
                answers.add(answer2);
                answers.add(answer3);
                answers.add(answer4);
            }                        
        }        
       
        return answers;
    }
}
