package com.example.demo.services.impl;

import com.example.demo.models.ChucVu;
import com.example.demo.repositories.ChucVuRepository;
import com.example.demo.services.ChucVuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class ChucVuServiceImpl implements ChucVuService {
    @Autowired
    ChucVuRepository chucVuRepository;


    @Override
    public Page<ChucVu> getAll(Pageable pageable) {
        return chucVuRepository.getAll(pageable);
    }

    @Override
    public Page<ChucVu> getAll1(Pageable pageable) {
        return chucVuRepository.getAll1(pageable);
    }

    @Override
    public List<ChucVu> findAll() {
        return chucVuRepository.findAll();
    }

    @Override
    public List<ChucVu> findAll0() {
        return chucVuRepository.findAll0();
    }

    @Override
    public ChucVu findById(UUID id) {
        return chucVuRepository.findById(id).orElse(null);
    }

    @Override
    public ChucVu add(ChucVu chucVu) {
        return chucVuRepository.save(chucVu);
    }

    @Override
    public ChucVu update(UUID id, ChucVu chucVu) {
        if (id != null) {
            ChucVu chucVuUpdate = chucVuRepository.findById(id).orElse(null);
            if (chucVuUpdate != null) {
                BeanUtils.copyProperties(chucVu, chucVuUpdate);
                chucVuRepository.save(chucVuUpdate);
            }
        }
        return null;
    }

    @Override
    public Boolean delete(UUID id) {
        if (id != null) {
            ChucVu chucVu = chucVuRepository.findById(id).orElse(null);
            if (chucVu != null) {
                chucVuRepository.delete(chucVu);
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateTT() {
        chucVuRepository.updateTT();

    }

    @Override
    public List<ChucVu> search0(String ten) {
        return chucVuRepository.search0(ten);
    }

    @Override
    public List<ChucVu> search1(String ten) {
        return chucVuRepository.search1(ten);
    }
}
