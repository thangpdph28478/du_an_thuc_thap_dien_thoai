<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Focus - Bootstrap Admin Dashboard </title>
    <!-- Favicon icon -->
</head>
<body>
<div>
    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
        <li class="nav-item">
            <a class="nav-link" href="/nhan-vien/hien-thi" role="tab">Thông tin nhân viên</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/nhan-vien/view-add" role="tab">Thêm nhân viên</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/nhan-vien/hien-thi-delete" role="tab">Nhân viên đã xoá</a>
        </li>

    </ul>
</div>
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab">
        <div class="col-lg-12 grid-margin stretch-card">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title" style="float: left">Danh sách Nhân viên đã xóa</h4>

                    <a href="/nhan-vien/update-all-status" class="btn btn-outline-danger btn-icon-text"
                       style="float: right; margin-left: 900px"
                       tabindex="-1"
                       role="button"
                       onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                        <i class="ti-reload btn-icon-prepend"></i>
                        Status All
                    </a>
                    <br>
                    <%--            Tìm kiếm               --%>
                    <form action="/nhan-vien/search-1" method="post">
                        <div class="input-group" style="width: 30%; float: right">
                            <input type="text" class="form-control" placeholder="Bạn tìm gì..."
                                   aria-label="Bạn tìm gì..." name="search">
                            <div class="input-group-append">
                                <button class="btn btn-sm btn-primary" type="submit">Search</button>
                            </div>
                        </div>
                    </form>
                    <%--           kết thúc tìm kiếm         --%>
                    <div class="table-responsive">
                        <table class="table table-striped" style="color: black">
                            <thead>
                            <tr>
                                <th>STT</th>
                                <th>Mã</th>
                                <th>Ảnh</th>
                                <th>Họ tên</th>
                                <th>Giới tính</th>
                                <th>SĐT</th>
                                <th>Chức vụ</th>
                                <th>Lương</th>
                                <th>Quê quán</th>
                                <th>Trạng thái</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <i class="mdi mdi-border-color"></i>
                            <c:forEach items="${listNhanVien}" var="nhanVien" varStatus="i">
                                <tr>
                                    <th scope="row">${i.index+1}</th>
                                    <td>${nhanVien.ma}</td>
                                    <td align="center">
                                        <img src="/uploads/${nhanVien.urlAnh}" width="40" height="40"></td>
                                    <td>${nhanVien.hoTen}</td>
                                    <td>
                                        <c:if test="${nhanVien.gioiTinh == true}">Nam</c:if>
                                        <c:if test="${nhanVien.gioiTinh == false}">Nữ</c:if>
                                    </td>
                                    <td>${nhanVien.sdt}</td>
                                    <td>${nhanVien.chucVu.ten}</td>
                                    <td>${nhanVien.luong}</td>
                                    <td>${nhanVien.queQuan}</td>
                                    <td>${nhanVien.trangThai()}</td>
                                    <td>
                                        <a href="/nhan-vien/reset-status/${nhanVien.id}"
                                           class="btn btn-danger btn-icon-text"
                                           tabindex="-1"
                                           role="button"
                                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                            <i class="ti-reload btn-icon-prepend"></i>
                                            Status</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <%--phân trang--%>
        <div align="center">
            <div class="btn-group" role="group" aria-label="Basic example">
                <ul class="pagination justify-content-center pagination-lg">
                    <li class="page-item"><a class="page-link" href="/nhan-vien/hien-thi-delete?pageNum=0"><</a></li>
                    <c:forEach begin="1" end="${total}" varStatus="status">
                        <li class="page-item">
                            <a href="${pageContext.request.contextPath}/nhan-vien/hien-thi-delete?pageNum=${status.index -1}"
                               class="page-link">${status.index}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item"><a class="page-link" href="/nhan-vien/hien-thi-delete?pageNum=${total-1}">></a></li>
                </ul>
            </div>
        </div>
        <%--kết thúc phân trang--%>
    </div>
</div>
</body>

</html>
