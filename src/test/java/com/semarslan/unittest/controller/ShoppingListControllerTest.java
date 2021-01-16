package com.semarslan.unittest.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.semarslan.unittest.dto.request.ShoppingListRequest;
import com.semarslan.unittest.dto.response.ShoppingListResponse;
import com.semarslan.unittest.entity.ShoppingList;
import com.semarslan.unittest.service.ShoppingListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class ShoppingListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShoppingListService shoppingListService;

    @Test
    void testGetAll() throws Exception {
        List<ShoppingListResponse> responseList = new ArrayList<>();
        responseList.add(ShoppingListResponse.of(new ShoppingList("Buy something 1" , true)));
        responseList.add(ShoppingListResponse.of(new ShoppingList("Buy something 2" , true)));

        when(shoppingListService.getAll()).thenReturn(responseList);
        mockMvc.perform(MockMvcRequestBuilders.get("/shoppingList")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(print());
    }

    @Test
    void testSave() throws Exception {
        ShoppingListResponse response = ShoppingListResponse.builder().content("Buy Something").completed(true).build();

        when(shoppingListService.save((ShoppingListRequest) any(ShoppingListRequest.class))).thenReturn(response);

        ObjectMapper objectMapper = new ObjectMapper();
        ShoppingList shoppingList = new ShoppingList("Buy Something", true);
        String shoppingListJson = objectMapper.writeValueAsString(shoppingList);

        ResultActions result = mockMvc
                .perform(post("/shoppingList").contentType(MediaType.APPLICATION_JSON).content(shoppingListJson));

        result.andExpect(status().isOk()).andExpect(jsonPath("$.text").value("Buy Something"))
                .andExpect(jsonPath("$.completed").value(true));
    }
}
