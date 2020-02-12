package com.xiepanpan.gmall.vo.search;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class SearchResponseAttrVo implements Serializable {

    private Long productAttributeId;//1
    //当前属性值的所有值
    private List<String> value = new ArrayList<String>();
    //属性名称
    private String name;//网络制式，分类
}
