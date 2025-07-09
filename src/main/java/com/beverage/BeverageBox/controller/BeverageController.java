package com.beverage.BeverageBox.controller;

import com.beverage.BeverageBox.dto.request.BeverageRequestDto;
import com.beverage.BeverageBox.dto.request.BeverageSearchCondition;
import com.beverage.BeverageBox.dto.response.BeverageResponseDto;
import com.beverage.BeverageBox.service.BeverageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beverages")
@RequiredArgsConstructor
public class BeverageController {

    private final BeverageService beverageService;

    @PostMapping
    public BeverageResponseDto create(@Valid @RequestBody BeverageRequestDto dto) {
        return beverageService.create(dto);
    }

    @GetMapping
    public List<BeverageResponseDto> findAll() {
        return beverageService.findAll();
    }

    @GetMapping
    public List<BeverageResponseDto> searchBeverages(@Valid @ModelAttribute BeverageSearchCondition condition) {
        return beverageService.searchBeverages(condition);
    }

}
