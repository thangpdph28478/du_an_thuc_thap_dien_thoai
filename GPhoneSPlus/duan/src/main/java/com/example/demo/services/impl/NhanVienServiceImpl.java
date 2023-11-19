package com.example.demo.services.impl;

import com.example.demo.models.NhanVien;
import com.example.demo.repositories.NhanVienRepository;
import com.example.demo.services.NhanVienService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class NhanVienServiceImpl implements NhanVienService {


    @Autowired
    NhanVienRepository nhanVienRepository;


    @Override
    public Page<NhanVien> getAll(Pageable pageable) {
        return nhanVienRepository.getAll(pageable);
    }

    @Override
    public Page<NhanVien> getAll1(Pageable pageable) {
        return nhanVienRepository.getAll1(pageable);
    }

    @Override
    public List<NhanVien> findAll() {
        return nhanVienRepository.findAll();
    }

    @Override
    public NhanVien findById(UUID id) {
        return nhanVienRepository.findById(id).orElse(null);
    }

    @Override
    public NhanVien add(NhanVien nhanVien) {
        return nhanVienRepository.save(nhanVien);
    }

    @Override
    public NhanVien update(UUID id, NhanVien nhanVien) {
        if (id != null) {
            NhanVien nhanVienUpdate = nhanVienRepository.findById(id).orElse(null);
            if (nhanVienUpdate != null) {
                BeanUtils.copyProperties(nhanVien, nhanVienUpdate);
                nhanVienRepository.save(nhanVienUpdate);
            }
        }
        return null;
    }

    @Override
    public Boolean delete(UUID id) {
        if (id != null) {
            NhanVien nhanVien = nhanVienRepository.findById(id).orElse(null);
            if (nhanVien != null) {
                nhanVienRepository.delete(nhanVien);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsByPhoneNumber(String sdt) {
        return existsByPhoneNumber(sdt);
    }

    @Override
    public void updateTT() {
        nhanVienRepository.updateTT();

    }

    @Override
    public List<NhanVien> search0(String ten) {
        return nhanVienRepository.search0(ten);
    }

    @Override
    public List<NhanVien> search1(String ten) {
        return nhanVienRepository.search1(ten);
    }

    @Override
    public List<NhanVien> searchByChucVuAndGioiTinh(String tenChucVu, String gioiTinh, Pageable pageable) {
        return nhanVienRepository.searchByTenChucVuAndGioiTinh(tenChucVu, Boolean.valueOf(gioiTinh));

    }

    @Override
    public List<NhanVien> nhanVienThanhToan(UUID id) {
        return nhanVienRepository.nhanVienThanhToan(id);
    }

    @Override
    public NhanVien nhanVienUpdateHoaDon(UUID id) {
        return nhanVienRepository.nhanVienUpdateHoaDon(id);
    }


}
