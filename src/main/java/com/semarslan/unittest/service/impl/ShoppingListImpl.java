package com.semarslan.unittest.service.impl;


import com.semarslan.unittest.dto.request.ShoppingListRequest;
import com.semarslan.unittest.dto.response.ShoppingListResponse;
import com.semarslan.unittest.entity.ShoppingList;
import com.semarslan.unittest.repository.ShoppingListRepository;
import com.semarslan.unittest.service.ShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingListImpl implements ShoppingListService {
    
    private final ShoppingListRepository repository;
    
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<ShoppingListResponse> getAll() {
        return repository.findAll().stream().map(ShoppingListResponse::of).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ShoppingListResponse save(ShoppingListRequest request) {

        final ShoppingList shoppingList = repository.save(new ShoppingList(request.getContent(), request.getCompleted()));

        return ShoppingListResponse.of(shoppingList);
    }
}
