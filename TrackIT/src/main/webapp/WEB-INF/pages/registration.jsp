<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>TrackIT</title>
    <link rel="stylesheet" href="../../assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic">
    <link rel="stylesheet" href="../../assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="../../assets/fonts/ionicons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.min.css">
    <link rel="stylesheet" href="../../assets/css/styles.min.css">
</head>

<body>
    <header class="masthead text-center text-white d-flex" style="background-image: url(../../assets/img/header-3.jpg);font-family: 'Open Sans', sans-serif;">
        <nav class="navbar navbar-light navbar-expand-lg fixed-top bg-dark" id="mainNav">
            <div class="container"><a class="navbar-brand js-scroll-trigger" href="index.html">Trackit</a><button class="navbar-toggler navbar-toggler-right" data-toggle="collapse" data-target="#navbarResponsive" type="button" aria-controls="navbarResponsive" aria-expanded="false"
                    aria-label="Toggle navigation"><i class="fa fa-align-justify" style="color: rgba(255,255,255,0.9);"></i></button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="nav navbar-nav ml-auto">
                        <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="#about" id="about">About</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="/login" id="login">login</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="#contact" id="contact">Contact</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container my-auto">
            <div class="row" style="margin-top: 10%;">
                <div class="col-lg-10 mx-auto">
                    <h3 class="text-uppercase"><strong>registration</strong></h3>
                    <hr>
                </div>
            </div>
            <div class="col-auto col-lg-8 text-dark mx-auto" style="background-color: rgba(15,15,15,0.8);padding: 15px;padding-top: 20px;padding-bottom: 20px;width: 385px;">
                <form action="/registration" method="POST">
                    <div class="col-lg-12 offset-lg-0 d-inline-flex d-lg-flex justify-content-lg-center">
                        <h3 class="text-danger" style="color: rgb(220,53,69);font-family: 'Open Sans', sans-serif;"><c:out value="${error}"/></h3>
                    </div><input class="bg-dark border-dark shadow form-control" type="text" name="nickname" placeholder="Nickname" style="margin-top: 10px;margin-bottom: 5px;"><input class="bg-dark border-dark shadow form-control" type="email" name="email"
                        placeholder="Email" inputmode="email" style="font-family: 'Open Sans', sans-serif;color: rgb(255,255,255);margin-bottom: 5px;margin-top: 0px;">
                    <div class="form-row">
                        <div class="col-lg-12 offset-lg-0 d-inline-flex d-lg-flex justify-content-lg-center"><input class="bg-dark border-dark shadow form-control" type="password" name="password" placeholder="Password" style="color: rgb(255,255,255);margin-bottom: 5px;"></div>
                        <div class="col-lg-12 offset-lg-0 d-inline-flex d-lg-flex justify-content-lg-center"><input class="bg-dark border-dark shadow form-control" type="password" name="repeat_password" placeholder="Repeat password" style="color: rgb(255,255,255);"></div>
                    </div>
                </form>
                <div class="row">
                    <div class="col" style="margin: 5px;"><br></div>
                </div>
                <div class="row">
                    <div class="col"><button class="btn btn-success btn-lg bg-success border rounded border-success shadow" type="button" id="report_a_drop" style="font-family: 'Open Sans', sans-serif;font-style: normal;font-weight: normal;width: 40%;color: rgb(0,0,0);">Register</button></div>
                </div>
            </div>
        </div>
    </header>
    <div class="footer-dark" style="font-family: 'Open Sans', sans-serif;background-color: rgb(37,37,41);">
        <footer>
            <div class="container">
                <div class="row">
                    <div class="col-sm-6 col-md-3 item">
                        <h3>Services</h3>
                        <ul>
                            <li><a href="#">Web design</a></li>
                            <li><a href="#">Development</a></li>
                            <li><a href="#">Hosting</a></li>
                        </ul>
                    </div>
                    <div class="col-sm-6 col-md-3 item">
                        <h3>About</h3>
                        <ul>
                            <li><a href="#">Company</a></li>
                            <li><a href="#">Team</a></li>
                            <li><a href="#">Careers</a></li>
                        </ul>
                    </div>
                    <div class="col-md-6 item text">
                        <h3>Company Name</h3>
                        <p>Praesent sed lobortis mi. Suspendisse vel placerat ligula. Vivamus ac sem lacus. Ut vehicula rhoncus elementum. Etiam quis tristique lectus. Aliquam in arcu eget velit pulvinar dictum vel in justo.</p>
                    </div>
                    <div class="col item social"><a href="#"><i class="icon ion-social-facebook"></i></a><a href="#"><i class="icon ion-social-twitter"></i></a><a href="#"><i class="icon ion-social-snapchat"></i></a><a href="#"><i class="icon ion-social-instagram"></i></a></div>
                </div>
                <p class="copyright">TrackIT © 2019</p>
            </div>
        </footer>
    </div>
    <script src="../../assets/js/jquery.min.js"></script>
    <script src="../../assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js"></script>
    <script src="../../assets/js/script.min.js"></script>
</body>

</html>