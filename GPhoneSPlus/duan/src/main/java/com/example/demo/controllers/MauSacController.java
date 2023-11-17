package com.example.demo.controllers;

import com.example.demo.models.MauSac;
import com.example.demo.models.Ram;
import com.example.demo.services.MauSacService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/mau-sac")
@Controller
public class MauSacController {
    @Autowired
    private MauSacService mauSacService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @ModelAttribute("mauSac") MauSac mauSac,
                          @RequestParam("pageNum") Optional<Integer> pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "15") Integer pageSize) {
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        mauSac.setTinhTrang(0);
        Page<MauSac> page = mauSacService.getAll(pageable);
        model.addAttribute("total", page.getTotalPages());
        model.addAttribute("listMauSac", page.getContent());
        model.addAttribute("list", page.getNumber());
        model.addAttribute("contentPage", "../mausac/mau-sac.jsp");
        return "home/layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("mauSac") MauSac mauSac) {
        model.addAttribute("mauSac", new MauSac());
        model.addAttribute("contentPage", "../mausac/add.jsp");
        return "home/layout";
    }

    @PostMapping("/add-mau-sac")
    public String addMauSac(Model model, @ModelAttribute("mauSac") @Valid MauSac mauSac, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contentPage", "../mausac/add.jsp");
            return "home/layout";
        }
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        Integer sl = mauSacService.findAll().size();
        String maMS = "";
        if (sl < 10) {
            maMS = "MaMS0" + sl;
        } else {
            maMS = "MaMS" + sl;
        }
        mauSac.setMa(maMS);
        mauSac.setNgayTao(date);
        mauSac.setNgayCapNhat(date);
        mauSac.setTinhTrang(0);
        mauSacService.add(mauSac);
        return "redirect:/mau-sac/hien-thi";
    }

    @GetMapping("/detail-mau-sac/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id,
                             @RequestParam("pageNum") Optional<Integer> pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "15") Integer pageSize) {
        Sort sort = Sort.by("ma").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize);
        MauSac mauSac = mauSacService.findById(id);
        model.addAttribute("mauSac", mauSac);
        Page<MauSac> page = mauSacService.getAll(pageable);
        model.addAttribute("contentPage", "../mausac/mau-sac-update.jsp");
        model.addAttribute("listMauSac", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "home/layout";
    }

    @PostMapping("/update-mau-sac/{id}")
    public String updateRam(Model model, @PathVariable("id") UUID id, @ModelAttribute("mauSac") @Valid MauSac mauSac,
                            BindingResult bindingResult, @RequestParam(name = "pageSize", required = false, defaultValue = "15") Integer pageSize) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contentPage", "../mausac/mau-sac-update.jsp");
            return "home/layout";
        }
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        mauSac.setNgayTao(date);
        mauSac.setNgayCapNhat(date);
        mauSacService.update(id, mauSac);
        return "redirect:/mau-sac/hien-thi";
    }

    @GetMapping("/hien-thi-delete")
    public String hienThiDelete(Model model, @ModelAttribute("ram") Ram ram,
                                @RequestParam("pageNum") Optional<Integer> pageNum,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "15") Integer pageSize) {

        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        Page<MauSac> page = mauSacService.getAll1(pageable);
        model.addAttribute("contentPage", "../mausac/mau-sac-delete.jsp");
        model.addAttribute("listMauSac", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "home/layout";
    }

    @GetMapping("/update-all-status")
    public String updateTT(Model model, @RequestParam("pageNum") Optional<Integer> pageNum,
                           @RequestParam(name = "pageSize", required = false, defaultValue = "15") Integer pageSize, @ModelAttribute("mauSac") MauSac mauSac) {
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        mauSac.setNgayCapNhat(date);
        mauSacService.updateTT();
        Page<MauSac> page = mauSacService.getAll1(pageable);
        model.addAttribute("contentPage", "../mausac/mau-sac-delete.jsp");
        model.addAttribute("listMauSac", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "home/layout";
    }

    @GetMapping("/update-status/{id}")
    public String updateStatus(Model model, @PathVariable("id") UUID id,
                               @RequestParam("pageNum") Optional<Integer> pageNum,
                               @RequestParam(name = "pageSize", required = false, defaultValue = "15") Integer pageSize, @ModelAttribute("mauSac") MauSac mauSac) {
        Sort sort = Sort.by("ma").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        MauSac mauSac1 = mauSacService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        mauSac1.setNgayCapNhat(date);
        mauSac1.setTinhTrang(1);
        mauSacService.update(id, mauSac1);
        Page<MauSac> page = mauSacService.getAll(pageable);
        model.addAttribute("contentPage", "../mausac/mau-sac.jsp");
        model.addAttribute("listMauSac", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "home/layout";
    }

    @GetMapping("/reset-status/{id}")
    public String resetStatus(Model model, @PathVariable("id") UUID id,
                              @RequestParam("pageNum") Optional<Integer> pageNum,
                              @RequestParam(name = "pageSize", required = false, defaultValue = "15") Integer pageSize,
                              @ModelAttribute("mauSac") MauSac mauSac
    ) {
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        MauSac mauSac1 = mauSacService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        mauSac1.setNgayCapNhat(date);
        mauSac1.setTinhTrang(0);
        mauSacService.update(id, mauSac1);
        Page<MauSac> page = mauSacService.getAll1(pageable);
        model.addAttribute("contentPage", "../mausac/mau-sac-delete.jsp");
        model.addAttribute("listMauSac", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "home/layout";
    }

    @PostMapping("/search-0")
    public String search0(Model model, @ModelAttribute("mauSac") MauSac mauSac, @RequestParam("search") String search) {
        List<MauSac> list = mauSacService.search0(search);
        model.addAttribute("listMauSac", list);
        model.addAttribute("contentPage", "../mausac/mau-sac.jsp");
        return "home/layout";
    }

    @PostMapping("/search-1")
    public String search1(Model model, @ModelAttribute("ram") Ram ram, @RequestParam("search") String search) {
        List<MauSac> list = mauSacService.search1(search);
        model.addAttribute("listMauSac", list);
        model.addAttribute("contentPage", "../mausac/mau-sac-delete.jsp");
        return "home/layout";
    }
}

