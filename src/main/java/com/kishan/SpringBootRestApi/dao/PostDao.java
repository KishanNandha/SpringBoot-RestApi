package com.kishan.SpringBootRestApi.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.kishan.SpringBootRestApi.models.Post;

@Component
public class PostDao {
	private static List<Post> posts = new ArrayList<>();
	
	static {
		posts.add(new Post(1, "test 1", "kishan"));
		posts.add(new Post(2, "test 2", "karan"));
		posts.add(new Post(3, "test 3", "ravi"));
	}
	
	public List<Post> findAll() {
		return posts;
	}

	public Post saveUser(Post post) {
		if(null == post.getPostId()) {
			post.setPostId(posts.size()+1);
		}
		posts.add(post);
		return post;
	}
	
	public Post findOne(Integer id) {
		if(id != null) {
			for(Post post: posts) {
				if(post.getPostId().equals(id)) {
					return post;
				}
			}
		}
		return null;
	}
	
	public void deleteOne(Post post) {
		posts.remove(post);
	}
}
