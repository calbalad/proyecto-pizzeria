package com.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.websocket.server.PathParam;
import javax.ws.rs.WebApplicationException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Jedis;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Carrito;
import com.fasterxml.jackson.databind.JsonNode;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.providers.PooledConnectionProvider;
import springfox.documentation.spring.web.json.Json;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping(path = "/carrito")
public class CarritoDTO {
	
	  HostAndPort config = new HostAndPort(Protocol.DEFAULT_HOST, 6380);
	  PooledConnectionProvider provider = new PooledConnectionProvider(config);
	  UnifiedJedis client = new UnifiedJedis(provider);
	  
	  @Autowired
		private StringRedisTemplate template;
		private ValueOperations<String, String> redisValue;

	@GetMapping("/{session}")
	private Object get(@PathVariable String session) {
		return client.jsonGet(session);
	}

	@PostMapping("/add/{session}")
	@ApiResponses(value = {
			    @ApiResponse(code = 400, message = "Detalles del carrito o sesion no recibidos!"),
			    @ApiResponse(code = 500, message = "No se pudo añadir el carrito a redis!") })
	public Object addCarrito(@RequestBody String carrito, @PathVariable String session ) {
		redisValue = template.opsForValue();
				
		System.out.println(carrito);
		client.jsonSet("Carritos", carrito); 
	        	return client.get(session);
		}
//		String datos = carrito.toString();
//		redisValue.set(session, carrito.toString(1));
//		return datos;
//	}
	

	
	
	@DeleteMapping("/{session}")
	private Object deleteCarrito(@PathVariable String session) {
			if (client.jsonGet(session) != null)
				return "" + client.del(session);

		return "No existe el id";
	}
}
