<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html style="background-color: rgba(9,12,13,0);">

<head>
    <meta charset="UTF-8">
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

<body style="font-family: Roboto, sans-serif;background-color: #444349;background-image: url(../../assets/img/pattern.png);background-size: cover;background-repeat: no-repeat;height: 100%;color: rgba(255,255,255,0);">
    <header class="d-lg-flex masthead text-center text-white d-flex" style="font-family: 'Open Sans', sans-serif;margin-top: -40%;">
        <nav class="navbar navbar-light navbar-expand-lg fixed-top bg-dark" id="mainNav" style="-webkit-box-shadow: 0px 3px 20px 0px rgba(0,0,0,0.75);-moz-box-shadow: 0px 3px 20px 0px rgba(0,0,0,0.75);box-shadow: 0px 3px 20px 0px rgba(0,0,0,0.75);background-color: #252529;font-family: Roboto, sans-serif;">
            <div class="container"><a class="navbar-brand js-scroll-trigger" href="#">Trackit</a><button class="navbar-toggler navbar-toggler-right" data-toggle="collapse" data-target="#navbarResponsive" type="button" aria-controls="navbarResponsive" aria-expanded="false"
                    aria-label="Toggle navigation"><i class="fa fa-align-justify" style="color: rgba(255,255,255,0.9);"></i></button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="nav navbar-nav ml-auto">
                        <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" style="cursor: pointer;" onclick="editUserDataWindow()" id="edit_data">edit data</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="/logout" id="logout">logout</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="/payment" id="payment">pay</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
    <div class="container">
        <div class="row">
            <div class="col-lg-10 mx-auto">
                <h3 class="text-uppercase"><strong class="d-lg-flex justify-content-lg-center" style="color: rgb(255,255,255);font-family: Roboto, sans-serif;">Personal cabinet</strong></h3>
                <hr>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="jumbotron" style="background-color: rgba(233,236,239,0);">
                    <div class="col-auto col-lg-8 text-dark mx-auto" style="-webkit-box-shadow: 0px 0px 25px 0px rgba(0,0,0,0.75);-moz-box-shadow: 0px 0px 25px 0px rgba(0,0,0,0.75);box-shadow: 0px 0px 25px 0px rgba(0,0,0,0.75);background-color: rgba(15,15,15,0.8);padding: 15px;padding-top: 20px;padding-bottom: 20px;">
                        <form>
                            <div class="col-lg-12 offset-lg-0 d-inline-flex d-lg-flex justify-content-lg-center">
                                <h3 class="text-danger" style="color: rgb(220,53,69);font-family: 'Open Sans', sans-serif;">&nbsp;</h3>
                            </div>
                        </form>
                        <div class="row">
                            <div class="col"><button class="btn btn-dark btn-block btn-lg bg-dark border rounded border-dark shadow" type="button" id="email" style="font-family: 'Open Sans', sans-serif;">- - - - - - - -</button></div>
                            <div class="col" id="plus_account"><button class="btn btn-dark btn-block btn-lg bg-dark border rounded border-dark shadow" type="button">plus account</button></div>
                            <div class="col" style="margin: 5px;"><br>
<br>
<br><strong class="d-lg-flex justify-content-lg-center" style="color: rgb(255,255,255);font-size: 18px;font-family: Roboto, sans-serif;">TRACKING</strong>
                                <form action="/cabinet/add_task" method="POST" id="addTaskForm">
                                <div class="row" style="margin-bottom: 15px;margin-top: 15px;">
                                    <div class="col-lg-12 offset-lg-0 d-inline-flex d-lg-flex justify-content-lg-center"><input class="bg-dark border rounded border-dark shadow form-control-sm d-md-flex" type="url" id="url" placeholder="Enter URL" inputmode="url" style="font-family: Roboto, sans-serif;color: rgb(255,255,255);margin-right: 5px;width: 100%;font-size: 16px;padding-top: 6px;padding-bottom: 6px;"></div>
                                </div>
                                <div class="row" style="margin-bottom: 15px;margin-top: 15px;">
                                    <div class="col-lg-12 offset-lg-0 d-inline-flex d-lg-flex justify-content-lg-center" style="margin-top: -7px;"><input class="bg-dark border rounded border-dark shadow form-control-sm d-md-flex" id="currency_codes" placeholder="Currency codes [ EUR/USD ]" pattern="^[a-zA-Z]{3,3}/[a-zA-Z]{3}$" title="Format for entering a currency code [ BTC/USD ]" style="font-family: Roboto, sans-serif;color: rgb(255,255,255);margin-right: 5px;width: 100%;font-size: 16px;padding-top: 6px;padding-bottom: 6px;">
                                        <input
                                            class="bg-dark border rounded border-dark shadow form-control-sm d-md-flex" type="url" id="final_price" placeholder="Final price" inputmode="numeric" style="font-family: Roboto, sans-serif;color: rgb(255,255,255);margin-right: 5px;width: 100%;font-size: 16px;padding-top: 6px;padding-bottom: 6px;"></div>
                                </div>
                                <div class="row">
                                    <div class="col d-md-flex d-lg-flex justify-content-md-center align-items-md-center justify-content-lg-center align-items-lg-start"><button class="btn btn-dark btn-sm bg-danger border rounded border-danger shadow" type="button" id="report_a_drop" onclick="increaseToggle(false)" style="font-family: Roboto, sans-serif;font-style: normal;font-weight: normal;margin-right: 5px;width: 160px;color: rgb(0,0,0);padding-top: 5px;padding-bottom: 5px;font-size: 15px;">report a drop</button>
                                        <button
                                            class="btn btn-dark btn-sm bg-success border rounded border-success shadow" type="button" id="report_promotion" onclick="increaseToggle(true)" style="font-family: Roboto, sans-serif;font-style: normal;font-weight: normal;width: 160px;color: rgb(0,0,0);padding-top: 5px;padding-bottom: 5px;font-size: 15px;">report promotion</button><br>
<br>
<br></div>
                                </div>
                                </form>
                                <div class="table-responsive" style="color: rgb(255,255,255);margin-top: 55px;">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>TITLE</th>
                                                <th>VALUE</th>
                                                <th>DELETE</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                    <div id="empty_field"></div>
                                </div>
                            </div>
                        </div><br>
</div>
                    <p></p>
                </div>
            </div>
        </div>
    </div>
    <div class="text-left d-flex d-lg-flex justify-content-lg-center footer-dark" style="-webkit-box-shadow: 0px -3px 20px 0px rgba(0,0,0,0.75);-moz-box-shadow: 0px -3px 20px 0px rgba(0,0,0,0.75);box-shadow: 0px -3px 20px 0px rgba(0,0,0,0.75);font-family: 'Open Sans', sans-serif;background-color: rgb(37,37,41);">
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
    <script src="../../assets/js/script.min.js" charset="UTF-8"></script>
    <script src="../../assets/js/cabinet.js" charset="UTF-8"></script>
    <script src="../../assets/js/add_task.js" charset="UTF-8"></script>
    <script src="../../assets/js/jalert_gen.js" charset="UTF-8"></script>
</body>

</html>