package com.imooc.repository;

import com.imooc.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by XuQin on 2018/7/5.
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}
