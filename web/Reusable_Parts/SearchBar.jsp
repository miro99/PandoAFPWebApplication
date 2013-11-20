<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html>
    <head>
        <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <form action="Search" method="POST">
            <section id="keyword">     
                <div id="surveyQuestion">
                    <!--<h1>Q: What did you think about this survey?</h1>-->
                    <div id="surveyTable">
                        <div id="surveyQuestionTitle">
                            Question: 
                        </div>
                        <div id="surveyQuestionSelector">
                            <select>
                                <option>${question.questionText}</option>                                    
                            </select>
                        </div>
                    </div>
                </div>
                <div id="text">Search:</div>
                
                <div id="txtInput">
                    <input id="keywordInput" type="text" name="txtkeyword" value="" />
                </div>
                <div id="buttondiv">
                    <input type="submit" value="GO"/>
                </div>
            </section>
        </form>            
    </body>
</html>
