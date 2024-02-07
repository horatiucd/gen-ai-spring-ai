package com.hcd.genaispringai.response;

import java.util.List;

public record JobReasons(String job,
                         String location,
                         List<String> reasons) {
}
