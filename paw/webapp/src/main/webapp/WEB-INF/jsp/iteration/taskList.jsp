<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html>

<html>

<head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/mustache.js/2.2.1/mustache.min.js"></script>
    
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
    crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
    crossorigin="anonymous">

    <link rel="stylesheet" href="styles/main.css">
    <link rel="stylesheet" href="styles/project.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
    crossorigin="anonymous"></script>
</head>


<body>

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#nav-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">Scrumlr</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="nav-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Project view <span class="sr-only">(current)</span></a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Projects <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Project Scrumlr</a></li>
                        </ul>
                    </li>
                </ul>

            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

    <div class="container-fluid">
        <div class="page-header">
            <h1>Iteration #1 <small>for Project Scrumlr</small></h1>
        </div>
    </div>

    <div class="container-fluid">
        <div class="panel-group" id="tasks-list" role="tablist" aria-multiselectable="true">

			<div class="panel panel-default">
                <div class="panel-heading" role="tab" id="task-heading-${idx}">
                    <h2 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#task-list" href="#task-collapse-${idx}" aria-expanded="true" aria-controls="task-collapse-${idx}">
                            Task name
                            <span class="label label-warning" style="float: right;">in progress</span>
                            
                        </a>
                    </h2>
                </div>
                <div id="task-collapse-${idx}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="task-heading-${idx}">

                    <div class="panel-body">
                        <div class="row">
                            <div class="col-sm-8">
                                ${description}
                            </div>
                            <div class="col-sm-4">
                                ${status}
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!-- /panel -->

        </div>
    </div>
    
</body>

</html>