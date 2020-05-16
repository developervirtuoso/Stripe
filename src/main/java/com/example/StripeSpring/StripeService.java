package com.example.StripeSpring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.model.Charge;

@Service
public class StripeService {
	@Value("${STRIPE_SECRET_KEY}")
    private String API_SECRET_KEY;

    @Autowired
    public StripeService() {
        Stripe.apiKey = "sk_test_Lbxu4SAcJG7U1sV1WzUT3hmb00anSpB386";
    }

    public Charge chargeNewCard(String token, double amount) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount * 100));
        chargeParams.put("currency", "INR");
        chargeParams.put("source", token);
        Charge charge = Charge.create(chargeParams);
      
        return charge;
    }
}
