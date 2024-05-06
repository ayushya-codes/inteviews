package com.sentryc.interview.SentrycInterview.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Ayushya
 */

@Getter
@Setter
public class Data implements Serializable {
    private List<SellerResponseDto> data = new ArrayList<>();
}
