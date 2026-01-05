package ru.itche.giftmanagement.controller.giftcatalog;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itche.giftmanagement.service.giftcatalog.GiftCatalogService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/gift")
public class GiftCatalogController {

    private final GiftCatalogService giftCatalogService;


}
