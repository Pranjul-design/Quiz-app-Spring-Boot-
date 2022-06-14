<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <style>
			#paragraph span{
			    background-color: #0096FF;
			    color:white;
			}
    </style>

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
                        <h1 class="h3 mb-0 text-gray-800">Question Master</h1>
                        <a href="/question/addquestion" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-plus fa-sm text-white-50"></i> Add Question</a>
                    </div>
                    
                    <div class="card-header py-3">
                   		<form:form action="/question/search" modelAttribute="customSearch">
                         <div class="row">
                         
                             <div class="col-md-6">
                                <form:input path="keyword" placeholder="Search Question here..." type="text" class="form-control border-success" />
                              </div>
                                 <div class="col-md-2 mb-md-0 mb-2">
                                      <form:select path="subId" class="form-control">
                                      	<form:option value="-1">Select</form:option>
										   <c:forEach var="temp" items="${subjectDetails}">
                                             <form:option value="${temp.subjectId}">${temp.subjectName}</form:option>
                                          </c:forEach>
										</form:select> 
                                   </div>
                                <button class="btn btn-primary px-5" onClick="setTimeout( findAndHighlight, delay )">Search</button>
                                	
                           </div>
                           </form:form>
                          
                       </div>


                    <!-- Content Row -->

                    <div class="row">

                        <!-- Area Chart -->
                        <div class="col-xl-12">
                            <div class="card shadow mb-4">
                                <!-- Card Header - Dropdown -->
                                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                    <h6 class="m-0 font-weight-bold text-primary">Question List</h6>
                                </div>
                                <!-- Card Body -->
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table border">
                                            <thead>
                                                <tr>
                                                    <th>Question satement</th>
                                                    <th>Subject Name</th>
                                                    <th>Correct Answer</th>
                                                    <th>Status</th>
                                                    <th class="text-center" style="width: 80px;">Action</th>
                                                </tr>
                                            </thead>
                                           <tbody>
                                            <c:forEach var="data" items="${questionDetails}">
                                                <tr class="line-content">
                                                    <td id="paragraph">${data.question}</td>
                                                    <td>${data.subjectName}</td>
                                                    <td>${data.correctAns}</td>
                                                    <c:choose>
                                                    	<c:when test="${data.queStatus > 0}">
                                                    		<td class="text-success">Active</td>
                                                    	</c:when>
                                                    	<c:otherwise>
                                                    		<td class="text-danger">InActive</td>
                                                    	</c:otherwise>
                                                    </c:choose>
                                                    <td class="text-center">
                                                        <a href="/question/updatequestion/${data.questionId}"><i class="fas fa-pencil-alt"></i></a> <strong class="text-light">|</strong>
                                                        <a href="/question/deletequestion/${data.questionId}"><i class="fa fa-trash"></i></a>
                                                    </td>
                                                </tr>
                                              </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="mt-3">
                                        <nav aria-label="Page navigation">
                                            <ul class="pagination justify-content-center">
                                           	
                                               
                                            </ul>
                                        </nav>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>



                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->

            <!-- End of Footer -->

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
    <script type="text/javascript" src="/static/js/pagination2.js"></script>
     <script>
        $(function() {
            $('[data-toggle="tooltip"]').tooltip();
        });
    </script>
    <script>
 
 
    function findAndHighlight() {
	 
	    var text = document.getElementById("keyword").value;
	    var search = new RegExp("(\\b" + text + "\\b)", "gim");
	    var e = document.getElementById("paragraph").innerHTML;
	    var enew = e.replace(/(<span>|<\/span>)/igm, "");
	    document.getElementById("paragraph").innerHTML = enew;
	    var newe = enew.replace(search, "<span>$1</span>");
	    document.getElementById("paragraph").innerHTML = newe;
	 
		}
    	var delay = 1000;

    	setTimeout( findAndHighlight, delay )
	</script>

</body>

</html>