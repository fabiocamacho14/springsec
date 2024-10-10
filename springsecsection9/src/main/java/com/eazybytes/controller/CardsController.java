package com.eazybytes.controller;

import com.eazybytes.model.Cards;
import com.eazybytes.repository.CardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardsController {

    private CardsRepository cardsRepository;

    @GetMapping("/myCards")
    public List<Cards> getCardsDetails(@RequestParam Integer customerId) {
        List<Cards> cardsList = cardsRepository.findByCustomerId(customerId);
        if (!cardsList.isEmpty()){
            return cardsList;
        } else {
            return null;
        }
    }
}
