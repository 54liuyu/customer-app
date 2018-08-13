package cn.ly.customer;

import cn.ly.common.util.JsonUtils;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyu685
 * @since 2018/8/4
 */
@RestController
public class CustomerController {
  @Autowired private EurekaClient eurekaClient;
  @Autowired private RestTemplate restTemplate;

  @Autowired private LoadBalancerClient loadBalancerClient;
  @Autowired private DiscoveryClient discoveryClient;

  @RequestMapping("/consume/list")
  public String getConsumeList() {
    List<String> consumeList = new ArrayList();
    for (long i = 1; i <= 3; i++) {
      String storeName = getStoreName(i);
      String customerName = getCustomerName(i);
      consumeList.add(customerName + "-" + storeName);
    }
    return JsonUtils.toJSONString(consumeList);
  }

  private String getCustomerName(Long customerId) {
    if (customerId == 1) {
      return "lucy ";
    } else if (customerId == 2) {
      return "peter";
    } else {
      return "mark";
    }
  }

  private String serviceUrl() {
    List<ServiceInstance> instanceList = discoveryClient.getInstances("store-app");
    for(ServiceInstance instance : instanceList){
      System.out.println(instance.getUri());
    }
    InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("store-app", false);
    return instanceInfo.getHomePageUrl();
  }

  private String getStoreName(Long customerId) {
    String url = serviceUrl() + "/store/" + customerId;
    return restTemplate.getForObject(url, String.class);
  }
}
