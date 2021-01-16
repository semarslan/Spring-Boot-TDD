package com.semarslan.unittest.service;

import com.semarslan.unittest.dto.request.ShoppingListRequest;
import com.semarslan.unittest.dto.response.ShoppingListResponse;
import com.semarslan.unittest.entity.ShoppingList;
import com.semarslan.unittest.repository.ShoppingListRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ShoppingListServiceTest {

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private ShoppingListService shoppingListService;

    @AfterEach
    void tearDown(){
        shoppingListRepository.deleteAll();
    }

    @Test
    void testGetAll(){
        ShoppingList shoppingList = new ShoppingList("buy something", true);
        shoppingListRepository.save(shoppingList);

        List<ShoppingListResponse> responseList = shoppingListService.getAll();
        ShoppingListResponse lastShopping = responseList.get(responseList.size() - 1);

        assertEquals(shoppingList.getContent(), lastShopping.getContent());
        assertEquals(shoppingList.getCompleted(), lastShopping.getCompleted());
    }

    @Test
    void testSave() {
        ShoppingListRequest request = new ShoppingListRequest("Buy Something", false);

        shoppingListService.save(request);

        assertEquals(1.0, shoppingListRepository.count());
    }
}
