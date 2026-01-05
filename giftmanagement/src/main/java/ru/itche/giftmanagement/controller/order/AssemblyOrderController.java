package ru.itche.giftmanagement.controller.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itche.giftmanagement.dto.order.create.AssemblyOrderResponse;
import ru.itche.giftmanagement.dto.order.complete.CompleteAssemblyOrderRequest;
import ru.itche.giftmanagement.dto.order.complete.CompleteAssemblyOrderResponse;
import ru.itche.giftmanagement.dto.order.status.ChangeAssemblyOrderStatusRequest;
import ru.itche.giftmanagement.dto.order.status.ChangeAssemblyOrderStatusResponse;
import ru.itche.giftmanagement.service.order.AssemblyOrderService;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class AssemblyOrderController {

    private final AssemblyOrderService orderService;

    @PostMapping("/create-order/{letterId}")
    public ResponseEntity<AssemblyOrderResponse> createOrder(@PathVariable Long letterId) {

        AssemblyOrderResponse response = orderService.createOrderFromLetter(letterId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/complete")
    public ResponseEntity<CompleteAssemblyOrderResponse> completeOrder(
            @RequestBody CompleteAssemblyOrderRequest request) {

        CompleteAssemblyOrderResponse response = orderService.completeOrder(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping("/change-status")
    public ResponseEntity<ChangeAssemblyOrderStatusResponse> changeStatus(
            @RequestBody ChangeAssemblyOrderStatusRequest request) {
        ChangeAssemblyOrderStatusResponse response = orderService.changeStatus(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
