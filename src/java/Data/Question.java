/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.ArrayList;
import java.util.List;
import Data.Database.DataStore;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

/**
 *
 * @author ajmiro
 */
public class Question {

    private String questionText;
    private int reponses;
    private String questionID;
    private DataStore dataStore;
    private Company company;
    private Question[] allQuestions;
    
    /**
     *
     * @param questionID
     */
    public Question() {                
    }
    
    public void Initialize(String questionID, Company company){
        StringBuilder sqlStatement = new StringBuilder("Select Q1,")
                .append(questionID).append(" From survey_data WHERE Q10 = ")
                .append("'").append(company.getCompanyName()).append("'");
       
        StringBuilder sqlCountStatement = new StringBuilder("Select Count(")
                .append(questionID).append(") From survey_data WHERE Q10 = ")
                .append("'").append(company.getCompanyName()).append("'");

        this.dataStore = new DataStore(sqlStatement.toString(), 
                sqlCountStatement.toString());
        this.questionID = questionID;
        this.questionText = getQuestionText(questionID);        
        this.reponses = getNumberOfResponses();
        this.company = company;
    }
    
    public Question[] getAllQuestions(){
        ArrayList<Question> questions = new ArrayList<Question>();
        String sqlString = "SELECT * FROM column_key";
        String sqlCountString = "SELECT COUNT('KEY') FROM column_key";
        DataStore ds = new DataStore(sqlString, sqlCountString);
        int totalPages = ds.GetTotalPages();
        for (int i = 1; i <= totalPages; i++) {
            try {
                ResultSet result = ds.GetPage(i);
                while (result.next()) {
                    String questionID = result.getString("key");
                    String question = result.getString("question");
                    Question q = new Question();
                    q.questionID = questionID;
                    q.questionText = question;
                    questions.add(q);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Question[] allQuestions = new Question[questions.size()];
        questions.toArray(allQuestions);
        return allQuestions;
    }

    private String getQuestionText(String questionID) {       
        String question = dataStore.GetColumnNameByID(questionID);
        return question;        
    }

    /**
     * @return the questionText
     */
    public String getQuestionText() {
        return questionText;
    }

//    private int getNumberOfResponses(String questionID) {
    private int getNumberOfResponses() {        
        return dataStore.GetNumberOfResults();        
    }

    /**
     * @return the reponses
     */
    public int getResponses() {
        return reponses;
    }
    
    public List<Answer> getPageOfAnswers(int pageNumber){
        List<Answer> answers = new ArrayList<Answer>();
        try {                        
            ResultSet page = dataStore.GetPage(pageNumber);
            while (page.next()) {
                Answer ans = new Answer(page.getString(1), page.getString(2));
                answers.add(ans);
            }                                           
        } catch (SQLException ex) {
            Logger
                .getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answers;
    }   
    
    public int getTotalPages(){
        return dataStore.GetTotalPages();
    }
    
    public void Close()
    {
        this.dataStore.Close();
    }        

    /**
     * @return the questionID
     */
    public String getQuestionID() {
        return questionID;
    }
}
