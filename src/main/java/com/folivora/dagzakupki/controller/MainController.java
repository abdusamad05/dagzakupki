package com.folivora.dagzakupki.controller;


import com.folivora.dagzakupki.domain.Bid;
import com.folivora.dagzakupki.domain.User;
import com.folivora.dagzakupki.reps.BidRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static java.util.UUID.randomUUID;

@Controller
public class MainController {

    @Autowired
    private BidRep bidRep;

    @Value("${upload.path}")
    private String uploadPath;



    @GetMapping("/")
    public String home(Map<String, Object> model) {
        return "home";
    }

    @GetMapping("/bids")
    public String bids(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Bid> bids = bidRep.findAll();
        if (filter !=null && !filter.isEmpty()){
            bids = bidRep.findByObjectLike("%" + filter + "%");
        }else {
            bids = bidRep.findAll();
        }
        model.addAttribute("bids", bids);
        model.addAttribute("filter", filter);
        return "bids";
    }


    @GetMapping("/created")
    public String created(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Bid> bids = bidRep.findAll();
        if (filter !=null && !filter.isEmpty()){
            bids = bidRep.findByObject(filter);
        }else {
            bids = bidRep.findAll();
        }
        model.addAttribute("bids", bids);
        model.addAttribute("filter", filter);
        return "created";
    }

    @PostMapping("/created")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String object,
            @RequestParam Long price, Map<String, Object> model,
            @RequestParam ("file") MultipartFile file
    ) throws IOException {
        Bid bid = new Bid(object, price, user);

        if (file != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            bid.setFilename(resultFilename);
        }

        bidRep.save(bid);

        Iterable<Bid> bids = bidRep.findAll();
        model.put("bids", bids);
        return "created";
    }

}
