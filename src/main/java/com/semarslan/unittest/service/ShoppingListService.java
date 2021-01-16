package com.semarslan.unittest.service;

import com.semarslan.unittest.dto.request.ShoppingListRequest;
import com.semarslan.unittest.dto.response.ShoppingListResponse;

import java.util.List;

public interface ShoppingListService {

    List<ShoppingListResponse> getAllShoppingLists();

    ShoppingListResponse save(ShoppingListRequest request);
}
