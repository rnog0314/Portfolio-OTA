package com.example.portfolio.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.example.portfolio.model.entity.ChargeRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeService {

  // application.ymlに記述したシークレットキー
  @Value("${stripe.keys.secret}")
  private String secretKey;

  // このサービスクラスが初期化されたら、secretKeyがその後のStripeの処理で使用される
  @PostConstruct
  public void init() {
    Stripe.apiKey = secretKey;
  }

  /**
   * 決済処理
   * @param chargeRequest ChargeRequest
   * @return charge Charge
   * @throws StripeException
   */
  public Charge charge(ChargeRequest chargeRequest) throws StripeException {
    Map<String, Object> chargeParams = new HashMap<>();
    chargeParams.put("amount", chargeRequest.getAmount());
    chargeParams.put("currency", chargeRequest.getCurrency());
    chargeParams.put("description", chargeRequest.getDescription());
    chargeParams.put("source", chargeRequest.getStripeToken());
    return Charge.create(chargeParams);
  }
}
