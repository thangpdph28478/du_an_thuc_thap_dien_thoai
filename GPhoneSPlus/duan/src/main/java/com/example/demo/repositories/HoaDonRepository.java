package com.example.demo.repositories;

import com.example.demo.models.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, UUID> {
    @Query("select hd from HoaDon hd where hd.tinhTrang=0 and hd.loai=0")
    List<HoaDon> find();

    @Query("select hd from HoaDon hd where  hd.loai=1 order by hd.ngayTao desc ")
    List<HoaDon> donHang();

    @Query("select hd from HoaDon hd order by hd.ngayTao desc")
    List<HoaDon> hoaDon();

    @Transactional
    @Modifying
    @Query("update HoaDon hd set hd.tinhTrang=:tinhTrang,hd.ngayCapNhat =:ngayCapNhat where hd.id=:id")
    void update(UUID id, int tinhTrang, Date ngayCapNhat);

    @Query("SELECT hd FROM HoaDon hd " +
            "LEFT JOIN KhachHang kh ON hd.khachHang.id = kh.id " +
            "LEFT JOIN NhanVien nv ON hd.nhanVien.id = nv.id " +
            "LEFT JOIN DiaChi dc ON hd.diaChi.id = dc.id " +
            "LEFT JOIN QuyDoi qd ON hd.quyDoi.id = qd.id " +
            "WHERE hd.ma LIKE %:ten% OR kh.hoTen LIKE %:ten% OR nv.hoTen LIKE %:ten% OR dc.diaChi LIKE %:ten% OR qd.soTienQuyDoi = :soTienQuyDoi OR hd.sdt like %:ten%")
    List<HoaDon> search(@Param("ten") String ten, @Param("soTienQuyDoi") BigDecimal soTienQuyDoi);

    @Query("select hd from HoaDon hd " +
            "LEFT JOIN KhachHang kh ON hd.khachHang.id = kh.id  " +
            "LEFT JOIN NhanVien nv ON hd.nhanVien.id = nv.id " +
            "LEFT JOIN DiaChi dc ON hd.diaChi.id = dc.id " +
            " where (:idKH IS NULL OR kh.id=:idKH) " +
            "and (:idNV IS NULL OR nv.id=:idNV)  " +
            "and (:idDC IS NULL OR dc.id=:idDC) " +
            "AND (:trangThai is null or hd.tinhTrang=:trangThai)" +
            "and(:loai is null or hd.loai=:loai) AND " +
            "((:startDate IS NULL OR :endDate IS NULL) OR hd.ngayThanhToan >= COALESCE(:startDate, hd.ngayThanhToan) " +
            "and hd.ngayThanhToan <= COALESCE(:endDate, hd.ngayThanhToan))  " +
            "AND ((:shipStartDate IS NULL OR :shipEndDate IS NULL) OR hd.ngayShip >= COALESCE(:shipStartDate, hd.ngayShip) " +
            "and hd.ngayShip <= COALESCE(:shipEndDate, hd.ngayShip))  " +
            "AND ((:receiveStartDate IS NULL OR :receiveEndDate IS NULL) OR hd.ngayNhan >= COALESCE(:receiveStartDate, hd.ngayNhan) " +
            "and hd.ngayNhan <= COALESCE(:receiveEndDate, hd.ngayNhan)) ")
    List<HoaDon> loc1(
            UUID idKH, UUID idNV, UUID idDC, Integer trangThai, Integer loai,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(value = "shipStartDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date shipStartDate,
            @RequestParam(value = "shipEndDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date shipEndDate,
            @RequestParam(value = "receiveStartDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date receiveStartDate,
            @RequestParam(value = "receiveEndDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date receiveEndDate);

    @Query("SELECT h FROM HoaDon h WHERE h.ngayThanhToan >= :startDate")
    List<HoaDon> findAllByCreatedAtAfter(@Param("startDate") java.util.Date startDate);

    @Query("SELECT h FROM HoaDon h WHERE h.ngayNhan >= :startDate")
    List<HoaDon> findAllByNgayNhan(@Param("startDate") java.util.Date startDate);

    @Query("SELECT h FROM HoaDon h WHERE h.ngayShip >= :startDate")
    List<HoaDon> findAllByNgayShip(@Param("startDate") java.util.Date startDate);

    @Query("select hd from HoaDon hd " +
            "LEFT JOIN KhachHang kh ON hd.khachHang.id = kh.id  " +
            "LEFT JOIN NhanVien nv ON hd.nhanVien.id = nv.id " +
            "LEFT JOIN DiaChi dc ON hd.diaChi.id = dc.id " +
            " where hd.loai=1 and (:idKH IS NULL OR kh.id=:idKH) " +
            "and (:idNV IS NULL OR nv.id=:idNV)  " +
            "and (:idDC IS NULL OR dc.id=:idDC) " +
            "AND (:trangThai is null or hd.tinhTrang=:trangThai)" +
            "AND ((:startDate IS NULL OR :endDate IS NULL) OR hd.ngayThanhToan >= COALESCE(:startDate, hd.ngayThanhToan) " +
            "and hd.ngayThanhToan <= COALESCE(:endDate, hd.ngayThanhToan))  " +
            "AND ((:shipStartDate IS NULL OR :shipEndDate IS NULL) OR hd.ngayShip >= COALESCE(:shipStartDate, hd.ngayShip) " +
            "and hd.ngayShip <= COALESCE(:shipEndDate, hd.ngayShip))  " +
            "AND ((:receiveStartDate IS NULL OR :receiveEndDate IS NULL) OR hd.ngayNhan >= COALESCE(:receiveStartDate, hd.ngayNhan) " +
            "and hd.ngayNhan <= COALESCE(:receiveEndDate, hd.ngayNhan)) ")
    List<HoaDon> locDonHang(
            UUID idKH, UUID idNV, UUID idDC, Integer trangThai,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(value = "shipStartDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date shipStartDate,
            @RequestParam(value = "shipEndDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date shipEndDate,
            @RequestParam(value = "receiveStartDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date receiveStartDate,
            @RequestParam(value = "receiveEndDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date receiveEndDate);

    @Query("SELECT hd FROM HoaDon hd " +
            "LEFT JOIN KhachHang kh ON hd.khachHang.id = kh.id " +
            "LEFT JOIN NhanVien nv ON hd.nhanVien.id = nv.id " +
            "LEFT JOIN DiaChi dc ON hd.diaChi.id = dc.id " +
            "LEFT JOIN QuyDoi qd ON hd.quyDoi.id = qd.id " +
            "WHERE hd.loai=1 and (hd.ma LIKE %:ten% OR kh.hoTen LIKE %:ten% OR nv.hoTen LIKE %:ten% OR dc.diaChi LIKE %:ten% OR hd.sdt like %:ten%)")
    List<HoaDon> searchDonHang(String ten);

    @Query("SELECT h FROM HoaDon h WHERE h.ngayThanhToan >= :startDate and h.loai=1")
    List<HoaDon> findDonHangByCreatedAtAfter(@Param("startDate") java.util.Date startDate);

    @Query("SELECT h FROM HoaDon h WHERE h.ngayNhan >= :startDate and h.loai=1")
    List<HoaDon> findDonHangByNgayNhan(@Param("startDate") java.util.Date startDate);

    @Query("SELECT h FROM HoaDon h WHERE h.ngayShip >= :startDate and h.loai=1")
    List<HoaDon> findDonHangByNgayShip(@Param("startDate") java.util.Date startDate);
}
