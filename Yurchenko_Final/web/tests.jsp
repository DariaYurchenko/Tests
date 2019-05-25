<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Tests</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</head>
<body>
<div class="row" style="margin-left: 15px">
    <header class="header col-lg-12">
        <nav class="navbar navbar-dark bg-dark navbar-expand-md col-lg-12">
            <a class="navbar-brand" href="#">JavaFox</a>
            <div class="signUp"><button onclick="window.location = '#'" class="btn btn-primary">Sign Up</button></div>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse " id="navbarSupportedContent">
                <ul class="navbar-nav mr-4">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Log In</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link " href="#">Tests</a>
                    </li>
                </ul>

            </div>
            <div class="languages">
                <form action="tests" method="GET" name="lang-form">
                    <input type="hidden" value="CHANGE_LANGUAGE" name="command">
                    <input type="hidden" value="register_page.jsp" name="address">
                    <button type="submit" id="lang1" class="form-control" name="lang" value="eng"><img src="images/en-flag.jpg"></button>
                    <button type="submit" id="lang2" class="form-control" name="lang" value="rus"><img src="images/rus-flag.jpg"></button>
                    <script>
                        $('form[name="lang-form"] > input[name="address"]').attr('value', window.location);
                    </script>
                </form>
            </div>
        </nav>
    </header>

    <div><h1 align="center"><c:out value="${question.theme.themeName}"/></h1>

    <c:if test="${question.questionType.type eq 'RADIO'}">
    <c:out value="${question.question}"/>
        <p>Answers</p>
        <p><c:out value="${question.incorrectOption1}"/></p>
        <p><c:out value="${question.incorrectOption2}"/></p>
        <p><c:out value="${question.incorrectOption3}"/></p>
        <p><c:out value="${question.correctOption1}"/></p>
    </c:if>

        <c:if test="${counter < length}">
        <button onclick="window.location = '<c:url value="/tests?command=START_TEST&counter=${counter}&theme_id=1"/>'">Forward</button>
        </c:if>
        <c:if test="${counter >= length}">
            <button onclick="window.location = '<c:url value="/tests?command=SHOW_RESULTS"/>'">Forward</button>
        </c:if>

    </div>
    <div class="container d-flex justify-content-center">
        <div class="col-lg-8 col-md-8">
            <section class="work-section">
                <div class="progress">
                    <div class="progress-bar" role="progressbar" style="width: 75%" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
                </div>
                <div>
                    <div>What will be printed as a result of the following code execution?</div>
                    <pre>
public class Main {
 public static void main(String args[]) {
  byte b = 0;
  while (++b > 0);
  System.out.println(b);
 }
}
        </pre>
                    <div class="answers">
                        <form>
                            <div class="custom-control custom-radio">
                                <input type="radio" class="custom-control-input" id="customControlValidation1" name="radio-stacked" required>
                                <label class="custom-control-label" for="customControlValidation1">Toggle this custom radio</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" class="custom-control-input" id="customControlValidation2" name="radio-stacked" required>
                                <label class="custom-control-label" for="customControlValidation2">Or toggle this other custom radio</label>
                                <div class="invalid-feedback">More example invalid feedback text</div>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" class="custom-control-input" id="customControlValidation3" name="radio-stacked" required>
                                <label class="custom-control-label" for="customControlValidation3">Or toggle this other custom radio</label>
                                <div class="invalid-feedback">More example invalid feedback text</div>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" class="custom-control-input" id="customControlValidation4" name="radio-stacked" required>
                                <label class="custom-control-label" for="customControlValidation4">Or toggle this other custom radio</label>
                                <div class="invalid-feedback">More example invalid feedback text</div>
                            </div>
                            <div class="button-div text-center"><button class="btn" type="submit">Go on!</button></div>
                        </form>
                    </div>
                </div>
            </section>
        </div>
        <aside class="col-lg-4 col-md-4">
            <section>
                <h2 class="text-center">If else, switch and loops</h2>
                <div class="course-img-container"><img src="images/if-else.svg"></div>
            </section>
            <section class="stats">
                <h2 class="text-center">Your stats
                </h2>
                <div id="chartContainer" style="height: 240px; width: 100%;"></div>
                <script>
                    window.onload = function () {

                        var chart = new CanvasJS.Chart("chartContainer", {
                            animationEnabled: true,
                            data: [{
                                type: "doughnut",
                                startAngle: 60,
                                //innerRadius: 60,
                                indexLabelFontSize: 15,
                                indexLabel: "{label} - #percent%",
                                toolTipContent: "<b>{label}:</b> {y} (#percent%)",
                                dataPoints: [
                                    { y: 67, label: "Right" },
                                    { y: 208, label: "Wrong" }
                                ]
                            }]
                        });
                        chart.render();

                    }
                </script>
            </section>
        </aside>
    </div>

</div>
</body>
</html>
