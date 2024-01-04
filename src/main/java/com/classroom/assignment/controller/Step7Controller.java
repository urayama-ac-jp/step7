package com.classroom.assignment.controller;

import com.classroom.assignment.model.request.CommentForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.classroom.assignment.service.CommentService;
import com.classroom.assignment.entity.Comment;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/step7")
public class Step7Controller {

  private final CommentService commentService;

  public Step7Controller(CommentService commentService) {
    this.commentService = commentService;
  }

  @GetMapping()
  public String index(CommentForm commentForm, Model model) {
    List<Comment> list = commentService.getAll();
    model.addAttribute("commentList", list);

    return "step7/index";
  }

  @PostMapping()
  public String pageBack(CommentForm commentForm, Model model) {
    List<Comment> list = commentService.getAll();
    model.addAttribute("commentList", list);
    model.addAttribute("comment", commentForm);

    return "step7/index";
  }

  @PostMapping("/comment")
  public String comment(@Validated CommentForm commentForm, BindingResult result, Model model) {
    if (result.hasErrors()) {
      List<Comment> list = commentService.getAll();
      model.addAttribute("commentList", list);

      return "step7/index";
    }
    model.addAttribute("name", commentForm.getName());
    model.addAttribute("content", commentForm.getContent());
    model.addAttribute("mail", commentForm.getMail());
    return "step7/confirm";
  }

  @PostMapping("/save")
  public String save(@Validated CommentForm commentForm, BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "redirect:";
    }
    Comment Comment = new Comment();
    Comment.setName(commentForm.getName());
    Comment.setMail(commentForm.getMail());
    Comment.setContent(commentForm.getContent());
    Comment.setCreated(LocalDateTime.now());

    commentService.save(Comment);

    return "redirect:";
  }

  @GetMapping("/search-name")
  public String searchName(@RequestParam(name = "searchName", required = false) String name,
      CommentForm commentForm, Model model) {
    List<Comment> list = commentService.getCommentSerchByName(name);

    model.addAttribute("commentList", list);
    model.addAttribute("searchName", name);

    return "step7/index";
  }

  @GetMapping("/search-id")
  public String searchId(@RequestParam(name = "searchId", required = false) String id,
      CommentForm commentForm, Model model) {
    List<Comment> list = commentService.getCommentSerchById(id);

    model.addAttribute("commentList", list);
    model.addAttribute("searchId", id);

    return "step7/index";
  }
}
