package com.imooc.repository;

import com.imooc.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by XuQin on 2018/7/5.
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
}
