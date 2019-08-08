<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

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

<body style="background-color: #444349;background-image: url(../../assets/img/pattern.png);background-repeat: no-repeat;background-size: cover;">
    <header class="masthead text-center text-white d-flex" style="font-family: 'Open Sans', sans-serif;background-color: rgba(255,255,255,0);">
        <nav class="navbar navbar-light navbar-expand-lg fixed-top bg-dark" id="mainNav" style="-webkit-box-shadow: 0px 3px 20px 0px rgba(0,0,0,0.75);-moz-box-shadow: 0px 3px 20px 0px rgba(0,0,0,0.75);box-shadow: 0px 3px 20px 0px rgba(0,0,0,0.75);font-family: Roboto, sans-serif;">
            <div class="container"><a class="navbar-brand js-scroll-trigger" href="index.html">Trackit</a><button class="navbar-toggler navbar-toggler-right" data-toggle="collapse" data-target="#navbarResponsive" type="button" aria-controls="navbarResponsive" aria-expanded="false"
                    aria-label="Toggle navigation"><i class="fa fa-align-justify" style="color: rgba(255,255,255,0.9);"></i></button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="nav navbar-nav ml-auto">
                        <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="/login" id="login">login</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container my-auto">
            <div class="row" style="margin-top: 10%;">
                <div class="col-lg-10 mx-auto">
                    <h3 class="text-uppercase"><strong style="font-family: Roboto, sans-serif;">registration</strong></h3>
                    <hr>
                </div>
            </div>
            <div class="col-auto col-lg-8 text-dark mx-auto" style="-webkit-box-shadow: 0px 0px 25px 0px rgba(0,0,0,0.75);-moz-box-shadow: 0px 0px 25px 0px rgba(0,0,0,0.75);box-shadow: 0px 0px 25px 0px rgba(0,0,0,0.75);background-color: rgba(15,15,15,0.8);padding: 15px;padding-top: 20px;padding-bottom: 20px;width: 385px;">
                <form action="/registration/new_user" method="POST" id="addNewUserForm">
                    <div class="col-lg-12 offset-lg-0 d-inline-flex d-lg-flex justify-content-lg-center" style="font-family: Roboto, sans-serif;">
                        <h3 class="text-danger" style="color: rgb(220,53,69);font-family: 'Open Sans', sans-serif;">&nbsp;</h3>
                    </div><input class="bg-dark border-dark shadow form-control" type="text" id="nickname" placeholder="Nickname" style="margin-top: 10px;margin-bottom: 5px;color: rgb(255,255,255);font-family: Roboto, sans-serif;">
                    <input class="bg-dark border-dark shadow form-control"
                        type="email" id="email" placeholder="Email" inputmode="email" style="font-family: Roboto, sans-serif;color: rgb(255,255,255);margin-bottom: 5px;margin-top: 0px;">
                    <input class="bg-dark border-dark shadow form-control"
                           type="tel" pattern="[0-9]{12}" title="Phone format 380123456789" id="number" placeholder="38 012 345 67 89" style="font-family: Roboto, sans-serif;color: rgb(255,255,255);margin-bottom: 5px;margin-top: 0px;">
                    <div class="form-row">
                        <div class="col-lg-12 offset-lg-0 d-inline-flex d-lg-flex justify-content-lg-center"><input class="bg-dark border-dark shadow form-control" inputmode="password" type="password" id="password" placeholder="Password" style="color: rgb(255,255,255);margin-bottom: 5px;font-family: Roboto, sans-serif;"></div>
                        <div class="col-lg-12 offset-lg-0 d-inline-flex d-lg-flex justify-content-lg-center"><input class="bg-dark border-dark shadow form-control" inputmode="password" type="password" id="password2" placeholder="Repeat password" style="color: rgb(255,255,255);font-family: Roboto, sans-serif;"></div>
                    </div>
                <div class="row">
                    <div class="col" style="margin: 5px;font-family: Roboto, sans-serif;"><br></div>
                </div>
                <div class="row">
                    <div class="col"><button class="btn btn-success btn-lg bg-success border rounded border-success shadow" type="button" id="submit_reg" style="font-family: Roboto, sans-serif;font-style: normal;font-weight: normal;width: 40%;color: rgb(0,0,0);">Register</button></div>
                </div>
                </form>
            </div>
        </div>
    </header>
    <div class="footer-dark" style="-webkit-box-shadow: 0px -3px 20px 0px rgba(0,0,0,0.75);-moz-box-shadow: 0px -3px 20px 0px rgba(0,0,0,0.75);box-shadow: 0px -3px 20px 0px rgba(0,0,0,0.75);font-family: 'Open Sans', sans-serif;background-color: rgb(37,37,41);">
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
    <script src="../../https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
    <script src="../../assets/js/script.min.js"></script>
    <script src="../../assets/js/registration.js" charset="UTF-8"></script>
    <script src="../../assets/js/jalert_gen.js" charset="UTF-8"></script>
</body>

</html>