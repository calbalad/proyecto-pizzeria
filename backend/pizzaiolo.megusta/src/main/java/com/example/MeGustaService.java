package com.example;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/me-gusta")
public class MeGustaService {
	public final String ME_GUSTA_CONT = "megusta";
	@Autowired
	private StringRedisTemplate template;
	private ValueOperations<String, String> redisValue;

	@GetMapping("/{id}")
	private String get(@PathVariable int id) {
		return redisValue.get(Integer.toString(id));
	}

	@PostMapping("/{id}")
	private String addLike(@PathVariable int id) {
		redisValue = template.opsForValue();
		if (redisValue.get(Integer.toString(id)) == null)
			redisValue.set(Integer.toString(id), "1");
		else
			redisValue.increment(Integer.toString(id));

		return redisValue.get(Integer.toString(id));
	}
	
	@DeleteMapping("/{id}")
	private String deleteLike(@PathVariable int id) {
		redisValue = template.opsForValue();
			if ((redisValue.get(Integer.toString(id)) != null) && (Integer.parseInt(redisValue.get(Integer.toString(id))) >= 1))
				return "" + redisValue.decrement(Integer.toString(id));

		return "No existe el id";
	}
}
