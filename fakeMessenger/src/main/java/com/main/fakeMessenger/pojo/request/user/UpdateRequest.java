package com.main.fakeMessenger.pojo.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequest {


    private Integer id;

    private String name;

    private String image_url;

}
