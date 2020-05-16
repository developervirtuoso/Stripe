package com.example.StripeSpring;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stripe.model.Charge;

@Controller
public class PaymentController {
	@Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("amount",10 * 100); // In cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        return "index";
    }
    
    @Autowired
    private StripeService stripeService;

    @RequestMapping(value = "/charge", method = RequestMethod.POST)
    public String chargeCard(HttpServletRequest request,Model model) throws Exception {
        String token = request.getParameter("stripeToken");
        Double amount = Double.parseDouble(request.getParameter("amount"));
        Charge charge= stripeService.chargeNewCard(token, amount);
        System.out.println("token = "+token);
        System.out.println("amount = "+amount);
        System.out.println("getApplication = "+charge.getApplication());
        System.out.println("getId = "+charge.getId());
        System.out.println("getAuthorizationCode = "+charge.getAuthorizationCode());
        System.out.println("getBalanceTransaction = "+charge.getBalanceTransaction());
        System.out.println("getCurrency = "+charge.getCurrency());
        System.out.println("getStatus = "+charge.getStatus());
        System.out.println("getCustomer = "+charge.getCustomer());
        System.out.println("getPaymentMethod = "+charge.getPaymentMethod());
        System.out.println("getReceiptEmail = "+charge.getReceiptEmail());
        System.out.println("getReview = "+charge.getReview());
        System.out.println("getStatementDescriptor = "+charge.getStatementDescriptor());
        System.out.println("getReceiptEmail = "+charge.getReceiptEmail());
        model.addAttribute("token", token);
        model.addAttribute("amount", amount);
        model.addAttribute("paymentid", charge.getId());
        model.addAttribute("transaction", charge.getBalanceTransaction());
        model.addAttribute("currency", charge.getCurrency());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("paymentmethod", charge.getPaymentMethod());
        return "result";
    }
}
