package com.beverage.BeverageBox.service;

import com.beverage.BeverageBox.dto.request.BeverageRequestDto;
import com.beverage.BeverageBox.dto.response.BeverageResponseDto;
import com.beverage.BeverageBox.entity.Beverage;
import com.beverage.BeverageBox.repository.BeverageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeverageService {

    private final BeverageRepository beverageRepository;

    public BeverageResponseDto create(BeverageRequestDto dto) {
        Beverage beverage = Beverage.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .imageUrl(dto.getImageUrl())
                .build();

        beverageRepository.save(beverage);

        return toDto(beverage);
    }

    public List<BeverageResponseDto> findAll() {
        return beverageRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private BeverageResponseDto toDto(Beverage b) {
        return new BeverageResponseDto(
                b.getId(),
                b.getName(),
                b.getDescription(),
                b.getPrice(),
                b.getStock(),
                b.getImageUrl()
        );
    }
}
