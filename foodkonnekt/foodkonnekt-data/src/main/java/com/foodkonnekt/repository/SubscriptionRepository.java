package com.foodkonnekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodkonnekt.model.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    /**
     * Find by SubscriptionPlanId
     * 
     * @param subscriptionPlanId
     * @return Subscription instance
     */
    public Subscription findBySubscriptionPlanId(String subscriptionPlanId);

}
