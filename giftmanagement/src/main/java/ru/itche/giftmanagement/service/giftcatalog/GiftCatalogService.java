package ru.itche.giftmanagement.service.giftcatalog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itche.giftmanagement.repository.GiftCatalogRepository;

@Service
@RequiredArgsConstructor
public class GiftCatalogService {

    private final GiftCatalogRepository giftCatalogRepository;


}
