package com.example.springorder.Page;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class PageRequest {
    private int page;
    private int size;
    private String sortBy;
    private boolean isAsc;
}
