package com.kishan.SpringBootRestApi.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kishan.SpringBootRestApi.dao.PostDao;
import com.kishan.SpringBootRestApi.exception.PostNotFoundException;
import com.kishan.SpringBootRestApi.models.Post;

@RestController
public class PostResource {

	@Autowired
	private PostDao postDao;
	
	@GetMapping("/posts")
	public List<Post> getAllPosts(){
		return postDao.findAll();
	}
	
	@GetMapping("/posts/{id}")
	public Post getPost(@PathVariable int id) {
		Post post = postDao.findOne(id);
		if(null == post) {
			throw new PostNotFoundException("Post with id:"+id+" not found");
		}
		return post;
	}

	@PostMapping("/posts")
	public  ResponseEntity<Object> createPost(@Valid @RequestBody Post post) {
		Post newPost = postDao.saveUser(post);
		URI location = ServletUriComponentsBuilder   
						.fromCurrentRequest() //gets current request path i.e. localhost:8080/posts
						.path("/{id}") //appends {id}
						.buildAndExpand(newPost.getPostId()) //replaces {id} with actual id
						.toUri(); //builds uri
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/posts/{id}")
	public void daletePost(@PathVariable int id) {
		Post post = postDao.findOne(id);
		if(null == post) {
			throw new PostNotFoundException("Post with id:"+id+" not found");
		}
		postDao.deleteOne(post);
	}
}
