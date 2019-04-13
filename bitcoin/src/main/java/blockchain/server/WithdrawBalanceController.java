package blockchain.server;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/server")
public class WithdrawBalanceController {

    @RequestMapping(method = RequestMethod.POST, value="/withdrawBalance")
    public double getUsersValueForToken (@RequestBody IncomingMessage message) {
        return 10;
    }
}
