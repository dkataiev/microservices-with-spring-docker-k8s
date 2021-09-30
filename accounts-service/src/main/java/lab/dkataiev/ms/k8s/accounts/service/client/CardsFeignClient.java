package lab.dkataiev.ms.k8s.accounts.service.client;

import lab.dkataiev.ms.k8s.accounts.model.Card;
import lab.dkataiev.ms.k8s.accounts.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.core.MediaType;
import java.util.List;

import static lab.dkataiev.ms.k8s.accounts.util.K8SCommons.CORRELATION_ID;

@FeignClient("cards")
public interface CardsFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/c", consumes = MediaType.APPLICATION_JSON)
    List<Card> getCardsDetails(@RequestHeader(CORRELATION_ID) String correlationId, @RequestBody Customer customer);

}
