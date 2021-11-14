package Mock.mockmoviesgame.mock.controller;

import Mock.mockmoviesgame.mock.model.ResponseUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/mock/ranking")
public class MockController {

    @GetMapping
    public List<ResponseUser>getUsersRanking(){
        return List.of(
                        new ResponseUser("Player 7", 50),
                        new ResponseUser("Player 2", 45),
                        new ResponseUser("Player 3", 45),
                        new ResponseUser("Player 10", 40),
                        new ResponseUser("Player 5", 30),
                        new ResponseUser("PLayer 4", 25 ),
                        new ResponseUser("Player 6", 20),
                        new ResponseUser("Player 1", 15),
                        new ResponseUser("Player 8", 5),
                        new ResponseUser("Player 9", 0)
                );
    }
}
