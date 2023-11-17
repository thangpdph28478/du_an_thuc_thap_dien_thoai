package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "man_hinh")
public class ManHinh {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "ma")
    private String ma;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "thong_so")
    private String thongSo;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "loai_cam_ung")
    private String loaiCamUng;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "ti_le_khung_hinh")
    private String tiLeKhungHinh;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "cong_nghe_man_hinh")
    private String congNghe;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "do_phan_giai")
    private String doPhanGiai;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "tan_so_quet")
    private String tanSoQuet;

    @CreationTimestamp
    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_cap_nhat")
    private Date ngayCapNhat;

    @Column(name = "tinh_trang")
    private int tinhTrang;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "mo_ta")
    private String moTa;
}
