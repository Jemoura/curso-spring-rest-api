package com.algaworks.osworks.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.api.model.Comment;
import com.algaworks.osworks.api.model.CommentInputModel;
import com.algaworks.osworks.api.model.CommentModel;
import com.algaworks.osworks.domain.exception.EntityNotFoundException;
import com.algaworks.osworks.domain.model.ServiceOrder;
import com.algaworks.osworks.domain.repository.ServiceOrderRepository;
import com.algaworks.osworks.domain.service.ServiceOrderService;

@RestController
@RequestMapping("/service-orders/{serviceOrderId}/comments")
public class CommentController {
	
	@Autowired
	private ServiceOrderService serviceOrderService;
	
	@Autowired
	private ServiceOrderRepository serviceOrderRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CommentModel addComment(@PathVariable Long serviceOrderId, @RequestBody CommentInputModel commentInputModel) {
		Comment comment = serviceOrderService.addComment(serviceOrderId, commentInputModel.getDescription());
		
		return toModel(comment);
	}
	
	@GetMapping
	public List<CommentModel> listAll(@PathVariable Long serviceOrderId) {
		ServiceOrder serviceOrder = serviceOrderService.fetch(serviceOrderId)
				.orElseThrow(() -> new EntityNotFoundException("Ordem de serviço não encontrada."));
		return toCollectionModel(serviceOrder.getComments());
	}
	
	private CommentModel toModel(Comment comment) {
		return modelMapper.map(comment, CommentModel.class);
	}
	
	private List<CommentModel> toCollectionModel(List<Comment> comments) {
		return comments.stream().map(comment -> toModel(comment)).collect(Collectors.toList());
	}

}
