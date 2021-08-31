package com.infosat.appRecruitment.controller;


import com.infosat.appRecruitment.model.PostuleInfos;
import com.infosat.appRecruitment.security.service.PostuleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/PostulerInfos")
public class PostuleController {


    /*@Autowired
    private PostuleInfosRepository repo;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/postuleInfos_form")
    public String showAddPostuleInfos(Model model) {
        model.addAttribute("postuleInfos", new PostuleInfos());
        return "postuleInfos_form";
    }

    @PostMapping("/process_postuleInfos")
    public String processPostuleInfos(PostuleInfos postuleInfos, Principal principal) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        //postuleInfos.setCondidatPostulé(user);
        repo.save(postuleInfos);
        return "offre/all";
    }*/


    private final PostuleService postuleService;


    public PostuleController(PostuleService postuleService) {

        this.postuleService = postuleService;
    }

    @PostMapping("/addpostuleInfos")
    public ResponseEntity<PostuleInfos> addPostuleInfos(@RequestBody PostuleInfos postuleInfos) {
        //System.out.println("safaa !" + postuleInfos.toString());
        PostuleInfos newPostuleInfos = postuleService.addPostuleInfos(postuleInfos);

        System.out.print("hey hey !");
        return new ResponseEntity<>(newPostuleInfos, HttpStatus.CREATED);
    }

    @GetMapping("/allpostuleInfos")
    public ResponseEntity<List<PostuleInfos>> getAllPostInfos() {
        List<PostuleInfos> postuleInfoss = postuleService.findAllPostInfos();
        System.out.println("ccccc fait !");
        return new ResponseEntity<>(postuleInfoss, HttpStatus.OK);
    }

    @GetMapping("/findPostInfos/{id}")
    public ResponseEntity<PostuleInfos> getPostInfosById(@PathVariable("id") Long id) {
        PostuleInfos postuleInfos = postuleService.findPostuleInfosById(id);
        return new ResponseEntity<>(postuleInfos, HttpStatus.OK);
    }


}