/**
 *
 */
package lab.dkataiev.ms.k8s.accounts.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class CustomerDetails {

    private Account account;
    private List<Loan> loans;
    private List<Card> cards;


}
