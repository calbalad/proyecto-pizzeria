package com.pizzaiolo.authorization.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.pizzaiolo.authorization.repositories.AddressRepository;
import com.pizzaiolo.authorization.repositories.UserRepository;
import com.pizzaiolo.authorization.services.interfaces.AddressService;
import com.pizzaiolo.authorization.exceptions.ResourceNotFoundException;
import com.pizzaiolo.authorization.models.dtos.CreateAddressDto;
import com.pizzaiolo.authorization.models.entities.Address;
import com.pizzaiolo.authorization.models.entities.User;

@Service
public class AddressServiceImpl implements AddressService {
	private final AddressRepository addressRepository;
	private final UserRepository userRepository;

	public AddressServiceImpl(AddressRepository addressRepository, UserRepository userRepository) {
		this.addressRepository = addressRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Address save(String id, CreateAddressDto createAddressDto) {
		Optional<User> user = userRepository.findById(id);
		Address newAddress = new Address();

		newAddress.setCity(createAddressDto.getCity()).setLocation(createAddressDto.getLocation())
				.setName(createAddressDto.getName()).setNumber(createAddressDto.getNumber())
				.setPostalCode(createAddressDto.getPostalCode()).setStreet(createAddressDto.getStreet());
		addressRepository.save(newAddress);
		
		user.ifPresent(userFound -> {
			userFound.addAddress(newAddress);
			userRepository.save(userFound);
		});
		return newAddress;
	}


	@Override
	public void delete(String id) {
		addressRepository.deleteById(new ObjectId(id));;

	}


}
