package com.semarslan.unittest.dto.response;


import com.semarslan.unittest.entity.ShoppingList;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class ShoppingListResponse {

    private final String content;

    private final Boolean completed;

    private ShoppingListResponse(String content, Boolean completed) {
        this.content = content;
        this.completed = completed;
    }

    public static ShoppingListResponse of(ShoppingList list) {
        return ShoppingListResponse.builder()
                .content(list.getContent())
                .completed(list.getCompleted())
                .build();
    }
}
