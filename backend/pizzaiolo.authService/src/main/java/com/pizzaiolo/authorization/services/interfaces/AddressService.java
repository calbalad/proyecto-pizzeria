package com.pizzaiolo.authorization.services.interfaces;

import com.pizzaiolo.authorization.models.dtos.CreateAddressDto;
import com.pizzaiolo.authorization.models.entities.Address;

public interface AddressService {
	
	Address save(String id, CreateAddressDto role);

	void delete(String id);
}
