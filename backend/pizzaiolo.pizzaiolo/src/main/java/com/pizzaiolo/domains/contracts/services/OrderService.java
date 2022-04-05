package com.pizzaiolo.domains.contracts.services;

import java.util.List;

import com.pizzaiolo.domains.entities.Order;
import com.pizzaiolo.domains.entities.Order.Status;

public interface OrderService extends ProjectionDomainService <Order, Integer>{
	public <T> List<T> getSolicitado(Class <T> type);
	public <T> List<T> getElaborandose(Class <T> type);
	public <T> List<T> getPreparado(Class <T> type);
	public <T> List<T> getEnviado(Class <T> type);
	public <T> List<T> getRecibido(Class <T> type);
	public <T> List<T> getCancelado(Class <T> type);
	public <T> List<T> getOrderById(String id,Class <T> type);
	public <T> List<T> getOrderByIdUser(String id,Status status,Class <T> type);
}
