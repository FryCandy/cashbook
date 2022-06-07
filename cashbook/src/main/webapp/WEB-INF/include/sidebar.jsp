<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <!-- jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

	       <div class="sidebar-header">
	           <div class="d-flex justify-content-between">
	               <div class="logo">
	                   <a href="${pageContext.request.contextPath}/index.jsp"><img src="${pageContext.request.contextPath}/template/assets/images/logo/cashBookLogo.PNG" alt="Logo"></a>
	               </div>
	           </div>
	       </div>
	       <div class="sidebar-menu">
	           <ul class="menu">
	               <li class="sidebar-title">Menu</li>
	               <!-- 관리자 전용기능, 관리자는 5레벨 이상-->
	               <c:if test="${sessionLoginMember.level > 4 }">
		               <li class="sidebar-item  has-sub">
		                   <a href="" class='sidebar-link'>
		                       <i class="bi bi-collection-fill"></i>
		                       <span>관리자 전용 기능</span>
		                   </a>
		                   <ul class="submenu">
		                       <li class="submenu-item">
		                           <a href="${pageContext.request.contextPath}/host/hostPageController">접속중인 사용자 관리</a>
		                       </li>
		                       <li class="submenu-item ">
		                           <a href="extra-component-sweetalert.html">회원 관리</a>
		                       </li>
		                       <li class="submenu-item ">
		                           <a href="extra-component-toastify.html">공지사항 관리</a>
		                       </li>
		                       <li class="submenu-item ">
		                           <a href="extra-component-rating.html"></a>
		                       </li>
		                       <li class="submenu-item ">
		                           <a href="extra-component-divider.html"></a>
		                       </li>
		                   </ul>
		               </li>
	               </c:if>
           			<!-- 홈버튼 -->
	               <li class="sidebar-item active ">
	                   <a href="${pageContext.request.contextPath}/member/homeController" class='sidebar-link'>
	                       <i class="bi bi-grid-fill"></i>
	                       <span>HOME</span>
	                   </a>
	               </li>
	               <!-- 일일가계부 버튼-->
	               <li class="sidebar-item">
	                   <a href="${pageContext.request.contextPath}/member/cashBookListByMonthController" class='sidebar-link'>
	                   	<svg class="bi" width="1em" height="1em" fill="currentColor">
	                    	<use xlink:href="${pageContext.request.contextPath}/template/assets/vendors/bootstrap-icons/bootstrap-icons.svg#book" />
	                    </svg>
	                       <span>일일가계부</span>
	                   </a>
	               </li>
	               <!-- 월별가계부 버튼-->
	               <li class="sidebar-item">
	                   <a href="${pageContext.request.contextPath}/member/cashBookListByMonthController" class='sidebar-link'>
	                   	<svg class="bi" width="1em" height="1em" fill="currentColor">
	                    	<use xlink:href="${pageContext.request.contextPath}/template/assets/vendors/bootstrap-icons/bootstrap-icons.svg#calendar2-check" />
	                    </svg>
	                       <span>월별가계부</span>
	                   </a>
	               </li>
                  <!-- 통계 버튼-->
	               <li class="sidebar-item  has-sub">
	                   <a href="" class='sidebar-link'>
	                       <i class="bi bi-bar-chart-fill"></i>
	                       <span>통계</span>
	                   </a>
	                   <ul class="submenu">
	                       <li class="submenu-item ">
	                           <a href="ui-chart-chartjs.html">태그 순위</a>
	                       </li>
	                       <li class="submenu-item ">
	                           <a href="ui-chart-apexcharts.html"></a>
	                       </li>
	                   </ul>
	               </li>
	
	
	               <li class="sidebar-item  has-sub">
	                   <a href="#" class='sidebar-link'>
	                       <i class="bi bi-grid-1x2-fill"></i>
	                       <span>Layouts</span>
	                   </a>
	                   <ul class="submenu ">
	                       <li class="submenu-item ">
	                           <a href="layout-default.html">Default Layout</a>
	                       </li>
	                       <li class="submenu-item ">
	                           <a href="layout-vertical-1-column.html">1 Column</a>
	                       </li>
	                       <li class="submenu-item ">
	                           <a href="layout-vertical-navbar.html">Vertical with Navbar</a>
	                       </li>
	                       <li class="submenu-item ">
	                           <a href="layout-horizontal.html">Horizontal Menu</a>
	                       </li>
	                   </ul>
	               </li>
	
	               <li class="sidebar-title">Forms &amp; Tables</li>
	
	               <li class="sidebar-item  has-sub">
	                   <a href="#" class='sidebar-link'>
	                       <i class="bi bi-hexagon-fill"></i>
	                       <span>Form Elements</span>
	                   </a>
	                   <ul class="submenu ">
	                       <li class="submenu-item ">
	                           <a href="form-element-input.html">Input</a>
	                       </li>
	                       <li class="submenu-item ">
	                           <a href="form-element-input-group.html">Input Group</a>
	                       </li>
	                       <li class="submenu-item ">
	                           <a href="form-element-select.html">Select</a>
	                       </li>
	                       <li class="submenu-item ">
	                           <a href="form-element-radio.html">Radio</a>
	                       </li>
	                       <li class="submenu-item ">
	                           <a href="form-element-checkbox.html">Checkbox</a>
	                       </li>
	                       <li class="submenu-item ">
	                           <a href="form-element-textarea.html">Textarea</a>
	                       </li>
	                   </ul>
	               </li>
	
	               <li class="sidebar-item  ">
	                   <a href="form-layout.html" class='sidebar-link'>
	                       <i class="bi bi-file-earmark-medical-fill"></i>
	                       <span>Form Layout</span>
	                   </a>
	               </li>
	
	               <li class="sidebar-item  has-sub">
	                   <a href="#" class='sidebar-link'>
	                       <i class="bi bi-pen-fill"></i>
	                       <span>Form Editor</span>
	                   </a>
	                   <ul class="submenu ">
	                       <li class="submenu-item ">
	                           <a href="form-editor-quill.html">Quill</a>
	                       </li>
	                       <li class="submenu-item ">
	                           <a href="form-editor-ckeditor.html">CKEditor</a>
	                       </li>
	                       <li class="submenu-item ">
	                           <a href="form-editor-summernote.html">Summernote</a>
	                       </li>
	                       <li class="submenu-item ">
	                           <a href="form-editor-tinymce.html">TinyMCE</a>
	                       </li>
	                   </ul>
	               </li>
	
	               <li class="sidebar-item  ">
	                   <a href="table.html" class='sidebar-link'>
	                       <i class="bi bi-grid-1x2-fill"></i>
	                       <span>Table</span>
	                   </a>
	               </li>
	
	               <li class="sidebar-item  ">
	                   <a href="table-datatable.html" class='sidebar-link'>
	                       <i class="bi bi-file-earmark-spreadsheet-fill"></i>
	                       <span>Datatable</span>
	                   </a>
	               </li>
	
	               <li class="sidebar-title">Extra UI</li>
	
	               <li class="sidebar-item  has-sub">
	                   <a href="#" class='sidebar-link'>
	                       <i class="bi bi-pentagon-fill"></i>
	                       <span>Widgets</span>
	                   </a>
	                   <ul class="submenu ">
	                       <li class="submenu-item ">
	                           <a href="ui-widgets-chatbox.html">Chatbox</a>
	                       </li>
	                       <li class="submenu-item ">
	                           <a href="ui-widgets-pricing.html">Pricing</a>
	                       </li>
	                       <li class="submenu-item ">
	                           <a href="ui-widgets-todolist.html">To-do List</a>
	                       </li>
	                   </ul>
	               </li>
	
	               <li class="sidebar-item  has-sub">
	                   <a href="#" class='sidebar-link'>
	                       <i class="bi bi-egg-fill"></i>
	                       <span>Icons</span>
	                   </a>
	                   <ul class="submenu ">
	                       <li class="submenu-item ">
	                           <a href="ui-icons-bootstrap-icons.html">Bootstrap Icons </a>
	                       </li>
	                       <li class="submenu-item ">
	                           <a href="ui-icons-fontawesome.html">Fontawesome</a>
	                       </li>
	                       <li class="submenu-item ">
	                           <a href="ui-icons-dripicons.html">Dripicons</a>
	                       </li>
	                   </ul>
	               </li>
	

	
	               <li class="sidebar-item  ">
	                   <a href="ui-file-uploader.html" class='sidebar-link'>
	                       <i class="bi bi-cloud-arrow-up-fill"></i>
	                       <span>File Uploader</span>
	                   </a>
	               </li>
	
	               <li class="sidebar-item  has-sub">
	                   <a href="#" class='sidebar-link'>
	                       <i class="bi bi-map-fill"></i>
	                       <span>Maps</span>
	                   </a>
	                   <ul class="submenu ">
	                       <li class="submenu-item ">
	                           <a href="ui-map-google-map.html">Google Map</a>
	                       </li>
	                       <li class="submenu-item ">
	                           <a href="ui-map-jsvectormap.html">JS Vector Map</a>
	                       </li>
	                   </ul>
	               </li>
	
	               <li class="sidebar-title">Pages</li>
	
	               <li class="sidebar-item  ">
	                   <a href="application-email.html" class='sidebar-link'>
	                       <i class="bi bi-envelope-fill"></i>
	                       <span>Email Application</span>
	                   </a>
	               </li>
	
	               <li class="sidebar-item  ">
	                   <a href="application-chat.html" class='sidebar-link'>
	                       <i class="bi bi-chat-dots-fill"></i>
	                       <span>Chat Application</span>
	                   </a>
	               </li>
	
	               <li class="sidebar-item  ">
	                   <a href="application-gallery.html" class='sidebar-link'>
	                       <i class="bi bi-image-fill"></i>
	                       <span>Photo Gallery</span>
	                   </a>
	               </li>
	
	               <li class="sidebar-item  ">
	                   <a href="application-checkout.html" class='sidebar-link'>
	                       <i class="bi bi-basket-fill"></i>
	                       <span>Checkout Page</span>
	                   </a>
	               </li>
	
	               <li class="sidebar-item  has-sub">
	                   <a href="#" class='sidebar-link'>
	                       <i class="bi bi-person-badge-fill"></i>
	                       <span>Authentication</span>
	                   </a>
	                   <ul class="submenu ">
	                       <li class="submenu-item ">
	                           <a href="auth-login.html">Login</a>
	                       </li>
	                       <li class="submenu-item ">
	                           <a href="auth-register.html">Register</a>
	                       </li>
	                       <li class="submenu-item ">
	                           <a href="auth-forgot-password.html">Forgot Password</a>
	                       </li>
	                   </ul>
	               </li>
	
	               <li class="sidebar-item  has-sub">
	                   <a href="#" class='sidebar-link'>
	                       <i class="bi bi-x-octagon-fill"></i>
	                       <span>Errors</span>
	                   </a>
	                   <ul class="submenu ">
	                       <li class="submenu-item ">
	                           <a href="error-403.html">403</a>
	                       </li>
	                       <li class="submenu-item ">
	                           <a href="error-404.html">404</a>
	                       </li>
	                       <li class="submenu-item ">
	                           <a href="error-500.html">500</a>
	                       </li>
	                   </ul>
	               </li>
	
	               <li class="sidebar-title">Raise Support</li>
	
	               <li class="sidebar-item  ">
	                   <a href="https://zuramai.github.io/mazer/docs" class='sidebar-link'>
	                       <i class="bi bi-life-preserver"></i>
	                       <span>Documentation</span>
	                   </a>
	               </li>
	
	               <li class="sidebar-item  ">
	                   <a href="https://github.com/zuramai/mazer/blob/main/CONTRIBUTING.md" class='sidebar-link'>
	                       <i class="bi bi-puzzle"></i>
	                       <span>Contribute</span>
	                   </a>
	               </li>
	
	               <li class="sidebar-item  ">
	                   <a href="https://github.com/zuramai/mazer#donate" class='sidebar-link'>
	                       <i class="bi bi-cash"></i>
	                       <span>Donate</span>
	                   </a>
	               </li>
	
	           </ul>
	       </div>
	       <button class="sidebar-toggler btn x"><i data-feather="x"></i></button>
    <script src="${pageContext.request.contextPath}/template/assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="${pageContext.request.contextPath}/template/assets/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/template/assets/vendors/apexcharts/apexcharts.js"></script>
    <script src="${pageContext.request.contextPath}/template/assets/js/pages/dashboard.js"></script>
    <script src="${pageContext.request.contextPath}/template/assets/js/main.js"></script>