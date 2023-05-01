package com.sergeev.palindrom.app.api.v1.controller.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserPalindromeResponse {

    private String userName;

    private Long points;

    private String lastAttemptDescription;
}
