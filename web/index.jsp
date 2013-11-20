<%-- 
    Document   : index
    Created on : Sep 20, 2013, 2:18:09 PM
    Author     : ajmiro
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Data.Answer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="pandoAFPSurveyCSS.css"/>
        <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body id="pageBody">
        
       

        <jsp:useBean id="company" scope="session" class="Data.Company"/>
        <% company.InitCompany(request.getParameter("Company"));%>
       
        <jsp:useBean id="question" scope="session" class="Data.Question" />
        <% question.Initialize(request.getParameter("QuestionID"), company);%>
                
        <section id="wholeDocument">
            <section id="header">                
                <jsp:include page="Reusable_Parts/SiteHeader.jsp">
                    <jsp:param name="pageTitle" value="Survey Response Analysis" />
                </jsp:include>                               
            </section>
            <div id="headerBottom">
                <jsp:include page="Reusable_Parts/SearchBar.jsp"/>
            </div>

            <section id="mainSection">
                <section id="mainSectionTable">
                    <div id="filterColumn">
                        <h1 id="responseFilterText">Response Filters</h1>
                        
                        <ul>
                            <li class="item">
                                <div class="filterControl">                                    
                                    <div class="filterTable">
                                        <div class="filterName">Age:</div>                                        
                                    </div>
                                    <input class="txtFilter" type="text" 
                                           name="filterText" value="" />
                                </div>
                            </li>
                            
                            <li class="item">
                                <div class="filterControl">
                                    <div class="filterTable">
                                        <div class="filterName">Sex:</div>                                        
                                    </div>
                                    <input class="txtFilter" type="text" 
                                           name="filterText" value="" />
                                </div>
                            </li>
                            
                            <li class="item">
                                <div class="filterControl">
                                    <div class="filterTable">
                                        <div class="filterName">Weight:</div>
                                    </div>
                                    <input class="txtFilter" type="text" 
                                           name="filterText" value="" />
                                </div>
                            </li>                            
                        </ul>
                    </div>

                    <div id="detailArea">
                        <!-- <div id="detailAreaHeader">
                            <div id="surveyQuestion">                                
                                <select>
                                    <option><%= question.getQuestionText()%></option>
                                </select>
                            </div>
                            <div id="numberOfResponses">
                                <h1><%= question.getResponses()%> responses</h1>
                            </div>
                        </div> -->
                        
                        <div id="detailAreaData"> 
                            
                    
                
                <% 
                    List<Answer> answers = question.getPageOfAnswers(Integer.parseInt(request.getParameter("Page")));                    
                    Iterator it = answers.iterator();
                    while(it.hasNext()){    
                       Answer answer = (Answer) it.next();                                        
                %>
            
                            
                            <div class="dataItem">
                                <div class="applicantInfo">
                                    <div class="applicant">Applicant</div>
                                    <div class="applicantID">
                                        <!--#6942-->
                                        #<%= answer.getApplicantNumber()%>
                                        <button class="viewSurvey">View Full Survey</button>
                                    </div></br>
                                </div>
                                                                
                                <div class="answer">
                                    <!--"This is the best question I have ever seen..."-->
                                    <%=answer.getAnswerText()%>
                                </div>
                            </div>
                <%}%>
                            
                           <!-- <div class="dataItem">
                                <div class="applicantInfo">
                                    <div class="applicant">Applicant</div>
                                    <div class="applicantID">#6943
                                        <button class="viewSurvey">
                                            View Full Survey
                                        </button>
                                    </div></br>                                    
                                </div>
                                <div class="answer">
                                    "This survey made me laugh my a$$ off..."
                                </div>
                            </div> -->
                            
                            <div id="pageSelectionTool">                                
                                <a id="pageLeft">&lt;</a>
                                <% for(int i=1; i <= question.getTotalPages(); i++){                                                                                                                                       
                                %>
                                <a class="pageSelection" href="http://localhost:8080/PandoAFPWebApplication/index.jsp?Company=<%=request.getParameter("Company")%>&QuestionID=<%=request.getParameter("QuestionID")%>&Page=<%=i%>"><%=i%></a>
                                <%
                                }
                                %>
                                <!--<a class="pageSelection">1</a>
                                <a class="pageSelection">2</a>
                                <a class="pageSelection">3</a>
                                <a class="pageSelection">4</a> -->
                                <a id="pageRight">&gt;</a>                                
                            </div>
                        </div>
                    </div>                                        
                </section>
            </section>
        </section>                                   
    </body>
    <% question.Close();%>
</html>
