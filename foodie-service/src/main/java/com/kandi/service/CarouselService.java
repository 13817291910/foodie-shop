package com.kandi.service;

import com.kandi.pojo.Carousel;
import java.util.List;

public interface CarouselService {

    /**
     * 查询所有轮播图列表
     * @param isShow
     * @return
     */
    List<Carousel> queryAll(Integer isShow);

}
