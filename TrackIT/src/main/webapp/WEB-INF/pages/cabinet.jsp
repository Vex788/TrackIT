<!DOCTYPE html>
<html style="background-color: rgba(9,12,13,0);">

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

<body style="font-family: 'Open Sans', sans-serif;background-color: #ffffff;background-image: url(../../assets/img/header-3.jpg);background-size: cover;background-repeat: no-repeat;height: 100%;">
    <header class="d-lg-flex masthead text-center text-white d-flex" style="font-family: 'Open Sans', sans-serif;margin-top: -40%;">
        <nav class="navbar navbar-light navbar-expand-lg fixed-top bg-dark" id="mainNav" style="background-color: #252529;">
            <div class="container"><a class="navbar-brand js-scroll-trigger" href="#page-top">Trackit</a><button class="navbar-toggler navbar-toggler-right" data-toggle="collapse" data-target="#navbarResponsive" type="button" aria-controls="navbarResponsive" aria-expanded="false"
                    aria-label="Toggle navigation"><i class="fa fa-align-justify" style="color: rgba(255,255,255,0.9);"></i></button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="nav navbar-nav ml-auto">
                        <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="#about" id="about">About</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="/registration" id="registration">logout</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="/payment" id="pay">pay</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="#contact" id="contact">Contact</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
    <div class="container">
        <div class="row">
            <div class="col-lg-10 mx-auto">
                <h3 class="text-uppercase"><strong class="d-lg-flex justify-content-lg-center" style="color: rgb(255,255,255);">Personal cabinet</strong></h3>
                <hr>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="jumbotron" style="background-color: rgba(233,236,239,0);">
                    <div class="col-auto col-lg-8 text-dark mx-auto" style="background-color: rgba(15,15,15,0.8);padding: 15px;padding-top: 20px;padding-bottom: 20px;">
                        <form>
                            <div class="col-lg-12 offset-lg-0 d-inline-flex d-lg-flex justify-content-lg-center">
                                <h3 class="text-danger" style="color: rgb(220,53,69);font-family: 'Open Sans', sans-serif;">&nbsp;</h3>
                            </div>
                        </form>
                        <div class="row">
                            <div class="col"><button class="btn btn-dark btn-block btn-lg bg-dark border rounded border-dark shadow" type="button">email@email.com</button></div>
                            <div class="col"><button class="btn btn-warning btn-block btn-lg bg-warning border rounded border-dark shadow" type="button" disabled="disabled">plus account</button></div>
                            <div class="col" style="margin: 5px;"><br>
<br>
<br><strong class="d-lg-flex justify-content-lg-center" style="color: rgb(255,255,255);font-size: 18px;font-family: 'Open Sans', sans-serif;">TRACKING</strong>
                                <div class="row" style="margin-bottom: 15px;margin-top: 15px;">
                                    <div class="col-lg-12 offset-lg-0 d-inline-flex d-lg-flex justify-content-lg-center"><input class="bg-dark border rounded border-dark shadow form-control-sm" type="url" name="url" placeholder="Enter URL" inputmode="url" style="font-family: 'Open Sans', sans-serif;color: rgb(255,255,255);margin-right: 5px;width: 100%;font-size: 16px;padding-top: 6px;padding-bottom: 6px;">
                                        <input
                                            class="bg-dark border rounded border-dark shadow form-control-sm d-lg-flex justify-content-lg-end" type="text" name="current_price" placeholder="Current price" inputmode="numeric" style="width: 40%;font-family: 'Open Sans', sans-serif;color: rgb(255,255,255);font-size: 16px;padding-top: 6px;padding-bottom: 6px;"></div>
                                </div>
                                <div class="row">
                                    <div class="col d-lg-flex justify-content-lg-center align-items-lg-start"><button class="btn btn-dark btn-sm bg-danger border rounded border-danger shadow" type="button" id="report_a_drop" style="font-family: 'Open Sans', sans-serif;font-style: normal;font-weight: normal;margin-right: 5px;width: 160px;color: rgb(0,0,0);padding-top: 5px;padding-bottom: 5px;">report a drop</button>
                                        <button
                                            class="btn btn-dark btn-sm bg-success border rounded border-success shadow" type="button" id="report_a_drop" style="font-family: 'Open Sans', sans-serif;font-style: normal;font-weight: normal;width: 160px;color: rgb(0,0,0);padding-top: 5px;padding-bottom: 5px;">report promotion</button><br>
