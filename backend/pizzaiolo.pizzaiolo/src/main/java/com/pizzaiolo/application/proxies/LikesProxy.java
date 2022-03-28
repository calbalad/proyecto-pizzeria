package com.pizzaiolo.application.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "likes", url = "${http://localhost:8080}")
public interface LikesProxy {
	@GetMapping(path = "/api/v1/likes/{id}")
	String getLikes(@PathVariable int id);
}