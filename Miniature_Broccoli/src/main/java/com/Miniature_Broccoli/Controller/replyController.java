package com.Miniature_Broccoli.Controller;

<<<<<<< Updated upstream
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

=======
import com.Miniature_Broccoli.PoJo.Article;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



>>>>>>> Stashed changes
@CrossOrigin
@RestController
@RequestMapping("/replyform")
@Api(tags = "回复接口")
public class replyController {

<<<<<<< Updated upstream
=======
    @GetMapping()
    public Object getClassify() {
        return 10 / 0;
    }
>>>>>>> Stashed changes
}