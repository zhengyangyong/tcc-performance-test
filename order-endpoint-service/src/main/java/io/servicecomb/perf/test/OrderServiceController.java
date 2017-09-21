package io.servicecomb.perf.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/order")
public class OrderServiceController {

    @Autowired
    OrderServiceImpl orderService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String makeOrder() {
        orderService.makeOrder("mike");

        return "success";
    }
}
