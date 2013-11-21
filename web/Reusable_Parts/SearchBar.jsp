<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html>
    <head>
        <!-- jQuery library (served from Google) -->
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>       
        <script>
             var companyID = '${company.companyName}';
             var questionID = '${param.QuestionID}';
            function loadQuestion()
            {                                 
                  //TEST
                  document.getElementById("questionList").style.visibility ="visible";
                  document.getElementById("mainSection").style.opacity =".5";
            }
               
               function selectQuestion(objButton)
               {
                   var str = objButton.value;
                   var res = str.split("|")
                   document.getElementById("surveyQuestionSelector").innerHTML = res[1];
                   document.getElementById("questionList").style.visibility = "hidden";
                   document.getElementById("mainSection").style.opacity ="1";
                   
                    var xmlhttp;
                    if (window.XMLHttpRequest)
                      {// code for IE7+, Firefox, Chrome, Opera, Safari
                        xmlhttp=new XMLHttpRequest();
                      }
                    else
                      {// code varfor IE6, IE5
                        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
                      }
                      xmlhttp.onreadystatechange = function()
                                    {
                                        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
                                        {
                                            //document.getElementById("surveyQuestionSelector").innerHTML = xmlhttp.responseText;
                                        }
                                    }

                      xmlhttp.open("GET", "getQuestion?Company="+companyID+"&QuestionID="+questionID, true);
                      xmlhttp.send();

                   //loadQuestion();
               }
        </script>
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
                            <div id="buttondiv">                    
                                <button type="button" onclick="loadQuestion()">?</button>
                            </div>
                        </div>
                        <div id="surveyQuestionSelector">                           
                        </div>                        
                    </div>
                </div>               
            </section>
        </form>            
    </body>
</html>
