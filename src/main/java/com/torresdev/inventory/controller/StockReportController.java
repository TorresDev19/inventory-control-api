package com.torresdev.inventory.controller;

import com.torresdev.inventory.dto.stock.StockReportResponseDTO;
import com.torresdev.inventory.service.StockReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class StockReportController {

    private final StockReportService stockReportService;

    public StockReportController(StockReportService stockReportService) {
        this.stockReportService = stockReportService;
    }

    @GetMapping("/stock")
    public List<StockReportResponseDTO> stockReport() {
        return stockReportService.generateStockReport();
    }
}
