package com.semarslan.unittest.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingListRequest {

    private String content;

    private Boolean completed;

}
