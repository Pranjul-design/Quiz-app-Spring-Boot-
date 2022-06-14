<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Quiz</title>

    <!-- Custom fonts for this template-->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/static/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-smile"></i>
                </div>
                <div class="sidebar-brand-text mx-3">Admin</div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item ">
                <a class="nav-link" href="/subject">
                    <i class="fas fa-fw fa-list"></i>
                    <span>Subject</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/question">
                    <i class="fas fa-fw fa-folder"></i>
                    <span>Question Master</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/quizsetting">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>Quiz Setting</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#" data-toggle="modal" data-target="#logoutModal">
                    <i class="fas fa-sign-out-alt fa-fw"></i>
                    <span>Logout</span></a>
            </li>


        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link  rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>


                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Update Question</h1>
                    </div>


                    <!-- Content Row -->

                    <div class="row">

                        <!-- Area Chart -->
                        <div class="col-xl-12">
                        <form:form action="edit/${id}" modelAttribute="questionDetails">
                            <div class="card shadow mb-4">
                                <!-- Card Header - Dropdown -->
                                
                                <div class="card-header py-3">
                                    <div class="row">
                                        <div class="col-md-2 mb-md-0 mb-2">
                                        	<form:hidden path="questionId" />
                                            <form:select path="subjectId" class="form-control">
                                            	<form:option value="0">SubjectName</form:option>
                                            	<c:forEach var="temp" items="${subjectDetails}">
                                               	 <form:option value="${temp.subjectId}">${temp.subjectName}</form:option>
                                                </c:forEach>
                                            </form:select>
                                            </div>
                                       		<div class="col-md-2">
                                       			<form:select path="queStatus" class="form-control">
                                       				<form:option value="1">Active</form:option>
                                       				<form:option value="0">Inactive</form:option>
                                       			</form:select>
                                       		</div>
                                       		<div class="col-md-8">
                                    			<form:input type="text" path="question"  placeholder="Type Question Statement here..." class="form-control border-success"/>
                                    		</div>
                                    </div>
                                </div>
                                
                                <!-- Card Body -->
                                <div class="card-body">
                                    <div class="table-responsive">
                                    <table class="table border">
                                            <thead>
                                                <tr>
                                                    <th></th>
                                                    <th>Answer Label</th>
                                                    <th class="text-center">Select correct answer</th>
                                                </tr>
                                            </thead>
                                         <tbody>
                                                <tr>
                                                    <td>Answer 1</td>
                                                    <form:hidden path="ansList[0].answerId" />
                                                    <td><form:input path="ansList[0].answer" type="text" class="form-control"/> </td>
                                                    <td class="text-center">
                                                        <form:radiobutton value="${questionDetails.ansList[0].answerId}" path="correctAnswerId" name="answerradio" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Answer 2</td>
                                                    <form:hidden path="ansList[1].answerId" />
                                                    <td><form:input  path="ansList[1].answer" type="text" class="form-control"/> </td>
                                                    <td class="text-center">
                                                        <form:radiobutton value="${questionDetails.ansList[1].answerId}" path="correctAnswerId" name="answerradio" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Answer 3</td>
                                                    <form:hidden path="ansList[2].answerId" />
                                                    <td><form:input  path="ansList[2].answer" type="text" class="form-control"/> </td>
                                                    <td class="text-center">
                                                        <form:radiobutton value="${questionDetails.ansList[2].answerId}" path="correctAnswerId" name="answerradio" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Answer 4</td>
                                                    <form:hidden path="ansList[3].answerId" />
                                                    <td><form:input path="ansList[3].answer"  type="text" class="form-control"/> </td>
                                                    <td class="text-center">
                                                        <form:radiobutton value="${questionDetails.ansList[3].answerId}" path="correctAnswerId" name="answerradio"/>
                                                    </td>
                                                </tr>

                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="col-12 mt-3 text-center">
                                        <button class="btn btn-primary px-5">Save</button>
                                    </div>
                                </div>
                            </div>
                            </form:form>
                        </div>

                    </div>



                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->



        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="../">Logout</a>
                </div>
            </div>
        </div>
    </div>

   <!-- Bootstrap core JavaScript-->
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/static/js/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/static/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="/static/js/Chart.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="/static/js/chart-area-demo.js"></script>
    <script src="/static/js/chart-pie-demo.js"></script>

</body>

</html>