<br>
<br></div>
                                </div>
                                <div class="table-responsive" style="color: rgb(255,255,255);margin-top: 55px;">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>URL</th>
                                                <th>Report</th>
                                                <th><strong>Notification</strong></th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>test</td>
                                                <td>Below amount</td>
                                                <td>Email</td>
                                                <td><button class="btn btn-dark btn-sm bg-primary border rounded border-primary shadow" type="button" id="report_a_drop" style="font-family: 'Open Sans', sans-serif;font-style: normal;font-weight: normal;width: 60px;font-size: 15px;color: rgb(0,0,0);">edit</button></td>
                                            </tr>
                                            <tr>
                                                <td>test</td>
                                                <td>Below amount</td>
                                                <td>Email</td>
                                                <td><button class="btn btn-dark btn-sm bg-primary border rounded border-primary shadow" type="button" id="report_a_drop" style="font-family: 'Open Sans', sans-serif;font-style: normal;font-weight: normal;width: 60px;font-size: 15px;color: rgb(0,0,0);">edit</button></td>
                                            </tr>
                                            <tr>
                                                <td>test</td>
                                                <td>Below amount</td>
                                                <td>Email</td>
                                                <td><button class="btn btn-dark btn-sm bg-primary border rounded border-primary shadow" type="button" id="report_a_drop" style="font-family: 'Open Sans', sans-serif;font-style: normal;font-weight: normal;width: 60px;font-size: 15px;color: rgb(0,0,0);">edit</button></td>
                                            </tr>
                                            <tr>
                                                <td>test</td>
                                                <td>Below amount</td>
                                                <td>Email</td>
                                                <td><button class="btn btn-dark btn-sm bg-primary border rounded border-primary shadow" type="button" id="report_a_drop" style="font-family: 'Open Sans', sans-serif;font-style: normal;font-weight: normal;width: 60px;font-size: 15px;color: rgb(0,0,0);">edit</button></td>
                                            </tr>
                                            <tr>
                                                <td>test</td>
                                                <td>Below amount</td>
                                                <td>Email</td>
                                                <td><button class="btn btn-dark btn-sm bg-primary border rounded border-primary shadow" type="button" id="report_a_drop" style="font-family: 'Open Sans', sans-serif;font-style: normal;font-weight: normal;width: 60px;font-size: 15px;color: rgb(0,0,0);">edit</button></td>
                                            </tr>
                                            <tr>
                                                <td>test</td>
                                                <td>Below amount</td>
                                                <td>Email</td>
                                                <td><button class="btn btn-dark btn-sm bg-primary border rounded border-primary shadow" type="button" id="report_a_drop" style="font-family: 'Open Sans', sans-serif;font-style: normal;font-weight: normal;width: 60px;font-size: 15px;color: rgb(0,0,0);">edit</button></td>
                                            </tr>
                                            <tr>
                                                <td>test</td>
                                                <td>Below amount</td>
                                                <td>Email</td>
                                                <td><button class="btn btn-dark btn-sm bg-primary border rounded border-primary shadow" type="button" id="report_a_drop" style="font-family: 'Open Sans', sans-serif;font-style: normal;font-weight: normal;width: 60px;font-size: 15px;color: rgb(0,0,0);">edit</button></td>
                                            </tr>
                                            <tr>
                                                <td>test</td>
                                                <td>Above sum</td>
                                                <td>Email</td>
                                                <td><button class="btn btn-dark btn-sm bg-primary border rounded border-primary shadow" type="button" id="report_a_drop" style="font-family: 'Open Sans', sans-serif;font-style: normal;font-weight: normal;width: 60px;font-size: 15px;color: rgb(0,0,0);">edit</button></td>
                                            </tr>
                                            <tr>
                                                <td>test</td>
                                                <td>Above sum</td>
                                                <td>Email</td>
                                                <td><button class="btn btn-dark btn-sm bg-primary border rounded border-primary shadow" type="button" id="report_a_drop" style="font-family: 'Open Sans', sans-serif;font-style: normal;font-weight: normal;width: 60px;font-size: 15px;color: rgb(0,0,0);">edit</button></td>
                                            </tr>
                                            <tr>
                                                <td>test</td>
                                                <td>Above sum</td>
                                                <td>Email</td>
                                                <td><button class="btn btn-dark btn-sm bg-primary border rounded border-primary shadow" type="button" id="report_a_drop" style="font-family: 'Open Sans', sans-serif;font-style: normal;font-weight: normal;width: 60px;font-size: 15px;color: rgb(0,0,0);">edit</button></td>
                                            </tr>
                                            <tr>
                                                <td>test</td>
                                                <td>Above sum</td>
                                                <td>Email</td>
                                                <td><button class="btn btn-dark btn-sm bg-primary border rounded border-primary shadow" type="button" id="report_a_drop" style="font-family: 'Open Sans', sans-serif;font-style: normal;font-weight: normal;width: 60px;font-size: 15px;color: rgb(0,0,0);">edit</button></td>
                                            </tr>
                                            <tr>
                                                <td>test</td>
                                                <td>Above sum</td>
                                                <td>Email</td>
                                                <td><button class="btn btn-dark btn-sm bg-primary border rounded border-primary shadow" type="button" id="report_a_drop" style="font-family: 'Open Sans', sans-serif;font-style: normal;font-weight: normal;width: 60px;font-size: 15px;color: rgb(0,0,0);">edit</button></td>
                                            </tr>
                                            <tr>
                                                <td>test</td>
                                                <td>Above sum</td>
                                                <td>Email</td>
                                                <td><button class="btn btn-dark btn-sm bg-primary border rounded border-primary shadow" type="button" id="report_a_drop" style="font-family: 'Open Sans', sans-serif;font-style: normal;font-weight: normal;width: 60px;font-size: 15px;color: rgb(0,0,0);">edit</button></td>
                                            </tr>
                                            <tr>
                                                <td>test</td>
                                                <td>Above sum</td>
                                                <td>Email</td>
                                                <td><button class="btn btn-dark btn-sm bg-primary border rounded border-primary shadow" type="button" id="report_a_drop" style="font-family: 'Open Sans', sans-serif;font-style: normal;font-weight: normal;width: 60px;font-size: 15px;color: rgb(0,0,0);">edit</button></td>
                                            </tr>
                                            <tr>
                                                <td>test</td>
                                                <td>Above sum</td>
                                                <td>Email</td>
                                                <td><button class="btn btn-dark btn-sm bg-primary border rounded border-primary shadow" type="button" id="report_a_drop" style="font-family: 'Open Sans', sans-serif;font-style: normal;font-weight: normal;width: 60px;font-size: 15px;color: rgb(0,0,0);">edit</button></td>
                                            </tr>
                                            <tr>
                                                <td>test</td>
                                                <td>Above sum</td>
                                                <td>Email</td>
                                                <td><button class="btn btn-dark btn-sm bg-primary border rounded border-primary shadow" type="button" id="report_a_drop" style="font-family: 'Open Sans', sans-serif;font-style: normal;font-weight: normal;width: 60px;font-size: 15px;color: rgb(0,0,0);">edit</button></td>
                                            </tr>
                                            <tr>
                                                <td>test</td>
                                                <td>Above sum</td>
                                                <td>Email</td>
                                                <td><button class="btn btn-dark btn-sm bg-primary border rounded border-primary shadow" type="button" id="report_a_drop" style="font-family: 'Open Sans', sans-serif;font-style: normal;font-weight: normal;width: 60px;font-size: 15px;color: rgb(0,0,0);">edit</button></td>
                                            </tr>
                                            <tr>
                                                <td>test</td>
                                                <td>Above sum</td>
                                                <td>Email</td>
                                                <td><button class="btn btn-dark btn-sm bg-primary border rounded border-primary shadow" type="button" id="report_a_drop" style="font-family: 'Open Sans', sans-serif;font-style: normal;font-weight: normal;width: 60px;font-size: 15px;color: rgb(0,0,0);">edit</button></td>
                                            </tr>
                                            <tr>
                                                <td>test</td>
                                                <td>Above sum</td>
                                                <td>Email</td>
                                                <td><button class="btn btn-dark btn-sm bg-primary border rounded border-primary shadow" type="button" id="report_a_drop" style="font-family: 'Open Sans', sans-serif;font-style: normal;font-weight: normal;width: 60px;font-size: 15px;color: rgb(0,0,0);">edit</button></td>
                                            </tr>
                                            <tr>
                                                <td>test</td>
                                                <td>Above sum</td>
                                                <td>Email</td>
                                                <td><button class="btn btn-dark btn-sm bg-primary border rounded border-primary shadow" type="button" id="report_a_drop" style="font-family: 'Open Sans', sans-serif;font-style: normal;font-weight: normal;width: 60px;font-size: 15px;color: rgb(0,0,0);">edit</button></td>
                                            </tr>
                                            <tr>
                                                <td>test</td>
                                                <td>Above sum</td>
                                                <td>Email</td>
                                                <td><button class="btn btn-dark btn-sm bg-primary border rounded border-primary shadow" type="button" id="report_a_drop" style="font-family: 'Open Sans', sans-serif;font-style: normal;font-weight: normal;width: 60px;font-size: 15px;color: rgb(0,0,0);">edit</button></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div><br>
</div>
                    <p></p>
                </div>
            </div>
        </div>
    </div>
    <div class="text-left d-flex d-lg-flex justify-content-lg-center footer-dark" style="font-family: 'Open Sans', sans-serif;background-color: rgb(37,37,41);">
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