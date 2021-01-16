package com.semarslan.unittest.controller;

import com.semarslan.unittest.dto.request.ShoppingListRequest;
import com.semarslan.unittest.dto.response.ShoppingListResponse;
import com.semarslan.unittest.service.ShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("shoppingList")
@RestController
@RequiredArgsConstructor
public class ShoppingListController {

    private final ShoppingListService service;


    @GetMapping
    public ResponseEntity<List<ShoppingListResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid ShoppingListRequest request) {
        final var response = service.save(request);
        return ResponseEntity.ok(response);
    }
}
