<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html style="background-color: rgba(9,12,13,0);">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>TrackIT</title>
    <link rel="shortcut icon" href="../../assets/img/favicon.png">
    <link rel="stylesheet" href="../../assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic">
    <link rel="stylesheet" href="../../assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="../../assets/fonts/ionicons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
    <link rel="stylesheet" href="../../assets/css/styles.min.css">
</head>

<body style="font-family: 'Roboto', sans-serif;background-color: #444349;background-image: url(../../assets/img/pattern.png);background-size: cover;background-repeat: no-repeat;height: 100%;">
<header class="d-lg-flex masthead text-center text-white d-flex"
        style="font-family: 'Roboto', sans-serif;margin-top: -43%;">
    <nav class="navbar navbar-light navbar-expand-lg fixed-top bg-dark" id="mainNav"
         style="-webkit-box-shadow: 0px 3px 20px 0px rgba(0,0,0,0.75);-moz-box-shadow: 0px 3px 20px 0px rgba(0,0,0,0.75);box-shadow: 0px 3px 20px 0px rgba(0,0,0,0.75);background-color: #252529;font-family: Roboto, sans-serif;">
        <div class="container"><a class="navbar-brand js-scroll-trigger" href="index.html">Trackit</a>
            <button class="navbar-toggler navbar-toggler-right" data-toggle="collapse" data-target="#navbarResponsive"
                    type="button" aria-controls="navbarResponsive" aria-expanded="false"
                    aria-label="Toggle navigation"><i class="fa fa-align-justify"
                                                      style="color: rgba(255,255,255,0.9);"></i></button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="nav navbar-nav ml-auto">
                    <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="/registration"
                                                                id="registration">logout</a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="/payment"
                                                                id="payment">pay</a></li>
                </ul>
            </div>
        </div>
    </nav>
</header>
<div class="container">
    <div class="row">
        <div class="col-lg-10 mx-auto">
            <h3 class="text-uppercase"><strong class="d-lg-flex justify-content-lg-center"
                                               style="color: rgb(255,255,255);"><c:out value="${error_code}"/>
                code</strong></h3>
            <hr>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <div class="jumbotron" style="background-color: rgba(233,236,239,0);">
                <div class="col-auto col-lg-7 text-dark mx-auto"
                     style="-webkit-box-shadow: 0px 0px 25px 0px rgba(0,0,0,0.75);-moz-box-shadow: 0px 0px 25px 0px rgba(0,0,0,0.75);box-shadow: 0px 0px 25px 0px rgba(0,0,0,0.75);background-color: rgba(15,15,15,0.8);padding-top: 20px;padding-bottom: 20px;">
                    <div class="row">
                        <div class="col text-center" style="color: rgb(255,255,255);margin-bottom: -15px;">
                            <label class="col-form-label">Hmm...<br/>
                                Looks like a error.</label>
                            <img class="img-fluid" src="../../assets/img/shinobu.png" width="450px">
                            <div class="row">
                                <div class="col"><label class="col-form-label"><c:out value="${error_msg}"/></label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br>
                </div>
                <p></p>
            </div>
        </div>
    </div>
</div>
<div class="text-left d-flex d-lg-flex justify-content-lg-center footer-dark"
     style="-webkit-box-shadow: 0px -3px 20px 0px rgba(0,0,0,0.75);-moz-box-shadow: 0px -3px 20px 0px rgba(0,0,0,0.75);box-shadow: 0px -3px 20px 0px rgba(0,0,0,0.75);font-family: 'Open Sans', sans-serif;background-color: rgb(37,37,41);">
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-sm-6 col-md-3 item">
                    <h3>Info</h3>
                    <ul>
                        <li><a>Web design  - Vex788</a></li>
                        <li><a>Development - Vex788</a></li>
                        <li><a href="https://www.heroku.com/">Hosting     - Heroku</a></li>
                    </ul>
                </div>
                <div class="col-sm-6 col-md-3 item">

                </div>
                <div class="col-md-6 item text">
                    <h3>Zeus Corp</h3>
                    <p>Quod si hoc dicere ad turbas capitque volutpat erras.</p>
                </div>
                <div class="col item social"><a href="#"><i class="icon ion-social-facebook"></i></a><a href="#"><i class="icon ion-social-twitter"></i></a><a href="#"><i class="icon ion-social-google"></i></a><a href="#"><i class="icon ion-social-instagram"></i></a></div>
            </div>
            <p class="copyright">TrackIT 2019</p>
        </div>
    </footer>
</div>
<script src="../../assets/js/jquery.min.js"></script>
<script src="../../assets/bootstrap/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
<script src="../../assets/js/script.min.js"></script>
<c:if test="${error_code == 401}">
    <script src="../../assets/js/error.js"></script>
</c:if>
</body>

</html>