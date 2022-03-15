package com.example;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.websocket.server.PathParam;
import javax.ws.rs.WebApplicationException;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Carrito;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.providers.PooledConnectionProvider;
import springfox.documentation.spring.web.json.Json;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping(path = "/carrito")
public class CarritoService {
	
	  HostAndPort config = new HostAndPort(Protocol.DEFAULT_HOST, 6380);
	  PooledConnectionProvider provider = new PooledConnectionProvider(config);
	  UnifiedJedis client = new UnifiedJedis(provider);

	
	@Autowired
	private StringRedisTemplate template;
	private ValueOperations<String, String> redisValue;

	@GetMapping("/{session}")
	private String get(@PathVariable int id) {
		return redisValue.get(Integer.toString(id));
	}

	@PostMapping("/add/{session}")
	@ApiResponses(value = {
            @ApiResponse(code = 400, message = "Detalles del carrito o sesion no recibidos!"),
            @ApiResponse(code = 500, message = "No se pudo añadir el carrito a redis!") })
	 public String addCarrito(
	            @ApiParam(value = "SessionId", required = true) @PathParam("session") String session,
	            @ApiParam(value = "Coste", required = true) BigDecimal amount,
	            @ApiParam(value = "Cantidad", required = true) Integer quantity,
	            @ApiParam(value = "idPizza", required = true) Integer idPizza
	            ) {

	        if (session == null || amount == null
	                || quantity == null || idPizza == null) {
	            final String shortReason = "Detalles del carrito no recibidos!";
	            Exception cause = new IllegalArgumentException(shortReason);
	            throw new WebApplicationException(cause,
	                    javax.ws.rs.core.Response.Status.BAD_REQUEST);
	        }
	        	Carrito carrito = new Carrito(session, amount, quantity, idPizza);
	        	client.jsonSet("session:"+session, carrito);
	        	
	        	return client.get(session);
		}
	
	
	@DeleteMapping("/{session}")
	private String deleteCarrito(@PathVariable int id) {
		redisValue = template.opsForValue();
			if ((redisValue.get(Integer.toString(id)) != null) && (Integer.parseInt(redisValue.get(Integer.toString(id))) >= 1))
				return "" + redisValue.decrement(Integer.toString(id));

		return "No existe el id";
	}
}
